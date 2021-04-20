# -*- coding: utf-8 -*-

import scrapy

from scrapy_splash import SplashRequest  # js에서 렌더링된 html을 스크랩 가능 library

# from scrapy.dupefilter import RFPDupeFilter

# 프로그램 내부에 내장 가능한 스크립트 언어
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

splash_args = {  # splash Request옵션, js 이벤트 할당 가능.
    # 'js = string.format("document.querySelector('#sxxz > li:nth-child(3) > a').click();", args.page)'
    'html': 1,
    'png': 1,
    'width': 600,
    'render_all': 1,
    'lua_source': LUA_SCRIPT,
    'wait': 1,
}


class checkSpider(scrapy.Spider):
    # global requestUrl
    name = 'check'
    allow_domains = 'https://www.hyundai-robotics.com'
    setLinkList = set([])  # 중복 제거된 url setList
    linkDic = ['#', '#gnb', '/index.html', '/member/login.html', '/member/join.html', 'pdf',
               '#n' '../', '..'
                           '.png', '.jpg', 'gif', 'png', 'jpg', 'gif', '#Submenu', 'hrms', '/hrms',
               '#contents']  # 불필요한 링크에 대한 dictionary list이며, 정확하게 동일한 글자여야 한다.

    def start_requests(self):

        urls = [
            'https://www.hyundai-robotics.com'
        ]

        # pageReuqest ==================================================================================================

        for url in urls:
            yield SplashRequest(url=url, dont_filter=True, callback=self.parse, args=splash_args,
                                endpoint='render.html')

    def parse(self, response):

        if response.status == 200:

            urls = response.css('html').css('a::attr(href)').getall()
            # src = response.css('html').css('img::attr(src)').getall()
            # for url in src:
            #     urls.append(url)

            for index, url in enumerate(
                    # list item 별로 "http:"가 있는 지 검사. #for in enermurate : for문에서 반복되는 구간의 현재 위치를 알려주는 함수, 인덱스를 return 한다.
                    urls):
                if url not in checkSpider.linkDic:
                    if not (url.startswith("http://") or url.startswith('https"//') or url.startswith('www.')):
                        url = response.url + url
                        checkSpider.setLinkList.add(url)

                    elif (url.startswith('http://') or url.startswith('https"//') or url.startswith(
                            'www.')) and (url.startswith(checkSpider.allow_domains) and (
                            url.endswith('.html') or url.endswith('.jsp') or url.endswith('.com') or url.endswith(
                        '.co.kr'))):
                        checkSpider.setLinkList.add(url)

            return self.parseMultiplePage(checkSpider.setLinkList)
        else:
            print("error")

    def parseMultiplePage(self, response):

        for url in response:
            yield SplashRequest(url=url, dont_filter=True, callback=self.parseRequestEachPage, args=splash_args,
                                endpoint='render.html')

    # 현재 요청된 페이지의 모든 url을 취득
    def parseRequestEachPage(self, response):
        # print(response.url)

        urls = response.css('html').css('a::attr(href)').getall()
        # src = response.css('html').css('img::attr(src)').getall()
        # for url in src:
        #     urls.append(url)

        for index, url in enumerate(urls):
            if url not in checkSpider.linkDic:
                if not (url.startswith("http://") or url.startswith('https"//') or url.startswith('www.')):
                    url = response.url + url
                    checkSpider.setLinkList.add(url)

                elif (url.startswith('http://') or url.startswith('https"//') or url.startswith(
                        'www.')) and (url.startswith(checkSpider.allow_domains) and (
                        url.endswith('.html') or url.endswith('.jsp') or url.endswith('.com') or url.endswith(
                    '.co.kr'))):
                    checkSpider.setLinkList.add(url)

        for index, url in enumerate(checkSpider.setLinkList):
            if url.startswith('../') or url.startswith('#'):
                checkSpider.setLinkList.remove(index)
        print(checkSpider.setLinkList)

        # dictionary = dict.fromkeys(checkSpider.setLinkList)
        # print(len(dictionary))

# process = CrawlerProcess()
# process.crawl(checkSpider)
# process.start()
