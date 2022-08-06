<template>
  <div class="popular mt-50">
    <h4>Most frequent terms!</h4>
    <b-table striped hover :items="terms"></b-table>
  </div>
</template>

<script>
export default {
  name: "Popular",
  data() {
    return {
      terms : [
        {
          "term": "코인",
          "count": 10
        },
        {
          "term": "인생이란",
          "count": 9
        },
        {
          "term": "물가상승",
          "count": 8
        },
        {
          "term": "삼성전자",
          "count": 7
        },
        {
          "term": "한강물온도",
          "count": 6
        }
      ]
    }
  }
}
</script>

<style scoped lang="scss">
.popular{
  text-align: left;
  height: 400px;
}
</style>