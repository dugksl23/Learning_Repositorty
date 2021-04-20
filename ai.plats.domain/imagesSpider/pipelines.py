# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://docs.scrapy.org/en/latest/topics/item-pipeline.html


# useful for handling different item types with a single interface
from scrapy.exporters import JsonItemExporter, CsvItemExporter


class ImagesSpiderPipeline:

    def __init__(self):
        self.file = open('../assets/news.csv', 'wb')
        self.exporter = CsvItemExporter(self.file, encoding='utf-8')
        self.exporter.start_exporting()

    def close_spider(self, spider):
        self.exporter.finish_exporting()
        self.file.close()

    def process_item(self, item, spider):
        self.exporter.export_item(item)
        return item


class JsonPipeline(object):
    def __init__(self):
        self.file = open("../assets/news.json", 'wb')
        self.exporter = JsonItemExporter(self.file, encoding='utf-8')
        self.exporter.start_exporting()

    def close_spider(self, spider):
        self.exporter.finish_exporting()
        self.file.close()

    def process_item(self, item, spider):
        self.exporter.export_item(item)
        return item


class CustomImagePipeline(ImagesSpiderPipeline):

    def file_path(self, request, response=None, info=None, *, item=None):
        # self.file = open('../assets/news.json', 'wb')
        self.exporter.start_exporting()
        return request.url.split('/')[-1]


