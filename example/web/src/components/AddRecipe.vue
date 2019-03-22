<template >
    <div class="submitform">

        <div v-if="!submitted" style="font-size:20px">
            <h1 style="font-size:170%">Lisa uus retsept</h1>

            <div class="form-group">
                <label for="name">Nimi</label>
                <input type="text" class="form-control" id="name" v-model="recipe.name" name="name">
                <div  style="color:red;"> {{nameError}}</div>
            </div>


            <div class="form-group">
                <label for="description">Juhend</label>
                <textarea type="text" rows=4 style="overflow-y: scroll;" class="form-control" id="description" required v-model="recipe.description" name="description"></textarea>
                <div  style="color:red;"> {{desError}}</div>
            </div>
            <div class="form-group">
                <label for="materials">Materjalid</label>
                <textarea type="text" rows=3 style="overflow-y: scroll;" class="form-control" id="materials" required v-model="recipe.materials" name="materials"></textarea>
                <div  style="color:red;"> {{matError}}</div>
            </div>

            <div id="radiobuttons">

                <input type="radio" value="hommikusöök" v-model="recipe.category">
                <label>Hommikusöök</label>

                <input type="radio"  value="jook" v-model="recipe.category">
                <label>Jook</label>

                <input type="radio" value="kook" v-model="recipe.category">
                <label>Kook</label>

                <input type="radio" value="magustoit" v-model="recipe.category">
                <label>Magustoit</label>

                <input type="radio" value="pastatoit" v-model="recipe.category">
                <label>Pastatoit</label>

                <input type="radio" value="pirukad" v-model="recipe.category">
                <label>Pirukad</label>

                <input type="radio" value="praad" v-model="recipe.category">
                <label>Praad</label>

                <input type="radio" value="salat" v-model="recipe.category">
                <label>Salat</label>

                <input type="radio" value="supp" v-model="recipe.category">
                <label>Supp</label>

                <input type="radio" value="suupisted" v-model="recipe.category">
                <label>Suupisted</label>

                <input type="radio" value="tort" v-model="recipe.category">
                <label>Tort</label>

                <input type="radio" value="võileivatort" v-model="recipe.category">
                <label>Võileivatort</label>

                <input type="radio" value="vormiroog" v-model="recipe.category">
                <label>Vormiroog</label>

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
            <button class="btn btn-success" v-on:click="newRecipe">Uus retsept</button>
        </div>
    </div>
</template>

<script>
    import http from "../http-common";
    import Vue from 'vue';
    import VeeValidate from 'vee-validate';
    Vue.use(VeeValidate);

    export default {
        name: 'add-recipe',
        data() {
            errorMessage:null
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
                submitted: false,
                nameError:"",
                desError:"",
                matError:""
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
                if(!this.recipe.name){this.nameError="Lisa nimi" }
                if(this.recipe.name){this.nameError=""}
                 if(!this.recipe.description){this.desError="Lisa juhised" }
                 if(this.recipe.description){this.desError=""}
                 if(!this.recipe.materials){this.matError="Lisa materjalid" }
                 if(this.recipe.materials){this.matError=""}
                 if(this.recipe.name && this.recipe.description && this.recipe.materials){
                  http
                     .post("/recipe", data)
                     .then(response => {
                         this.recipe.id = response.data.id;

                     });

                     this.submitted = true;}

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