import Vue from "vue"
import VueRouter from "vue-router"
import SignUp from '../view/SignUp.vue'
import Login from '../view/Login.vue'
import Dashboard from '../view/Dashboard.vue'
import DashboardLayout from '../view/DashboardLayout.vue'
import Favorites from '../components/Favorites.vue'
import Popular from '../components/Popular.vue'
import Form from '../components/SignUpForm.vue'
import Success from '../components/Success.vue'

Vue.use(VueRouter)

const router = new VueRouter({
    routes: [
        {
            path: "/",
            component: DashboardLayout,
            children: [
                {
                    path: "",
                    name: "Dashboard",
                    component: Dashboard
                },
                {
                    path: "favorite",
                    name: "Favorite",
                    component: Favorites
                },
                {
                    path: "popular",
                    name: "Popular",
                    component: Popular
                }
            ]
        },
        {
          path: "/login",
          component: Login
        },
        {
            path: "/signup",
            component: SignUp,
            children: [
                {
                    path: "",
                    name: 'Form',
                    component: Form
                },
                {
                    path: "success",
                    name: 'Success',
                    component: Success
                }
            ]
        },

    ],
})

export default router