<template>
  <div class="dashboard-navbar mb-4 mt-4">
    <b-navbar toggleable class="navbar-dark">
      <div class="container-fluid">
        <div class="brand" @click="routeToDashboard">Search Blog</div>
        <div class="menu">
          <span class="name">{{userName}}</span>
          <b-button size="sm" pill variant="outline-danger" @click="routeToFavorite"><b-icon icon="heart-fill"></b-icon></b-button>
          <b-button size="sm" pill variant="outline-warning" @click="routeToPopular"><b-icon icon="graph-up"></b-icon></b-button>
        </div>
      </div>
    </b-navbar>
  </div>
</template>

<script>
import Dashboard from "@/view/Dashboard";

export default {
  name: "DashboardNavbar",
  data() {
    return {

    }
  },
  computed: {
    userName() {
      return this.$store.getters.userName;
    }
  },
  methods: {
    routeToFavorite() {
      this.$router.push("favorite")
    },
    routeToDashboard() {
      if (this.$router.currentRoute.name !== Dashboard.name) {
        this.$router.push("/")
      }
    },
    routeToPopular() {
      this.$router.push("popular")
    }
  }
}
</script>

<style scoped lang="scss">
.menu > * {
  margin-right: 10px;
}

.brand {
  color : blueviolet;
}
</style>