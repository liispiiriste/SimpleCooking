<template xmlns:th="http://www.w3.org/1999/xhtml">
    <div id="app" style="margin:auto;">
        <div class="wrapper">
        <!-- navbar -->
        <div class="navbar navbar-expand-lg fixed-top navbar-dark bg-dark">
            <div class="container">
                <router-link to="/home" class="navbar-brand">Kodu</router-link>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor02" aria-controls="navbarColor02" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarColor02">
                    <ul v-if="authenticated" class="navbar-nav mr-auto">
                        <li  class="nav-item">
                            <router-link  class="nav-link" to="/recipes">Retseptid</router-link>
                        </li>
                        <li class="nav-item">
                            <router-link class="nav-link" to="/add">Lisa retsept</router-link>
                        </li>
                        <li class="nav-item">
                            <router-link class="nav-link" to="/MyAccount">Minu konto</router-link>
                        </li>
                       <!--- <li class="nav-item">
                            <router-link class="nav-link" to="/login">Logi sisse</router-link>
                        </li>
                        <li class="nav-item">
                            <router-link class="nav-link" to="/register">Registreeri</router-link>
                        </li>-->
                    </ul>
                    <form v-if="authenticated" class="form-inline my-2 my-lg-0">
                        <input class="form-control mr-sm-2" type="text" placeholder="Otsing">
                        <button class="btn btn-secondary my-2 my-sm-0" type="submit">Otsi</button>
                    </form>
                    <router-link class="nav-link" v-if="authenticated" to="/login" v-on:click.native="logout()" replace>Logout</router-link>
                </div>

            </div>
        </div>
        <br>
        <div class="container" style="margin-top:45px;">
            <transition name="moveInUp">
                <router-view @authenticated="setAuthenticated" />
            </transition>
        </div>
        <div class="footer" style="margin-bottom:20px;"></div>
    </div></div>
</template>
<script>
    import './stylesheets/main.css';
export default {
  name: 'app',
    data() {
        return {
            authenticated: false,

        }
    },mounted() {
        if(!this.authenticated) {
            this.$router.replace({ name: "login" });
        }
    },
    methods: {
        setAuthenticated(status) {
            this.authenticated = status;
        },
        logout() {
            this.authenticated = false;
        }
    }
}
</script>
<style>


.moveInUp-enter-active{
    animation: fadeIn 2s ease-in;
}
.moveInUp-leave-active{
    animation: moveInUp .3s ease-in;
}
</style>