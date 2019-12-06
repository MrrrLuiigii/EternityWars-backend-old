<template>
  <div>
      <HomeButton title="Game"/>
      <HomeButton title="Deck Builder"/>
      <HomeButton title="Shop"/>
      <HomeButton title="Logout" functie="logout"/>
             {{this.$store.state.player}}
  </div>
</template>

<script>
import  HomeButton  from './HomeButton.vue';
import axios from 'axios';
export default {
    name: 'Home',
    data(){
        return{
        }
    },
      mounted(){
            this.getPlayerInfo()
    },

    components: {
        HomeButton
    },
    methods:{
       async getPlayerInfo(){
            axios.request({
                url: '/private/user/getbyemail',
                method: 'post',
                baseURL: 'http://localhost:8082/api',
                data:{
                    email: this.$auth.user.email
                },
                headers: {
                    'Content-Type': 'text/plain',
                    'Authorization': 'Bearer ' + await this.$auth.getTokenSilently()
                }
             })
          .then(response => {
            this.$store.dispatch('SavePlayerInfo', response.data)
          }).catch(e => {
            console.log(e);
          })
          if(this.$store.state.player.username == null){
                this.$router.push('register')
          }
        }
    }
}
</script>

<style>

</style>