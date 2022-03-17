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
              v-model="profile_patient"
              :table-header-color="tableHeaderColor"
            >
              <md-table-row slot="md-table-row" slot-scope="{ item }">
                <md-table-cell md-label="Họ và tên">{{
                  item.name
                }}</md-table-cell>
              </md-table-row>
            </md-table>
            <md-button class="md-round md-success">Thêm hồ sơ</md-button>
          </md-card-content>
        </md-card>
      </div>
      <div class="md-layout-item md-medium-size-100 md-size-33">
        <md-card>
          <md-card-header class="header-patient" data-background-color="green">
            <h4 class="title-patient">THÔNG TIN</h4>
          </md-card-header>
          <md-card-content>
            <md-table
              v-model="patient_info"
              :table-header-color="tableHeaderColor"
            >
              <md-table-row slot="md-table-row" slot-scope="{ item }">
                <p>Ngày sinh: {{ item.birthday }}</p>
                <p>Số điện thoại: {{ item.phone }}</p>
                <p>Giới tính: {{ item.sex }}</p>
                <p>CMND: {{ item.sex }}</p>
                <p>Nghề nghiệp: {{ item.job }}</p>
                <p>Dân tộc: {{ item.ethnic }}</p>
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
      <SelectDate></SelectDate>
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
      <SelectOPD></SelectOPD>
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
          <div>Da Liễu</div>
        </div>
        <div class="md-layout-item verify-column md-size-25">
          <div>Bác sĩ</div>
          <div>BSCKII. Lâm Phương Nam</div>
        </div>
        <div class="md-layout-item verify-column md-size-25">
          <div>Ngày khám</div>
          <div>07/12/2021</div>
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
          <div>Họ và tên: {{ this.patient_info[0].name }}</div>
          <div>Ngày sinh: {{ this.patient_info[0].birthday }}</div>
          <div>Giới tính: {{ this.patient_info[0].sex }}</div>
          <div>Số điện thoại: {{ this.patient_info[0].phone }}</div>
        </div>
        <div class="md-layout-item verify-column md-size-50">
          <div>CMND: {{ this.patient_info[0].id }}</div>
          <div>Dân tộc: {{ this.patient_info[0].ethnic }}</div>
          <div>Nghề nghiệp: {{ this.patient_info[0].job }}</div>
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
      visible_selectprofile: true,
      visible_selectdate: false,
      visible_selectopd: false,
      visible_selectdoctor: false,
      visible_verify: false,
      profile_patient: [
        {
          name: "Nguyễn Duy Thanh",
        },
        {
          name: "Nguyễn Ngọc Tân",
        },
        {
          name: "Hà Ngọc Thọ",
        },
      ],
      patient_info: [
        {
          name: "Nguyễn Duy Thanh",
          birthday: "03/03/2000",
          phone: "0962530448",
          sex: "Nam",
          id: "077200003808",
          job: "Sinh viên",
          ethnic: "Kinh",
        },
      ],
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
    },
    SelectOPDComplete() {
      this.visible_selectopd = false;
      this.visible_selectdoctor = true;
    },
    SelectDoctorComplete() {
      this.visible_selectdoctor = false;
      this.visible_verify = true;
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
</style>
