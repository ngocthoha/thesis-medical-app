// Imports
import Vue from "vue";
import Router from "vue-router";
import { trailingSlash } from "@/util/helpers";
import { layout, route } from "@/util/routes";

Vue.use(Router);

const router = new Router({
  mode: "history",
  base: process.env.BASE_URL,
  scrollBehavior: (to, from, savedPosition) => {
    if (to.hash) return { selector: to.hash };
    if (savedPosition) return savedPosition;

    return { x: 0, y: 0 };
  },

  routes: [
    //Home Pages
    route("Trang chủ", { default: "home/home" }, "/"),

    //DashBoard Pages
    layout("Default", [
      route("Dashboard"),

      // Pages
      route("UserProfile", null, "components/profile"),

      // Components
      route("Notifications", null, "components/notifications"),
      route("Icons", null, "components/icons"),
      route("Typography", null, "components/typography"),

      // Tables
      route("Regular Tables", null, "tables/regular"),

      // Maps
      route("Google Maps", null, "maps/google"),

      //doctor Pages
      route(
        "Lịch trình",
        { default: "doctor/Schedule" },
        "/doctor/doctor-schedule"
      ),
      route(
        "Bệnh Án",
        { default: "doctor/HoSoBenhNhan" },
        "/doctor/doctor-record"
      ),
      route(
        "Thông báo",
        { default: "doctor/Notification" },
        "/doctor/doctor-notification"
      ),
      route(
        "Quá trình chữa bệnh",
        { default: "doctor/Healingprocess" },
        "/doctor/doctor-healingprocess"
      ),

      //admin Pages
      route("Bác sĩ", { default: "admin/DoctorAdmin" }, "/admin/doctor-admin"),
      route(
        "Lịch hẹn",
        { default: "admin/AppointmentAdmin" },
        "/admin/appointment-admin"
      ),
      route(
        "Bệnh nhân",
        { default: "admin/PatientAdmin" },
        "/admin/patient-admin"
      ),
      route("Bệnh án", { default: "admin/RecordAdmin" }, "/admin/record-admin"),
      route(
        "Lịch trình BS",
        { default: "admin/ScheduleAdmin" },
        "/admin/schedule-admin"
      ),
      route(
        "Tài khoản",
        { default: "admin/AccountAdmin" },
        "/admin/account-admin"
      ),
      route("Tạo phòng", { default: "admin/RoomAdmin" }, "/admin/room-admin")
    ]),

    //Auth Pages
    route("Đăng nhập", { default: "auth/Login" }, "/login"),
    route("Đăng ký", { default: "auth/Signup" }, "/signup")
  ]
});

router.beforeEach((to, from, next) => {
  return to.path.endsWith("/") ? next() : next(trailingSlash(to.path));
});

export default router;
