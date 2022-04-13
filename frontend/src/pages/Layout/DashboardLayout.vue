<template>
  <div class="wrapper" :class="{ 'nav-open': $sidebar.showSidebar }">
    <notifications></notifications>

    <side-bar
      :sidebar-item-color="sidebarBackground"
      :sidebar-background-image="sidebarBackgroundImage"
    >
      <mobile-menu slot="content"></mobile-menu>

      <div class="user" v-show="user_type">
        <sidebar-link to="/home/appointment/appointment-by-date">
          <md-icon>person</md-icon>
          <p>Đặt lịch khám theo ngày</p>
        </sidebar-link>
        <sidebar-link to="/home/appointment/appointment-by-doctor">
          <md-icon>person</md-icon>
          <p>Đặt lịch khám bác sĩ</p>
        </sidebar-link>
        <sidebar-link to="/home/appointment/view-appointment">
          <md-icon>person</md-icon>
          <p>Lịch sử đặt lịch</p>
        </sidebar-link>
      </div>

      <div class="doctor" v-show="doctor_type">
        <sidebar-link to="/home/doctor/doctor-schedule">
          <md-icon>person</md-icon>
          <p>Lịch trình của bác sĩ</p>
        </sidebar-link>
        <sidebar-link to="/home/doctor/doctor-record">
          <md-icon>person</md-icon>
          <p>Hồ Sơ bệnh án</p>
        </sidebar-link>
        <sidebar-link to="/home/doctor/doctor-profile">
          <md-icon>person</md-icon>
          <p>Trang cá nhân</p>
        </sidebar-link>
        <sidebar-link to="/home/doctor/doctor-notification">
          <md-icon>person</md-icon>
          <p>Thông báo</p>
        </sidebar-link>
        <sidebar-link to="/home/doctor/doctor-healingprocess">
          <md-icon>person</md-icon>
          <p>Quá trình chữa bệnh</p>
        </sidebar-link>
      </div>

      <div class="admin" v-show="admin_type">
        <sidebar-link to="/home/admin/doctor-admin">
          <md-icon>person</md-icon>
          <p>Bác sĩ</p>
        </sidebar-link>
        <sidebar-link to="/home/admin/appointment-admin">
          <md-icon>person</md-icon>
          <p>Lịch hẹn</p>
        </sidebar-link>
        <sidebar-link to="/home/admin/patient-admin">
          <md-icon>person</md-icon>
          <p>Bệnh nhân</p>
        </sidebar-link>
        <sidebar-link to="/home/admin/record-admin">
          <md-icon>person</md-icon>
          <p>Bệnh án</p>
        </sidebar-link>
        <sidebar-link to="/home/admin/schedule-admin">
          <md-icon>person</md-icon>
          <p>Lịch trình</p>
        </sidebar-link>
        <sidebar-link to="/home/admin/account-admin">
          <md-icon>person</md-icon>
          <p>Tài khoản</p>
        </sidebar-link>
        <sidebar-link to="/home/admin/room-admin">
          <md-icon>person</md-icon>
          <p>Tạo phòng</p>
        </sidebar-link>
      </div>
    </side-bar>

    <div class="main-panel">
      <top-navbar></top-navbar>

      <fixed-plugin
        :color.sync="sidebarBackground"
        :image.sync="sidebarBackgroundImage"
      >
      </fixed-plugin>

      <dashboard-content> </dashboard-content>

      <content-footer v-if="!$route.meta.hideFooter"></content-footer>
    </div>
  </div>
</template>

<script>
import TopNavbar from "./TopNavbar.vue";
import ContentFooter from "./ContentFooter.vue";
import DashboardContent from "./Content.vue";
import MobileMenu from "@/pages/Layout/MobileMenu.vue";
import FixedPlugin from "./Extra/FixedPlugin.vue";

export default {
  components: {
    TopNavbar,
    DashboardContent,
    ContentFooter,
    MobileMenu,
    FixedPlugin,
  },

  created(){
    this.setType();
  },
  data() {
    return {
      sidebarBackground: "green",
      sidebarBackgroundImage: require("@/assets/img/sidebar-2.jpg"),
      doctor_type: false,
      user_type: false,
      admin_type: false,
    };
  },

  methods:{
    setType(){
      var type = this.$store.getters["auth/types"]
      type.forEach(element => {
        if(element.authority === "ROLE_USER"){
          this.user_type = true;
        }
         if(element.authority === "ROLE_DOCTOR"){
          this.doctor_type = true;
        }
         if(element.authority === "ROLE_ADMIN"){
          this.admin_type = true;
        }
      });
    }
  }
};
</script>
