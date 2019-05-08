<template>
    <div class="recipes">
        <div class="sidemenu">
            <b-button-group vertical >
                <b-button style="opacity:0.75;" variant="info" v-on:click="chosenCategory('all')" active>
                    Kõik<input type="radio" name="options" id="all" autocomplete="off" checked></b-button>
                <b-button variant="info" style="opacity:0.75;" v-for="(category, index) in categories" :key="index" v-on:click="chosenCategory(category.name)">
                {{category.name}}<input type="radio" name="options" :id=category.name autocomplete="off"></b-button>
            </b-button-group>
        </div>
            <div class="recipe-list">
                <h4>Retseptid</h4>
               <span class="info"> {{this.filterCat}}</span>
                        <ul>
                            <li v-for="(recipe, index) in recipes" :key="index"
                                style="text-align: center">
                                <router-link :to="{
                            name: 'recipe',
                            params: { recipe: recipe, id: recipe.id }
                        }">
                                    {{recipe.name}}
                                </router-link>
                            </li>
                        </ul>
                    <div>
                        <router-view @refreshData="refreshList"></router-view>
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
                categories:[],
                filterCat:''
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
                    });
                    this.filterCat=category
                } else {

                    http.get("/recipes").then(response => {
                        this.recipes = response.data;
                    });
                    this.filterCat=''
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

</style>