<template>
    <div class="login">
        <b-form id="login" v-on:submit.prevent="login">
            <b-form-input type='text' placeholder='kasutajanimi' v-model='username' required style="margin-bottom: 5px"/>
            <b-form-input type='password' placeholder='parool' v-model='password' required style="margin-bottom: 5px"/>
            <span class="err">{{loginError}}</span>
            <b-button class="mb-3" type="submit" variant="info" block>
                Logi sisse
            </b-button>
        </b-form>
        <b-button class="create-user-btn" variant="outline-info" to='/register' block>Loo kasutaja</b-button>
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
</style>
