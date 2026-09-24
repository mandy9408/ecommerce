//Ahora tenemos:
//RegisterView ->userService->Spring Boot


import type {
  CreateUserRequest,
  UserResponse,
  ValidationErrors
} from '../types/user'

export class ApiError extends Error {
  status: number
  errors: ValidationErrors

  constructor(
    status: number,
    message: string,
    errors: ValidationErrors = {}
  ) {
    super(message)

    this.name = 'ApiError'
    this.status = status
    this.errors = errors
  }
}

export async function createUser(
  user: CreateUserRequest
): Promise<UserResponse> {

  const response = await fetch(
    `${import.meta.env.VITE_API_URL}/users`,
    {
      method: 'POST',

      headers: {
        'Content-Type': 'application/json'
      },

      body: JSON.stringify(user)
    }
  )

  const data = await response.json()

  if (!response.ok) {
    throw new ApiError(
      response.status,
      data.message ?? 'No se ha podido crear el usuario',
      data.errors ?? {}
    )
  }

  return data
}