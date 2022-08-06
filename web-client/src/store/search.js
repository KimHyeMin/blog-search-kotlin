import { searchBlogs } from '@/module/searching'

const search = {
        namespaced: true,
        state: () => ({
          results: null
        }),
        mutations: {
          success(state, result) {
            state.results = result;
          }
        },
        actions: {
            search({ commit }, param) {
                return searchBlogs(param).then(
                    response => {
                        let apiResponse = response.data;
                        commit('success', apiResponse.result);
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

export default search