<template>
    <div class="account">
        <div class="container">
            <h4>Minu konto</h4>

            <!--labelid tglt v-if useri kohta-->
            <div class="labelid">
                <div>
                    <label>Kasutajanimi: </label>
                </div>

                <div>
                    <label>Email: </label>
                </div>

                <div>
                    <!--TODO: listi kujundust muuta.-->
                    <label>Minu retseptid: </label>
                    <li v-for="(recipe, index) in recipes" :key="index">
                        <router-link :to="{
                            name: 'recipe',
                            params: { recipe: recipe, id: recipe.id }
                        }" style="color:#333">
                            {{recipe.name}}
                        </router-link>
                    </li>
                </div>

            </div>
        </div>
        <div class="col-md-6">
            <router-view @refreshData="refreshList"></router-view>
        </div>
    </div>
</template>

<script>
    import http from "../http-common";

    export default {
        name: "MyAccount",
        data() {
            return {
                recipes: []
            }
        },
        methods: {
             // TODO: Siduda useri id-ga.
            retrieveRecipes() {
                http.
                    get("/recipes").
                    then(response => {
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