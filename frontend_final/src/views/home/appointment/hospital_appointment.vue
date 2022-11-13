<template>
  <div>
    <v-card
      width="100%"
      class="d-flex flex-column justify-space-around align-center"
      min-height="316px"
      color="#EEF2F6"
    >
      <p class="font-weight-bold mt-12" style="color: #537da5; font-size: 40px">
        Tìm kiếm bệnh viện theo tên
      </p>
      <v-card width="640px" color="#EEF2F6" elevation="0">
        <!-- <p>
          Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet
          sint. Velit officia consequat duis enim velit mollit. Exercitation
          veniam consequat sunt nostrud amet.
        </p> -->
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
        <!-- list hospital -->
        <v-row justify="center">
          <v-col
            v-for="(hospital, iHospital) in hospital_list"
            :key="iHospital"
            md="6"
          >
            <!-- in -->
            <v-card
              class="d-flex flex-column justify-center align-center px-5"
              width="596"
              height="244"
            >
              <v-card
                width="548"
                height="112"
                elevation="0"
                class="d-flex flex-column"
              >
                <v-card
                  width="100%"
                  class="d-flex flex-row justify-space-between"
                  elevation="0"
                >
                  <div class="d-flex flex-row">
                    <v-card width="126" height="84" class="align-self-start">
                      <v-img :src="getImgOfHospital(hospital)"></v-img>
                    </v-card>
                    <v-card class="d-flex flex-column ml-3" elevation="0">
                      <p class="text-body-2 mb-2 font-weight-bold">
                        {{ hospital.name }}
                      </p>
                      <div class="d-flex flex-row">
                        <v-icon
                          class="align-self-start mr-3"
                          size="24"
                          color="#537da5"
                          >mdi-map-marker-radius-outline</v-icon
                        >
                        <p class="text-body-2 mb-3">
                          {{ get_hospital_address(hospital) }}
                        </p>
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
                          <p style="color: #537da5">
                            {{ hospital.registrationNumber }}
                          </p>
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
                          <p style="color: #537da5">{{ hospital.favorite }}</p>
                        </v-card>
                      </div>
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
                  <v-btn
                    width="110"
                    height="44"
                    class="white--text btn text-body-2"
                    elevation="0"
                    color="#537DA5"
                    @click="moveToInfo(hospital)"
                  >
                    Xem chi tiết
                  </v-btn>
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
              :length="totalPages"
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
  created() {
    this.get_hospital_list();
  },

  data() {
    return {
      page: 2,
      hospital_list: [],
      params: {
        filters: [],
        sorts: [],
        page: 0,
        size: 8
      },
      page: 1,
      totalPages: 0
    };
  },
  watch: {
    page: {
      handler() {
        this.get_hospital_list();
      }
    }
  },
  methods: {
    async moveToInfo(hospital) {
      await this.$store.dispatch("hospital/set_hospital_select_info", hospital);
      this.$router
        .push({ name: "Thông tin đặt lịch bệnh viện" })
        .catch(error => {
          if (error == null) {
            return;
          }
          if (error.name != "NavigationDuplicated") {
            throw error;
          }
        });
    },

    async get_hospital_list() {
      const params = this._.cloneDeep(this.params);
      let res = await this.$store.dispatch(
        "hospital/read_all_hospital",
        params
      );
      this.totalPages = res.meta?.totalPages;
      let hospital_all_data = this.$store.getters["hospital/hospital_all_data"];
      this.hospital_list = hospital_all_data;
    },

    get_hospital_address(hospital) {
      let address_str = "";
      if (hospital.address.address != null) {
        if (address_str.length == 0) {
          address_str = hospital.address.address;
        }
      }
      if (hospital.address.ward != null) {
        address_str =
          address_str.length == 0
            ? hospital.address.ward
            : address_str + ", " + hospital.address.ward;
      }
      if (hospital.address.district != null) {
        address_str =
          address_str.length == 0
            ? hospital.address.district
            : address_str + ", " + hospital.address.district;
      }
      if (hospital.address.province != null) {
        address_str =
          address_str.length == 0
            ? hospital.address.province
            : address_str + ", " + hospital.address.province;
      }

      if (hospital.address.country != null) {
        address_str =
          address_str.length == 0
            ? hospital.address.country
            : address_str + ", " + hospital.address.country;
      }
      return address_str;
    },

    getImgOfHospital(hospital) {
      if (hospital.imageUrl != null) {
        return hospital.imageUrl;
      } else {
        return require("@/assets/img/home/hospital_avt.png");
      }
    }
  }
};
</script>
<style scoped>
.btn {
  text-transform: none;
}
</style>
