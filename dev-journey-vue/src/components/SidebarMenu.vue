<template>
  <div class="sidebar">
    <ul>
      <li><a href="#" @click="selectPage('UserList')">用户列表</a></li>
      <li><a href="#" @click="selectPage('SignupUser')">新增会员</a></li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'SidebarMenu',
  methods: {
    selectPage(page) {
      this.$emit('select-page', page);
    }
  }
}
</script>

<style scoped>
.sidebar {
  width: 200px;
  background-color: #f4f4f4;
  padding: 10px;
  height: 100vh; /* Full height */
}

.sidebar ul {
  list-style-type: none;
  padding: 0;
}

.sidebar li {
  margin-bottom: 10px;
}

.sidebar a {
  text-decoration: none;
  color: #333;
}

.sidebar a:hover {
  text-decoration: underline;
}
</style>
