<template>
        <div class="navbar-end">
            <div class="navbar-item">
                 <div class="buttons">
                    <!-- Check that the SDK client is not currently loading before accessing is methods -->
                    <div v-if="!$auth.loading">
                        <!-- show play when authenticated -->
                        <a v-if="$auth.isAuthenticated" @click="null" class="button is-dark"><strong>Play</strong></a>
                    </div>
                </div>
            </div>
             <div class="navbar-item">
                 <div class="buttons">
                    <!-- Check that the SDK client is not currently loading before accessing is methods -->
                    <div v-if="!$auth.loading">
                        <!-- show play when authenticated -->
                        <a v-if="$auth.isAuthenticated" @click="null" class="button is-dark"><strong>Deck builder</strong></a>
                    </div>
                </div>
            </div>
             <div class="navbar-item">
                 <div class="buttons">
                    <!-- Check that the SDK client is not currently loading before accessing is methods -->
                    <div v-if="!$auth.loading">
                        <!-- show play when authenticated -->
                        <a v-if="$auth.isAuthenticated" @click="getToken" class="button is-dark"><strong>Shop</strong></a>
                    </div>
                </div>
            </div>
            <div class="navbar-item">
                 <div class="buttons">
                    <!-- Check that the SDK client is not currently loading before accessing is methods -->
                    <div v-if="!$auth.loading">
                    <!-- show login when not authenticated -->
                        <a v-if="!$auth.isAuthenticated" @click="login" class="button is-dark"><strong>Sign in</strong></a>
                        <!-- show logout when authenticated -->
                        <a v-if="$auth.isAuthenticated" @click="logout" class="button is-dark"><strong>Log out</strong></a>
                </div>
            </div>
        </div>
        <p>{{this.token}}</p>
    </div>
</template>

<script>

export default {
  name: 'Nav',
  data() {
      return{
      token: null,
    };
  },
  methods: {
  // Log the user in
  login() {
    this.$auth.loginWithRedirect();
  },
  // Log the user out
  logout() {
    this.$auth.logout({
      returnTo: window.location.origin
    });
  },
    getToken(){
    this.$auth.getTokenSilently().then(accesToken => console.log(accesToken))
    }   
  }
}
</script>