<template>
  <div class="search-result">
    <div class="result-meta">검색결과 약 {{ count }}개 (0.42초) </div>
    <blog-card  v-for="(blog,idx) in blogList" :key="idx"
                :title="blog.title"
                :sub-title="blog.contents"
                :link="blog.url"
                :thumbnail="blog.thumbnail"
                :footer="blog.blogName">
    </blog-card>
  </div>
</template>

<script>
import BlogCard from "@/components/BlogCard";

export default {
  name: "SearchResult",
  components: {
    BlogCard
  },
  data() {
    return {

    }
  },
  computed: {
    count() {
      return this.$store.state.$search.meta.totalCount || 0;
    },
    blogList() {
      return this.$store.state.$search.blogList || [];
    }
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