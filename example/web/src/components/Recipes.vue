<template>
    <div><div class="sidemenu" style=" float:left; display:inline; "><!--width: 25%;-->
       
        <div class="btn-group-vertical" data-toggle="buttons" style="float:right;">
            <button type="button" class="btn btn-secondary btn-sm" v-on:click="chosenCategory('all')" active>Kõik<input type="radio" name="options" id="all" autocomplete="off" checked></button>
            <button type="button" class="btn btn-secondary btn-sm" v-on:click="chosenCategory('hommikusöök')">Hommikusöögid<input type="radio" name="options" id="breakfast" autocomplete="off"></button>
            <button type="button" class="btn btn-secondary btn-sm" v-on:click="chosenCategory('jook')">Joogid<input type="radio" name="options" id="drinks" autocomplete="off"></button>
            <button type="button" class="btn btn-secondary btn-sm" v-on:click="chosenCategory('kook')">Koogid<input type="radio" name="options" id="cakes" autocomplete="off"></button>
            <button type="button" class="btn btn-secondary btn-sm" v-on:click="chosenCategory('magustoit')">Magustoidud<input type="radio" name="options" id="dessert" autocomplete="off"></button>
            <button type="button" class="btn btn-secondary btn-sm" v-on:click="chosenCategory('pastatoit')">Pastatoidud<input type="radio" name="options" id="pasta" autocomplete="off"></button>
            <button type="button" class="btn btn-secondary btn-sm" v-on:click="chosenCategory('pirukad')">Pirukad<input type="radio" name="options" id="pies" autocomplete="off"></button>
            <button type="button" class="btn btn-secondary btn-sm" v-on:click="chosenCategory('praad')">Praed<input type="radio" name="options" id="meal" autocomplete="off"></button>
            <button type="button" class="btn btn-secondary btn-sm" v-on:click="chosenCategory('salat')">Salatid<input type="radio" name="options" id="salad" autocomplete="off"></button>
            <button type="button" class="btn btn-secondary btn-sm" v-on:click="chosenCategory('supp')">Supid<input type="radio" name="options" id="soup" autocomplete="off"></button>
            <button type="button" class="btn btn-secondary btn-sm" v-on:click="chosenCategory('suupisted')">Suupisted<input type="radio" name="options" id="snaks" autocomplete="off"></button>
            <button type="button" class="btn btn-secondary btn-sm" v-on:click="chosenCategory('tort')">Tordid<input type="radio" name="options" id="pie" autocomplete="off"></button>
            <button type="button" class="btn btn-secondary btn-sm" v-on:click="chosenCategory('võileivatort')">Võileivatordid<input type="radio" name="options" id="sandwich-cake" autocomplete="off"></button>
            <button type="button" class="btn btn-secondary btn-sm" v-on:click="chosenCategory('vormiroog')">Vormiroad<input type="radio" name="options" id="formdish" autocomplete="off"></button>
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
    input{
        visibility:hidden;
    }

</style>