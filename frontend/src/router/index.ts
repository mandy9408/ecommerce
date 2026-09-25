import { createRouter, createWebHistory } from 'vue-router'
import LoginView from '../features/auth/views/LoginView.vue'
import RegisterView from '../features/users/views/RegisterView.vue'

const router = createRouter({

  history: createWebHistory(),

  routes: [

    {
      path: '/',
      redirect: '/register'
    },

    {
      path: '/register',
      name: 'register',
      component: RegisterView
    },
    {
      path: '/login',
      name: 'login',
      component: LoginView
    }

  ]

})

export default router