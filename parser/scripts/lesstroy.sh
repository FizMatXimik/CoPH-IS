#!/usr/bin/env bash
set -e
cd /home/aleksandr/CoPH-IS/parser/scripts
source "./../CoPH-IS_parser/bin/activate"
python ./../parsers/lesstroy.py
