<template>
    <div class="recipe">
        <div class="container">
            <div v-if="!submitted">
                <b-card style="background:rgba(255, 255, 255, 0.2); border:none; border-radius:25px;">
                    <h4>{{this.recipe.name}}</h4>

                    <div v-if="this.recipe" style="text-align:left">
                        <div>
                            <label>Juhend: </label> {{this.recipe.description}}
                        </div>
                        <div>
                            <label>Materjalid: </label> {{this.recipe.materials}}
                        </div>
                        <div>
                            <label>Portsjon: </label> {{this.recipe.portion}}
                        </div>
                        <div>
                            <label>Hind: </label> {{this.recipe.price}}
                        </div>
                        <div>
                            <label>Kategooria: </label>
                            {{this.recipe.category}}
                        </div>

                        <div id="recipeImage" if v-if="this.image !== null">
                            <img class="uploading-image" v-bind:src="this.image" alt="recipe's image"/>
                        </div>
                    </div>

                    <div v-if="this.recipe.user.id === this.user.id">
                        <router-link :to="{name: 'editRecipe', params: {recipe:recipe, id: recipe.id}}">
                            <b-button variant="outline-success" style="float:left;">
                                Muuda
                            </b-button>
                        </router-link>
                        <b-button variant="outline-warning" style="float:left; margin-left:5px"
                                  v-on:click="deleteRecipe()">
                            Kustuta
                        </b-button>

                    </div>
                </b-card>
            </div>
            <div v-else>
                <h4>Retsept on edukalt kustutatud!</h4>
                <router-link to="/recipes">
                    <button type="reset" class="btn btn-success">Kõik retseptid</button>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script>
    import http from "../http-common";
    import axios from "axios";

    export default {
        name: "recipe",
        props: ["recipe"],
        data() {
            return {
                submitted: false,
                image: this.showImage(),
                imgUrl: ''
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
            },
            showImage() {
                http.get('/recipe/' + this.recipe.id + '/recipeImage', {responseType: 'blob'})
                    .then(response => {
                        if (response.data.size > 0) {
                            let reader = new FileReader();
                            reader.readAsDataURL(response.data);
                            reader.onload = () => {
                                this.image = reader.result;
                            }
                        } else {
                            this.image = null;
                        }

                    })
            }
        },
        mounted() {
            axios.get('http://localhost:8080/api/loggedIn').then(response => (this.user = response.data))
        }
    }
</script>

<style scoped>
    a:hover, :link, :visited, :active {
        color: white;
        text-decoration: none;

    }

    label {
        min-width: 90px;
        margin: auto;
        font-weight: bold;
        margin-top: 5px;
    }

    .container {
        width: 70%;
        margin: auto;
    }

    #recipeImage {
        display: flex;
        justify-content: center;
        align-items: center;
    }

    #recipeImage img {
        max-width: 100%;
        max-height: 500px;
    }

</style>