<template>
  <v-app>
    <v-footer color="white" class="pb-10"
      ><v-col cols="12">
        <v-row justify="center"
          ><strong
            class="font-weight-medium"
            style="color: #322e2a; font-size: 36px"
            >Hỗ Trợ Khám Bệnh Online</strong
          ></v-row
        > </v-col
      ><v-row justify="center"
        ><v-img :src="image" alt="" max-height="440" max-width="660"></v-img
      ></v-row>
    </v-footer>
    <v-sheet class="pa-10" width="100%" color="#f8f9fd">
      <v-row justify="center">
        <v-slide-group show-arrows>
          <v-slide-item v-for="(item, index) in links" :key="index">
            <div>
              <v-card
                color="#eceefb"
                class="pa-2 mx-10 mb-5"
                height="100px"
                width="100px"
                hover="true"
                ><v-row justify="center"
                  ><v-icon large color="purple darken-2" class="my-5">
                    mdi-arrow-up-bold-box-outline</v-icon
                  ></v-row
                ></v-card
              >
              <v-row justify="center" style="font-size: 11px">{{
                item.name
              }}</v-row>
            </div>
          </v-slide-item>
        </v-slide-group>
      </v-row>
    </v-sheet>
    <default-view />
  </v-app>
</template>

<script>
export default {
  name: "UserLayout",

  components: {
    DefaultBar: () =>
      import(
        /* webpackChunkName: "default-app-bar" */
        "./AppBar"
      ),
    // DefaultFooter: () =>
    //   import(
    //     /* webpackChunkName: "default-footer" */
    //     "./Footer"
    //   ),
    // DefaultSettings: () =>
    //   import(
    //     /* webpackChunkName: "default-settings" */
    //     "./Settings"
    //   ),
    DefaultView: () =>
      import(
        /* webpackChunkName: "default-view" */
        "./View"
      )
  },

  data: () => ({
    image: require("@/assets/img/home/home_content.png"),
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
    login() {
      this.$router.push({ name: "Đăng nhập" });
    },
    signup() {
      this.$router.push({ name: "Đăng ký" });
    },
    ChangePage(item_name) {
      this.$router.push({ name: item_name });
    }
  }
};
</script>
