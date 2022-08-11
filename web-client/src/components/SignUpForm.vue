<template>
  <div class="signUpForm">
    <!-- Header -->
    <div class="header bg-gradient-success py-7 py-lg-8 pt-lg-9">
      <b-container class="container">
        <div class="header-body text-center mb-7">
          <b-row class="justify-content-center">
            <b-col xl="5" lg="6" md="8" class="px-5">
              <h1>Create an account</h1>
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

                  <div class="name-group">
                    <base-input alternative
                                class="mb-3 mr-8"
                                placeholder="firstName"
                                style="width:100%"
                                v-model="signUpForm.firstName">
                    </base-input>
                    <base-input alternative
                                class="mb-3"
                                placeholder="lastName"
                                style="width:100%"
                                v-model="signUpForm.lastName">
                    </base-input>
                  </div>
                  <base-input alternative
                              class="mb-3"
                              placeholder="Email"
                              v-model="signUpForm.email">
                  </base-input>

                  <base-input alternative
                              class="mb-3"
                              placeholder="password"
                              type="password"
                              v-model="signUpForm.password">
                  </base-input>
                  <div class="text-muted font-italic text-left" v-if="checkPassword">
                    <small>password strength: <span class="text-success font-weight-700">strong</span></small>
                  </div>
                  <div class="error" v-if="message">{{message}}</div>
                  <div class="text-center">
                    <b-button type="submit" variant="primary" class="mt-4 mb-3" @click="signUp">Create account</b-button>
                  </div>
                  <span>or back to <router-link to="/login">login</router-link></span>

            </b-card-body>
          </b-card>
        </b-col>
      </b-row>
    </b-container>

  </div>
</template>
<script>
import {SignUpForm} from "@/models"
import BaseInput from "@/components/common/BaseInput";

export default {
  name: 'SignUpForm',
  components: {
    BaseInput
  },
  data() {
    return {
      signUpForm: SignUpForm.init(),
      successful: false,
      message: null
    }
  },
  computed: {
    checkPassword() {
      //todo implement
      return this.signUpForm.password.length > 0
    }
  },
  methods: {
    signUp() {
      //todo validation input

      this.$store.dispatch('$auth/register', this.signUpForm)
          .then(data => {
                let user = data.result.user
                this.message = `${user.name} 님 회원가입을 축하드립니다.`
                this.successful = true
                this.$router.push({name: 'Success', params: {message : this.message, success:this.successful}});
              }, error => {
                this.message = error.errorMessage
                this.successful = false
          })
    }
  }
}
</script>
<style lang="scss" scoped>
  .bg-secondary {
    background-color: #f7fafc!important;
  }
  .name-group{
    display: flex;
    justify-content: center;
  }

  .text-success {
    color: #2dce89!important;
  }

  .font-weight-700 {
    font-weight: 700!important;
  }
</style>