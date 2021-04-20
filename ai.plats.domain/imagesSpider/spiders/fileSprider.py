# -*- coding: utf-8 -*-

import scrapy

from scrapy_splash import SplashRequest


class FileSpider(scrapy.Spider):
    name = 'file'

    def start_requests(self):
        urls = [
            'https://www.efacec.pt/en/news/'
        ]

        for url in urls:
            yield SplashRequest(url=url,
                                callback=self.parse)  # response.follow(link.get(), calback=self.parse_categories)

    def parse(self, response):
        container = response.xpath('//*[@id="posts-container"]/div[1]/article')

        # from imagesSpider.imagesSpider.items import ImagesSpiderItem

        links = {}

        links['file_urls'] = container.xpath('div/div[1]/ul[1]/li/div/img').xpath('@src').getall()
        links['files'] = container.xpath('div/div[1]/ul[1]/li/div/img').xpath('@src').getall()
        # items.py로 filed를 지정해주지 않으면 파일 다운로드는 되지 않는다. 하지만, setting.py에서 설정해주면 가능하다.
        print("links :", links)

        yield links
        # 스파이더가 반환하는 필드에 "image_urls"가 key 값을 포함되어져 있어야 한다.
        # download delay를 설정해줘야 spider가 정지당하는 경우를 피할 수 있다.
        # getall 단위로 할 시에는 for문은 필요가 없으며, 해당 태그의 모든 것을 가져와서 배열로 담아서 return 한다.
