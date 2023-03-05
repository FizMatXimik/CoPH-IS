import json
import os
import requests
from bs4 import BeautifulSoup
import datetime
import random
import csv
from time import sleep


class ParserStroim_dom_rf:

    def __init__(self):
        self.session = requests.Session()
        self.url = 'https://xn--h1aieheg.xn--d1aqf.xn--p1ai/api/v1/contractors?page=0&size=1000&sort=name%2CASC'
        self.session.headers = {
            'User-Agent': 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.75 Safari/537.36',
            'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9',
            'Accept-Language': 'ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7',
        }
        self.number_of_pages = 1

    def get_request(self, page, retry=5):
        try:
            response = self.session.get(url=self.url, headers=self.session.headers)
        except Exception as ex:
            if retry:
                print(f"[INFO] retry={retry} => {self.url}")
                return self.get_request(page, retry=(retry - 1))
            else:
                raise
        else:
            return response

    def get_data(self):
        cur_time = datetime.datetime.now().strftime('%d_%m_%Y')

        folder_name = f"./data/stroim_dom_rf"

        if os.path.exists(folder_name):
            print("Папка уже существует!")
        else:
            os.mkdir(folder_name)

        response = self.get_request(page='1')
        json_data = json.loads(response.text)
        print("Success!!!")

        with open(f"{folder_name}/data_{cur_time}.json", "a", encoding="utf-8") as file:
            json.dump(json_data, file, indent=4, ensure_ascii=False)

        # https://xn--h1aieheg.xn--d1aqf.xn--p1ai/api/v1/contractors/47
        # ссылка на конкретную компанию подрядную
        # такую же штуку сделать с ресурсообеспечивающими компаниями


def main():
    parser_stroim_dom_rf = ParserStroim_dom_rf()
    parser_stroim_dom_rf.get_data()


if __name__ == '__main__':
    main()
