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
      style=" border-color: rgba(16, 24, 40, 0.03) !important;"
    ></v-divider>
    <!-- tab -->
    <v-tabs color="#537DA5" fixed-tabs slider-size="3" v-model="tab">
      <v-tab
        style="text-transform: none;"
        class="font-weight-medium text-body-1"
        href="#tab-1"
        >Tạo hồ sơ mới</v-tab
      >
      <v-tab
        style="text-transform: none;"
        class="font-weight-medium text-body-1"
        href="#tab-2"
        >Tìm kiếm hồ sơ</v-tab
      >
    </v-tabs>
    <v-tabs-items v-model="tab">
      <v-tab-item :value="'tab-1'">
        <v-card flat> </v-card>
      </v-tab-item>
      <v-tab-item :value="'tab-2'">
        <v-card flat class="d-flex flex-column">
          <div class="d-flex flex-column ml-8 mt-6">
            <p class="font-weight-bold" style="font-size:20px">
              Nhập số điện thoại
            </p>
            <p>Vui lòng nhập số điện thoại của thân nhân</p>
            <v-card
              width="441px"
              height="48px"
              class="d-flex mb-5"
              elevation="0"
              outlined
            >
              <v-text-field
                placeholder="Số điện thoại"
                solo
                flat
                dense
                class="text-body-2"
              ></v-text-field
            ></v-card>
            <v-btn
              color="#537DA5"
              width="102px"
              height="44px"
              class="white--text btn-not-transform  text-body-1"
              >Tìm kiếm</v-btn
            >
          </div>
          <v-divider
            class="mt-6"
            style=" border-color: rgba(16, 24, 40, 0.03) !important;"
          ></v-divider>
          <div v-if="true" class="d-flex flex-column mt-6">
            <p class="font-weight-bold ml-8" style="font-size:20px">
              Kết quả tìm kiếm
            </p>
            <v-card width="100%" class="d-flex flex-column" elevation="0">
              <v-img
                class="d-flex justify-center"
                src="@/assets/img/user/profile/lookingNotFound.svg"
                width="183px"
                height="186px"
                contain
              ></v-img>
              <div>
                <p>Không có kết quả tìm kiếm</p>
                <p>
                  Vui lòng nhập số điện thoại thân nhân để hiển thị kết quả.
                </p>
              </div>
            </v-card>
          </div>
        </v-card>
      </v-tab-item>
    </v-tabs-items>
  </div>
</template>

<script>
export default {
  data() {
    return {
      items: ["web", "shopping", "videos", "images", "news"],
      tab: null
    };
  }
};
</script>

<style scoped>
.btn-not-transform {
  text-transform: none;
}
</style>
