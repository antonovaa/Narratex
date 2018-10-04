// import axios from 'axios'
// import BootstrapVue from "bootstrap-vue"
import Vue from 'vue'
import App from './App.vue'
import Store from './store/store'
import VueRouter from 'vue-router'
// import axios from './store/store'

Vue.use(VueRouter);
// Vue.use(BootstrapVue);


// export const bus=new Vue();


new Vue({
  el: '#app',
  template: '<App/>',
  store:Store,
  components: { App },
});
