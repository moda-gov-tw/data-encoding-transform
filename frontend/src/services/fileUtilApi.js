import { API_BASE_URL, API_ENDPOINTS } from '@/config/api'
import axios from 'axios'

export const uploadFile = async (formData) => {
  try {
    const response = await axios.post(`${API_BASE_URL}${API_ENDPOINTS.upload}`, formData, {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    })
    if (response.status !== 200) {
      throw new Error(`HTTP error! status: ${response.status}`)
    }
    return 'success'
  } catch (error) {
    console.error('Upload API Error:', error)
    throw error
  }
}

export const fileExport = async ({ alignment, filled, charset }) => {
  try {
    const response = await fetch(`${API_BASE_URL}/api/fileExport`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
      },
      body: new URLSearchParams({ alignment, filled, charset }),
    })

    if (response.ok) {
      return `${API_BASE_URL}/api/download`
    } else {
      throw new Error('匯出失敗')
    }
  } catch (error) {
    console.error('File Export API Error:', error)
    throw error
  }
}
