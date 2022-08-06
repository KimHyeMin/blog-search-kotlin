<template>
  <div id="app" class="main-content bg-default">

    <router-view></router-view>
  </div>
</template>

<script>

export default {
  name: 'App',
  components: {

  }
}
</script>

<style>

#app {
  min-height: 100vh;
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

.mr-8 {
  margin-right: 8px;
}

.mt-50 {
  margin-top: 50px;
}

.font-italic {
  font-style: italic!important;
}
.text-muted {
  color: #8898aa!important;
}

.text-left {
  text-align: left;
}


</style>
