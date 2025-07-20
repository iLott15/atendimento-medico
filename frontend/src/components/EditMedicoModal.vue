<template>
    <div class="modal-overlay" v-if="mostrar">
        <div class="modal">
            <h2>✏️ Editar Médico</h2>

            <form @submit.prevent="atualizarMedico">
                <div class="form-group">
                    <label for="nome">Nome</label>
                    <input id="nome" v-model="medico.nome" type="text" required />
                </div>

                <div class="form-group">
                    <label for="email">Email</label>
                    <input id="email" v-model="medico.email" type="email" required />
                </div>

                <div class="form-group">
                    <label for="especialidade">Especialidade</label>
                    <input id="especialidade" v-model="medico.especialidade" type="text" required />
                </div>

                <div class="form-group">
                    <label for="crm">CRM</label>
                    <input id="crm" v-model="medico.crm" type="text" required />
                </div>

                <!-- Campos de endereço -->
                <div class="form-group">
                    <label for="logradouro">Logradouro</label>
                    <input id="logradouro" v-model="medico.endereco.logradouro" type="text" required />
                </div>

                <div class="form-group">
                    <label for="numero">Número</label>
                    <input id="numero" v-model="medico.endereco.numero" type="text" required />
                </div>

                <div class="form-group">
                    <label for="bairro">Bairro</label>
                    <input id="bairro" v-model="medico.endereco.bairro" type="text" required />
                </div>

                <div class="form-group">
                    <label for="cidade">Cidade</label>
                    <input id="cidade" v-model="medico.endereco.cidade" type="text" required />
                </div>

                <div class="form-group">
                    <label for="uf">UF</label>
                    <input id="uf" v-model="medico.endereco.uf" type="text" maxlength="2" required />
                </div>

                <div class="form-group">
                    <label for="cep">CEP</label>
                    <input id="cep" v-model="medico.endereco.cep" type="text" placeholder="00000-000"
                        pattern="\d{5}-\d{3}" required />
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
import { ref, watch } from 'vue'
import api from '@/services/api'
import { toast } from 'vue3-toastify'

const props = defineProps({
    mostrar: Boolean,
    medicoSelecionado: Object
})
const emit = defineEmits(['fechar', 'salvo'])

const medico = ref({
    nome: '',
    email: '',
    especialidade: '',
    crm: '',
    endereco: {
        logradouro: '',
        numero: '',
        bairro: '',
        cidade: '',
        uf: '',
        cep: ''
    }
})

watch(
    () => props.medicoSelecionado,
    (novo) => {
        if (novo) {
            medico.value = {
                nome: novo.nome || '',
                email: novo.email || '',
                especialidade: novo.especialidade || '',
                crm: novo.crm || '',
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

const atualizarMedico = async () => {
    try {
        await api.put(`/medicos/${props.medicoSelecionado.id}`, medico.value)
        toast.success('Médico atualizado com sucesso!')
        emit('salvo')
        emit('fechar')
    } catch (error) {
        console.error('Erro ao atualizar médico:', error)
        toast.error('Erro ao atualizar médico.')
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