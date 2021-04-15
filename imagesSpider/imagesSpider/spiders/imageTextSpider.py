# -*- coding: utf-8 -*-

import scrapy

from scrapy_splash import SplashRequest


class Item(scrapy.Item):
    title = scrapy.Field()
    file_urls = scrapy.Field()
    files = scrapy.Field()


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


class ImageTextSpider(scrapy.Spider):
    name = 'imageText'

    # time.sleep(5)
    def start_requests(self):
        urls = [
            'https://en.sungrowpower.com/newsList/'
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
                                endpoint='render.html')

    def parse(self, response):
        headLine = response.xpath('//*[@id="form1"]/div[12]/div')
        print(headLine)

        print(headLine)
        item = {}
        # for a_tag in headLine:
        # list = 'https://www.hdec.kr' + a_tag.xpath('a/img').xpath('@src').get()
        # item['title'] = headLine.xpath('a/figcaption/dl/dd[1]/text()').getall()  # 배열을 넣어야 하는데 str을 넣고 있다는 점.
        item['file_urls'] = headLine.xpath('a/img/@src').getall()
        item['files'] = headLine.xpath('a/img/@src').getall()  # get은 String, getall은 배열로 return한다.

        print("item:", item)
        yield item
        # get은 단일 결과를 반환한다. 일치하는 항목이 여러개인 경우에는 첫번째 일치하는 내용이 반환
        # dictionary 형은 일종의 map과 같이 key값과 value 값 형태로 구성.

        # 딕셔너리형으로 만약에 변수에 list를 넣을 떄는 for+g
        # et() / for문 없이 getall
        # file_urls 및 image_urls 는 list형이어야 한다.
        # getall은 모든 결과를 list로 반환 일치하는 항목이 없다면 none
