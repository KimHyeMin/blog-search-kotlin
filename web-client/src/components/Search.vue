<template>
  <div class="search">
    <b-form class="navbar-search form-inline mr-sm-3 navbar-search-light"  id="navbar-search-main" @submit.prevent="">
      <b-form-group class="mb-0">
        <b-input-group class="input-group-alternative input-group-merge">
          <div class="input-group-prepend">
            <span class="input-group-text bg-white"> <b-icon icon="search"></b-icon></span>
          </div>
          <b-input placeholder="Search" type="text" v-model="searchRequest.keywords" @keyup.enter="search"></b-input>
        </b-input-group>

        <div class="condition-group">

          <div class="pagination" >
            <b-pagination v-if="exist"
                          first-number align="fill"
                          :per-page="searchRequest.size" :total-rows="total" v-model="searchRequest.page"
                          @change="search">
            </b-pagination>
          </div>

          <b-dropdown size="sm" :text="searchRequest.sort" variant="success" class="sorting-dropdown">
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
import { mapState } from "vuex"

export default {
  name: "Search",
  data() {
    return {
      sortingOpt : [
        'ACCURACY', 'RECENCY'
      ]
    }
  },
  computed: {
    ...mapState("$search", ["searchRequest", "meta"]),
    exist(){
      return this.$store.getters.resultExist
    },
    total() {
      return this.meta.pageableCount;
    }
  },
  methods: {
    search() {
      //todo validation input

      if (!this.searchRequest.keywords) {
        //init result, view
        this.$store.commit("$search/init")
      } else {
        this.$store.dispatch("$search/search", this.searchRequest)
      }
      if (this.$router.currentRoute.name !== Dashboard.name) {
        this.$router.push("/")
      }
    },
    changeSortingOption(value) {
      if (this.searchRequest.sort !== value) {
        this.searchRequest.sort = value;
        this.search();
      }
    },
  }
}
</script>

<style scoped>
.input-group-prepend {
  margin-right: -2px;
  display: flex;
}

.condition-group {
  margin-top: 0.5rem!important;
  display: flex;
  width: 100%;
  justify-content: space-between;
}

.sorting-dropdown {
  height: fit-content;
}


</style>