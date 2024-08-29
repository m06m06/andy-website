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
import '@/assets/css/app.css';  

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


