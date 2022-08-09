import axios from 'axios'

import { setInterceptors } from '@/utils/interceptors';

const axiosService = axios.create({

});

setInterceptors(axiosService);

const API_URL = '/api/v1/search/'

export function searchBlogs(param) {
    return axiosService.get(API_URL + 'blogs', {params : param})
}


export function getFrequentKeyword() {
    return axiosService.get(API_URL + 'frequent/keywords')
}