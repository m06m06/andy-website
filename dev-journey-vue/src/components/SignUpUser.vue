<template>
  <div>
    <h1>新增會員</h1>
    <form @submit.prevent="submitForm">
      <div>
        <label for="account">用户名:</label>
        <input type="text" id="account" v-model="user.account" required />
      </div>
      <div>
        <label for="password">密码:</label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <div>
        <label for="confirmPassword">確認密码:</label>
        <input type="confirmPassword" id="confirmPassword" v-model="user.confirmPassword" required />
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
    <div v-if="errorMessages.length > 0" class="error-messages">
      <ul>
        <li v-for="(msg, index) in errorMessages" :key="index">{{ msg }}</li>
      </ul>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      user: {
        account: '',
        password: '',
        confirmPassword: '',
        nickname: '',
        email: '',
        accountStatus: '',
        userRole: '',
      },
      errorMessages: []
    };
  },
  methods: {
    async submitForm() {
      try {
        const response = await axios.post('http://localhost:8080/signUpUser', this.user);
        console.log('新增会员成功:', response.data);
        this.errorMessages = [];
      } catch (error) {
        if (error.response && error.response.data) {
          this.errorMessages = error.response.data;
        } else {
          this.errorMessages = ['新增会员时发生错误'];
        }
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

.error-messages {
  color: red;
  margin-top: 20px;
}

.error-messages ul {
  list-style-type: none;
  padding: 0;
}

.error-messages li {
  margin-bottom: 5px;
}
</style>
