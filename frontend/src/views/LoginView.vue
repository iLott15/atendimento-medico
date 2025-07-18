<template>
  <div class="login-page">
    <div class="login-container">
      <h1>Bem-vindo</h1>
      <input type="text" placeholder="Usuário" v-model="login" />
      <input type="password" placeholder="Senha" v-model="senha" />
      <button @click="fazerLogin">Entrar</button>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/auth'
import api from '@/services/api'

const router = useRouter()
const auth = useAuthStore()

const login = ref('')
const senha = ref('')

const fazerLogin = async () => {
  try {
    const { data } = await api.post('/login', {
      login: login.value,
      senha: senha.value
    })
    auth.setToken(data.token)
    const redirectPath = router.currentRoute.value.query.redirect || '/'
    router.push(redirectPath)
  } catch (error) {
    alert('Login inválido')
  }
}
</script>

<style scoped>
.login-page {
  background-color: #121212;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  font-family: 'Segoe UI', sans-serif;
}

.login-container {
  background-color: #1c1c1e;
  padding: 40px;
  width: 100%;
  max-width: 420px;
  border-radius: 10px;
  box-shadow: 0 0 25px rgba(255, 25, 25, 0.12);
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.login-container h1 {
  text-align: center;
  color: #f5f5f5;
  margin-bottom: 10px;
  font-weight: 500;
}

input {
  background-color: #2a2a2c;
  border: 1px solid #3a3a3c;
  color: #f5f5f5;
  padding: 12px;
  font-size: 15px;
  border-radius: 6px;
  outline: none;
  transition: border-color 0.2s ease-in-out;
}

input:focus {
  border-color: #ff0033;
}

input::placeholder {
  color: #aaa;
}

button {
  background-color: #990f0f;
  color: #fff;
  border: none;
  padding: 12px;
  font-size: 16px;
  font-weight: 500;
  border-radius: 6px;
  cursor: pointer;
  transition: background-color 0.25s ease;
}

button:hover {
  background-color: #c11f1f;
}
</style>