<template>
    <div id="app" style="width:80%; margin:auto; margin-top:50px">
        <h1>SimpleCooking</h1>
        <!-- navbar -->
        <nav class="navbar navbar-expand-lg fixed-top navbar-dark bg-primary">
            <div class="container">
                <router-link to="/" class="navbar-brand" style="float:left">Kodu</router-link>

                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav">

                        <li class="nav-item">
                            <router-link class="nav-link" to="/Recipes">Retseptid</router-link>
                        </li>
                        <li class="nav-item">
                            <router-link class="nav-link" to="/AddRecipe">Lisa retsept</router-link>
                        </li>
                        <li class="nav-item">
                            <router-link class="nav-link" to="/MyAccount">Minu konto</router-link>
                        </li>
                    </ul>
                </div>
            </div>
        </nav><br>
        <div class="container">
            <transition name="moveInUp">
                <router-view/>
            </transition>

        </div>
    </div>
</template>


<script>
import axios from "axios";
import Home from "./components/Home.vue"
export default {
  name: 'App',
    component: {
      Home
    },

  mounted () {
    axios.get('http://localhost:8080/hello-world').then(response => (this.home = response.data));
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
ul {
    list-style-type: none;
}
li{
    width:100px;
    float:left;
}
</style>
