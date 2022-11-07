<template>
  <div>
    <!-- header -->
    <v-card height="132" class="px-8" elevation="0">
      <p class="font-weight-bold mb-3 pt-5" style="font-size: 24px">
        Lịch sử đặt khám
      </p>
      <p style="color: #667085" class="text-body-1">
        Danh sách và thông tin các cuộc hẹn gặp khám của bạn
      </p>
    </v-card>
    <v-divider
      style="border-color: rgba(16, 24, 40, 0.03) !important"
    ></v-divider>
    <!-- body -->
    <div class="d-flex justify-center mt-8 px-8">
      <v-card class="d-flex flex-column" width="100%" elevation="0">
        <v-card
          width="100%"
          height="120"
          class="d-flex flex-row mb-5"
          v-for="n in 3"
          :key="n"
          elevation="1"
        >
          <v-card
            elevation="0"
            color="#EEF2F6"
            tile
            width="20%"
            class="d-flex flex-column justify-center align-center"
          >
            <p class="ma-0 font-weight-bold text-body-1" style="color:#537DA5">
              24/10/2022
            </p>
            <p class="ma-0 font-weight-medium text-body-2">11:00 - 12:00</p>
            <p
              class="ma-0 font-weight-medium text-body-2"
              style="color: #667085"
              v-if="n == 1"
            >
              Chưa tiến hành
            </p>

            <p
              class="ma-0 font-weight-medium text-body-2"
              style="color: #12B76A"
              v-if="n == 2"
            >
              Đã hoàn tất
            </p>
            <p
              class="ma-0 font-weight-medium text-body-2"
              style="color: red"
              v-if="n == 3"
            >
              Đã hủy
            </p>
          </v-card>

          <v-card class="d-flex flex-row align-center ml-3" elevation="0">
            <v-avatar size="40">
              <img src="@/assets/img/home/appbar/avatar.png" />
            </v-avatar>
            <div class="d-flex flex-column ml-3">
              <p class="ma-0 font-weight-bold text-body-1">
                Nguyễn Duy Thanh
              </p>
              <p
                class="ma-0 font-weight-normal text-body-2"
                style="color: #667085"
              >
                chủ tài khoản
              </p>
            </div>
          </v-card>
          <v-spacer></v-spacer>
          <v-card
            class="d-flex flex-column justify-center align-center px-5"
            elevation="0"
          >
            <v-btn
              elevation="0"
              color="#537DA5"
              class="white--text btn-not-transform font-weight-bold"
              >Chi tiết</v-btn
            >

            <!-- <v-card>Chưa khám</v-card>
          <v-btn
            elevation="0"
            color="#537DA5"
            class="white--text btn-not-transform font-weight-bold"
            >Chi tiết</v-btn
          > -->
          </v-card>
        </v-card>
      </v-card>
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    view_detail_pointment() {
      this;
    }
  }
};
</script>
<style scoped>
.btn-not-transform {
  text-transform: none;
}
</style>
