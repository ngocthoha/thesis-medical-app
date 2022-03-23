<template>
  <div class="content">
    <div class="md-layout" v-show="visible_selectprofile">
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
                            v-model="new_patient_info.birthday"
                            label="Ngày sinh"
                            prepend-icon="mdi-calendar"
                            readonly
                            v-bind="attrs"
                            v-on="on"
                          ></v-text-field>
                        </template>
                        <v-date-picker
                          v-model="new_patient_info.birthday"
                          @input="menu2 = false"
                          locale="vi-cn"
                        ></v-date-picker>
                      </v-menu>

                      <v-text-field
                        v-model="new_patient_info.phone"
                        label="Số điện thoại"
                        required
                      ></v-text-field>

                      <v-text-field
                        v-model="new_patient_info.sex"
                        label="Giới tính"
                        required
                      ></v-text-field>
                      <v-text-field
                        v-model="new_patient_info.id"
                        label="CMND"
                        required
                      ></v-text-field>

                      <v-text-field
                        v-model="new_patient_info.job"
                        label="Nghề nghiệp"
                        required
                      ></v-text-field>
                      <v-text-field
                        v-model="new_patient_info.ethnic"
                        label="Dân tộc"
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
                <p>Ngày sinh: {{ patient_info.birthday }}</p>
                <p>Số điện thoại: {{ patient_info.phone }}</p>
                <p>Giới tính: {{ patient_info.sex }}</p>
                <p>CMND: {{ patient_info.id }}</p>
                <p>Nghề nghiệp: {{ patient_info.job }}</p>
                <p>Dân tộc: {{ patient_info.ethnic }}</p>
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
    <div class="md-layout select-day" v-show="visible_selectdate">
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
    <div class="md-layout select-opd" v-show="visible_selectopd">
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
    <div class="md-layout select-doctor-layout" v-show="visible_selectdoctor">
      <SelectDoctor
        class="md-layout-item md-size-80 select-doctor-item"
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
    <div class="md-layout glutter verify-layout" v-show="visible_verify">
      <div class="md-layout-item">Thông tin khám</div>
      <div class="md-layout-item md-size-100">
        <div class="md-layout-item verify-column md-size-25">
          <div>Chuyên khoa</div>
          <div>{{ this.opdSelection }}</div>
        </div>
        <div class="md-layout-item verify-column md-size-25">
          <div>Bác sĩ</div>
          <div>BSCKII. Lâm Phương Nam</div>
        </div>
        <div class="md-layout-item verify-column md-size-25">
          <div>Ngày khám</div>
          <div>{{ this.dateSelection }}</div>
          <div>8:00 - 9:00</div>
        </div>
        <div class="md-layout-item verify-column md-size-25">
          <div>Phòng</div>
          <div>48</div>
        </div>
      </div>
      <div class="md-layout-item">Thông tin bệnh nhân</div>
      <div class="md-layout-item md-size-60">
        <div class="md-layout-item verify-column md-size-50">
          <div>Họ và tên: {{ this.patient_info.name }}</div>
          <div>Ngày sinh: {{ this.patient_info.birthday }}</div>
          <div>Giới tính: {{ this.patient_info.sex }}</div>
          <div>Số điện thoại: {{ this.patient_info.phone }}</div>
        </div>
        <div class="md-layout-item verify-column md-size-50">
          <div>CMND: {{ this.patient_info.id }}</div>
          <div>Dân tộc: {{ this.patient_info.ethnic }}</div>
          <div>Nghề nghiệp: {{ this.patient_info.job }}</div>
        </div>
      </div>
      <div class="btns-verify">
        <md-button class="md-round md-success back-button">Quay lại</md-button>
        <md-button class="md-round md-success continue-button"
          >Xác nhận</md-button
        >
      </div>
    </div>
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
  data() {
    return {
      dialog: false,
      menu2: false,

      visible_selectprofile: true,
      visible_selectdate: false,
      visible_selectopd: false,
      visible_selectdoctor: false,
      visible_verify: false,

      profile_patient_list: [
        {
          name: "Nguyễn Duy Thanh",
          birthday: "03/03/2000",
          phone: "0962530448",
          sex: "Nam",
          id: "077200003808",
          job: "Sinh viên",
          ethnic: "Kinh",
        },
        {
          name: "Hà Ngọc Thọ",
          birthday: "03/06/1999",
          phone: "096253021312",
          sex: "Nam",
          id: "077200009999",
          job: "Nhân viên văn phòng",
          ethnic: "Kinh",
        },
        {
          name: "Nguyễn Thúy Liễu",
          birthday: "03/03/2000",
          phone: "0962530448",
          sex: "Nữ",
          id: "077200005348",
          job: "Kỹ sư",
          ethnic: "Kinh",
        },
      ],

      patient_info: {
        name: "",
        birthday: "",
        phone: "",
        sex: "",
        id: "",
        job: "",
        ethnic: "",
      },

      new_patient_info: {
        name: "",
        birthday: "",
        phone: "",
        sex: "",
        id: "",
        job: "",
        ethnic: "",
      },

      dateSelection: "",
      opdSelection: "",
    };
  },

  methods: {
    selectProfileComplete() {
      this.visible_selectprofile = false;
      this.visible_selectdate = true;
    },
    SelectDateComplete() {
      this.visible_selectdate = false;
      this.visible_selectopd = true;
      this.dateSelection = this.$refs.dateSelect.date;
    },
    SelectOPDComplete() {
      this.visible_selectopd = false;
      this.visible_selectdoctor = true;
      this.opdSelection = this.$refs.OPDSelect.selected;
    },
    SelectDoctorComplete() {
      this.visible_selectdoctor = false;
      this.visible_verify = true;
    },

    SetPatientInfo(patient_select) {
      this.patient_info.name = patient_select.name;
      this.patient_info.birthday = patient_select.birthday;
      this.patient_info.phone = patient_select.phone;
      this.patient_info.sex = patient_select.sex;
      this.patient_info.id = patient_select.id;
      this.patient_info.job = patient_select.job;
      this.patient_info.ethnic = patient_select.ethnic;
    },

    AddNewPatientProfile() {
      console.log(this.new_patient_info);
      this.dialog = false;
      this.new_patient_info = {};
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
  width: 100%;
  border-style: solid;
  border-color: #37833b;
}
.verify-column {
  float: left;
  display: block;
}
.md-layout.glutter.verify-layout {
  width: 60%;
  margin: auto;
}

.btns-verify {
  width: 100%;
  margin-top: 100px;
}
.continue-button {
  float: right;
}
</style>
