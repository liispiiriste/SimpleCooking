<template >
    <div class="submitform" style="color: #333;">
           <h1>Lisa uus retsept</h1>
        <div v-if="!submitted" style="font-size:20px">
            <div class="form-group">
                <label for="name">Nimi</label>
                <input type="text" class="form-control" id="name" required v-model="recipe.name" name="name">
            </div>

            <div class="form-group">
                <label for="description">Kirjeldus</label>
                <textarea type="text" rows=4 style="overflow-y: scroll;" class="form-control" id="description" required v-model="recipe.description" name="description"></textarea>
            </div>
            <div class="form-group">
                <label for="materials">Materjalid</label>
                <textarea type="text" rows=3 style="overflow-y: scroll;" class="form-control" id="materials" required v-model="recipe.materials" name="materials"></textarea>
            </div>
            <div class="form-group">
                <label for="portion">Portsjon</label>
                <input class="small-input" type="number" id="portion" min="1" required v-model="recipe.portion" name="portion">
                <label for="price">Hind</label>
                <input class="small-input" type="number" id="price"  min="1" v-model="recipe.price" name="price">
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
        margin: 30px;
        float: left;
        width: 80%
    }
    input, textarea{
        max-width:500px;
        margin:auto;
    }
    .small-input{
        width:75px;
        margin:10px;
    }
</style>