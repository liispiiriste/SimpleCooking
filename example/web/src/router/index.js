import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '@/components/Home'
import Recipes from '@/components/Recipes'
import AddRecipe from '@/components/AddRecipe'
import MyAccount from '@/components/MyAccount'
import Recipe from '@/components/Recipe';
import EditRecipe from "@/components/EditRecipe";
import login from "@/components/Login";
import store from "@/store";


Vue.use(VueRouter);

const router = new VueRouter({
    routes: [
        {
            path: '/',
            name: 'Home',
            component: Home
        },
        {
            path: '/login',
            name: 'login',
            component: login
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
            path: '/edit',
            component: EditRecipe,
            name: 'editRecipe',
            props:true
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
        },


    ]
});

router.beforeEach((to, from, next) => {
    const publicPages = ['/login'];
    const authRequired = !publicPages.includes(to.path);
    const loggedIn = store.getters.isAuthenticated;

    if (authRequired && !loggedIn) {
        return next('/login');
    }
    next();
});
export default router;