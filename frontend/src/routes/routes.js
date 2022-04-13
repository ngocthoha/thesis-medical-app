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
import RoomAdmin from "@/pages/Admin/RoomAdmin.vue";

//home page
import Home from "@/pages/Home/home.vue";
import Contact from "@/pages/Home/Contact.vue";
import Introduce from "@/pages/Home/Introduce.vue";
import Procedure from "@/pages/Home/Procedure.vue";
import Service from "@/pages/Home/Service.vue";

import AllSpecialist from "@/pages/Home/Specialist/AllSpecialist.vue";
import ImageAnalysation from "@/pages/Home/Specialist/ImageAnalysation.vue"; // hình ảnh
import Cardiology from "@/pages/Home/Specialist/Cardiology.vue"; //tim mạch
import EarNoseThroat from "@/pages/Home/Specialist/EarNoseThroat.vue"; // tai mũi họng
import EndoscopyDepartment from "@/pages/Home/Specialist/EndoscopyDepartment.vue"; // nội soi
import Gastroenterology from "@/pages/Home/Specialist/Gastroenterology.vue"; // tiêu hóa
import MusclesBonesJoints from "@/pages/Home/Specialist/MusclesBonesJoints.vue"; // cơ xương khớp
import Obstetric from "@/pages/Home/Specialist/Obstetric.vue"; // sản
import Pediatrics from "@/pages/Home/Specialist/Pediatrics.vue"; // nhi
import PsychiatryDepartment from "@/pages/Home/Specialist/PsychiatryDepartment.vue"; // tâm thần
import RespiratoryMedicine from "@/pages/Home/Specialist/RespiratoryMedicine.vue"; // hô hấp
import TestLaboratory from "@/pages/Home/Specialist/TestLaboratory.vue"; // xét nghiệm
import Surgery from "@/pages/Home/Specialist/Surgery.vue"; //ngoại khoa

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
        path: "admin/room-admin",
        name: "Tạo phòng",
        component: RoomAdmin,
      },
      {
        path: "appointment/view-appointment",
        name: "Lịch sử đặt khám",
        component: ViewAppointment,
      },
    ],
    

  },
  {
    path: "/specialist",
    name: "Tất cả chuyên khoa",
    component: AllSpecialist,
  },
  {
    path: "/specialist/image-analysation",
    name: "Chẩn đoán hình ảnh",
    component: ImageAnalysation,
  },
  {
    path: "/specialist/Cardiology",
    name: "Tim mạch",
    component: Cardiology,
  },
  {
    path: "/specialist/EarNoseThroat",
    name: "Tai - Mũi - Họng",
    component: EarNoseThroat,
  },
  {
    path: "/specialist/EndoscopyDepartment",
    name: "Nội soi",
    component: EndoscopyDepartment,
  },
  {
    path: "/specialist/Gastroenterology",
    name: "Tiêu hóa gan mật",
    component: Gastroenterology,
  },
  {
    path: "/specialist/MusclesBonesJoints",
    name: "Cơ - Xương - Khớp",
    component: MusclesBonesJoints,
  },
  {
    path: "/specialist/Obstetric",
    name: "Sản - Phụ khoa",
    component: Obstetric,
  },
  {
    path: "/specialist/Pediatrics",
    name: "Nhi khoa",
    component: Pediatrics,
  },
  {
    path: "/specialist/PsychiatryDepartment",
    name: "Tâm thần kinh",
    component: PsychiatryDepartment,
  },
  {
    path: "/specialist/RespiratoryMedicine",
    name: "Hô hấp và miễn dịch dị ứng lâm sàng",
    component: RespiratoryMedicine,
  },
  {
    path: "/specialist/TestLaboratory",
    name: "Xét nghiệm",
    component: TestLaboratory,
  },
  {
    path: "/specialist/Surgery",
    name: "Khoa ngoại",
    component: Surgery,
  },

  {
    path: "/introduce",
    name: "Giới thiệu",
    component: Introduce,
  },
  {
    path: "/contact",
    name: "Liên hệ",
    component: Contact,
  },
  {
    path: "/procedure",
    name: "Quy trình",
    component: Procedure,
  },
  {
    path: "/service",
    name: "Dịch vụ",
    component: Service,
  },
  
];

export default routes;
