<template>
  <div>
    <!-- avarta -->
    <v-card width="100%" class="d-flex flex-row" height="236px" flat>
      <v-card width="50%" flat>
        <p class="font-weight-medium">Hình đại diện</p>
        <v-card width="278px" flat>
          <p class="text-body-2 font-weight-medium" style="color: #667085">
            Chọn hình đại diện mới và thả vào khung đăng ảnh để thay đổi ảnh đại
            diện
          </p>
        </v-card>
      </v-card>
      <v-card
        width="50%"
        flat
        class="d-flex flex-row align-center"
        max-width="400px"
        max-height="172px"
      >
        <v-avatar size="64">
          <v-img contain :src="avt_img" ref="avt" />
        </v-avatar>
        <v-card
          class="d-flex align-center justify-center ml-8"
          width="100%"
          color="#FCFCFD"
          height="100%"
        >
          <div class="d-flex flex-column">
            <div class="d-flex justify-center">
              <v-card
                class="d-flex align-center justify-center pa-2"
                rounded="circle"
                color="#EEF2F6"
              >
                <v-icon color="#537DA5" large @click="$refs.file.click()"
                  >mdi-cloud-upload-outline
                </v-icon>
                <input
                  type="file"
                  ref="file"
                  accept="image/*"
                  style="display: none"
                  @change="previewFiles"
                />
              </v-card>
            </div>
            <p class="d-flex font-weight-medium">
              Click to upload or drag and drop
            </p>
            <p class="d-flex text-body-2" style="color: #667085">
              SVG, PNG, JPG or GIF (max 800x400px)
            </p>
          </div>
        </v-card>
      </v-card>
    </v-card>
    <!-- form -->
    <div class="d-flex flex-column">
      <v-divider
        style="border-color: rgba(16, 24, 40, 0.03) !important"
      ></v-divider>
      <!-- header -->
      <v-card height="112px" elevation="0">
        <div class="mt-6">
          <p class="font-weight-bold" style="font-size: 20px">
            Thông tin bệnh nhân
          </p>
          <p style="color: #667085">
            Vui lòng cập nhật thông tin của bệnh nhân.
          </p>
        </div>
      </v-card>
      <!-- name -->
      <v-card height="140px" elevation="0">
        <div class="mt-6 d-flex flex-row justify-space-between">
          <div class="d-flex flex-column">
            <p class="mb-2 font-weight-medium text-body-2">
              Họ và tên lót (có dấu):
            </p>
            <v-card
              width="417px"
              height="48px"
              class="d-flex mb-5"
              elevation="0"
              style="padding-top: 1px"
              outlined
            >
              <v-text-field
                v-model="profile.last_name"
                placeholder="VD: Nguyễn Duy"
                solo
                flat
                dense
                class="text-body-1"
              ></v-text-field>
            </v-card>
          </div>
          <div class="d-flex flex-column">
            <p class="mb-2 font-weight-medium text-body-2">Tên (có dấu):</p>
            <v-card
              width="417px"
              height="48px"
              class="d-flex mb-5"
              elevation="0"
              style="padding-top: 1px"
              outlined
            >
              <v-text-field
                v-model="profile.first_name"
                placeholder="VD: Thanh"
                solo
                flat
                dense
                class="text-body-1"
              ></v-text-field>
            </v-card>
          </div>
        </div>
      </v-card>

      <!-- phone, email -->
      <v-divider
        style="border-color: rgba(16, 24, 40, 0.03) !important"
      ></v-divider>
      <v-card height="140px" elevation="0">
        <div class="mt-6 d-flex flex-row justify-space-between">
          <div class="d-flex flex-column">
            <p class="mb-2 font-weight-medium text-body-2">Số điện thoại:</p>
            <v-card
              width="417px"
              height="48px"
              class="d-flex mb-5"
              elevation="0"
              style="padding-top: 1px"
              outlined
            >
              <v-text-field
                v-model="profile.phone"
                placeholder="Số điện thoại"
                solo
                flat
                dense
                class="text-body-1"
              ></v-text-field>
            </v-card>
          </div>
          <div class="d-flex flex-column">
            <p class="mb-2 font-weight-medium text-body-2">Email:</p>
            <v-card
              v-model="profile.email"
              width="417px"
              height="48px"
              class="d-flex mb-5"
              elevation="0"
              style="padding-top: 1px"
              outlined
            >
              <v-text-field
                placeholder="VD thanhbkcse@gmail.com"
                solo
                flat
                dense
                class="text-body-1"
              ></v-text-field>
            </v-card>
          </div>
        </div>
      </v-card>

      <!-- birthday -->
      <v-divider
        style="border-color: rgba(16, 24, 40, 0.03) !important"
      ></v-divider>
      <v-card height="140px" elevation="0">
        <div class="mt-6 d-flex flex-column">
          <p class="mb-2 font-weight-medium text-body-2">Ngày sinh:</p>
          <div class="d-flex flex-row justify-space-between">
            <v-card
              width="270px"
              height="48px"
              class="d-flex mb-5"
              elevation="0"
              style="padding-top: 1px"
              outlined
            >
              <v-select
                v-model="profile.dob.date"
                :items="days.date"
                :label="profile.dob.date"
                solo
                flat
                dense
                append-icon="mdi-chevron-down"
                menu-props="auto"
              ></v-select>
            </v-card>
            <v-card
              width="270px"
              height="48px"
              class="d-flex mb-5"
              elevation="0"
              style="padding-top: 1px"
              outlined
            >
              <v-select
                v-model="profile.dob.month"
                :items="days.month"
                :label="profile.dob.month"
                solo
                flat
                dense
                append-icon="mdi-chevron-down"
                menu-props="auto"
              ></v-select>
            </v-card>
            <v-card
              width="270px"
              height="48px"
              class="d-flex mb-5"
              elevation="0"
              style="padding-top: 1px"
              outlined
            >
              <v-select
                v-model="profile.dob.year"
                :items="days.year"
                :label="profile.dob.year"
                solo
                flat
                dense
                menu-props="auto"
                append-icon="mdi-chevron-down"
              ></v-select>
            </v-card>
          </div>
        </div>
      </v-card>

      <!-- gender -->
      <v-divider
        style="border-color: rgba(16, 24, 40, 0.03) !important"
      ></v-divider>
      <v-card height="140px" elevation="0">
        <div class="mt-6 d-flex flex-row justify-space-between">
          <div class="d-flex flex-column">
            <p class="mb-2 font-weight-medium text-body-2">Giới tính</p>
            <v-radio-group row v-model="profile.gender">
              <v-radio
                label="Nam"
                value="Nam"
                color="#537DA5"
                class="mr-6"
              ></v-radio>
              <v-radio label="Nữ" value="Nữ" color="#537DA5"></v-radio>
            </v-radio-group>
          </div>
        </div>
      </v-card>

      <!-- national, identify -->
      <v-divider
        style="border-color: rgba(16, 24, 40, 0.03) !important"
      ></v-divider>
      <v-card height="140px" elevation="0">
        <div class="mt-6 d-flex flex-row justify-space-between">
          <div class="d-flex flex-column">
            <p class="mb-2 font-weight-medium text-body-2">Quốc gia:</p>
            <v-card
              width="417px"
              height="48px"
              class="d-flex mb-5"
              elevation="0"
              style="padding-top: 1px"
              outlined
            >
              <v-text-field
                placeholder="Việt Nam"
                solo
                flat
                dense
                class="text-body-1"
              ></v-text-field>
            </v-card>
          </div>
          <div class="d-flex flex-column">
            <p class="mb-2 font-weight-medium text-body-2">Số CMND/Passport:</p>
            <v-card
              width="417px"
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
              ></v-text-field>
            </v-card>
          </div>
        </div>
      </v-card>

      <!-- ethnic, job -->
      <v-divider
        style="border-color: rgba(16, 24, 40, 0.03) !important"
      ></v-divider>
      <v-card height="140px" elevation="0">
        <div class="mt-6 d-flex flex-row justify-space-between">
          <div class="d-flex flex-column">
            <p class="mb-2 font-weight-medium text-body-2">Dân tộc:</p>
            <v-card
              width="417px"
              height="48px"
              class="d-flex mb-5"
              elevation="0"
              style="padding-top: 1px"
              outlined
            >
              <v-text-field
                placeholder="VD: Kinh"
                solo
                flat
                dense
                class="text-body-1"
              ></v-text-field>
            </v-card>
          </div>
          <div class="d-flex flex-column">
            <p class="mb-2 font-weight-medium text-body-2">Nghề nghiệp:</p>
            <v-card
              width="417px"
              height="48px"
              class="d-flex mb-5"
              elevation="0"
              style="padding-top: 1px"
              outlined
            >
              <v-text-field
                placeholder="VD: Sinh viên"
                solo
                flat
                dense
                class="text-body-1"
              ></v-text-field>
            </v-card>
          </div>
        </div>
      </v-card>

      <!-- city, district -->
      <v-divider
        style="border-color: rgba(16, 24, 40, 0.03) !important"
      ></v-divider>
      <v-card height="140px" elevation="0">
        <div class="mt-6 d-flex flex-row justify-space-between">
          <div class="d-flex flex-column">
            <p class="mb-2 font-weight-medium text-body-2">Tỉnh/Thành phố:</p>
            <v-card
              width="417px"
              height="48px"
              class="d-flex mb-5"
              elevation="0"
              style="padding-top: 1px"
              outlined
            >
              <v-select
                :items="days.date"
                label="24"
                solo
                flat
                dense
                append-icon="mdi-chevron-down"
                menu-props="auto"
              ></v-select>
            </v-card>
          </div>
          <div class="d-flex flex-column">
            <p class="mb-2 font-weight-medium text-body-2">Quận/Huyện:</p>
            <v-card
              width="417px"
              height="48px"
              class="d-flex mb-5"
              elevation="0"
              style="padding-top: 1px"
              outlined
            >
              <v-select
                :items="days.date"
                label="24"
                solo
                flat
                dense
                append-icon="mdi-chevron-down"
                menu-props="auto"
              ></v-select>
            </v-card>
          </div>
        </div>
      </v-card>

      <!-- sub-district, street, number -->
      <v-divider
        style="border-color: rgba(16, 24, 40, 0.03) !important"
      ></v-divider>
      <v-card height="140px" elevation="0">
        <div class="mt-6 d-flex flex-row justify-space-between">
          <div class="d-flex flex-column">
            <p class="mb-2 font-weight-medium text-body-2">Xã/Phường:</p>
            <v-card
              width="417px"
              height="48px"
              class="d-flex mb-5"
              elevation="0"
              style="padding-top: 1px"
              outlined
            >
              <v-select
                :items="days.date"
                label="24"
                solo
                flat
                dense
                append-icon="mdi-chevron-down"
                menu-props="auto"
              ></v-select>
            </v-card>
          </div>
          <div class="d-flex flex-column">
            <p class="mb-2 font-weight-medium text-body-2">
              Số nhà và tên đường
            </p>
            <v-card
              width="417px"
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
              ></v-text-field>
            </v-card>
          </div>
        </div>
      </v-card>

      <!-- a relative -->
      <v-card height="112px" elevation="0">
        <div class="mt-6">
          <p class="font-weight-bold" style="font-size: 20px">
            Thông tin nhân thân
          </p>
          <p style="color: #667085">
            Vui lòng cập nhật thông tin của thân nhân.
          </p>
        </div>
      </v-card>
      <!-- name, relationship -->
      <v-card height="140px" elevation="0">
        <div class="mt-6 d-flex flex-row justify-space-between">
          <div class="d-flex flex-column">
            <p class="mb-2 font-weight-medium text-body-2">
              Họ và tên người thân (có dấu):
            </p>
            <v-card
              width="417px"
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
              ></v-text-field>
            </v-card>
          </div>
          <div class="d-flex flex-column">
            <p class="mb-2 font-weight-medium text-body-2">
              Quan hệ với bệnh nhân:
            </p>
            <v-card
              width="417px"
              height="48px"
              class="d-flex mb-5"
              elevation="0"
              style="padding-top: 1px"
              outlined
            >
              <v-select
                :items="days.date"
                label="24"
                solo
                flat
                dense
                append-icon="mdi-chevron-down"
                menu-props="auto"
              ></v-select>
            </v-card>
          </div>
        </div>
      </v-card>

      <!-- phone, email -->
      <v-divider
        style="border-color: rgba(16, 24, 40, 0.03) !important"
      ></v-divider>
      <v-card height="140px" elevation="0">
        <div class="mt-6 d-flex flex-row justify-space-between">
          <div class="d-flex flex-column">
            <p class="mb-2 font-weight-medium text-body-2">Số điện thoại:</p>
            <v-card
              width="417px"
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
              ></v-text-field>
            </v-card>
          </div>
          <div class="d-flex flex-column">
            <p class="mb-2 font-weight-medium text-body-2">Email:</p>
            <v-card
              width="417px"
              height="48px"
              class="d-flex mb-5"
              elevation="0"
              style="padding-top: 1px"
              outlined
            >
              <v-text-field
                placeholder="VD: duythanh0303@gmail.com"
                solo
                flat
                dense
                class="text-body-1"
              ></v-text-field>
            </v-card>
          </div>
        </div>
      </v-card>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  props: {
    last_name: {
      type: String,
      default: "",
    },

    first_name: {
      type: String,
      default: "",
    },
    phone: {
      type: String,
      default: "",
    },
    email: {
      type: String,
      default: "",
    },

    date: {
      type: Number,
      default: 1,
    },

    month: {
      type: Number,
      default: 1,
    },

    year: {
      type: Number,
      default: 1970,
    },

    gender: {
      type: String,
      default: "Nam",
    },

    identify: {
      type: String,
      default: "",
    },
    country: {
      type: String,
      default: "Việt Nam",
    },
  },

  data() {
    return {
      avt_img: require("@/assets/img/user/profile/avatar1.svg"),
      days: {
        date: [],
        month: [],
        year: [],
      },

      profile: {
        last_name: "",
        first_name: "",
        phone: "",
        email: "",
        dob: {
          date: 0,
          month: 0,
          year: 0,
        },
        gender: "",
        identify: "",
        folk: "",
        job: "",
        address: {
          country: "",
          city: "",
          district: "",
          sub_district: "",
          street: "",
          number: "",
        },
      },
    };
  },

  created() {
    //create day
    this.days.month = Array.from({ length: 12 }, (_, i) => i + 1);
    this.days.date = Array.from({ length: 31 }, (_, i) => i + 1);
    let year = Array.from({ length: 100 }, (_, i) => i + 1923);
    this.days.year = year.reverse();
    this.this.setDataForm();
  },

  methods: {
    previewFiles(event) {
      let file_1 = event.target.files[0];
      let reader = new FileReader();
      reader.addEventListener(
        "load",
        () => {
          // convert image file to base64 string
          this.avt_img = reader.result;
        },
        false
      );
      if (file_1) {
        reader.readAsDataURL(file_1);
      } else {
        this.avt_img = require("@/assets/img/user/profile/avatar1.svg");
      }
    },

    setDataForm() {
      this.profile.last_name = this.last_name;
      this.profile.first_name = this.first_name;
      this.profile.phone = this.phone;
      this.profile.email = this.email;
      this.profile.dob.date = this.date;
      this.profile.dob.month = this.month;
      this.profile.dob.year = this.year;
      this.profile.gender = this.gender;
    },
  },
};
</script>
