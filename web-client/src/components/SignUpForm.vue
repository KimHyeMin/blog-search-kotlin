<template>
  <div class="signUpForm">
    <div>
      <p>계정 만들기</p>
      <input type="text" placeholder="성" v-model="signUpForm.firstName"><br>
      <input type="text" placeholder="이름" v-model="signUpForm.secondName"><br>
      <input type="text" placeholder="email" v-model="signUpForm.email"><br>
      <input type="password" placeholder="비밀번호" v-model="signUpForm.password"><br>
      <input type="password" placeholder="확인" v-model="passwordConfirm"><br>
      <b-button @click="signUp">가입하기</b-button>
      <span>또는 <router-link to="/login">로그인</router-link>으로 돌아가기</span>
    </div>
    <router-view></router-view>
  </div>
</template>
<script>
import {SignUpForm} from "@/models"

export default {
  name: 'signUpForm',
  data() {
    return {
      signUpForm: SignUpForm.init(),
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
                let user = data.result.user
                this.message = `${user.name} 님 회원가입을 축하드립니다.`
                this.successful = true
                this.$router.push({name: 'Success', params: {message : this.message, success:this.successful}});
              }, error => {
                this.message = error.message
                this.successful = false
          })
    }
  }
}
</script>
<style lang="scss" scoped>

</style>