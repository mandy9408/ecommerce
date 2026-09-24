//Es prácticamente el equivalente frontend de tus DTO del backend.
export interface CreateUserRequest {
  name: string
  email: string
  password: string
}

export interface UserResponse {
  id: number
  name: string
  email: string
}

export interface ValidationErrors {
  [field: string]: string
}