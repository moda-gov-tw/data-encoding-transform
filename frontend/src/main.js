import { createApp } from 'vue';
import App from './App.vue';
import { createRouter, createWebHistory } from 'vue-router';
import { routes } from './routes'; 
import Vuelidate from '@vuelidate/core'; 
import axios from 'axios'; 

const router = createRouter({
  history: createWebHistory(),
  routes
});
const app = createApp(App);
app.use(Vuelidate);
app.use(router);
app.config.globalProperties.$http = axios.create({
  baseURL: 'http://localhost:5000/api/',
  timeout: 1000,
});

app.mount('#app');