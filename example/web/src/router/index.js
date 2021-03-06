import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '@/components/Home'
import Recipes from '@/components/Recipes'
import AddRecipe from '@/components/AddRecipe'
import MyAccount from '@/components/MyAccount'
import Recipe from '@/components/Recipe';

import EditRecipe from "@/components/EditRecipe";
import store from "@/store";

import Register from "@/components/Register";
import Login from "../components/Login";
import Search from "../components/Search";


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
            component: Login
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
            props: true
        },
        {
            path: '/edit',
            component: EditRecipe,
            name: 'editRecipe',
            props: true
        },
        {
            path: '/add',
            name: 'AddRecipe',
            component: AddRecipe,
        },
        {
            path: '/MyAccount',
            name: 'MyAccount',
            component: MyAccount,
            props: true
        },
        {
            path: '/register',
            name: 'Register',
            component: Register
        },

        {
            path: '/search',
            name: 'Search',
            component: Search
        },

        // otherwise redirect to home
        {
            path: '*',
            redirect: '/login'
        }

    ]
});

router.beforeEach((to, from, next) => {
    const publicPages = ['/login', '/register'];
    const authRequired = !publicPages.includes(to.path);
    const loggedIn = store.getters.isAuthenticated;

    if (authRequired && !loggedIn) {
        return next('/login');
    } else if (authRequired && loggedIn) {
        next();
    } else {
        next();
    }
});
export default router;