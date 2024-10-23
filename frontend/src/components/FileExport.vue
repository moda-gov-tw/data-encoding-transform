<template>
  <div class="card border-dark" style="max-width: auto; margin: auto; padding: auto;">
    <div class="card-body">
      <!-- 表單元素 -->
      <label for="alignment">靠左靠右對齊：</label>
      <select id="alignment" v-model="alignment">
          <option value="靠左">靠左</option>
          <option value="靠右">靠右</option>
      </select><br><br>

      <label for="filled">填充字元：</label>
      <input type="text" id="filled" v-model="filled" required><br><br>

      <label for="charset">編碼：</label>
      <select id="charset" v-model="charset">
          <option value="UTF-8">UTF-8</option>
          <option value="BIG5">BIG5</option>
      </select><br><br>

      <!-- 按鈕觸發請求 -->
      <div class="input-group mb-3">
        <button class="btn btn-primary" @click="fileExport">匯出資料至檔案</button>
      </div>

      <!-- 匯出狀態顯示 -->
      <div v-if="exportStatus" class="mt-2">
        <div class="alert" :class="{'alert-success': exportStatus.includes('成功'), 'alert-danger': exportStatus.includes('失敗')}">
          {{ exportStatus }}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "FileExport",
  data() {
    return {
      alignment: '靠左',  // 預設為靠左對齊
      filled: '',         // 使用者輸入的填充字符
      charset: 'UTF-8',   // 預設字符集為 UTF-8
      exportStatus: ''    // 匯出狀態
    };
  },
  methods: {
    async fileExport() {
      try {
        // 發送 POST 請求到後端 API
        const response = await fetch('http://localhost:4000/api/fileExport', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          },
          body: new URLSearchParams({
            alignment: this.alignment,
            filled: this.filled,
            charset: this.charset
          })
        });

        // 根據後端響應的結果顯示匯出狀態
        if (response.ok) {
          // 匯出成功，重定向到文件下載 URL
          window.location.href = 'http://localhost:4000/api/download';
          this.exportStatus = '匯出成功';
        } else {
          this.exportStatus = '匯出失敗';
        }
      } catch (error) {
        // 捕獲異常並更新匯出狀態
        this.exportStatus = '匯出失敗';
        console.error(error);
      }
    }
  }
};
</script>

<style>
/* 自定義樣式 */
.file-input {
    width: 100px;
}
</style>
