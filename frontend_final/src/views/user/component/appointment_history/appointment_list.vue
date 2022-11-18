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
          v-for="appointment in appointment_list"
          :key="appointment.id"
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
              {{ appointment.date }}
            </p>
            <p class="ma-0 font-weight-medium text-body-2">
              {{ appointment.time }}
            </p>
            <p
              class="ma-0 font-weight-medium text-body-2"
              style="color: #667085"
              v-if="appointment.status === 'PENDING'"
            >
              Chưa tiến hành
            </p>

            <p
              class="ma-0 font-weight-medium text-body-2"
              style="color: #12B76A"
              v-if="appointment.status === 'COMPLETE'"
            >
              Đã hoàn tất
            </p>
            <!-- <p
              class="ma-0 font-weight-medium text-body-2"
              style="color: red"
              v-if="n == 3"
            >
              Đã hủy
            </p> -->
          </v-card>

          <v-card class="d-flex flex-row align-center ml-3" elevation="0">
            <v-avatar size="40">
              <img src="@/assets/img/home/appbar/avatar.png" />
            </v-avatar>
            <div class="d-flex flex-column ml-3">
              <p class="ma-0 font-weight-bold text-body-1">
                {{ appointment.profile.lastName }}
              </p>
              <p
                class="ma-0 font-weight-normal text-body-2"
                style="color: #667085"
              >
                {{ appointment.profile.firstName }}
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
              @click="view_appointment_detail(appointment)"
              >Chi tiết</v-btn
            >
          </v-card>
        </v-card>
      </v-card>
    </div>
  </div>
</template>

<script>
export default {
  created() {
    this.get_appointment_by_user();
  },
  data() {
    return {
      appointment_list: []
    };
  },
  methods: {
    view_appointment_detail(appointment) {
      console.log(appointment);
      this.$emit("viewDetail", appointment);
    },

    async get_appointment_by_user() {
      const params = {
        token: this.$store.getters["auth/access_token"]
      };
      await this.$store.dispatch("appointment/get_appointment_by_user", params);
      this.appointment_list = this.$store.getters[
        "appointment/user_appointment_list"
      ];
    }
  }
};
</script>
<style scoped>
.btn-not-transform {
  text-transform: none;
}
</style>
