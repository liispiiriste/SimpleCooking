import Vue from 'vue'
import App from './App.vue'
import router from "./router";
import store from "./store"
import axios from "axios";
import BootstrapVue from 'bootstrap-vue'

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'


Vue.use(BootstrapVue)
Vue.config.productionTip = false;
axios.defaults.withCredentials = true;

new Vue({
  router,
  store,
  render: h=> h (App),
}).$mount('#app');