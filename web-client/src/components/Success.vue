<template>
  <div class="result">
    <b-alert show :variant="alertType">
        {{this.message}}
    </b-alert>
    <span><router-link to="/login">로그인</router-link>하러 가기 ||</span>
    <span>&nbsp;<router-link to="/signup">회원가입</router-link>으로 돌아가기</span>
  </div>
</template>
<script>

export default {
  name: 'success',
  data() {
    return {
      message: "",
      success: "primary"
    }
  },
  computed: {
    alertType() {
      return this.success ? 'primary' : 'danger'
    }
  },
  methods: {

  },
  created() {
    this.message = this.$route.params.message;
    this.success = this.$route.params.success;
    if (!this.success) {
      this.$router.push("/signup")
    }
  },
}
</script>
<style lang="scss" scoped>

</style>