import {getMyList, addFavorite, removeFavorite} from "@/module/favorites";

const favorite = {
  namespaced: true,
  state: () => ({
    myList : [],
    lastFavoriteId : null,
    errorMessage:null
  }),
  mutations: {
    updateMyList(state, favorites) {
      if (!favorites || favorites.length === 0) {
        state.lastFavoriteId = null;
      } else {
        state.lastFavoriteId = favorites.slice(-1)[0].favoriteId;
      }
      state.myList = favorites;
    },
    appendList(state, favorites) {
      if (!favorites || favorites.length === 0) {
        state.lastFavoriteId = null;
      } else {
        state.myList.push(...favorites);
        state.lastFavoriteId = favorites.slice(-1)[0].favoriteId;
      }
    },
    removeFavorite(state, favoriteId) {
      state.myList = state.myList.filter(it => it.favoriteId !== favoriteId)
    },
    failure(state, message) {
      state.errorMessage = message;
    },
  },
  actions: {
    fetchMyList({ commit }, param) {
      return getMyList(param).then(
          response => {
            let apiResponse = response.data;
            commit('updateMyList', apiResponse.result.blogList || []);
            return Promise.resolve(response.data)
          },
          error => {
            commit('failure', error)
            return Promise.resolve(error.response.data)
          }
      )
    },
    fetchMore({ commit, state }, param) {
      param.lastFavoriteId = state.lastFavoriteId;
      return getMyList(param).then(
          response => {
            let apiResponse = response.data;
            commit('appendList', apiResponse.result.blogList || []);
            return Promise.resolve(response.data)
          },
          error => {
            commit('failure', error.response.errorMessage)
            return Promise.resolve(error.response.data)
          }
      )
    },
    like({ commit }, param) {
      return addFavorite(param).then(
          response => {
            return Promise.resolve(response.data.result.success)
          },
          error => {
            commit('failure')
            return Promise.resolve(error.response.data)
          }
      )
    },
    unlike({ commit }, param) {
      return removeFavorite(param).then(
          response => {
            if (response.data.code === 200) {
              commit('removeFavorite', param.favoriteId);
              return Promise.resolve(response.data.result.success)
            } else {
              commit('failure')
              return Promise.resolve(response.data)
            }
          },
          error => {
            commit('failure')
            return Promise.resolve(error.response.data)
          }
      )
    }
  }
}


export default favorite