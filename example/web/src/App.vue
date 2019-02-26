<template>
    <div id="app" style="width:80%; margin:auto; margin-top:50px">
        <h1>SimpleCooking</h1>
        <!-- <form action="http://localhost:9000/#/MyAccount">
            <input type="text" placeholder="Sisesta e-mail" name="email" required>
            <input type="password" placeholder="Sisesta parool" name="password" required>
            <button type="submit" class="logInBtn">Logi sisse</button>
        </form>-->
        <!-- navbar -->
        <nav class="navbar navbar-expand-lg fixed-top navbar-dark bg-primary">
            <div class="container">
                <router-link to="/" class="navbar-brand" style="float:left">Kodu</router-link>

                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav">

                        <li class="nav-item">
                            <router-link class="nav-link" to="/recipes">Retseptid</router-link>
                        </li>
                        <li class="nav-item">
                            <router-link class="nav-link" to="/add">Lisa retsept</router-link>
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
export default {
  name: 'app',
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
    form {
        float:right;
    }
</style>
