<template>
  <div>
    <v-card color="#FCFCFD" width="100%" class="pa-12">
      <div class="d-flex flex-column mx-8 justify-center align-center">
        <v-card min-height="500" width="1216" elevation="0" color="#FCFCFD">
          <!-- doctor detail -->
          <v-card class="d-flex flex-row pa-6" width="100%">
            <!-- avatar -->
            <v-card width="240" height="160" class="align-self-start">
              <v-img :src="getImgOfHospital(this.hospital_info)"></v-img>
            </v-card>
            <!-- detail -->
            <v-card class="d-flex flex-column ml-5" elevation="0">
              <p class="mb-2 font-weight-bold" style="font-size: 20px">
                {{ this.hospital_info.name }}
              </p>
              <div class="d-flex flex-row">
                <v-icon class="align-self-start mr-3" size="24" color="#537da5"
                  >mdi-map-marker-radius-outline</v-icon
                >
                <p class="text-body-2 mb-3">
                  {{ this.get_hospital_address(this.hospital_info) }}
                </p>
              </div>
              <div class="d-flex flex-row mb-3">
                <v-card
                  class="d-flex flex-row"
                  height="24"
                  color="#EEF2F6"
                  elevation="0"
                >
                  <v-icon color="#537DA5" class="align-self-start mr-3"
                    >mdi-calendar-month-outline</v-icon
                  >
                  <p style="color: #537da5">
                    {{ this.hospital_info.registrationNumber }}
                  </p>
                </v-card>
                <v-card
                  class="d-flex flex-row ml-1"
                  height="24"
                  width="56"
                  color="#F9FAFB"
                  elevation="0"
                >
                  <v-icon color="#FFC107" class="align-self-start mr-3"
                    >mdi-star</v-icon
                  >
                  <p style="color: #537da5">4.8</p>
                </v-card>
              </div>
              <div class="d-flex flex-row">
                <v-icon class="align-self-start mr-3" size="24" color="#537da5"
                  >mdi-clock-time-nine-outline</v-icon
                >
                <p v-if="this.hospital_info.isActive" class="text-body-2">
                  Có lịch làm việc
                </p>
                <p v-else class="text-body-2">Không có lịch làm việc</p>

                <a
                  class="text-body-2 ml-3 text-decoration-underline"
                  @click="hospital_hour_dialog = true"
                >
                  (Xem lịch)
                </a>

                <v-dialog v-model="hospital_hour_dialog" width="300">
                  <v-card class="d-flex flex-column pa-5 align-center">
                    <p class="font-weight-bold">Giờ làm việc</p>
                    <v-card width="100%" elevation="0">
                      <v-card class="d-flex flex-row" elevation="0">
                        <p class="ma-0 font-weight-bold">Thứ hai:</p>
                        <v-spacer></v-spacer>
                        <p>{{ this.hospital_info.hospitalHour.mondayTime }}</p>
                      </v-card>
                      <v-card class="d-flex flex-row" elevation="0">
                        <p class="ma-0 font-weight-bold">Thứ ba:</p>
                        <v-spacer></v-spacer>
                        <p>{{ this.hospital_info.hospitalHour.tuesdayTime }}</p>
                      </v-card>
                      <v-card class="d-flex flex-row" elevation="0">
                        <p class="ma-0 font-weight-bold">Thứ tư:</p>
                        <v-spacer></v-spacer>
                        <p>
                          {{ this.hospital_info.hospitalHour.wednesdayTime }}
                        </p>
                      </v-card>
                      <v-card class="d-flex flex-row" elevation="0">
                        <p class="ma-0 font-weight-bold">Thứ năm:</p>
                        <v-spacer></v-spacer>
                        <p>
                          {{ this.hospital_info.hospitalHour.thursdayTime }}
                        </p>
                      </v-card>
                      <v-card class="d-flex flex-row" elevation="0">
                        <p class="ma-0 font-weight-bold">Thứ sáu:</p>
                        <v-spacer></v-spacer>
                        <p>
                          {{ this.hospital_info.hospitalHour.fridayTime }}
                        </p>
                      </v-card>
                      <v-card class="d-flex flex-row" elevation="0">
                        <p class="ma-0 font-weight-bold">Thứ bảy:</p>
                        <v-spacer></v-spacer>
                        <p>
                          {{ this.hospital_info.hospitalHour.saturdayTime }}
                        </p>
                      </v-card>
                      <v-card class="d-flex flex-row" elevation="0">
                        <p class="ma-0 font-weight-bold">Chủ nhật</p>
                        <v-spacer></v-spacer>
                        <p>
                          {{ this.hospital_info.hospitalHour.sundayTime }}
                        </p>
                      </v-card>
                    </v-card>
                  </v-card>
                </v-dialog>
              </div>
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
                  >{{ titleFavorite }}</v-tab
                >
                <v-tab
                  style="text-transform: none"
                  class="font-weight-medium text-body-1"
                  :key="'tab-3'"
                  >{{ titleQuestion }}</v-tab
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
                      {{ this.hospital_info.info }}
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
                            {{ this.get_hospital_address(this.hospital_info) }}
                          </p>
                          <p
                            class="text-body-2 font-weight-medium"
                            style="color: #537da5"
                          >
                            Hiện chỉ đường
                          </p>
                        </v-card>
                      </v-card>
                    </div>
                  </v-card>
                </v-tab-item>

                <!-- evaluate tab -->
                <v-tab-item :key="'tab-2'">
                  <favorite
                    v-if="hospital_info && username"
                    :username="username"
                    :object="hospital_info"
                    @reloadFavorite="reloadFavorite"
                    @setNumFavorite="numFavorite = $event"
                    :key="keyFavorite"
                  ></favorite>
                </v-tab-item>

                <!-- question tab -->
                <v-tab-item :key="'tab-3'">
                  <question
                    v-if="hospital_info && username"
                    :username="username"
                    :object="hospital_info"
                    @reloadQuestion="reloadQuestion"
                    @setNumQuestion="numQuestion = $event"
                    :key="keyQuestion"
                  ></question>
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
            width="100%"
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
            <v-col v-for="doctor in doctor_list" :key="doctor.id" md="6">
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
                          {{doctor.level}}. {{ doctor.name }}
                        </p>
                        <p class="text-body-2 mb-3">
                          {{ doctor.hospital.name }}
                        </p>
                        <v-card class="text-body-2" color="#D0D5DD" outlined>
                          <v-card
                            elevation="0"
                            color="#F9FAFB"
                            class="d-flex justify-center"
                          >
                            {{ doctor.specialty }}</v-card
                          >
                        </v-card>
                      </v-card>
                    </div>
                    <div class="d-flex flex-row">
                      <v-card
                        class="d-flex flex-row"
                        height="24"
                        color="#EEF2F6"
                        elevation="0"
                      >
                        <v-icon color="#537DA5" class="align-self-start mr-3"
                          >mdi-calendar-month-outline</v-icon
                        >
                        <p style="color: #537da5">
                          {{ doctor.registrationNumber }}
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
                        <p style="color: #537da5">{{ doctor.favorite }}</p>
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
                        {{ doctor.price }} đ
                      </p>
                    </div>
                    <v-btn
                      width="110"
                      height="44"
                      class="white--text btn text-body-2"
                      elevation="0"
                      color="#537DA5"
                      @click="move_to_doctor_info(doctor)"
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
            class="d-flex justify-center mt-10"
          >
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
    </v-card>
  </div>
</template>

<script>
import Favorite from "../Component/favorite.vue";
import Question from "../Component/question.vue";

export default {
  components: {
    Favorite,
    Question
  },
  created() {
    this.hospital_info = this.$store.getters["hospital/hospital_selected"];
    this.username = this.$store.getters["auth/username"];
    this.get_doctor_by_hospital();
  },
  data() {
    return {
      page: 1,
      totalPages: 0,
      tab: null,
      calander_tab: null,
      selected: null,
      hospital_hour_dialog: false,
      hospital_info: {},
      doctor_list: [],
      rating: 0,
      username: null,
      keyFavorite: 0,
      keyQuestion: 0,
      numFavorite: 0,
      numQuestion: 0
    };
  },
  watch: {
    page: {
      handler() {
        this.get_doctor_by_hospital();
      }
    }
  },
  computed: {
    titleFavorite() {
      if (this.tab !== 1) return "Đánh giá";
      return `Đánh giá (${this.numFavorite})`;
    },
    titleQuestion() {
      if (this.tab !== 2) return "Câu hỏi";
      return `Câu hỏi (${this.numQuestion})`;
    }
  },
  methods: {
    move_to_service_info() {
      this.$router.push({ name: "Thông tin đặt lịch dịch vụ" }).catch(error => {
        if (error == null) {
          return;
        }
        if (error.name != "NavigationDuplicated") {
          throw error;
        }
      });
    },
    reloadFavorite() {
      this.keyFavorite++;
    },
    reloadQuestion() {
      this.keyQuestion++;
    },
    async move_to_doctor_info(doctor) {
      await this.$store.dispatch(
        "appointment/set_doctor_select_to_make_appointment",
        doctor
      );
      this.$router.push({ name: "Thông tin đặt lịch bác sĩ" }).catch(error => {
        if (error == null) {
          return;
        }
        if (error.name != "NavigationDuplicated") {
          throw error;
        }
      });
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

    async get_doctor_by_hospital() {
      const param = {
        hospitalId: this.hospital_info.id,
        page: this.page - 1,
        size: 8
      };
      let res = await this.$store.dispatch(
        "hospital/get_doctor_by_hospital",
        param
      );
      this.totalPages = res.meta?.totalPages;
      this.doctor_list = this.$store.getters["hospital/doctor_by_hospital"];
    },

    getImgOfHospital(hospital) {
      if (hospital.hospitalImageUrl != null) {
        return hospital.hospitalImageUrl;
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
