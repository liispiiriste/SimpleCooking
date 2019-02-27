<template>
    <div class="recipe">
        <div class="container">
            <div v-if="!submitted">
                <h4>{{this.recipe.name}}</h4>

                <div v-if="this.recipe" style="text-align:left">
                    <div>
                        <label>Kirjeldus: </label> {{this.recipe.description}}
                    </div>
                    <div>
                        <label>Materialid: </label> {{this.recipe.materials}}
                    </div>
                    <div>
                        <label>Portsjon: </label> {{this.recipe.portion}}
                    </div>
                    <div>
                        <label>Hind: </label> {{this.recipe.price}}
                    </div>
                    <a class="btn btn-warning btn-xs"><router-link :to="{name: 'editRecipe', params: {id: recipe.id}}">Edit</router-link></a>
                </div>
                <button class="btn btn-danger btn-xs" v-on:click="deleteRecipe()">Kustuta</button>
            </div>
            <div v-else>
                <h4>Retsept on edukalt kustutatud!</h4>
                <!--<div class="col-md-6">
                    <router-view @refreshData="refreshDetails"></router-view>
                </div> -->
            </div>
        </div>
    </div>
</template>

<script>
    import http from "../http-common";

    export default {
        name: "Recipe",

        props: ["recipe"],
        data() {
            return {
                submitted: false
            };
        },
        methods: {
            deleteRecipe() {
                http
                    .delete("/recipe/" + this.recipe.id)
                    .then(response => {
                        this.$emit("refreshData");
                        this.$router.push('/');
                    });
                this.submitted = true;
            }
        }
    }
</script>

<style scoped>

</style>