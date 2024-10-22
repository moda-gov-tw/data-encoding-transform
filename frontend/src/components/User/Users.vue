<template>
  <div class="card border-dark" style="max-width: auto; margin: auto; padding: auto">
    <div class="card-body">
      <div>
        <div
          class="alert alert-danger alert-dismissible fade show"
          role="alert"
          v-if="alert.isOpen"
        >
          {{ alert.msg }}
          <button type="button" class="close" @click="alert.isOpen = false">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <p>總人數 : {{ total }}</p>
        <table class="table table-bordered">
          <thead>
            <tr>
              <th scope="col">序號</th>
              <th scope="col">姓名</th>
              <th scope="col">電話</th>
              <th scope="col">年齡</th>
              <th scope="col">動作</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="user in users" :key="user.id">
              <th scope="row">{{ user.id }}</th>
              <td>{{ user.name }}</td>
              <td>{{ user.phone }}</td>
              <td>{{ user.age }}</td>
              <td>
                <button
                  id="action-button"
                  title="Edit"
                  class="btn btn-warning"
                  role="button"
                  @click="editUser(user.id)"
                >
                  <i class="fa fa-edit"></i>
                  編輯
                </button>
                <button
                  id="action-button"
                  title="Remove"
                  class="btn btn-danger"
                  role="button"
                  @click="removeUser(user.id)"
                >
                  <i class="fa fa-trash-alt"></i>
                  刪除
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "UsersList",
  data() {
    return {
      alert: {
        isOpen: false,
        msg: ""
      },
      users: [],
      total: 0
    };
  },
  created() {
    this.fetchUsers();
  },
  methods: {
    fetchUsers() {
      this.$http.get("user/listByFile")
        .then(response => {
          if (response.data && response.data.data) {
            this.users = response.data.data; 
            this.total = response.data.totalCount;
          } else {
            this.alert.isOpen = true;
            this.alert.msg = "無法取得用戶資料"; 
          }
        })
        .catch(error => {
          console.error(error);
          this.alert.isOpen = true;
          this.alert.msg = "後端程式發生異常";
        });
    },
    editUser(id) {
      console.log(`Edit user with ID: ${id}`);
    },
    removeUser(id) {
      console.log(`Remove user with ID: ${id}`);
    }
  }
};
</script>

<style scoped>
#action-button {
  margin: 0px 2% 0px 2%;
}
.table {
  width: 100%;
  margin: 0 auto;
  border-collapse: collapse;
}

.table th,
.table td {
  padding: 8px 12px;
  text-align: center;
  vertical-align: middle;
}

.table-bordered th,
.table-bordered td {
  border: 1px solid #dee2e6;
}

.table th {
  background-color: #f8f9fa;
  font-weight: bold;
}

.table td {
  background-color: #fff;
}

.btn {
  padding: 4px 8px;
  font-size: 14px;
}

.fa {
  margin-right: 4px;
}

tbody tr:hover {
  background-color: #f1f1f1;
}
</style>
