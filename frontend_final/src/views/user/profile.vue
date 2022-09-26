<template>
  <div>
    <v-card width="922px" elevation="4" class="pb-8">
      <profile-list
        :profile_list="profile_list"
        v-if="visible_profile_list"
        v-on:OpenCreateProfile="createProfile"
        v-on:OpenEditProfile="editProfile"
      ></profile-list>
      <add-profile
        v-if="visible_add_profile"
        v-on:addProfileClose="closeAddProfile"
      ></add-profile>
      <edit-profile
        v-if="visible_edit_profile"
        v-on:editProfileClose="closeEditProfile"
      ></edit-profile>
    </v-card>
  </div>
</template>

<script>
import ProfileList from "./component/profileList.vue";
import AddProfile from "./component/addProfile.vue";
import EditProfile from "./component/editProfile.vue";
export default {
  components: {
    ProfileList,
    AddProfile,
    EditProfile,
  },
  data() {
    return {
      visible_profile_list: true,
      visible_add_profile: false,
      visible_edit_profile: false,
      profile_list: [],
      edit_profile: null,
    };
  },

  created() {
    this.getProfileList();
  },

  methods: {
    createProfile() {
      this.visible_profile_list = false;
      this.visible_add_profile = true;
    },

    closeAddProfile() {
      this.visible_profile_list = true;
      this.visible_add_profile = false;
    },

    editProfile() {
      this.visible_profile_list = false;
      this.visible_edit_profile = true;
    },

    closeEditProfile() {
      this.visible_profile_list = true;
      this.visible_edit_profile = false;
    },

    async getProfileList() {
      let token = this.$store.getters["auth/access_token"];
      const param = {
        token: token,
      };
      await this.$store.dispatch("profile/profile_list", param);
      this.profile_list = this.$store.getters["profile/profile_list"];
    },
  },
};
</script>
