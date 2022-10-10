<template>
  <div>
    <v-card
      width="100%"
      class="d-flex flex-column justify-space-around align-center"
      min-height="316px"
      color="#EEF2F6"
    >
      <p class="font-weight-bold mt-12" style="color: #537da5; font-size: 40px">
        Tìm kiếm dịch vụ
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
            <v-card class="d-flex flex-column pa-6" @click="moveToInfo">
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

                    <p class="text-body-2 mb-3">Bệnh viện đa khoa Việt Đức</p>

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
        <v-card width="100%" elevation="0" class="d-flex justify-center mt-10">
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
</template>

<script>
export default {
  setup() {},
  data() {
    return {
      page: 2,
    };
  },

  methods: {
    moveToInfo() {
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
  },
};
</script>
<style scoped>
.btn {
  text-transform: none;
}
</style>
