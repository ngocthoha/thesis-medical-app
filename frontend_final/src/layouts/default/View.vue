<template>
  <v-main>
    <v-container fluid style="height:100vh;">
      <router-view :key="$route.path" />
    </v-container>
  </v-main>
</template>

<script>
export default {
  name: "DefaultView"
};
</script>
