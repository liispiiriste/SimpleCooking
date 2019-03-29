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
                <input type="text" class="form-control" id="materials" v-model="recipe.materials" name="materials">

                <div  style="color:red;"> {{matError}}</div>
            </div>
            <div class="form-group">

                <label>Kategooria</label>
                <select class="custom-select" v-model="cat">

                    <option value="hommikusöök">Hommikusöök</option>
                    <option value="jook">Jook</option>
                    <option value="kook">Kook</option>
                    <option value="magustoit">Magustoit</option>
                    <option value="pastatoit">Pastatoit</option>
                    <option value="pirukad">Pirukad</option>
                    <option value="praad">Praad</option>
                    <option value="salat">Salat</option>
                    <option value="supp">Suupisted</option>
                    <option value="tort">Tort</option>
                    <option value="võileivatort">Võileivatort</option>
                    <option value="vormiroog">Vormiroog</option>
                    <option value="muu">Muu</option>
                </select>
                <button v-on:click="addCategory()">Lisa</button>
                <div v-for="(cat,n) in recipe.category">
                    <span class="cat"> {{cat}}</span>
                </div>
                <div  style="color:red;"> {{catError}}</div>
            </div>
                        <div class="form-group">
                <label for="portion">Portsjon</label>
                <input class="small-input" type="number" id="portion" min="1" required v-model="recipe.portion" name="portion">
                <div  style="color:red;"> {{portionError}}</div>
                <label for="price">Hind</label>
                <input class="small-input" type="number" id="price"  min="1" v-model="recipe.price" name="price">
                <div  style="color:red;"> {{priceError}}</div>
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
                    category: [],
                    portion: 0,
                    price: 0
                 },
                submitted: false,
                nameError:"",
                desError:"",
                matError:"",
                catError:"",
                portionError:"",
                priceError:"",
                cat:''
            };
        },
        methods: {

             saveRecipe() {

                let data = {
                    name: this.recipe.name,
                    description: this.recipe.description,
                    materials: this.recipe.materials,
                    category: this.recipe.category.toString(),
                    portion: this.recipe.portion,
                    price: this.recipe.price
                };
                if(!this.recipe.name){this.nameError="Lisa nimi" }
                if(this.recipe.name){this.nameError=""}
                 if(!this.recipe.description){this.desError="Lisa juhised" }
                 if(this.recipe.description){this.desError=""}
                 if(!this.recipe.materials){this.matError="Lisa materjalid" }
                 if(this.recipe.materials){this.matError=""}
                 if(!data.category){this.catError="Vali kategooria" }
                 if(data.category){this.catError=""}
                 if(!this.recipe.portion){this.portionError="Lisa portsjon"}
                 if(this.recipe.portion) {this.portionError=""}
                 if(!this.recipe.price){this.priceError="Lisa hind"}
                 if(this.recipe.price) {this.priceError=""}
                 if(this.recipe.name && this.recipe.description && this.recipe.materials && this.recipe.category && this.recipe.portion
                 && this.recipe.price){
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
                window.location.reload();
            },
            addCategory(){
                 var newCategory = this.cat;
                 if(!newCategory) {return};

                 this.recipe.category.push(newCategory);
                 this.cat='';
                 newCategory='';
            },
            removeCategory(x){
                 this.category.splice(x,1);
                 this.saveCategory();
            },
            saveCategory(){

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
    input, textarea, select{
        max-width:500px;
        margin:auto;
        align: center;
    }
    .small-input{
        width:75px;
        margin:auto;
        align: center;
    }
    .form-group{
        width: 500px;
        align: center;
        margin: auto;
    }
</style>