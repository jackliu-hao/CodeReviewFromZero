// src/api/hello.js
import apiClient from '../axios/axios';

export async   function fetchHelloMessage (data){
    try {
        // 使用 data 作为查询参数
        const  response = await  apiClient.get('/v1/xssTest', {
            params: data
        });
        console.log(response);
        return  response.data;
    } catch (error) {
        console.error('Error fetching data:', error);
        throw error;
    }
}
