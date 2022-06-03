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
    layout("Home", [route("Trang chủ", { default: "home/home" }, "/"), route("Dịch vụ",  { default: "home/Service" }, "/service"),]),
    

    //DashBoard Pages
    layout("Default", [
      route("Dashboard", { default: "Dashboard" }, "/dashboardall"),

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
      route("Bệnh Án", { default: "doctor/Record" }, "/doctor/doctor-record"),
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
      route(
        "Tạo tài khoản bác sĩ",
        { default: "admin/DoctorAdmin" },
        "/admin/doctor-admin"
      ),
      route(
        "Trạng thái cuộc hẹn trong ngày",
        { default: "admin/AppointmentStatus" },
        "/admin/appointment-status-admin"
      ),
      route(
        "Lịch hẹn",
        { default: "admin/AppointmentAdmin" },
        "/admin/appointment-admin"
      ),
      route(
        "Dashboard Admin",
        { default: "admin/DashboardAdmin" },
        "/admin/dashboard-admin"
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
    route("Đăng ký", { default: "auth/Signup" }, "/signup"),

    //UserPages
    // layout(
    //   "Home",
    //   [
    //     layout(
    //       "User",
    //       [route("Đặt lịch hẹn", { default: "user/CreateAppointment" }, "add")],
    //       "user"
    //     )
    //   ],
    //   "home"
    // )

    
    layout(
      "Home",
      [
        layout(
          "User",
          [
            route(
              "Đặt lịch khám theo ngày",
              { default: "user/CreateAppointment" },
              "create-appointment"
            ),
            route(
              "Xem lịch sử cuộc hẹn",
              { default: "user/ViewAppointment" },
              "view-appointment"
            )
          ],
          "user"
        )
      ],
      "/home"
    )

    // {
    //   path: "/home",
    //   component: () =>
    //     import(
    //       /* webpackChunkName: "layout-[request]" */
    //       `@/layouts/home/Index`
    //     ),
    //   children: [
    //     {
    //       path: "user",
    //       component: () =>
    //         import(
    //           /* webpackChunkName: "layout-[request]" */
    //           `@/layouts/user/Index`
    //         ),
    //       children: [
    //         //{ path: '', redirect: { name: 'posts' } },
    //         {
    //           path: "add",
    //           name: "Tạo lịch hẹn",
    //           component: () =>
    //             import(
    //               /* webpackChunkName: "views-[request]" */
    //               `@/views/user/CreateAppointment`
    //             )
    //         }
    //       ]
    //     }
    //   ]
    // }
  ]
});

router.beforeEach((to, from, next) => {
  return to.path.endsWith("/") ? next() : next(trailingSlash(to.path));
});

export default router;
