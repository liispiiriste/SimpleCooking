<template>

    <div class="recipes">
        <div class="sidemenu">
            <b-button-group vertical>
                <b-button v-on:click="chosenCategory('all')" active>
                    Kõik<input type="radio" name="options" id="all" autocomplete="off" checked></b-button>
                <b-button v-for="(category, index) in categories" :key="index" v-on:click="chosenCategory(category.name)">

                {{category.name}}<input type="radio" name="options" :id=category.name autocomplete="off" checked></b-button>
            </b-button-group>

        </div>
        <div class="container">
            <div style="margin:15px; padding:10px; background:rgba(255, 255, 255, 0.2); border:none; border-radius:15px;">
                <h4>Retseptid</h4>
                        <ul>
                            <li v-for="(recipe, index) in recipes" :key="index"
                                style="text-align: center">
                                <router-link :to="{
                            name: 'recipe',
                            params: { recipe: recipe, id: recipe.id }
                        }" style="color:#333">
                                    {{recipe.name}}
                                </router-link>
                            </li>
                        </ul>


                    <div>
                        <router-view @refreshData="refreshList"></router-view>
                    </div>

            </div>
        </div>
    </div>
</template>

<script>
    import http from "../http-common";
    import axios from "axios";

    export default {
        name: "Recipes",
        data() {
            return {
                recipes: [],
                categories:[]

            }
        },

        methods: {
            retrieveRecipes() {
                http.get("/recipes").then(response => {
                    this.recipes = response.data;
                });

            },

            retrieveCategories(){
                http.get("/categories").then(response => {
                    this.categories = response.data;
                });
            },
            refreshList() {
                this.retrieveRecipes();
            },


            chosenCategory(category) {
                if (category !== "all") {
                    http.get("/recipes/" + category).then(response => {
                        this.recipes = response.data;

                    })
                } else {
                    http.get("/recipes").then(response => {
                        this.recipes = response.data;
                    })
                }
            }
        },

        mounted() {
            this.retrieveRecipes();
            this.retrieveCategories();
            axios.get('http://localhost:8080/api/loggedIn').then(response => (this.user = response.data));

        }

    };
</script>

<style scoped>

    input {
        visibility: hidden;
    }

    ul {
        list-style-type: none;
        margin: 0;
        -webkit-columns: 2;
        -moz-columns: 2;
        columns: 2;
        padding-left: 0;
    }
    ul li {
        list-style-position: inside;
        -webkit-column-break-inside: avoid;
        page-break-inside: avoid;
        break-inside: avoid;
    }
    li {
        font: 200 17px/1.5 Helvetica, Verdana, sans-serif;
        border-bottom: 1px solid #ccc;
    }

    li:last-child {
        border: none;
    }

    li a {
        text-decoration: none;
        color: #000;
        display: block;
        width: auto;
    }

    li a:hover {
        background: rgba(255, 255, 255, 0.5);
    }
    .container {
        width: 55%;
        display: block;
        margin-left: 125px;
    }
    h2 {
        margin-right: 700px;
    }

</style>