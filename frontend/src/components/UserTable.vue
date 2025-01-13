<template>
  <div class="user-table">
    <table>
      <thead>
        <tr>
          <th>序號</th>
          <th>姓名</th>
          <th>電話</th>
          <th>年齡</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="user in users" :key="user.id">
          <td>{{ user.id }}</td>
          <td>{{ user.name }}</td>
          <td>{{ user.pno }}</td>
          <td>{{ user.age }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  name: 'UserTable',
  props: {
    users: {
      type: Array,
      required: true
    }
  }
}
</script>
<style scoped>
.user-table {
  margin: 20px;
  overflow-x: auto;
}

table {
  width: 100%;
  border-collapse: collapse;
  background: white;
}

th, td {
  padding: 10px 15px;
  text-align: left;
  border: 1px solid #ddd;
}

th {
  background-color: #f5f5f5;
  font-weight: bold;
}

tr {
  border-bottom: 1px solid #ddd; 
}

button {
  margin: 0 5px;
  padding: 4px 8px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:first-child {
  background-color: #42b983;
  color: white;
}

button:last-child {
  background-color: #ff4444;
  color: white;
}
</style>
