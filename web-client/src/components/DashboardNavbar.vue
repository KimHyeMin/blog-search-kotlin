<template>
  <div class="dashboard-navbar mb-4 mt-4">
    <b-navbar toggleable class="navbar-dark">
      <div class="container-fluid">
        <div class="brand">Search Blog</div>
        <div>
          <b-icon icon="person-circle"></b-icon>
          <span class="name">{{userName}}</span>
        </div>
      </div>
    </b-navbar>
  </div>
</template>

<script>
export default {
  name: "DashboardNavbar",
  data() {
    return {

    }
  },
  computed: {
    userName() {
      return this.$store.state.userName;
    }
  }
}
</script>

<style scoped lang="scss">
.name {
  margin-left: 6px;
}

.brand {
  color : blueviolet;
}
</style>