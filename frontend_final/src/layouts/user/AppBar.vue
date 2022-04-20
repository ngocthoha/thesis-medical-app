<template>
  <v-app-bar id="default-app-bar" absolute color="#fff9ed">
    <!-- <v-sheet class="mx-auto" max-width="700" color="#248eff">
      <v-slide-group show-arrows>
        <v-slide-item
          v-for="(item, index) in links"
          :key="index"
          v-slot="{ active, toggle }"
        >
          <v-card
            color="#248eff"
            active-class="primary white--text"
            class="white--text pa-2 mx-2"
            elevation="0"
            @click="ChangePage(item.link_name)"
            style="font-size: 12px"
            >{{ item.name }}</v-card
          >
        </v-slide-item>
      </v-slide-group>
    </v-sheet> -->
  </v-app-bar>
</template>

<script>
// Utilities
import { get, sync } from "vuex-pathify";

export default {
  name: "HomeBar",

  components: {
    // DefaultAccount: () =>
    //   import(
    //     /* webpackChunkName: "default-account" */
    //     "./widgets/Account"
    //   ),
    // DefaultDrawerToggle: () =>
    //   import(
    //     /* webpackChunkName: "default-drawer-toggle" */
    //     "./widgets/DrawerToggle"
    //   ),
    // DefaultGoHome: () =>
    //   import(
    //     /* webpackChunkName: "default-go-home" */
    //     "./widgets/GoHome"
    //   ),
    // DefaultNotifications: () =>
    //   import(
    //     /* webpackChunkName: "default-notifications" */
    //     "./widgets/Notifications"
    //   ),
    // DefaultSearch: () =>
    //   import(
    //     /* webpackChunkName: "default-search" */
    //     "./widgets/Search"
    //   )
  },

  computed: {},

  data: () => ({
    image: require("@/assets/img/home/background.jpg"),
    links: [
      {
        link_name: "Đặt lịch khám theo ngày",
        name: "ĐẶT LỊCH KHÁM THEO NGÀY"
      },
      {
        link_name: "Đặt lịch khám theo bác sĩ",
        name: "ĐẶT LỊCH KHÁM THEO BÁC SĨ"
      },
      {
        link_name: "Xem lịch sử cuộc hẹn",
        name: "LỊCH SỬ ĐẶT KHÁM"
      }
    ]
  }),

  methods: {
    ChangePage(item_name) {
      this.$router.push({ name: item_name });
    }
  }
};
</script>
