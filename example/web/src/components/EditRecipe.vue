<template id="editRecipe">
    <div>
        <div v-if="!submitted" style="font-size:20px">
            <h2>Muuda retsepti</h2>
            <div>
                <div class="form-group">
                    <label for="edit-name">Nimi</label>
                    <input class="form-control" type="text" id="edit-name" required v-model="recipe.name"/>
                </div>
                <div class="form-group">
                    <label for="edit-description">Juhend</label>
                    <textarea class="form-control" id="edit-description" rows="3"
                              v-model="recipe.description"></textarea>
                </div>

                <div class="form-group">
                    <label for="edit-materials">Materjalid</label>
                    <textarea class="form-control" id="edit-materials" rows="3" v-model="recipe.materials"></textarea>
                </div>

                <div class="form-group">
                    <input type="radio"  value="magustoit" v-model="recipe.category">
                    <label>Magustoit</label>

                    <input type="radio" value="supp" v-model="recipe.category">
                    <label>Supp</label>

                    <input type="radio" value="eelroog" v-model="recipe.category">
                    <label>Eelroog</label>
                </div>

                <div class="form-group">
                    <label for="edit-portion">Kogus<span class="glyphicon glyphicon-euro"></span></label>
                    <input class="small-input" type="number" id="edit-portion" v-model="recipe.portion"/>

                    <label for="edit-price">Hind<span class="glyphicon glyphicon-euro"></span></label>
                    <input class="small-input" type="number" id="edit-price" v-model="recipe.price"/>
                </div>

                <button @click="updateRecipe" class="btn btn-primary"><router-link :to="{
                            name: 'recipe',
                            params: { recipe: recipe, id: recipe.id }
                        }" style="color:whitesmoke">Salvesta muudatused</router-link></button>

                <a class="btn btn-default">
                        <router-link :to="{
                            name: 'recipe',
                            params: { recipe: recipe, id: recipe.id }
                        }">Loobu</router-link>
                </a>
            </div>
        </div>
        <div v-else>
            <h4>Muudetud!</h4>

        </div>
    </div>
</template>

<script>

    import http from "../http-common"

    export default {
        name: "editRecipe",
        props: ["recipe"],
        data() {
            return {
                recipe: {

                    id: this.recipe.id,
                    name: this.recipe.name,
                    description: this.recipe.description,
                    materials: this.recipe.materials,
                    category:this.recipe.category,
                    portion: this.recipe.portion,
                    price: this.recipe.price
                },
                submitted: false
            };
        },
        methods: {
            updateRecipe() {
                let data = {
                    name: this.recipe.name,
                    description: this.recipe.description,
                    materials: this.recipe.materials,
                    category:this.recipe.category,
                    portion: this.recipe.portion,
                    price: this.recipe.price
                };
                http
                    .put("/recipe/" + this.recipe.id, data)
                    .then(response => {
                        this.recipe.id = response.data.id;
                    });
                this.submitted = true;
            }
        }
    }

</script>


<style scoped>
    .submitform {
        margin: 30px;
        float: left;
        width: 80%
    }

    input, textarea {
        max-width: 500px;
        margin: auto;
    }

    .small-input {
        width: 75px;
        margin: 10px;
    }
</style>