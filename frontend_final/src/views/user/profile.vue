<template>
  <div>
    <v-card width="922px" elevation="4" class="pb-8">
      <profile-list v-if="false"></profile-list>
      <add-profile v-if="true"></add-profile>
    </v-card>
  </div>
</template>

<script>
import ProfileList from "./component/profileList.vue";
import AddProfile from "./component/addProfile.vue";
export default {
  components: {
    ProfileList,
    AddProfile
  }
};
</script>
