<template id="editRecipe">
    <div>
        <h2>Muuda retsepti</h2>
        <form v-on:submit="updateRecipe">
            <div class="form-group" style="margin:auto">
                <label for="edit-name">Nimi</label>
                <input class="form-control" id="edit-name" v-model="recipe.name" required/>
            </div>
            <div class="form-group">
                <label for="edit-description">Kirjeldus</label>
                <textarea class="form-control" id="edit-description" rows="3" v-model="recipe.description"></textarea>
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
            <button type="submit" class="btn btn-primary">Save</button>
            <a class="btn btn-default">
                <router-link to="/">Cancel</router-link>
            </a>
        </form>
    </div>
</template>

<script>
    import http from "../http-common";

    function findRecipe(id) {
        return recipes[findRecipeKey(id)];
    }

    function findRecipeKey(id) {
        for (let key = 0; key < recipes.length; key++) {
            if (recipes[key].id === id) {
                return key;
            }
        }
    }

    export default ({
        template: "#editRecipe",

        data: function () {
            return {recipe: findRecipe(this.$route.params.id)};
        },
        methods: {
            updateRecipe: function () {
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

    })

</script>


<style scoped>
    .submitform {
        margin: 30px;
        float: left;
        width: 80%
    }
</style>