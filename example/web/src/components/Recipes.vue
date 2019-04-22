<template>
    <div class="recipes">
        <div class="sidemenu">
            <b-button-group vertical>
                <b-button v-on:click="chosenCategory('all')" active>
                    Kõik<input type="radio" name="options" id="all" autocomplete="off" checked></b-button>
                <b-button v-on:click="chosenCategory('hommikusöök')">
                    Hommikusöögid<input type="radio" name="options" id="breakfast" autocomplete="off"></b-button>
                <b-button v-on:click="chosenCategory('jook')">
                    Joogid<input type="radio" name="options" id="drinks" autocomplete="off"></b-button>
                <b-button v-on:click="chosenCategory('kook')">
                    Koogid<input type="radio" name="options" id="cakes" autocomplete="off"></b-button>
                <b-button v-on:click="chosenCategory('magustoit')">
                    Magustoidud<input type="radio" name="options" id="dessert" autocomplete="off"></b-button>
                <b-button v-on:click="chosenCategory('pastatoit')">
                    Pastatoidud<input type="radio" name="options" id="pasta" autocomplete="off"></b-button>
                <b-button v-on:click="chosenCategory('pirukad')">
                    Pirukad<input type="radio" name="options" id="pies" autocomplete="off"></b-button>
                <b-button v-on:click="chosenCategory('praad')">
                    Praed<input type="radio" name="options" id="meal" autocomplete="off"></b-button>
                <b-button v-on:click="chosenCategory('salat')">
                    Salatid<input type="radio" name="options" id="salad" autocomplete="off"></b-button>
                <b-button v-on:click="chosenCategory('supp')">
                    Supid<input type="radio" name="options" id="soup" autocomplete="off"></b-button>
                <b-button v-on:click="chosenCategory('suupisted')">
                    Suupisted<input type="radio" name="options" id="snacks" autocomplete="off"></b-button>
                <b-button v-on:click="chosenCategory('tort')">
                    Tordid<input type="radio" name="options" id="pie" autocomplete="off"></b-button>
                <b-button v-on:click="chosenCategory('võileivatort')">
                    Võileivatordid<input type="radio" name="options" id="sandwich-cake" autocomplete="off"></b-button>
                <b-button v-on:click="chosenCategory('vormiroog')">
                    Vormiroad<input type="radio" name="options" id="formdish" autocomplete="off"></b-button>
            </b-button-group>

        </div>
        <div class="list row">
            <b-card title="Retseptid" style="background:rgba(255, 255, 255, 0.2); border:none; border-radius:25px;">
                <b-card-text>
                    <div class="col-md-6">
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
                    <div>
                        <router-view @refreshData="refreshList"></router-view>
                    </div>
                </b-card-text>
            </b-card>
        </div>
    </div>
</template>

<script>
    import http from "../http-common";

    import axios from "axios";

    export default {
        name: "Recipes",
        data() {
            return {
                recipes: []
            }
        },
        methods: {
            retrieveRecipes() {
                http.get("/recipes").then(response => {
                    this.recipes = response.data;

                });

            },

            refreshList() {
                this.retrieveRecipes();
            },
            chosenCategory(c) {
                if (c != "all") {
                    http.get("/recipes/" + c).then(response => {
                        this.recipes = response.data;

                    })
                } else {
                    http.get("/recipes").then(response => {
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


    ul {
        text-align: left;
        list-style: inside;
        font-size: 20px;
        width: 300%;
    }

    input {
        visibility: hidden;
    }

</style>