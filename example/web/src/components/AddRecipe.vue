<template>
    <div class="submitform">

        <div v-if="!submitted && !pic" style="font-size:20px">
            <h1 style="font-size:170%">Lisa uus retsept</h1>
            <div class="form-group">
                <label for="name">Nimi</label>
                <input type="text" class="form-control" id="name" v-model="recipe.name" name="name">
                <div class="err"> {{nameError}}</div>
            </div>
            <div class="form-group">
                <label for="description">Juhend</label>
                <textarea type="text" rows=4 style="overflow-y: scroll;" class="form-control" id="description" required
                          v-model="recipe.description" name="description"></textarea>
                <div class="err"> {{desError}}</div>
            </div>
            <div class="form-group">
                <label for="materials">Materjalid</label><br>

                <input type="text" class="small-input" id="materials" v-model="mat" name="materials" style="padding-left:10px;height:40px;width:235px;font-size:15px">
                <input type="number" min="0" class="small-input" style="padding-left:10px;height:40px" id="quantity" v-model="mat2" name="quantity">
                <select class="custom-select" v-model="mat3" style="width:75px; font-size:15px; max-height:40px; margin-left:10px; height: 37px">
                    <option  v-for="(measurement, index) in measurements" :key="index" :value="measurement">
                        {{measurement}}
                    </option>

                </select>
                <b-button variant="secondary" size="sm" style="width:75px;float:right;margin-left:5px" v-on:click="addMaterial()">Lisa</b-button>
                <div class="arraylist" v-for="(mat,m) in recipe.materials">
                    <div class="arraytext"><span style="text-align:left" class="mat"> {{mat}}</span></div>
                    <div class="arraybutton">
                        <b-button style="width:75px;float:right"
                                  variant="warning" size="sm" @click="removeMaterial(n)">Eemalda
                        </b-button>
                    </div>
                    <br>
                </div>
                <div class="err"> {{matError}}</div>
            </div>
            <div class="form-group">
                <label>Kategooria</label><br>
                <select class="custom-select" v-model="cat" style="width:415px">

                   <option  v-for="(category, index) in categories" :key="index" :value="category.name">
                       {{category.name}}
                   </option>

                </select>
                <b-button variant="secondary" size="sm" style="width:75px;float:right;margin-left:5px" v-on:click="addCategory()">Lisa</b-button>
                <div class="arraylist" v-for="(cat,n) in recipe.category">
                    <div class="arraytext"><span style="text-align:left" class="cat"> {{cat}}</span></div>
                    <div class="arraybutton">
                        <b-button style="width:75px;float:right"
                                  variant="warning" size="sm"
                                class="btn btn-warning btn-sm" @click="removeCategory(n)">Eemalda
                        </b-button>
                    </div>
                    <br>
                </div>
                <div class="err"> {{catError}}</div>
            </div>
            <br>
            <div class="form-group">
                <label for="portion">Portsjon</label>
                <input class="small-input" type="number" id="portion" min="1" required v-model="recipe.portion"
                       name="portion">
                <div class="err"> {{portionError}}</div>
                <br>
                <label for="price">Hind</label>
                <input class="small-input" type="number" id="price" min="1" v-model="recipe.price" name="price">
                <div class="err"> {{priceError}}</div>
                <br>
            </div>
            <button v-on:click="saveRecipe" class="btn btn-success">Salvesta</button>
        </div>
        <div v-else-if="submitted && !pic && this.recipe">
            <h4>Retsept lisatud!</h4><br>
            <h4>Lisa pilt</h4>
            <div>
                <b-form-file
                        v-on:change="onFileSelected"
                        placeholder="Choose a file..."
                        style="margin-top: 5px; width: 500px;"
                ></b-form-file>

            </div>
            <div id="preview">
                <img :src="previewImage" class="uploading-image" style="border-radius: 10px;"/>
            </div>
            <div>
                <b-button variant="success" @click="onUpload" id="save-btn">Salvesta pilt</b-button>
            </div>
        </div>
        <div v-else>

            <h4>Pilt lisatud!</h4>
            <button class="btn btn-success" v-on:click="newRecipe" style="margin-right: 5px">Uus retsept</button>

            <router-link to="/recipes">
                <button type="reset" class="btn btn-success">Kõik retseptid</button>
            </router-link>
        </div>
        <div>
            <router-view @refreshData="refreshList"></router-view>
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
            return {
                recipe: {
                    id: '',
                    name: "",
                    description: "",
                    materials: [],
                    category: [],
                    portion: 0,
                    price: 0,
                },
                user: {
                    userid: ''
                },

                submitted: false,

                pic: false,
                nameError: "",
                desError: "",
                matError: "",
                catError: "",
                portionError: "",
                priceError: "",
                mat: '',
                mat2: 0,
                mat3: '',
                cat: '',
                measurements:
                    [
                    "g", "kg", "sl", "tl", "dl", "ml", "tk"
                    ],
                categories: [],
                selectedFile: null,
                previewImage: null

            };
        },
        methods: {

            saveRecipe() {
                let data = {
                    id: this.recipe.id,
                    name: this.recipe.name,
                    description: this.recipe.description,
                    materials: this.recipe.materials.toString(),
                    category: this.recipe.category.toString(),
                    portion: this.recipe.portion,
                    price: this.recipe.price,
                    userid: this.user.id

                };

                this.nameError = (!this.recipe.name) ? "Lisa nimi" : "";
                this.desError = (!this.recipe.description) ? "Lisa juhised" : "";
                this.matError = (!data.materials) ? "Lisa materjalid" : "";
                this.catError = (!data.category) ? "Vali kategooria" : "";
                this.portionError = (!this.recipe.portion) ? "Lisa portjon" : "";
                this.priceError = (!this.recipe.price) ? "Lisa hind" : "";

                if (this.recipe.name
                    && this.recipe.description
                    && data.materials
                    && data.category
                    && this.recipe.portion
                    && this.recipe.price) {
                    this.submitted = true;
                    http
                        .post("/recipe", data)
                        .then(response => {
                            this.recipe.id = response.data;
                        });
                }

            },
            newRecipe() {
                this.submitted = false;
                this.recipe = {};
                window.location.reload();
            },
            addCategory() {
                var newCategory = this.cat;
                if (!newCategory) return;
                if (this.recipe.category.includes(newCategory)) return;
                this.recipe.category.push(newCategory);
                this.cat = '';
                newCategory = '';
            },
            removeCategory(x) {
                this.recipe.category.splice(x, 1);
                this.saveCategory();
            },
            saveCategory() {
            },
            addMaterial() {
                var newMaterial = " " + this.mat + " - " + this.mat2 + " " + this.mat3;
                if (!newMaterial) return;
                if (this.recipe.materials.includes(newMaterial)) return;
                this.recipe.materials.push(newMaterial);
                this.mat = '';
                newMaterial = '';
            },
            removeMaterial(x) {
                this.recipe.materials.splice(x, 1);
                this.saveMaterial();
            },
            saveMaterial() {
            },
            retrieveCategories() {
                http.get("/categories").then(response => {
                    this.categories = response.data;
                });
            },
            refreshList() {
                this.retrieveCategories();
            },
            onFileSelected(event) {
                this.selectedFile = event.target.files[0];
                const reader = new FileReader();
                reader.readAsDataURL(this.selectedFile);
                reader.onload = event => {
                    this.previewImage = event.target.result;
                }
            },
            onUpload() {
                const formData = new FormData();
                formData.append('image', this.selectedFile, this.selectedFile.name);
                http.post('/recipe/' + this.recipe.id + '/image', formData)
                    .then(this.recipe);
                this.pic = true;
            }
        },
        mounted() {
            http.get('/loggedIn').then(response => (this.user = response.data));
            this.retrieveCategories();
        }
    }
</script>


<style scoped>
    .submitform {
        margin: auto;
        width: 80%;
        align: center;
        color: #333
    }

    input, textarea, select {
        max-width: 500px;
        margin: auto;
        align: center;
        color: #333
    }

    .small-input {
        width: 75px;
        margin: auto;
        align: center;
        color: #333;
        margin-left: 10px;
        border-radius: 4px;
        border-style: solid;
        border-color: lightgrey;
        border-width: 1px;
        font-size: 15px;
        padding-left: 10px;
        height: 40px


    }

    .form-group {
        width: 500px;
        align: center;
        margin: auto;
        color: #333
    }

    .arraylist {
        margin-bottom: 5px
    }

    .arraytext {
        width: 75%;
        float: left;
        font-size: 16px
    }

    .arraybutton {
        width: 20%;
        float: right;
    }

    .err {
        color: red;
        font-size: 16px
    }


</style>