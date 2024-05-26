import json

import torch
from PIL import Image
from torchvision import transforms


class Test:
    def __init__(self, img, moduleid):
        self.img = img
        self.moduleid = moduleid

    def maxTensor(self, array):
        max = 0
        for idx in range(1 ,20):
            if(array[idx] > array[max]):
                max = idx
        return max

    def test(self):
        # 导入中文标签
        global module
        file = open("D:\\Coding\\PythonWeb\\Tea_Classify_\\lable.json", 'r')
        dict = json.load(file)
        img = self.img

        # 测试用transform
        transforms_test = transforms.Compose([
            transforms.Resize(500),
            transforms.CenterCrop(500),
            transforms.Resize((224, 224)),
            transforms.ToTensor(),
            transforms.Normalize((0.4914, 0.4822, 0.4465), (0.2023, 0.1994, 0.2010))
        ])

        if img != None:
            # 加载网络
            if self.moduleid == "0":
                module = torch.load("D:\\Coding\\PythonWeb\\Tea_Classify_\\net\\resnet.pth", map_location=torch.device('cpu'))
            if self.moduleid == "1":
                module = torch.load("D:\\Coding\\PythonWeb\\Tea_Classify_\\net\\vgg.pth", map_location=torch.device('cpu'))
            module.eval()

            img = transforms_test(img)
            img = torch.reshape(img, (1, 3, 224, 224))

            with torch.no_grad():
                output = module(img)
            output = output[0]
            print(output)

            max = self.maxTensor(output)
            temp = output[max].item()
            if temp < 15 and self.moduleid == "0":
                # print("-1")
                return "-1"
            output[max] = 0
            second = self.maxTensor(output)
            output[second] = 0
            last = self.maxTensor(output)

            return "{},{},{}".format(max,second,last)

            # output = str(output)
            # for idx in range(len(output) - 1):
            #     # print(idx)
            #     if output[idx] == "]":
            #         output = output[8:idx]
            # return "编号：{} ， 名字为：{}".format(output, dict[str(output)])


if __name__ == '__main__':
    url = "D:\\Coding\\PythonWeb\\Tea_Classify_\\img\\liushu.jpg"
    img = Image.open(url)
    test = Test(img)
    result = test.test()
    print(result)
