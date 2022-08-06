<template>
  <div class="dashboard-layout">
    <dashboardNavbar></dashboardNavbar>
    <search></search>
    <router-view></router-view>
  </div>
</template>

<script>
import DashboardNavbar from "@/components/DashboardNavbar";
import Search from "@/components/Search";
export default {
  name: "DashboardLayout",
  components: {
    DashboardNavbar,
    Search
  },
  data() {
    return {}
  },
  computed: {
  },
  methods: {

  }
}
</script>
<style scoped>
.dashboard-layout {
  width: 80%;
  margin: 0 auto;
  min-width: 600px;
}
</style>