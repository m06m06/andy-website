<template>
  <div class="sidebar-menu">
    <ul>
      <li><a href="#" @click="selectPage('UserList')">使用者清單</a></li>
      <li><a href="#" @click="selectPage('SignupUser')">使用者註冊</a></li>
    </ul>
  </div>
</template>

<script>
import '@/assets/css/sidebar-menu.css';
export default {
  name: 'SidebarMenu',
  methods: {
    selectPage(page) {
      this.$emit('select-page', page);
    }
  }
}
</script>


