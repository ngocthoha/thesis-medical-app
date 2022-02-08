<template>
  <div class="content">
    <div class="md-layout" v-show="visible">
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
            <md-button class="md-round md-success select" @click="selectProfile">Chọn
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
    <select-date v-show="visible_selectdate"></select-date>
  </div>
</template>

<script>
//import FixedPlugin from "@/pages/Layout/Extra/FixedPlugin.vue";
import SelectDate from "@/pages/Appointment/SelectDate.vue";
export default {
  components: {
    SelectDate
  },
  data() {
    return {
      visible: true,
      visible_selectdate: false,
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
    selectProfile(){
      this.visible=false;
      this.visible_selectdate=true;
    },
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
</style>
