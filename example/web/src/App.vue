<template xmlns:th="http://www.w3.org/1999/xhtml">
    <div id="app">
        <!-- navbar -->
        <div>
            <b-navbar toggleable="lg" type="dark" variant="info">
                <router-link to="/home">
                    <b-navbar-brand>Kodu</b-navbar-brand>
                </router-link>
                <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
                <b-collapse id="nav-collapse" is-nav>
                    <b-navbar-nav v-if="authenticated">
                        <b-nav-item>
                            <router-link to="/recipes">
                                <b-nav-text>Retseptid</b-nav-text>
                            </router-link>
                        </b-nav-item>
                        <b-nav-item>
                            <router-link to="/add">
                                <b-nav-text>Lisa retsept</b-nav-text>
                            </router-link>
                        </b-nav-item>
                        <b-nav-item>
                            <router-link to="/MyAccount">
                                <b-nav-text>Minu konto</b-nav-text>
                            </router-link>
                        </b-nav-item>
                        <b-nav-item>
                            <router-link to="/search">
                                <b-nav-text>Otsi retsepte</b-nav-text>
                            </router-link>
                        </b-nav-item>
                        <b-nav-item href="#" @click="logout">
                            <b-nav-text>Logi välja</b-nav-text>
                        </b-nav-item>
                    </b-navbar-nav>
                    <b-nav-item v-if="!authenticated">
                        <router-link to="/login">
                            <b-nav-text>Logi sisse</b-nav-text>
                        </router-link>
                    </b-nav-item>
                </b-collapse>
            </b-navbar>
        </div>
        <img class="cupcake" src="@/assets/cupcake2.jpg" alt="cupcake">
        <br>
        <div class="container">
            <transition name="moveInUp">
                <router-view @authenticated="setAuthenticated"/>
            </transition>
        </div>
        <div class="footer"></div>
    </div>
</template>
<script>
    import './stylesheets/main.css';
    import http from "./http-common";
    import {AUTH_LOGOUT} from "./store/constants";
    export default {
      name: 'app',
        data() {
            return {
                authenticated: false,
            }
        },
        mounted() {
            if (!this.authenticated) {
                this.$router.replace({name: "login"});
            }
        },
        methods: {
            setAuthenticated(status) {
                this.authenticated = status;
                console.log(this.authenticated)
            },
            logout() {
                this.$store.dispatch(AUTH_LOGOUT).then(() => {
                    this.$router.push("/login");
                })
                this.authenticated = false;
            },
        }
}
</script>
<style>
</style>