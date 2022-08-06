<template>
  <div class="favorites mt-4">
    <h4>My Favorites</h4>
    <Favorite  title="블로그 글 제목dddddddddddddddddddddddddddddddddddddddddddddddddddddaggg"
               sub-title="블로그 글 요약"
               link="https://brunch.co.kr/@tourism/91"
               thumbnail="http://search3.kakaocdn.net/argon/130x130_85_c/7r6ygzbvBDc"
               footer="블로그의 이름">
    </Favorite>

    <Favorite  title="블로그 글 제목dddddddddddddddddddddddddddddddddddddddddddddddddddddaggg"
               sub-title="블로그 글 요약"
               link="https://brunch.co.kr/@tourism/91"
               thumbnail="http://search3.kakaocdn.net/argon/130x130_85_c/7r6ygzbvBDc"
               footer="블로그의 이름">
    </Favorite>

  </div>
</template>

<script>
import Favorite from "@/components/Favorite";
export default {
  name: "Favorites",
  components: {Favorite}
}
</script>

<style scoped>

.favorites {
  text-align: left;
}

</style>