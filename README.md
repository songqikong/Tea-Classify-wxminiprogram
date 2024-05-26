# Tea Classification WeChat Mini Program

[中文](https://github.com/songqikong/Tea-Classify-wxminiprogram/blob/main/README_CN.md)

This project is a WeChat mini program for classifying tea leaves, divided into three main components:

1. **WeChat Mini Program Frontend (`./miniprogram-uniapp`)**: 
   - This part is for user interaction and is developed using Uniapp. 
   - It can be deployed using HBuilder.

2. **Server Backend (`./server-springboot`)**:
   - This part stores user data and recognition results.
   - It is developed using Spring Boot and uses Gradle for package management.

3. **Inference Endpoint (`./inference-flask`)**:
   - This part handles the inference model.
   - The trained weights are deployed in a Python Flask web container, communicating with the server via HTTP.

Additionally, we used PyTorch to train the weights for tea recognition. The ResNet34 and VGG16 network structures were used, and the dataset consists of tea leaf images collected from the internet. The project repository for the dataset is available [here](https://github.com/songqikong/chinese_tea_dataset). The trained model achieves an accuracy of 89.1%.

## Usage Instructions

1. **Download Model Weights**:
   - Please download the model weights from [Baidu Cloud](https://pan.baidu.com/s/1CNr7CRNAQYlxL8bqt-tKUQ?pwd=e220) and place them in the `./inference-flask/net` directory.

2. **Deploying the Server Backend**:
   - Ensure you are using JDK 1.8.

3. **Deploying the Inference Endpoint**:
   - Environment requirements:
     - Python 3.9.7
     - Flask 2.1.1
     - PyTorch 1.11.0
     - Torchvision 1.12.0

4. **Deployment**:
   - Deploy the WeChat mini program frontend, server backend, and inference endpoint on the same device.
   - Ensure that all three components can communicate within the local network.

## Project Structure

```
├── miniprogram-uniapp      # WeChat Mini Program Frontend
├── server-springboot       # Server Backend
├── inference-flask         # Inference Endpoint
│   └── net                 # Directory for model weights
```

## Getting Started

### Frontend (WeChat Mini Program)

1. Open `./miniprogram-uniapp` in HBuilder.
2. Follow the HBuilder instructions to deploy the mini program.

### Backend (Spring Boot Server)

1. Navigate to `./server-springboot`.
2. Build and run the project using Gradle:
   ```sh
   ./gradlew bootRun
   ```

### Inference Endpoint (Flask)

1. Navigate to `./inference-flask`.
2. Set up the Python environment and install the dependencies:
   ```sh
   conda create -n teaclassify
   pip install -r requirements.txt
   ```
3. Run the Flask app:
   ```sh
   flask run
   ```

## Communication Setup

- Ensure the frontend, backend, and inference endpoint are all on the same device and can communicate over the local network.
- Configure the necessary IP addresses and ports in the respective configuration files.

## Contributors

- Project Maintainer: [SongqiKong](https://github.com/songqikong)

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

Feel free to contribute and open issues if you encounter any problems!