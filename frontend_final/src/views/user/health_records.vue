<template>
  <div>
    <v-card width="922px" elevation="1" class="pb-8">
      <div class="d-flex flex-column">
        <!-- header -->
        <v-card height="132" class="px-8 d-flex flex-row pt-5" elevation="0">
          <div>
            <p class="font-weight-bold mb-3" style="font-size: 24px">
              Hồ sơ sức khỏe
            </p>
            <p style="color: #667085" class="text-body-1">
              Theo dõi sức khỏe trong quá trình bạn sử dụng dịch vụ tại Blouse
              Care
            </p>
          </div>
          <v-spacer></v-spacer>
          <v-btn
            class="mr-6 btn-not-transform text-body-2 white--text"
            elevation="0"
            color="#537DA5"
            @click="profile_list_dialog = true"
            >Chọn hồ sơ</v-btn
          >
        </v-card>
        <v-divider
          style="border-color: rgba(16, 24, 40, 0.03) !important"
        ></v-divider>
        <div class="d-flex justify-center mt-8 px-8">
          <v-progress-circular
            v-if="_.isEmpty(selectedProfile)"
            indeterminate
            color="primary"
          ></v-progress-circular>
        </div>
        <!-- profile list dialog-->
        <v-dialog v-model="profile_list_dialog" width="600">
          <v-card class="pa-4">
            <v-card
              class="d-flex align-center justify-center pb-3"
              elevation="0"
            >
              <h4>
                Vui Lòng Chọn Hồ Sơ Bệnh Nhân
              </h4>
            </v-card>
            <v-divider
              style="border-color: rgba(16, 24, 40, 0.03) !important"
            ></v-divider>
            <v-radio-group v-model="radioGroup" class="ma-0">
              <v-card
                v-for="(profile, i) in profiles"
                :key="i"
                class="pa-6 d-flex flex-row"
                tile
                elevation="0"
              >
                <v-avatar size="48" class="mr-5">
                  <v-img :src="getImgOfProfile(profile)"></v-img>
                </v-avatar>
                <div class="d-flex flex-column justify-center">
                  <p class="mb-2 font-weight-bold">
                    {{ profile.lastName }} {{ profile.firstName }}
                  </p>
                  <p class="ma-0 text-body-2" style="color: #667085">
                    {{ profile.relationship }}
                  </p>
                </div>
                <v-spacer></v-spacer>
                <v-radio :value="profile" color="#537DA5"></v-radio>
              </v-card>
            </v-radio-group>
            <v-btn
              class="mt-8  white--text text-body-1 btn-not-transform"
              color="#537DA5"
              elevation="0"
              width="100%"
              @click="view_health_record()"
              >Xác nhận
            </v-btn>
          </v-card>
        </v-dialog>
        <!--  -->
        <!-- medical record dialog -->
        <v-dialog v-model="medical_record_dialog" width="800">
          <v-card
            tile
            class="pa-3 d-flex flex-row justify-space-between align-center"
            elevation="0"
            color="#EEF2F6"
            ><p
              class="ma-0 font-weight-bold white--text"
              style="color: #537DA5; font-size:20px"
            >
              Kết quả khám
            </p>
            <v-btn icon @click="medical_record_dialog = false"
              ><v-icon color="#537DA5"> mdi-close</v-icon>
            </v-btn>
          </v-card>
          <v-card class="d-flex flex-column pa-5" tile>
            <p class="font-weight-bold">Chuẩn đoán</p>
            <v-card outlined class="mb-6 pa-3">
              <p style="color: #667085" class="ma-0">Bệnh thống phong</p>
            </v-card>
            <p class="font-weight-bold">Ghi chú</p>
            <v-card outlined class="mb-6 pa-3">
              <p style="color: #667085" class="ma-0">Tránh ăn thức ăn đạm</p>
            </v-card>
            <p class="font-weight-bold">Đơn thuốc</p>
            <v-card class="d-flex flex-column mb-6" outlined>
              <!-- medicine list -->
              <v-data-table
                :headers="prescriptions_header"
                :items="prescriptions"
                checkbox-color="#3C5E7E"
                hide-default-footer
                no-data-text="Đơn thuốc trống"
              >
              </v-data-table>
            </v-card>
            <p class="font-weight-bold">Kết quả cận lâm sàn</p>
          </v-card>
        </v-dialog>
        <!-- profile -->
        <v-card
          v-if="!_.isEmpty(selectedProfile)"
          class="pa-6 d-flex flex-row"
          tile
          elevation="0"
          width="100%"
        >
          <v-avatar size="48" class="mr-5">
            <v-img :src="getImgOfProfile(selectedProfile)"></v-img>
          </v-avatar>
          <div class="d-flex flex-column justify-center">
            <p class="mb-2 font-weight-bold">
              {{ selectedProfile.lastName }} {{ selectedProfile.firstName }}
            </p>
            <p class="ma-0 text-body-2" style="color: #667085">
              {{ selectedProfile.relationship }}
            </p>
          </div>
        </v-card>
        <v-divider
          style="border-color: rgba(16, 24, 40, 0.03) !important"
        ></v-divider>

        <!-- body -->
        <v-card v-if="records.length" class="pa-8" elevation="0">
          <v-scroll-y-transition>
            <v-timeline dense>
              <v-timeline-item v-for="(record, i) in records" :key="i" small>
                <template v-slot:opposite>
                  <span class="headline font-weight-bold">{{
                    getDate(record.appointment.date)
                  }}</span>
                </template>
                <v-card color="#537DA5">
                  <v-card-title class="white--text" style="font-size: 16px">
                    {{ getServiceName(record.appointment) }}
                    <v-spacer></v-spacer>
                    {{ record.appointment.time }},
                    {{ getDate(record.appointment.date) }}
                  </v-card-title>
                  <v-card-text class="white text--primary pa-3">
                    <div class="d-flex flex-row">
                      <p class="ma-0 mr-3 font-weight-bold">Cơ sở y tế:</p>
                      <p class="ma-0">
                        {{
                          record.appointment.category === "DOCTOR"
                            ? record.appointment.doctor.hospital.name
                            : record.appointment.service.hospital.name
                        }}
                      </p>
                    </div>
                    <div class="d-flex flex-row">
                      <p class="ma-0 mr-3 font-weight-bold">
                        Chuẩn đoán:
                      </p>
                      <p class="ma-0">{{ record.diagnose | empty }}</p>
                    </div>
                    <div class="d-flex flex-row">
                      <p class="ma-0 mr-3 font-weight-bold">
                        Chỉ định:
                      </p>
                      <p class="ma-0">{{ record.prescribe | empty }}</p>
                    </div>
                    <div class="d-flex flex-row  mb-8">
                      <p class="ma-0 mr-3 font-weight-bold">
                        Ghi chú:
                      </p>
                      <p class="ma-0">{{ record.note | empty }}</p>
                    </div>
                    <v-btn
                      outlined
                      class="btn-not-transform"
                      @click="medical_record_dialog = true"
                    >
                      Xem chi tiết
                    </v-btn>
                  </v-card-text>
                </v-card>
              </v-timeline-item>
            </v-timeline>
          </v-scroll-y-transition>
        </v-card>
      </div>
    </v-card>
  </div>
</template>
<script>
const url = process.env.VUE_APP_ROOT_API;
export default {
  data: () => ({
    profiles: [],
    selectedProfile: null,
    records: [],
    radioGroup: null,
    loading: false,
    profile_list_dialog: false,
    medical_record_dialog: false,
    list_record_show: false,
    years: [],
    prescriptions_header: [
      {
        text: "Tên",
        align: "start",
        sortable: false,
        value: "name",
        class: "text-body-2 font-weight-medium"
      },
      {
        text: "Số lượng",
        value: "mount",
        sortable: false,
        class: "text-body-2 font-weight-medium"
      },
      {
        text: "Liều",
        value: "use",
        sortable: false,
        class: "text-body-2 font-weight-medium"
      },
      {
        text: "Đơn vị uống",
        value: "unit",
        sortable: false,
        class: "text-body-2 font-weight-medium"
      }
    ],
    prescriptions: [
      {
        name: "abc",
        mount: 120,
        use: "Sáng: 1, Chiều: 1, Tối: 1",
        unit: "Viên"
      },
      {
        name: "abc",
        mount: 120,
        use: "Sáng: 1, Chiều: 1, Tối: 1",
        unit: "Viên"
      },
      {
        name: "abc",
        mount: 120,
        use: "Sáng: 1, Chiều: 1, Tối: 1",
        unit: "Viên"
      }
    ]
  }),
  async mounted() {
    await this.getProfiles();
    await this.getRecords();
  },
  methods: {
    getDate(date) {
      let date_array = date.split("-");
      return `${date_array[2]}/${date_array[1]}/${date_array[0]}`;
    },
    getServiceName(appointment) {
      if (appointment.category == "DOCTOR") {
        if (appointment.type == "ONLINE") {
          return `Tư vấn trực tuyến với ${appointment.doctor.level}. ${appointment.doctor.name}`;
        }
        return `Khám trực tiếp với ${appointment.doctor.level}. ${appointment.doctor.name}`;
      } else {
        if (appointment.type == "ONLINE") {
          return `Dịch vụ khám tại nhà ${appointment.service.name}`;
        }
        return `Dịch vụ khám tại viện ${appointment.service.name}`;
      }
    },
    async getProfiles() {
      let token = this.$store.getters["auth/access_token"];

      this.axios.defaults.headers.common = {
        Authorization: `Bearer ${token}`
      };
      await this.axios.get(`${url}/api/profiles`).then(res => {
        this.profiles = res.data.results;
        this.selectedProfile = (this.profiles || []).find(
          p => p.relationship === "Chủ tài khoản"
        );
      });
    },
    async getRecords() {
      let token = this.$store.getters["auth/access_token"];

      this.axios.defaults.headers.common = {
        Authorization: `Bearer ${token}`
      };
      await this.axios
        .get(`${url}/api/user/records`, {
          params: {
            profileId: this.selectedProfile.id
          }
        })
        .then(res => {
          this.records = res.data.results;
        });
    },
    getImgOfProfile(profile) {
      if (profile.imageUrl != null) {
        return profile.imageUrl;
      } else {
        return require("@/assets/img/user/profile/avatar1.svg");
      }
    },
    view_health_record() {
      this.selectedProfile = this.radioGroup;
      this.years = [];
      this.profile_list_dialog = false;
    }
  }
};
</script>
<style scoped>
.btn-not-transform {
  text-transform: none;
}
</style>
