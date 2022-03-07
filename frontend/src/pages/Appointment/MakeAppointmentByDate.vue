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
                <md-table-cell md-label="Họ và tên">{{ item.name }}</md-table-cell>
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
                <p>CMND: {{ item.sex }} </p>
                <p>Nghề nghiệp: {{ item.job }} </p>
                <p>Dân tộc: {{ item.ethnic }} </p>
              </md-table-row>
            </md-table>
            <md-button class="md-round md-success select" @click="selectProfileComplete">Chọn
              <!-- <fixed-plugin
              v-for="item in sidebarColors"
              :key="item.color"
              class="badge filter"
              :class="[`badge-${item.color}`, { active: item.active }]"
              :data-color="item.color"
              @click="changeSidebarBackground(item)"
              ></fixed-plugin> -->
            </md-button>
          </md-card-content>
        </md-card>
      </div>
    </div>
    <div class="md-layout select-day" v-show="visible_selectdate">
       <SelectDate></SelectDate>
        <div class="btns-selectdate">
            <md-button class="md-round md-success back-button">Quay lại</md-button>
            <md-button class="md-round md-success continue-button" @click="SelectDateComplete">Tiếp tục</md-button>
        </div>
    </div>
    <div class="md-layout select-opd" v-show="visible_selectopd">
        <SelectOPD></SelectOPD>
        <div class="btns-selectdate">
            <md-button class="md-round md-success back-button">Quay lại</md-button>
            <md-button class="md-round md-success continue-button" @click="SelectOPDComplete">Tiếp tục</md-button>
        </div>
    </div>
  </div>
</template>

<script>
//import FixedPlugin from "@/pages/Layout/Extra/FixedPlugin.vue";
import SelectDate from "@/pages/Appointment/SelectDate.vue";
import SelectOPD from "@/pages/Appointment/SelectOPD.vue";
export default {
  components: {
    SelectDate,
    SelectOPD,
  },
  data() {
    return {
      visible_selectprofile: true,
      visible_selectdate: false,
      visible_selectopd: false,
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
           birthday: "03/03/2000",
           phone: "0962530448",
           sex: "Nam",
           id: "077200003808",
           job: "Sinh viên",
           ethnic: "Kinh",
           }
       ]
    };
  },
  methods: {
    selectProfileComplete(){
      this.visible_selectprofile=false;
      this.visible_selectdate=true;
    },
    SelectDateComplete(){
      this.visible_selectdate=false;
      this.visible_selectopd = true;
    },
     SelectOPDComplete(){
      this.visible_selectdate=false;
      this.visible_selectopd = true;
    }
  },
};
</script>
<style scoped>
    .header-patient{
        text-align: center;
    }
    .select{
        margin:auto;
        display: block;
    }
    .btns-selectdate{
        width: 700px;
    }
    .select-day{
      flex-direction: column;
      align-items: center;
      background-color: white;
      padding-bottom: 50px;
    }
    .select-opd{
      flex-direction: column;
      align-items: center;
      padding: 100px;
      background-color: white;
    }
</style>
