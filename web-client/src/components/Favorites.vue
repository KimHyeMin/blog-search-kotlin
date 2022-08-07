<template>
  <div class="favorites mt-50">
    <h4>My Favorites</h4>
    <blog-card v-bind:blog="blog">
    </blog-card>

    <blog-card v-bind:blog="blog">
    </blog-card>

  </div>
</template>

<script>
import BlogCard from "@/components/BlogCard";
export default {
  name: "Favorites",
  components: {
    BlogCard
  },
  data() {
    return {
      blog: {
        title: "블로그 글 제목dddddddddddddddddddddddddddddddddddddddddddddddddddddaggg",
        contents: "블로그 글 요약",
        url : "https://brunch.co.kr/@tourism/91",
        thumbnail : "http://search3.kakaocdn.net/argon/130x130_85_c/7r6ygzbvBDc",
        blogName: "블로그의 이름"
      }
    }
  }
}
</script>

<style scoped>

.favorites {
  text-align: left;
}

</style>