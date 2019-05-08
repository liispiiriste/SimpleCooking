<template>
<div id="search" >

    <div class="search-wrapper" >
        <input type="text" v-model="searchStr" placeholder="Otsi retsepte"/>

    </div>

    <div class="box" >
        <ul>
        <li v-for="(recipe,index) in filteredList" :key="index">
            <router-link :to="{
                            name: 'recipe',
                            params: { recipe: recipe, id: recipe.id }
                        }" style="color:#333">
                {{recipe.name}}
            </router-link>


        </li>
        </ul>

</div>
</div>

</template>

<script>

    import axios from "axios";
    import http from "../http-common";

    export default {
        name: "Search",

        data() {
            return {
                recipes: [],
                searchStr: ''
            }
        },
        methods: {

        },
        computed: {
            filteredList() {
                return this.recipes.filter(recipe => {
                    return recipe.name.toLowerCase().includes(this.searchStr.toLowerCase())
                })
            }
        },

        mounted() {
            axios.get('http://localhost:8080/api/loggedIn').then(response => (this.user = response.data));
            http.get("/recipes").then(response=> {this.recipes = response.data;})
        }
    }
</script>

<style scoped>
        html, body {
            display: flex;
            align-items: center;
            justify-content: center;
            flex-direction: column;
            margin-top: 16px;
            margin-bottom: 16px;

        }


        .search-wrapper {
            position: relative;
        }


        input {
            padding: 4px 12px;
            color: rgba(0, 0, 0, .70);
            border: 1px solid rgba(0, 0, 0, .12);
            transition: .15s all ease-in-out;
            background: white;
            font-size: 18px
        }
        focus {
             outline: none;
             transform: scale(1.05);
         }

        .wrapper {
            display: flex;
            max-width: 444px;
            flex-wrap: wrap;
            padding-top: 12px;

        }

        hover {
             transform: scale(1.1);
         }
        a {
            text-decoration: none;
            padding: 16px;
            color: #333;
            font-size: 15px;
            display: flex;
            flex-direction: column;
            align-items: center;
            font-family: 'Avenir', Helvetica, Arial, sans-serif;

        }
        small {
            font-size: 10px;
            padding: 4px;
        }
</style>