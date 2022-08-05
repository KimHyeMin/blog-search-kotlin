import axios from 'axios'

const API_URL = '/api/v1/user/'

export function callRegister(sighUpForm) {
    return axios.post(API_URL + 'sign-up', {
        firstName: sighUpForm.firstName,
        secondName: sighUpForm.secondName,
        email: sighUpForm.email,
        password: sighUpForm.password
    })
}