import Vue from 'vue'
import App from './App.vue'
import router from "./router"
import createStore from './store';
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.config.productionTip = false
Vue.use(BootstrapVue)

const $store = createStore

new Vue({
  el: "#app",
  render: h => h(App),
  store: $store,
  router,
})
