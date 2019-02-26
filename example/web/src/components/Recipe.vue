<template>
    <div class="recipe">
        <div class="container">
            <h4>Retsept</h4>
            <div v-for="(recipe,index) in details" :key="index">
                <div v-if="Rid == recipe.id">
                    <h1>{{recipe.name}} sth</h1>
                </div>
            </div>
           <!-- <h1 class="text-primary text-center">{{recipe.name}}</h1>-->
        </div>
        <div class="col-md-6">
            <router-view @refreshData="refreshDetails"></router-view>
        </div>
    </div>
</template>

<script>
    import http from "../http-common";
    export default {
        name: "Recipe",
        data(){
            return{
                details: []
            }
        },
        methods:{
            retrieveDetails() {
            http.
            get("/recipe").
            then(response=> {
                this.recipes = response.data;

            })

        },
            refreshDetails() {
                this.retrieveDetails();
            }},
        mounted() {
            this.retrieveDetails();
        }

    }
</script>

<style scoped>

</style>