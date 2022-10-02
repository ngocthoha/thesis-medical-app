<template>
  <div>
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
            Thêm mới hồ sơ
          </p>
          <p style="color: #667085" class="text-body-1">
            Vui lòng lựa chọn các mục dưới đây để thêm hồ sơ mới
          </p>
        </div>
      </div>
    </v-card>
    <v-divider
      style="border-color: rgba(16, 24, 40, 0.03) !important"
    ></v-divider>
    <!-- tab -->
    <v-tabs color="#537DA5" fixed-tabs slider-size="3" v-model="tab">
      <v-tab
        style="text-transform: none"
        class="font-weight-medium text-body-1"
        href="#tab-1"
        >Tạo hồ sơ mới</v-tab
      >
      <v-tab
        style="text-transform: none"
        class="font-weight-medium text-body-1"
        href="#tab-2"
        >Tìm kiếm hồ sơ</v-tab
      >
    </v-tabs>
    <!-- Add new profile tab -->
    <v-tabs-items v-model="tab">
      <v-tab-item :value="'tab-1'">
        <v-card flat class="ma-6">
          <profile-form v-on:interface="getChildInterface"> </profile-form>
          <v-divider
            style="border-color: rgba(16, 24, 40, 0.03) !important"
          ></v-divider>
          <div class="d-flex flex-row justify-end mt-8">
            <v-btn
              class="mr-6 btn-not-transform text-body-1 font-weight-medium"
              elevation="0"
              outlined
              color="#667085"
              text
              @click="cancleAddProfile"
              >Hủy bỏ</v-btn
            >
            <v-btn
              class="btn-not-transform white--text text-body-1 font-weight-medium"
              elevation="0"
              color="#537DA5"
              @click="AddProfile"
              >Thêm hồ sơ</v-btn
            >
          </div>
        </v-card>
      </v-tab-item>
      <!-- Search and add existing profiles -->
      <v-tab-item :value="'tab-2'">
        <v-card flat class="d-flex flex-column">
          <div class="d-flex flex-column ml-8 mt-6">
            <p class="font-weight-bold" style="font-size: 20px">
              Nhập số điện thoại
            </p>
            <p>Vui lòng nhập số điện thoại của thân nhân</p>
            <v-card
              width="441px"
              height="48px"
              class="d-flex mb-5"
              elevation="0"
              style="padding-top: 1px"
              outlined
            >
              <v-text-field
                placeholder="Số điện thoại"
                solo
                flat
                dense
                class="text-body-1"
                v-model="phoneInputData"
              ></v-text-field>
            </v-card>
            <div class="d-flex flex-row">
              <v-btn
                color="#537DA5"
                class="white--text btn-not-transform text-body-1"
                elevation="0"
                @click="lookingSubmit()"
                >Tìm kiếm</v-btn
              >
              <v-btn
                class="ml-6 btn-not-transform text-body-1 font-weight-medium"
                elevation="0"
                outlined
                color="#667085"
                text
                @click="cancleAddProfile"
                >Hủy bỏ</v-btn
              >
            </div>
          </div>
          <v-divider
            class="mt-6"
            style="border-color: rgba(16, 24, 40, 0.03) !important"
          ></v-divider>
          <div class="d-flex flex-column mt-6" v-if="hasLookingResult">
            <p class="font-weight-bold ml-8" style="font-size: 20px">
              Kết quả tìm kiếm
            </p>
            <div class="d-flex flex-column" v-if="notFound">
              <v-card width="100%" class="d-flex justify-center" elevation="0">
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
                Vui lòng nhập số điện thoại thân nhân để hiển thị kết quả.
              </p>
            </div>
            <div v-if="!notFound">
              <v-list>
                <v-list-item v-for="n in 1" :key="n">
                  <v-card
                    class="d-flex flex-row align-center justify-space-between mb-5 mx-4"
                    outlined
                    width="100%"
                    height="92px"
                  >
                    <div class="d-flex flex-row align-center ml-6">
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
                    </div>
                    <div class="d-flex">
                      <v-btn
                        width="106px"
                        height="36px"
                        color="#537DA5"
                        class="white--text mr-4 btn-not-transform text-body-2"
                        >Gửi yêu cầu</v-btn
                      >
                    </div>
                  </v-card>
                </v-list-item>
              </v-list>
            </div>
          </div>
        </v-card>
      </v-tab-item>
    </v-tabs-items>
  </div>
</template>

<script>
import ProfileForm from "./profileForm.vue";
export default {
  components: {
    ProfileForm,
  },
  data() {
    return {
      items: ["web", "shopping", "videos", "images", "news"],
      tab: null,
      hasLookingResult: false,
      notFound: true,
      phoneInputData: "",
    };
  },
  methods: {
    lookingSubmit() {
      if (this.phoneInputData != "") {
        this.notFound = false;
      } else {
        this.notFound = true;
      }
      this.hasLookingResult = true;
    },
    cancleAddProfile() {
      this.$emit("addProfileClose");
    },

    getChildInterface(childInterface) {
      this.$options.childInterface = childInterface;
    },

    async AddProfile() {
      await this.$options.childInterface.addNewProfile();
      this.$emit("addProfileClose");
    },
  },
};
</script>

<style scoped>
.btn-not-transform {
  text-transform: none;
}

/* .v-text-field--outlined >>> fieldset {
  border-color: #d0d5dd;
} */
</style>
