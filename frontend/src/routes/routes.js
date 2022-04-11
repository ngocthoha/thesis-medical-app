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
import Signup from "@/pages/Login/Signup.vue";

//user page
import AppointmentByDate from "@/pages/Appointment/MakeAppointmentByDate.vue";
import AppointmentByDoctor from "@/pages/Appointment/MakeAppointmentByDoctor.vue";
import ViewAppointment from "@/pages/Appointment/ViewAppointment.vue";

//doctor page
import ScheduleOfDoctor from "@/pages/Doctor/ScheduleOfDoctor.vue";
import HoSoBenhNhan from "@/pages/Doctor/HoSoBenhNhan.vue";
import Profile from "@/pages/Doctor/Profile.vue";
import Notification from "@/pages/Doctor/Notification.vue";
import Healingprocess from "@/pages/Doctor/Healingprocess.vue";

//admin page
import DoctorAdmin from "@/pages/Admin/DoctorAdmin.vue";
import AppointmentAdmin from "@/pages/Admin/AppointmentAdmin.vue";
import PatientAdmin from "@/pages/Admin/PatientAdmin.vue";
import RecordAdmin from "@/pages/Admin/RecordAdmin.vue";
import ScheduleAdmin from "@/pages/Admin/ScheduleAdmin.vue";
import AccountAdmin from "@/pages/Admin/AccountAdmin.vue";

//home page
import Home from "@/pages/Home/home.vue";

let authPage = {
  path: "/login",
  name: "Đăng nhập",
  component: Login,
};

let homePage = {
  path: "/",
  name: "home",
  component: Home,
};

const routes = [
  homePage,
  authPage,
  {
    path: "/signup",
    name: "Đăng ký",
    component: Signup,
  },
  {
    path: "/home",
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
        name: "Danh sách bệnh án",
        component: HoSoBenhNhan,
      },
      {
        path: "doctor/doctor-healingprocess",
        name: "Quá trình chữa bệnh",
        component: Healingprocess,
      },
      {
        path: "doctor/doctor-profile",
        name: "Trang cá nhân",
        component: Profile,
      },
      {
        path: "doctor/doctor-notification",
        name: "Thông báo",
        component: Notification,
      },
      {
        path: "admin/doctor-admin",
        name: "Bác sĩ",
        component: DoctorAdmin,
      },
      {
        path: "admin/appointment-admin",
        name: "Lịch hẹn",
        component: AppointmentAdmin,
      },
      {
        path: "admin/patient-admin",
        name: "Bệnh nhân",
        component: PatientAdmin,
      },
      {
        path: "admin/record-admin",
        name: "Bệnh án",
        component: RecordAdmin,
      },
      {
        path: "admin/account-admin",
        name: "Tài khoản",
        component: AccountAdmin,
      },
      {
        path: "admin/schedule-admin",
        name: "Lịch trình",
        component: ScheduleAdmin,
      },
      {
        path: "appointment/view-appointment",
        name: "Lịch sử đặt khám",
        component: ViewAppointment,
      },
    ],
  },
];

export default routes;
