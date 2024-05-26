# 茶叶分类微信小程序

这是一个用于茶叶分类的微信小程序项目，分为三个主要部分：

1. **微信小程序前端 (`./miniprogram-uniapp`)**：
   - 该部分用于用户交互，使用 Uniapp 编写。
   - 可以使用 HBuilder 部署。

2. **服务端 (`./server-springboot`)**：
   - 该部分用于存储用户数据和识别结果。
   - 使用 Spring Boot 编写，并使用 Gradle 进行包管理。

3. **推理端 (`./inference-flask`)**：
   - 该部分用于推理模型。
   - 我们将训练好的权重部署在 Python 的 Flask Web 容器中，通过 HTTP 与服务端进行通讯。

另外，我们使用 PyTorch 训练了用于茶叶识别的权重文件，分别采用了 ResNet34 和 VGG16 网络结构，数据集是通过互联网采集的茶叶图片。数据集项目地址：[茶叶数据集](https://github.com/songqikong/chinese_tea_dataset)。训练后的识别准确率可以达到 89.1%。

## 使用说明

1. **下载模型权重文件**：
   - 请在 [百度网盘](https://pan.baidu.com/s/1CNr7CRNAQYlxL8bqt-tKUQ?pwd=e220) 下载模型权重文件并放在 `./inference-flask/net` 目录下。

2. **部署服务端**：
   - 请使用 JDK 1.8。

3. **部署推理端**：
   - 环境要求：
     - Python 3.9.7
     - Flask 2.1.1
     - PyTorch 1.11.0
     - Torchvision 1.12.0

4. **部署**：
   - 将微信小程序前端、服务端和推理端部署在同一设备上。
   - 确保三端可以在局域网内通讯。

## 项目结构

```
├── miniprogram-uniapp      # 微信小程序前端
├── server-springboot       # 服务端
├── inference-flask         # 推理端
│   └── net                 # 模型权重文件目录
```

## 快速开始

### 前端 (微信小程序)

1. 在 HBuilder 中打开 `./miniprogram-uniapp`。
2. 按照 HBuilder 的说明部署小程序。

### 后端 (Spring Boot 服务)

1. 进入 `./server-springboot` 目录。
2. 使用 Gradle 构建并运行项目：
   ```sh
   ./gradlew bootRun
   ```

### 推理端 (Flask)

1. 进入 `./inference-flask` 目录。
2. 设置 Python 环境并安装依赖：
   ```sh
   conda create -n teaclassify
   pip install -r requirements.txt
   ```
3. 运行 Flask 应用：
   ```sh
   flask run
   ```

## 通讯设置

- 确保前端、后端和推理端都在同一设备上，并能通过局域网通讯。
- 在相应的配置文件中配置必要的 IP 地址和端口。

## 贡献者

- 项目维护者：[SongqiKong](https://github.com/songqikong)

## 许可证

本项目使用 MIT 许可证 - 详见 [LICENSE](LICENSE) 文件。

---

欢迎贡献和提交问题，如果你遇到任何问题，请随时联系！