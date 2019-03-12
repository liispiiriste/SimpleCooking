<template>
    <div >


        <div v-if="!submitted">
            <h2>Logi sisse</h2>
        <div class="form-group">
            <input
                    type="text"
                    class="form-control"
                    placeholder="Kasutajanimi"
                    v-model="user.username"
            >
        </div>
        <div class="form-group">
            <input
                    type="password"
                    class="form-control"
                    placeholder="Parool"
                    v-model="user.password"
            >
        </div>
        <button class="btn btn-primary" v-on:click="submit">Logi sisse</button>
        </div>
    <div v-else>
        Tere, {{this.user.username}}!
    </div></div>
</template>

<script>
    import auth from "../auth"
    export default {
        name: "SignIn",
        data(){
            return{
                user:{
                    username:"",
                    password:""

                },
                submitted:false
            }
        },
        methods:{
            submit(){
                var user={
                    username:this.user.username,
                    password:this.user.password
                };
                    this.submitted=true,
                        auth.login(this,user,"home");

            }
        }
    }
</script>

<style scoped>

</style>