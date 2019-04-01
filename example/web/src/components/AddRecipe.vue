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
                <label for="materials">Materjalid</label><br>
                <input type="text" class="small-input" id="materials" v-model="mat" name="materials" style="width:200px">
                <input type="number" min="0" class="small-input" id="quantity" v-model="mat2" name="quantity">
                <select class="small-input" v-model="mat3">
                    <option value="g">g</option>
                    <option value="kg">kg</option>
                    <option value="sl">sl</option>
                    <option value="tl">tl</option>
                    <option value="l">l</option>
                    <option value="dl">dl</option>
                    <option value="ml">ml</option>
                    <option value="tk">tk</option>
                </select>
                <button class="small-input" v-on:click="addMaterial()">Lisa</button>
                <div v-for="(mat,m) in recipe.materials">
                    <span class="mat"> {{mat}}</span>
                </div>
                <div  style="color:red;"> {{matError}}</div>
            </div>
            <div class="form-group">

                <label>Kategooria</label><br>
                <select class="custom-select" v-model="cat" style="width:300px">

                    <option value="hommikusöök">Hommikusöök</option>
                    <option value="jook">Jook</option>
                    <option value="kook">Kook</option>
                    <option value="magustoit">Magustoit</option>
                    <option value="pastatoit">Pastatoit</option>
                    <option value="pirukad">Pirukad</option>
                    <option value="praad">Praad</option>
                    <option value="salat">Salat</option>
                    <option value="suupisted">Suupisted</option>
                    <option value="supp">Supp</option>
                    <option value="tort">Tort</option>
                    <option value="võileivatort">Võileivatort</option>
                    <option value="vormiroog">Vormiroog</option>
                    <option value="muu">Muu</option>
                </select>
                <button v-on:click="addCategory()" class="small-input">Lisa</button>
                <div v-for="(cat,n) in recipe.category">
                    <span class="cat"> {{cat}}</span>
                </div>
                <div  style="color:red;"> {{catError}}</div>
            </div><br>
            <div class="form-group">
                <label for="portion">Portsjon</label>
                <input class="small-input" type="number" id="portion" min="1" required v-model="recipe.portion" name="portion">
                <div  style="color:red;"> {{portionError}}</div><br>
                <label for="price">Hind</label>
                <input class="small-input" type="number" id="price"  min="1" v-model="recipe.price" name="price">
                <div  style="color:red;"> {{priceError}}</div><br>
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
    import $store from "../store/modules/auth";
    import axios from "axios";
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
                    materials: [],
                    category: [],
                    portion: 0,
                    price: 0,

                 },
                user: {
                    id: ''
                },
                submitted: false,
                nameError:"",
                desError:"",
                matError:"",
                catError:"",
                portionError:"",
                priceError:"",
                mat:'',
                mat2:0,
                mat3:'',
                cat:''
            };
        },
        methods: {

             saveRecipe() {

                let data = {
                    name: this.recipe.name,
                    description: this.recipe.description,
                    materials: this.recipe.materials.toString(),
                    category: this.recipe.category.toString(),
                    portion: this.recipe.portion,
                    price: this.recipe.price,
                    id: this.user.id

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
                         console.log(data)
                         console.log("lisatud", response)

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
                 if(!newCategory) {return}

                 this.recipe.category.push(newCategory);
                 this.cat='';
                 newCategory='';
            },
            removeCategory(x){
                 this.category.splice(x,1);
                 this.saveCategory();
            },
            saveCategory(){

            },
            addMaterial(){
                var newMaterial = " " + this.mat + " - " + this.mat2 +  " " + this.mat3;
                if(!newMaterial) {return}

                this.recipe.materials.push(newMaterial);
                this.mat='';
                newMaterial='';
            },
            removeMaterial(x){
                this.materials.splice(x,1);
                this.saveMaterial();
            },
            saveMaterial(){

            },


        },
        mounted() {
            axios.get('http://localhost:8080/api/loggedIn').then(response => (this.user = response.data));
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
        color: #333
    }
    .small-input{
        width:75px;
        margin:auto;
        align: center;
        color: #333;
        margin-left: 10px;
        border-radius: 4px;
    }
    .form-group{
        width: 500px;
        align: center;
        margin: auto;
        color: #333
    }
</style>