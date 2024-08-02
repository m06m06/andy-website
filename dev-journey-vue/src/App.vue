<template>
  <div id="app">
    <div class="container">
      <SidebarMenu @select-page="currentPage = $event" />
      <MainContentArea :currentPage="currentPage" />
    </div>
  </div>
</template>

<script>
import SidebarMenu from './components/SidebarMenu.vue';
import MainContentArea from './components/MainContentArea.vue';

export default {
  name: 'App',
  components: {
    SidebarMenu,
    MainContentArea
  },
  data() {
    return {
      currentPage: 'UserList'
    };
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  height: 100vh;
  display: flex;
  margin: 0;
  padding: 0;
}

.container {
  display: flex;
  flex: 1;
}
</style>
