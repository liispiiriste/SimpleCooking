import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import Recipes from '@/components/Recipes'
import AddRecipe from '@/components/AddRecipe'
import MyAccount from '@/components/MyAccount'
import Recipe from '@/components/Recipe';
import EditRecipe from "../components/EditRecipe";

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            name: 'Home',
            component: Home
        },
        {
            path: '/home',
            name: 'Home',
            component: Home
        },
        {
            path: '/recipes',
            name: 'recipes',
            component: Recipes

        },
        {
            path: '/recipe',
            name: 'recipe',
            component: Recipe,
            props:true
        },
        {
            path: '/recipe/:id/edit',
            component: EditRecipe,
            name: 'editRecipe',
        },
        {
            path: '/add',
            name: 'AddRecipe',
            component: AddRecipe
        },
        {
            path: '/MyAccount',
            name: 'MyAccount',
            component: MyAccount
        }
    ]
});