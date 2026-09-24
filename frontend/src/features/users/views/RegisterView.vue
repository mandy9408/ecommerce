<script setup lang="ts">
import { ref } from 'vue'

import {
  ApiError,
  createUser
} from '../services/userService'

const name = ref('')
const email = ref('')

const loading = ref(false)

const successMessage = ref('')
const errorMessage = ref('')

const fieldErrors = ref<Record<string, string>>({})

const password = ref('')

const register = async () => {

  successMessage.value = ''
  errorMessage.value = ''
  fieldErrors.value = {}

  loading.value = true

  try {

    await createUser({
      name: name.value,
      email: email.value,
      password: password.value
    })

    successMessage.value =
      'Cuenta creada correctamente'
//Después de un registro correcto puedes limpiar:
    name.value = ''
    email.value = ''
    password.value = ''

  } catch (error) {

    if (error instanceof ApiError) {

      errorMessage.value = error.message
      fieldErrors.value = error.errors

    } else {

      errorMessage.value =
        'No se puede conectar con el servidor'
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
          Crear cuenta
        </h1>

        <p class="text-zinc-500 mt-2">
          Regístrate para empezar a comprar.
        </p>

      </header>


      <form
        class="space-y-5"
        @submit.prevent="register"
      >

        <div>

          <label
            for="name"
            class="block text-sm font-medium text-zinc-700 mb-1"
          >
            Nombre
          </label>

          <input
            id="name"
            v-model="name"
            type="text"
            placeholder="Amanda"
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

          <p
            v-if="fieldErrors.name"
            class="mt-1 text-sm text-red-600"
          >
            {{ fieldErrors.name }}
          </p>

        </div>


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
            placeholder="amanda@email.com"
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

          <p
            v-if="fieldErrors.email"
            class="mt-1 text-sm text-red-600"
          >
            {{ fieldErrors.email }}
          </p>

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
    placeholder="Mínimo 8 caracteres"
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

  <p
    v-if="fieldErrors.password"
    class="mt-1 text-sm text-red-600"
  >
    {{ fieldErrors.password }}
  </p>
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
            disabled:cursor-not-allowed
          "
        >

          {{
            loading
              ? 'Creando cuenta...'
              : 'Crear cuenta'
          }}

        </button>

      </form>

    </section>

  </main>

</template>