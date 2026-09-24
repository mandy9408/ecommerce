import {
  createRouter,
  createWebHistory
} from 'vue-router'

import RegisterView
  from '../features/users/views/RegisterView.vue'

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
    }

  ]

})

export default router