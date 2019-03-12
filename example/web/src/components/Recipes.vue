<template>
    <div class="list row">
        <div class="col-md-6" >
            <h1 style="margin-left:60px; font-size:200%">Retseptid</h1>
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
        <div class="col-md-6">
            <router-view @refreshData="refreshList"></router-view>
        </div>
    </div>

</template>

<script>
    import http from "../http-common";
    export default {
        name: "Recipes",
        data() {
            return {
                recipes: []
            }
        },
        methods: {
            retrieveRecipes() {
                http.
                get("/recipes").
                then(response=> {
                    this.recipes = response.data;

                })

            },
            refreshList() {
                this.retrieveRecipes();
            }
        },
        mounted() {
            this.retrieveRecipes();
        }
    };
</script>

<style scoped>
    .list {
        text-align: left;
        max-width: 450px;
        margin: auto;
        align: center

    }
    ul {
        text-align: left;
        list-style: inside;
        font-size:20px;
        width: 300%;
    }

</style>