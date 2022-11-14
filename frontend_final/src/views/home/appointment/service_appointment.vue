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
        <v-card elevation="0"
          ><p
            class="ma-0 font-weight-medium text-body-1 ml-3"
            style="color: #667085"
          >
            Tìm tên dịch vụ, mô tả dịch vụ
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
        class="mx-4 pa-8 d-flex flex-column"
        width="1000"
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
                      <p style="color: #537da5" class="ma-0">
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
      }
    };
  },
  async created() {
    await this.getListService();
  },
  watch: {
    page: {
      handler() {
        this.getListService();
      }
    }
  },
  methods: {
    async getListService() {
      const url = process.env.VUE_APP_ROOT_API;
      let params = this._.cloneDeep(this.params);
      params.page = this.page - 1;
      const res = await this.axios.post(`${url}/api/services/search`, params);
      this.listService = res.data.results;
      this.totalPages = res.data?.meta?.totalPages;
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
