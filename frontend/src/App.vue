<template>
  <div class="container">
    <div class="jumbotron custom-jumbotron">
      <div class="custom-container">
      <h1 class="display-5">資料動態編碼轉換系統</h1>
  </div>
  <hr>
    </div>
    <ul class="nav nav-tabs">
      <li class="nav-item">
        <router-link to="/" class="nav-link" active-class="active" exact>首頁</router-link>
      </li>
      <li class="nav-item">
        <router-link to="/fileUpload" class="nav-link" active-class="active">匯入資料庫</router-link>
      </li>
      <li class="nav-item">
        <router-link to="/users" class="nav-link" active-class="active">人員資料列表</router-link>
      </li>
    </ul>
    <br>
    <router-view></router-view>
  </div>
</template>

<script>

export default {
  name: 'app',
  components: {
  
  }
}
</script>

<style>
.error {
  color: red;
}

.error-input {
  border-color: red;
  color: red;
}

.custom-jumbotron {
    padding: 1rem 2rem; 
    margin-bottom: 1rem; 
    background-color: #e5eaeb; 
  }
  .custom-container {
    padding: 0.5rem;
  }
</style>
