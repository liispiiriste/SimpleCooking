<template>
    <div class="submitform">
        <div v-if="!submitted" class="register-form" style="font-size:20px">
            <h1 style="font-size:170%">Registreeri</h1>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" required v-model="user.email" name="email">
                <div  class="error"> {{emailError}}</div>
            </div>
            <div class="form-group">
                <label for="username">Kasutajanimi</label>
                <input type="text" class="form-control" id="username" required v-model="user.username" name="username">
                <div class="error"> {{usernameError}}</div>
            </div>
            <div class="form-group">
                <label for="password">Parool</label>
                <input type="password" class="form-control" id="password" required v-model="user.password" name="password">
                <div class="error"> {{passwordError}}</div>
            </div>
            <div class="form-group">
                <label for="password2">Parooli kordus</label>
                <input type="password" class="form-control" id="password2" required v-model="password2" name="password2">
                <div class="error"> {{password2Error}}</div>
                <div class="error"> {{passwordMError}}</div>
            </div>
            <b-button v-on:click="signUp" variant="info">Registreeri</b-button>
        </div>
        <div v-else>
            <h4>Registreeritud!</h4>
            <router-link to="/login">
                <b-button type="reset"
                          variant="info">
                    Logi sisse
                </b-button>
            </router-link>
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
                    role:"0"
                },
                submitted:false,
                emailError:"",
                usernameError:"",
                passwordError:"",
                password2:"",
                password2Error:"",
                passwordMError:""
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
                if(!this.validEmail(this.user.email)){this.emailError="Lisa päris emaili aadress"}
                if(!this.user.email){this.emailError="Lisa email" }
                if(this.user.email&&this.validEmail(this.user.email)){this.emailError=""}
                if(!this.user.username){this.usernameError="Lisa kasutajanimi" }
                if(this.user.username){this.usernameError=""}
                if(this.user.password.length<5){this.passwordError="Parool peab sisaldama vähemalt 5 sümbolit" }
                if(this.user.password.length>=5){this.passwordError=""}
                if(!this.password2){this.password2Error="Korda parooli"}
                if(this.password2){this.password2Error=""}
                if(this.password2!=this.user.password && this.password2 && this.user.password ){this.passwordMError="Paroolid ei ole samad"}
                if(this.password2==this.user.password){this.passwordMError=""}
                if(!this.user.emailError && !this.user.usernameError && !this.user.passwordError && !this.password2Error && !this.passwordMError){
                http
                    .post("/register", data)
                    .then(response =>{
                        this.user.id = response.data.id;
                    });
                this.submitted=true;}
            },
            validEmail: function (email) {
                var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
                return re.test(email);
            }
        }
    };
</script>
<style scoped>
</style>