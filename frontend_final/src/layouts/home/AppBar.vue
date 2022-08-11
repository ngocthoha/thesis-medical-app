<template>
  <v-responsive aspect-ratio="18:1">
    <v-app-bar
      id="default-app-bar"
      color="white"
      height="80px"
      app
      elevation="1"
      scroll-target="#scrolling-techniques-7"
    >
      <v-row justify="center" class="my-3 mx-16">
        <v-spacer />
        <v-btn
          color="#667085"
          elevation="0"
          class="font-weight-medium text-body-1 btn"
          text
          >Trang chủ</v-btn
        >
        <v-menu
          offset-y
          open-on-hover
          content-class="elevation-1 overflow-hidden"
          style="overflow: hidden !important"
        >
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
          <v-list>
            <v-list-item v-for="(item, i) in appointment_list" :key="i" link>
              <v-list-item-content style="height:92px; width:296px">
                <v-col cols="12" class="pa-0">
                  <v-row class="ma-0"
                    ><v-col cols="2" class="pb-0"
                      ><v-img :src="item.icon"></v-img></v-col
                    ><v-col cols="10" class="pl-0"
                      ><p class="font-weight-medium text-body-1 ma-0">
                        {{ item.title }}
                      </p>
                      <p class="text-body-2" style="color:#667085">
                        {{ item.content }}
                      </p></v-col
                    ></v-row
                  ></v-col
                >
              </v-list-item-content>
            </v-list-item>
          </v-list>
        </v-menu>
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
    appointment_list: [
      {
        icon: require("@/assets/img/home/appbar/doctor.svg"),
        title: "Bác sĩ",
        content:
          "Đặt lịch khám với bác sĩ chuyên khoa tại bệnh viện hoặc online"
      },
      {
        icon: require("@/assets/img/home/appbar/hospital.svg"),
        title: "Bệnh viện",
        content: "Đặt lịch khám chuyên khoa tại các bệnh viện"
      },
      {
        icon: require("@/assets/img/home/appbar/service.svg"),
        title: "Dịch vụ",
        content: "Các dịch vụ và gói khám tùy chọn theo nhu cầu"
      }
    ]
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

.btn:hover::before {
  opacity: 0 !important;
}
</style>
