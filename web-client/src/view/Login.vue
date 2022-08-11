<template>
  <div class="login">
    <!-- Header -->
    <div class="header bg-gradient-success py-7 py-lg-8 pt-lg-9">
      <b-container class="container">
        <div class="header-body text-center mb-7">
          <b-row class="justify-content-center">
            <b-col xl="5" lg="6" md="8" class="px-5">
              <h1>Welcome!</h1>
            </b-col>
          </b-row>
        </div>
      </b-container>
    </div>

    <!-- Page content -->
    <b-container class="mt--8 pb-5">
      <!-- Table -->
      <b-row class="justify-content-center">
        <b-col lg="6" md="8" >
          <b-card no-body class="bg-secondary border-0">
            <b-card-body class="px-lg-5 py-lg-5">

                <base-input alternative
                            class="mb-3"
                            placeholder="Email"
                            v-model="email">
                </base-input>

                <base-input alternative
                            class="mb-3"
                            placeholder="password"
                            type="password"
                            v-model="password">
                </base-input>

                <div class="error" v-if="errorMessage">{{errorMessage}}</div>
                <div class="text-center">
                  <b-button type="submit" variant="primary" class="mt-4 mb-3" @click="signIn">Sign in</b-button>
                </div>
                <span>or <router-link to="/signUp">Create new account</router-link></span>

            </b-card-body>
          </b-card>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import BaseInput from "@/components/common/BaseInput";
import { mapState } from "vuex"

export default {
  name: 'Login',
  components: {
    BaseInput
  },
  data() {
    return {
      email: '',
      password: ''
    }
  },
  computed: {
    ...mapState("$auth", ["errorMessage"])
  },
  methods: {
    signIn() {
      //todo validation input
      this.$store.dispatch("$auth/login", {email: this.email, password: this.password})
          .then(() => {
            this.$router.push('/');
          },
          ()=> {
            console.log("login failed");
          });
    }
  }
}
</script>

<style lang="scss" scoped>
.bg-secondary {
  background-color: #f7fafc!important;
}

.login {
  margin-top: 60px;
}
</style>