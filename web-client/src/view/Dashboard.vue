<template>
  <div class="dashboard">
    <search></search>
    <Favorites></Favorites>
  </div>
</template>

<script>
import Search from "@/components/Search";
import Favorites from "@/components/Favorites";

export default {
  name: "dashboard",
  components: {
    Search,
    Favorites
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