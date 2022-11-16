<template>
  <div>
    <v-card
      width="100%"
      class="d-flex flex-column justify-space-around align-center"
      min-height="316px"
      color="#EEF2F6"
    >
      <p class="font-weight-bold mt-12" style="color: #537da5; font-size: 40px">
        Đặt khám theo dịch vụ
      </p>
      <p>
        Lựa chọn dịch vụ theo nhu cầu của bạn
      </p>
      <v-card
        elevation="0"
        class="d-flex align-center mt-8 mb-12"
        width="720px"
        height="76px"
        outlined
      >
        <v-text-field
          v-model="search"
          full-width
          solo
          flat
          placeholder="Tìm kiếm tên dịch vụ, mô tả dịch vụ"
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
          <v-autocomplete
            v-model="hospitalSelect"
            :items="hospitals"
            item-text="text"
            item-value="value"
            outlined
            dense
            prepend-inner-icon="mdi-domain"
            label="Bệnh viện"
            placeholder="Tìm bệnh viện"
            clearable
          ></v-autocomplete>
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
        class="mx-4 pa-8 d-flex flex-column"
        width="1000"
        elevation="0"
        color="#FCFCFD"
      >
        <div class="d-flex flex-column" v-if="!listService.length">
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
        <!-- list service -->
        <v-row :justify="listService.length == 1 ? 'center' : ''">
          <v-col :md="6" v-for="(service, i) in listService" :key="i">
            <v-card class="d-flex flex-column pa-6" @click="moveToInfo">
              <v-card elevation="0" class="d-flex flex-row">
                <div class="d-flex flex-row">
                  <v-card
                    width="126"
                    min-height="84"
                    class="align-self-start"
                    elevation="0"
                  >
                    <v-img :src="getImgOfService(service)"></v-img>
                  </v-card>
                  <v-card class="d-flex flex-column ml-3" elevation="0">
                    <!-- service name -->
                    <p class="text-body-2 mb-2 font-weight-bold">
                      {{ service.name }}
                    </p>

                    <p class="text-body-2 mb-3">{{ service.hospital.name }}</p>

                    <div
                      class="d-flex flex-row align-center justify-space-between"
                    >
                      <p
                        class="font-weight-bold text-body-2"
                        style="color: #537da5"
                      >
                        {{ service.price }} đ
                      </p>

                      <v-icon small color="#537da5">mdi-arrow-right</v-icon>
                    </div>
                  </v-card>
                </div>
              </v-card>
            </v-card>
          </v-col>
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
      listService: [],
      totalPages: 0,
      params: {
        filters: [],
        sorts: [],
        page: 0,
        size: 8
      },
      loading: false,
      hospitals: [],
      specialties: [],
      hospitalSelect: null,
      specialtySelect: null,
      search: ""
    };
  },
  async created() {
    await this.getListService();
    this.getHospitals();
    this.getSpecialties();
    this.loading = false;
  },
  watch: {
    page: {
      handler() {
        this.getListService();
      }
    },
    hospitalSelect: {
      handler: _.debounce(function() {
        this.getListService();
      }, 400)
    },
    specialtySelect: {
      handler: _.debounce(function() {
        this.getListService();
      }, 400)
    },
    search: {
      handler: _.debounce(function() {
        this.getListService();
      }, 400)
    }
  },
  methods: {
    async getListService() {
      this.loading = true;
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

      if (this.hospitalSelect) {
        params.filters.push({
          key: "hospital.id",
          operator: "EQUAL_NESTED",
          field_type: "STRING",
          value: this.hospitalSelect
        });
      }
      params.page = this.page - 1;
      const res = await this.axios.post(`${url}/api/services/search`, params);
      this.loading = false;
      this.listService = res.data.results;
      this.totalPages = res.data?.meta?.totalPages;
    },
    async getHospitals() {
      this.axios.get(`${url}/api/hospitals/list`).then(res => {
        this.hospitals = res.data.results;
      });
    },
    async getSpecialties() {
      this.axios.get(`${url}/api/specialties`).then(res => {
        this.specialties = res.data.results;
      });
    },
    moveToInfo() {
      this.$router.push({ name: "Thông tin đặt lịch dịch vụ" }).catch(error => {
        if (error == null) {
          return;
        }
        if (error.name != "NavigationDuplicated") {
          throw error;
        }
      });
    },
    getImgOfService(service) {
      if (service.imageUrl != null) {
        return service.imageUrl;
      } else {
        return require("@/assets/img/home/service_avt.png");
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
