<template >
    <div class="submitform">

        <div v-if="!submitted" style="font-size:20px">
            <h1 style="font-size:170%">Lisa uus retsept</h1>
            <div class="form-group">
                <label for="name">Nimi</label>
                <input type="text" class="form-control" id="name" required v-model="recipe.name" name="name">
            </div>

            <div class="form-group">
                <label for="description">Juhend</label>
                <textarea type="text" rows=4 style="overflow-y: scroll;" class="form-control" id="description" required v-model="recipe.description" name="description"></textarea>
            </div>
            <div class="form-group">
                <label for="materials">Materjalid</label>
                <textarea type="text" rows=3 style="overflow-y: scroll;" class="form-control" id="materials" required v-model="recipe.materials" name="materials"></textarea>
            </div>

            <div id="radiobuttons">
                <input type="radio"  value="magustoit" v-model="recipe.category">
                <label>Magustoit</label>

                <input type="radio" value="supp" v-model="recipe.category">
                <label>Supp</label>

                <input type="radio" value="eelroog" v-model="recipe.category">
                <label>Eelroog</label>

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
                    category: "",
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
                    category: this.recipe.category,
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

        }
    };
</script>


<style scoped>
    .submitform {
        margin: auto;
        width: 80%;
        align: center;
        color: #333
    }
    input, textarea{
        max-width:500px;
        margin:auto;
        align: center;
    }
    .small-input{
        width:75px;
        margin:auto;
        align: center;
    }
</style>