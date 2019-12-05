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
    components: {
        HomeButton
    },
    mounted(){
            this.getPlayerInfo();
            if(this.$store.player === null){
                this.$router.push('register')
            }

    },
    methods:{
        getPlayerInfo(){
            axios.request({
                url: '/private/user/getemail',
                method: 'post',
                baseURL: 'localhost:8082/api',
                data:{
                    email: this.$auth.user.email
                },
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': this.$auth.getTokenSilently()
                }
             })
          .then(response => {
            this.$store.dispatch('SavePlayerInfo', response)
          }).catch(e => {
            console.log(e);
          });
        }
    }
}
</script>

<style>

</style>