<template>
    <div class="list row">
        <div class="col-md-6">
            <h4>Retseptid</h4>
            <ul>
                <li v-for="(recipe, index) in recipes" :key="index">

                    <router-link :to="{
                            name: 'recipe',
                            params: { recipe: recipe, id: recipe.id }
                        }">
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
    }

    ul {list-style-type: square;}
</style>