<template>
    <div class="recipe" style="width:60%;margin:auto">
        <div class="container">
            <div v-if="!submitted">
                <h4>{{this.recipe.name}}</h4>

                <div v-if="this.recipe" style="text-align:left">
                    <div>
                        <label style="min-width: 75px">Kirjeldus: </label> {{this.recipe.description}}
                    </div>
                    <div>
                        <label style="min-width: 75px">Materjalid: </label> {{this.recipe.materials}}
                    </div>
                    <div>
                        <label style="min-width: 75px">Portsjon: </label> {{this.recipe.portion}}
                    </div>
                    <div>
                        <label style="min-width: 75px">Hind: </label> {{this.recipe.price}}
                    </div>
                    <button class="btn btn-danger btn-xs" style="float:left" >
                        <a><router-link :to="{name: 'editRecipe', params: {id: recipe.id}}">Muuda</router-link></a></button>
                </div>

                <button class="btn btn-danger btn-xs" style="float:left; margin-left:5px" v-on:click="deleteRecipe()">Kustuta</button>
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
#a:hover, :link, :visited, :active{
    color: white;
    text-decoration: none;

}
    #label{
        min-width: 300px;
    }

</style>