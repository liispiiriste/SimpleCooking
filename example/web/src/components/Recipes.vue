<template>
    <div><div class="sidemenu" style=" float:left; display:inline; width: 25%;">
        <p style="font-size:20px; margin-top:50px;">Kategooriad:</p>
        <div class="btn-group-vertical" data-toggle="buttons" style="float:right;">
            <button type="button" class="btn btn-secondary btn-sm" v-on:click="chosenCategory('all')">Kõik</button>
            <button type="button" class="btn btn-secondary btn-sm" v-on:click="chosenCategory('hommikusöök')">Hommikusöögid</button>
            <button type="button" class="btn btn-secondary btn-sm" v-on:click="chosenCategory('jook')">Joogid</button>
            <button type="button" class="btn btn-secondary btn-sm" v-on:click="chosenCategory('kook')">Koogid</button>
            <button type="button" class="btn btn-secondary btn-sm" v-on:click="chosenCategory('magustoit')">Magustoidud</button>
            <button type="button" class="btn btn-secondary btn-sm" v-on:click="chosenCategory('pastatoit')">Pastatoidud</button>
            <button type="button" class="btn btn-secondary btn-sm" v-on:click="chosenCategory('pirukad')">Pirukad</button>
            <button type="button" class="btn btn-secondary btn-sm" v-on:click="chosenCategory('praad')">Praed</button>
            <button type="button" class="btn btn-secondary btn-sm" v-on:click="chosenCategory('salat')">Salatid</button>
            <button type="button" class="btn btn-secondary btn-sm" v-on:click="chosenCategory('supp')">Supid</button>
            <button type="button" class="btn btn-secondary btn-sm" v-on:click="chosenCategory('suupisted')">Suupisted</button>
            <button type="button" class="btn btn-secondary btn-sm" v-on:click="chosenCategory('tort')">Tordid</button>
            <button type="button" class="btn btn-secondary btn-sm" v-on:click="chosenCategory('võileivatort')">Võileivatordid</button>
            <button type="button" class="btn btn-secondary btn-sm" v-on:click="chosenCategory('vormiroog')">Vormiroad</button>
        </div>

    </div>

    <div class="list row" style="float:left; display:inline; width:60%;">
        <div class="col-md-6" >
            <h1 style="margin-left:60px; font-size:200%">Retseptid</h1>
            <ul>
                <li v-for="(recipe, index) in recipes" :key="index">

                    <router-link :to="{
                            name: 'recipe',
                            params: { recipe: recipe, id: recipe.id }
                        }" style="color:#333">
                        {{recipe.name}}
                    </router-link>
                </li>
            </ul>
        </div>
        <div class="col-md-6">
            <router-view @refreshData="refreshList"></router-view>
        </div>
    </div>
    </div>
</template>

<script>
    import http from "../http-common";
    export default {
        name: "Recipes",
        data() {
            return {
                recipes: []
            }
        },
        methods: {
            retrieveRecipes() {
                http.
                get("/recipes").
                then(response=> {
                    this.recipes = response.data;

                })


            },
            refreshList() {
                this.retrieveRecipes();
            },
            chosenCategory(c){
                if(c!="all"){http.get("/recipes/"+c).then(response=> {
                    this.recipes = response.data;

                })}
                else {
                    http.
                    get("/recipes").
                    then(response=> {
                        this.recipes = response.data;

                    })
                }
            }
        },
        mounted() {
            this.retrieveRecipes();
        }
    };
</script>

<style scoped>
    .list {
        text-align: left;
        max-width: 450px;
        margin: auto;
        align: center

    }
    ul {
        text-align: left;
        list-style: inside;
        font-size:20px;
        width: 300%;
    }

</style>