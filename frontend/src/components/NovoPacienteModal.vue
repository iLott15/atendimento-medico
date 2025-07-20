<template>
    <div class="overlay" v-if="mostrar">
        <div class="modal">
            <h2>➕ Cadastrar Novo Paciente</h2>

            <form @submit.prevent="cadastrarPaciente">
                <div class="form-group">
                    <label for="nome">Nome</label>
                    <input id="nome" v-model="paciente.nome" required />
                </div>

                <div class="form-group">
                    <label for="email">Email</label>
                    <input id="email" type="email" v-model="paciente.email" required />
                </div>

                <div class="form-group">
                    <label for="telefone">Telefone</label>
                    <input id="telefone" v-model="paciente.telefone" required />
                </div>

                <div class="form-group">
                    <label for="logradouro">Logradouro</label>
                    <input id="logradouro" v-model="paciente.endereco.logradouro" required />
                </div>

                <div class="form-group">
                    <label for="numero">Número</label>
                    <input id="numero" v-model="paciente.endereco.numero" required />
                </div>

                <div class="form-group">
                    <label for="bairro">Bairro</label>
                    <input id="bairro" v-model="paciente.endereco.bairro" required />
                </div>

                <div class="form-group">
                    <label for="cidade">Cidade</label>
                    <input id="cidade" v-model="paciente.endereco.cidade" required />
                </div>

                <div class="form-group">
                    <label for="uf">UF</label>
                    <input id="uf" v-model="paciente.endereco.uf" maxlength="2" style="text-transform: uppercase"
                        required />
                </div>

                <div class="form-group">
                    <label for="cep">CEP</label>
                    <input id="cep" v-model="paciente.endereco.cep" @blur="formatarCep" maxlength="9" required />
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
import { reactive } from 'vue'
import { toast } from 'vue3-toastify'
import api from '@/services/api'
import 'vue3-toastify/dist/index.css'

defineProps({ mostrar: Boolean })
const emit = defineEmits(['fechar', 'salvo'])

const paciente = reactive({
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

const formatarCep = () => {
    let cep = paciente.endereco.cep.replace(/\D/g, '') // remove não dígitos
    if (cep.length === 8) {
        paciente.endereco.cep = cep.replace(/(\d{5})(\d{3})/, '$1-$2')
    }
}

const cadastrarPaciente = async () => {
    try {
        await api.post('/pacientes', paciente)
        toast.success('Paciente cadastrado com sucesso!')
        resetarFormulario()
        emit('salvo')
    } catch (error) {
        toast.error('Erro ao cadastrar paciente.')
        console.error('Erro ao cadastrar paciente:', error)
    }
}

const resetarFormulario = () => {
    paciente.nome = ''
    paciente.email = ''
    paciente.telefone = ''
    paciente.endereco = {
        logradouro: '',
        numero: '',
        bairro: '',
        cidade: '',
        uf: '',
        cep: ''
    }
}
</script>

<style scoped>
.overlay {
    position: fixed;
    top: 0;
    left: 0;
    height: 100vh;
    width: 100vw;
    background: rgba(0, 0, 0, 0.6);
    display: flex;
    justify-content: center;
    align-items: center;
    z-index: 1000;
}

.modal {
    background: white;
    padding: 30px;
    border-radius: 12px;
    width: 100%;
    max-width: 500px;
    box-shadow: 0 10px 25px rgba(0, 0, 0, 0.3);
}

.modal h2 {
    margin-bottom: 20px;
    text-align: center;
    color: #007bff;
}

.form-group {
    margin-bottom: 16px;
    display: flex;
    flex-direction: column;
}

.form-group label {
    margin-bottom: 6px;
    font-weight: 500;
}

.form-group input {
    padding: 10px;
    font-size: 15px;
    border: 1px solid #ccc;
    border-radius: 6px;
}

.botoes {
    display: flex;
    justify-content: space-between;
    margin-top: 20px;
}

.btn-salvar {
    background-color: #007bff;
    color: white;
    border: none;
    padding: 10px 22px;
    border-radius: 6px;
    font-weight: bold;
    cursor: pointer;
}

.btn-salvar:hover {
    background-color: #0056b3;
}

.btn-cancelar {
    background-color: #aaa;
    color: white;
    border: none;
    padding: 10px 22px;
    border-radius: 6px;
    font-weight: bold;
    cursor: pointer;
}

.btn-cancelar:hover {
    background-color: #888;
}
</style>