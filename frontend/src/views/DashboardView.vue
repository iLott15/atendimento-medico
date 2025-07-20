<template>
  <div class="dashboard">
    <header class="dashboard-header">
      <h1>Página Inicial</h1>
    </header>

    <section class="user-info" v-if="user">
      <p>👋 Olá, <strong>{{ user.login }}</strong></p>
      <p>🔐 Perfil: <span class="role">{{ user.role }}</span></p>
    </section>


    <div class="grid grid-full">
      <div class="box full">
        <h2>🩺 Agendar Atendimentos</h2>
        <button class="btn-acesso" @click="irPara('atendimentos')">Agendar atendimentos</button>
      </div>
    </div>

    <div class="grid">

      <div class="box">
        <h2>👤 Pacientes</h2>
        <button class="btn-acesso" @click="irPara('pacientes')">Ver pacientes</button>
      </div>

      <div class="box">
        <h2>🧑‍⚕️ Médicos</h2>
        <button class="btn-acesso" @click="irPara('medicos')">Ver médicos</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import api from '@/services/api'
import { useAuthStore } from '@/stores/auth'
import { useRouter } from 'vue-router'

const auth = useAuthStore()
const router = useRouter()
const user = ref(null)

const fetchUser = async () => {
  try {
    const { data } = await api.get('/me')
    user.value = data
  } catch (err) {
    console.error('Erro ao buscar dados do usuário:', err)
  }
}

const irPara = (rota) => {
  router.push(`/${rota}`)
}

onMounted(fetchUser)
</script>

<style scoped>
.dashboard {
  background-color: #000;
  color: #000;
  min-height: 100vh;
  padding: 40px;
  font-family: 'Segoe UI', sans-serif;
}

.dashboard-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 30px;
}

.dashboard-header h1 {
  font-size: 28px;
  font-weight: 600;
  color: #fff;
}

.user-info {
  color: #fff;
  margin-bottom: 30px;
  font-size: 18px;
}

.role {
  background-color: #d1e7ff;
  padding: 4px 10px;
  border-radius: 4px;
  color: #007bff;
  font-weight: bold;
}

.grid {
  display: grid;
  grid-template-columns: repeat(12, 1fr);
  gap: 24px;
  margin-top: 30px;
}

.grid>.box {
  grid-column: span 6;
}

.grid-full {
  margin-top: 24px;
  display: grid;
  grid-template-columns: repeat(12, 1fr);
  gap: 24px;
}

.full {
  grid-column: span 12 !important;
}

.box {
  background-color: #91a8c8;
  padding: 24px;
  border-radius: 12px;
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.08);
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.box h2 {
  font-size: 20px;
  margin-bottom: 16px;
  text-align: center;
  color: #fff;
}

.btn-acesso {
  background-color: #516d91;
  color: #ffffff;
  border: none;
  padding: 14px 32px;
  font-size: 16px;
  font-weight: bold;
  border-radius: 8px;
  cursor: pointer;
  transition: background-color 0.3s;
  width: 100%;
  max-width: 240px;
  text-align: center;
}

.btn-acesso:hover {
  background-color: #93beec;
}
</style>