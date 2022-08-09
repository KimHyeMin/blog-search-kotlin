import { searchBlogs } from '@/module/searching'
import { SearchRequest } from '@/models'

const search = {
        namespaced: true,
        state: () => ({
          blogList: null,
          meta: null,
          searchRequest: SearchRequest.default()
        }),
        mutations: {
          success(state, result) {
            state.blogList = result.blogList;
            state.meta = result.metaData;
          },
          failure(state) {
            state.results = null;
          },
          init(state) {
            state.blogList = null;
            state.meta = null;
            state.searchRequest = SearchRequest.default();
          },
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