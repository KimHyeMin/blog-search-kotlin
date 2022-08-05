import { callRegister } from '@/module/auth'

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
            }
        }
}

export default auth