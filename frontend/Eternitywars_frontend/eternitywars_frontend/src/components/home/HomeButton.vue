<template>
  <div >{{this.title}}</div>
</template>

<script>
export default {
    name: 'HomeButton',
    props:{
        title: String,
    },
    methods: {
         // Log the user out
        logout() {
            this.$auth.logout({
            returnTo: window.location.origin
            });
        }
    }
}

</script>

<style>

</style>