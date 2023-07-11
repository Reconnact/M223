import { createApp } from 'vue';
import VueAxios from 'vue-axios';
import App from '@/App';
import axios from 'axios';
import mitt from 'mitt';
import {createRouter, createWebHistory} from "vue-router";
import AdminPage from "@/components/AdminPage.vue";
import AddUserPage from "@/components/AddUserPage.vue";
import EditUser from "@/components/EditUser.vue";
import PageNotFound from "@/components/PageNotFound.vue";

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {path: "/", name: "AdminPage", component: AdminPage},
        {path: "/addUser", name: "AddUserPage", component: AddUserPage},
        {path: "/editUser/:id", name: "EditUserPage", component: EditUser},
        { path: "/:catchAll(.*)", name: "PageNotFoundPage", component: PageNotFound }
    ]
})

const emitter = mitt();
const app = createApp(App);
app.config.globalProperties.emitter = emitter;
app.use(VueAxios, axios);
app.use(router);
app.mount('#app');
