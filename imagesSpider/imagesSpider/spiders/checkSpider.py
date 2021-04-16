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


class checkSpider(scrapy.Spider):
    name = 'check'

    def start_requests(self):
        urls = [
            'https://www.hyundai-robotics.com/index.html'
        ]

        # pageReuqest ==================================================================================================
        splash_args = {
            'html': 1,
            'png': 1,
            'width': 600,
            'render_all': 1,
            'lua_source': LUA_SCRIPT,
            'wait': 3,
        }
        for url in urls:
            yield SplashRequest(url=url, dont_filter=True, callback=self.parse, args=splash_args,
                                endpoint='render.html')

    def parse(self, response):
        a = response.css('html').css('a::attr(href)').getall()

        print(a)
        # print(a[0][6, -1])
        # print(str(a[0])[int(str(a[0]).find('href=')), -1])


