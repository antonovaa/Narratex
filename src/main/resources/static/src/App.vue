<template>
  <div class="wrapper">
    <div class="my-header">
      <header-my></header-my>
    </div>


    <div class="main-content">
      <general></general>
    </div>
  </div>
</template>

<script>

  import HeaderMy from './components/header/HeaderMy.vue'
  import General from './components/main/Main.vue'

  export default {
    name: 'app',
    data() {
      return {
        msg: 'Welcome to Your Vue.js App',
        msg1: '3',
        msg2: 'Welcome to Your Vue.js App',
        msg3: 'Welcome to Your Vue.js App'
      }
    },
    components: {
      HeaderMy,
      General
    },
  }
</script>

<style lang="scss">
  $sidebar-width: 100px;
  $primary-color: #000423;
  .my-header {
    width: 100%;
    height: 100px;
    border: 3px solid $primary-color;
    position: fixed;
  }

  .wrapper {
    position: relative;
  }

  .left-content-sidebar {
    position: fixed;
    left: 0;
    top: 103px;
    width: $sidebar-width;
  }

  .main-content {
    position: absolute;
    width: 95%;
    top: 103px;
    left: 10px;
    z-index: -1;
  }

</style>
