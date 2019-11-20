import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: Home,
    meta:{
      requiresGuest: true
    }
  },
  {
    path: '/login',
    name: 'login',
    component: Home,
    meta:{
      requiresGuest: true
    }
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.,
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue'),
    meta:{
      requiresAuth: true
    }
  }
]


const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

// // Nav Guard
// router.beforeEach((to, from, next) => {
//   // Check for requiresAuth guard
//   if (to.matched.some(record => record.meta.requiresAuth)) {
//     // Check if NO logged user
//     if (!firebase.auth().currentUser) {
//       // Go to login
//       next({
//         path: '/login',
//         query: {
//           redirect: to.fullPath
//         }
//       });
//     } else {
//       // Proceed to route
//       next();
//     }
//   } else if (to.matched.some(record => record.meta.requiresGuest)) {
//     // Check if NO logged user
//     if (firebase.auth().currentUser) {
//       // Go to login
//       next({
//         path: '/',
//         query: {
//           redirect: to.fullPath
//         }
//       });
//     } else {
//       // Proceed to route
//       next();
//     }
//   } else {
//     // Proceed to route
//     next();
//   }
// });


export default router
