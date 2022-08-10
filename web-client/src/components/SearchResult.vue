<template>
  <div class="search-result">
    <div class="result-meta">검색결과 약 {{ count }}개 </div>

    <div class="pagination" >
      <b-pagination v-if="exist"
                    first-number align="fill"
                    :per-page="searchRequest.size" :total-rows="total" v-model="searchRequest.page"
                    @change="search(false)">
      </b-pagination>
    </div>

    <blog-card  v-for="(blog,idx) in blogList" :key="idx"
                v-bind:blog="blog"
                @like="addFavorite(blog)"
    >
    </blog-card>
  </div>
</template>

<script>
import BlogCard from "@/components/BlogCard";
import {mapState} from "vuex";

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
    ...mapState("$search", ["searchRequest", "meta"]),
    count() {
      return this.$store.state.$search.meta.totalCount || 0;
    },
    blogList() {
      return this.$store.state.$search.blogList || [];
    },
    exist(){
      return this.$store.getters.resultExist
    },
    total() {
      return this.meta.pageableCount;
    },
  },
  methods: {
    addFavorite(blog) {
      let userId = this.$store.getters.userId;
      if (!userId) {
        return;
      }
      this.$store.dispatch("$favorite/like", {blog:blog, userId:userId})
      this.$set(blog);
    },
    search(first) {
      this.searchRequest['first'] = first;
      this.$store.dispatch("$search/search", this.searchRequest)
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

.pagination {
  margin-top: 15px;
}
</style>