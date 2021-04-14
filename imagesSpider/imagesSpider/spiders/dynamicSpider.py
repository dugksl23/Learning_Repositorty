# -*- coding: utf-8 -*-

import scrapy
from scrapy_splash import SplashRequest

# load_page_script = """
#     function main(splash)
#         splash:set_user_agent(splash.args.ua)
#         assert(splash:go(splash.args.url))
#         splash:wait(5)
#         function wait_for(splash, condition)
#             while not condition() do
#                 splash:wait(0.5)
#             end
#         end
#         local result, error = splash:wait_for_resume([[
#             function main(splash) {
#                 setTimeout(function () {
#                     splash.resume();
#                 }, 5000);
#             }
#         ]])
#         wait_for(splash, function()
#             return splash:evaljs("document.querySelector('#user-form') != null")
#         end)
#         -- repeat
#         -- splash:wait(5))
#         -- until( splash:select('#user-form') ~= nil )
#         return {html=splash:html()}
#     end
# """

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


class SplashSpider(scrapy.Spider):
    name = 'splash'
    allowed_domains = ['quotes.toscrape.com']
    # page_number = 2

    def start_requests(self):
        urls = [
            'http://quotes.toscrape.com/js/page/1'
        ]

        splash_args = {
            'html': 1,
            'png': 1,
            'width': 600,
            'render_all': 1,
            'lua_source': script,
            'wait': 3,
        }

        for url in urls:
            yield SplashRequest(url=url,
                                callback=self.parse,
                                args=splash_args,
                                endpoint='render.html')  # response.follow(link.get(), calback=self.parse_categories)

    def parse(self, response):
        # container = response.xpath('quotes.toscrape.com')

        # print("container", container)
        # links = {}

        # links['title'] = container.xpath('/a/div[2]/header/ht/text()').getall()
        # links['file_urls'] = container.xpath('a/div[1]/div/img').xpath('@src').getall()
        # links['files'] = container.xpath('a/div[1]/div/img').xpath('@src').getall()
        # items.py로 filed를 지정해주지 않으면 파일 다운로드는 되지 않는다. 하지만, setting.py에서 설정해주면 가능하다.

        # for q in response.css(".container .quote"):
        q = response.css(".container .quote")
        quote = {}
        quote["author"] = q.css(".author::text").getall()
        quote["quote"] = q.css(".text::text").getall()

        yield quote
        # 스파이더가 반환하는 필드에 "image_urls"가 key 값을 포함되어져 있어야 한다.
        # download delay를 설정해줘야 spider가 정지당하는 경우를 피할 수 있다.
        # getall 단위로 할 시에는 for문은 필요가 없으며, 해당 태그의 모든 것을 가져와서 배열로 담아서 return 한다.
        # 동적 페이지 - PhantomJS
        # scrapy pagenatedPage - scrapy splash

        # follow pagination link
        nextPageUrl = response.css('li.next > a::attr(href)').extract_first()
        print("next_page", nextPageUrl)
        nextPage = nextPageUrl[9:-1]
        nextPage = int(nextPage)
        nextPageUrl = f'http://quotes.toscrape.com{nextPageUrl}'
        print("nextPageUrl", nextPageUrl)
        if nextPage < 11:
            nextPage += 1
            yield SplashRequest(nextPageUrl, callback=self.parse)
