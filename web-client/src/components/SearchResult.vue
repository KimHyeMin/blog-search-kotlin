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

    <blog-card  ref="resultCard"
                v-for="(blog,idx) in blogList" :key="idx"
                v-bind:blog="blog"
                @like="addFavorite(blog, idx)"
                @unlike="removeFavorite(blog, idx)"
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
    addFavorite(blog, idx) {
      let userId = this.$store.getters.userId;
      if (!userId) {
        return;
      }

      this.$refs.resultCard[idx].buttonDisable = true;

      this.$store.dispatch("$favorite/like", {blog:blog, userId:userId})
      .then(result => {
        if (result.success) {
          blog.favoriteId = result.favoriteId;
          this.$set(blog);
          this.$bvToast.toast('Your favorite is added to your favorite list. Please check favorite page', {
                title: 'Like this blog',
                variant: 'success',
                solid: true,
                appendToast: true
              }
          )
        } else {
          this.$bvToast.toast('adding favorite is failed', {
                title: 'Failed',
                variant: 'danger',
                solid: true,
                appendToast: true
              }
          )
        }
      }, () => console.log("add favorite failed"))
      .finally(() => {
          this.$refs.resultCard[idx].buttonDisable = false;
      })
    },
    removeFavorite(blog, idx) {
      let userId = this.$store.getters.userId;
      if (!userId) {
        return;
      }

      this.$refs.resultCard[idx].buttonDisable = true;

      this.$store.dispatch("$favorite/unlike", {favoriteId: blog.favoriteId, userId:userId})
      .then(result => {
        if (result.success) {
          blog.favoriteId = null;
          this.$set(blog);
          this.$bvToast.toast('This blog removed from your favorite', {
                title: 'Unlike this blog',
                variant: 'warning',
                solid: true,
                appendToast: true
              }
          )
        } else {
          this.$bvToast.toast('removing favorite is failed', {
                title: 'Failed.',
                variant: 'danger',
                solid: true,
                appendToast: true
              }
          )
        }
      }, () => console.log("remove favorite failed"))
      .finally(() => {
        this.$refs.resultCard[idx].buttonDisable = false;
      })

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