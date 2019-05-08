<template>
  <div class="home">

<h2>{{ msg }}</h2>
    <img src="@/assets/logo.png" alt="">
  </div>
</template>

<script>
export default {
  name: 'Home',
    data () {
        return {
            msg: 'Tere tulemast meie kodulehele',

        }
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>