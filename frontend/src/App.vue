<template>
  <div class="container">
    <div class="jumbotron custom-jumbotron">
      <div class="custom-container">
        <h1 class="display-6 custom-title">資料動態編碼轉換系統</h1>
      </div>
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
      <li class="nav-item">
        <router-link to="/fileExport" class="nav-link" active-class="active">匯出資料庫</router-link>
      </li>
    </ul>
    <router-view></router-view>
  </div>
</template>

<style>
.custom-jumbotron {
  margin-bottom: 5;
  padding-bottom: 0;
}

.container {
  max-width: 900px;
  margin: 0 auto;
}

.custom-jumbotron {
   background-color: #5c9ee0;
  padding: 10px 20px;
  min-height: 30px;
  display: flex;
  align-items: center;
  justify-content: center; 
  border-radius: 5px;
}

.custom-title {
  font-size: 1.5rem;
  margin: 0;
  text-align: center;
}

.nav-item .nav-link:hover {
  background-color: #d39c37;
}
</style>
