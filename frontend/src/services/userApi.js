import { API_BASE_URL, API_ENDPOINTS } from '@/config/api'

export const fetchUsers = async () => {
  try {
    const response = await fetch(`${API_BASE_URL}${API_ENDPOINTS.users}`)
    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`)
    }
    const result = await response.json()
    return result.data
  } catch (error) {
    console.info('API Error:', error)
    throw error
  }
}
