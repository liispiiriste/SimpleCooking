<template>
    <div class="submitform">

        <div v-if="!submitted" style="font-size:20px">
            <h1 style="font-size:170%">Registreeri</h1>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="text" class="form-control" id="email" required v-model="user.email" name="email">
            </div>
            <div class="form-group">
                <label for="username">Kasutajanimi</label>
                <input type="text" class="form-control" id="username" required v-model="user.username" name="username">
            </div>

            <div class="form-group">
                <label for="password">Parool</label>
                <input type="text" class="form-control" id="password" required v-model="user.password" name="password">
            </div>

            <button v-on:click="signUp" class="btn btn-success">Registreeri</button>
        </div>

        <div v-else>
            <h4>Registreeritud!</h4>
        </div>
    </div>
</template>

<script>
    import http from "../http-common";
    export default {
        name: "Register",
        data(){
            return{
                user:{
                    id:0,
                    email:"",
                    username:"",
                    password:"",
                    role:"1"
                },
                submitted:false
            };
        },
        methods:{
            signUp(){
                let data = {
                    email: this.user.email,
                    username: this.user.username,
                    password: this.user.password,
                    role: this.user.role
                };
                http
                    .post("/register", data)
                    .then(response =>{
                        this.user.id = response.data.id;
                    });
                this.submitted=true;
            }
        }
    };
</script>

<style scoped>
</style>