<template>
  <div class="pacientes">
    <div class="topo">
      <h1>Pacientes Cadastrados</h1>
      <button class="btn-voltar" @click="voltarDashboard">← Voltar</button>
    </div>

    <div v-if="loading">🔄 Carregando pacientes...</div>
    <div v-else-if="pacientes.length === 0">❗Nenhum paciente encontrado.</div>

    <div class="grid">
      <div class="box" v-for="paciente in pacientes" :key="paciente.id">
        <h2>🧍 <span class="nome">{{ paciente.nome }}</span></h2>
        <p><strong>Email:</strong> {{ paciente.email }}</p>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import api from '@/services/api'

const pacientes = ref([])
const loading = ref(true)
const router = useRouter()

const voltarDashboard = () => {
  router.push('/')
}

const formatarData = (dataISO) => {
  if (!dataISO) return ''
  const data = new Date(dataISO)
  return data.toLocaleDateString('pt-BR')
}

onMounted(async () => {
  try {
    const { data } = await api.get('/pacientes')
    pacientes.value = data.content
  } catch (error) {
    console.error('Erro ao buscar pacientes:', error)
  } finally {
    loading.value = false
  }
})
</script>

<style scoped>
.pacientes {
  background-color: #000;
  min-height: 100vh;
  padding: 40px;
  font-family: 'Segoe UI', sans-serif;
  color: #333;
}

.topo {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 30px;
}

h1 {
  font-size: 26px;
  font-weight: 600;
  color: #fff;
  margin: 0;
}

.btn-voltar {
  background-color: #007bff;
  color: white;
  border: none;
  padding: 10px 22px;
  border-radius: 8px;
  font-weight: 500;
  cursor: pointer;
  transition: background-color 0.3s;
}

.btn-voltar:hover {
  background-color: #0056b3;
}

.grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
  gap: 24px;
}

.box {
  background-color: #ffffff;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 0 12px rgba(0, 0, 0, 0.06);
}

h2 {
  color: #007bff;
  margin-bottom: 10px;
}
</style>