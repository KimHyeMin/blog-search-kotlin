<template>
  <div class="favorites mt-50">
    <h4>My Favorites</h4>
    <blog-card v-for="(blog, index) in myList" :key="index"
               v-bind:blog="blog"
               @unlike="removeFavorite"
    >
    </blog-card>
    <button v-if="lastFavoriteId" type="button" class="btn btn-primary btn-lg btn-block" @click="fetchMore">Fetch more</button>
  </div>
</template>

<script>
import BlogCard from "@/components/BlogCard";
import { mapState } from "vuex"

export default {
  name: "Favorites",
  components: {
    BlogCard
  },
  data() {
    return {

    }
  },
  computed: {
    ...mapState("$favorite", ["myList", "lastFavoriteId"])
  },
  methods: {
    removeFavorite(blog) {
      let userId = this.$store.getters.userId;
      let blogId = blog.favoriteId;
      if (!userId || !blogId) {
        return;
      }
      this.$store.dispatch("$favorite/unlike", {favoriteId:blogId, userId:userId})
      .then(success => {
        if (success) {
          this.$set(blog);
          this.$bvToast.toast('Your favorite is removed from your favorite list. Please check favorite page', {
                title: 'My Favorite blog is removed',
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
      })
    },
    fetchMore() {
      let userId = this.$store.getters["userId"];
      if (!userId) {
        return;
      }
      let param = {userId : userId};
      this.$store.dispatch("$favorite/fetchMore", param);
    },
    fetchMyList() {
      let userId = this.$store.getters["userId"];
      if (!userId) {
        return;
      }
      let param = {userId : userId};
      this.$store.dispatch("$favorite/fetchMyList", param)
    }
  },
  mounted() {
    this.fetchMyList();
  }
}
</script>

<style scoped>

.favorites {
  text-align: left;
}

</style>