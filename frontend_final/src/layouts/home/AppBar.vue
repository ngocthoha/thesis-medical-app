<template>
  <v-app-bar
    id="default-app-bar"
    app
    color="#DCF5FF"
    height="70"
    elevate-on-scroll
    scroll-target="#scrolling-techniques-7"
  >
    <!-- <v-app-bar-nav-icon class="hidden-md-and-up" @click="drawer = !drawer" /> -->

    <!-- <default-drawer-toggle class="hidden-sm-and-down" /> -->

    <!-- <v-toolbar-title class="font-weight-light text-h5" v-text="name" /> -->
    <v-spacer />
    <v-col cols="5">
      <v-row justify="center" no-gutters>
        <v-btn
          v-for="link in links"
          :key="link.label"
          color="#046792"
          text
          rounded
          class="my-2"
          @click="getpage(link.name)"
        >
          <strong style="font-size:14px">{{ link.label }}</strong>
        </v-btn>
      </v-row></v-col
    >

    <v-spacer />

    <!-- <default-search class="hidden-sm-and-down" /> -->

    <!-- <default-go-home /> -->

    <!-- <default-notifications />

    <default-account /> -->

    <v-btn color="#046792" rounded class="my-2 white--text" @click="login">
      Đăng nhập
    </v-btn>
    <v-btn color="#046792" rounded class="my-2 white--text" @click="signup">
      Đăng ký
    </v-btn>
  </v-app-bar>
</template>

<script>
// Utilities
import { get, sync } from "vuex-pathify";

export default {
  name: "HomeBar",

  components: {
    DefaultAccount: () =>
      import(
        /* webpackChunkName: "default-account" */
        "./widgets/Account"
      ),
    DefaultDrawerToggle: () =>
      import(
        /* webpackChunkName: "default-drawer-toggle" */
        "./widgets/DrawerToggle"
      ),
    DefaultGoHome: () =>
      import(
        /* webpackChunkName: "default-go-home" */
        "./widgets/GoHome"
      ),
    DefaultNotifications: () =>
      import(
        /* webpackChunkName: "default-notifications" */
        "./widgets/Notifications"
      ),
    DefaultSearch: () =>
      import(
        /* webpackChunkName: "default-search" */
        "./widgets/Search"
      )
  },

  computed: {},

  data: () => ({
    links: [
      {
        label: "Trang chủ",
        name: "Trang chủ"
      },
      {
        label: "Giới thiệu",
        name: "Giới thiệu"
      },
      {
        label: "Quy trình",
        name: "Quy trình"
      },
      {
        label: "Dịch vụ",
        name: "Dịch vụ"
      },
      {
        label: "Liên hệ",
        name: "Liên hệ"
      }
    ]
  }),

  methods: {
    login() {
      this.$router.push({ name: "Đăng nhập" });
    },
    signup() {
      this.$router.push({ name: "Đăng ký" });
    },
    getpage(link_name) {
      this.$router.push({ name: link_name });
    }
  }
};
</script>
