<template>
  <div>
    <v-card width="922px" elevation="4" class="pb-8">
      <div>
        <!-- header -->
        <v-card height="132" class="px-8 d-flex flex-row pt-5" elevation="0">
          <div>
            <p class="font-weight-bold mb-3" style="font-size: 24px">
              Hồ sơ sức khỏe
            </p>
            <p style="color: #667085" class="text-body-1">
              Theo dõi quá trình bạn sử dụng dịch vụ tại Blouse Care
            </p>
          </div>
          <v-spacer></v-spacer>
          <v-btn
            class="mr-6 btn-not-transform text-body-2 white--text"
            elevation="0"
            color="#537DA5"
            @click="close_view_appointment_detail"
            >Chọn hồ sơ</v-btn
          >
        </v-card>
        <v-divider
          style="border-color: rgba(16, 24, 40, 0.03) !important"
        ></v-divider>
        <!-- account -->
        <!-- body -->
        <v-card class="pa-8" elevation="0">
          <v-timeline>
            <v-timeline-item
              v-for="(year, i) in years"
              :key="i"
              :color="year.color"
              small
            >
              <template v-slot:opposite>
                <span
                  :class="`headline font-weight-bold ${year.color}--text`"
                  v-text="year.year"
                ></span>
              </template>
              <v-card color="#EEF2F6">
                <v-card-title style="color: #537DA5">
                  11:00 - 12:00
                </v-card-title>
                <v-card-text class="white text--primary pa-3">
                  <div class="d-flex flex-row">
                    <p class="ma-0 mr-3 font-weight-bold">Cơ sở y tế:</p>
                    <p class="ma-0">Bệnh viện đại học y dược</p>
                  </div>
                  <div class="d-flex flex-row">
                    <p class="ma-0 mr-3 font-weight-bold">Dịch vụ:</p>
                    <p class="ma-0">Khám theo nhu cầu</p>
                  </div>
                  <div class="d-flex flex-row mb-16">
                    <p class="ma-0 mr-3 font-weight-bold">Bác sĩ:</p>
                    <p class="ma-0">Nguyễn Xuân Đức</p>
                  </div>

                  <v-btn :color="year.color" outlined class="btn-not-transform">
                    Xem chi tiết
                  </v-btn>
                </v-card-text>
              </v-card>
            </v-timeline-item>
          </v-timeline>
          <v-dialog></v-dialog>
        </v-card>
      </div>
    </v-card>
  </div>
</template>
<script>
export default {
  data: () => ({
    years: [
      {
        color: "#537DA5",
        year: "17/12/1960"
      },
      {
        color: "#537DA5",
        year: "17/12/1960"
      },
      {
        color: "#537DA5",
        year: "17/12/1960"
      },
      {
        color: "#537DA5",
        year: "17/12/1960"
      },
      {
        color: "#537DA5",
        year: "17/12/1960"
      }
    ]
  })
};
</script>
<style scoped>
.btn-not-transform {
  text-transform: none;
}
</style>
