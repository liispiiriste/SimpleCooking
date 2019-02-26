<template>
    <div id="app" style="width:80%; margin:auto; margin-top:50px">
        <!-- <form action="http://localhost:9000/#/MyAccount">
            <input type="text" placeholder="Sisesta e-mail" name="email" required>
            <input type="password" placeholder="Sisesta parool" name="password" required>
            <button type="submit" class="logInBtn">Logi sisse</button>
        </form>-->
        <!-- navbar -->
        <div class="navbar navbar-expand-lg fixed-top navbar-dark bg-primary">
            <div class="container">
                <router-link to="/" class="navbar-brand">Kodu</router-link>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor02" aria-controls="navbarColor02" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
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
        </div><br>
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
/*ul {
    list-style-type: none;
}
li{
    width:100px;
    float:left;
}
    form {
        float:right;
    }*/
.moveInUp-enter-active{
    animation: fadeIn 2s ease-in;
}
@keyframes fadeIn{
    0%{
        opacity: 0;
    }
    50%{
        opacity: 0.5;
    }
    100%{
        opacity: 1;
    }
}
.moveInUp-leave-active{
    animation: moveInUp .3s ease-in;
}
@keyframes moveInUp{
    0%{
        transform: translateY(0);
    }
    100%{
        transform: translateY(-400px);
    }
}
</style>
