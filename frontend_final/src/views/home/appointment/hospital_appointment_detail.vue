<template>
  <div>
    <v-card color="#FCFCFD" width="100%" class="pa-12">
      <div class="d-flex flex-column mx-8 justify-center align-center">
        <v-card min-height="500" width="1216" elevation="0" color="#FCFCFD">
          <!-- doctor detail -->
          <v-card class="d-flex flex-row pa-6" width="100%">
            <!-- avatar -->
            <v-card width="240" height="160" class="align-self-start">
              <v-img src="@/assets/img/home/hospital_avt.png"></v-img>
            </v-card>
            <!-- detail -->
            <v-card class="d-flex flex-column ml-5" elevation="0">
              <p class="mb-2 font-weight-bold" style="font-size: 20px">
                Bệnh viện hữu nghị Việt Đức
              </p>
              <div class="d-flex flex-row">
                <v-icon class="align-self-start mr-3" size="24" color="#537da5"
                  >mdi-map-marker-radius-outline</v-icon
                >
                <p class="text-body-2 mb-3">
                  08 Đ. Nguyễn Hữu Cảnh, Phường 22, Bình Thạnh, Thành phố Hồ Chí
                  Minh
                </p>
              </div>
              <div class="d-flex flex-row mb-3">
                <v-card
                  class="d-flex flex-row"
                  height="24"
                  width="56"
                  color="#EEF2F6"
                  elevation="0"
                >
                  <v-icon color="#537DA5" class="align-self-start"
                    >mdi-calendar-month-outline</v-icon
                  >
                  <p style="color: #537da5">146</p>
                </v-card>
                <v-card
                  class="d-flex flex-row ml-1"
                  height="24"
                  width="56"
                  color="#F9FAFB"
                  elevation="0"
                >
                  <v-icon color="#FFC107" class="align-self-start"
                    >mdi-star</v-icon
                  >
                  <p style="color: #537da5">146</p>
                </v-card>
              </div>
              <v-spacer></v-spacer>
            </v-card>
          </v-card>

          <!-- description -->
          <v-card
            width="100%"
            min-height="200"
            class="mt-8"
            color="#FCFCFD"
            elevation="0"
          >
            <v-card max-width="321" class="rounded-b-0">
              <v-tabs color="#537DA5" slider-size="0" v-model="tab">
                <v-tab
                  style="text-transform: none"
                  class="font-weight-medium text-body-1"
                  :key="'tab-1'"
                  >Thông tin</v-tab
                >
                <v-tab
                  style="text-transform: none"
                  class="font-weight-medium text-body-1"
                  :key="'tab-2'"
                  >Đánh giá</v-tab
                >
                <v-tab
                  style="text-transform: none"
                  class="font-weight-medium text-body-1"
                  :key="'tab-3'"
                  >Câu hỏi</v-tab
                >
              </v-tabs>
            </v-card>
            <v-card class="pa-6 rounded-t-0">
              <!-- all tab -->
              <v-tabs-items v-model="tab">
                <!-- infomation tab -->
                <v-tab-item :key="'tab-1'">
                  <v-card class="d-flex flex-column">
                    <p class="text-body-1 mb-6">
                      Là giảng viên của trường Đại học Y dược Thái Nguyên nhiều
                      năm kinh nghiệm, tận tình, nhiệt huyết. Đi đầu trong lĩnh
                      vực dịch vụ y tế tại nhà trong khu vực.
                    </p>
                    <div class="d-flex flex-row justify-space-between">
                      <v-card class="d-flex flex-row" width="45%" elevation="0">
                        <v-icon
                          class="align-self-start"
                          size="24"
                          color="#537da5"
                          >mdi-map-marker-radius-outline</v-icon
                        >
                        <v-card class="ml-3" elevation="0">
                          <p class="text-body-2 mb-2 font-weight-medium">
                            08 Đ. Nguyễn Hữu Cảnh, Phường 22, Bình Thạnh, Thành
                            phố Hồ Chí Minh
                          </p>
                          <p
                            class="text-body-2 font-weight-medium"
                            style="color: #537da5"
                          >
                            Hiện chỉ đường
                          </p>
                        </v-card>
                      </v-card>
                      <v-card class="d-flex flex-row" width="45%" elevation="0">
                        <v-icon
                          class="align-self-start"
                          size="24"
                          color="#537da5"
                          >mdi-clock-time-nine-outline</v-icon
                        >
                        <v-card class="ml-3" elevation="0">
                          <p class="text-body-2 mb-2 font-weight-medium">
                            Thứ 2 đến Thứ 7
                          </p>
                          <p
                            v-for="n in 3"
                            :key="n"
                            class="text-body-2 mb-0"
                            style="color: #667085"
                          >
                            10:15 AM - 02:30 PM
                          </p>
                        </v-card>
                      </v-card>
                    </div>
                  </v-card>
                </v-tab-item>

                <!-- evaluate tab -->
                <v-tab-item :key="'tab-2'">
                  <v-card> evaluate tab </v-card>
                </v-tab-item>

                <!-- question tab -->
                <v-tab-item :key="'tab-3'">
                  <v-card> question tab </v-card>
                </v-tab-item>
              </v-tabs-items>
            </v-card>
          </v-card>
        </v-card>
        <!-- servive of hospital -->
        <v-card
          width="100%"
          min-height="500px"
          color="#FCFCFD"
          class="py-12 px-16 d-flex justify-center"
          elevation="0"
        >
          <v-card
            class="mx-4 pa-8 d-flex flex-wrap justify-center"
            max-width="1280"
            elevation="0"
            color="#FCFCFD"
          >
            <v-card
              height="180"
              class="d-flex flex-column align-center"
              color="#FCFCFD"
              elevation="0"
            >
              <p class="font-weight-bold text-body-1" style="color: #537da5">
                Dịch vụ
              </p>
              <p class="font-weight-bold" style="font-size: 40px">
                Tất cả các dịch vụ
              </p>
            </v-card>
            <!-- list service -->
            <v-row justify="center">
              <v-col v-for="n in 10" :key="n" md="5">
                <!-- in -->
                <v-card
                  class="d-flex flex-column pa-6"
                  @click="move_to_service_info"
                >
                  <v-card elevation="0" class="d-flex flex-row">
                    <div class="d-flex flex-row">
                      <v-card
                        width="126"
                        height="84"
                        class="align-self-start"
                        elevation="0"
                      >
                        <v-img src="@/assets/img/home/service_avt.png"></v-img>
                      </v-card>
                      <v-card class="d-flex flex-column ml-3" elevation="0">
                        <!-- service name -->
                        <p class="text-body-2 mb-2 font-weight-bold">
                          Khám Cơ - Xương -Khớp và điều trị ngoại trú
                        </p>

                        <p class="text-body-2 mb-3">
                          Bệnh viện đa khoa Việt Đức
                        </p>

                        <div
                          class="d-flex flex-row align-center justify-space-between"
                        >
                          <p style="color: #537da5" class="ma-0">100.000 VND</p>

                          <v-icon small color="#537da5">mdi-arrow-right</v-icon>
                        </div>
                      </v-card>
                    </div>
                  </v-card>
                </v-card></v-col
              >
            </v-row>

            <!-- pagination -->
            <v-card
              width="100%"
              elevation="0"
              color="#FCFCFD"
              class="d-flex justify-center mt-10"
            >
              <div class="text-center">
                <v-pagination
                  color="#537DA5"
                  v-model="page"
                  :length="15"
                  :total-visible="7"
                ></v-pagination>
              </div>
            </v-card>
          </v-card>
        </v-card>
      </div>
    </v-card>
    <v-card color="#EEF2F6" width="100%" class="pa-12">
      <!-- doctor of hospital -->
      <v-card
        width="100%"
        min-height="500px"
        color="#EEF2F6"
        class="py-12 px-16 d-flex justify-center"
        elevation="0"
      >
        <v-card
          class="mx-4 pa-8 d-flex flex-wrap justify-center"
          max-width="1280"
          elevation="0"
          color="#EEF2F6"
        >
          <v-card
            height="180"
            class="d-flex flex-column align-center"
            color="#EEF2F6"
            elevation="0"
          >
            <p class="font-weight-bold text-body-1" style="color: #537da5">
              Bác sĩ
            </p>
            <p class="font-weight-bold" style="font-size: 40px">
              Đặt khám theo bác sĩ
            </p>
          </v-card>
          <!-- list doctor -->
          <v-row justify="center">
            <v-col v-for="n in 8" :key="n" md="6">
              <!-- in -->
              <v-card
                class="d-flex flex-column justify-center align-center"
                width="596"
                height="216"
              >
                <v-card
                  width="548"
                  height="84"
                  elevation="0"
                  class="d-flex flex-column"
                >
                  <v-card
                    width="100%"
                    class="d-flex flex-row justify-space-between"
                    elevation="0"
                  >
                    <div class="d-flex flex-row">
                      <v-avatar size="64" class="align-self-start">
                        <v-img
                          src="@/assets/img/user/profile/avatar1.svg"
                        ></v-img>
                      </v-avatar>
                      <v-card class="d-flex flex-column ml-3" elevation="0">
                        <p class="text-body-2 mb-2 font-weight-bold">
                          Đỗ Hồng Việt
                        </p>
                        <p class="text-body-2 mb-3">
                          Bệnh viện hữu nghị Việt Đức
                        </p>
                        <v-card class="text-body-2" color="#D0D5DD" outlined>
                          <v-card
                            elevation="0"
                            color="#F9FAFB"
                            class="d-flex justify-center"
                          >
                            Chuyên khoa TT Nam học</v-card
                          >
                        </v-card>
                      </v-card>
                    </div>
                    <div class="d-flex flex-row">
                      <v-card
                        class="d-flex flex-row"
                        height="24"
                        width="56"
                        color="#EEF2F6"
                        elevation="0"
                      >
                        <v-icon color="#537DA5" class="align-self-start"
                          >mdi-calendar-month-outline</v-icon
                        >
                        <p style="color: #537da5">146</p>
                      </v-card>
                      <v-card
                        class="d-flex flex-row ml-1"
                        height="24"
                        width="56"
                        color="#F9FAFB"
                        elevation="0"
                      >
                        <v-icon color="#FFC107" class="align-self-start"
                          >mdi-star</v-icon
                        >
                        <p style="color: #537da5">146</p>
                      </v-card>
                    </div>
                  </v-card>
                </v-card>
                <!-- price and booking -->
                <v-card class="mt-2" width="548" height="40" elevation="0">
                  <v-divider
                    class="mt-1"
                    style="border-color: #f2f4f7 !important"
                  ></v-divider>
                  <div
                    class="d-flex flex-row mt-4 align-center justify-space-between"
                  >
                    <div class="d-flex flex-row align-center">
                      <p>Giá khám:</p>
                      <p
                        class="ml-3 font-weight-bold"
                        style="color: #537da5; font-size: 20px"
                      >
                        100000VND
                      </p>
                    </div>
                    <v-btn
                      width="110"
                      height="44"
                      class="white--text btn text-body-2"
                      elevation="0"
                      color="#537DA5"
                      @click="move_to_doctor_info"
                    >
                      Đặt khám
                    </v-btn>
                  </div>
                </v-card>
              </v-card></v-col
            >
          </v-row>

          <!-- pagination -->
          <v-card
            width="100%"
            elevation="0"
            color="#EEF2F6"
            class="d-flex justify-center justify-lg-end mt-10"
          >
            <div class="text-center">
              <v-pagination
                color="#537DA5"
                v-model="page"
                :length="15"
                :total-visible="7"
              ></v-pagination>
            </div>
          </v-card>
        </v-card>
      </v-card>
    </v-card>
  </div>
</template>

<script>
export default {
  setup() {},
  data() {
    return {
      page: 2,
      tab: null,
      calander_tab: null,
      selected: null,
      morning_time: [
        "09:00 - 09:30",
        "10:00 - 10:30",
        "11:00 - 11:30",
        "12:00 - 12:30",
      ],
      affternoon_time: [
        "13:00 - 13:30",
        "14:00 - 14:30",
        "15:00 - 15:30",
        "16:00 - 16:30",
      ],
    };
  },
  methods: {
    move_to_service_info() {
      this.$router
        .push({ name: "Thông tin đặt lịch dịch vụ" })
        .catch((error) => {
          if (error == null) {
            return;
          }
          if (error.name != "NavigationDuplicated") {
            throw error;
          }
        });
    },

    move_to_doctor_info() {
      this.$router
        .push({ name: "Thông tin đặt lịch bác sĩ" })
        .catch((error) => {
          if (error == null) {
            return;
          }
          if (error.name != "NavigationDuplicated") {
            throw error;
          }
        });
    },
  },
};
</script>

<style scoped>
.btn {
  text-transform: none;
}
</style>
