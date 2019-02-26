<template>
    <div class="list row">
        <div class="col-md-6">
            <h4>Retseptid</h4>
            <ul>
                <li v-for="(recipe, index) in recipes" :key="index">
                    <h4 @click="goToRecipe(recipe.id)">
                        {{recipe.name}}
                    </h4>
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
            },
            goToRecipe(recipId) {
                let recId=recipId
                this.$router.push({name:'recipe',params:{Rid:recId}})
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