<template>
  <div>
    <header v-if="showHeader">
      <nav>
        <router-link to="/">Início</router-link>
        <button @click="logout">Sair</button>
      </nav>
    </header>

    <router-view />
  </div>
</template>

<script setup>
import { useRoute, useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/auth'

const route = useRoute()
const router = useRouter()
const auth = useAuthStore()

const showHeader = route.name !== 'login'

const logout = () => {
  auth.logout()
  router.push('/login')
}
</script>

<style scoped>
nav {
  display: flex;
  justify-content: space-between;
  background-color: #222;
  color: white;
  padding: 10px 20px;
}

nav a {
  color: white;
  text-decoration: none;
  margin-right: 20px;
}
</style>