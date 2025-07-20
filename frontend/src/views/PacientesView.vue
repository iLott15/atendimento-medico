<template>
  <div class="pacientes">
    <div class="topo">
      <h1>Pacientes Cadastrados</h1>
      <div class="acoes">
        <button class="btn-novo" @click="abrirModal">➕ Novo Paciente</button>
        <button class="btn-voltar" @click="voltarDashboard">← Voltar</button>
      </div>
    </div>

    <div v-if="loading">🔄 Carregando pacientes...</div>
    <div v-else-if="pacientes.length === 0">❗Nenhum paciente encontrado.</div>

    <div class="grid">
      <div class="box" v-for="paciente in pacientes" :key="paciente.id">
        <h2>🧍 <span class="nome">{{ paciente.nome }}</span></h2>
        <p><strong>Email:</strong> {{ paciente.email }}</p>
        <p><strong>Telefone:</strong> {{ paciente.telefone }}</p>
        <p v-if="paciente.endereco">
          <strong>Endereço:</strong>
          {{ paciente.endereco.logradouro }},
          {{ paciente.endereco.numero || 's/n' }} -
          {{ paciente.endereco.bairro }},
          {{ paciente.endereco.cidade }} / {{ paciente.endereco.uf }} -
          {{ paciente.endereco.cep }}
        </p>
        <p v-else>
          <strong>Endereço:</strong> Não informado
        </p>
        <div class="botoes">
          <button class="btn-editar" @click="editarPaciente(paciente)">✏️ Editar</button>
          <button class="btn-excluir" @click="excluirPaciente(paciente.id)">🗑 Excluir</button>
        </div>
      </div>
    </div>

    <NovoPacienteModal :mostrar="mostrarModal" @fechar="fecharModal" @salvo="buscarPacientes" />
    <EditarPacienteModal :mostrar="mostrarModalEdicao" :pacienteSelecionado="pacienteSelecionado"
      @fechar="fecharModalEdicao" @salvo="buscarPacientes" />
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import api from '@/services/api'
import NovoPacienteModal from '@/components/NovoPacienteModal.vue'
import EditPacienteModal from '@/components/EditPacienteModal.vue'

const pacientes = ref([])
const loading = ref(true)
const mostrarModal = ref(false)
const router = useRouter()

const buscarPacientes = async () => {
  loading.value = true
  try {
    const { data } = await api.get('/pacientes')
    pacientes.value = data.content
  } catch (error) {
    console.error('Erro ao buscar pacientes:', error)
  } finally {
    loading.value = false
    mostrarModal.value = false
  }
}

const abrirModal = () => {
  mostrarModal.value = true
}

const fecharModal = () => {
  mostrarModal.value = false
}

const voltarDashboard = () => {
  router.push('/')
}

onMounted(buscarPacientes)


import EditarPacienteModal from '@/components/EditPacienteModal.vue'

const mostrarModalEdicao = ref(false)
const pacienteSelecionado = ref(null)

const editarPaciente = (paciente) => {
  pacienteSelecionado.value = paciente
  mostrarModalEdicao.value = true
}

const fecharModalEdicao = () => {
  mostrarModalEdicao.value = false
  pacienteSelecionado.value = null
}

import Swal from 'sweetalert2'
import { toast } from 'vue3-toastify'

const excluirPaciente = async (id) => {
  const { isConfirmed } = await Swal.fire({
    title: 'Tem certeza?',
    text: 'Essa ação vai excluir permanentemente o paciente.',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#d33',
    cancelButtonColor: '#3085d6',
    confirmButtonText: 'Sim, excluir',
    cancelButtonText: 'Cancelar'
  })

  if (!isConfirmed) return

  try {
    await api.delete(`/pacientes/${id}`)
    toast.success('Paciente excluído com sucesso!')
    buscarPacientes()
  } catch (error) {
    console.error('Erro ao excluir paciente:', error)
    toast.error('Erro ao excluir paciente.')
  }
}
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

.acoes {
  display: flex;
  gap: 12px;
}

.btn-novo,
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

.btn-novo:hover,
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

h1 {
  font-size: 26px;
  font-weight: 600;
  color: #fff;
  margin: 0;
}

h2 {
  color: #007bff;
  margin-bottom: 10px;
}

.acoes-paciente {
  display: flex;
  justify-content: flex-end;
  gap: 10px;
  margin-top: 15px;
}

.btn-editar,
.btn-excluir {
  padding: 6px 12px;
  border: none;
  border-radius: 6px;
  font-weight: 500;
  cursor: pointer;
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