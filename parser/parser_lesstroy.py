import requests
from bs4 import BeautifulSoup
import datetime
import random
import csv
from time import sleep


class ParserLesstroy:

    def __init__(self):
        self.session = requests.Session()
        self.url = 'https://lesstroy.net/contractors/?pageNumber='
        self.session.headers = {
            'User-Agent': 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.75 Safari/537.36',
            'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9',
            'Accept-Language': 'ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7',
        }
        self.number_of_pages = 1

    def get_request(self, page, retry=5):
        try:
            response = self.session.get(url=self.url + page, headers=self.session.headers)
        except Exception as ex:
            if retry:
                print(f"[INFO] retry={retry} => {url}")
                return self.get_request(page, retry=(retry - 1))
            else:
                raise
        else:
            return response

    def get_data(self):
        cur_time = datetime.datetime.now().strftime('%d_%m_%Y')

        with open(f"data/lesstroy/data_{cur_time}.csv", "w", encoding='utf-8') as file:
            writer = csv.writer(file)
            writer.writerow(
                (
                    'company_logo',
                    'company_name',
                    'company_phones_list',
                    'company_address',
                    'company_description'
                )
            )

        response = self.get_request(page='1')
        soup = BeautifulSoup(response.text, 'lxml')

        try:
            pagination = soup.find('div', class_='pagination').find_all('li')[-1].find('a').get('href')
            self.number_of_pages = int(pagination.replace('/contractors/?pageNumber=', ''))
        except Exception as ex:
            self.number_of_pages = 1

        for i in range(1, self.number_of_pages + 1):

            try:
                response = self.get_request(page=str(i))
            except Exception as ex:
                continue

            soup = BeautifulSoup(response.text, 'lxml')

            list_of_companies = soup.find_all('div', class_='item__i')

            for company in list_of_companies:

                try:
                    company_logo ='https:' + company.find('div', class_='item-logo').find('img').get('src')
                except Exception as ex:
                    company_logo = 'Логотипа компании нет!'

                try:
                    company_name =company.find('div', class_='item-name').find('span').text.strip()
                except Exception as ex:
                    company_name = 'Названия компании нет!'

                try:
                    company_phones = company.find('div', class_='phones').find_all('p')
                    company_phones_list = []
                    for phone in company_phones:
                        company_phones_list.append(phone.text)
                except Exception as ex:
                    company_phones_list = []

                try:
                    company_address = company.find('div', class_='address').text.strip()
                except Exception as ex:
                    company_address = 'Адреса компании нет!'

                try:
                    company_description = company.find('div', class_='annotation').text.strip()
                except Exception as ex:
                    company_description = 'Описания компании нет'

                with open(f"data/lesstroy/data_{cur_time}.csv", "a", encoding='utf-8') as file:
                    writer = csv.writer(file)
                    writer.writerow(
                        (
                            company_logo,
                            company_name,
                            ', '.join(company_phones_list),
                            company_address,
                            company_description
                        )
                    )

            print(f"# Страница {i}/{self.number_of_pages} ...")
            sleep(random.randrange(2, 4))




