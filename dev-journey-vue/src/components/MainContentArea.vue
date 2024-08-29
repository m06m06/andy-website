<template>
    <div class="main-content-area">
      <component :is="currentPage"></component>
    </div>
  </template>
  
  <script>
  import UserList from './page/UserList.vue';
  import SignupUser from './page/SignUpUser.vue';
  import '@/assets/css/main-content-area.css'; 
  
  export default {
    name: 'MainContentArea',
    components: {
      UserList,
      SignupUser
    },
    props: ['currentPage']
  }
  </script>

  