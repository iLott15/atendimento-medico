import { createRouter, createWebHistory } from 'vue-router'
import { useAuthStore } from '@/stores/auth'
import LoginView from '@/views/LoginView.vue'
import DashboardView from '@/views/DashboardView.vue'
import MedicosView from '@/views/MedicosView.vue'
import HomeView from '@/views/HomeView.vue'
import PacientesView from '@/views/PacientesView.vue'
import AtendimentoView from '@/views/AtendimentoView.vue'


const routes = [
  { path: '/login', component: LoginView },
  { path: '/', component: DashboardView, meta: { requiresAuth: true } },
  { path: '/medicos', component: MedicosView, meta: { requiresAuth: true } },
  { path: '/pacientes', component: PacientesView, meta: { requiresAuth: true } },
  { path: '/atendimentos', component: AtendimentoView, meta: { requiresAuth: true } },
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  const auth = useAuthStore()

  if (to.meta.requiresAuth && !auth.token) {
    next('/login')
  } else {
    next()
  }
})

export default router