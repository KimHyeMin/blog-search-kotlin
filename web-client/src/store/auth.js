import { callRegister, callLogin } from '@/module/auth'
import store  from '../store'

const auth = {
        namespaced: true,
        state: () => ({
            errorMessage: null
        }),
        mutations: {
            success(state) {
                state.status = {}
                state.errorMessage = null;
            },
            failure(state, message) {
                state.errorMessage = message;
            }
        },
        actions: {
            register({ commit }, signUpForm) {
                return callRegister(signUpForm).then(
                    response => {
                      if (response.data.code === 200) {
                        commit('success')
                        return Promise.resolve(response.data)
                      } else {
                        commit('failure')
                        return Promise.reject(response.data)
                      }
                    },
                    error => {
                        commit('failure')
                        return Promise.reject(error.response.data)
                    }
                )
            },
            login({commit}, loginForm) {
                return callLogin(loginForm).then(
                    response => {
                      let apiRes = response.data;
                      let result = apiRes.result;
                      commit('success')
                      store.commit('setUser', result)
                      return Promise.resolve(response.data)
                    },
                    error => {
                      commit('failure', error.response.data.errorMessage)
                      return Promise.reject(error.response.data)
                    }
                )
            },
            logout({commit}) {
              store.commit('expireUser')
              store.commit('$search/init')
              commit('success')
            }
        }
}

export default auth