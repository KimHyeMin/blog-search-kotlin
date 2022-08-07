<template>
  <div class="dashboard-navbar mb-4 mt-4">
    <b-navbar>

      <b-navbar-brand class="brand" @click="routeToDashboard">Search Blog</b-navbar-brand>

      <b-navbar-nav class="ml-auto menu" >
        <b-nav-item-dropdown right>
          <template #button-content>
            <em>{{ userName }}</em>
          </template>
          <b-dropdown-item @click="signOut">Sign Out</b-dropdown-item>
        </b-nav-item-dropdown>

        <b-button size="sm" pill variant="outline-danger" @click="routeToFavorite"><b-icon icon="heart-fill"></b-icon></b-button>
        <b-button size="sm" pill variant="outline-warning" @click="routeToPopular"><b-icon icon="graph-up"></b-icon></b-button>

      </b-navbar-nav>
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
    },
    signOut() {
      this.$store.dispatch("$auth/logout");
      this.$router.push("/login")
    }
  }
}
</script>

<style scoped lang="scss">
.menu > * {
  margin-right: 10px;
}

.ml-auto {
  margin-left: auto!important;
}

.brand {
  color : blueviolet;
}
</style>