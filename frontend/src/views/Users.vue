<template>
  <div class="card border-dark" style="max-width: auto; margin: auto; padding: auto;">
  <div class="card-body">
    <div v-show="loading">載入中...</div>
    <div v-show="!loading">
        <div v-if="users.length === 0">目前尚未有人員資料</div>
        <UserTable v-else :users="users" />
    </div>
  </div>
</div>
</template>

<script>
import { ref, onMounted } from 'vue'
import UserTable from '@/components/UserTable.vue'
import { fetchUsers } from '@/services/userApi'

export default {
  components: {
    UserTable
  },
  setup() {
    const users = ref([])
    const loading = ref(true)

    const loadUsers = async () => {
      try {
        loading.value = true
        users.value = await fetchUsers()
        console.info('Loaded users:', users.value)
      } catch (error) {
        console.info('Error loading users:', error)
      } finally {
        loading.value = false
      }
    }

    onMounted(() => {
      loadUsers()
    })

    return {
      users,
      loading
    }
  }
}
</script>
