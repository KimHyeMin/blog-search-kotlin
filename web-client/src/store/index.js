import Vuex from 'vuex';
import Vue from 'vue';
import auth from './auth.js'
import search from './search.js'


Vue.use(Vuex);

const store = new Vuex.Store({
    modules: {
      $auth: auth,
      $search: search
    },
    state() {
      return {
        user: null,
        isAuthenticated: false,
        token: null
      }
    },
    mutations: {
      setUser(state, result) {
        state.token = result.token;
        state.user = result.user;
        state.isAuthenticated = true;
      }
    },
    actions: {

    },
    getters: {
      userName(state) {
        return state.user === null ? "" : state.user.name;
      },
      isAuthenticated(state) {
        return state.isAuthenticated;
      },
      getAccessToken: function (state) {
        return state.token
      },
      resultExist(state) {
        return !!state.$search.blogList;
      }
    },
});

export default store