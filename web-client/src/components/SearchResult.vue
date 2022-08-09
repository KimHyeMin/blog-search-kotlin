<template>
  <div class="search-result">
    <div class="result-meta">검색결과 약 {{ count }}개 </div>
    <blog-card  v-for="(blog,idx) in blogList" :key="idx"
                v-bind:blog="blog"
                @like="addFavorite(blog)"
    >
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
  },
  methods: {
    addFavorite(blog) {
      let userId = this.$store.getters.userId;
      if (!userId) {
        return;
      }
      this.$store.dispatch("$favorite/like", {blog:blog, userId:userId})
      this.$set(blog);
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