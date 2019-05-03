<template xmlns:th="http://www.w3.org/1999/xhtml">
    <div id="app" style="margin:auto;">

        <!-- navbar -->
            <div>
                <b-navbar toggleable="lg" type="dark" variant="secondary">
                    <router-link to="/home"><b-navbar-brand>Kodu</b-navbar-brand></router-link>

                    <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

                    <b-collapse id="nav-collapse" is-nav>
                        <b-navbar-nav v-if="authenticated">
                            <b-nav-item><router-link to="/recipes"><b-nav-text >Retseptid</b-nav-text></router-link></b-nav-item>
                            <b-nav-item><router-link to="/add"><b-nav-text>Lisa Retsept</b-nav-text></router-link></b-nav-item>
                            <b-nav-item> <router-link to="/MyAccount"><b-nav-text>Kasutaja</b-nav-text></router-link></b-nav-item>

                        </b-navbar-nav>

                        <b-nav-item v-if="!authenticated">
                            <router-link to="/login">
                                <b-nav-text>Logi sisse</b-nav-text>
                            </router-link>
                        </b-nav-item>



                        <!-- Right aligned nav items -->
                        <b-navbar-nav class="ml-auto">
                            <b-nav-form id="search" v-if="authenticated">

                                <b-form-input size="sm" class="mr-sm-2" placeholder="Otsi retsepte..." v-model="searchStr" type="search" />

                                <b-button size="sm" class="my-2 my-sm-0" v-on:click="searchRecipe('searchStr')">
                                    Otsi
                                </b-button>
                            </b-nav-form>

                            <div
                                :filter-key="searchStr">

                            </div>

                        </b-navbar-nav>
                    </b-collapse>
                </b-navbar>
            </div>
        <br>
        <div class="container" style="margin-top:45px;">
            <transition name="moveInUp">
                <router-view @authenticated="setAuthenticated" />
            </transition>
        </div>
        <div class="footer" style="margin-bottom:20px;"></div>
    </div>
</template>
<script>

    import './stylesheets/main.css';
    import http from "./http-common";
    import axios from "axios";

    export default {

      name: 'app',

        data() {


            return {
                authenticated: false,
                searchStr: ''

            }
        },
        mounted() {
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
            },
            searchRecipe(){
                http.get('http://localhost:8080/api/recipes/search/' + this.searchStr)
                    .then(response => {
                        this.recipes = response.data;
                    })

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

.search-wrapper {
    position: relative;
}

.wrapper {
    display: flex;
    max-width: 444px;
    flex-wrap: wrap;
    padding-top: 12px;
}




</style>