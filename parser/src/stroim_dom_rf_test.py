import json
import os

import psycopg2
import requests
from bs4 import BeautifulSoup
import datetime
import random
import csv
from time import sleep
import uuid


class ParserStroim_dom_rf:

    def __init__(self):
        self.session = requests.Session()
        self.url = 'https://xn--h1aieheg.xn--d1aqf.xn--p1ai/api/v1/contractors?page=0&size=1000&sort=name%2CASC'
        self.company_base_url = 'https://xn--h1aieheg.xn--d1aqf.xn--p1ai/api/v1/contractors/'
        self.session.headers = {
            'User-Agent': 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.75 Safari/537.36',
            'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9',
            'Accept-Language': 'ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7',
        }
        self.company_urls_list = []

    def get_request(self, url, retry=5):
        try:
            response = self.session.get(url=url, headers=self.session.headers)
        except Exception as ex:
            if retry:
                print(f"[INFO] retry={retry} => {url}")
                return self.get_request(url, retry=(retry - 1))
            else:
                raise
        else:
            return response

    def get_data(self):
        cur_time = datetime.datetime.now().strftime('%d_%m_%Y_%H:%M:%S')

        folder_name = f"./data/stroim_dom_rf"

        if not os.path.exists(folder_name):
            os.mkdir(folder_name)

        response = self.get_request(url=self.url)
        json_data = json.loads(response.text)
        print("Список компаний получен")

        # with open(f"{folder_name}/data_{cur_time}.json", "a", encoding="utf-8") as file:
        #     json.dump(json_data, file, indent=4, ensure_ascii=False)

        # data = ""
        # with open(f"{folder_name}/data_26_03_2023_20:53:54.json", "r", encoding="utf-8") as file:
        #     data = json.load(file)

        for company in json_data["payload"]:
            self.company_urls_list.append(self.company_base_url + str(company["id"]))

        # print(self.company_urls_list)
        conn = None
        try:
            conn = psycopg2.connect(
                host="localhost",
                port=5445,
                database="data_service",
                user="admin",
                password="admin")

            i = 0
            for company_url in self.company_urls_list[158:]:
                sleep(random.randrange(2, 4))
                response = self.get_request(company_url)
                print(f"# {company_url}")
                i += 1
                json_data = json.loads(response.text)
                #
                # with open(f"{folder_name}/companies/company_info_{json_data['payload']['id']}.json", "a",
                # encoding="utf-8") as file: json.dump(json_data, file, indent=4, ensure_ascii=False)

                # data = ""
                # with open(f"{folder_name}/companies/company_info_21.json", "r", encoding="utf-8") as file:
                #     data = json.load(file)

                company_info = json_data["payload"]

                try:
                    name = company_info["name"]
                except Exception as ex:
                    name = None
                try:
                    phone = company_info["phone"]
                except Exception as ex:
                    phone = None
                try:
                    email = company_info["email"]
                except Exception as ex:
                    email = None
                try:
                    website = company_info["website"]
                except Exception as ex:
                    website = None
                try:
                    inn = company_info["inn"]
                except Exception as ex:
                    inn = None
                try:
                    kpp = company_info["kpp"]
                except Exception as ex:
                    kpp = None
                try:
                    ogrn = company_info["ogrn"]
                except Exception as ex:
                    ogrn = None
                try:
                    legalAddress = company_info["legalAddress"]
                except Exception as ex:
                    legalAddress = None
                try:
                    fullName = company_info["fullName"]
                except Exception as ex:
                    fullName = None
                try:
                    description = ""
                except Exception as ex:
                    description = None

                sql = """INSERT INTO companies (id, name, phone, email, home_url, type_id, inn, kpp, ogrn, address, 
                full_name, description, created_at) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s) """
                record_to_insert = (str(uuid.uuid4()),
                                    name,
                                    phone,
                                    email,
                                    website,
                                    1,
                                    inn,
                                    kpp,
                                    ogrn,
                                    legalAddress,
                                    fullName,
                                    description,
                                    datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S'))

                cur = conn.cursor()
                cur.execute(sql, record_to_insert)
                conn.commit()
                count = cur.rowcount
                print(f"{i}/{len(self.company_urls_list)},")
                cur.close()

            print('Готово!')

        except Exception as error:
            print(error)

        finally:
            if conn is not None:
                conn.close()
                print('Database connection closed.')

        # такую же штуку сделать с ресурсообеспечивающими компаниями


def main():
    parser_stroim_dom_rf = ParserStroim_dom_rf()
    parser_stroim_dom_rf.get_data()


if __name__ == '__main__':
    main()
