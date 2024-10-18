## 智能數據解析及擷取
> 本共用元件旨在根據設定檔的定義，從原始數據檔（row data）中精確擷取所需的欄位資訊，以便進行後續的數據處理與分析。此外，若源頭的數據長度發生變化，無需修改程式碼，只需在設定檔中調整欄位的長度，即可完成數據解析的更新，提升維護的靈活性與效率。
## 1.所用技術
- Spring Boot
- Spring Data JPA
- Hibernate
- H2 Database Engine
- Swagger
## 2.安裝指南
> 請確保您的環境中安裝有 Java 18 及 Maven 3 以上版本
```bat
# 執行步驟
> git clone https://gitlab.iisigroup.com/ps150/g-p231301/modapc/byteextractproject.git
> cd byteextractproject
> mvn clean package
> java -jar target/byteExtractProject-0.0.1-SNAPSHOT.jar
```
## 3.使用情境範例
> 系統需從大量的原始數據檔案中提取客戶資訊。透過「智能數據解析及擷取」元件，使用設定檔來定義每個資料欄位的長度，能快速且準確地解析出客戶的姓名和年齡等資訊。

- 設定檔 ./resources/config.properties
```properties
#擋頭.欄位=起始位置,擷取長度(bytes)
USER.NAME=0,15
USER.PHONE=15,10
USER.AGE=25,3
```
- 測試資料檔位置：./resources/testData/User.data 內容範例：
  (<span style="font-size:12px;">編碼格式：此範例檔案使用 UTF-8 編碼，中文字符每個佔 3 個 byte，英文字符和數字佔 1 個 byte。</span>)

```plaintext
王大同      0910123456 20
張大衛      0912123456 40
李明哲      0913123456 35
陳曉華      0914123456 28
林佳怡      0915123456 22
趙志強      0916123456 45
劉小玲      0917123456 30
黃志明      0918123456 38
許美芳      0919123456 25
周建宏      0920123456 50           
```

- 範例程式說明
```java
//調用 ImportDataUtils.getAllField 方法:
Map<String, String> importData = ImportDataUtils.getAllField("USER", line);
//擷取欄位資料
String name = importData.get("NAME");
String phone = importData.get("PHONE");
int age = Integer.parseInt(importData.get("AGE"));
```

## 4.結果展示
> 可以透過 Swagger API 進行查詢測試：
```url
http://localhost:9100/swagger-ui/index.html
```
> 或使用 Postman 進行測試：
```
 http://localhost:9100/api/user/listByFile
```
> 亦可透過命令行工具（CMD）發送請求：
```
 curl http://localhost:9100/api/user/listByFile
```
> 當執行資料提取後，系統會返回一個 JSON 格式的資料結構，包含使用者的基本資訊。以下是回傳結果的範例：
```json
{
  "data": [
    {
      "id": 1,
      "name": "王大同",
      "phone": "0910123456",
      "age": 20
    },
    {
      "id": 2,
      "name": "張大衛",
      "phone": "0912123456",
      "age": 40
    },
    {
      "id": 3,
      "name": "李明哲",
      "phone": "0913123456",
      "age": 35
    },
    {
      "id": 4,
      "name": "陳曉華",
      "phone": "0914123456",
      "age": 28
    },
    {
      "id": 5,
      "name": "林佳怡",
      "phone": "0915123456",
      "age": 22
    },
    {
      "id": 6,
      "name": "趙志強",
      "phone": "0916123456",
      "age": 45
    },
    {
      "id": 7,
      "name": "劉小玲",
      "phone": "0917123456",
      "age": 30
    },
    {
      "id": 8,
      "name": "黃志明",
      "phone": "0918123456",
      "age": 38
    },
    {
      "id": 9,
      "name": "許美芳",
      "phone": "0919123456",
      "age": 25
    },
    {
      "id": 10,
      "name": "周建宏",
      "phone": "0920123456",
      "age": 50
    }
  ],
  "totalCount": 10
}