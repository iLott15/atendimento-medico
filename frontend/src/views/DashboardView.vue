<template>
  <div class="dashboard">
    <header class="dashboard-header">
      <h1>Dashboard</h1>
    </header>

    <section class="user-info" v-if="user">
      <p>👋 Olá, <strong>{{ user.login }}</strong></p>
      <p>🔐 Perfil: <span class="role">{{ user.role }}</span></p>
    </section>

    <div class="grid">
      <div class="box">
        <h2>🩺 Atendimentos</h2>
        <button class="btn-acesso" @click="irPara('atendimentos')">Ver atendimentos</button>
      </div>

      <div class="box">
        <h2>👤 Pacientes</h2>
        <button class="btn-acesso" @click="irPara('pacientes')">Ver pacientes</button>
      </div>

      <div class="box">
        <h2>🧑‍⚕️ Médicos</h2>
        <button class="btn-acesso" @click="irPara('medicos')">Ver médicos</button>
      </div>

      <div class="box">
        <h2>👤 Criar/Editar Paciente</h2>
        <button class="btn-acesso" @click="irPara('editar-paciente')">Gerenciar Paciente</button>
      </div>

      <div class="box">
        <h2>🧑‍⚕️ Criar/Editar Médico</h2>
        <button class="btn-acesso" @click="irPara('editar-medico')">Gerenciar Médico</button>
      </div>

      <div class="box">
        <h2>📅 Agendar Atendimento</h2>
        <button class="btn-acesso" @click="irPara('agendar')">Agendar</button>
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
  background-color: #121212;
  color: #f5f5f5;
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

.dashboard-header button {
  background-color: #990f0f;
  border: none;
  padding: 10px 18px;
  font-weight: 500;
  border-radius: 6px;
  color: #fff;
  cursor: pointer;
  transition: background-color 0.3s;
}

.dashboard-header button:hover {
  background-color: #c11f1f;
}

.user-info {
  margin-bottom: 30px;
  font-size: 18px;
}

.role {
  background-color: #222;
  padding: 4px 10px;
  border-radius: 4px;
  color: #208adc;
  font-weight: bold;
}

.grid {
  display: grid;
  grid-template-columns: repeat(12, 1fr);
  gap: 20px;
}

.box {
  background-color: #1c1c1e;
  padding: 25px;
  border-radius: 10px;
  border-left: 5px solid #208adc;
  box-shadow: 0 0 10px rgba(255, 0, 51, 0.2);
  grid-column: span 4;
}

.box.full {
  grid-column: span 12;
}

.box h2 {
  margin-bottom: 10px;
  font-size: 20px;
}

.grid {
  display: grid;
  grid-template-columns: repeat(12, 1fr);
  gap: 24px;
  margin-top: 30px;
}

.box {
  grid-column: span 4;
  background-color: #91a8c8;
  padding: 24px;
  border-radius: 12px;
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.08);
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.box.full {
  grid-column: span 12;
}

h2 {
  color: #333;
  font-size: 20px;
  margin-bottom: 16px;
  text-align: center;
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

body {
  background-color: #f0f2f5;
  font-family: 'Segoe UI', sans-serif;
}
</style>