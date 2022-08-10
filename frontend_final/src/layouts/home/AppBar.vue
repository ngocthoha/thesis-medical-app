<template>
  <v-responsive aspect-ratio="18:1">
    <v-app-bar
      id="default-app-bar"
      color="white"
      height="80px"
      scroll-target="#scrolling-techniques-7"
    >
      <!-- <v-app-bar-nav-icon class="hidden-md-and-up" @click="drawer = !drawer" /> -->

      <!-- <default-drawer-toggle class="hidden-sm-and-down" /> -->

      <!-- <v-toolbar-title class="font-weight-light text-h5" v-text="name" />
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
            @click="getpage(link)"
          >
            <strong style="font-size:14px">{{ link.label }}</strong>
          </v-btn>
        </v-row></v-col
      > -->

      <!-- <v-spacer /> -->

      <!-- <default-search class="hidden-sm-and-down" /> -->

      <!-- <default-go-home /> -->

      <!-- <default-notifications />

    <default-account /> -->

      <!-- <v-btn
        color="#046792"
        rounded
        class="my-2 white--text mr-1"
        @click="login"
      >
        Đăng nhập
      </v-btn>
      <v-btn color="#046792" rounded class="my-2 white--text" @click="signup">
        Đăng ký
      </v-btn> -->
      <v-row justify="center" class="my-3 mx-16">
        <v-spacer />
        <v-btn
          color="#667085"
          elevation="0"
          class="font-weight-medium text-body-1 btn"
          text
          >Trang chủ</v-btn
        >
        <v-menu offset-y open-on-hover content-class="elevation-1">
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              color="#667085"
              elevation="0"
              class="font-weight-medium text-body-1 btn"
              text
              v-bind="attrs"
              v-on="on"
            >
              Đặt lịch <v-icon right>mdi-chevron-down</v-icon>
            </v-btn>
          </template>
          <div style="background-color: white">
            <v-hover v-slot="{ hover }">
              <v-card
                :elevation="hover ? 1 : 0"
                class="mt-4 mx-4"
                width="296px"
                height="92px"
                ><v-col cols="12" class="pa-0">
                  <v-row class="ma-0"
                    ><v-col cols="2" class="pb-0"
                      ><v-img
                        src="@/assets/img/home/appbar/doctor.svg"
                      ></v-img></v-col
                    ><v-col cols="10" class="pl-0"
                      ><p class="font-weight-medium text-body-1 ma-0">Bác sĩ</p>
                      <p class="text-body-2" style="color:#667085">
                        Đặt lịch khám với bác sĩ chuyên khoa tại bệnh viện hoặc
                        online
                      </p></v-col
                    ></v-row
                  ></v-col
                ></v-card
              >
            </v-hover>
            <v-hover v-slot="{ hover }">
              <v-card
                :elevation="hover ? 1 : 0"
                class="mt-1 mx-4"
                width="296px"
                height="92px"
                >hello</v-card
              >
            </v-hover>
            <v-hover v-slot="{ hover }">
              <v-card
                :elevation="hover ? 1 : 0"
                class="mt-1 mx-4 mb-4"
                width="296px"
                height="92px"
                >hello</v-card
              >
            </v-hover>
          </div>
        </v-menu>
        <!-- <v-btn color="#667085" elevation="0" class="text-body-1 btn" text
          >Đặt lịch</v-btn
        > -->
        <v-btn
          color="#667085"
          elevation="0"
          class="font-weight-medium text-body-1 btn"
          text
          >Chuyên khoa</v-btn
        >
        <v-btn
          color="#667085"
          elevation="0"
          class="font-weight-medium text-body-1 btn"
          text
          >Cộng đồng</v-btn
        >
        <v-btn
          color="#667085"
          elevation="0"
          class="font-weight-medium text-body-1 btn"
          text
          >Cẩm nang</v-btn
        >
        <v-spacer />
        <v-btn
          color="#667085"
          elevation="0"
          class="mr-3 font-weight-medium text-body-1 btn"
          text
          >Đăng nhập</v-btn
        >
        <v-btn color="#537DA5" elevation="0" class="text-body-1 btn white--text"
          >Đăng ký</v-btn
        >
      </v-row>
    </v-app-bar>
  </v-responsive>
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
    // links: [
    //   {
    //     label: "Trang chủ",
    //     name: "Trang chủ"
    //   },
    //   {
    //     label: "Giới thiệu",
    //     name: "Giới thiệu"
    //   },
    //   {
    //     label: "Quy trình",
    //     name: "Quy trình"
    //   },
    //   {
    //     label: "Dịch vụ",
    //     name: "Dịch vụ",
    //     path: "/service"
    //   },
    //   {
    //     label: "Liên hệ",
    //     name: "Liên hệ"
    //   }
    // ]
  }),

  methods: {
    login() {
      this.$router.push({ name: "Đăng nhập" });
    },
    signup() {
      this.$router.push({ name: "Đăng ký" });
    },
    async getpage(link) {
      this.$router.push({ name: link.name }).catch(error => {
        if (error == null) {
          return;
        }
        if (error.name != "NavigationDuplicated") {
          throw error;
        }
      });
    }
  }
};
</script>

<style scoped>
.btn {
  text-transform: none;
}
</style>
