import { searchBlogs, getFrequentKeyword } from '@/module/searching'
import { SearchRequest } from '@/models'

const search = {
        namespaced: true,
        state: () => ({
          blogList: null,
          meta: null,
          searchRequest: SearchRequest.default(),
          keywordRanking:null,
          errorMessage:null
        }),
        mutations: {
          success(state, result) {
            state.blogList = result.blogList;
            state.meta = result.metaData;
          },
          failure(state, message) {
            state.errorMessage = message;
          },
          init(state) {
            state.blogList = null;
            state.meta = null;
            state.searchRequest = SearchRequest.default();
            state.errorMessage = null;
          },
          updateRanking(state, result) {
            state.keywordRanking = result;
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
                        commit('failure', error.response.errorMessage)
                        return Promise.reject(error.response.data)
                    }
                )
            },
          fetchRanking({ commit }) {
            return getFrequentKeyword().then(
                response => {
                  commit('updateRanking', response.data.result.list);
                  return Promise.resolve(response.data)
                },
                error => {
                  commit('failure', error.response.errorMessage)
                  return Promise.reject(error.response.data)
                }
            )
          }
        }
}

export default search