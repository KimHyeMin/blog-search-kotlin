import * as Cookies from 'js-cookie';
import {User} from '@/models';

function getAuthFromCookie() {
  return Cookies.get('authToken')
}

function getUserFromCookie() {
  let userStr = Cookies.get('user');
  if (!userStr) {
    return null;
  }
  let user = JSON.parse(userStr);
  return new User(user.id, user.name, user.email)
}

function setAuthToCookie(value) {
  Cookies.set('authToken', value)
}

function setUserToCookie(value) {
  Cookies.set('user', value)
}

function deleteCookie() {
  Cookies.remove('authToken')
  Cookies.remove('user')
}

export {
  getAuthFromCookie,
  setAuthToCookie,
  setUserToCookie,
  getUserFromCookie,
  deleteCookie
}