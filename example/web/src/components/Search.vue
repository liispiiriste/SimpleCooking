<template>
<div id="searchs">

        <form id="search" >

            <input size="sm" class="mr-sm-2" placeholder="Otsi retsepte..." v-model="searchStr" type="search" />

            <button size="sm" class="my-2 my-sm-0"  v-on:click="searchRecipe('searchStr')">
                Otsi
            </button>
        </form>

    <li v-for="(recipe, index) in recipes" :key="index">

        <router-link :to="{
                            name: 'recipe',
                            params: { recipe: recipe, id: recipe.id }
                        }" style="color:#333">
            {{recipe.name}}
        </router-link>
    </li>




</div>


</template>

<script>

    import axios from "axios";
    import http from "../http-common";

    export default {
        name: "Search",

        data() {


            return {
                recipes: {
                    recipes: []
                },
                searchStr: ''

            }
        },
        methods: {
            searchRecipe(){
                http.get('http://localhost:8080/api/recipes/search/' + this.searchStr)
                    .then(response => {
                        this.recipes = response.data;
                    })

            }
        },

        mounted() {
            axios.get('http://localhost:8080/api/loggedIn').then(response => (this.user = response.data));
            http.get("/recipes").then(response=> {this.recipes = response.data;})
            http.get("/recipes" + this.searchStr).then(response=> {this.recipes = response.data;})


        }
    }
</script>

<style scoped>

</style>