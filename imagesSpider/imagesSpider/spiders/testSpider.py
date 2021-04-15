# -*- coding: utf-8 -*-

import scrapy
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


class SplashSpider(scrapy.Spider):
    name = 'test'
    allowed_domains = ['https://selinc.com/']

    # page_number = 2

    def start_requests(self):
        urls = [
            'https://selinc.com/company/news/?Type.Corporate%20News'
        ]

        splash_args = {
            'html': 1,
            'png': 1,
            'width': 600,
            'render_all': 1,
            'lua_source': LUA_SCRIPT,
            'wait': 3,
        }

        for url in urls:
            yield SplashRequest(url=url,
                                callback=self.parse,
                                args=splash_args,
                                endpoint='render.html')  # response.follow(link.get(), calback=self.parse_categories)

    def parse(self, response):
        q = response.css('#menuId98280')
        print(q)
        q = response.css('/#menuId98280')
        print(q)

        # quote = {}
        # quote["author"] = q.xpath("div/div/div[1]/span/a/text()").getall()
        # quote["quote"] = q.xpath("div/div/div[1]/span/div[1]/small/div/text()").getall()
        #
        # yield quote
        # 스파이더가 반환하는 필드에 "image_urls"가 key 값을 포함되어져 있어야 한다.
        # download delay를 설정해줘야 spider가 정지당하는 경우를 피할 수 있다.
        # getall 단위로 할 시에는 for문은 필요가 없으며, 해당 태그의 모든 것을 가져와서 배열로 담아서 return 한다.
        # 동적 페이지 - PhantomJS
        # scrapy pagenatedPage - scrapy splash

        # follow pagination link
        # nextPageUrl = response.css('li.next > a::attr(href)').extract_first()
        # print("next_page", nextPageUrl)
        # nextPage = nextPageUrl[9:-1]
        # nextPage = int(nextPage)
        # nextPageUrl = f'http://quotes.toscrape.com{nextPageUrl}'
        # print("nextPageUrl", nextPageUrl)
        # if nextPage < 11:
        #     nextPage += 1
        #     yield SplashRequest(nextPageUrl, callback=self.parse)
