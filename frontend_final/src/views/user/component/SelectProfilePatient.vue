<template>
  <v-app>
    <v-row justify="center" style="background-color: #F3FCFF;">
      <v-card width="80%" height="700px" elevation="6">
        <v-row justify="center">
          <v-card width="60%" height="120px" class="profile-title my-10">
            <v-row justify="center" class="mt-7">
              <strong class="text-title">CHỌN HỒ SƠ BỆNH NHÂN</strong></v-row
            >
          </v-card>
        </v-row>

        <v-row justify="center" style="margin-top: 200px">
          <v-alert
            width="60%"
            height="320"
            style="border: 3px solid #ffdfcc !important;"
          >
            <v-row justify="center" class="text-profile-name"
              ><strong>Nguyễn Duy Thanh</strong></v-row
            >
            <v-row class="text-profile-info" justify="center">
              <v-col cols="6">
                <v-row>Ngày sinh: 03/03/2000</v-row>
                <v-row>Số điện thoại: </v-row>
                <v-row>Giới tính</v-row>
                <v-row>CMND:</v-row>
                <v-row>Nghề nghiệp:</v-row>
                <v-row>Địa chỉ:</v-row>
                <v-row>Dân tộc:</v-row>
                <v-row>Người giám hộ:</v-row>
              </v-col>
            </v-row>
            <v-row class="mt-10 ml-3"
              ><v-btn elevation="1" color="#E6F0F5" class="mr-3"
                ><strong style="color: #3C89AB;">Chỉnh sửa</strong></v-btn
              >
              <v-btn elevation="1" color="#FCE6D7" class="mr-3"
                ><strong style="color: #FC544B;">Xóa</strong></v-btn
              >
              <v-btn elevation="1" color="#eceefb"
                ><strong style="color: #6e6fd9;">Chọn</strong></v-btn
              ></v-row
            >
          </v-alert>
        </v-row>
        <!-- <v-col cols="12">
          <v-row justify="center">
            <v-card width="60%" height="120px" class="profile-title">
              <v-row justify="center" class="mt-7">
                <strong class="text-title">CHỌN HỒ SƠ BỆNH NHÂN</strong></v-row
              >
            </v-card>
          </v-row>
        </v-col> -->
        <v-row justify="center"
          ><v-btn color="#3C89AB" outlined width="60%"
            ><strong style="color: #3C89AB;">Chọn hồ sơ khác</strong></v-btn
          ></v-row
        >
      </v-card>
    </v-row>
  </v-app>
</template>

<style scoped>
.profile-title {
  position: absolute;
  background: linear-gradient(
    180deg,
    #ffdfcc -11.34%,
    rgba(255, 223, 204, 0.33) 88.66%
  );
  border: 1px solid #ffdfcc;
}
.text-title {
  font-family: "Quicksand";
  font-style: normal;
  font-weight: 700;
  font-size: 30px;
  line-height: 38px;

  color: #046792;
}

.text-profile-name {
  font-family: "Quicksand";
  font-style: normal;
  font-weight: 700;
  font-size: 25px;
  color: #046792;
}

.text-profile-info {
  font-family: "Quicksand";
  font-style: normal;
  font-weight: 500;
  font-size: 16px;
  line-height: 18px;

  color: #5b626b;
}
</style>
