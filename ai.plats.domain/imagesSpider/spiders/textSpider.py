# -*- coding: utf-8 -*-

import scrapy


class textSpider(scrapy.Spider):
    name = 'text'

    def start_requests(self):
        urls = [
            'https://www.hyundai-robotics.com/product/product1.html?p=1'
        ]

        for url in urls:
            yield scrapy.Request(url=url,
                                 callback=self.parse)  # response.follow(link.get(), calback=self.parse_categories)

    def parse(self, response):
        headLine = response.xpath('//*[@id="frm"]/div/div[1]/table/tbody/tr')
        item = {}

        for a_tag in headLine:

            item['num'] = a_tag.xpath('td[1]/span/text()').getall()
            item['title'] = a_tag.xpath('td[2]/a/text()').getall()
            item['regDate'] = a_tag.xpath('td[3]/a/text()').getall()
            item['count'] = a_tag.xpath('td[4]/a/text()').getall()
            print("item :", item)

        # items.py로 filed를 지정해주지 않으면 파일 다운로드는 되지 않는다. 하지만, setting.py에서 설정해주면 가능하다.
        print("imageText :", item)
        yield item
        # 스파이더가 반환하는 필드에 "image_urls"가 key 값을 포함되어져 있어야 한다.
        # download delay
