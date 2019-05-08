<template>
<div id="search" >

    <div class="search-wrapper" >
        <input type="text" v-model="searchStr" placeholder="Otsi retsepte"/>

    </div>

    <div class="wrapper" >
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

        ul {
            list-style-type: none;
            margin: 0;
            -webkit-columns: 2;
            -moz-columns: 2;
            columns: 2;
            padding-left: 0;

        }
        ul li {
            list-style-position: inside;
            -webkit-column-break-inside: avoid;
            page-break-inside: avoid;
            break-inside: avoid;
        }
        li {
            font-family: "Times New Roman", Times, serif;
            font-size: 20px;
            border-bottom: 1px solid #ccc;
        }

        li:last-child {
            border: none;
        }

        li a {
            text-decoration: none;
            color: #000;
            display: block;
            width: auto;
            font-family: "Times New Roman", Times, serif;
            font-size: 17px;
        }

        li a:hover {
            background: rgba(255, 255, 255, 0.5);
        }


</style>