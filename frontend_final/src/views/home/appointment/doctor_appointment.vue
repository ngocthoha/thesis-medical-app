<template>
  <div>
    <v-card
      width="100%"
      class="d-flex flex-column justify-space-around align-center"
      min-height="316px"
      color="#EEF2F6"
    >
      <p class="font-weight-bold mt-12" style="color:#537DA5; font-size:40px">
        Tìm kiếm bác sĩ theo tên
      </p>
      <v-card width="640px" color="#EEF2F6" elevation="0">
        <p>
          Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet
          sint. Velit officia consequat duis enim velit mollit. Exercitation
          veniam consequat sunt nostrud amet.
        </p>
      </v-card>
      <v-card
        elevation="0"
        class="d-flex align-center mt-8 mb-12"
        width="720px"
        height="76px"
        outlined
      >
        <v-card elevation="0"
          ><p
            class="ma-0 font-weight-medium text-body-1 ml-3"
            style="color: #667085"
          >
            Search Title
          </p></v-card
        >
        <v-spacer />
        <v-divider inset vertical></v-divider>
        <v-menu>
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              color="#667085"
              elevation="0"
              class="font-weight-medium text-body-1 btn"
              text
              v-bind="attrs"
              v-on="on"
              width="135px"
              height="44px"
            >
              Địa điểm <v-icon right>mdi-chevron-down</v-icon>
            </v-btn>
          </template>
        </v-menu>
        <v-btn
          color="#537DA5"
          elevation="0"
          class="white--text btn font-weight-medium text-body-1 mr-3"
          >Tìm kiếm
          <v-img class="ml-3" src="@/assets/img/home/search_icon.svg"></v-img
        ></v-btn>
      </v-card>
    </v-card>
    <v-card
      width="100%"
      min-height="500px"
      color="#FCFCFD"
      class="py-12 px-16 d-flex justify-center"
    >
      <!-- <v-card width="100%" height="100%"> </v-card> -->
      <v-card
        class="mx-4 pa-8 d-flex flex-wrap justify-center justify-space-between align-space-around"
        width="1280"
        elevation="0"
        color="#FCFCFD"
      >
        <v-card
          v-for="n in 8"
          :key="n"
          class="mb-6 d-flex flex-column justify-center align-center"
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
                  <v-img src="@/assets/img/user/profile/avatar1.svg"></v-img>
                </v-avatar>
                <v-card class="d-flex flex-column ml-3" elevation="0">
                  <p class="text-body-2 mb-2 font-weight-bold">Đỗ Hồng Việt</p>
                  <p class="text-body-2 mb-3">Bệnh viện hữu nghị Việt Đức</p>
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
                  <p style="color: #537DA5">146</p>
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
                  <p style="color: #537DA5">146</p>
                </v-card>
              </div>
            </v-card>
          </v-card>

          <v-card class="mt-2" width="548" height="24" elevation="0">
            <v-divider
              class="mt-1"
              style="border-color: #f2f4f7 !important"
            ></v-divider>
            <div
              class="d-flex flex-row mt-4 align-center justify-space-between"
            >
              <div class="d-flex flex-row">
                <p>Giá khám:</p>
                <p>$70</p>
              </div>
              <v-btn width="110" height="44" class="">
                Đặt khám
              </v-btn>
            </div>
          </v-card>
        </v-card>
      </v-card>
    </v-card>
  </div>
</template>

<style scoped>
.btn {
  text-transform: none;
}
</style>
