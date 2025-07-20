<template>
    <div class="modal-overlay" v-if="mostrar">
        <div class="modal">
            <h2>✏️ Editar Paciente</h2>

            <form @submit.prevent="atualizarPaciente">
                <div class="form-group">
                    <label for="nome">Nome</label>
                    <input id="nome" v-model="paciente.nome" type="text" required />
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input id="email" v-model="paciente.email" type="email" required />
                </div>
                <div class="form-group">
                    <label for="telefone">Telefone</label>
                    <input id="telefone" v-model="paciente.telefone" type="text" required />
                </div>

                <template v-if="paciente.endereco">
                    <div class="form-group">
                        <label for="logradouro">Logradouro</label>
                        <input id="logradouro" v-model="paciente.endereco.logradouro" type="text" required />
                    </div>
                    <div class="form-group">
                        <label for="numero">Número</label>
                        <input id="numero" v-model="paciente.endereco.numero" type="text" required />
                    </div>
                    <div class="form-group">
                        <label for="bairro">Bairro</label>
                        <input id="bairro" v-model="paciente.endereco.bairro" type="text" required />
                    </div>
                    <div class="form-group">
                        <label for="cidade">Cidade</label>
                        <input id="cidade" v-model="paciente.endereco.cidade" type="text" required />
                    </div>
                    <div class="form-group">
                        <label for="uf">UF</label>
                        <input id="uf" v-model="paciente.endereco.uf" type="text" required />
                    </div>
                    <div class="form-group">
                        <label for="cep">CEP</label>
                        <input id="cep" v-model="paciente.endereco.cep" type="text" required />
                    </div>
                </template>

                <div class="botoes">
                    <button type="submit" class="btn-salvar">Salvar</button>
                    <button type="button" class="btn-cancelar" @click="$emit('fechar')">Cancelar</button>
                </div>
            </form>
        </div>
    </div>
</template>

<script setup>
import { ref, watch } from 'vue'
import api from '@/services/api'
import { toast } from 'vue3-toastify'
import 'vue3-toastify/dist/index.css'

const props = defineProps({
    mostrar: Boolean,
    pacienteSelecionado: Object
})
const emit = defineEmits(['fechar', 'salvo'])

const paciente = ref({
    nome: '',
    email: '',
    telefone: '',
    endereco: {
        logradouro: '',
        numero: '',
        bairro: '',
        cidade: '',
        numero: '',
        uf: '',
        cep: ''
    }
})

watch(
    () => props.pacienteSelecionado,
    (novo) => {
        if (novo) {
            paciente.value = {
                nome: novo.nome || '',
                email: novo.email || '',
                telefone: novo.telefone || '',
                endereco: {
                    logradouro: novo.endereco?.logradouro || '',
                    numero: novo.endereco?.numero || '',
                    bairro: novo.endereco?.bairro || '',
                    cidade: novo.endereco?.cidade || '',
                    uf: novo.endereco?.uf || '',
                    cep: novo.endereco?.cep || ''
                }
            }
        }
    },
    { immediate: true }
)

const atualizarPaciente = async () => {
    try {
        await api.put(`/pacientes/${props.pacienteSelecionado.id}`, paciente.value)
        toast.success('Paciente atualizado com sucesso!')
        emit('salvo')
        emit('fechar')
    } catch (error) {
        toast.error('Erro ao atualizar paciente.')
        console.error(error)
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

input {
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