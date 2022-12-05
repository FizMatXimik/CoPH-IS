import requests
from bs4 import BeautifulSoup
import lxml


class Parser:

    def __init__(self):
        self.session = requests.Session()
        self.session.headers = {
            'User-Agent': 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.75 Safari/537.36',
            'Accept-Language': 'ru',
        }

    def hello_parser(self):
        print('Hello, parser!')

