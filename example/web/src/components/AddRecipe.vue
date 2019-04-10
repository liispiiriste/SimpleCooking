<template >
    <div class="submitform">

        <div v-if="!submitted" style="font-size:20px">
            <h1 style="font-size:170%">Lisa uus retsept</h1>
            <div class="form-group">
                <label for="name">Nimi</label>
                <input type="text" class="form-control" id="name" v-model="recipe.name" name="name">

                <div  class="err"> {{nameError}}</div>

            </div>
            <div class="form-group">
                <label for="description">Juhend</label>
                <textarea type="text" rows=4 style="overflow-y: scroll;" class="form-control" id="description" required v-model="recipe.description" name="description"></textarea>
                <div class="err"> {{desError}}</div>

            </div>
            <div class="form-group">
                <label for="materials">Materjalid</label><br>
                <input type="text" class="small-input" id="materials" v-model="mat" name="materials" style="padding-left:10px;height:40px;width:235px;font-size:15px">
                <input type="number" min="0" class="small-input" style="padding-left:10px;height:40px" id="quantity" v-model="mat2" name="quantity">
                <select class="custom-select" v-model="mat3" style="width:75px; font-size:15px; max-height:40px; margin-left:10px; height: 37px">
                    <option value="g">g</option>
                    <option value="kg">kg</option>
                    <option value="sl">sl</option>
                    <option value="tl">tl</option>
                    <option value="l">l</option>
                    <option value="dl">dl</option>
                    <option value="ml">ml</option>
                    <option value="tk">tk</option>
                </select>

                <button style="margin-bottom:5px" class="small-input" v-on:click="addMaterial()">Lisa</button>
                <div class="arraylist" v-for="(mat,m) in recipe.materials">
                    <div class="arraytext"><span style="text-align:left" class="mat"> {{mat}}</span></div>
                    <div class="arraybutton"><button style="float:right; background-color:#fff87c; border-color:#f4f09c; color:#666" class="btn btn-warning btn-sm" @click="removeMaterial(n)">Eemalda</button></div><br>
                </div>
                <div class="err"> {{matError}}</div>

            </div>
            <div class="form-group">

                <label>Kategooria</label><br>
                <select class="custom-select" v-model="cat" style="width:415px">

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
                <button style="margin-bottom:5px" v-on:click="addCategory()" class="small-input">Lisa</button>
                <div class="arraylist" v-for="(cat,n) in recipe.category">
                    <div class="arraytext"><span style="text-align:left" class="cat"> {{cat}}</span></div>
                    <div class="arraybutton"><button style="float:right; background-color:#fff87c; border-color:#f4f09c; color:#666" class="btn btn-warning btn-sm" @click="removeCategory(n)">Eemalda</button></div><br>
                </div>

                <div class="err"> {{catError}}</div>

            </div><br>
            <div class="form-group">
                <label for="portion">Portsjon</label>
                <input class="small-input" type="number" id="portion" min="1" required v-model="recipe.portion" name="portion">
                <div class="err"> {{portionError}}</div><br>
                <label for="price">Hind</label>
                <input class="small-input" type="number" id="price"  min="1" v-model="recipe.price" name="price">

                <div class="err"> {{priceError}}</div><br>

            </div>

            <button v-on:click="saveRecipe" class="btn btn-success">Salvesta retsept</button>

        </div>

        <div v-else>
            <h4>Lisatud!</h4>
            <button class="btn btn-success" v-on:click="newRecipe">Uus retsept</button>
            <router-link to="/recipes">
                <button type="reset" class="btn btn-success" >Kõik retseptid</button>
            </router-link>
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
                    id: this.recipe.id

                };
                if(!this.recipe.name){this.nameError="Lisa nimi" }
                if(this.recipe.name){this.nameError=""}
                 if(!this.recipe.description){this.desError="Lisa juhised" }
                 if(this.recipe.description){this.desError=""}
                 if(!data.materials){this.matError="Lisa materjalid" }
                 if(data.materials){this.matError=""}
                 if(!data.category){this.catError="Vali kategooria" }
                 if(data.category){this.catError=""}
                 if(!this.recipe.portion){this.portionError="Lisa portsjon"}
                 if(this.recipe.portion) {this.portionError=""}
                 if(!this.recipe.price){this.priceError="Lisa hind"}
                 if(this.recipe.price) {this.priceError=""}
                 if(this.recipe.name && this.recipe.description && data.materials && data.category && this.recipe.portion
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
                 if(!newCategory) {return};
                 if(this.recipe.category.includes(newCategory)){return}
                 this.recipe.category.push(newCategory);
                 this.cat='';
                 newCategory='';
            },
            removeCategory(x){
                 this.recipe.category.splice(x,1);
                 this.saveCategory();
            },
            saveCategory(){

            },
            addMaterial(){
                var newMaterial = " " + this.mat + " - " + this.mat2 +  " " + this.mat3;
                if(!newMaterial) {return}
                if(this.recipe.materials.includes(newMaterial)){return}

                this.recipe.materials.push(newMaterial);
                this.mat='';
                newMaterial='';
            },
            removeMaterial(x){
                this.recipe.materials.splice(x,1);
                this.saveMaterial();
            },
            saveMaterial(){

            }
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
        border-style: solid;
        border-color: lightgrey;
        border-width: 1px;
        font-size:15px;
        padding-left:10px;
        height:40px


    }
    .form-group{
        width: 500px;
        align: center;
        margin: auto;
        color: #333
    }
    .arraylist{
        margin-bottom:5px
    }
    .arraytext{
        width:75%;
        float:left;
        font-size:16px
    }
    .arraybutton{
        width:20%;
        float:right;
    }
    .err{
        color:red;
        font-size:16px
    }
</style>