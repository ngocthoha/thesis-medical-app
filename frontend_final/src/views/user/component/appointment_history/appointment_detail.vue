<template>
  <div>
    <!-- header -->
    <v-card height="132" class="px-8 d-flex flex-row pt-5" elevation="0">
      <div>
        <p class="font-weight-bold mb-3" style="font-size: 24px">
          Thông tin đặt khám
        </p>
        <p style="color: #667085" class="text-body-1">
          Chi tiết thông tin đặt khám của bạn
        </p>
      </div>
      <v-spacer></v-spacer>
      <v-btn
        class="btn-not-transform text-body-1 font-weight-medium"
        elevation="0"
        outlined
        color="#667085"
        text
        @click="close_view_appointment_detail"
        >Hủy xem</v-btn
      >
      <v-btn
        v-if="appointment.status == 'CANCEL'"
        class="ml-3 white--text btn-not-transform text-body-2 font-weight-medium"
        elevation="0"
        color="#537DA5"
        @click="
          $router.push(
            '/doctor-appointment-detail/?id=' + appointment.doctor.id
          )
        "
        >Đặt khám lại</v-btn
      >
      <v-btn
        v-if="
          appointment.status == 'PENDING' || appointment.status == 'PROCESS'
        "
        class="ml-3 white--text btn-not-transform text-body-2 font-weight-medium"
        elevation="0"
        color="red"
        @click="dialog = true"
        >Hủy đặt khám</v-btn
      >
      <v-dialog v-model="dialog" width="408px">
        <v-card height="192px" class="d-flex flex-column">
          <div class="d-flex flex-column mt-8 ml-8">
            <p style="font-size: 24px" class="font-weight-bold">
              Xác nhận hủy đặt khám
            </p>
            <p style="color: #667085">
              Bạn có chắc chắn muốn hủy lịch hẹn này không?
            </p>
          </div>
          <div class="d-flex flex-row justify-space-between ml-8 mr-8">
            <v-btn
              class="btn-not-transform font-weight-medium"
              width="160px"
              outlined
              color="#667085"
              text
              @click="dialog = false"
              >Huỷ bỏ</v-btn
            >
            <v-btn
              class="btn-not-transform white--text font-weight-medium"
              width="160px"
              color="#F04438"
              elevation="0"
              :loading="loadingCancel"
              @click="cancelAppointment()"
              >Hủy đặt khám</v-btn
            >
          </div>
        </v-card>
      </v-dialog>
    </v-card>
    <v-divider
      style="border-color: rgba(16, 24, 40, 0.03) !important"
    ></v-divider>
    <!-- medical record dialog -->
    <v-dialog v-model="medical_record_dialog" width="800">
      <v-card
        tile
        class="pa-3 d-flex flex-row justify-space-between align-center"
        elevation="0"
        color="#EEF2F6"
        ><p
          class="ma-0 font-weight-bold"
          style="color: #537DA5; font-size:20px"
        >
          Kết quả khám
        </p>
        <v-btn icon @click="medical_record_dialog = false"
          ><v-icon color="#537DA5"> mdi-close</v-icon>
        </v-btn>
      </v-card>
      <v-card class="d-flex flex-column pa-5" tile>
        <p class="font-weight-bold">Chuẩn đoán</p>
        <v-card outlined class="mb-6 pa-3">
          <p style="color: #667085" class="ma-0">Bệnh thống phong</p>
        </v-card>
        <p class="font-weight-bold">Ghi chú</p>
        <v-card outlined class="mb-6 pa-3">
          <p style="color: #667085" class="ma-0">Tránh ăn thức ăn đạm</p>
        </v-card>
        <p class="font-weight-bold">Đơn thuốc</p>
        <v-card class="d-flex flex-column mb-6" outlined>
          <!-- medicine list -->
          <v-data-table
            :headers="prescriptions_header"
            :items="prescriptions"
            checkbox-color="#3C5E7E"
            hide-default-footer
            no-data-text="Đơn thuốc trống"
          >
          </v-data-table>
        </v-card>
        <p class="font-weight-bold">Kết quả cận lâm sàn</p>
      </v-card>
    </v-dialog>
    <!-- body -->
    <v-card class="mt-8 px-8" elevation="0">
      <!-- appointment info -->
      <v-card
        class="mb-5 d-flex flex-column justify-center pa-6"
        style="border: #D0D5DD solid 1px"
        elevation="0"
      >
        <div class="d-flex">
          <div style="width: 100%">
            <v-card class="d-flex flex-row mb-3" elevation="0">
              <!-- label -->
              <v-card width="45%" elevation="0"
                ><p
                  class="ma-0 text-body-1 font-weight-regular"
                  style="color: #667085"
                >
                  Người khám
                </p></v-card
              >
              <p class="ma-0 font-weight-medium">
                {{ this.appointment.profile.lastName }}
                {{ this.appointment.profile.firstName }}
              </p>
            </v-card>

            <v-card class="d-flex flex-row mb-3 align-center" elevation="0">
              <!-- label -->
              <v-card width="45%" elevation="0">
                <p
                  class="ma-0 text-body-1 font-weight-regular"
                  style="color: #667085"
                >
                  Tình trạng
                </p></v-card
              >
              <p
                class="ma-0 font-weight-medium text-body-2"
                style="color: #667085"
                v-if="appointment.status == 'PENDING'"
              >
                <v-chip text-color="white" color="orange" small>
                  Chờ duyệt
                </v-chip>
              </p>
              <p
                v-else-if="appointment.status == 'CANCEL'"
                class="ma-0 font-weight-medium text-body-2"
                style="color: red"
              >
                <v-chip small color="red" text-color="white">
                  Đã hủy
                </v-chip>
              </p>
              <p
                class="ma-0 font-weight-medium text-body-2"
                style="color: #667085"
                v-else-if="appointment.status === 'PROCESS'"
              >
                <v-chip color="primary" small>
                  Chưa tiến hành
                </v-chip>
              </p>
              <div
                class="d-flex flex-row align-center"
                v-else-if="appointment.status === 'COMPLETE'"
              >
                <p
                  class="ma-0 font-weight-medium text-body-1"
                  style="color: #12B76A"
                >
                  <v-chip small color="green" text-color="white">
                    Đã hoàn tất
                  </v-chip>
                </p>
                <v-tooltip bottom>
                  <template v-slot:activator="{ on, attrs }">
                    <v-icon
                      color="#537DA5"
                      class="ml-3"
                      dark
                      v-bind="attrs"
                      v-on="on"
                      @click="medical_record_dialog = true"
                    >
                      mdi-account-eye
                    </v-icon>
                  </template>
                  <span>Bấm để xem kết quả khám</span>
                </v-tooltip>
              </div>
            </v-card>
            <v-card class="d-flex flex-row mb-3" elevation="0">
              <!-- label -->
              <v-card width="45%" elevation="0">
                <p
                  class="ma-0 text-body-1 font-weight-regular"
                  style="color: #667085"
                >
                  Ngày đặt khám
                </p></v-card
              >
              <v-card elevation="0">
                <p class="ma-0 font-weight-medium">
                  {{ formatDate(appointment.createdAt) | empty }}
                </p>
              </v-card>
            </v-card>
            <v-card class="d-flex flex-row mb-3" elevation="0">
              <!-- label -->
              <v-card width="45%" elevation="0">
                <p
                  class="ma-0 text-body-1 font-weight-regular"
                  style="color: #667085"
                >
                  Triệu chứng
                </p></v-card
              >
              <v-card elevation="0">
                <p class="ma-0 font-weight-medium">
                  {{ this.appointment.symptom | empty }}
                </p>
              </v-card>
            </v-card>

            <v-card class="d-flex flex-row mb-3" elevation="0">
              <!-- label -->
              <v-card width="45%" elevation="0">
                <p
                  class="ma-0 text-body-1 font-weight-regular"
                  style="color: #667085"
                >
                  HÌnh ảnh bổ sung
                </p></v-card
              >
              <v-card elevation="0" class="d-flex flex-row"> </v-card>
            </v-card>
          </div>
          <div style="margin-left: auto;">
            <div class="d-flex flex-column align-center" style="width: 280px">
              <h4>Mã QR code đặt khám</h4>
              <p class="text-center mb-0">
                Cơ sở y tế sẽ quét Mã QR Code để xem thông tin lịch khám của bạn
              </p>
              <v-img
                style="width: 120px; height: 120px"
                :src="appointment.qrcode"
              ></v-img>

              <v-chip text-color="black" color="green" outlined>
                Mã đặt khám:
                <span style="color: green" class="ml-2">{{
                  appointment.code
                }}</span>
              </v-chip>
            </div>
          </div>
        </div>
      </v-card>
      <!-- service info -->
      <v-card
        class="mb-5 d-flex flex-column justify-center pa-6"
        style="border: #D0D5DD solid 1px"
        elevation="0"
      >
        <div v-if="appointment.category == 'DOCTOR'">
          <v-card class="d-flex flex-row mb-3" elevation="0">
            <!-- label -->
            <v-card width="30%" elevation="0"
              ><p
                class="ma-0 text-body-1 font-weight-regular"
                style="color: #667085"
              >
                Bác sĩ
              </p></v-card
            >
            <p class="ma-0 font-weight-medium">
              {{ appointment.doctor.level }}.
              {{ appointment.doctor.name }}
              <v-tooltip bottom>
                <template v-slot:activator="{ on, attrs }">
                  <a
                    v-bind="attrs"
                    v-on="on"
                    target="_blank"
                    style="text-decoration: none;"
                    :href="
                      '/doctor-appointment-detail/?id=' + appointment.doctor.id
                    "
                    ><v-icon small color="primary">mdi-open-in-new</v-icon></a
                  >
                </template>
                <span>Xem bác sĩ</span>
              </v-tooltip>
            </p>
          </v-card>

          <v-card class="d-flex flex-row mb-3" elevation="0">
            <!-- label -->
            <v-card width="30%" elevation="0"
              ><p
                class="ma-0 text-body-1 font-weight-regular"
                style="color: #667085"
              >
                Chuyên khoa
              </p></v-card
            >
            <p class="ma-0 font-weight-medium">
              {{ appointment.doctor.specialty }}
            </p>
          </v-card>
        </div>
        <v-card v-else class="d-flex flex-row mb-3" elevation="0">
          <!-- label -->
          <v-card width="30%" elevation="0"
            ><p
              class="ma-0 text-body-1 font-weight-regular"
              style="color: #667085"
            >
              Dịch vụ
            </p></v-card
          >
          <p class="ma-0 font-weight-medium">
            {{ getServiceName(appointment) }}
          </p>
        </v-card>
        <v-card class="d-flex flex-row mb-3" elevation="0">
          <!-- label -->
          <v-card width="30%" elevation="0"
            ><p
              class="ma-0 text-body-1 font-weight-regular"
              style="color: #667085"
            >
              Thời gian khám
            </p></v-card
          >
          <p class="ma-0 font-weight-medium">
            {{ appointment.time }}, {{ getDate(appointment.date) }}
          </p>
        </v-card>
        <v-card class="d-flex flex-row mb-3" elevation="0">
          <!-- label -->
          <v-card width="30%" elevation="0"
            ><p
              class="ma-0 text-body-1 font-weight-regular"
              style="color: #667085"
            >
              Hình thức
            </p></v-card
          >
          <div
            class="d-flex flex-row"
            v-if="this.appointment.type === 'ONLINE'"
          >
            <p class="ma-0 font-weight-medium mr-3">Trực tuyến</p>
            <v-tooltip bottom>
              <template v-slot:activator="{ on, attrs }">
                <v-icon color="#537DA5" dark v-bind="attrs" v-on="on">
                  mdi-video
                </v-icon>
              </template>
              <span>Bấm để vào đường link khám trực tuyến</span>
            </v-tooltip>
          </div>
          <div class="d-flex flex-row" v-if="appointment.type === 'OFFLINE'">
            <p class="ma-0 font-weight-medium mr-3">Trực tiếp tại viện</p>
          </div>
        </v-card>
        <v-card class="d-flex flex-row mb-3" elevation="0">
          <!-- label -->
          <v-card width="30%" elevation="0"
            ><p
              class="ma-0 text-body-1 font-weight-regular"
              style="color: #667085"
            >
              Tên phòng khám
            </p></v-card
          >
          <p class="ma-0 font-weight-medium">
            {{ appointment.room.name }}
          </p>
        </v-card>
        <v-card class="d-flex flex-row mb-3" elevation="0">
          <!-- label -->
          <v-card width="30%" elevation="0"
            ><p
              class="ma-0 text-body-1 font-weight-regular"
              style="color: #667085"
            >
              Cơ sở y tế
            </p></v-card
          >
          <p class="ma-0 font-weight-medium">
            {{ appointment.doctor.hospital.name }}
            <v-tooltip bottom>
              <template v-slot:activator="{ on, attrs }">
                <a
                  v-bind="attrs"
                  v-on="on"
                  target="_blank"
                  style="text-decoration: none;"
                  :href="
                    '/hospital-appointment-detail/?id=' +
                      appointment.doctor.hospital.id
                  "
                  ><v-icon small color="primary">mdi-open-in-new</v-icon></a
                >
              </template>
              <span>Xem bệnh viện</span>
            </v-tooltip>
          </p>
        </v-card>
        <v-card class="d-flex flex-row" elevation="0">
          <!-- label -->
          <v-card width="30%" elevation="0"
            ><p
              class="ma-0 text-body-1 font-weight-regular"
              style="color: #667085"
            >
              Địa chỉ
            </p></v-card
          >
          <p class="ma-0 font-weight-medium">
            {{ getAddress(appointment.profile) }}
          </p>
        </v-card>
      </v-card>
      <!-- payment -->
      <v-card
        class="mb-5 d-flex flex-column justify-center pa-6"
        style="border: #D0D5DD solid 1px"
        elevation="0"
      >
        <v-card class="d-flex flex-row mb-3" elevation="0">
          <!-- label -->
          <v-card width="30%" elevation="0"
            ><p
              class="ma-0 text-body-1 font-weight-regular"
              style="color: #667085"
            >
              Tổng tiền
            </p></v-card
          >
          <p class="ma-0 font-weight-medium">{{ this.appointment.fee }} đ</p>
        </v-card>
        <v-card class="d-flex flex-row mb-3" elevation="0">
          <!-- label -->
          <v-card width="30%" elevation="0"
            ><p
              class="ma-0 text-body-1 font-weight-regular"
              style="color: #667085"
            >
              Tổng tiền thanh toán
            </p></v-card
          >
          <p style="color: red" class="ma-0 font-weight-medium">
            {{ this.appointment.fee }} đ
          </p>
        </v-card>
        <v-card class="d-flex flex-row mb-3" elevation="0">
          <!-- label -->
          <v-card width="30%" elevation="0"
            ><p
              class="ma-0 text-body-1 font-weight-regular"
              style="color: #667085"
            >
              Phương thức thanh toán
            </p></v-card
          >
          <p class="ma-0 font-weight-medium" v-if="!appointment.paymentType">
            Không xác định
          </p>
          <p class="ma-0 font-weight-medium" v-else>
            {{ getPayment(appointment.paymentType) }}
          </p>
        </v-card>
        <v-card class="d-flex flex-row mb-3" elevation="0">
          <!-- label -->
          <v-card width="30%" elevation="0"
            ><p
              class="ma-0 text-body-1 font-weight-regular"
              style="color: #667085"
            >
              Trạng thái thanh toán
            </p></v-card
          >
          <p
            class="ma-0 font-weight-medium"
            v-if="this.appointment.isPaid == false"
            style="color: red"
          >
            Chưa thanh toán
          </p>
          <p class="ma-0 font-weight-medium" v-else>
            Đã thanh toán
          </p>
        </v-card>
      </v-card>
    </v-card>
  </div>
</template>

<script>
const url = process.env.VUE_APP_ROOT_API;
export default {
  props: {
    appointment: Object
  },
  data() {
    return {
      medical_record_dialog: false,
      dialog: false,
      prescriptions_header: [
        {
          text: "Tên",
          align: "start",
          sortable: false,
          value: "name",
          class: "text-body-2 font-weight-medium"
        },
        {
          text: "Số lượng",
          value: "mount",
          sortable: false,
          class: "text-body-2 font-weight-medium"
        },
        {
          text: "Liều",
          value: "use",
          sortable: false,
          class: "text-body-2 font-weight-medium"
        },
        {
          text: "Đơn vị uống",
          value: "unit",
          sortable: false,
          class: "text-body-2 font-weight-medium"
        }
      ],
      prescriptions: [
        {
          name: "abc",
          mount: 120,
          use: "Sáng: 1, Chiều: 1, Tối: 1",
          unit: "Viên"
        },
        {
          name: "abc",
          mount: 120,
          use: "Sáng: 1, Chiều: 1, Tối: 1",
          unit: "Viên"
        },
        {
          name: "abc",
          mount: 120,
          use: "Sáng: 1, Chiều: 1, Tối: 1",
          unit: "Viên"
        }
      ],
      loadingCancel: false
    };
  },

  methods: {
    async cancelAppointment() {
      this.loadingCancel = true;
      let token = this.$store.getters["auth/access_token"];

      this.axios.defaults.headers.common = {
        Authorization: `Bearer ${token}`
      };
      const params = {
        orderId: this.appointment.orderId,
        status: "CANCEL"
      };
      await this.axios.patch(`${url}/api/appointments`, params);
      this.loadingCancel = false;
      this.dialog = false;
      this.$emit("reloadDetail");
      this.$store.dispatch("snackbar/set_snackbar", {
        text: "Hủy đặt khám thành công",
        type: "success"
      });
    },
    formatDate(date_string) {
      let date = new Date(date_string);
      let day = date.getDate();
      let month = date.getMonth();
      let year = date.getFullYear();
      return `${day} tháng ${month}, ${year}`;
    },
    getPayment(type) {
      const map = {
        MOMO: "Thanh toán bằng Momo",
        ATM: "Thanh toán bằng thẻ ATM",
        VISA: "Thanh toán bằng thẻ VISA",
        DIRECT: "Thanh toán tại CSYT"
      };
      return map[type];
    },
    getAddress(profile) {
      if (profile.address == null) return "";
      return (
        profile.address.address +
        ", " +
        profile.address.ward +
        ", " +
        profile.address.district +
        ", " +
        profile.address.province
      );
    },
    getDate(date) {
      let date_array = date.split("-");
      return `${date_array[2]} tháng ${date_array[1]}, ${date_array[0]}`;
    },
    close_view_appointment_detail() {
      this.$emit("appointmentDetailClose");
    },
    getServiceName(appointment) {
      if (appointment.category == "DOCTOR") {
        if (appointment.type == "ONLINE") {
          return `Tư vấn trực tuyến`;
        }
        return `Khám trực tiếp`;
      }
    }
  }
};
</script>
<style scoped>
.btn-not-transform {
  text-transform: none;
}
</style>
