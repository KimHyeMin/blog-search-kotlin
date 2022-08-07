import axios from 'axios'

const axiosService = axios.create({
    headers: {

    }
});

const API_URL = '/api/v1/user/'

export function callRegister(sighUpForm) {
    return axiosService.post(API_URL + 'register', sighUpForm)
}

export function callLogin(loginForm) {
    return axiosService.post(API_URL + 'login', loginForm)
}