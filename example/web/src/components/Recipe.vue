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
                        
                        <!--<div id="recipeImage">
                        <img :src="showImage()" alt="recipe's image">-->
                    </div>


                    <router-link :to="{name: 'editRecipe', params: {recipe:recipe, id: recipe.id}}">
                    <b-button variant="outline-success" style="float:left;">
                        Muuda
                    </b-button></router-link>
                    <b-button variant="outline-warning" style="float:left; margin-left:5px" v-on:click="deleteRecipe()">
                        Kustuta
                    </b-button>
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
        name: "Recipe",

        props: ["recipe"],
        data() {
            return {
                submitted: false,
                image: {}
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
            showImage(event) {
                http.get('/recipe/' + this.recipe.id + '/recipeImage')
                    .then(response => (this.image = response.data));
                this.image = event.target.files[0];
                console.log(this.image);
                const reader = new FileReader();

                reader.onload = event => {
                    this.image = event.target.result;
                    this.$refs.cropper.replace(this.image);
                }
                console.log(this.recipe.id);

            },
            mounted() {
                this.showImage();
            }
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
    #recipeImage{
        display: flex;
        justify-content: center;
        align-items: center;
    }
    #recipeImage img {
        max-width: 100%;
        max-height: 500px;
    }

</style>