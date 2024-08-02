<template>
    <div>
      <h1>新增會員</h1>
      <form @submit.prevent="submitForm">
        <div>
          <label for="userName">用户名:</label>
          <input type="text" id="userName" v-model="user.userName" required />
        </div>
        <div>
          <label for="password">密码:</label>
          <input type="password" id="password" v-model="user.password" required />
        </div>
        <div>
          <label for="nickname">昵称:</label>
          <input type="text" id="nickname" v-model="user.nickname" />
        </div>
        <div>
          <label for="email">邮箱:</label>
          <input type="email" id="email" v-model="user.email" required />
        </div>
        <div>
          <label for="accountStatus">账户状态:</label>
          <input type="text" id="accountStatus" v-model="user.accountStatus" />
        </div>
        <div>
          <label for="userRole">用户角色:</label>
          <input type="text" id="userRole" v-model="user.userRole" />
        </div>
        <button type="submit">提交</button>
      </form>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        user: {
          userName: '',
          password: '',
          nickname: '',
          email: '',
          accountStatus: '',
          userRole: '',
        },
      };
    },
    methods: {
      async submitForm() {
        try {
          const response = await axios.post('http://localhost:8080/signUpUser', this.user);
          console.log('新增会员成功:', response.data);
        } catch (error) {
          console.error('新增会员时出错:', error);
        }
      },
    },
  };
  </script>
  
  <style scoped>
  form {
    display: flex;
    flex-direction: column;
    max-width: 400px;
    margin: 0 auto;
  }
  
  div {
    margin-bottom: 10px;
  }
  
  label {
    margin-bottom: 5px;
  }
  
  input {
    padding: 8px;
    font-size: 14px;
  }
  
  button {
    padding: 10px;
    background-color: #007bff;
    color: white;
    border: none;
    cursor: pointer;
  }
  
  button:hover {
    background-color: #0056b3;
  }
  </style>
  