<template>
  <div>
    <div v-if="message" :class="{'success-message': isSuccess, 'error-messages': !isSuccess}">
      {{ message }}
    </div>
    <h1>使用者註冊</h1>
    <form @submit.prevent="submitForm">
    <table >
      <tr>
        <td><label for="account">帳號:</label></td>
        <td><input type="text" id="account" v-model="user.account" /></td>
      </tr>
      <tr>
        <td><label for="password">密碼:</label></td>
        <td><input type="text" id="password" v-model="user.password" /></td>
      </tr>
      <tr>
        <td><label for="confirmPassword">確認密碼:</label></td>
        <td><input type="text" id="confirmPassword" v-model="user.confirmPassword" /></td>
      </tr>
      <tr>
        <td><label for="email">信箱:</label></td>
        <td><input type="text" id="email" v-model="user.email" /></td>
      </tr>
      <tr>
        <td><label for="nickname">暱稱:</label></td>
        <td><input type="text" id="nickname" v-model="user.nickname" /></td>
      </tr>
      <tr>
        <td><label for="accountStatus">帳號狀態:</label></td>
        <td><input type="text" id="accountStatus" v-model="user.accountStatus" /></td>
      </tr>
      <tr>
        <td><label for="userRole">帳號角色:</label></td>
        <td><input type="text" id="userRole" v-model="user.userRole" /></td>
      </tr>
      <tr>
        <td colspan="2" style="text-align: center;">
          <button type="submit">提交</button>
        </td>
      </tr>
    </table>
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
      message:'',
      isSuccess: false,
      errorMessages: ''
    };
  },
  methods: {
    async submitForm() {
      try {
        const response = await axios.post('http://localhost:8080/api/signUpUser', this.user);
        console.log('新增會員成功:', response.data);
        this.message="註冊成功";
        this.isSuccess=true;
        this.errorMessages = '';
        this.user=response.data;
      } catch (error) {
        if (error.response && error.response.data) {
          this.errorMessages = error.response.data;
        } else {
          this.errorMessages = '新增會員失敗';
        }
        this.message="註冊失敗";
        this.isSuccess=false;
      }
    },
  },
};
</script>

