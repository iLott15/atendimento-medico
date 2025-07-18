<template>
  <div>
    <h1>Bem-vindo ao sistema de agendamento médico!</h1>
    <button @click="logout">Sair</button>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/auth'

const router = useRouter()
const auth = useAuthStore()

const logout = () => {
  auth.logout()
  router.push('/login')
}
</script>