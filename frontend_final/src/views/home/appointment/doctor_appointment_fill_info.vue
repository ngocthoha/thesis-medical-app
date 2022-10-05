<template>
  <v-card color="#FCFCFD" width="100%" class="pa-12">
    <div
      class="d-flex flex-column flex-md-row mx-8 justify-center align-center"
    >
      <v-card
        width="704"
        min-height="500"
        elevation="0"
        color="#FCFCFD"
        class="align-self-start"
      >
        <v-card width="700" class="d-flex flex-column">
          <!-- header -->
          <v-card
            class="d-flex flex-column pa-6 font-weight-bold"
            elevation="0"
            tile
            style="font-size: 24px"
            >Thông tin khám bệnh</v-card
          >
          <v-divider style="border-color: #f2f4f7 !important"></v-divider>
          <!-- price -->
          <v-card class="pa-6 d-flex flex-row align-center" tile elevation="0">
            <v-avatar size="28" class="mr-5">
              <v-img src="@/assets/img/home/appbar/doctor.svg"></v-img>
            </v-avatar>
            <p class="ma-0 font-weight-bold">Khám theo yêu cầu</p>
            <v-spacer></v-spacer>
            <p class="ma-0 font-weight-bold" style="color: #537da5">
              100.000VND
            </p>
          </v-card>
          <v-divider style="border-color: #f2f4f7 !important"></v-divider>
          <!-- time -->
          <v-card
            class="pa-6 d-flex flex-row"
            tile
            elevation="0"
            color="#FCFCFD"
          >
            <v-avarta class="mr-5">
              <v-card
                rounded="circle"
                class="pa-3"
                color="#EEF2F6"
                elevation="0"
              >
                <v-icon color="#537DA5"
                  >mdi-clock-time-nine-outline</v-icon
                ></v-card
              >
            </v-avarta>
            <div class="d-flex flex-column">
              <p class="mb-3 font-weight-bold">09:00 - 09:30</p>
              <p class="ma-0 text-body-2" style="color: #667085">24/8/2022</p>
            </div>
          </v-card>
          <v-divider style="border-color: #f2f4f7 !important"></v-divider>
          <!-- doctor -->
          <v-card
            class="pa-6 d-flex flex-row"
            tile
            elevation="0"
            color="#FCFCFD"
          >
            <v-avatar size="48" class="mr-5">
              <v-img src="@/assets/img/user/profile/avatar1.svg"></v-img>
            </v-avatar>
            <div class="d-flex flex-column justify-center">
              <p class="mb-2 font-weight-bold">Nguyễn Xuân Ngũ</p>
              <p class="ma-0 text-body-2" style="color: #667085">
                Phòng khám Vinmec Central Park
              </p>
            </div>
          </v-card>
          <v-divider style="border-color: #f2f4f7 !important"></v-divider>
          <!-- place -->
          <v-card
            class="pa-6 d-flex flex-row"
            tile
            elevation="0"
            color="#FCFCFD"
          >
            <v-avatar size="48" class="mr-5">
              <v-img src="@/assets/img/user/profile/avatar1.svg"></v-img>
            </v-avatar>
            <div class="d-flex flex-column justify-center">
              <p class="mb-2 font-weight-bold">
                Phòng khám Vinmec Central Park
              </p>
              <p class="text-body-2" style="color: #667085">
                08 Đ. Nguyễn Hữu Cảnh, Phường 22, Bình Thạnh, Thành phố Hồ Chí
                Minh
              </p>
              <p class="text-body-2 font-weight-medium" style="color: #537da5">
                Hiện chỉ đường
              </p>
            </div>
          </v-card>
        </v-card>
        <v-card width="700" min-height="500" class="mt-8 d-flex flex-column">
          <!-- header -->
          <v-card
            class="d-flex flex-column pa-6 font-weight-bold"
            elevation="0"
            tile
            style="font-size: 24px"
            >Thông tin bệnh nhân</v-card
          >
          <v-divider style="border-color: #f2f4f7 !important"></v-divider>
          <!-- info -->
          <v-card class="pa-6 d-flex flex-column" elevation="0">
            <!-- diagnostic -->
            <p class="font-weight-medium text-body-1">Triệu chứng</p>
            <v-card outlined class="mb-6">
              <v-textarea
                spellcheck="false"
                solo
                height="148"
                flat
                hide-details
                placeholder="Vui lòng mô tả triệu chứng của bạn..."
              >
              </v-textarea>
            </v-card>
            <!-- test results type -->
            <div class="d-flex flex-row justify-space-between align-center">
              <p class="font-weight-medium text-body-1 ma-0">
                Kết quả xét nghiệm
              </p>
              <v-card width="221" outlined>
                <v-combobox
                  spellcheck="false"
                  v-model="test_select"
                  :items="test_type"
                  solo
                  dense
                  flat
                  append-icon="mdi-chevron-down"
                  item-color="light-blue darken-4"
                  placeholder="Chọn loại xét nghiệm"
                  hide-details=""
                ></v-combobox>
              </v-card>
            </div>
            <!-- add test result  -->
            <v-card outlined class="my-5 pa-2 d-flex flex-row">
              <v-file-input
                placeholder="Vui lòng chọn hình ảnh"
                solo
                dense
                prepend-inner-icon="mdi-link-variant"
                clear-icon=""
                prepend-icon=""
                hide-details=""
                flat
              ></v-file-input>
              <v-btn
                class="btn white--text font-weight-medium text-body-2"
                color="#537DA5"
                elevation="0"
                >Tải lên</v-btn
              >
            </v-card>

            <!-- list add test result -->
            <div
              class="d-flex flex-wrap justify-space-between align-center mb-5"
              v-for="(image, index) in test_add_list"
              :key="index"
            >
              <p
                class="font-weight-medium text-body-1 ma-0"
                style="color: #667085"
              >
                {{ image.type }}
              </p>
              <v-card min-width="50%" outlined class="pa-3 d-flex flex-row">
                <v-icon class="mr-2">mdi-image-outline</v-icon>
                <v-card min-width="50%" elevation="0">
                  <p class="ma-0" style="">
                    {{ image.file_name }}
                  </p>
                </v-card>

                <v-spacer></v-spacer>
                <v-icon @click="removeTestFile(index)">mdi-close</v-icon>
              </v-card>
            </div>
            <!-- Image analysation type -->
            <div
              class="d-flex flex-row justify-space-between align-center mt-5"
            >
              <p class="font-weight-medium text-body-1 ma-0">
                Chuẩn đoán hình ảnh
              </p>
              <v-card width="221" outlined>
                <v-combobox
                  spellcheck="false"
                  v-model="image_select"
                  :items="image_analyst_type"
                  solo
                  dense
                  flat
                  append-icon="mdi-chevron-down"
                  item-color="light-blue darken-4"
                  placeholder="Chọn loại hình ảnh"
                  hide-details=""
                ></v-combobox>
              </v-card>
            </div>
            <!-- add test result  -->
            <v-card outlined class="my-5 pa-2 d-flex flex-row">
              <v-file-input
                placeholder="Vui lòng chọn hình ảnh"
                solo
                dense
                prepend-inner-icon="mdi-link-variant"
                clear-icon=""
                prepend-icon=""
                hide-details=""
                flat
              ></v-file-input>
              <v-btn
                class="btn white--text font-weight-medium text-body-2"
                color="#537DA5"
                elevation="0"
                >Tải lên</v-btn
              >
            </v-card>

            <!-- list add test result -->
            <div
              class="d-flex flex-wrap justify-space-between align-center mb-5"
              v-for="(image, index) in test_add_list"
              :key="index"
            >
              <p
                class="font-weight-medium text-body-1 ma-0"
                style="color: #667085"
              >
                {{ image.type }}
              </p>
              <v-card min-width="50%" outlined class="pa-3 d-flex flex-row">
                <v-icon class="mr-2">mdi-image-outline</v-icon>
                <v-card min-width="50%" elevation="0">
                  <p class="ma-0" style="">
                    {{ image.file_name }}
                  </p>
                </v-card>

                <v-spacer></v-spacer>
                <v-icon @click="removeTestFile(index)">mdi-close</v-icon>
              </v-card>
            </div>
          </v-card>
        </v-card>
      </v-card>
      <v-card class="ml-8 align-self-start" width="448" min-height="500">
      </v-card>
    </div>
  </v-card>
</template>

<script>
export default {
  setup() {},
  data() {
    return {
      test_type: ["Xét nghiệm máu", "Xét nghiệm nước tiểu", "Xét nghiệm khác"],
      test_select: "",
      image_select: "",
      test_add_list: [
        {
          type: "Xét nghiệm máu",
          file_name: "ketquaxetnghiem.jpg"
        },
        {
          type: "Xét nghiệm nước tiểu",
          file_name: "ketquaxetnghiem.jpg"
        },
        {
          type: "Xét nghiệm khác",
          file_name: "ketquaxetnghiem.jpg"
        }
      ],
      image_analyst_type: ["CT", "X-quang", "PET", "Siêu âm", "Hình ảnh khác"]
    };
  },
  methods: {
    removeTestFile(index) {
      this.test_add_list.splice(index, 1);
    }
  }
};
</script>

<style scoped>
.btn {
  text-transform: none;
}
</style>
