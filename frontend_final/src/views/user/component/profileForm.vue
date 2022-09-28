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
                value="MALE"
                color="#537DA5"
                class="mr-6"
              ></v-radio>
              <v-radio label="Nữ" value="FEMALE" color="#537DA5"></v-radio>
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
                :items="province_list"
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
                label=""
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
                placeholder=""
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
              <v-text-field solo flat dense class="text-body-1"></v-text-field>
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
                v-model="profile.relationship"
                :items="relationship"
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
            <p class="mb-2 font-weight-medium text-body-2">CMND:</p>
            <v-card
              width="417px"
              height="48px"
              class="d-flex mb-5"
              elevation="0"
              style="padding-top: 1px"
              outlined
            >
              <v-text-field
                placeholder="VD: 07720xxxxxx"
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
      default: "MALE",
    },

    identify: {
      type: String,
      default: "",
    },
    country: {
      type: String,
      default: "Việt Nam",
    },
    type: {
      type: Number,
      default: 0, // 0 is create, 1 is edit
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
      relationship: [
        "Ba",
        "Mẹ",
        "Ông",
        "Bà",
        "Anh",
        "Chị",
        "Em",
        "Con",
        "Vợ",
        "Chồng",
        "Khác",
      ],
      province_list: [
        "Thủ đô Hà Nội",
        "Thành phố Hồ Chí Minh",
        "Bà Rịa-Vũng Tàu",
      ],

      profile: {
        firstName: "",
        lastName: "",
        country: "",
        province: "",
        town: "",
        commune: "",
        detailedAddress: "",
        phone: "",
        dob: {
          year: "",
          month: "",
          date: "",
        },
        job: "",
        identityCard: "",
        healthInsurance: "e",
        folk: "",
        gender: "",
        guardian: "",
        guardianPhone: "",
        guardianIdentityCard: "",
        relationship: "",
        relationshipWithPatient: "",
      },
    };
  },

  created() {
    //create day
    this.days.month = Array.from({ length: 12 }, (_, i) => (i + 1).toString);
    this.days.date = Array.from({ length: 31 }, (_, i) => (i + 1).toString);
    let year = Array.from({ length: 100 }, (_, i) => (i + 1923).toString);
    this.days.year = year.reverse();
    //this.setDataForm();
  },
  mounted() {
    // Emits on mount
    this.emitInterface();
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

    async addNewProfile() {
      let data = {
        name: "last_name",
        address: "address",
        phone: "phoneNumber",
        dob: "2012-04-23T18:25:43.511Z",
        job: "job",
        identityCard: "09102312",
        healthInsurance: "healthInsurance",
        folk: "folk",
        gender: "MALE",
        guardian: "guardian",
        guardianPhone: "guardianPhone",
        guardianIdentityCard: "guardianIdentityCard",
        relationship: "relationship",
      };
      //process data
      data.name = this.profile.last_name + " " + this.profile.first_name;
      data.address = this.profile.address.country;
      data.address = data.address + "," + this.profile.address.city;
      data.address = data.address + "," + this.profile.address.district;
      data.address = data.address + "," + this.profile.address.sub_district;
      data.address =
        data.address + "," + this.profile.address.street_and_number;
      data.dob =
        this.profile.dob.year +
        "-" +
        this.profile.dob.month +
        "-" +
        this.profile.dob.date;
      data.phone = this.profile.phone;
      data.job = this.profile.job;
      data.identityCard = this.profile.identityCard;
      data.healthInsurance = this.profile.healthInsurance;
      data.folk = this.profile.folk;
      data.gender = this.profile.gender;
      data.guardian = this.profile.guardian;
      data.guardianPhone = this.profile.guardianPhone;
      data.guardianIdentityCard = this.profile.guardianIdentityCard;
      data.relationship = this.profile.relationship;
      // let token = this.$store.getters["auth/access_token"];
      // const params = {
      //   token: token,
      //   data: data,
      // };
      // await this.$store.dispatch("profile/add_new_profile", params);
      console.log(data);
    },

    editProfile() {
      console.log("editprofile");
    },

    /**
     * Emitting an interface with callable methods from outside
     */
    emitInterface() {
      this.$emit("interface", {
        addNewProfile: () => this.addNewProfile(),
        editProfile: () => this.editProfile(),
      });
    },
  },
};
</script>
