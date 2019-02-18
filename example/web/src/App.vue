<template>
  <div id="app">
<!---    <img alt="Vue logo" src="./assets/logo.png">--->
    <Home v-bind:msg="hello"/>

  </div>
</template>

<script>
import Home from './components/Home.vue'
import axios from "axios";

export default {
  name: 'app',
  components: {
    Home
  },
  data() {
    return {
      hello: null
    }
  },
  mounted () {
    axios.get('http://localhost:8080/hello-world').then(response => (this.hello = response.data));
  }
}

</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
