<template>
  <div class="dashboard">
    <DashboardNavbar></DashboardNavbar>
    <search></search>
    <popular></popular>
    <Favorites></Favorites>
  </div>
</template>

<script>
import Search from "@/components/Search";
import Favorites from "@/components/Favorites";
import Popular from "@/components/Popular";
import DashboardNavbar from "@/components/DashboardNavbar";

export default {
  name: "dashboard",
  components: {
    DashboardNavbar,
    Search,
    Favorites,
    Popular
  },
  data() {
    return {}
  },
  computed() {

  },
  methods: {

  }
}
</script>
<style scoped>
  .dashboard {
    width: 80%;
    margin: 0 auto;
    min-width: 600px;
  }
</style>