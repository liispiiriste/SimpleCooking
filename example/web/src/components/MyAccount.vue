<template>
    <div class="account">
        <div class="container">
            <h4>Minu konto</h4>

            <!--labelid tglt v-if useri kohta-->
            <div class="labelid">
                <div>
                    <label>Kasutajanimi: </label> {{this.user.username}}
                </div>

                <div>
                    <label>Email: </label> {{this.user.email}}
                </div>

                <div>
                    <!--TODO: listi kujundust muuta.-->
                    <label>Minu retseptid: </label> {{this.user.recipes}}

                </div>

            </div>
        </div>

    </div>
</template>

<script>
    import axios from "axios";

    export default {
        name: "MyAccount",
        data() {
            return {
                user: {
                    id: this.user.id,
                    email: '',
                    username: '',
                    recipes: []
                }
            }
        },
        mounted() {
            axios.get('http://localhost:8080/api/getById/'+this.user.id).then(response => (this.user = response.data));
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