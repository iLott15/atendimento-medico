<script setup>
import { ref, onMounted } from 'vue'
import { getVinculosDoMedico } from '@/services/api'

const diasDisponiveis = ref([])
const diasIndisponiveis = ref([])

onMounted(async () => {
  const { data } = await getVinculosDoMedico(1) // idMedico fixo só pra teste
  diasDisponiveis.value = data.filter(d => d.disponivel)
  diasIndisponiveis.value = data.filter(d => !d.disponivel)
})
</script>

<template>
  <div>
    <h2>Disponibilidade do Médico</h2>
    <div>
      <h3>Dias Disponíveis</h3>
      <ul>
        <li v-for="dia in diasDisponiveis" :key="dia.dia">{{ dia.dia }}</li>
      </ul>

      <h3>Dias Indisponíveis</h3>
      <ul>
        <li v-for="dia in diasIndisponiveis" :key="dia.dia">{{ dia.dia }}</li>
      </ul>
    </div>
  </div>
</template>