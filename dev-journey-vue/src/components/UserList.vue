<template>
  <div>
    <h1>用户列表</h1>
    <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>帳號</th>
          <th>密码</th>
          <th>昵称</th>
          <th>邮箱</th>
          <th>账户状态</th>
          <th>用户角色</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="user in users" :key="user.userId">
          <td>{{ user.userId }}</td>
          <td>{{ user.account }}</td>
          <td>{{ user.password }}</td>
          <td>{{ user.nickname }}</td>
          <td>{{ user.email }}</td>
          <td>{{ user.accountStatus }}</td>
          <td>{{ user.userRole }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      users: [],
    };
  },
  created() {
    this.fetchUsers();
  },
  methods: {
    async fetchUsers() {
      try {
        const response = await axios.get('http://localhost:8080/userList');
        this.users = response.data;
      } catch (error) {
        console.error('获取用户数据时出错:', error);
      }
    },
  },
};
</script>

<style scoped>
table {
  width: 100%;
  border-collapse: collapse;
}
th, td {
  border: 1px solid #ddd;
  padding: 8px;
}
th {
  background-color: #f2f2f2;
}
</style>
