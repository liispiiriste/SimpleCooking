<template>
    <div class="account">
        <div class="container">
            <h4>Minu konto</h4>

            <!--labelid tglt v-if useri kohta-->
            <div class="labelid">
                <div>
                    <label>Kasutajanimi: </label> {{user.username}}
                </div>

                <div>
                    <label>Email: </label> {{user.email}}
                </div>

                <div>
                    <!--TODO: listi kujundust muuta.-->
                    <label>Minu retseptid: </label> {{user.recipes}}

                </div>

            </div>
        </div>

    </div>
</template>

<script>
    import axios from "axios";
    import $store from "../store/modules/auth";

    export default {
        name: "MyAccount",

        data() {
            return {
                user: {
                    email: $store.state.user.email,
                    username: $store.state.user.username,
                    recipes: $store.state.user.recipes
                }
            }
        },
        mounted() {
            axios.get('http://localhost:8080/api/loggedIn').then(response => (this.user = response.data));
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