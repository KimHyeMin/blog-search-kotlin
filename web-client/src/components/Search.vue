<template>
  <div class="search">
    <b-input-group class="input-group">
      <div class="input-group-prepend">
        <span class="input-group-text bg-white"> <b-icon icon="search"></b-icon></span>
      </div>
      <b-input placeholder="Search" type="text" v-model="searchRequest.keywords" @keypress.enter="search"></b-input>
    </b-input-group>

    <div class="condition-group">
      <b-dropdown size="sm" :text="searchRequest.sort" variant="success" class="sorting-dropdown">
        <b-dropdown-item @click="changeSortingOption('ACCURACY')">ACCURACY</b-dropdown-item>
        <b-dropdown-item @click="changeSortingOption('RECENCY')">RECENCY</b-dropdown-item>
      </b-dropdown>
    </div>

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
      ],
      searchCount : 0
    }
  },
  computed: {
    ...mapState("$search", ["searchRequest"]),
  },
  methods: {
    search(event) {
      //todo validation input

      if (event.isComposing || event.keyCode === 229) {
        return;
      }

      if (!this.searchRequest.keywords) {
        //init result, view
        this.$store.commit("$search/init")
      } else {
        this.$store.dispatch("$search/search", this.searchRequest)
        this.searchCount ++
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
  text-align: right;
  width: 100%;
}

.sorting-dropdown {
  height: fit-content;
}


</style>