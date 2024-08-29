<template>
  <div class="list">
    <div v-if="message" :class="{'success-message': isSuccess, 'error-messages': !isSuccess}">
      {{ message }}
    </div>
    <h1>使用者列表</h1>
    <table >
      <thead>
        <tr>
          <th>編號</th>
          <th>帳號</th>
          <th>密碼</th>
          <th>信箱</th>
          <th>暱稱</th>
          <th>帳號狀態</th>
          <th>帳號角色</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="user in users" :key="user.userId">
          <td>{{ user.userId }}</td>
          <td>{{ user.account }}</td>
          <td>{{ user.password }}</td>
          <td>{{ user.email }}</td>
          <td>{{ user.nickname }}</td>
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
      message:'',
      isSuccess:false,
    };
  },
  created() {
    this.fetchUsers();
  },
  methods: {
    async fetchUsers() {
      try {
        const response = await axios.get('http://localhost:8080/api/userList');
        this.users = response.data;
        this.message='查詢成功';
        this.isSuccess=true;
      } catch (error) {
        console.error('使用者查詢錯誤：', error);
        this.message='查詢失敗';
        this.isSuccess=false;
      }
    },
  },
};
</script>
