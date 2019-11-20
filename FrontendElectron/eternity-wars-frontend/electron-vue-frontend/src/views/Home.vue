<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png" />
    <HelloWorld msg="Welcome to Your Vue.js App" />
  </div>
</template>
<script src="/socket.io/socket.io.js" />
<script>
import HelloWorld from "@/components/HelloWorld.vue";



export default {
  name: "home",
  components: {
    HelloWorld
  }
 }

</script>
