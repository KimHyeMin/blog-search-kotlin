<template>
  <div class="result">
    {{this.message}}
  </div>
</template>
<script>

export default {
  name: 'result',
  props: [
    "message","success"
  ],
  data() {
    return {

    }
  },
  methods: {

  }
}
</script>
<style lang="scss" scoped>

</style>