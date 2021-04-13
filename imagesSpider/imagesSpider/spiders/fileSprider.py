# -*- coding: utf-8 -*-

import scrapy


class ImageSpider(scrapy.Spider):
    name = 'file'

    def start_requests(self):
        urls = [
            'https://www.efacec.pt/en/news/'
        ]

        for url in urls:
            yield scrapy.Request(url=url,
                                 callback=self.parse)  # response.follow(link.get(), calback=self.parse_categories)

    def parse(self, response):
        list = response.xpath('//*[@id="posts-container"]/div[1]/article')

        # from imagesSpider.imagesSpider.items import ImagesSpiderItem

        links = {}
        links['file_urls'] = list.xpath('div/div[1]/ul[1]/li/div/img').xpath('@src').getall()
        links['files'] = list.xpath('div/div[1]/ul[1]/li/div/img').xpath('@src').getall()
        # items.py로 filed를 지정해주지 않으면 파일 다운로드는 되지 않는다. 하지만, setting.py에서 설정해주면 가능하다.
        print("links :", links)
        yield links
        # 스파이더가 반환하는 필드에 "image_urls"가 key 값을 포함되어져 있어야 한다.
