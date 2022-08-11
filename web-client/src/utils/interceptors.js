import store from '@/store/index';

export function setInterceptors(axiosService) {
  axiosService.interceptors.request.use(function (config) {

    config.headers.Authorization = 'Bearer '+store.getters.getAccessToken;
    return config;
  }, function (error) {

    return Promise.reject(error);
  });

  axiosService.interceptors.response.use(function (response) {

    return response;
  }, function (error) {
    return Promise.reject(error);
  });

  return axiosService;
}