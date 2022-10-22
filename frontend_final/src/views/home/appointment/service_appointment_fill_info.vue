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
            <p class="ma-0 font-weight-bold">
              Gói xét nghiệm tổng quát cho Nam
            </p>
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
            <v-avatar class="mr-5">
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
            </v-avatar>
            <div class="d-flex flex-column">
              <p class="mb-3 font-weight-bold">09:00 - 09:30</p>
              <p class="ma-0 text-body-2" style="color: #667085">24/8/2022</p>
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
        <!-- profile after select -->
        <v-card width="700" class="mt-8" v-if="is_payment">
          <!-- header -->
          <v-card
            class="d-flex flex-column pa-6 font-weight-bold"
            elevation="0"
            tile
            style="font-size: 24px"
            >Hồ sơ khám bệnh</v-card
          >
          <v-divider style="border-color: #f2f4f7 !important"></v-divider>
          <v-expansion-panels accordion tile flat>
            <v-expansion-panel
              v-for="profile in profile_list"
              :key="profile.id"
              class="mb-4"
            >
              <v-expansion-panel-header>
                <v-card class="d-flex flex-row align-center" elevation="0">
                  <v-avatar size="40">
                    <img src="@/assets/img/home/appbar/avatar.png" />
                  </v-avatar>
                  <div class="d-flex flex-column ml-3">
                    <p class="ma-0 font-weight-bold text-body-1">
                      {{ profile.lastName }} {{ profile.firstName }}
                    </p>
                    <p
                      class="ma-0 font-weight-normal text-body-2"
                      style="color: #667085"
                    >
                      {{ profile.relationship }}
                    </p>
                  </div>
                </v-card>

                <template v-slot:actions>
                  <v-card
                    elevation="0"
                    rounded="circle"
                    color="#FCFCFD"
                    width="32px"
                    height="32px"
                    class="d-flex justify-center"
                  >
                    <v-icon small> mdi-chevron-down </v-icon>
                  </v-card>
                </template>
              </v-expansion-panel-header>

              <v-expansion-panel-content class="pa-0">
                <!-- user information -->
                <div class="d-flex flex-column">
                  <!-- email -->
                  <v-divider
                    style="border-color: #f2f4f7 !important"
                  ></v-divider>
                  <v-card
                    class="d-flex flec-row justify-space-between align-center"
                    elevation="0"
                    height="72px"
                  >
                    <p class="ma-0 font-weight-medium" style="color: #667085">
                      Email
                    </p>
                    <p class="ma-0 font-weight-medium">
                      {{ profile.email }}
                    </p>
                  </v-card>
                  <!-- phone -->
                  <v-divider
                    style="border-color: #f2f4f7 !important"
                  ></v-divider>
                  <v-card
                    class="d-flex flec-row justify-space-between align-center"
                    elevation="0"
                    height="72px"
                  >
                    <p class="ma-0 font-weight-medium" style="color: #667085">
                      Số điện thoại
                    </p>
                    <p class="ma-0 font-weight-medium">
                      {{ profile.phone }}
                    </p>
                  </v-card>
                  <!-- address -->
                  <v-divider
                    style="border-color: #f2f4f7 !important"
                  ></v-divider>
                  <v-card
                    class="d-flex flec-row justify-space-between align-center"
                    elevation="0"
                    height="72px"
                  >
                    <p class="ma-0 font-weight-medium" style="color: #667085">
                      Địa chỉ
                    </p>
                    <p class="ma-0 font-weight-medium">
                      {{ getAddress(profile) }}
                    </p>
                  </v-card>
                  <!-- gender -->
                  <v-divider
                    style="border-color: #f2f4f7 !important"
                  ></v-divider>
                  <v-card
                    class="d-flex flec-row justify-space-between align-center"
                    elevation="0"
                    height="72px"
                  >
                    <p class="ma-0 font-weight-medium" style="color: #667085">
                      Giới tính
                    </p>
                    <p class="ma-0 font-weight-medium">
                      {{ getGender(profile.gender) }}
                    </p>
                  </v-card>
                  <!-- birthday -->
                  <v-divider
                    style="border-color: #f2f4f7 !important"
                  ></v-divider>
                  <v-card
                    class="d-flex flec-row justify-space-between align-center"
                    elevation="0"
                    height="72px"
                  >
                    <p class="ma-0 font-weight-medium" style="color: #667085">
                      Ngày sinh
                    </p>
                    <p class="ma-0 font-weight-medium">
                      {{ getDate(profile.dob) }}
                    </p>
                  </v-card>
                  <!-- identify -->
                  <v-divider
                    style="border-color: #f2f4f7 !important"
                  ></v-divider>
                  <v-card
                    class="d-flex flec-row justify-space-between align-center"
                    elevation="0"
                    height="72px"
                  >
                    <p class="ma-0 font-weight-medium" style="color: #667085">
                      Số CMND/CCCD
                    </p>
                    <p class="ma-0 font-weight-medium">
                      {{ profile.identityCard }}
                    </p>
                  </v-card>
                  <!-- job -->
                  <v-divider
                    style="border-color: #f2f4f7 !important"
                  ></v-divider>
                  <v-card
                    class="d-flex flec-row justify-space-between align-center"
                    elevation="0"
                    height="72px"
                  >
                    <p class="ma-0 font-weight-medium" style="color: #667085">
                      Nghề nghiệp
                    </p>
                    <p class="ma-0 font-weight-medium">{{ profile.job }}</p>
                  </v-card>
                  <!-- country -->
                  <v-divider
                    style="border-color: #f2f4f7 !important"
                  ></v-divider>
                  <v-card
                    class="d-flex flec-row justify-space-between align-center"
                    elevation="0"
                    height="72px"
                  >
                    <p class="ma-0 font-weight-medium" style="color: #667085">
                      Quốc gia
                    </p>
                    <p class="ma-0 font-weight-medium">
                      {{ profile.address.country }}
                    </p>
                  </v-card>
                  <!-- ethnic -->
                  <v-divider
                    style="border-color: #f2f4f7 !important"
                  ></v-divider>
                  <v-card
                    class="d-flex flec-row justify-space-between align-center"
                    elevation="0"
                    height="72px"
                  >
                    <p class="ma-0 font-weight-medium" style="color: #667085">
                      Dân tộc
                    </p>
                    <p class="ma-0 font-weight-medium">{{ profile.folk }}</p>
                  </v-card>
                </div>
              </v-expansion-panel-content>
            </v-expansion-panel>
          </v-expansion-panels>
        </v-card>
        <!-- fill patient profile -->
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
                :readonly="!is_select_profile"
              >
              </v-textarea>
            </v-card>
            <!-- test results type -->
            <div class="d-flex flex-row justify-space-between align-center">
              <p class="font-weight-medium text-body-1 ma-0">
                Kết quả xét nghiệm
              </p>
              <v-card width="221" outlined v-if="is_select_profile">
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
            <v-card
              outlined
              class="my-5 pa-2 d-flex flex-row"
              v-if="is_select_profile"
            >
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
              <v-card width="221" outlined v-if="is_select_profile">
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
            <v-card
              outlined
              class="my-5 pa-2 d-flex flex-row"
              v-if="is_select_profile"
            >
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
              :key="index + 3"
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
      <!-- select profile and make payment -->
      <v-card
        class="ml-8 align-self-start d-flex flex-column"
        width="448"
        color="#FCFCFD"
        elevation="0"
      >
        <!-- select profile -->
        <div v-if="is_select_profile">
          <v-card class="d-flex flex-column">
            <v-card
              class="d-flex flex-column pa-6 font-weight-bold"
              elevation="0"
              tile
              style="font-size: 24px"
              >Hồ sơ khám bệnh</v-card
            >
            <v-divider style="border-color: #f2f4f7 !important"></v-divider>
            <!-- price -->
            <v-radio-group v-model="radioGroup" class="ma-0">
              <v-card
                v-for="n in 3"
                :key="n"
                class="pa-6 d-flex flex-row"
                tile
                elevation="0"
              >
                <v-avatar size="48" class="mr-5">
                  <v-img src="@/assets/img/user/profile/avatar1.svg"></v-img>
                </v-avatar>
                <div class="d-flex flex-column justify-center">
                  <p class="mb-2 font-weight-bold">Nguyễn Xuân Ngũ</p>
                  <p class="ma-0 text-body-2" style="color: #667085">
                    Chủ tài khoản
                  </p>
                </div>
                <v-spacer></v-spacer>
                <v-radio :value="n" color="#537DA5"></v-radio>
              </v-card>
            </v-radio-group>
          </v-card>
          <v-btn
            class="mt-8 btn white--text text-body-1"
            color="#537DA5"
            elevation="0"
            width="100%"
            @click="select_profile_success"
            >Tiến hành thanh toán
          </v-btn>
        </div>
        <!-- make payment -->
        <div v-if="is_payment">
          <v-card class="d-flex flex-column">
            <v-card
              class="d-flex flex-column pa-6 font-weight-bold"
              elevation="0"
              tile
              style="font-size: 24px"
              >Thanh toán</v-card
            >
            <v-divider style="border-color: #f2f4f7 !important"></v-divider>
            <!-- select payment method -->
            <v-card elevation="0" class="pa-6 d-flex flex-column">
              <v-card color="#FCFCFD" class="d-flex flex-row align-center pa-4">
                <p class="ma-0 text-body-1">Mã ưu đãi</p>
                <v-spacer></v-spacer>
                <v-card class="text-body-2" color="#D0D5DD" outlined>
                  <v-card
                    elevation="0"
                    color="#F9FAFB"
                    class="d-flex v-row justify-center pa-1 align-center"
                  >
                    <v-img src="@/assets/img/payment/discount.svg"></v-img>
                    <p class="ma-0 text-body-1" style="color: #667085">
                      Chọn mã ưu đãi
                    </p></v-card
                  >
                </v-card>
                <v-icon class="ml-6">mdi-chevron-right</v-icon>
              </v-card>
              <!-- payment list -->
              <v-card class="mt-6 d-flex flex-column" elevation="0">
                <p class="ma-0 font-weight-bold" style="font-size: 20px">
                  Phương thức thanh toán
                </p>
                <v-radio-group v-model="payment_selection" class="ma-0">
                  <!-- Visa -->
                  <v-card
                    class="mt-6 d-flex flex-row pa-4"
                    elevation="0"
                    color="#FCFCFD"
                  >
                    <v-img
                      contain
                      height="32"
                      width="32"
                      class="mr-5"
                      src="@/assets/img/payment/visa.png"
                    ></v-img>
                    <v-card class="d-flex flex-column mr-3" elevation="0">
                      <p class="mb-2 font-weight-bold">
                        Thanh toán bằng thẻ Visa
                      </p>
                      <p class="ma-0">
                        Hỗ trợ thanh toán xuyên quốc gia qua thẻ
                        Visa/Mastercard.
                      </p>
                    </v-card>
                    <v-radio value="VISA" color="#537DA5"></v-radio>
                  </v-card>
                  <!-- ATM -->
                  <v-card
                    class="mt-6 d-flex flex-row pa-4"
                    elevation="0"
                    color="#FCFCFD"
                  >
                    <v-img
                      contain
                      height="32"
                      width="32"
                      class="mr-5"
                      src="@/assets/img/payment/atm_card.png"
                    ></v-img>
                    <v-card class="d-flex flex-column mr-3" elevation="0">
                      <p class="mb-2 font-weight-bold">
                        Thanh toán bằng thẻ ATM nội địa
                      </p>
                      <p class="ma-0">
                        Sử dụng tài khoản Internet Banking của ngân hàng bạn
                        đang sử dụng để thanh toán.
                      </p>
                    </v-card>
                    <v-radio value="ATM" color="#537DA5"></v-radio>
                  </v-card>
                  <!-- Momo -->
                  <v-card
                    class="mt-6 d-flex flex-row pa-4"
                    elevation="0"
                    color="#FCFCFD"
                  >
                    <v-img
                      contain
                      height="32"
                      width="32"
                      class="mr-5"
                      src="@/assets/img/payment/momo.png"
                    ></v-img>
                    <v-card class="d-flex flex-column mr-3" elevation="0">
                      <p class="mb-2 font-weight-bold">
                        Thanh toán bằng thẻ Visa
                      </p>
                      <p class="ma-0">
                        Quét mã QR trong ứng dụng ví điện tử Momo để thanh toán
                        dịch vụ.
                      </p>
                    </v-card>
                    <v-radio value="MOMO" color="#537DA5"></v-radio>
                  </v-card>
                </v-radio-group>
                <v-card></v-card>
              </v-card>
            </v-card>
          </v-card>
          <v-btn
            class="mt-8 btn white--text text-body-1"
            color="#537DA5"
            elevation="0"
            width="100%"
            @click="make_payment"
            >Xác nhận thanh toán
          </v-btn>
        </div>
      </v-card>
    </div>
  </v-card>
</template>

<script>
export default {
  setup() {},
  data() {
    return {
      is_select_profile: true,
      is_payment: false,
      test_type: ["Xét nghiệm máu", "Xét nghiệm nước tiểu", "Xét nghiệm khác"],
      test_select: "",
      image_select: "",
      radioGroup: 1,
      payment_selection: "",
      profile_list: [
        {
          id: "1",
          firstName: "Tho",
          lastName: "Ha Ngoc",
          address: {
            country: "Viet Nam",
            province: "Tinh",
            district: "Huyen",
            ward: "Xa",
            address: "364 Do Bi",
          },
          phone: "phoneNumber",
          email: "email@gmail.com",
          dob: "2012-04-23T18:25:43.511Z",
          job: "job",
          identityCard: "identityCard",
          healthInsurance: "healthInsurance",
          folk: "folk",
          gender: "MALE",
          guardian: "guardian",
          guardianPhone: "guardianPhone",
          guardianIdentityCard: "guardianIdentityCard",
          relationship: "relationship",
          relationshipWithPatient: "relationshipWithPatient",
        },
      ],
      test_add_list: [
        {
          type: "Xét nghiệm máu",
          file_name: "ketquaxetnghiem.jpg",
        },
        {
          type: "Xét nghiệm nước tiểu",
          file_name: "ketquaxetnghiem.jpg",
        },
        {
          type: "Xét nghiệm khác",
          file_name: "ketquaxetnghiem.jpg",
        },
      ],
      image_analyst_type: ["CT", "X-quang", "PET", "Siêu âm", "Hình ảnh khác"],
    };
  },
  methods: {
    removeTestFile(index) {
      this.test_add_list.splice(index, 1);
    },

    getAddress(profile) {
      return (
        profile.address.province +
        ", " +
        profile.address.district +
        ", " +
        profile.address.ward +
        ", " +
        profile.address.address
      );
    },

    getDate(date_string) {
      let date = new Date(date_string);
      return date.toLocaleDateString();
    },

    getGender(string_gender) {
      return string_gender === "MALE" ? "Nam" : "Nữ";
    },

    select_profile_success() {
      this.is_select_profile = false;
      this.is_payment = true;
    },

    make_payment() {
      this.$router
        .push({ name: "Đặt lịch bác sĩ thành công" })
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
