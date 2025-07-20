<template>
    <div class="medicos">
        <div class="topo">
            <h1>Médicos Cadastrados</h1>
            <div class="acoes">
                <button class="btn-novo" @click="abrirModal">➕ Novo Médico</button>
                <button class="btn-voltar" @click="voltarDashboard">← Voltar</button>
            </div>
        </div>

        <div v-if="loading">🔄 Carregando médicos...</div>
        <div v-else-if="medicos.length === 0">❗Nenhum médico encontrado.</div>

        <div class="grid">
            <div class="box" v-for="medico in medicos" :key="medico.id">
                <h2>🧑‍⚕️ <span class="nome">{{ medico.nome }}</span></h2>
                <p><strong>Especialidade:</strong> {{ medico.especialidade }}</p>
                <p><strong>Email:</strong> {{ medico.email }}</p>
                <p><strong>CRM:</strong> {{ medico.crm }}</p>
                <div class="acoes-paciente">
                    <button class="btn-editar" @click="editarMedico(medico)">✏️ Editar</button>
                    <button class="btn-excluir" @click="excluirMedico(medico.id)">🗑 Excluir</button>
                </div>
            </div>
        </div>

        <NovoMedicoModal :mostrar="mostrarModal" @fechar="fecharModal" @salvo="buscarMedicos" />
        <EditMedicoModal :mostrar="mostrarModalEdicao" :medicoSelecionado="medicoSelecionado"
            @fechar="fecharModalEdicao" @salvo="buscarMedicos" />
    </div>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import { useRouter } from 'vue-router'
import api from '@/services/api'
import { toast } from 'vue3-toastify'
import NovoMedicoModal from '@/components/NovoMedicoModal.vue'
import EditMedicoModal from '@/components/EditMedicoModal.vue'

const medicos = ref([])
const loading = ref(true)
const mostrarModal = ref(false)
const mostrarModalEdicao = ref(false)
const medicoSelecionado = ref(null)
const router = useRouter()

const buscarMedicos = async () => {
    loading.value = true
    try {
        const { data } = await api.get('/medicos')
        medicos.value = data.content
    } catch (error) {
        console.error('Erro ao buscar médicos:', error)
    } finally {
        loading.value = false
        mostrarModal.value = false
    }
}

const abrirModal = () => (mostrarModal.value = true)
const fecharModal = () => (mostrarModal.value = false)
const voltarDashboard = () => router.push('/')
const editarMedico = (medico) => {
    medicoSelecionado.value = medico
    mostrarModalEdicao.value = true
}
const fecharModalEdicao = () => {
    mostrarModalEdicao.value = false
    medicoSelecionado.value = null
}
const excluirMedico = async (id) => {
    const confirmado = confirm('Deseja realmente excluir este médico?')
    if (!confirmado) return
    try {
        await api.delete(`/medicos/${id}`)
        toast.success('Médico excluído com sucesso!')
        buscarMedicos()
    } catch (error) {
        console.error('Erro ao excluir médico:', error)
        toast.error('Erro ao excluir médico.')
    }
}

onMounted(buscarMedicos)
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

.btn-voltar,
.btn-novo {
    background-color: #007bff;
    color: white;
    border: none;
    padding: 10px 22px;
    border-radius: 8px;
    font-weight: 500;
    cursor: pointer;
    transition: background-color 0.3s;
}

.btn-voltar:hover,
.btn-novo:hover {
    background-color: #0056b3;
}

.btn-editar,
.btn-excluir {
    padding: 6px 12px;
    border: none;
    border-radius: 6px;
    font-weight: 500;
    cursor: pointer;
    margin-right: 8px;
}

.btn-editar {
    background-color: #ffc107;
    color: #000;
}

.btn-editar:hover {
    background-color: #e0a800;
}

.btn-excluir {
    background-color: #dc3545;
    color: #fff;
}

.btn-excluir:hover {
    background-color: #c82333;
}
</style>