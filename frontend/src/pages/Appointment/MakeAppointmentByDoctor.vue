<template>
  <div class="content">
    <v-stepper class="stepper" v-model="visible_navigation">
      <v-stepper-header>
        <v-stepper-step :complete="step.step1" step="1" color="green lighten-1">
          Chọn hồ sơ
        </v-stepper-step>

        <v-divider></v-divider>

        <v-stepper-step :complete="step.step2" step="2" color="green lighten-1">
          Chọn bác sĩ
        </v-stepper-step>

        <v-divider></v-divider>

        <v-stepper-step :complete="step.step3" step="3" color="green lighten-1">
          Chọn ngày khám
        </v-stepper-step>
        <v-divider></v-divider>
        <v-stepper-step :complete="step.step4" step="4" color="green lighten-1">
          Thông tin bổ sung
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
      class="selectdoctor-section md-layout"
      v-show="visible_navigation.visible_selectdoctor"
    >
      <v-col cols="8"
        ><v-card width="100%" min-height="500">
          <!-- <v-col align-self="center" cols="12"><v-card width="80%" outlined color="green">Hello</v-card></v-col> -->
          <v-footer
            padless
            width="100%"
            color="green lighten-1"
            class="mb-10 white--text"
          >
            <v-col class="text-center" cols="12">
              <strong>Danh sách bác sĩ</strong>
            </v-col>
          </v-footer>
           <v-col
            v-for="(doctor, index) in listDoctor"
            :key="index"
            cols="12"
          >
            <v-row class="my-1" justify="center" width="100%"
              ><v-card width="80%" class="pa-4"
                ><v-row width="100%"
                  ><v-col class="pa-2" cols="5">
                    <strong>Tên Bác Sĩ: {{ doctor.doctor.name }}</strong></v-col
                  ><v-col class="pa-2" cols="5">
                   <strong>Chuyên khoa: {{ doctor.doctor.specialty }}</strong></v-col
                  >
                  <v-col class="pa-2" cols="2">
                    <v-btn
                      class="mx-2"
                    
                      dark
                      small
                      color="green lighten-1"
                      @click="setSelectDoctor(doctor)"
                    >Chọn
                    </v-btn></v-col
                  ></v-row
                ></v-card
              ></v-row
            ></v-col>
          <!-- <v-row justify="center">
            <v-col cols="8">
              <v-row justify="center">
                <v-expansion-panels>
                  <v-expansion-panel
                    v-for="(doctor, index) in listDoctor"
                    :key="index"
                  >
                    <div class="panel-header">
                      <v-expansion-panel-header>
                        <div class="md-layout font-weight-bold">
                          {{ doctor.doctor.level }}. {{ doctor.doctor.name }}
                        </div>
                      </v-expansion-panel-header>
                      <div class="md-layout doctor-room">
                        Phòng {{ doctor.room.name }}
                      </div>
                      <v-expansion-panel-content>
                        <v-btn-toggle
                          v-model="time_select"
                          v-for="(time, time_index) in doctor.times"
                          :key="time_index"
                          color="green lighten-1"
                        >
                          <v-btn
                            outlined
                            color="green lighten-1"
                            :value="time"
                            @click="
                              setSelectDoctor(
                                doctor.doctor.id,
                                doctor.doctor.name,
                                doctor.room.name,
                                time
                              )
                            "
                            >{{ time }}</v-btn
                          >
                        </v-btn-toggle>
                      </v-expansion-panel-content>
                    </div>
                  </v-expansion-panel>
                </v-expansion-panels>
              </v-row>
            </v-col>
            <div class="content">{{ this.doctorSelection }}</div></v-row
          > -->

        </v-card>
        
      </v-col>
      <v-col cols="4"
        ><v-card width="100%" min-height="400">
          <v-footer
            padless
            width="100%"
            color="green lighten-1"
            class="white--text"
          >
            <v-col class="text-center" cols="12">
              <strong>Tìm kiếm lịch hẹn</strong>
            </v-col>
          </v-footer>
          <label>Chọn chuyên khoa</label>
          <v-select
            v-model="profile_id_selection"
            :items="profile_patient_list"
            item-text="name"
            item-value="id"
            label="Solo field"
            solo
          ></v-select>
          <v-text-field
                        
                        label="Nhập tên bác sĩ"
                        required
                      ></v-text-field>
          
          <v-row class="mt-5" justify="center"
            ><v-btn class="white--text" color="green lighten-1" @click="getDoctorList">
              Tìm kiếm
            </v-btn></v-row
          >
        </v-card>
      </v-col>
    </div>
  </div>
</template>

<script>
export default {
  created() {
    this.getProfileList();
  },

  data() {
    return {
      visible_navigation: {
        visible_selectprofile: true,
        visible_selectdate: false,
        visible_selectopd: false,
        visible_selectdoctor: false,
        visible_verify: false,
      },
      step: {
        step1: false,
        step2: false,
        step3: false,
        step4: false,
        step5: false,
      },

      profile_patient_list: [],

      new_patient_info: {},

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

      listDoctor: [
        {
          id: 3,
          date: "16",
           room: {
            name: "H2 á"
          },
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
          room: {
            name: "H2 á"
          },
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
          room: {
            name: "H2 á"
          },
          times: ["10-11", "11-12"],
          doctor: {
            id: "3e2c4b76-d826-4202-a987-7108047f7fb8",
            name: "John",
            specialty: "NHI KHOA",
            level: "level",
          },
        },
      ],

      doctorSelection: null,

    };
  },

  methods: {
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

    selectProfileComplete() {
      this.visible_navigation.visible_selectprofile = false;
      this.visible_navigation.visible_selectdoctor = true;
      this.step.step1 = true;
    },

    setSelectDoctor(doctor) {
      this.doctorSelection = doctor; 
    },

    async getDoctorList(){
      console.log("yeah")
    }
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
