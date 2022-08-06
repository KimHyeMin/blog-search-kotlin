<template>
  <div class="search">
    <b-form class="navbar-search form-inline mr-sm-3 navbar-search-light"  id="navbar-search-main">
      <b-form-group class="mb-0">
        <b-input-group class="input-group-alternative input-group-merge">
          <div class="input-group-prepend">
            <span class="input-group-text bg-white"> <b-icon icon="search"></b-icon></span>
          </div>
          <b-form-input placeholder="Search" type="text"></b-form-input>
        </b-input-group>
      </b-form-group>
    </b-form>
  </div>
</template>

<script>
export default {
  name: "Search"
}
</script>

<style scoped>
.input-group-prepend {
  margin-right: -2px;
  display: flex;
}


</style>