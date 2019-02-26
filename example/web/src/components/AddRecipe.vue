<template>
    <div class="submitform">
        <div v-if="!submitted">
            <div class="form-group">
                <label for="name">Nimi</label>
                <input type="text" class="form-control" id="name" required v-model="recipe.name" name="name">
            </div>

            <div class="form-group">
                <label for="description">Kirjeldus</label>
                <input type="text" class="form-control" id="description" required v-model="recipe.description" name="description">
            </div>
            <div class="form-group">
                <label for="materials">Materjalid</label>
                <input type="text" class="form-control" id="materials" required v-model="recipe.materials" name="materials">
            </div>
            <div class="form-group">
                <label for="portion">Portsjon</label>
                <input type="number" class="form-control" id="portion" required v-model="recipe.portion" name="portion">
            </div>
            <div class="form-group">
                <label for="price">Hind</label>
                <input type="number" class="form-control" id="price" required v-model="recipe.price" name="price">
            </div>

            <button v-on:click="saveRecipe" class="btn btn-success">Salvesta retsept</button>
        </div>

        <div v-else>
            <h4>Lisatud!</h4>
            <button class="btn btn-success" v-on:click="newRecipe">Tagasi</button>
        </div>
    </div>
</template>

<script>
    import http from "../http-common";

    export default {
        name: 'add-recipe',
        data() {
            return {
                recipe:{
                    id:0,
                    name: "",
                    description: "",
                    materials: "",
                    portion: 0,
                    price: 0
                 },
                submitted: false
            };
        },
        methods: {
             saveRecipe() {
                let data = {
                    name: this.recipe.name,
                    description: this.recipe.description,
                    materials: this.recipe.materials,
                    portion: this.recipe.portion,
                    price: this.recipe.price
                };

                http
                    .post("/recipe", data)
                    .then(response => {
                        this.recipe.id = response.data.id;

                    });
                this.submitted = true;
            },
            newRecipe() {
                this.submitted = false;
                this.recipe = {};
            }
            /* eslint-enable no-console */
        }
    };

</script>


<style scoped>
    .submitform {
        max-width: 300px;
        margin: auto;
    }
</style>