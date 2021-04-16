# -*- coding: utf-8 -*-


import scrapy
from scrapy import FormRequest
from scrapy.utils.response import open_in_browser
from scrapy_splash import SplashRequest

LUA_SCRIPT = """
function main(splash)
    splash.private_mode_enabled = false
    splash:go(splash.args.url)
    splash:wait(2)
    html = splash:html()
    splash.private_mode_enabled = true
    return html
end
"""

script = """
function main(splash, args)
    splash.private_mode_enabled = false
    url = args.url
    assert(splash:go(url))
    assert(splash:wait(1))
    splash:set_viewport_full()
    return splash:png()
end
"""


class LoginSpider(scrapy.Spider):
    name = 'login'
    allowed_domains = ['https://quotes.toscrape.com/']
    start_urls = ['https://quotes.toscrape.com/login']

    def parse(self, response):
        print(response)
        token = response.xpath('/html/body/div/form/input[1]/@value').extract_first()
        print("token", token)

        return FormRequest.from_response(response, dont_filter=True, clickdata={'type': 'submit'}, formdata={
            'csrf_token': token,
            'username': 'test@test.com',
            'password': 'test'
        }, callback=self.after_login)

    def after_login(self, response):
        print("logged in!")
        # open_in_browser(response)

        q = response.css(".container .quote")
        quote = {}
        quote["author"] = q.css(".author::text").getall()
        quote["quote"] = q.css(".text::text").getall()

        yield quote

        splash_args = {
            'html': 1,
            'png': 1,
            'width': 600,
            'render_all': 1,
            'lua_source': script,
            'wait': 3,
        }

        # follow pagination link
        nextPageUrl = response.css('li.next > a::attr(href)').extract_first()
        print("next_page:", nextPageUrl)
        if nextPageUrl is not None:

            nextPage = nextPageUrl[6:-1]

            nextPage = int(nextPage)
            nextPageUrl = f'http://quotes.toscrape.com{nextPageUrl}'
            print("nextPageUrl", nextPageUrl)

            if nextPage < 10000:
                nextPage += 1
                yield SplashRequest(nextPageUrl, dont_filter=True, callback=self.after_login, args=splash_args,
                                    endpoint='render.html')
        else:
            print('page가 없습니다.')
