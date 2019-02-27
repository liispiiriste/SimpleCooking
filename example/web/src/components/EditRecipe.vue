<template>

    <div class="editRecipe">
        <div class="container">
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

                </div>
            </div>

        </div>

</template>

<script>
    import http from "../http-common";

    export default {
        name: "editRecipe",

        props: ["recipe"],


    }

</script>


<style scoped>
    .submitform {
        margin: 30px;
        float: left;
        width: 80%
    }
</style>