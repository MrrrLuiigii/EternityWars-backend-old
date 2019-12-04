<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png">
    <Nav/>
  </div>
</template>

<script>
// @ is an alias to /src
import Nav from '@/components/partials/Nav.vue'

export default {
  name: 'home',
  components: {
    Nav
  }
}
</script>
