from flask import Flask, request
import json
import subprocess

app = Flask(__name__)


@app.route('/execute/<sh_name>')
def hello_world(sh_name):
    process = subprocess.Popen(["python3", f"./{sh_name}.py"], stdout=subprocess.PIPE, text=True)
    exit_code = process.wait()
    output, error = process.communicate()
    jsonString = json.dumps({"output": str(output), "error": str(error), "exit_code": str(exit_code)}, indent=4,
                            ensure_ascii=False)
    return jsonString, 200


if __name__ == "__main__":
    app.run(debug=False, port=8003, host='0.0.0.0')
