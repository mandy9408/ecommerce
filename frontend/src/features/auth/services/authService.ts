import type { LoginRequest } from '../types/auth'
import type { UserResponse } from '../../users/types/user'

const API_URL = import.meta.env.VITE_API_URL

export async function login(
  credentials: LoginRequest
): Promise<UserResponse> {

  const response = await fetch(
    `${API_URL}/auth/login`,
    {
      method: 'POST',

      headers: {
        'Content-Type': 'application/json'
      },

      credentials: 'include',

      body: JSON.stringify(credentials)
    }
  )

  const data = await response.json()

  if (!response.ok) {
    throw new Error(
      data.message ?? 'No se ha podido iniciar sesión'
    )
  }

  return data
}


export async function getCurrentUser():
  Promise<UserResponse> {

  const response = await fetch(
    `${API_URL}/auth/me`,
    {
      method: 'GET',
      credentials: 'include'
    }
  )

  if (!response.ok) {
    throw new Error(
      'No hay ningún usuario autenticado'
    )
  }

  return response.json()
}