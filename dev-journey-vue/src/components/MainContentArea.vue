<template>
    <div class="main-content">
      <component :is="currentPage"></component>
    </div>
  </template>
  
  <script>
  import UserList from './UserList.vue';
  import SignupUser from './SignUpUser.vue';
  
  export default {
    name: 'MainContentArea',
    components: {
      UserList,
      SignupUser
    },
    props: ['currentPage']
  }
  </script>
  
  <style scoped>
  .main-content {
    flex: 1;
    padding: 20px;
  }
  </style>
  