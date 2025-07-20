<template>
  <div class="modal-overlay" v-if="mostrar">
    <div class="modal">
      <h2>➕ Novo Atendimento</h2>
      <form @submit.prevent="cadastrar">
        <div class="form-group">
          <label>Médico</label>
          <select v-model="agendamento.idMedico" required>
            <option value="">Selecione</option>
            <option v-for="m in medicos" :value="m.id" :key="m.id">{{ m.nome }}</option>
          </select>
        </div>
        <div class="form-group">
          <label>Paciente</label>
          <select v-model="agendamento.idPaciente" required>
            <option value="">Selecione</option>
            <option v-for="p in pacientes" :value="p.id" :key="p.id">{{ p.nome }}</option>
          </select>
        </div>
        <div class="form-group">
          <label>Data e Hora</label>
          <input type="datetime-local" v-model="agendamento.data" required />
        </div>
        <div class="form-group">
          <label>Motivo</label>
          <input type="text" v-model="agendamento.motivo" />
        </div>
        <div class="form-group">
          <label>Descrição</label>
          <textarea v-model="agendamento.descricao" rows="2"></textarea>
        </div>
        <div class="botoes">
          <button type="submit" class="btn-salvar">Salvar</button>
          <button type="button" class="btn-cancelar" @click="$emit('fechar')">Cancelar</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import api from '@/services/api'
import { toast } from 'vue3-toastify'

const props = defineProps({ mostrar: Boolean })
const emit = defineEmits(['fechar', 'salvo'])

const agendamento = ref({
  idMedico: '',
  idPaciente: '',
  data: '',
  motivo: '',
  descricao: ''
})

const medicos = ref([])
const pacientes = ref([])

onMounted(async () => {
  const m = await api.get('/medicos')
  medicos.value = m.data.content || []
  const p = await api.get('/pacientes')
  pacientes.value = p.data.content || []
})

const cadastrar = async () => {
  try {
    await api.post('/agendamentos/agendar', agendamento.value)
    toast.success('Atendimento cadastrado com sucesso!')
    emit('salvo')
    emit('fechar')
  } catch (error) {
    console.error(error)

    if (error.response && error.response.data && error.response.data.message) {
      toast.error(error.response.data.message)
    } else if (error.response && error.response.data && error.response.data.erros) {
      toast.error(error.response.data.erros.join('\n'))
    } else {
      toast.error('Erro ao cadastrar atendimento.')
    }
  }
}
</script>

<style scoped>
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.6);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.modal {
  background-color: #ffffff;
  border-radius: 12px;
  padding: 30px;
  width: 100%;
  max-width: 600px;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
  position: relative;
}

h2 {
  font-size: 24px;
  margin-bottom: 20px;
  color: #007bff;
}

.form-group {
  margin-bottom: 14px;
}

label {
  display: block;
  font-weight: 600;
  margin-bottom: 4px;
  color: #333;
}

input,
select,
textarea {
  width: 100%;
  padding: 10px;
  border-radius: 8px;
  border: 1px solid #ccc;
}

.botoes {
  display: flex;
  justify-content: flex-end;
  gap: 10px;
  margin-top: 20px;
}

.btn-salvar {
  background-color: #007bff;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 8px;
  cursor: pointer;
}

.btn-salvar:hover {
  background-color: #0056b3;
}

.btn-cancelar {
  background-color: #ccc;
  color: #333;
  border: none;
  padding: 10px 20px;
  border-radius: 8px;
  cursor: pointer;
}

.btn-cancelar:hover {
  background-color: #999;
}
</style>
