<template>
    <div class="login">
        <form id="login" v-on:submit.prevent="login">
            <input class="form-control" type='text' placeholder='kasutajanimi' v-model='username'>
            <input class="form-control" type='password' placeholder='parool' v-model='password'>
            <span style="color:red">{{loginError}}</span>
            <button >
                Logi sisse
            </button>
        </form>
<br>
        <router-link to="/register">
            <button type="reset" >Mul ei ole kasutajat</button>
        </router-link>

    </div>

</template>

<script>
    import {AUTH_REQUEST} from "../store/constants";

    export default {
        name: 'login',
        data() {

            return {
                username: '',
                password: '',
                loginError:''
            }
        },
        methods: {
            login () {
                if(this.username != "" && this.password != "") {
                const { username, password } = this;
                this.$store.dispatch(AUTH_REQUEST, {username, password}).then(() => {
                    this.$router.push('/home');
                    this.$emit("authenticated", true);
                });
                } else {
                    this.loginError="Lisa kasutajanimi ja/või parool";
                }
            }
        }

    }
</script>

<style scoped>
    h3 {
        margin: 40px 0 0;
    }
    ul {
        list-style-type: none;
        padding: 0;
    }
    li {
        display: inline-block;
        margin: 0 10px;
    }
    a {
        color: #42b983;
    }
    .login {
        margin: auto;
        width: 80%;
        max-width:400px;
        align: center;
        color: #333
    }
</style>
