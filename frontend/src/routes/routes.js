import DashboardLayout from "@/pages/Layout/DashboardLayout.vue";

import Dashboard from "@/pages/Dashboard.vue";
import UserProfile from "@/pages/UserProfile.vue";
import TableList from "@/pages/TableList.vue";
import Typography from "@/pages/Typography.vue";
import Icons from "@/pages/Icons.vue";
import Maps from "@/pages/Maps.vue";
import Notifications from "@/pages/Notifications.vue";
import UpgradeToPRO from "@/pages/UpgradeToPRO.vue";

import Login from "@/pages/Login/Login.vue";

//user page
import AppointmentByDate from "@/pages/Appointment/MakeAppointmentByDate.vue";
import AppointmentByDoctor from "@/pages/Appointment/MakeAppointmentByDoctor.vue";

//doctor page
import ScheduleOfDoctor from "@/pages/Doctor/ScheduleOfDoctor.vue";
import HoSoBenhNhan from "@/pages/Doctor/HoSoBenhNhan.vue";
import Profile from "@/pages/Doctor/Profile.vue";

let authPage = {
  path: "/login",
  name: "login",
  component: Login,
};

const routes = [
  {
    path: "/",
    component: DashboardLayout,
    redirect: "/appointment/appointment-by-date",
    children: [
      {
        path: "dashboard",
        name: "Dashboard",
        component: Dashboard,
      },
      {
        path: "user",
        name: "User Profile",
        component: UserProfile,
      },
      {
        path: "table",
        name: "Table List",
        component: TableList,
      },
      {
        path: "typography",
        name: "Typography",
        component: Typography,
      },
      {
        path: "icons",
        name: "Icons",
        component: Icons,
      },
      {
        path: "maps",
        name: "Maps",
        meta: {
          hideFooter: true,
        },
        component: Maps,
      },
      {
        path: "notifications",
        name: "Notifications",
        component: Notifications,
      },
      {
        path: "upgrade",
        name: "Upgrade to PRO",
        component: UpgradeToPRO,
      },
      {
        path: "appointment/appointment-by-date",
        name: "Đặt lịch khám theo ngày",
        component: AppointmentByDate,
      },
      {
        path: "appointment/appointment-by-doctor",
        name: "Đặt lịch khám bác sĩ",
        component: AppointmentByDoctor,
      },
      {
        path: "doctor/doctor-schedule",
        name: "Lịch trình",
        component: ScheduleOfDoctor,
      },
      {
        path: "doctor/doctor-record",
        name: "Hồ sơ bệnh nhân",
        component: HoSoBenhNhan,
      },
      {
        path: "doctor/doctor-profile",
        name: "Trang cá nhân",
        component: Profile,
      },
    ],
  },

  authPage,
];

export default routes;
