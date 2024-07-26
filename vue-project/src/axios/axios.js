// src/axios/axios.js
import axios from 'axios';

const apiClient = axios.create({
    baseURL: import.meta.env.VITE_APP_API_URL,
    headers: {
        'Content-Type': 'application/json'
    }
});

export default apiClient;
