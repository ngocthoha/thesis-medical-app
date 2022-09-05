<template>
  <div>
    <v-card width="922px" elevation="4">
      <!-- header section -->
      <v-card
        width="100%"
        height="132px"
        elevation="0"
        class="d-flex justify-space-between"
      >
        <div class="d-flex align-center ml-8">
          <div class="d-flex flex-column">
            <p class="font-weight-bold mb-3" style="font-size: 24px">
              Hồ sơ khám bệnh
            </p>
            <p style="color: #667085" class="text-body-1">
              Chọn và cài đặt hồ sơ mà bạn muốn theo dõi sức khoẻ
            </p>
          </div>
        </div>
        <div class="d-flex align-center mr-8">
          <v-btn
            color="#537DA5"
            class="white--text btn-not-transform text-body-2"
            ><v-icon left>mdi-account-plus-outline</v-icon> Thêm mới hồ
            sơ</v-btn
          >
        </div>
      </v-card>
      <v-divider
        style=" border-color: rgba(16, 24, 40, 0.03) !important;"
      ></v-divider>

      <!-- list profile -->
      <div class="d-flex justify-center mt-8">
        <v-card width="858px" elevation="0">
          <v-expansion-panels accordion tile flat>
            <v-expansion-panel
              v-for="n in 5"
              :key="n"
              class="mb-4 rounded-lg"
              style="border:#F2F4F7 1px solid"
            >
              <v-expansion-panel-header>
                <v-card class="d-flex flex-row align-center" elevation="0">
                  <v-avatar size="40">
                    <img src="@/assets/img/home/appbar/avatar.png" />
                  </v-avatar>
                  <div class="d-flex flex-column ml-3">
                    <p class="ma-0 font-weight-bold text-body-1">
                      Nguyễn Xuân Hoà
                    </p>
                    <p
                      class="ma-0 font-weight-normal text-body-2"
                      style="color: #667085"
                    >
                      Chủ tài khoản
                    </p>
                  </div>
                </v-card>
                <!-- button control -->
                <div class="d-flex justify-end">
                  <v-btn
                    fab
                    dark
                    small
                    elevation="0"
                    color="#EEF2F6"
                    width="32px"
                    height="32px"
                    class="d-flex justify-center mr-5"
                  >
                    <v-icon small color="#537DA5">
                      mdi-pencil-outline
                    </v-icon>
                  </v-btn>
                  <v-btn
                    fab
                    dark
                    small
                    elevation="0"
                    color="#FEF3F2"
                    width="32px"
                    height="32px"
                    class="d-flex justify-center  mr-5"
                  >
                    <v-icon small color="#F04438">
                      mdi-account-remove-outline
                    </v-icon>
                  </v-btn>
                </div>
                <template v-slot:actions>
                  <v-card
                    elevation="0"
                    rounded="circle"
                    color="#FCFCFD"
                    width="32px"
                    height="32px"
                    class="d-flex justify-center"
                  >
                    <v-icon small>
                      mdi-chevron-down
                    </v-icon>
                  </v-card>
                </template>
              </v-expansion-panel-header>

              <v-expansion-panel-content class="pa-0">
                <div class="d-flex flex-column" v-for="n in 9" :key="n">
                  <v-divider
                    style=" border-color: #F2F4F7 !important;"
                  ></v-divider>
                  <v-card
                    class="d-flex flec-row justify-space-between align-center"
                    elevation="0"
                    height="72px"
                  >
                    <p class="ma-0">Email</p>
                    <p class="ma-0">germanydesign@figma.com</p>
                  </v-card>
                </div>
              </v-expansion-panel-content>
            </v-expansion-panel>
          </v-expansion-panels>
        </v-card>
      </div>
    </v-card>
  </div>
</template>

<script>
import SelectDoctor from "./component/SelectDoctor.vue";
export default {
  components: {
    SelectDoctor
  }
};
</script>

<style scoped>
.btn-not-transform {
  text-transform: none;
}
</style>
