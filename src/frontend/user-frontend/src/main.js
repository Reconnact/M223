import { createApp } from 'vue';
import VueAxios from 'vue-axios';
import App from '@/App';
import axios from 'axios';
import mitt from 'mitt';
import {createRouter, createWebHistory} from "vue-router";
import AdminPage from "@/components/AdminPage.vue";
import AddUserPage from "@/components/AddUserPage.vue";

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {path: "/", name: "AdminPage", component: AdminPage},
        {path: "/addUser", name: "AddUserPage", component: AddUserPage}
    ]
})

const emitter = mitt();
const app = createApp(App);
app.config.globalProperties.emitter = emitter;
app.use(VueAxios, axios);
app.use(router);
app.mount('#app');
