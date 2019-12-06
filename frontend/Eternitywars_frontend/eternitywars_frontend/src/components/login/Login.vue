<template>      
      <div class="navbar-item">
            <div class="buttons">
              <!-- Check that the SDK client is not currently loading before accessing is methods -->
              <div v-if="!$auth.loading">
              <!-- show login when not authenticated -->
                  <a v-if="!$auth.isAuthenticated" @click="login" class="button is-dark"><strong>Sign in</strong></a>
          </div>
      </div>

      <div>{{this.$auth.user}}</div>
  </div>
</template>

<script>

export default {
  name: 'Login',
  data() {
      return{
    };
  },
  methods: {
  // Log the user in
  login() {
    this.$auth.loginWithRedirect();
  },
    getToken(){
    this.$auth.getTokenSilently().then(accesToken => console.log(accesToken))
    }   
  }
}
</script>