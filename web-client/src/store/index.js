import Vuex from 'vuex';
import Vue from 'vue';
import auth from './auth.js'

Vue.use(Vuex);

const store = new Vuex.Store({
    modules: {
      $auth: auth
    },
    state() {
      return {
        userName: "김혜민"
      }
    },
    mutations: {

    },
    actions: {

    },
    getters: {

    },
});

export default store