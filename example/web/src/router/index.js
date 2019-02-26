import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import Recipes from '@/components/Recipes'
import AddRecipe from '@/components/AddRecipe'
import MyAccount from '@/components/MyAccount'
import Recipe from '@/components/Recipe';

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            name: 'Home',
            component: Home
        },
        {
            path: '/recipes',
            name: 'recipes',
            component: Recipes
            /*children: [
                {
                    path: "/recipe/:id",
                    name: "recipe-details",
                    component: Recipe,
                    props: true
                },

            ]*/
        },
        {
            path: '/recipe/:Rid',
            name: 'recipe',
            component: Recipe
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