<template>
  <div class="search">
    <b-form class="navbar-search form-inline mr-sm-3 navbar-search-light"  id="navbar-search-main" @submit.prevent="">
      <b-form-group class="mb-0">
        <b-input-group class="input-group-alternative input-group-merge">
          <div class="input-group-prepend">
            <span class="input-group-text bg-white"> <b-icon icon="search"></b-icon></span>
          </div>
          <b-input placeholder="Search" type="text" v-model="keyword" @keyup.enter="search"></b-input>
        </b-input-group>
        <div class="condition-group">
          <b-dropdown size="sm" :text="sort" class="m-2" variant="success">
            <b-dropdown-item @click="changeSortingOption('ACCURACY')">ACCURACY</b-dropdown-item>
            <b-dropdown-item @click="changeSortingOption('RECENCY')">RECENCY</b-dropdown-item>
          </b-dropdown>
        </div>
      </b-form-group>
    </b-form>
  </div>
</template>

<script>
import Dashboard from "@/view/Dashboard";
export default {
  name: "Search",
  data() {
    return {
      keyword : "",
      sort: "ACCURACY",
      page: 1,
      size: 30,
    }
  },
  methods: {
    search() {
      //todo validation
      let param = {"keywords" : this.keyword, "sort": this.sort, page: this.page, size:this.size }
      this.$store.dispatch("$search/search", param)
           .then(() => {
              if (this.$router.currentRoute.name !== Dashboard.name) {
                this.$router.push("/")
              }
           })
    },
    changeSortingOption(value) {
      if (this.sort !== value) {
        this.sort = value;
        this.search();
      }
    }
  }
}
</script>

<style scoped>
.input-group-prepend {
  margin-right: -2px;
  display: flex;
}

.condition-group {
  text-align: right;
  display: block;
}


</style>