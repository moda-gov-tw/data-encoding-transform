import Home from './components/Home'
import FileUpload from './components/FileUpload'
import Users from './components/User/Users'

export const routes = [
    { path: '/', component: Home},
    { path: '/fileUpload', component: FileUpload},
    { path: '/users', component: Users},
];