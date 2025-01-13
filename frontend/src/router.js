import FileExport from '@/views/FileExport.vue'
import FileUpload from '@/views/FileUpload.vue'
import Home from '@/views/Home.vue'
import Users from '@/views/Users.vue'
import { createRouter, createWebHistory } from 'vue-router'

export default createRouter({
  history: createWebHistory(),
  routes: [
    { path: '/', component: Home },
    { path: '/fileUpload', component: FileUpload },
    { path: '/users', component: Users },
    { path: '/fileExport', component: FileExport },
    { path: '/:pathMatch(.*)*', redirect: '/' },
  ],
})
