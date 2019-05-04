<template>
    <div class="login">
        <b-form id="login" v-on:submit.prevent="login">
            <b-form-input type='text' placeholder='kasutajanimi' v-model='username' required style="margin-bottom: 5px"/>
            <b-form-input type='password' placeholder='parool' v-model='password' required style="margin-bottom: 5px"/>
            <span style="color:red">{{loginError}}</span>
            <b-button class="mb-3" type="submit" variant="success" block>
                Logi sisse
            </b-button>
        </b-form>
        <b-button variant="outline-success" to='/register' block>Loo kasutaja</b-button>
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
                if(this.username !== "" && this.password !== "") {
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
