<template id="editRecipe">
    <div>
        <div v-if="!submitted" style="font-size:20px">
            <h2>Muuda retsepti</h2>
            <div>
                <div class="form-group">
                    <label for="edit-name">Nimi</label>
                    <input type="text" id="edit-name" required v-model="recipe.name"/>
                </div>
                <div class="form-group">
                    <label for="edit-description">Kirjeldus</label>
                    <textarea class="form-control" id="edit-description" rows="3"
                              v-model="recipe.description"></textarea>
                </div>

                <div class="form-group">
                    <label for="edit-materials">Materjalid</label>
                    <textarea class="form-control" id="edit-materials" rows="3" v-model="recipe.materials"></textarea>
                </div>

                <div class="form-group">
                    <label for="edit-portion">Kogus<span class="glyphicon glyphicon-euro"></span></label>
                    <input type="number" class="form-control" id="edit-portion" v-model="recipe.portion"/>
                </div>
                <div class="form-group">
                    <label for="edit-price">Hind<span class="glyphicon glyphicon-euro"></span></label>
                    <input type="number" class="form-control" id="edit-price" v-model="recipe.price"/>
                </div>

                <button v-on:click="updateRecipe" class="btn btn-primary">Salvesta muudatused</button>

                <a class="btn btn-default">
                    <router-link to="/">Loobu</router-link>
                </a>
            </div>
        </div>
        <div v-else>
            <h4>Muudetud!</h4>

        </div>
    </div>
</template>

<script>
    /*function findRecipe(id) {
        return recipes[findRecipeKey(id)];
    }

    function findRecipeKey(id) {
        for (let key = 0; key < recipes.length; key++) {
            if (recipes[key].id === id) {
                return key;
            }
        }
    }*/
import http from "../http-common"
    export default ({
        name: "editRecipe",
        props: ["recipe"],


        data() {
            return {
                recipe: {
                    id: this.recipe.id,
                    name: this.recipe.name,
                    description: this.recipe.description,
                    materials: this.recipe.materials,
                    portion: this.recipe.portion,
                    price: this.recipe.price
                },
                submitted: false
            };
        },
        updateRecipe: function() {

            let data = {
                id: this.recipe.id,
                name: this.recipe.name,
                description: document.getElementById("edit-description"),
                materials: this.recipe.materials,
                portion: this.recipe.portion,
                price: this.recipe.price
            };

            http
                .post("/recipe", data)
                .then(response => {
                    this.recipe.id = response.data.id;
                    this.$emit("refreshData");
                    this.$router.push('/edit');


                });
            this.submitted = true;
        },
        /*   methods: {
               updateRecipe() {
                   let recipe = this.recipe;
                   recipes[findRecipeKey(recipe.id)] = {
                       id: recipe.id,
                       name: recipe.name,
                       description: recipe.description,
                       materials: recipe.materials,
                       portion: recipe.portion,
                       price: recipe.price
                   };
                   this.$router.push('/');
               }

           }
   */
    })

</script>


<style scoped>
    .submitform {
        margin: 30px;
        float: left;
        width: 80%
    }
</style>