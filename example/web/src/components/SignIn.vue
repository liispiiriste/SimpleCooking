<template>
    <div class="submitform">

        <div v-if="!submitted" style="font-size:20px">
            <h1 style="font-size:170%">Logi sisse</h1>
            <div class="form-group">
                <label for="username">Kasutajanimi</label>
                <input type="text" class="form-control" id="username" required v-model="user.username" name="username">
            </div>

            <div class="form-group">
                <label for="password">Parool</label>
                <input type="text" class="form-control" id="password" required v-model="user.password" name="password"></input>
            </div>

            <button v-on:click="signIn" class="btn btn-success">Logi sisse</button>
        </div>

        <div v-else>
            <h4>Sees!</h4>
        </div>
    </div>
</template>

<script>
    export default {
        name: "SignIn",
        data(){
            return{
                user:{
                    id:0,
                    username:""
                }
            }
        }
    }
</script>

<style scoped>

</style>