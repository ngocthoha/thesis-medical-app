<template>
  <div class="wrapper" :class="{ 'nav-open': $sidebar.showSidebar }">
    <notifications></notifications>

    <side-bar
      :sidebar-item-color="sidebarBackground"
      :sidebar-background-image="sidebarBackgroundImage"
    >
      <mobile-menu slot="content"></mobile-menu>
      <!--<sidebar-link to="/dashboard">
        <md-icon>dashboard</md-icon>
        <p>Dashboard</p>
      </sidebar-link>
      <sidebar-link to="/user">
        <md-icon>person</md-icon>
        <p>User Profile</p>
      </sidebar-link>
      <sidebar-link to="/table">
        <md-icon>content_paste</md-icon>
        <p>Table list</p>
      </sidebar-link>
      <sidebar-link to="/typography">
        <md-icon>library_books</md-icon>
        <p>Typography</p>
      </sidebar-link>
      <sidebar-link to="/icons">
        <md-icon>bubble_chart</md-icon>
        <p>Icons</p>
      </sidebar-link>
      <sidebar-link to="/maps">
        <md-icon>location_on</md-icon>
        <p>Maps</p>
      </sidebar-link>
      <sidebar-link to="/notifications">
        <md-icon>notifications</md-icon>
        <p>Notifications</p>
      </sidebar-link>-->
      <sidebar-link to="/home/appointment/appointment-by-date">
        <md-icon>person</md-icon>
        <p>Đặt lịch khám theo ngày</p>
      </sidebar-link>
      <sidebar-link to="/home/appointment/appointment-by-doctor">
        <md-icon>person</md-icon>
        <p>Đặt lịch khám bác sĩ</p>
      </sidebar-link>
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

      <!-- <sidebar-link to="/upgrade" class="active-pro">
        <md-icon>unarchive</md-icon>
        <p>Upgrade to PRO</p>
      </sidebar-link> -->
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
  data() {
    return {
      sidebarBackground: "green",
      sidebarBackgroundImage: require("@/assets/img/sidebar-2.jpg"),
    };
  },
};
</script>
