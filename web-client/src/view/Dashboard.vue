<template>
  <div class="dashboard">
    <search-result v-if="searched"></search-result>
    <popular v-if="!searched"></popular>
    <favorites v-if="!searched"></favorites>
  </div>
</template>

<script>
import Favorites from "@/components/Favorites";
import Popular from "@/components/Popular";
import SearchResult from "@/components/SearchResult";

export default {
  name: "Dashboard",
  components: {
    SearchResult,
    Favorites,
    Popular
  },
  data() {
    return {}
  },
  computed: {
    searched(){
      return this.$store.getters.resultExist
    }
  },
  methods: {

  }
}
</script>
<style scoped>

</style>