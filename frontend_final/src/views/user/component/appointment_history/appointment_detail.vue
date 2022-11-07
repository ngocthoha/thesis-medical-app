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
        class="mr-6 btn-not-transform text-body-1 font-weight-medium"
        elevation="0"
        outlined
        color="#667085"
        text
        >Hủy xem</v-btn
      >
    </v-card>
    <v-divider
      style="border-color: rgba(16, 24, 40, 0.03) !important"
    ></v-divider>
    <!-- body -->
    <v-card class="mt-8 px-8" elevation="0">
      <!-- appointment info -->
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
              Người khám
            </p></v-card
          >
          <p class="ma-0 font-weight-medium">Nguyễn Duy Thanh</p>
        </v-card>
        <v-card class="d-flex flex-row mb-3" elevation="0">
          <!-- label -->
          <v-card width="30%" elevation="0">
            <p
              class="ma-0 text-body-1 font-weight-regular"
              style="color: #667085"
            >
              Tình trạng
            </p></v-card
          >
          <p
            class="ma-0 font-weight-medium text-body-1 d-flex align-center"
            style="color: #667085"
          >
            Chưa tiến hành
          </p>

          <!-- <p
            class="ma-0 font-weight-medium text-body-1"
            style="color: #12B76A"
            v-if="n == 2"
          >
            Đã hoàn tất
          </p>
          <p
            class="ma-0 font-weight-medium text-body-1"
            style="color: red"
            v-if="n == 3"
          >
            Đã hủy
          </p> -->
        </v-card>
        <v-card class="d-flex flex-row mb-3" elevation="0">
          <!-- label -->
          <v-card width="30%" elevation="0">
            <p
              class="ma-0 text-body-1 font-weight-regular"
              style="color: #667085"
            >
              Triệu chứng
            </p></v-card
          >
          <v-card width="70%" elevation="0">
            <p class="ma-0 font-weight-medium">
              Tôi bị đau bụng
            </p>
          </v-card>
        </v-card>

        <v-card class="d-flex flex-row" elevation="0">
          <!-- label -->
          <v-card width="30%" elevation="0">
            <p
              class="ma-0 text-body-1 font-weight-regular"
              style="color: #667085"
            >
              HÌnh ảnh bổ sung
            </p></v-card
          >
          <v-card width="70%" elevation="0" class="d-flex flex-row"> </v-card>
        </v-card>
      </v-card>
      <!-- service info -->
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
              Bác sĩ
            </p></v-card
          >
          <p class="ma-0 font-weight-medium">Nguyễn Xuân Hồng</p>
        </v-card>
        <v-card class="d-flex flex-row mb-3" elevation="0">
          <!-- label -->
          <v-card width="30%" elevation="0"
            ><p
              class="ma-0 text-body-1 font-weight-regular"
              style="color: #667085"
            >
              Dịch vụ
            </p></v-card
          >
          <p class="ma-0 font-weight-medium">Khám theo yêu cầu</p>
        </v-card>
        <v-card class="d-flex flex-row mb-3" elevation="0">
          <!-- label -->
          <v-card width="30%" elevation="0"
            ><p
              class="ma-0 text-body-1 font-weight-regular"
              style="color: #667085"
            >
              Thời gian
            </p></v-card
          >
          <p class="ma-0 font-weight-medium">11:00 - 12:00, 12/07/2022</p>
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
          <p class="ma-0 font-weight-medium mr-3">Trực tuyến</p>
          <v-tooltip bottom>
            <template v-slot:activator="{ on, attrs }">
              <v-icon color="#537DA5" dark v-bind="attrs" v-on="on">
                mdi-video
              </v-icon>
            </template>
            <span>Bấm để vào đường link khám trực tuyến</span>
          </v-tooltip>
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
          <p class="ma-0 font-weight-medium">H3</p>
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
          <p class="ma-0 font-weight-medium">Bệnh viện Hữu Nghị Việt Đức</p>
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
            Số 18 Phủ Doãn, Hàng Bông, Hoàn Kiếm, Thành phố Hà Nội
          </p>
        </v-card>
      </v-card>
      <!-- payment -->
      <v-card
        class="mb-5 d-flex flex-column justify-center pa-6"
        style="border: #D0D5DD solid 1px"
        elevation="0"
      >
        <v-card class="d-flex flex-row" elevation="0">
          <!-- label -->
          <v-card width="30%" elevation="0"
            ><p
              class="ma-0 text-body-1 font-weight-regular"
              style="color: #667085"
            >
              Tổng tiền
            </p></v-card
          >
          <p class="ma-0 font-weight-medium">
            100.000VND
          </p>
        </v-card>
        <v-card class="d-flex flex-row" elevation="0">
          <!-- label -->
          <v-card width="30%" elevation="0"
            ><p
              class="ma-0 text-body-1 font-weight-regular"
              style="color: #667085"
            >
              Trạng thái thanh toán
            </p></v-card
          >
          <p class="ma-0 font-weight-medium">
            Chưa thanh toán
          </p>
        </v-card>
      </v-card>
    </v-card>
  </div>
</template>
<style scoped>
.btn-not-transform {
  text-transform: none;
}
</style>
