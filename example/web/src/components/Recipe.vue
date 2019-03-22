<template>
    <div class="recipe">
        <div class="container">
            <div v-if="!submitted">
                <h4>{{this.recipe.name}}</h4>

                <div v-if="this.recipe" style="text-align:left">
                    <div>
                        <label >Juhend: </label> {{this.recipe.description}}
                    </div>
                    <div>
                        <label >Materjalid: </label> {{this.recipe.materials}}
                    </div>
                    <div>
                        <label >Portsjon: </label> {{this.recipe.portion}}
                    </div>
                    <div>
                        <label >Hind: </label> {{this.recipe.price}}
                    </div>
                    <div>
                        <label >Kategooria: </label>
                       {{this.recipe.category}}
                    </div>
                    <button class="btn btn-warning btn-xs" style="float:left;" >
                        <router-link :to="{name: 'editRecipe', params: {recipe:recipe, id: recipe.id}}">Muuda</router-link></button>
                </div>

                <button class="btn btn-danger btn-xs" style="float:left; margin-left:5px" v-on:click="deleteRecipe()">Kustuta</button>
            </div>
            <div v-else>
                <h4>Retsept on edukalt kustutatud!</h4>
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
                        this.$router.push('/recipe');
                    });
                this.submitted = true;
            }
        }
    }
</script>

<style scoped>
a:hover, :link, :visited, :active{
    color: white;
    text-decoration: none;

}
    label{
        min-width: 90px;
        margin:auto;
        font-weight:bold;
        margin-top:5px;
    }
    .container{
        width:70%;
        margin:auto;
    }

</style>