<template>
  <div class="card border-dark" style="max-width: auto; margin: auto; padding: auto;">
    <div class="card-body">
      <p class="card-text">&nbsp;&nbsp; 將檔案匯入至資料庫</p>
      <div class="input-group mb-3">
        <input type="file" class="form-control" @change="handleFileUpload" />
        <button class="btn btn-primary" @click="uploadFile">執行匯入</button>
      </div>
      <div v-if="uploadStatus" class="mt-2">
        <div class="alert" :class="{'alert-success': uploadStatus.includes('成功'), 'alert-danger': uploadStatus.includes('失敗')}">
          {{ uploadStatus }}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import { uploadFile } from '@/services/fileUtilApi';
export default {
  name: "FileUpload",
  data() {
    return {
      selectedFile: null,
      uploadStatus: ''
    };
  },
  methods: {
    handleFileUpload(event) {
      this.selectedFile = event.target.files[0];
    },
    async uploadFile() {
      if (!this.selectedFile) {
        this.uploadStatus = '請選擇檔案';
        return;
      }

      const formData = new FormData();
      formData.append('file', this.selectedFile);

      try {
        const uploadStatus = await uploadFile(formData);
        this.uploadStatus = '上傳成功';
      } catch (error) {
        this.uploadStatus = '上傳失敗';
      }
    }
  }
};
</script>

<style>
.file-input {
  width: 100px; 
}
</style>
