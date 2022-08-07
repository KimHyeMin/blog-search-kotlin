<template>
  <div class="blog-card mt-4 mb-4">
    <b-card
        no-body
        class="card-lift--hover shadow">
      <b-card-header class="bg-white">
        <div class="head-wrap">
          <b-img thumbnail fluid :src="blog.thumbnail"  v-bind="mainProps" :blank=!blog.thumbnail></b-img>
          <h5 class="card-title text-uppercase text-muted mb-0 pt-2" v-if="blog.title">
            <b-link target="_blank" class="title" :href="blog.url">{{blog.title}}</b-link>
          </h5>
        </div>
      </b-card-header>
      <b-card-body>
        <b-row>
          <b-col>
              <div class="mb-3 mt-3 sub-title" v-if="blog.contents">
                <span>{{blog.contents}}</span>
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
            <span class="time">created at {{blog.datetime}}</span>
          </b-col>
        </b-row>
      </b-card-body>

      <b-card-footer class="bg-white">
        <div class="footer">
          <span class="text-success mr-2">{{blog.blogName}}</span>
          <div class="icon-wrap h5"><b-icon :icon="icon" @click="clickLike"></b-icon></div>
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
      like : true,
      icon : "heart-fill"
    }
  },
  methods: {
    clickLike() {
      //todo implements

      if (this.like) {
        this.like = false;
        this.icon = "heart";
      } else {
        this.like = true;
        this.icon = "heart-fill";
      }
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