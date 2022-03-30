<template>
  <div class="content">
    <v-stepper class="stepper" v-model="visible_navigation">
      <v-stepper-header>
        <v-stepper-step :complete="step.step1" step="1" color="green lighten-1">
          Chọn hồ sơ
        </v-stepper-step>

        <v-divider></v-divider>

        <v-stepper-step :complete="step.step2" step="2" color="green lighten-1">
          Chọn ngày khám
        </v-stepper-step>

        <v-divider></v-divider>

        <v-stepper-step :complete="step.step3" step="3" color="green lighten-1">
          Chọn chuyên khoa khám
        </v-stepper-step>
        <v-divider></v-divider>
        <v-stepper-step :complete="step.step4" step="4" color="green lighten-1">
          Chọn bác sĩ và khung giờ khám
        </v-stepper-step>
      </v-stepper-header>
    </v-stepper>
    <div class="md-layout" v-show="visible_navigation.visible_selectprofile">
      <div
        class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-60"
      >
        <md-card>
          <md-card-header class="header-patient" data-background-color="green">
            <h4 class="title-patient">CHỌN HỒ SƠ BỆNH NHÂN</h4>
          </md-card-header>
          <md-card-content>
            <md-table
              v-model="profile_patient_list"
              :table-header-color="tableHeaderColor"
            >
              <md-table-row
                slot="md-table-row"
                slot-scope="{ item }"
                @click="SetPatientInfo(item)"
              >
                <md-table-cell md-label="Họ và tên">{{
                  item.name
                }}</md-table-cell>
              </md-table-row>
            </md-table>
            <!-- <md-button class="md-round md-success">Thêm hồ sơ</md-button> -->
            <v-dialog
              transition="dialog-top-transition"
              max-width="600"
              v-model="dialog"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                  class="white--text"
                  color="green lighten-1"
                  v-bind="attrs"
                  v-on="on"
                  >Thêm hồ sơ</v-btn
                >
              </template>
              <template>
                <v-card>
                  <v-toolbar color="green lighten-1" dark>Thêm Hồ Sơ</v-toolbar>
                  <v-card-text>
                    <v-form ref="form" v-model="valid" lazy-validation>
                      <v-text-field
                        v-model="new_patient_info.name"
                        label="Họ và tên"
                        required
                      ></v-text-field>
                      <v-menu
                        v-model="menu2"
                        :close-on-content-click="false"
                        :nudge-right="40"
                        transition="scale-transition"
                        offset-y
                        min-width="auto"
                      >
                        <template v-slot:activator="{ on, attrs }">
                          <v-text-field
                            v-model="new_patient_info.dob"
                            label="Ngày sinh"
                            prepend-icon="mdi-calendar"
                            readonly
                            v-bind="attrs"
                            v-on="on"
                          ></v-text-field>
                        </template>
                        <v-date-picker
                          v-model="new_patient_info.dob"
                          @input="menu2 = false"
                          locale="vi-cn"
                        ></v-date-picker>
                      </v-menu>

                      <v-text-field
                        v-model="new_patient_info.phoneNumber"
                        label="Số điện thoại"
                        required
                      ></v-text-field>

                      <v-text-field
                        v-model="new_patient_info.address"
                        label="Địa chỉ"
                        required
                      ></v-text-field>

                      <v-text-field
                        v-model="new_patient_info.gender"
                        label="Giới tính"
                        required
                      ></v-text-field>
                      <v-text-field
                        v-model="new_patient_info.identityCard"
                        label="CMND"
                        required
                      ></v-text-field>

                      <v-text-field
                        v-model="new_patient_info.job"
                        label="Nghề nghiệp"
                        required
                      ></v-text-field>
                      <v-text-field
                        v-model="new_patient_info.folk"
                        label="Dân tộc"
                        required
                      ></v-text-field>
                      <v-text-field
                        v-model="new_patient_info.healthInsurance"
                        label="Mã số thẻ bảo hiểm y tế"
                        required
                      ></v-text-field>
                      <v-text-field
                        v-model="new_patient_info.protector"
                        label="Tên người giám hộ"
                        required
                      ></v-text-field>
                    </v-form>
                  </v-card-text>
                  <v-card-actions class="justify-end">
                    <v-btn text @click="AddNewPatientProfile">Thêm</v-btn>
                    <v-btn text @click="dialog = false">Đóng</v-btn>
                  </v-card-actions>
                </v-card>
              </template>
            </v-dialog>
          </md-card-content>
        </md-card>
      </div>
      <div class="md-layout-item md-medium-size-100 md-size-33">
        <md-card>
          <md-card-header class="header-patient" data-background-color="green">
            <h4 class="title-patient">THÔNG TIN</h4>
          </md-card-header>
          <md-card-content>
            <md-table :table-header-color="tableHeaderColor">
              <md-table-row v-model="patient_info">
                <p>Ngày sinh: {{ patient_info.dob }}</p>
                <p>Số điện thoại: {{ patient_info.phoneNumber }}</p>
                <p>Giới tính: {{ patient_info.gender }}</p>
                <p>CMND: {{ patient_info.identityCard }}</p>
                <p>Nghề nghiệp: {{ patient_info.job }}</p>
                <p>Địa chỉ: {{ patient_info.address }}</p>
                <p>Dân tộc: {{ patient_info.folk }}</p>
                <p>Người giám hộ: {{ patient_info.protector }}</p>
                <p>Thẻ bảo hiểm y tế: {{ patient_info.healthInsurance }}</p>
              </md-table-row>
            </md-table>
            <md-button
              class="md-round md-success select"
              @click="selectProfileComplete"
              >Chọn
            </md-button>
          </md-card-content>
        </md-card>
      </div>
    </div>
    <div
      class="md-layout select-day"
      v-show="visible_navigation.visible_selectdate"
    >
      <SelectDate ref="dateSelect"></SelectDate>
      <div class="btns-selectdate">
        <md-button class="md-round md-success back-button">Quay lại</md-button>
        <md-button
          class="md-round md-success continue-button"
          @click="SelectDateComplete"
          >Tiếp tục</md-button
        >
      </div>
    </div>
    <div
      class="md-layout select-opd"
      v-show="visible_navigation.visible_selectopd"
    >
      <SelectOPD ref="OPDSelect"> </SelectOPD>
      <div class="btns-selectdate">
        <md-button class="md-round md-success back-button">Quay lại</md-button>
        <md-button
          class="md-round md-success continue-button"
          @click="SelectOPDComplete"
          >Tiếp tục</md-button
        >
      </div>
    </div>
    <div
      class="md-layout select-doctor-layout"
      v-show="visible_navigation.visible_selectdoctor"
    >
      <SelectDoctor
        class="md-layout-item md-size-80 select-doctor-item"
        ref="doctorSelect"
        :listDoctor="listDoctor"
      ></SelectDoctor>
      <div class="btns-selectdate">
        <md-button class="md-round md-success back-button">Quay lại</md-button>
        <md-button
          class="md-round md-success continue-button"
          @click="SelectDoctorComplete"
          >Tiếp tục</md-button
        >
      </div>
    </div>
    <v-card
      class="md-layout glutter verify-layout"
      width="800"
      v-show="visible_navigation.visible_verify"
    >
      <div class="md-layout-item md-size-100">
        <v-toolbar flat color="green lighten-1" dark>
          <v-toolbar-title>Thông tin khám</v-toolbar-title>
        </v-toolbar>
      </div>
      <div class="md-layout-item md-size-100">
        <div class="md-layout-item verify-column md-size-25">
          <div><p class="font-weight-bold">Chuyên khoa</p></div>
          <div>{{ this.opdSelection }}</div>
        </div>
        <div class="md-layout-item verify-column md-size-25">
          <div><p class="font-weight-bold">Bác sĩ</p></div>
          <div>{{ this.doctorSelection.name }}</div>
        </div>
        <div class="md-layout-item verify-column md-size-25">
          <div><p class="font-weight-bold">Ngày khám</p></div>
          <div>{{ this.dateSelection }}</div>
          <div>{{ this.doctorSelection.time }}</div>
        </div>
        <div class="md-layout-item verify-column md-size-25">
          <div><p class="font-weight-bold">Phòng</p></div>
          <div>{{ this.doctorSelection.room }}</div>
        </div>
      </div>
      <div class="md-layout-item md-size-100">
        <v-toolbar flat color="green lighten-1" dark>
          <v-toolbar-title>Thông tin bệnh nhân</v-toolbar-title>
        </v-toolbar>
      </div>
      <div class="md-layout-item md-size-100">
        <div class="md-layout-item verify-column md-size-50">
          <div>
            <strong class="font-weight-bold">Họ và tên: </strong>
            {{ this.patient_info.name }}
          </div>
          <div>
            <strong class="font-weight-bold">Ngày sinh: </strong
            >{{ this.patient_info.dob }}
          </div>
          <div>
            <strong class="font-weight-bold">Giới tính: </strong
            >{{ this.patient_info.gender }}
          </div>
          <div>
            <strong class="font-weight-bold">Số điện thoại: </strong>
            {{ this.patient_info.phoneNumber }}
          </div>
        </div>
        <div class="md-layout-item verify-column md-size-50">
          <div>
            <strong class="font-weight-bold">CMND: </strong
            >{{ this.patient_info.identityCard }}
          </div>
          <div>
            <strong class="font-weight-bold">Dân tộc: </strong
            >{{ this.patient_info.folk }}
          </div>
          <div>
            <strong class="font-weight-bold">Nghề nghiệp: </strong>
            {{ this.patient_info.job }}
          </div>
        </div>
      </div>
      <br />
      <div class="md-layout-item md-size-100">
        <v-toolbar flat color="green lighten-1" dark>
          <v-toolbar-title>Thông tin bổ sung</v-toolbar-title>
        </v-toolbar>
        <div class="md-layout-item verify-column md-size-50">
          <label class="font-weight-bold" for="">Triệu chứng</label>
          <v-card>
            <input type="text" style="width: 100%" />
          </v-card>
          <label class="font-weight-bold" for="">Mô tả chi tiết</label>
          <v-card>
            <input type="text" style="width: 100%" />
          </v-card>
          <label class="font-weight-bold" for=""
            >Thời gian diễn ra tình trạng trên</label
          >
          <v-card>
            <input type="text" style="width: 100%" />
          </v-card>
          <v-checkbox
            v-model="ex4"
            label="Đã tự điều trị bệnh"
            color="success"
            value="success"
            hide-details
          ></v-checkbox>
        </div>
        <div class="md-layout-item verify-column md-size-100">
          <label class="font-weight-bold" for=""
            >Hình ảnh, kết quả các xét nghiệm trước đó (nếu có)</label
          >
          <v-file-input
            v-model="files"
            color="green lighten-1"
            counter
            label=""
            multiple
            placeholder="Chọn file"
            prepend-icon="mdi-paperclip"
            height="40"
            outlined
            :show-size="1000"
          >
            <template v-slot:selection="{ index, text }">
              <v-chip v-if="index < 2" color="green lighten-1" dark label small>
                {{ text }}
              </v-chip>

              <span
                v-else-if="index === 2"
                class="text-overline grey--text text--darken-3 mx-2"
              >
                +{{ files.length - 2 }} File(s)
              </span>
            </template>
          </v-file-input>
        </div>
      </div>
      <div class="btns-verify">
        <md-button class="md-round md-success back-button">Quay lại</md-button>
        <md-button class="md-round md-success continue-button"
          >Xác nhận</md-button
        >
      </div>
    </v-card>
  </div>
</template>

<script>
import SelectDate from "@/pages/Appointment/SelectDate.vue";
import SelectOPD from "@/pages/Appointment/SelectOPD.vue";
import SelectDoctor from "@/pages/Appointment/SelectDoctor.vue";

export default {
  components: {
    SelectDate,
    SelectOPD,
    SelectDoctor,
  },
  created() {
    this.getProfileList();
  },
  data() {
    return {
      dialog: false,
      menu2: false,

      visible_navigation: {
        visible_selectprofile: true,
        visible_selectdate: false,
        visible_selectopd: false,
        visible_selectdoctor: false,
        visible_verify: false,
      },

      profile_patient_list: [],

      patient_info: {
        id: "",
        profileNumber: "",
        name: "",
        address: "",
        phoneNumber: "",
        dob: "",
        job: "",
        identityCard: "",
        healthInsurance: "",
        folk: "",
        gender: "",
        protector: "",
      },

      new_patient_info: {},

      step: {
        step1: false,
        step2: false,
        step3: false,
        step4: false,
        step5: false,
      },
      dateSelection: "",
      opdSelection: "",
      doctorSelection: "",
      profile_list: [],
      listDoctor: [
        {
          id: 3,
          date: "16",
          room: "H2 á",
          times: ["10-11", "11-12"],
          doctor: {
            id: "3e2c4b76-d826-4202-a987-7108047f7fb8",
            name: "John",
            specialty: "NHI KHOA",
            level: "level",
          },
        },
        {
          id: 3,
          date: "16",
          room: "H2 á",
          times: ["10-11", "11-12"],
          doctor: {
            id: "3e2c4b76-d826-4202-a987-7108047f7fb8",
            name: "John",
            specialty: "NHI KHOA",
            level: "level",
          },
        },
        {
          id: 3,
          date: "16",
          room: "H2 á",
          times: ["10-11", "11-12"],
          doctor: {
            id: "3e2c4b76-d826-4202-a987-7108047f7fb8",
            name: "John",
            specialty: "NHI KHOA",
            level: "level",
          },
        },
      ],
    };
  },

  methods: {
    selectProfileComplete() {
      this.visible_navigation.visible_selectprofile = false;
      this.visible_navigation.visible_selectdate = true;
      this.step.step1 = true;
    },

    SelectDateComplete() {
      this.visible_navigation.visible_selectdate = false;
      this.visible_navigation.visible_selectopd = true;
      this.dateSelection = this.$refs.dateSelect.date;
      this.step.step2 = true;
    },

    SelectOPDComplete() {
      this.visible_navigation.visible_selectopd = false;
      this.visible_navigation.visible_selectdoctor = true;
      this.opdSelection = this.$refs.OPDSelect.selected;
      this.step.step3 = true;

      this.getDoctorList();
    },

    SelectDoctorComplete() {
      this.visible_navigation.visible_selectdoctor = false;
      this.visible_navigation.visible_verify = true;
      this.doctorSelection = this.$refs.doctorSelect.doctorSelection;
      this.step.step4 = true;
    },

    SetPatientInfo(patient_select) {
      this.patient_info.id = patient_select.id;
      this.patient_info.profileNumber = patient_select.profileNumber;
      this.patient_info.address = patient_select.address;
      this.patient_info.phoneNumber = patient_select.phoneNumber;
      this.patient_info.dob = patient_select.dob;
      this.patient_info.job = patient_select.job;
      this.patient_info.identityCard = patient_select.identityCard;
      this.patient_info.healthInsurance = patient_select.healthInsurance;
      this.patient_info.folk = patient_select.folk;
      this.patient_info.gender = patient_select.gender;
      this.patient_info.protector = patient_select.protector;
      this.patient_info.name = patient_select.name;
    },

    async AddNewPatientProfile() {
      console.log(this.new_patient_info);
      const params = {
        token: this.$store.getters["auth/access_token"],
        data: this.new_patient_info,
      };
      this.dialog = false;
      await this.$store.dispatch("profile/add_new_profile", params);
      this.new_patient_info = {};
      this.getProfileList();
    },

    async getProfileList() {
      const params = {
        token: this.$store.getters["auth/access_token"],
      };

      await this.$store.dispatch("profile/profile_list", params);
      this.profile_patient_list = this.$store.getters["profile/profile_list"];
      console.log(this.profile_patient_list);
    },

    async getDoctorList() {
      const params = {
        token: this.$store.getters["auth/access_token"],
        data: {
          date: this.dateSelection,
          specialty: this.opdSelection,
        },
      };
      await this.$store.dispatch(
        "appointment/getDoctorList_byDateAndSpeciality",
        params
      );
      //this.listDoctor = this.$store.getters["appointment/doctors_list"];
      console.log(this.listDoctor);
    },
  },
};
</script>
<style scoped>
.header-patient {
  text-align: center;
}
.select {
  margin: auto;
  display: block;
}
.btns-selectdate {
  width: 700px;
}
.select-day {
  flex-direction: column;
  align-items: center;
  background-color: white;
  padding-bottom: 50px;
}
.select-opd {
  flex-direction: column;
  align-items: center;
  padding: 100px;
  background-color: white;
}
.select-doctor-layout {
  flex-direction: column;
  align-items: center;
  background-color: white;
}

.select-doctor-item {
  margin: 50px;
}
.verify-layout {
  flex-direction: column;
  align-items: center;
  background-color: white;
  /* border-style: solid;
  border-color: #37833b; */
}
.verify-column {
  float: left;
  display: block;
}
.md-layout.glutter.verify-layout {
  width: 100%;
  margin: auto;
}

.btns-verify {
  width: 100%;
  margin-top: 100px;
}
.continue-button {
  float: right;
}
.stepper {
  margin: 10px;
}
</style>
