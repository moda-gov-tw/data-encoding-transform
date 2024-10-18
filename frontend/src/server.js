const express = require('express');
const multer = require('multer');
const path = require('path');

// 設定檔案儲存位置到 D:\home
const storage = multer.diskStorage({
  destination: (req, file, cb) => {
    cb(null, 'D:/home'); // 設定儲存的資料夾
  },
  filename: (req, file, cb) => {
    cb(null, file.originalname); // 使用原檔案名稱儲存
  }
});

// 初始化 multer
const upload = multer({ storage: storage });

const app = express();
const port = 8888; // 定義伺服器執行的端口

// 處理上傳檔案的 API
app.post('/api/upload', upload.single('file'), (req, res) => {
  if (!req.file) {
    return res.status(400).send('No file uploaded.');
  }
  res.send(`File uploaded successfully: ${req.file.originalname}`);
});

// 啟動伺服器
app.listen(port, () => {
  console.log(`Server is running on http://localhost:${port}`);
});
