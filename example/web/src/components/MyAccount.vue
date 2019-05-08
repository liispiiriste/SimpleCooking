<template>
    <div class="account">
        <div class="container">
            <h4>Minu konto</h4>
            <div class="box">
                <div>
                    <label>Kasutajanimi:</label> {{user.username}}
                </div>

                <div>
                    <label>Email:</label> {{user.email}}
                </div>
            </div>

            <div class="box">
                <label>Minu retseptid: </label>
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

            </div>
            <div class="box">
                <label style="text-align: left">Minu lemmikud: </label>
                <ul>
                    <li v-for="(recipe, index) in favourites" :key="index"
                        style="text-align: center">
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
</template>
<script>
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
                },
                favourites: {
                    favourites: []
                }
            }
        },
        mounted() {
            http.get('/loggedIn').then(response => (this.user = response.data));
            http.get("/userRecipes").then(response => {
                this.recipes = response.data;
            });
            http.get("/favourite").then(response => {
                this.favourites = response.data;
            });
        }
    }
</script>
<style scoped>
</style>