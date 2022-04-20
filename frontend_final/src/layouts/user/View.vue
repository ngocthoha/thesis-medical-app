<template>
  <v-container fluid style="padding: 0">
    <router-view :key="$route.path" />
  </v-container>
</template>

<script>
export default {
  name: "DefaultView"
};
</script>
