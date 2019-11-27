// import Vue from 'vue'
// import App from './App.vue'
// import router from './router'
// import store from 'vuex'




// new Vue({
//   store,
//   router,
//   render: h => h(App)
// }).$mount('#app')
const {app} = require('electron');

const {createAuthWindow} = require('../processes/auth-process');
const createAppWindow = require('../processes/app-process');
const authService = require('../processes/services/auth-services');

async function showWindow() {
  try {
    await authService.refreshTokens();
    return createAppWindow();
  } catch (err) {
    createAuthWindow();
  }
}

// This method will be called when Electron has finished
// initialization and is ready to create browser windows.
// Some APIs can only be used after this event occurs.
app.on('ready', showWindow);

// Quit when all windows are closed.
app.on('window-all-closed', () => {
  app.quit();
});
