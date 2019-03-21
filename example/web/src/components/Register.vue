<template>
    <div class="submitform">

        <div v-if="!submitted" class="register-form" style="font-size:20px">
            <h1 style="font-size:170%">Registreeri</h1>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="text" class="form-control" id="email" required v-model="user.email" name="email">
                <div  style="color:red;"> {{emailError}}</div>
            </div>
            <div class="form-group">
                <label for="username">Kasutajanimi</label>
                <input type="text" class="form-control" id="username" required v-model="user.username" name="username">
                <div  style="color:red;"> {{usernameError}}</div>
            </div>

            <div class="form-group">
                <label for="password">Parool</label>
                <input type="password" class="form-control" id="password" required v-model="user.password" name="password">
                <div  style="color:red;"> {{passwordError}}</div>
            </div>
            <div class="form-group">
                <label for="password2">Parooli kordus</label>
                <input type="password" class="form-control" id="password2" required v-model="password2" name="password2">
                <div  style="color:red;"> {{password2Error}}</div>
                <div  style="color:red;"> {{passwordMError}}</div>
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
                if(!this.user.email){this.emailError="Lisa email" }
                if(this.user.email){this.emailError=""}
                if(!this.user.username){this.usernameError="Lisa kasutajanimi" }
                if(this.user.username){this.usernameError=""}
                if(this.user.password.length<5){this.passwordError="Parool peab sisaldama vähemalt 5 sümbolit" }
                if(this.user.password.length>=5){this.passwordError=""}
                if(!this.password2){this.password2Error="Korda parooli"}
                if(this.password2){this.password2Error=""}
                if(this.password2!=this.user.password && this.password2 && this.user.password ){this.passwordMError="Paroolid ei ole samad"}
                if(this.password2==this.user.password){this.passwordMError=""}
                if(this.user.email && this.user.username && this.user.password && !this.password2Error && !this.passwordMError){
                http
                    .post("/register", data)
                    .then(response =>{
                        this.user.id = response.data.id;
                    });
                this.submitted=true;}
            }
        }
    };
</script>

<style scoped>
    .submitform {
        margin: auto;
        width: 80%;
        align: center;
        color: #333
    }
    .register-form{
        margin:auto;
        width: 40%;
        align:center;
        color:#333;
    }
</style>