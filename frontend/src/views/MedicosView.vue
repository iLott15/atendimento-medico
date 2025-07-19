<template>
    <div class="medicos">
        <div class="topo">
            <h1>Médicos Cadastrados</h1>
            <button class="btn-voltar" @click="voltarDashboard">← Voltar</button>
        </div>

        <div v-if="loading">🔄 Carregando médicos...</div>
        <div v-else-if="medicos.length === 0">❗Nenhum médico encontrado.</div>

        <div class="grid">
            <div class="box" v-for="medico in medicos" :key="medico.id">
                <h2>🧑‍⚕️ <span class="nome">{{ medico.nome }}</span></h2>
                <p><strong>Especialidade:</strong> {{ medico.especialidade }}</p>
                <p><strong>Email:</strong> {{ medico.email }}</p>
                <p><strong>CRM:</strong> {{ medico.crm }}</p>
            </div>
        </div>
    </div>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import { useRouter } from 'vue-router'
import api from '@/services/api'

const medicos = ref([])
const loading = ref(true)
const router = useRouter()

const voltarDashboard = () => {
    router.push('/')
}

onMounted(async () => {
    try {
        const { data } = await api.get('/medicos')
        medicos.value = data.content
    } catch (error) {
        console.error('Erro ao buscar médicos:', error)
    } finally {
        loading.value = false
    }
})
</script>

<style scoped>
.medicos {
    background-color: #000;
    min-height: 100vh;
    padding: 40px;
    font-family: 'Segoe UI', sans-serif;
    color: #333;
}

h1 {
    font-size: 26px;
    font-weight: 600;
    color: #ffffff;
    margin: 0;
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

.topo {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 30px;
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
</style>