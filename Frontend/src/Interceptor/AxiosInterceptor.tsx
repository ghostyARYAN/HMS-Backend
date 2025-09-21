// import { axios,  AxiosRequestConfig }  from 'axios';
import type { AxiosRequestConfig } from 'axios';
import axios1 from 'axios';

const axiosInstance = axios1.create({
    baseURL: 'https://supreme-engine-wxv6rwjqpx429w4q-3001.app.github.dev'
})
// axiosInstance.interceptors.request.use(
//     (config: AxiosRequestConfig)=> {
//         const token=localStorage.getItem('token');
//         if(token && config.headers) {
//             config.headers.Authorization = `Bearer ${token}`; 
//         }  
//         return config;
//     }
// )
// export default axiosInstance;
axios1.interceptors.request.use(
    (config) => {
        const token=localStorage.getItem('token');
        if(token && config.headers) {
            config.headers.Authorization = `Bearer ${token}`; 
        }
        return config;  
    });

export default axiosInstance;