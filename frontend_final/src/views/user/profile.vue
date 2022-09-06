<template>
  <div>
    <v-card width="922px" elevation="4" class="pb-8">
      <profile-list v-if="true"></profile-list>
    </v-card>
  </div>
</template>

<script>
import ProfileList from "./component/profileList.vue";
export default {
  components: {
    ProfileList
  }
};
</script>
