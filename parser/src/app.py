from flask import Flask, request
import json
import subprocess
import py_eureka_client.eureka_client as eureka_client

port = 8003
eureka_client.init(eureka_server="http://eureka-server:8070/eureka",
                   app_name="execute-service",
                   instance_port=port)

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
    app.run(debug=False, port=port, host='0.0.0.0')
