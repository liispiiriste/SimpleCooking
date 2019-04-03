<template>
    <div class="account">
        <div class="container">
            <h4>Minu konto</h4>

            <!--labelid tglt v-if useri kohta-->
            <div class="labelid">
                <div>
                    <label>Kasutajanimi: </label> {{user.username}}
                </div>

                <div>
                    <label>Email: </label> {{user.email}}
                </div>

                <div>
                    <!--TODO: listi kujundust muuta.-->
                    <label>Minu retseptid: </label> <ul>
                    <li v-for="(recipe, index) in recipes" :key="index">

                        <router-link :to="{
                            name: 'recipe',
                            params: { recipe: recipe, id: recipe.id }
                        }" style="color:#333">
                            {{recipe.name}}
                        </router-link>
                    </li>
                </ul>

                </div>

            </div>
        </div>

    </div>
</template>

<script>
    import axios from "axios";
    import $store from "../store/modules/auth";
    import http from "../http-common";

    export default {
        name: "MyAccount",

        data() {
            return {
                user: {
                    email: '',
                    username: ''
                },
                recipes: {
                    recipes: []
                }
            }
        },
        mounted() {
            axios.get('http://localhost:8080/api/loggedIn').then(response => (this.user = response.data));
            http.get("/userRecipes").then(response=> {this.recipes = response.data; })
        }
    }
</script>

<style scoped>
    h2 {
        margin-right:200px;
    }
    label{
        min-width:300px;
        margin:auto
    }
    .container{
        width:70%;
        margin:auto;
    }
    .labelid{
        text-align: left;
    }
</style>