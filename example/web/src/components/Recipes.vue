<template>
    <div class="recipes">
        <div class="sidemenu">
            <b-button-group vertical>
                <b-button v-on:click="chosenCategory('all')">
                    Kõik<input type="radio" name="options" id="all" autocomplete="off" checked></b-button>
                <b-button v-for="(category, index) in categories" :key="index" v-on:click="chosenCategory(category.name)">
                    {{category.name}}<input type="radio" name="options" id=category.name autocomplete="off" checked></b-button>
            </b-button-group>
        </div>
        <div class="list row">
            <b-card title="Retseptid" style="background:rgba(255, 255, 255, 0.2); border:none; border-radius:25px;">
                <b-card-text>
                    <div class="col-md-6">
                        <ul>
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
                    <div>
                        <router-view @refreshData="refreshList"></router-view>
                    </div>
                </b-card-text>
            </b-card>
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
                searchStr:'',
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
                //this.retrieveRecipes();
                this.retrieveCategories();
            },
            chosenCategory(category) {
                if (category != "all") {
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
        },

    };
</script>

<style scoped>


    ul {
        text-align: left;
        list-style: inside;
        font-size: 20px;
        width: 300%;
    }

    input {
        visibility: hidden;
    }

</style>