<template>
  <div>
    <v-card
      width="100%"
      class="d-flex flex-column justify-space-around align-center"
      min-height="316px"
      color="#EEF2F6"
    >
      <p class="font-weight-bold mt-12" style="color: #537da5; font-size: 40px">
        Đặt khám theo bác sĩ
      </p>
      <p>
        Để được tiếp đón ưu tiên viện hoặc được tư vấn với bác sĩ giỏi ngay tại
        nhà
      </p>
      <v-card
        elevation="0"
        class="d-flex align-center mt-8 mb-6"
        width="720px"
        height="76px"
        outlined
      >
        <v-text-field
          v-model="search"
          full-width
          solo
          flat
          placeholder="Tìm kiếm tên bác sĩ"
          hide-details=""
        ></v-text-field>
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
      <v-row style="width: 720px;">
        <v-col cols="4">
          <v-select
            v-model="levelSelect"
            :items="levels"
            prepend-inner-icon="mdi-school"
            label="Học Hàm/Học Vị"
            clearable
            dense
            outlined
          ></v-select>
        </v-col>
        <v-col cols="4">
          <v-select
            v-model="specialtySelect"
            :items="specialties"
            label="Chuyên Khoa"
            prepend-inner-icon="mdi-stethoscope"
            item-text="text"
            item-value="value"
            clearable
            dense
            outlined
          ></v-select>
        </v-col>

        <v-col cols="4">
          <v-select
            v-model="genderSelect"
            :items="genders"
            label="Giới Tính"
            prepend-inner-icon="mdi-gender-male"
            item-text="text"
            item-value="value"
            clearable
            dense
            outlined
          ></v-select>
        </v-col>
      </v-row>
    </v-card>
    <v-progress-linear
      :indeterminate="loading"
      :active="loading"
    ></v-progress-linear>

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
        <div class="d-flex flex-column" v-if="!listDoctor.length">
          <v-card
            width="100%"
            class="d-flex justify-center"
            elevation="0"
            style="background: none"
          >
            <v-img
              class="d-flex"
              src="@/assets/img/user/profile/lookingNotFound.png"
              width="183px"
              height="186px"
              contain
            ></v-img>
          </v-card>
          <p
            class="font-weight-bold ml-8 d-flex justify-center"
            style="font-size: 20px"
          >
            Không có kết quả tìm kiếm
          </p>
          <p class="ml-8 d-flex justify-center" style="color: #667085">
            Xin lỗi chúng tôi không tìm thấy kết quả phù hợp với bạn.
          </p>
        </div>
        <!-- list doctor -->
        <v-row justify="center">
          <v-col v-for="(doctor, i) in listDoctor" :key="i" md="6">
            <!-- in -->
            <v-card
              class="d-flex flex-column justify-center align-center pa-3"
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
                        {{ doctor.level }}. {{ doctor.name }}
                      </p>
                      <p class="text-body-2 mb-3">
                        {{ doctor.hospital.name }}
                      </p>
                      <v-card
                        class="py-1 px-2 mr-2 font-weight-medium text-body-2 d-flex justify-center"
                        elevation="0"
                        color="#F9FAFB"
                        dark
                        style="border: 1px solid #d0d5dd; color: #667085; width: fit-content"
                      >
                        {{ doctor.specialty }}</v-card
                      >
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
                      <v-icon color="#537DA5" class="align-self-start mr-2"
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
                      <v-icon color="#FFC107" class="align-self-start mr-2"
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
                    <p style="font-size: 90%">Giá khám:</p>
                    <p
                      class="font-weight-bold text-body-2 ml-3"
                      style="color: #537da5"
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
                    @click="moveToInfo()"
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
          style="background: none"
          width="100%"
          elevation="0"
          class="d-flex justify-center mt-10"
        >
          <div class="text-center">
            <v-pagination
              v-if="listDoctor.length"
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
const url = process.env.VUE_APP_ROOT_API;
export default {
  data() {
    return {
      page: 1,
      listDoctor: [],
      totalPages: 0,
      params: {
        filters: [],
        sorts: [],
        page: 0,
        size: 8
      },
      loading: false,
      search: "",
      specialties: [],
      specialtySelect: null,
      levelSelect: null,
      genderSelect: null,
      genders: [
        {
          value: "MALE",
          text: "Nam"
        },
        {
          value: "FEMALE",
          text: "Nữ"
        }
      ],
      levels: [
        "BSCKI",
        "TS.BS",
        "PGS.TS.BS",
        "BSCKII",
        "CN",
        "GS.TS.BS",
        "TS",
        "ThS",
        "ThS.BS",
        "BS",
        "CKI"
      ]
    };
  },
  async created() {
    await this.getListDoctor();
    await this.getSpecialties();
    this.loading = false;
  },
  watch: {
    page: {
      handler() {
        this.getListDoctor();
      }
    },
    search: {
      handler: _.debounce(function() {
        this.getListDoctor();
      }, 400)
    },
    specialtySelect: {
      handler: _.debounce(function() {
        this.getListDoctor();
      }, 400)
    },
    genderSelect: {
      handler: _.debounce(function() {
        this.getListDoctor();
      }, 400)
    },
    levelSelect: {
      handler: _.debounce(function() {
        this.getListDoctor();
      }, 400)
    }
  },
  methods: {
    moveToInfo() {
      this.$router.push({ name: "Thông tin đặt lịch bác sĩ" }).catch(error => {
        if (error == null) {
          return;
        }
        if (error.name != "NavigationDuplicated") {
          throw error;
        }
      });
    },
    async getListDoctor() {
      this.loading = true;
      const cancelToken = this.axios.CancelToken.source().token;
      let params = this._.cloneDeep(this.params);
      if (this.search) {
        params.filters.push({
          key: "name",
          operator: "LIKE",
          field_type: "STRING",
          value: this.search
        });
      }
      if (this.specialtySelect) {
        params.filters.push({
          key: "specialty",
          operator: "EQUAL",
          field_type: "SPECIALTY",
          value: this.specialtySelect
        });
      }

      if (this.genderSelect) {
        params.filters.push({
          key: "gender",
          operator: "EQUAL",
          field_type: "GENDER",
          value: this.genderSelect
        });
      }
      if (this.levelSelect) {
        params.filters.push({
          key: "level",
          operator: "EQUAL",
          field_type: "STRING",
          value: this.levelSelect
        });
      }
      params.page = this.page - 1;
      const res = await this.axios.post(`${url}/api/doctors/search`, params, {
        cancelToken
      });
      this.loading = false;
      this.listDoctor = res.data.results;
      this.totalPages = res.data?.meta?.totalPages;
    },
    async getSpecialties() {
      this.axios.get(`${url}/api/specialties`).then(res => {
        this.specialties = res.data.results;
      });
    }
  }
};
</script>
<style scoped>
.btn {
  text-transform: none;
}
</style>
