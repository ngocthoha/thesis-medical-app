<template>
  <v-navigation-drawer
    id="default-drawer"
    v-model="drawer"
    :right="$vuetify.rtl"
    :mini-variant.sync="mini"
    mini-variant-width="80"
    app
    color="white"
    width="320"
  >
    <!-- <template v-if="drawerImage" #img="props">
      <v-img :key="image" :gradient="gradient" v-bind="props" />
    </template> -->

    <div class="px-2">
      <default-drawer-header />

      <v-divider class="mx-3 mb-2" />

      <default-list :items="items" />
    </div>

    <div class="pt-12" />
  </v-navigation-drawer>
</template>

<script>
// Utilities
import { get, sync } from "vuex-pathify";

export default {
  name: "DefaultDrawer",

  components: {
    DefaultDrawerHeader: () =>
      import(
        /* webpackChunkName: "default-drawer-header" */
        "./widgets/DrawerHeader"
      ),
    DefaultList: () =>
      import(
        /* webpackChunkName: "default-list" */
        "./List"
      ),
  },
  data() {
    return {
      itemDefault: [
        {
          title: "Dashboard",
          icon: "mdi-view-dashboard",
          to: "/",
        },
        {
          title: "User Profile",
          icon: "mdi-account",
          to: "/components/profile/",
        },
        {
          title: "Regular Tables",
          icon: "mdi-clipboard-outline",
          to: "/tables/regular/",
        },
        {
          title: "Typography",
          icon: "mdi-format-font",
          to: "/components/typography/",
        },
        {
          title: "Icons",
          icon: "mdi-chart-bubble",
          to: "/components/icons/",
        },
        {
          title: "Google Maps",
          icon: "mdi-map-marker",
          to: "/maps/google/",
        },
        {
          title: "Notifications",
          icon: "mdi-bell",
          to: "/components/notifications/",
        },
        {
          title: "Notifications",
          icon: "mdi-bell",
          to: "/components/schedule",
        },
      ],

      itemDoctor1: [
        {
          items: [
            {
              title: "Lịch trình",
              icon: "mdi-map-marker",
              to: "/doctor/doctor-schedule",
            },
            {
              title: "Bệnh án",
              icon: "mdi-map-marker",
              to: "/doctor/doctor-record",
            },
            {
              title: "Gửi Thông báo",
              icon: "mdi-map-marker",
              to: "/doctor/doctor-notification",
            },
          ],
          title: "Lịch trình",
        },
        {
          items: [
            {
              title: "Lịch trình",
              icon: "mdi-map-marker",
              to: "/doctor/doctor-schedule",
            },
            {
              title: "Bệnh án",
              icon: "mdi-map-marker",
              to: "/doctor/doctor-record",
            },
            {
              title: "Gửi Thông báo",
              icon: "mdi-map-marker",
              to: "/doctor/doctor-notification",
            },
          ],
          title: "Lịch trình",
        },
        {
          items: [
            {
              title: "Lịch trình",
              icon: "mdi-map-marker",
              to: "/doctor/doctor-schedule",
            },
            {
              title: "Bệnh án",
              icon: "mdi-map-marker",
              to: "/doctor/doctor-record",
            },
            {
              title: "Gửi Thông báo",
              icon: "mdi-map-marker",
              to: "/doctor/doctor-notification",
            },
          ],
          title: "Lịch trình",
        },
      ],

      itemAdmin: [
        {
          title: "Thống kê",
          icon: "mdi-bell",
          to: "/admin/dashboard-admin",
        },
        {
          title: "Bác sĩ",
          icon: "mdi-bell",
          to: "/admin/doctor-admin",
        },
        {
          title: "Trạng thái cuộc hẹn",
          icon: "mdi-bell",
          to: "/admin/appointment-status-admin",
        },
        {
          title: "Tạo phòng",
          icon: "mdi-bell",
          to: "/admin/room-admin",
        },
        {
          title: "Lịch hẹn",
          icon: "mdi-bell",
          to: "/admin/appointment-admin",
        },

        {
          title: "Bệnh án",
          icon: "mdi-bell",
          to: "/admin/record-admin",
        },
        {
          title: "Lịch trình",
          icon: "mdi-bell",
          to: "/admin/schedule-admin",
        },
      ],

      itemDoctor: [
        {
          title: "QUẢN LÝ LỊCH",
          icon: "mdi-calendar-month-outline",
          items: [
            {
              title: "Lịch làm việc",
              to: "/doctor/doctor-schedule",
            },
          ],
        },
        {
          title: "QUẢN LÝ KHÁM BỆNH",
          icon: "mdi-stethoscope",
          items: [
            {
              title: "Khám bệnh",
            },
            {
              title: "Tư vấn Online",
            },
          ],
        },
      ],
    };
  },
  computed: {
    ...get("user", ["dark", "gradient", "image"]),

    items() {
      //dev login
      // if (process.env.VUE_APP_LOGIN_DEV === "TRUE") {
      //   var typedev = process.env.VUE_APP_ROLE_DEV;
      //   if (typedev === "ROLE_DOCTOR") {
      //     return this.itemDoctor;
      //   }
      //   if (typedev === "ROLE_ADMIN") {
      //     return this.itemAdmin;
      //   }
      //   return;
      // }

      // var type = this.$store.getters["auth/types"];
      // if (type[0].authority === "ROLE_DOCTOR") {
      //   return this.itemDoctor;
      // }
      // if (type[0].authority === "ROLE_ADMIN") {
      //   return this.itemAdmin;
      // }
      // return [];
      return this.itemDoctor;
    },
    // ...get('app', [
    //   'items',
    //   'version',
    // ]),
    ...sync("app", ["drawer", "drawerImage", "mini"]),
  },
};
</script>

<style lang="sass">
#default-drawer
  .v-list-item
    margin-bottom: 8px

  .v-list-item::before,
  .v-list-item::after
    display: none

  .v-list-group__header__prepend-icon,
  .v-list-item__icon
    margin-top: 12px
    margin-bottom: 12px
    margin-left: 4px

  &.v-navigation-drawer--mini-variant
    .v-list-item
      justify-content: flex-start !important
</style>
