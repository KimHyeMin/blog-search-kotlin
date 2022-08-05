<template>
  <div class="signUp">
    <div v-if="!message">
      <p>계정 만들기</p>
      <input type="text" placeholder="성" v-model="signUpForm.firstName"><br>
      <input type="text" placeholder="이름" v-model="signUpForm.secondName"><br>
      <input type="text" placeholder="email" v-model="signUpForm.email"><br>
      <input type="password" placeholder="비밀번호" v-model="signUpForm.password"><br>
      <input type="password" placeholder="확인" v-model="passwordConfirm"><br>
      <b-button @click="signUp">가입하기</b-button>
      <span>또는 <router-link to="/login">로그인</router-link>으로 돌아가기</span>
    </div>
    <div>
      <result v-if="message" :message="message" :success="successful"></result>
    </div>
  </div>
</template>
<script>
import {SignUpForm} from "@/models"
import result from "@/components/Result"
export default {
  name: 'signUp',
  components: {result},
  data() {
    return {
      signUpForm: new SignUpForm('','','',''),
      passwordConfirm: '',
      successful: false,
      message: null
    }
  },
  methods: {
    signUp() {
      //todo validation input

      this.$store.dispatch('$auth/register', this.signUpForm)
          .then(data => {
                this.message = data.result.message
                this.successful = true
              }, error => {
                this.message = error.message
                this.successful = false
              })
          .then(() => {
                // this.$router.push('/')
          })
    }
  }
}
</script>
<style lang="scss" scoped>

</style>