
import Home from './components/Home'
import FileUpload from './components/FileUpload'
import Users from './components/User/Users'
import FileExport from './components/FileExport'

export const routes = [
    { path: '/', component: Home},
    { path: '/fileUpload', component: FileUpload},
    { path: '/users', component: Users},
    { path: '/fileExport', component: FileExport},
];