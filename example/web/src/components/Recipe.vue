<template>
    <div class="recipe">
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
                    <label>Portsion: </label> {{this.recipe.portion}}
                </div>
                <div>
                    <label>Hind: </label> {{this.recipe.price}}
                </div>
        </div>
        <div class="col-md-6">
            <router-view @refreshData="refreshDetails"></router-view>
        </div>
        </div></div>
</template>

<script>
    export default {
        name: "Recipe",

        props: ["recipe"],
        }
</script>

<style scoped>

</style>