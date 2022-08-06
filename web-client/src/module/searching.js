import axios from 'axios'

const axiosService = axios.create({
    headers: {

    }
});

const API_URL = '/api/v1/search/'

export function searchBlogs(param) {
    return axiosService.get(API_URL + 'blogs', {params : param})
}