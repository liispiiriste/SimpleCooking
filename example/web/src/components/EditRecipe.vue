<template id="editRecipe" xmlns:display="http://www.w3.org/1999/xhtml">
    <div>
        <h2>Muuda retsepti</h2>
        <div>
            <div class="form-group">
                <label for="edit-name">Nimi</label>
                <input class="form-control" type="text" id="edit-name" required v-model="recipe.name"/>
                <div class="err">
                    {{nameError}}
                </div>
            </div>

            <div class="form-group">
                <label for="edit-description">Juhend</label>
                <textarea class="form-control" id="edit-description" rows="3"
                          v-model="recipe.description"></textarea>
                {{this.recipe.description}}
                <div class="err">
                    {{desError}}
                </div>
            </div>

            <div class="form-group">
                <label for="edit-materials">Materjalid</label>
                <textarea class="form-control" id="edit-materials" rows="3" v-model="recipe.materials"></textarea>
                <div class="err">
                    {{matError}}
                </div>
            </div>

            <div class="form-group">
            <label>Kategooria</label><br>

                <select class="custom-select" v-model="recipe.category" style="width:500px">
                    <option v-for="(category, index) in categories" :key="index" :value="category.name">
                        {{category.name}}
                    </option>
                </select>
                <div class="err">
                    {{catError}}
                </div>
            </div>


            <div class="form-group">
                <label for="edit-portion">Kogus<span class="glyphicon glyphicon-euro"></span></label>
                <input class="small-input" type="number" id="edit-portion" v-model="recipe.portion"/>
                <div class="err">
                    {{portionError}}
                </div>

                <label for="edit-price">Hind<span class="glyphicon glyphicon-euro"></span></label>
                <input class="small-input" type="number" id="edit-price" v-model="recipe.price"/>
                <div class="err">
                    {{priceError}}
                </div>

            </div>
            <div v-if="!pic">
                <b-img center rounded width="400px" :src="this.image" style="border-radius: 10px;"></b-img>
            </div>
            <div id="preview" style="align-items: center">
                <img :src="this.previewImage">
            </div>
            <div>
                <b-form-file
                        v-on:change="onFileSelected"
                        placeholder="Choose a file..."
                        style="margin-top: 5px; width: 500px;"
                ></b-form-file>
            </div>

                <b-button @click="updateRecipe" variant="success">
                    <router-link :to="{
                                name: 'recipe',
                                params: { recipe: recipe, id: recipe.id, }
                            }" style="color:whitesmoke">Salvesta muudatused
                    </router-link>
                </b-button>

            <a class="btn btn-default">
                <router-link :to="{
                            name: 'recipe',
                            params: { recipe: recipe, id: recipe.id }
                        }">
                    <b-button variant="outline-warning">
                        Loobu
                    </b-button>
                </router-link>

            </a>
        </div>

    </div>
</template>

<script>

    import http from "../http-common"

    export default {
        name: "editRecipe",
        props: ["recipe"],
        data() {
            return {
                recipe: {
                    id: this.recipe.id,
                    name: this.recipe.name,
                    description: this.recipe.description,
                    materials: this.recipe.materials,
                    category: this.recipe.category,
                    portion: this.recipe.portion,
                    price: this.recipe.price,


                },
                image: this.showOldImage(),
                submitted: false,
                pic: false,
                selectedFile: null,
                previewImage: null,
                categories: [],
                nameError: "",
                desError: "",
                matError: "",
                catError: "",
                portionError: "",
                priceError: "",

            };
        },
        methods: {
            updateRecipe() {
                let data = {
                    name: this.recipe.name,
                    description: this.recipe.description,
                    materials: this.recipe.materials,
                    category: this.recipe.category,
                    portion: this.recipe.portion,
                    price: this.recipe.price,

                };
                this.nameError = (!this.recipe.name) ? "Lisa nimi" : "";
                this.desError = (this.recipe.description=='') ? "Lisa juhised" : "";
                this.matError = (!this.recipe.materials) ? "Lisa materjalid" : "";
                this.catError = (!this.recipe.category) ? "Vali kategooria" : "";
                this.portionError = (!this.recipe.portion) ? "Lisa portjon" : "";
                this.priceError = (!this.recipe.price) ? "Lisa hind" : "";

                if (!this.nameError
                    && !this.desError
                    && !this.matError
                    && !this.catError
                    && !this.portionError
                    && !this.priceError) {
                    http
                        .put("/recipe/" + this.recipe.id, data)
                        .then(response => {
                            this.recipe.id = response.data.id;
                        });
                    this.submitted = true;
                }
                },
            onFileSelected(event) {
                this.selectedFile = event.target.files[0];
                const reader = new FileReader();
                reader.readAsDataURL(this.selectedFile);
                reader.onload = event => {
                    this.previewImage = event.target.result;
                }
                this.onUpload();

            },
            onUpload() {
                const formData = new FormData();
                formData.append('image', this.selectedFile, this.selectedFile.name);
                http.post('/recipe/' + this.recipe.id + '/image', formData)
                    .then(console.log("lisatud"));
                this.pic = true;
            },
            showOldImage() {
                http.get('/recipe/' + this.recipe.id + '/recipeImage', {responseType: 'blob'})
                    .then(response => {
                        if (response.data.size > 0) {
                            let reader = new FileReader();
                            reader.readAsDataURL(response.data);
                            reader.onload = () => {
                                this.image = reader.result;
                            }
                        } else {
                            this.image = null;
                        }
                    })
            },
            retrieveCategories() {
                http.get("/categories").then(response => {
                    this.categories = response.data;
                });
            },


        },
        mounted() {
           this.retrieveCategories();
        }
    }

</script>


<style scoped>
    input, textarea {
        max-width: 500px;
        margin: auto;
    }

    .small-input {
        width: 75px;
        margin: 10px;
        align: center;
    }

    .form-group {
        width: 500px;
        align: center;
        margin: auto;
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