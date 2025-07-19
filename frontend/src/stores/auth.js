import { defineStore } from 'pinia'
import api from '@/services/api'

export const useAuthStore = defineStore('auth', {
  state: () => ({
    token: localStorage.getItem('token') || '',
    user: null
  }),
  actions: {
    setToken(token) {
      this.token = token
      localStorage.setItem('token', token)
      api.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    async fetchUser() {
      try {
        const response = await api.get('/me')
        this.user = response.data
      } catch (err) {
        console.error('Erro ao buscar usuário logado:', err)
      }
    }
  }
})