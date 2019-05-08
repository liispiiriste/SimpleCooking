<template>
    <div class="recipe">
        <div class="container" v-if="!submitted">
            <div v-if="!submitted">
                <b-card v-bind:title="this.recipe.name"
                        style="background:rgba(255, 255, 255, 0.2); border:none; border-radius:25px;">
                    <div class="row">

                        <div class="col-sm-6">
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
                                <div>
                                    <label>Autor: </label>
                                    {{this.recipe.user.username}}
                                </div>

                            </div>
                        </div>

                        <div class="col-sm-6">

                            <div id="recipeImage" v-show="this.image !== null">
                                <b-img class="uploading-image" v-bind:src="this.image" alt="recipe's image"/>

                            </div>
                        </div>

                    </div>


                    <div v-if="this.recipe.user.id === this.user.id">
                        <router-link :to="{name: 'editRecipe', params: {recipe:recipe, id: recipe.id}}">
                            <b-button variant="outline-info" style="float:left; margin-top: 5px">
                                Muuda
                            </b-button>
                        </router-link>
                        <b-button variant="outline-warning" style="float:left; margin-left:5px; margin-top: 5px"
                                  v-on:click="deleteRecipe()">
                            Kustuta
                        </b-button>
                    </div>

                    <div v-else-if="!isFavourite(this.recipe.id) && !this.isFav">
                        <b-button variant="danger" style="float:left; background:transparent; margin-left:5px; margin-top: 5px; border:transparent;"
                                  v-on:click="addFavourites()" >
                            <img src="@/assets/heart.png" alt="" style="width:100px">
                        </b-button>
                    </div>
                    <div v-else><b-button style="float:left; background:transparent; margin-left:5px; margin-top: 5px; border:transparent;" deactive>
                        <img src="@/assets/heart3.png" alt="" style="width:100px;float:left"></b-button>

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

    export default {
        name: "recipe",
        props: ["recipe"],
        data() {
            return {
                submitted: false,
                image: this.showImage(),
                imgUrl: '',
                user: [],
                isFav: false,
                favRecipes: []
            };
        },
        methods: {
            addFavourites() {
                let data = {
                    recipe_id: this.recipe.id,
                    user_id: this.user.id
                };
                http
                    .post("/recipe/" + this.recipe.id + "/favourite/" + this.user.id, data)
                    .then(response => {
                        this.recipe.id = response.data;
                    });
                this.isFav = true;
                console.log("added to favs")
            },
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
            },
            isFavourite(id) {
                for (var i = 0; i < this.favRecipes.length; i++) {
                    if (this.favRecipes[i].id === id) {
                        return true;
                    }
                }
                return false;
            }

        },
        mounted() {
            http.get('/loggedIn').then(response => (this.user = response.data))
            http.get("/favourite").then(response => (this.favRecipes = response.data));


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
        margin-right: 8px;
        font-family: "Times New Roman", Times, serif;
        font-size: 18px;
    }

    .container {
        width: 70%;
        margin: auto;
    }

</style>