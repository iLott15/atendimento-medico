<template>
    <div class="atendimentos">
        <div class="topo">
            <h1>🩺 Atendimentos</h1>
            <div>
                <button class="btn-criar" @click="abrirModalCriar">+ Novo Atendimento</button>
                <button class="btn-voltar" @click="voltar">← Voltar</button>
            </div>
        </div>

        <div v-if="loading" class="carregando">🔄 Carregando atendimentos...</div>
        <div v-else-if="atendimentos.length === 0" class="vazio">❗Oh poxa! Nenhum atendimento encontrado.</div>

        <div class="grid">
            <div class="box" v-for="item in atendimentos" :key="item.id">
                <h2>📅 {{ formatarData(item.data) }}</h2>
                <p><strong>Médico:</strong> {{ item.medicoNome }}</p>
                <p><strong>Paciente:</strong> {{ item.pacienteNome }}</p>
                <p><strong>Motivo:</strong> {{ item.motivo }}</p>
                <p v-if="item.descricao"><strong>Descrição:</strong> {{ item.descricao }}</p>
                <div class="acoes">
                    <button class="btn-editar" @click="abrirModalEditar(item)">✏️</button>
                    <button class="btn-deletar" @click="confirmarExclusao(item.id)">🗑️</button>
                </div>
            </div>
        </div>

        <NovoAtendimentoModal v-if="mostrarModalCriar" :mostrar="mostrarModalCriar" @fechar="mostrarModalCriar = false"
            @salvo="buscarAtendimentos" />

        <EditAtendimentoModal v-if="mostrarModalEditar" :mostrar="mostrarModalEditar"
            :atendimentoSelecionado="atendimentoSelecionado" @fechar="mostrarModalEditar = false"
            @salvo="buscarAtendimentos" />
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import api from '@/services/api'
import { toast } from 'vue3-toastify'
import NovoAtendimentoModal from '@/components/NovoAtendimentoModal.vue'
import EditAtendimentoModal from '@/components/EditAtendimentoModal.vue'

const atendimentos = ref([])
const loading = ref(true)
const router = useRouter()
const mostrarModalCriar = ref(false)
const mostrarModalEditar = ref(false)
const atendimentoSelecionado = ref(null)

const voltar = () => {
    router.push('/')
}

const abrirModalCriar = () => {
    mostrarModalCriar.value = true
}

const abrirModalEditar = (atendimento) => {
    atendimentoSelecionado.value = atendimento
    mostrarModalEditar.value = true
}

import Swal from 'sweetalert2'

const confirmarExclusao = async (id) => {
    const resultado = await Swal.fire({
        title: 'Tem certeza?',
        text: 'Você realmente deseja excluir este atendimento?',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#3085d6',
        confirmButtonText: 'Sim, excluir',
        cancelButtonText: 'Cancelar'
    })

    if (resultado.isConfirmed) {
        try {
            await api.delete(`/agendamentos/${id}`)
            toast.success('Atendimento excluído com sucesso!')
            buscarAtendimentos()
        } catch (error) {
            console.error('Erro ao deletar atendimento:', error)
            toast.error('Erro ao excluir atendimento.')
        }
    }
}

const formatarData = (dataISO) => {
    const data = new Date(dataISO)
    return data.toLocaleString('pt-BR', {
        day: '2-digit', month: '2-digit', year: 'numeric',
        hour: '2-digit', minute: '2-digit'
    })
}

const buscarAtendimentos = async () => {
    loading.value = true
    try {
        const { data } = await api.get('/agendamentos')
        atendimentos.value = data
    } catch (error) {
        console.error('Erro ao buscar atendimentos:', error)
    } finally {
        loading.value = false
    }
}

onMounted(buscarAtendimentos)
</script>

<style scoped>
.atendimentos {
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

.btn-voltar,
.btn-criar {
    background-color: #007bff;
    color: #fff;
    padding: 10px 20px;
    border: none;
    border-radius: 6px;
    cursor: pointer;
    font-weight: 500;
    margin-left: 10px;
}

.btn-voltar:hover,
.btn-criar:hover {
    background-color: #0056b3;
}

h1 {
    font-size: 28px;
    font-weight: bold;
    color: #fff;
}

.grid {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
    gap: 24px;
}

.box {
    background-color: #ffffff;
    padding: 24px;
    border-radius: 10px;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
    position: relative;
}

h2 {
    color: #208adc;
    margin-bottom: 10px;
    font-size: 20px;
}

p {
    margin: 6px 0;
    font-size: 15px;
}

.acoes {
    position: absolute;
    top: 12px;
    right: 12px;
    display: flex;
    gap: 8px;
}

.btn-editar,
.btn-deletar {
    background: none;
    border: none;
    font-size: 18px;
    cursor: pointer;
}

.carregando,
.vazio {
    background-color: #666;
    text-align: center;
    font-size: 18px;
    margin-top: 40px;
    color: #fff;
}
</style>