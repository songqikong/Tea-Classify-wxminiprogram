import base64
from io import BytesIO
import json

import requests
from PIL import Image
from flask import request, Flask

from test import Test

from db import *

app = Flask(__name__)


@app.route('/identify', methods=['POST'])
def identify():
    base64photo = request.form['photo']
    base64photo = base64photo.split(",")[1]
    moduleid = request.form['module']
    print(type(moduleid))

    SECRET = request.form['secret']
    if SECRET == "c656e210bde0cda4f833c5f507364d48":
        imgbytes = base64.b64decode(base64photo)
        img_stream = BytesIO(imgbytes)
        img = Image.open(img_stream)

        test = Test(img, moduleid)

        result = test.test()

        return result
    else:
        return "unauthorized"


@app.route('/wxLogin', methods=['GET'])
def wxlogin():
    JSCODE = request.args.get("code")
    # print("code is :{}".format(JSCODE))
    APPID = "wx81389333e069203f"
    SECRET = "c656e210bde0cda4f833c5f507364d48"

    url = "https://api.weixin.qq.com/sns/jscode2session" \
          "?appid={}&secret={}&js_code={}&grant_type=authorization_code".format(APPID, SECRET, JSCODE)

    if JSCODE is None:
        return "未获取到正确Code"
    else:
        res = requests.get(url)

        dict = json.loads(res.text)
        openid = dict['openid']

        user = User.query.filter_by(openid = openid).all()
        print(user)

        return dict['openid']


if __name__ == '__main__':
    app.debug = True
    app.run()
    app.run(debug=True)
