import axios from 'axios'

export const getVinculosDoMedico = (idMedico) => api.get(`/vinculos-medico/${idMedico}`);

const api = axios.create({
    baseURL: 'http://localhost:8080', 
})

api.interceptors.request.use(config => {
    const token = localStorage.getItem('token')
    if (token) {
        config.headers.Authorization = `Bearer ${token}`
    }
    return config
})

export default api