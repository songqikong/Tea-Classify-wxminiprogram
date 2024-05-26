from flask import Flask
from flask_sqlalchemy import SQLAlchemy
import pymysql

pymysql.install_as_MySQLdb()

app = Flask(__name__)


class Config(object):
    """配置参数"""
    # 设置连接数据库的URL
    user = 'root'
    password = '020806'
    database = 'tea_wx_program'
    app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql://%s:%s@127.0.0.1:3306/%s' % (user, password, database)

    # 设置sqlalchemy自动更跟踪数据库
    SQLALCHEMY_TRACK_MODIFICATIONS = True

    # 查询时会显示原始SQL语句
    app.config['SQLALCHEMY_ECHO'] = True

    # 禁止自动提交数据处理
    app.config['SQLALCHEMY_COMMIT_ON_TEARDOWN'] = False


# 读取配置
app.config.from_object(Config)

# 创建数据库sqlalchemy工具对象
db = SQLAlchemy(app)


class User(db.Model):
    # 定义表名
    __tablename__ = 'user'
    # 定义字段
    openid = db.Column(db.String(255), primary_key=True)
    ava_src = db.Column(db.String(255))

    def __init__(self, openid, ava_src):
        self.openid = openid
        self.ava_src = openid


class Record(db.Model):
    # 定义表名
    __tablename__ = 'record'
    # 定义字段
    openid = db.Column(db.String(255), db.ForeignKey('user.open'))
    img_src = db.Column(db.String(255))
    label = db.Column(db.Integer)
    id = db.Column(db.Integer, primary_key=True)

    def __init__(self, openid, img_src, label, id):
        self.openid = openid
        self.img_src = img_src
        self.label = label
        self.id = id
