<template>
<div class="card border-dark" style="max-width: auto; margin: auto; padding: auto;">
  <div class="card-body">
    <div>
      <div class="alert alert-danger alert-dismissible fade show" role="alert" v-if="alert.isOpen">
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
              </button>
              <button
                id="action-button"
                title="Remove"
                class="btn btn-danger"
                role="button"
                @click="removeUser(user.id)"
              >
                <i class="fa fa-trash-alt"></i>
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
  name: "Users",
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
    this.$http.get("user/listByFile").then(
      response => {
        this.users = response.body.data;
        this.total = response.data.totalCount;
      },
      () => {
        this.alert.isOpen = true;
        this.alert.msg = "後端程式發生異常";
      }
    );
    
  }
};
</script>

<style scoped>
#action-button {
  margin: 0px 2% 0px 2%;
}

.table {
    table-layout: auto;
    width: 100%; 
}
.table th {
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
}
</style>


