FROM python:3.9

WORKDIR /src

COPY requirements.txt /src

RUN pip3 install --upgrade pip -r requirements.txt

COPY . /src

EXPOSE 8003

CMD [ "python3", "./app.py" ]