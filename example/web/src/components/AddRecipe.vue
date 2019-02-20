<template>
    <div class="AddRecipe">
        <h2 style="float:left">Lisa uus retsept</h2>
        <form action="http://localhost:9000/#/Recipes" method="get">
            Nimi: <input  type="text" name="Name"><br>
            Kirjeldus: <input type="text" name="Description"><br>
            Materjalid: <input type="text" name="Materials"><br>
            Portsjon: <input type="number" name="Portion" min="1"><br>
            Hind: <input type="number" name="Price" min="1"><br>
            <button @click="persist">Salvesta</button>
        </form>
    </div>
</template>
<!--
<div id="app">
    <h2>Retseptid</h2>
    <div v-for="(recipe, n) in recipes">
        <p>
            <span class="recipe">{{ recipe }}</span>
            <button @click="removeRecipe(n)">Kustuta retsept</button>
        </p>
    </div>

    <p>
        <input v-model="newRecipe">
        <button @click="addRecipe">Lisa retsept</button>
    </p>
</div> -->
<script>
    export default{
        name:'AddRecipe',
        data (){
            return{
                title:'AddRecipe'
            }
        }
    }
  /*  const app = new Vue({
        el: '#app',
        data: {
            recipes: [],
            newRecipe: null
        },
        mounted() {
            if (localStorage.getItem('recipes')) {
                try {
                    this.recipes = JSON.parse(localStorage.getItem('recipes'));
                } catch(e) {
                    localStorage.removeItem('recipes');
                }
            }
        },
        methods: {
            addRecipe() {
                // ensure they actually typed something
                if (!this.newRecipe) {
                    return;
                }

                this.recipes.push(this.newRecipe);
                this.newRecipe = '';
                this.saveRecipe();
            },
            removeRecipe(x) {
                this.recipes.splice(x, 1);
                this.saveRecipe();
            },
            saveRecipe() {
                const parsed = JSON.stringify(this.recipes);
                localStorage.setItem('recipes', parsed);
            }
        }
    })*/

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