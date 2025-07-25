import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import { createPinia } from 'pinia'
import './assets/styles.css'
import axios from 'axios'
import 'vue3-toastify/dist/index.css'

axios.defaults.baseURL = 'http://localhost:8080'

const token = localStorage.getItem('token')
if (token) {
    axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
}

const app = createApp(App)

app.use(createPinia())
app.use(router)
app.mount('#app')