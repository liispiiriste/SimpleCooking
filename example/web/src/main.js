import Vue from 'vue'
import App from './App.vue'
import Router from 'vue-router'

import Home from './components/Home.vue'
import LisaRetsept from './components/LisaRetsept.vue'

Vue.config.productionTip = false;

new Vue({
  render: h => h(App),
}).$mount('#app');

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/LisaRetsept',
      name: 'LisaRetsept',
      component: LisaRetsept
    }
  ]
})