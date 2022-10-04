<template>
  <v-card color="#FCFCFD" width="100%" min height="1000" class="pa-12">
    <div
      class="d-flex flex-column flex-md-row mx-8 justify-center align-center"
    >
      <v-card
        width="800"
        min-height="500"
        elevation="0"
        color="#FCFCFD"
        class="align-self-start"
      >
        <!-- doctor detail -->
        <v-card class="d-flex flex-row pa-6" width="100%">
          <!-- avatar -->
          <v-avatar size="64" class="align-self-start">
            <v-img src="@/assets/img/user/profile/avatar1.svg"></v-img>
          </v-avatar>
          <!-- detail -->
          <v-card class="d-flex flex-column ml-3" elevation="0">
            <p class="text-body-2 mb-2 font-weight-bold">Đỗ Hồng Việt</p>
            <p class="text-body-2 mb-3">Bệnh viện hữu nghị Việt Đức</p>
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
            <v-card max-width="422" class="d-flex flex-wrap" elevation="0">
              <v-card
                v-for="n in 3"
                :key="n"
                class="text-body-2 mr-3 mb-3"
                color="#D0D5DD"
                outlined
              >
                <v-card
                  elevation="0"
                  color="#F9FAFB"
                  class="d-flex justify-center"
                >
                  Chuyên khoa TT Nam học</v-card
                >
              </v-card>
            </v-card>
          </v-card>
          <!-- online booking -->
          <v-spacer></v-spacer>
          <v-btn
            color="#537DA5"
            elevation="0"
            class="white--text btn font-weight-medium text-body-1 align-self-start"
            width="222"
            height="44"
          >
            <v-icon left size="20">mdi-video-outline</v-icon>
            Đặt tư vấn qua video
          </v-btn>
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
                      <v-icon class="align-self-start" size="24" color="#537da5"
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
                      <v-icon class="align-self-start" size="24" color="#537da5"
                        >mdi-map-marker-radius-outline</v-icon
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
      <v-card class="ml-8 align-self-start" width="448">
        <!-- header -->
        <v-card class="pa-6 rounded-b-0 d-flex flex-row" color="#537DA5">
          <v-card
            rounded="circle"
            width="32"
            height="32"
            class="pa-2 mr-3"
            color="#EEF2F6"
          >
            <v-img src="@/assets/img/home/appbar/doctor.svg"></v-img>
          </v-card>

          <p
            class="mb-0 white--text font-weight-medium"
            style="font-size: 20px"
          >
            Chọn khung giờ khám
          </p>
        </v-card>
        <!-- price -->
        <v-card elevation="0" class="pa-6 d-flex flex-column">
          <!--  -->
          <div class="d-flex flex-row align-center justify-space-between">
            <p class="text-body-1 font-weight-medium">Khám theo yêu cầu</p>
            <p class="ml-3 font-weight-bold" style="color: #537da5">
              100000 VND
            </p>
          </div>
          <!--  -->
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
              <v-icon color="#FFC107" class="align-self-start">mdi-star</v-icon>
              <p style="color: #537da5">146</p>
            </v-card>
          </div>
          <!-- location -->
          <v-card class="d-flex flex-row" elevation="0">
            <v-icon class="align-self-start" size="24" color="#537da5"
              >mdi-map-marker-radius-outline</v-icon
            >
            <v-card class="ml-3" elevation="0">
              <p
                class="text-body-2 mb-2 font-weight-medium"
                style="color: #667085"
              >
                08 Đ. Nguyễn Hữu Cảnh, Phường 22, Bình Thạnh, Thành phố Hồ Chí
                Minh
              </p>
            </v-card>
          </v-card>
        </v-card>
        <v-divider></v-divider>
        <!-- date picker -->
        <v-card class="d-flex flex-row justify-center pa-6" elevation="0">
          <p class="mb-0 d-flex align-center font-weight-medium">
            Ngày 21 tháng 8 năm 2022
          </p>
          <v-spacer></v-spacer>
          <v-btn icon>
            <v-icon>mdi-calendar-month-outline</v-icon>
          </v-btn>
        </v-card>
        <v-divider
          class="mt-1"
          style="border-color: #f2f4f7 !important"
        ></v-divider>
        <v-card elevation="0">
          <v-tabs
            color="#537DA5"
            slider-size="3"
            v-model="calander_tab"
            fixed-tabs
            height="60"
          >
            <v-tab
              style="text-transform: none"
              class="font-weight-medium text-body-1"
              :key="'online'"
              >Trực truyến</v-tab
            >
            <v-tab
              style="text-transform: none"
              class="font-weight-medium text-body-1"
              :key="'offline'"
              >Trực tiếp tại viện</v-tab
            >
          </v-tabs>
          <v-tabs-items v-model="calander_tab">
            <!-- online tab -->
            <v-tab-item :key="'online'">
              <v-card class="pa-6 d-flex flex-column" elevation="0">
                <!-- morning -->
                <p class="font-weight-medium">Sáng</p>
                <div class="d-flex flex-wrap justify-start">
                  <v-item-group v-model="selected">
                    <v-item
                      v-for="(item, idx) in time"
                      :key="idx"
                      v-slot="{ active, toggle }"
                      :value="item"
                    >
                      <v-btn
                        :outlined="active ? false : true"
                        color="#537DA5"
                        @click="toggle"
                        class="mb-5 mx-3"
                        active-class="white--text"
                        elevation="0"
                      >
                        <p
                          class="ma-0 font-weight-medium"
                          :style="active ? 'color:white' : ''"
                        >
                          {{ item }}
                        </p>
                      </v-btn>
                    </v-item>
                  </v-item-group>
                </div>
                <!-- afternoon -->
                <p class="font-weight-medium">Chiều</p>
                <div class="d-flex flex-wrap justify-start">
                  <v-item-group v-model="selected">
                    <v-item
                      v-for="(item, idx) in time"
                      :key="idx"
                      v-slot="{ active, toggle }"
                      :value="item"
                    >
                      <v-btn
                        :outlined="active ? false : true"
                        color="#537DA5"
                        @click="toggle"
                        class="mb-5 mx-3"
                        active-class="white--text"
                        elevation="0"
                      >
                        <p
                          class="ma-0 font-weight-medium"
                          :style="active ? 'color:white' : ''"
                        >
                          {{ item }}
                        </p>
                      </v-btn>
                    </v-item>
                  </v-item-group>
                </div>
                <!-- booking -->
                <v-btn elevation="0" color="#D4DFE9" class="btn"
                  ><v-icon left color="#537da5">mdi-plus-circle-outline</v-icon>
                  <p
                    class="ma-0 font-weight-bold text-body-1"
                    style="color: #537da5"
                  >
                    Đặt lịch
                  </p>
                </v-btn>
              </v-card>
            </v-tab-item>

            <!-- offline tab -->
            <v-tab-item :key="'offline'">
              <v-card class="pa-6" elevation="0">
                <!-- morning -->
                <p class="font-weight-medium">Sáng</p>
                <div class="d-flex flex-wrap justify-start">
                  <v-item-group v-model="selected">
                    <v-item
                      v-for="(item, idx) in time"
                      :key="idx"
                      v-slot="{ active, toggle }"
                      :value="item"
                    >
                      <v-btn
                        :outlined="active ? false : true"
                        color="#537DA5"
                        @click="toggle"
                        class="mb-5 mx-3"
                        active-class="white--text"
                        elevation="0"
                      >
                        <p
                          class="ma-0 font-weight-medium"
                          :style="active ? 'color:white' : ''"
                        >
                          {{ item }}
                        </p>
                      </v-btn>
                    </v-item>
                  </v-item-group>
                </div>
                <!-- afternoon -->
                <p class="font-weight-medium">Chiều</p>
                <div class="d-flex flex-wrap justify-start">
                  <v-item-group v-model="selected">
                    <v-item
                      v-for="(item, idx) in time"
                      :key="idx"
                      v-slot="{ active, toggle }"
                      :value="item"
                    >
                      <v-btn
                        :outlined="active ? false : true"
                        color="#537DA5"
                        @click="toggle"
                        class="mb-5 mx-3"
                        active-class="white--text"
                        elevation="0"
                      >
                        <p
                          class="ma-0 font-weight-medium"
                          :style="active ? 'color:white' : ''"
                        >
                          {{ item }}
                        </p>
                      </v-btn>
                    </v-item>
                  </v-item-group>
                </div>
                <!-- booking -->
                <v-btn elevation="0" color="#D4DFE9" class="btn"
                  ><v-icon left color="#537da5">mdi-plus-circle-outline</v-icon>
                  <p
                    class="ma-0 font-weight-bold text-body-1"
                    style="color: #537da5"
                  >
                    Đặt lịch
                  </p>
                </v-btn>
              </v-card>
            </v-tab-item>
          </v-tabs-items>
        </v-card>
      </v-card>
    </div>
  </v-card>
</template>

<script>
export default {
  setup() {},
  data() {
    return {
      tab: null,
      calander_tab: null,
      selected: null,
      time: [
        "09:00 - 09:30",
        "10:00 - 10:30",
        "11:00 - 11:30",
        "12:00 - 12:30",
      ],
    };
  },
};
</script>

<style scoped>
.btn {
  text-transform: none;
}
</style>
