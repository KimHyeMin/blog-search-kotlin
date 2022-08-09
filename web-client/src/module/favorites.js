import axios from 'axios'

import { setInterceptors } from '@/utils/interceptors';

const axiosService = axios.create({

});

setInterceptors(axiosService);

const API_URL = '/api/v1/users/'
export function getMyList({userId, lastFavoriteId}) {
    if (lastFavoriteId) {
        return axiosService.get(API_URL + `${userId}/favorites/list?searchAfter=${lastFavoriteId}`)
    } else {
        return axiosService.get(API_URL + `${userId}/favorites/list`)
    }
}


export function addFavorite({blog, userId}) {
    return axiosService.post(API_URL + `${userId}/favorites`, blog)
}

export function removeFavorite({favoriteId, userId}) {
    return axiosService.delete(API_URL + `${userId}/favorites/${favoriteId}`)
}