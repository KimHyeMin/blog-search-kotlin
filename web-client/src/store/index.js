import Vuex from 'vuex';
import Vue from 'vue';
import auth from './auth.js'
import search from './search.js'
import favorite from './favorite.js'
import {setAuthToCookie, setUserToCookie, getUserFromCookie, getAuthFromCookie, deleteCookie} from '@/utils/cookies.js'

Vue.use(Vuex);

const store = new Vuex.Store({
    modules: {
      $auth: auth,
      $search: search,
      $favorite: favorite
    },
    state() {
      return {
        user: getUserFromCookie() || null,
        isAuthenticated: !!getAuthFromCookie() || false,
        token: getAuthFromCookie() || null
      }
    },
    mutations: {
      setUser(state, result) {
        state.token = result.token;
        state.user = result.user;
        state.isAuthenticated = true;
        setAuthToCookie(state.token);
        setUserToCookie(state.user);
      },
      expireUser(state) {
        state.user = null;
        state.isAuthenticated = false;
        state.token = null;
        deleteCookie();
      }
    },
    actions: {

    },
    getters: {
      userId(state) {
        return state.user === null ? "" : state.user.id;
      },
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