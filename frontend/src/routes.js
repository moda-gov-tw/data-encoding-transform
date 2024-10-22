import Home from './components/Home.vue';
import FileUpload from './components/FileUpload.vue';
import Users from './components/User/Users.vue'; 

const routes = [
    { path: '/', component: Home },
    { path: '/fileUpload', component: FileUpload },
    { path: '/users', component: Users },
];

export default routes; 
