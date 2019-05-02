<template>
    <div class="account">
        <div class="container">
            <h4>Minu konto</h4>

            <div class="labelid"
                 style="margin:10px; padding:5px; background:rgba(255, 255, 255, 0.2); border:none; border-radius:15px;">
                <div>
                    <label>Kasutajanimi:</label> {{user.username}}
                </div>

                <div>
                    <label>Email:</label> {{user.email}}
                </div>
            </div>

            <div style="margin:15px; padding:10px; background:rgba(255, 255, 255, 0.2); border:none; border-radius:15px; text-align: left;">
                <label style="text-align: left">Minu retseptid: </label>
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
                }
            }
        },
        mounted() {
            http.get('/loggedIn').then(response => (this.user = response.data));
            http.get("/userRecipes").then(response => {
                this.recipes = response.data;
            })
        }
    }
</script>

<style scoped>
    h2 {
        margin-right: 200px;
    }

    label {
        min-width: 300px;
        margin: auto;
        font-weight: bold;
        text-align: left;
    }

    .container {
        width: 55%;
        margin: auto;
    }

    .labelid {
        text-align: left;
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

</style>