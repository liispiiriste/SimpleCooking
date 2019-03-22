<template xmlns:th="http://www.w3.org/1999/xhtml">
    <div id="app" style="width:80%; margin:auto; margin-top:50px;">
        <!-- navbar -->
        <div class="navbar navbar-expand-lg fixed-top navbar-dark bg-dark">
            <div class="container">
                <router-link to="/home" class="navbar-brand">Kodu</router-link>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor02" aria-controls="navbarColor02" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarColor02">
                    <ul class="navbar-nav mr-auto">

                        <li class="nav-item">
                            <router-link class="nav-link" to="/recipes">Retseptid</router-link>
                        </li>
                        <li class="nav-item">
                            <router-link class="nav-link" to="/add">Lisa retsept</router-link>
                        </li>
                        <li class="nav-item">
                            <router-link class="nav-link" to="/MyAccount">Minu konto</router-link>
                        </li>
                        <li class="nav-item">
                            <router-link class="nav-link" to="/login">Logi sisse</router-link>
                        </li>
                        <li class="nav-item">
                            <router-link class="nav-link" to="/register">Registreeri</router-link>
                        </li>
                    </ul>
                    <form class="form-inline my-2 my-lg-0">
                        <input class="form-control mr-sm-2" type="text" placeholder="Otsing">
                        <button class="btn btn-secondary my-2 my-sm-0" type="submit">Otsi</button>
                    </form>
                    <form class="form-inline my-2 my-lg-0" th:action="@{/logout}" method="post" style="margin-left:10px;">
                        <input class="form-control mr-sm-2" type="submit" value="Sign Out"/>
                    </form>
                </div>
            </div>
        </div>
        <br>
        <div class="container">
            <transition name="moveInUp">
                <router-view/>
            </transition>
        </div>
        <div class="footer" style="margin-bottom:20px;"></div>
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
    color: #333;
    margin-top: 60px;
}
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
    .nav-item{
        text-align:left;
    }
</style>