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
import store from '../store'
Vue.use(VueRouter)

const beforeAuth = isAuth => (from, to, next) => {
    const isAuthenticated = store.getters["isAuthenticated"]
    if ((isAuthenticated && isAuth) || (!isAuthenticated && !isAuth)) {
        return next()
    } else {
        // 홈 화면으로 이동
        next("/login")
    }
}

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
            ],
            beforeEnter: beforeAuth(true)
        },
        {
          path: "/login",
          component: Login,
          beforeEnter: beforeAuth(false)
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
            ],
            beforeEnter: beforeAuth(false)
        },

    ],
})

export default router