<template>
    <div id="recipeapp">
        <div class="AddRecipe">
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
    </div>
</template>

<script>
    import Vue from "vue";

    export default {
        name: 'AddRecipe',
        data() {
            return {
                title: 'AddRecipe'
            }
        }
    }
    window.onload = function () {
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
                },*/
                saveRecipe() {
                    const parsed = JSON.stringify(this.recipes);
                    localStorage.setItem('recipes', parsed);
                }
            }
        })
    }

</script>


<style scoped>
    input {
        width: 50%;
        margin: 20px 0 0;
    }

    form {
        float: left;
    }
</style>