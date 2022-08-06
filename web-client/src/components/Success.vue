<template>
  <div class="result">
    <b-container class="container">

      <b-row class="justify-content-center">
        <b-col xl="7" lg="6" md="8" class="px-5">
            <b-alert class="jumbotron" show :variant="alertType">{{this.message}}</b-alert>
            <span><router-link to="/login">Go to login</router-link> ||&nbsp;
            <router-link to="/signup">Create new account</router-link></span>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>
<script>

export default {
  name: 'Success',
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
.jumbotron {
  height: 200px;
  font-size: 25px;
  padding: 4rem 2rem;
}
</style>