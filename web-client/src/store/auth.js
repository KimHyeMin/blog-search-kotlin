import { callRegister, callLogin } from '@/module/auth'
import store  from '../store'

const auth = {
        namespaced: true,
        state: () => ({

        }),
        mutations: {
            success(state) {
                state.status = {}
            },
            failure(state) {
                state.status = {}
            }
        },
        actions: {
            register({ commit }, signUpForm) {
                return callRegister(signUpForm).then(
                    response => {
                        commit('success')
                        return Promise.resolve(response.data)
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
                      return Promise.reject(error.response.data)
                    }
                )
            },
            logout({commit}) {
              store.commit('expireUser')
              commit('success')
            }
        }
}

export default auth