<script setup lang="ts">
import { ref } from 'vue'

import {
  login,
  getCurrentUser
} from '../services/authService'

const email = ref('')
const password = ref('')

const loading = ref(false)
const errorMessage = ref('')
const successMessage = ref('')

const handleLogin = async () => {

  errorMessage.value = ''
  successMessage.value = ''
  loading.value = true

  try {

    const user = await login({
      email: email.value,
      password: password.value
    })

    successMessage.value =
      `Bienvenida, ${user.name}`

    console.log(
      'Usuario autenticado:',
      user
    )

    // Comprobación temporal de la sesión
    const currentUser =
      await getCurrentUser()

    console.log(
      'Usuario recuperado mediante sesión:',
      currentUser
    )

  } catch (error) {

    if (error instanceof Error) {
      errorMessage.value = error.message
    } else {
      errorMessage.value =
        'Ha ocurrido un error inesperado'
    }

  } finally {

    loading.value = false
  }
}
</script>

<template>

  <main
    class="min-h-screen bg-zinc-100 flex items-center justify-center px-4"
  >

    <section
      class="w-full max-w-md bg-white rounded-2xl shadow-lg p-8"
    >

      <header class="mb-8">

        <p class="text-sm font-semibold text-indigo-600">
          E-COMMERCE
        </p>

        <h1 class="text-3xl font-bold text-zinc-900 mt-1">
          Iniciar sesión
        </h1>

        <p class="text-zinc-500 mt-2">
          Accede a tu cuenta.
        </p>

      </header>


      <form
        class="space-y-5"
        @submit.prevent="handleLogin"
      >

        <div>

          <label
            for="email"
            class="block text-sm font-medium text-zinc-700 mb-1"
          >
            Email
          </label>

          <input
            id="email"
            v-model="email"
            type="email"
            class="
              w-full rounded-lg
              border border-zinc-300
              px-4 py-3
              outline-none
              focus:border-indigo-500
              focus:ring-2
              focus:ring-indigo-200
            "
          />

        </div>


        <div>

          <label
            for="password"
            class="block text-sm font-medium text-zinc-700 mb-1"
          >
            Contraseña
          </label>

          <input
            id="password"
            v-model="password"
            type="password"
            class="
              w-full rounded-lg
              border border-zinc-300
              px-4 py-3
              outline-none
              focus:border-indigo-500
              focus:ring-2
              focus:ring-indigo-200
            "
          />

        </div>


        <p
          v-if="errorMessage"
          class="rounded-lg bg-red-50 p-3 text-sm text-red-700"
        >
          {{ errorMessage }}
        </p>


        <p
          v-if="successMessage"
          class="rounded-lg bg-green-50 p-3 text-sm text-green-700"
        >
          {{ successMessage }}
        </p>


        <button
          type="submit"
          :disabled="loading"
          class="
            w-full rounded-lg
            bg-zinc-900
            text-white
            py-3
            font-semibold
            hover:bg-zinc-800
            disabled:opacity-50
          "
        >

          {{
            loading
              ? 'Iniciando sesión...'
              : 'Iniciar sesión'
          }}

        </button>

      </form>

    </section>

  </main>

</template>