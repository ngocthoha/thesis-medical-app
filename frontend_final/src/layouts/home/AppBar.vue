<template>
  <v-responsive aspect-ratio="18:1">
    <v-app-bar
      id="default-app-bar"
      color="white"
      height="80px"
      app
      elevation="1"
    >
      <v-row justify="space-around" class="hidden-md-and-down" align="center">
        <div class="d-flex flex-row align-center">
          <v-avatar class="mr-2">
            <v-img src="@/assets/img/home/appbar/logo.png"></v-img>
          </v-avatar>
          <v-card class="font-weight-medium text-body-1 mr-lg-12" elevation="0">
            Blouse Care
          </v-card>
          <v-btn
            color="#667085"
            elevation="0"
            class="font-weight-medium text-body-1 btn-not-hover btn-not-transform"
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
                class="font-weight-medium text-body-1 btn-not-hover btn-not-transform"
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
            class="font-weight-medium text-body-1 btn-not-hover btn-not-transform"
            text
            >Chuyên khoa</v-btn
          >
          <v-btn
            color="#667085"
            elevation="0"
            class="font-weight-medium text-body-1 btn-not-hover btn-not-transform"
            text
            >Cộng đồng</v-btn
          >
          <v-btn
            color="#667085"
            elevation="0"
            class="font-weight-medium text-body-1 btn-not-hover btn-not-transform"
            text
            >Cẩm nang</v-btn
          >
        </div>
        <div class="d-flex flex-row align-center">
          <!-- Login Section -->
          <div v-show="!is_login">
            <v-dialog v-model="login_dialog" width="800px">
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                  color="#667085"
                  elevation="0"
                  class="mr-3 font-weight-medium text-body-1 btn-not-hover btn-not-transform"
                  text
                  v-on="on"
                  v-bind="attrs"
                  >Đăng nhập</v-btn
                >
              </template>
              <v-card class="d-flex flex-row" height="610px">
                <v-card width="50%" elevation="0" class="d-flex flex-column">
                  <p
                    class="mt-16 ml-8 font-weight-bold"
                    :style="{ 'font-size': 32 + 'px' }"
                  >
                    Đăng nhập
                  </p>
                  <p
                    class="ml-8 font-weight-normal"
                    :style="{ 'font-size': 16 + 'px', color: '#667085' }"
                  >
                    Vui lòng nhập các thông tin phía dưới để đăng nhập.
                  </p>
                  <!-- input -->
                  <v-card
                    class="mt-12 ml-8 d-flex flex-column"
                    color="white"
                    elevation="0"
                  >
                    <v-card
                      width="320px"
                      height="44px"
                      class="d-flex mb-5"
                      elevation="0"
                      outlined
                    >
                      <v-text-field
                        v-model="user.username"
                        placeholder="Số điện thoại/Tên đăng nhập"
                        solo
                        flat
                        dense
                        class="text-body-2"
                      ></v-text-field
                    ></v-card>

                    <v-card width="320px" height="44px" elevation="0" outlined>
                      <v-text-field
                        v-model="user.password"
                        placeholder="Mật khẩu"
                        :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
                        solo
                        flat
                        dense
                        :type="show1 ? 'text' : 'password'"
                        @click:append="show1 = !show1"
                        class="text-body-2"
                      ></v-text-field>
                    </v-card>
                  </v-card>
                  <v-card width="320px" elevation="0" class="ml-8">
                    <a
                      class="d-flex font-weight-medium justify-end my-4 text-body-2"
                      style="color: #537DA5"
                    >
                      Quên mật khẩu?
                    </a>
                  </v-card>
                  <v-btn
                    width="320px"
                    height="48px"
                    class="ml-8 white--text btn-not-hover btn-not-transform text-body-1"
                    color="#537DA5"
                    elevation="0"
                    @click="onLoginSubmmit"
                    >Đăng nhập</v-btn
                  >
                  <v-card
                    class="mt-8 d-flex flex-row align-center ml-8"
                    elevation="0"
                    width="320px"
                  >
                    <v-divider></v-divider>
                    <p class="ma-0 mx-3 text-body-2" style="color: #667085">
                      hoặc đăng nhập với
                    </p>
                    <v-divider></v-divider>
                  </v-card>
                  <v-card class="mt-8 ml-8 d-flex flex-row" elevation="0">
                    <v-btn
                      width="151.5px"
                      height="44px"
                      elevation="0"
                      color="#D0D5DD"
                      outlined
                      text
                      ><v-img
                        src="@/assets/img/home/logo-google.svg"
                        height="24px"
                        width="24px"
                        contain
                      ></v-img>
                      <v-card
                        class="ma-0 btn-not-hover btn-not-transform font-weight-medium"
                        style="color:#667085"
                        elevation="0"
                        width="60%"
                      >
                        Google
                      </v-card></v-btn
                    >
                    <v-btn
                      width="151.5px"
                      height="44px"
                      elevation="0"
                      color="#1674EA"
                      class="ml-4"
                      ><v-img
                        src="@/assets/img/home/logo-facebook.svg"
                        height="24px"
                        width="24px"
                        contain
                      ></v-img>
                      <p
                        class="font-weight-medium white--text ma-0 btn-not-hover btn-not-transform"
                      >
                        Facebook
                      </p></v-btn
                    >
                  </v-card>
                </v-card>
                <v-img
                  src="@/assets/img/home/login_image.png"
                  width="50%"
                ></v-img>
              </v-card>
            </v-dialog>
          </div>

          <!-- Sign up Section -->
          <div v-show="!is_login">
            <v-dialog v-model="sign_up_dialog" width="800px">
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                  color="#537DA5"
                  elevation="0"
                  class="text-body-1 btn-not-hover btn-not-transform white--text"
                  v-on="on"
                  v-bind="attrs"
                  >Đăng ký</v-btn
                >
              </template>
              <v-card class="d-flex flex-row" height="654px">
                <v-card width="50%" elevation="0" class="d-flex flex-column">
                  <p
                    class="mt-16 ml-8 font-weight-bold"
                    :style="{ 'font-size': 32 + 'px' }"
                  >
                    Đăng ký
                  </p>
                  <p
                    class="ml-8 font-weight-normal"
                    :style="{ 'font-size': 16 + 'px', color: '#667085' }"
                  >
                    Vui lòng nhập đầy đủ thông tin phía dưới để tiến hành đăng
                    kí.
                  </p>
                  <!-- input -->
                  <v-card
                    class="mt-8 ml-8 mb-5 d-flex flex-column"
                    color="white"
                    elevation="0"
                  >
                    <v-card
                      width="320px"
                      height="44px"
                      class="d-flex mb-5"
                      elevation="0"
                      outlined
                    >
                      <v-text-field
                        v-model="sign_up_form.username"
                        placeholder="Tên đăng nhập"
                        solo
                        flat
                        dense
                        class="text-body-2"
                      ></v-text-field
                    ></v-card>
                    <v-card
                      width="320px"
                      height="44px"
                      class="d-flex mb-5"
                      elevation="0"
                      outlined
                    >
                      <v-text-field
                        v-model="sign_up_form.phone"
                        placeholder="Số điện thoại"
                        solo
                        flat
                        dense
                        class="text-body-2"
                      ></v-text-field
                    ></v-card>

                    <v-card width="320px" height="44px" elevation="0" outlined>
                      <v-text-field
                        v-model="sign_up_form.password"
                        placeholder="Mật khẩu"
                        :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
                        solo
                        flat
                        dense
                        :type="show1 ? 'text' : 'password'"
                        @click:append="show1 = !show1"
                        class="text-body-2"
                      ></v-text-field>
                    </v-card>
                  </v-card>
                  <v-btn
                    width="320px"
                    height="48px"
                    class="ml-8 white--text btn-not-hover btn-not-transform text-body-1"
                    color="#537DA5"
                    elevation="0"
                    @click="onSignupSubmit"
                    >Đăng kí</v-btn
                  >
                  <v-card
                    class="mt-8 d-flex flex-row align-center ml-8"
                    elevation="0"
                    width="320px"
                  >
                    <v-divider></v-divider>
                    <p class="ma-0 mx-3 text-body-2" style="color: #667085">
                      hoặc đăng kí với
                    </p>
                    <v-divider></v-divider>
                  </v-card>
                  <v-card class="mt-8 ml-8 d-flex flex-row" elevation="0">
                    <v-btn
                      width="151.5px"
                      height="44px"
                      elevation="0"
                      color="#D0D5DD"
                      outlined
                      text
                      ><v-img
                        src="@/assets/img/home/logo-google.svg"
                        height="24px"
                        width="24px"
                        contain
                      ></v-img>
                      <v-card
                        class="ma-0 btn-not-hover btn-not-transform font-weight-medium"
                        style="color:#667085"
                        elevation="0"
                        width="60%"
                      >
                        Google
                      </v-card></v-btn
                    >
                    <v-btn
                      width="151.5px"
                      height="44px"
                      elevation="0"
                      color="#1674EA"
                      class="ml-4"
                      ><v-img
                        src="@/assets/img/home/logo-facebook.svg"
                        height="24px"
                        width="24px"
                        contain
                      ></v-img>
                      <p
                        class="font-weight-medium white--text ma-0 btn-not-hover btn-not-transform"
                      >
                        Facebook
                      </p></v-btn
                    >
                  </v-card>
                </v-card>
                <v-img
                  src="@/assets/img/home/signup_image.png"
                  width="416px"
                  contain
                ></v-img>
              </v-card>
            </v-dialog>
          </div>
          <!-- show when logging success -->
          <div v-show="is_login">
            <div class="d-flex flex-row">
              <!-- notification -->
              <v-card
                rounded="circle"
                width="40px"
                height="40px"
                color="#FCFCFD"
                class="d-flex justify-center align-center mr-6"
                elevation="0"
              >
                <v-btn icon class="btn-not-hover" :ripple="false">
                  <v-img
                    src="@/assets/img/home/appbar/bell_icon.svg"
                    contain
                    height="16.63px"
                    width="13.33px"
                  ></v-img>
                </v-btn>
              </v-card>
              <!-- dropdown user menu  -->
              <v-card
                rounded="circle"
                width="40px"
                height="40px"
                color="#FCFCFD"
                class="d-flex justify-center align-center"
                elevation="0"
              >
                <v-menu
                  offset-y
                  left
                  content-class="elevation-1 overflow-hidden"
                  style="overflow: hidden !important"
                >
                  <template v-slot:activator="{ on, attrs }">
                    <v-btn
                      icon
                      class="btn-not-hover"
                      :ripple="false"
                      v-bind="attrs"
                      v-on="on"
                    >
                      <v-avatar size="40">
                        <img src="@/assets/img/home/appbar/avatar.png" />
                      </v-avatar>
                    </v-btn>
                  </template>
                  <v-card class="mx-auto" width="240px" tile>
                    <v-list>
                      <v-list-item
                        v-for="(item, i) in function_menu"
                        :key="i"
                        @click="onMenuClick(item)"
                      >
                        <div class="d-flex flex-row">
                          <v-list-item-icon class="mr-3">
                            <v-img
                              :src="item.icon"
                              height="20px"
                              width="20px"
                              contain
                              content-class
                              class="d-flex align-end justify-start"
                            ></v-img>
                          </v-list-item-icon>
                          <v-list-item-content>
                            <p
                              class="d-flex align-end pa-0 ma-0 text-body-2"
                              :style="{ color: item.color }"
                            >
                              {{ item.content }}
                            </p>
                          </v-list-item-content>
                        </div>
                      </v-list-item>
                    </v-list>
                  </v-card>
                </v-menu>
              </v-card>
            </div>
          </div>
        </div>
      </v-row>
      <!-- OTP -->
      <v-dialog v-model="opt_dialog" width="376px">
        <v-card
          height="678"
          elevation="0"
          class="d-flex flex-column align-center justify-center"
        >
          <v-card max-height="176" max-width="184" elevation="0">
            <v-img src="@/assets/img/home/appbar/otp_image.svg" contain></v-img>
          </v-card>
          <p class="font-weight-bold" style="font-size:32px">Xác nhận OTP</p>
          <p class="font-weight-medium text-body-2 ma-0" style="color: #667085">
            Mã xác thực vừa được gửi tới số điện thoại:
          </p>
          <p class="font-weight-bold text-body-2">
            0962530448
          </p>
          <v-card max-width="80%" elevation="0">
            <v-otp-input length="6" type="number"></v-otp-input>
          </v-card>
          <v-card width="245" elevation="0">
            <p class="text-center text-body-2" style="color: #667085">
              Nếu chưa nhận được mã xác thực, bấm Gửi lại mã sau:
            </p>
          </v-card>
          <v-btn
            elevation="0"
            color="#D0D5DD"
            class="btn-not-transform btn-not-hover text-body-2"
            text
            >Gửi lại mã</v-btn
          >
          <p style="color:#537DA5;" class="text-body-1 font-weight-medium">
            02:00
          </p>
          <v-btn
            elevation="0"
            color="#537DA5"
            class="btn-not-transform btn-not-hover text-body-1 white--text"
            width="320"
            height="48"
            >Xác nhận</v-btn
          >
        </v-card>
      </v-dialog>
    </v-app-bar>
  </v-responsive>
</template>

<script>
// Utilities
const ButtonFunctionType = {
  LOG_OUT: 0,
  FUNCTION: 1
};
export default {
  name: "HomeBar",
  computed: {},

  data: () => ({
    show1: false,
    login_dialog: false,
    sign_up_dialog: false,
    opt_dialog: false,
    is_login: false,
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
    ],

    function_menu: [
      {
        icon: require("@/assets/img/home/appbar/account_icon.svg"),
        content: "Hồ sơ cá nhân",
        color: "#667085",
        type: ButtonFunctionType.FUNCTION,
        link: "/home/user/profile"
      },
      {
        icon: require("@/assets/img/home/appbar/clock_icon.svg"),
        content: "Lịch sử Đặt khám",
        color: "#667085",
        type: ButtonFunctionType.FUNCTION,
        link: "/home/user/appointment-history"
      },
      {
        icon: require("@/assets/img/home/appbar/document_icon.svg"),
        content: "Hồ sơ sức khỏe",
        color: "#667085",
        type: ButtonFunctionType.FUNCTION
      },
      {
        icon: require("@/assets/img/home/appbar/connection_icon.svg"),
        content: "Lịch sử giao dịch",
        color: "#667085",
        type: ButtonFunctionType.FUNCTION
      },
      {
        icon: require("@/assets/img/home/appbar/help_icon.svg"),
        content: "Câu hỏi của bạn",
        color: "#667085",
        type: ButtonFunctionType.FUNCTION
      },
      {
        icon: require("@/assets/img/home/appbar/logout_icon.svg"),
        content: "Đăng xuất",
        color: "#F04438",
        type: ButtonFunctionType.LOG_OUT,
        link: "/"
      }
    ],

    user: {
      username: "",
      password: ""
    },

    sign_up_form: {
      username: "",
      password: "",
      phone: ""
    }
  }),

  created() {
    this.is_login = this.$store.getters["auth/isLogin"];
  },

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
    },

    async onMenuClick(button) {
      if (button.type == ButtonFunctionType.LOG_OUT) {
        this.$store.dispatch("auth/logout", {});
        this.is_login = false;
      }
      this.$router.push({ path: button.link }).catch(error => {
        if (error == null) {
          return;
        }
        if (error.name != "NavigationDuplicated") {
          throw error;
        }
      });
    },

    async onLoginSubmmit() {
      //check login dev
      if (process.env.VUE_APP_LOGIN_DEV === "TRUE") {
        this.login_dialog = false;
        this.is_login = true;
        return;
      }

      const user = {
        username: this.user.username,
        password: this.user.password
      };

      await this.$store.dispatch("auth/login", user);

      if (this.$store.getters["auth/access_token"] != "") {
        this.login_dialog = false;
        this.is_login = true;
      }
    },

    onSignupSubmit() {
      const form = {
        username: this.sign_up_form.username,
        password: this.sign_up_form.password,
        phone: "+84" + this.sign_up_form.phone.substring(1),
        role: "ROLE_USER"
      };

      console.log(form);
      this.sign_up_dialog = false;
      this.opt_dialog = true;
    },

    onOtpSubmit() {
      this.opt_dialog = false;
    }
  }
};
</script>

<style scoped>
.btn-not-transform {
  text-transform: none;
}

.btn-not-hover:hover::before {
  opacity: 0 !important;
}
</style>
