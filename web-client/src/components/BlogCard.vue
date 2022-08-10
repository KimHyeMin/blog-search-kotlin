<template>
  <div class="blog-card mt-4 mb-4">
    <b-card
        no-body
        class="card-lift--hover shadow">
      <b-card-header class="bg-white">
        <div class="head-wrap">
          <b-img thumbnail fluid :src="blog.thumbnail"  v-bind="mainProps" :blank=!blog.thumbnail></b-img>
          <h5 class="card-title text-uppercase text-muted mb-0 pt-2" v-if="blog.title">
            <b-link target="_blank" class="title" :href="blog.url"><p v-html="blog.title"></p></b-link>
          </h5>
        </div>
      </b-card-header>
      <b-card-body>
        <b-row>
          <b-col>
              <div class="mb-3 mt-3 sub-title" v-if="blog.contents">
                <span v-html="blog.contents"></span>
              </div>
          </b-col>

          <b-col cols="auto">
            <slot name="icon">
              <div class="icon icon-shape text-white rounded-circle shadow">
                <i class="icon"></i>
              </div>
            </slot>
          </b-col>
        </b-row>
        <b-row>
          <b-col>
            <span class="time">WrittenAt at {{blog.datetime}}</span>
          </b-col>
        </b-row>
      </b-card-body>

      <b-card-footer class="bg-white">
        <div class="footer">
          <span class="text-success mr-2">{{blog.blogName}}</span>
          <div class="icon-wrap h5"><b-icon :icon="getIcon" @click="clickLike(blog)"></b-icon></div>
        </div>
      </b-card-footer>
    </b-card>
  </div>
</template>

<script>
export default {
  name: "BlogCard",
  props: {
    blog: Object
  },
  data() {
    return {
      mainProps: { blankColor: '#777', width: 75, height: 75, class: 'm1 mr-10' },
    }
  },
  computed: {
    getIcon() {
      return this.blog.like ? 'heart-fill' : 'heart'
    }
  },
  methods: {
    clickLike(blog) {
      let toast = {}
      if (blog.like) {
        blog.like = false;
        this.$emit('unlike', blog)
        toast.variant = "danger"
        toast.type = "removed"
        toast.message = "Your favorite is removed from your favorite list."
      } else {
        blog.like = true;
        this.$emit('like', blog)
        toast.variant = "success"
        toast.type = "added"
        toast.message = "Your favorite is added to your favorite list. Please check favorite page"
      }

      this.$bvToast.toast(toast.message, {
            title: `My Favorite blog is ${toast.type}`,
            variant: toast.variant,
            solid: true,
            appendToast: true
          }
      )
    }
  }
}
</script>

<style scoped>
.title{
  color: #8898aa;
  text-decoration: none;
}

.pt-2 {
  padding-top: 2px;
}

.head-wrap{
  display: flex;
}

.head-wrap h5 {
  overflow: hidden;
}
.mr-10 {
  margin-right: 10px;
}
.footer span {
  text-align: right;
}
.icon-wrap {
  float: right;
}

.icon-wrap svg {
  vertical-align: middle;
}

.card-footer {
  border-top: 0px;
}

</style>