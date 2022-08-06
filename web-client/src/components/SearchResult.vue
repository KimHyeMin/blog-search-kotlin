<template>
  <div class="search-result">
    <div class="result-meta">검색결과 약 551,000개 (0.42초) </div>
    <blog-card  title="블로그 글 제목dddddddddddddddddddddddddddddddddddddddddddddddddddddaggg"
                sub-title="블로그 글 요약"
                link="https://brunch.co.kr/@tourism/91"
                thumbnail="http://search3.kakaocdn.net/argon/130x130_85_c/7r6ygzbvBDc"
                footer="블로그의 이름">
    </blog-card>
  </div>
</template>

<script>
import BlogCard from "@/components/BlogCard";

export default {
  name: "SearchResult",
  components: {
    BlogCard
  }
}
</script>

<style scoped>

.search-result {
  text-align: left;
}

.result-meta {
  margin-top: 15px;
}
</style>