<template>
  <div class="popular mt-50">
    <h4>Most frequent terms!</h4>
    <b-table striped hover :items="keywordRanking"></b-table>
  </div>
</template>

<script>
import {mapState} from "vuex";

export default {
  name: "Popular",
  data() {
    return {

    }
  },
  computed: {
    ...mapState("$search", ["keywordRanking"]),
  },
  methods: {
    fetch() {
      this.$store.dispatch("$search/fetchRanking")
    }
  },
  mounted() {
    this.fetch();
  }
}
</script>

<style scoped lang="scss">
.popular{
  text-align: left;
  height: 600px;
}

</style>