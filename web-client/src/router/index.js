import Vue from "vue"
import VueRouter from "vue-router"
import SignUp from '../view/SignUp.vue'
import Result from '../components/Result.vue'

Vue.use(VueRouter)

const router = new VueRouter({
    routes: [
        {
            path: "/signUp",
            component: SignUp,
            name: SignUp,
            children: [
                {
                    path: "result",
                    component: Result
                }
            ]
        },
    ],
})

export default router