<template>
  <div class="content">
    <v-footer color="#DCF5FF" height="104px" :padless="padless">
      <v-col cols="3">icon</v-col>
      <v-col cols="5">
        <v-row justify="center" no-gutters>
          <v-btn
            v-for="link in links"
            :key="link"
            color="#046792"
            text
            rounded
            class="my-2"
          >
            {{ link }}
          </v-btn>
        </v-row></v-col
      >
      <v-col cols="4">
        <v-row justify="end" no-gutters>
          <v-btn
            color="#046792"
            rounded
            class="my-2 white--text"
            @click="login"
          >
            Đăng nhập
          </v-btn>
          <v-btn color="#046792" rounded class="my-2 white--text">
            Đăng ký
          </v-btn>
        </v-row></v-col
      >
    </v-footer>
    <v-footer color="#DCF5FF" height="500px" :padless="padless">
      <v-col cols="12">
        <v-row justify="center">
          <strong
            class="font-weight-medium"
            style="color: #046792; font-size: 20px"
            >Remove Doubts</strong
          >
        </v-row></v-col
      >
      <v-col cols="12">
        <v-row justify="center"
          ><strong
            class="font-weight-medium"
            style="color: #046792; font-size: 64px"
            >Free Doctor Consultation</strong
          ></v-row
        ></v-col
      >
      <v-col cols="12">
        <v-row justify="center"
          ><strong
            class="font-weight-medium"
            style="color: #68a4be; font-size: 16px"
            >24/7 Video consultations. Private consultation + Audio call Starts
            at just $10. Exclusively on mobile ap</strong
          ></v-row
        ></v-col
      >
      <v-col cols="12">
        <v-row justify="center">
          <v-card color="white" width="721px" class="rounded-pill"
            ><input
              type="text"
              v-model="search"
              placeholder="..."
              style="width: 80%"
              class="ml-5"
            />
            <v-btn color="#3685A8" rounded class="my-2 white--text">
              <v-icon small>fas fa-search</v-icon>
              Tìm kiếm
            </v-btn>
          </v-card></v-row
        ></v-col
      ><v-col cols="8">
        <v-row justify="center"
          ><strong
            class="font-weight-medium"
            style="color: #046792; font-size: 16px"
            >Bạn đang tìm kiếm điều gì</strong
          ></v-row
        >
      </v-col>
      <v-col cols="12">
        <v-row justify="center"
          ><v-card
            class="mx-2"
            v-for="(item, index) in card_link"
            :key="index"
            width="184px"
            height="84px"
            hover="true"
            style="
              background: linear-gradient(180deg, #ffdfcc 0%, #ffeee6 100%);
            "
          >
            <strong
              class="font-weight-medium ml-2"
              style="color: #046792; font-size: 16px"
              >{{ item.head }}</strong
            ><br /><strong
              class="font-weight-regular ml-2"
              style="color: #046792; font-size: 16px"
              >{{ item.body }}</strong
            >
          </v-card></v-row
        >
      </v-col>
    </v-footer>
    <v-footer color="white" height="494px" :padless="padless">
      <v-col cols="12">
        <v-row justify="center"
          ><strong
            class="font-weight-medium"
            style="color: #046792; font-size: 36px"
            >Consult Top doctor online for any health concern</strong
          ></v-row
        ></v-col
      >
      <v-col cols="12">
        <v-row justify="center"
          ><strong
            class="font-weight-regular"
            style="color: #a3a3a3; font-size: 16px"
            >Private online consultations with verified doctors in all
            specialists</strong
          ></v-row
        ></v-col
      >
      <v-col cols="12">
        <v-row justify="center"
          ><v-card
            class="mx-2"
            v-for="(item, index) in card_link"
            :key="index"
            width="200px"
            height="134px"
            hover="true"
            style="background-color: #e6f0f5"
          >
          <v-row justify="center" class="mt-2">
         
            <v-avatar height="48px" width="48px" color="white"> <img src="@/assets/img/home/home_icon1.png" /> </v-avatar
            >
          </v-row>
           <br /><strong
              class="font-weight-regular ml-2"
              style="color: #046792; font-size: 16px"
              >{{ item.body }}</strong
            >
          </v-card></v-row
        >
      </v-col>
      <v-col cols="12">
        <v-row justify="center"
          ><v-btn
            color="#3685A8"
            rounded
            class="my-2 white--text"
            height="40px"
            width="160px"
            style="font-size: 12px"
          >
            See All Specialities
          </v-btn></v-row
        >
      </v-col>
    </v-footer>
  </div>
</template>

<script>
export default {
  data: () => ({
    links: ["Trang chủ", "Giới thiệu", "Quy trình", "Dịch vụ", "Liên hệ"],
    card_link: [
      {
        head: "Bác sĩ",
        body: "Đặt lịch hẹn",
      },
      {
        head: "Khám bệnh",
        body: "Với các chuyên bác sĩ",
      },
      {
        head: "Bác sĩ",
        body: "Đặt lịch hẹn",
      },
      {
        head: "Bác sĩ",
        body: "Đặt lịch hẹn",
      },
    ],
  }),

  methods: {
    login() {
      this.$router.push({ name: "Đăng nhập" });
    },
  },
};
</script>
