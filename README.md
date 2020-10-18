## ...？(´・ω・｀)
Ktor Clientを用いた通信でデータのクラス内に何とかステータスコードを入れれないか検討してみた

### 実行結果
```
Response(status=200, data=User(id=1, name=MockUser))
Response(status=200, data=Cpu(name=Ryzen 5 3600, core=6, thread=12, speed=4.2, tdp=65))
Response(status=200, data=[Cpu(name=Ryzen 7 3700X, core=8, thread=16, speed=4.4, tdp=65), Cpu(name=Ryzen 5 3600, core=6, thread=12, speed=4.2, tdp=65), Cpu(name=Ryzen 5 3500, core=6, thread=6, speed=4.1, tdp=65), Cpu(name=Core i5 10400F, core=6, thread=12, speed=4.3, tdp=65), Cpu(name=Core i7 10700K, core=8, thread=16, speed=5.1, tdp=125), Cpu(name=Core i5 10400, core=6, thread=12, speed=4.3, tdp=65)])
Response(status=200, data=Storage(maker=TOSHIBA, capacity=1099511627776, type=SSHD))
Response(status=200, data=[Storage(maker=WESTERN DIGITAL, capacity=6597069766656, type=HDD), Storage(maker=crucial, capacity=536870912000, type=SSD), Storage(maker=Samsung, capacity=1099511627776, type=NVM_EXPRESS), Storage(maker=TOSHIBA, capacity=1099511627776, type=SSHD)])
Response(status=200, data=Cpu(name=Core i7 10700K, core=8, thread=16, speed=5.1, tdp=125))
Response(status=200, data=[Cpu(name=Ryzen 7 3700X, core=8, thread=16, speed=4.4, tdp=65), Cpu(name=Ryzen 5 3600, core=6, thread=12, speed=4.2, tdp=65), Cpu(name=Ryzen 5 3500, core=6, thread=6, speed=4.1, tdp=65), Cpu(name=Core i5 10400F, core=6, thread=12, speed=4.3, tdp=65), Cpu(name=Core i7 10700K, core=8, thread=16, speed=5.1, tdp=125), Cpu(name=Core i5 10400, core=6, thread=12, speed=4.3, tdp=65), Storage(maker=WESTERN DIGITAL, capacity=6597069766656, category=HDD), Storage(maker=crucial, capacity=536870912000, category=SSD), Storage(maker=Samsung, capacity=1099511627776, category=NVM_EXPRESS), Storage(maker=TOSHIBA, capacity=1099511627776, category=SSHD)])
Response(status=401, data=User(id=0, name=))
Response(status=500, data=Cpu(name=, core=0, thread=0, speed=0.0, tdp=0))
Response(status=404, data=Storage(maker=, capacity=0, type=UNKNOWN))
Response(status=401, data=Message(message=401 Unauthorized))
Response(status=500, data=Message(message=500 Internal Server Error))
Response(status=404, data=Message(message=404 Not Found))
```
