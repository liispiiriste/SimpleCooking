<template>
   <!-- <div class="AddRecipe">
        <div v-if="!submitted">
            <h2 style="float:left">Lisa uus retsept</h2>
            <form action="http://localhost:9000/#/Recipes" method="post">
                Nimi: <input v-model="name" type="text" name="Name" id="user_input"><br>
                Kirjeldus: <input v-model="description" type="text" name="Description"><br>
                Materjalid: <input v-model="materials" type="text" name="Materials"><br>
                Portsjon: <input v-model="portion" type="number" name="Portion" min="1"><br>
                Hind: <input v-model="price" type="number" name="Price" min="1"><br>
                <button @click="addRecipe">Salvesta</button>
             </form>
        </div>
    </div>-->
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
            <button class="btn btn-success" v-on:click="Recipes">Tagasi</button>
        </div>
    </div>
</template>

<script>
    import http from "../http-common";

    export default {
        name: 'AddRecipe',
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
                var data = {
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
                        console.log(response.data);
                    })
                    .catch(e => {
                        console.log(e);
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
  /*  window.onload = function () {
        new Vue({
            el: '#recipeapp',
            data: {
                recipes: [],
                name: null,
                description: null,
                materials: null,
                portion: null,
                price: null
            },
            mounted() {
                if (localStorage.getItem('recipes')) {
                    try {
                        this.recipes = JSON.parse(localStorage.getItem('recipes'));
                    } catch (e) {
                        localStorage.removeItem('recipes');
                    }
                }
            },
            methods: {
                addRecipe() {
                    // ensure they actually typed something
                    if (!this.name
                        || !this.description
                        || !this.materials
                        || !this.portion
                        || !this.price) {
                        return;
                    }

                    this.recipes.push(this.name);
                    this.recipes.push(this.description);
                    this.recipes.push(this.materials);
                    this.recipes.push(this.portion);
                    this.recipes.push(this.price);
                    this.name = '';
                    this.description = '';
                    this.materials = '';
                    this.portion = '';
                    this.price = '';
                    this.saveRecipe();
                },
                /*removeRecipe(x) {
                    this.recipes.splice(x, 1);
                    this.saveRecipe();
                },
                saveRecipe() {
                    const parsed = JSON.stringify(this.recipes);
                    localStorage.setItem('recipes', parsed);
                }
            }
        })
    } */

</script>


<style scoped>
    .submitform {
        max-width: 300px;
        margin: auto;
    }
</style>