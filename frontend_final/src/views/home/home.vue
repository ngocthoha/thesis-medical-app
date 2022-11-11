<template>
  <v-app>
    <div>
      <!-- section 1 -->
      <v-card
        width="100%"
        class="d-flex flex-column flex-lg-row justify-space-around align-center"
        min-height="500px"
        color="#EEF2F6"
      >
        <v-card
          color="#EEF2F6"
          max-width="564px"
          elevation="0"
          class="d-flex justify-sm-center"
        >
          <div class="d-flex flex-column pa-12">
            <v-card
              max-width="564px"
              min-height="96px"
              color="#EEF2F6"
              elevation="0"
              ><p
                class="font-weight-bold ma-0 d-flex justify-lg-start justify-center"
                :style="{ color: '#537DA5', fontSize: section1_title + 'px' }"
              >
                BLOUSE CARE | Ứng dụng
              </p>
              <p
                class="font-weight-bold d-flex justify-lg-start justify-center"
                :style="{ color: '#537DA5', fontSize: section1_title + 'px' }"
              >
                đặt lịch khám online hàng đầu
              </p>
            </v-card>
            <div class="d-flex flex-column">
              <v-card
                max-width="564px"
                min-height="96px"
                color="#EEF2F6"
                elevation="0"
                class="d-flex flex-column"
                ><p
                  class="font-weight-medium text-center text-lg-start"
                  style="color: #667085"
                >
                  Người tiêu dùng thông thái, hãy khám bệnh thông minh. Bạn là
                  người tiêu dùng thông thái, bạn yêu gia đình hơn chính bản
                  thân mình. Hãy cùng BLOUSE CARE chăm sóc sức khỏe cho bản thân
                  và gia đình để mỗi ngày bên nhau là một ngày ý nghĩa.
                </p></v-card
              >
            </div>

            <!-- fast book appointment -->
            <div class="d-flex justify-center justify-lg-start">
              <v-dialog v-model="fast_book_dialog" width="640px">
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                    height="48px"
                    width="155px"
                    color="#537DA5"
                    class="white--text text-body-1"
                    style="text-transform: none"
                    v-on="on"
                    v-bind="attrs"
                    >Đặt khám ngay</v-btn
                  >
                </template>
                <v-card class="d-flex flex-column">
                  <v-card
                    class="d-flex justify-end mt-10"
                    elevation="0"
                    width="586px"
                  >
                    <v-icon color="#101828" @click="fast_book_dialog = false"
                      >mdi-close</v-icon
                    >
                  </v-card>
                  <v-card class="d-flex flex-column" elevation="0" width="100%">
                    <div class="d-flex justify-center">
                      <v-img
                        height="107px"
                        width="177px"
                        contain
                        src="@/assets/img/home/fast_book.svg"
                      ></v-img>
                    </div>
                  </v-card>
                  <p
                    class="d-flex justify-center font-weight-bold"
                    style="font-size: 32px"
                  >
                    Đặt khám nhanh
                  </p>
                  <p class="d-flex justify-center">
                    Vui lòng để lại thông tin, chúng tôi sẽ liên hệ lại với bạn
                    để tư vấn.
                  </p>
                  <!-- input -->
                  <v-card
                    class="d-flex justify-center"
                    height="328px"
                    elevation="0"
                  >
                    <v-card width="592px" elevation="0">
                      <div class="d-flex flex-column">
                        <div class="d-flex flex-row">
                          <div class="d-flex flex-column">
                            <label for="" class="font-weight-bold text-body-2"
                              >Họ và tên</label
                            >
                            <v-card
                              width="288px"
                              height="44px"
                              class="d-flex mb-5"
                              elevation="0"
                              outlined
                            >
                              <v-text-field
                                placeholder="VD: Nguyen Xuan H"
                                solo
                                flat
                                dense
                                class="text-body-2"
                              ></v-text-field
                            ></v-card>
                          </div>
                          <div class="d-flex flex-column ml-4">
                            <label for="" class="font-weight-bold text-body-2"
                              >Số điện thoại</label
                            >
                            <v-card
                              width="288px"
                              height="44px"
                              class="d-flex mb-5"
                              elevation="0"
                              outlined
                            >
                              <v-text-field
                                placeholder="VD: 012345689"
                                solo
                                flat
                                dense
                                class="text-body-2"
                              ></v-text-field
                            ></v-card>
                          </div>
                        </div>
                        <div class="d-flex flex-column">
                          <label for="" class="font-weight-bold text-body-2"
                            >Nhu cầu khám</label
                          >
                          <v-card
                            width="592px"
                            height="128px"
                            class="d-flex mb-5"
                            elevation="0"
                            outlined
                          >
                            <v-textarea
                              solo
                              placeholder="Vui lòng nhập mô tả về nhu cầu khám"
                              class="text-body-2"
                              dense
                              flat
                              height="120px"
                            ></v-textarea
                          ></v-card>
                        </div>
                        <div class="d-flex flex-row">
                          <v-card class="d-flex align-center" elevation="0">
                            <v-img
                              sizes="24px"
                              src="@/assets/img/home/phone_icon.svg"
                              contain
                            ></v-img>
                            <p class="ma-0 ml-3 font-weight-medium text-body-2">
                              Liên hệ Hotline:
                            </p>
                            <p
                              class="ma-0 ml-2 font-weight-medium text-body-2"
                              style="color: #537da5"
                            >
                              190017473
                            </p>
                          </v-card>

                          <v-card class="ml-auto"
                            ><v-btn
                              class="d-flex align-center btn"
                              color="#537DA5"
                              ><v-img
                                src="@/assets/img/home/send_mail_icon.svg"
                                sizes="20px"
                                class="mr-3"
                              ></v-img>
                              <p class="ma-0 white--text">Gửi yêu cầu</p></v-btn
                            ></v-card
                          >
                        </div>
                      </div>
                    </v-card>
                  </v-card>
                </v-card>
              </v-dialog>
            </div>
          </div>
        </v-card>
        <!-- <v-card height="500px">a</v-card> -->
        <v-card
          class="d-flex flex-row align-end"
          :height="$vuetify.breakpoint.mdAndDown ? '250px' : '500'"
          elevation="0"
          color="#EEF2F6"
        >
          <v-card
            elevation="0"
            color="#EEF2F6"
            :width="$vuetify.breakpoint.mdAndDown ? '300px' : '600px'"
          >
            <v-img src="@/assets/img/home/section1-doctor.svg"></v-img>
          </v-card>
        </v-card>
      </v-card>

      <!-- section 2 -->
      <v-layout>
        <v-card
          class="d-flex flex-column justify-center"
          width="100%"
          height="296px"
        >
          <p
            class="d-flex justify-center font-weight-bold mb-0"
            style="font-size: 40px"
          >
            Tra cứu nhanh thông tin sức khỏe
          </p>
          <!---->
          <v-card elevation="0" class="d-flex justify-center">
            <v-card
              elevation="0"
              class="d-flex align-center mt-8"
              width="60%"
              height="76px"
              outlined
            >
              <v-menu bottom :offset-y="true" v-model="show_search_menu">
                <template v-slot:activator="{}">
                  <v-card
                    class="d-flex flex-row align-center"
                    width="100%"
                    elevation="0"
                  >
                    <v-text-field
                      full-width
                      solo
                      flat
                      placeholder="Tìm kiếm"
                      hide-details=""
                    ></v-text-field>

                    <v-divider class="mx-2" inset vertical></v-divider>
                    <v-menu bottom :offset-y="true">
                      <template v-slot:activator="{ attrs, on }">
                        <v-btn
                          color="#667085"
                          elevation="0"
                          class="font-weight-medium text-body-1 btn"
                          text
                          v-bind="attrs"
                          v-on="on"
                          width="135px"
                          height="44px"
                        >
                          Địa điểm <v-icon right>mdi-chevron-down</v-icon>
                        </v-btn>
                      </template>
                    </v-menu>
                    <v-btn
                      color="#537DA5"
                      elevation="0"
                      class="white--text btn font-weight-medium text-body-1 mr-3"
                      @click="show_search_menu = true"
                      >Tìm kiếm
                      <v-img
                        class="ml-3"
                        src="@/assets/img/home/search_icon.svg"
                      ></v-img
                    ></v-btn>
                  </v-card>
                </template>
                <!-- search result -->
                <v-card class="d-flex flex-column pa-3" width="100%">
                  <!-- hospital -->

                  <v-card class="d-flex flex-column" elevation="0" width="100%">
                    <p class="font-weight-bold">Cơ sở y tế</p>
                    <!-- <p class="font-weight-bold">Cơ sở y tế</p> -->
                    <div class="d-flex justify-center">
                      <v-card width="1000" class="d-flex flex-wrap">
                        <v-card
                          v-for="n in 4"
                          :key="n"
                          width="500"
                          height="150"
                          class="d-flex flex-row pa-3 align-center"
                          elevation="0"
                        >
                          <v-card>
                            <v-img
                              height="80"
                              width="80"
                              src="@/assets/img/home/hospital_avt.png"
                            ></v-img>
                          </v-card>
                          <div class="ml-2">
                            <p class="ma-0">
                              Bệnh viện đại học y dược
                            </p>
                            <p class="ma-0">
                              08 Đ. Nguyễn Hữu Cảnh, Phường 22, Bình Thạnh,
                              Thành phố Hồ Chí Minh
                            </p>
                          </div>
                        </v-card>
                      </v-card>
                    </div>
                  </v-card>
                  <!-- <v-card class="d-flex flex-row" elevation="0">
                    <v-card
                      class="d-flex flex-column"
                      width="50%"
                      elevation="0"
                    >
                      <p class="font-weight-bold">Bác sĩ</p>

                      <v-card
                        v-for="n in 4"
                        :key="n"
                        class="pa-3 mb-3"
                        width="100%"
                        elevation="0"
                      >
                        <v-img
                          height="80"
                          width="80"
                          src="@/assets/img/home/hospital_avt.png"
                        ></v-img>
                      </v-card>
                    </v-card>
                    <v-card
                      class="d-flex flex-column pr-8"
                      width="50%"
                      elevation="0"
                    >
                      <p class="font-weight-bold">Dịch vụ</p>
                      <v-card
                        v-for="n in 4"
                        :key="n"
                        class="pa-3 mb-3"
                        width="100%"
                        elevation="0"
                      >
                        <v-img
                          height="80"
                          width="80"
                          src="@/assets/img/home/hospital_avt.png"
                        ></v-img>
                      </v-card>
                    </v-card>
                  </v-card> -->
                </v-card>
              </v-menu>
            </v-card>
          </v-card>
          <!---->
          <v-card elevation="0" class="d-flex justify-center">
            <v-card
              elevation="0"
              class="d-flex align-center mt-5"
              width="720px"
            >
              <p class="ma-0 mr-4 font-weight-medium text-body-1">
                Tìm kiếm phổ biến:
              </p>
              <v-card
                v-for="(item, i) in poular_search_list"
                :key="i"
                class="py-1 px-2 mr-2 font-weight-medium text-body-2"
                elevation="0"
                color="#F9FAFB"
                dark
                style="border: 1px solid #d0d5dd; color: #667085"
                >{{ item }}</v-card
              >
            </v-card>
          </v-card>
        </v-card>
      </v-layout>

      <!-- section 3 -->
      <v-layout>
        <v-card
          class="d-flex flex-column"
          width="100%"
          height="441px"
          color="#FCFCFD"
          elevation="0"
        >
          <v-card>
            <v-card class="d-flex justify-center" color="#FCFCFD" elevation="0">
              <v-card
                class="d-flex justify-space-between"
                width="65%"
                color="#FCFCFD"
                elevation="0"
              >
                <p class="font-weight-bold" :style="{ 'font-size': 24 + 'px' }">
                  Dịch vụ nổi bật
                </p>
                <v-btn
                  class="btn font-weight-medium text-body-1"
                  elevation="0"
                  color="#FCFCFD"
                  :style="{ color: '#667085' }"
                  >Xem tất cả</v-btn
                >
              </v-card>
            </v-card>
          </v-card>
          <v-card elevation="0" color="#FCFCFD">
            <v-card class="d-flex justify-center" elevation="0" color="#FCFCFD">
              <div class="d-flex flex-row">
                <v-card
                  class="mr-8"
                  v-for="(item, i) in service_list"
                  :key="i"
                  width="320px"
                  height="290px"
                >
                  <div class="d-flex flex-column mx-3 mt-3">
                    <v-img :src="item.img" width="296px" height="145px"></v-img>
                    <p class="font-weight-medium text-body-2 mt-3">
                      {{ item.title }}
                    </p>
                    <p
                      class="font-weight-medium text-body-2"
                      style="color: #667085"
                    >
                      {{ item.place }}
                    </p>
                    <p
                      class="font-weight-bold text-body-2"
                      style="color: #537da5"
                    >
                      {{ item.price }}
                    </p>
                  </div>
                </v-card>
              </div>
            </v-card>
          </v-card>
        </v-card>
      </v-layout>

      <!-- section 4 -->
      <v-layout>
        <v-card
          height="336px"
          class="d-flex flex-column"
          width="100%"
          elevation="0"
        >
          <p
            class="d-flex justify-center mt-16 text-body-1 font-weight-bold"
            style="color: #537da5"
          >
            Dịch vụ
          </p>
          <p
            class="d-flex justify-center font-weight-bold mb-0"
            style="font-size: 40px"
          >
            Trải nghiệm khám bệnh hiện đại
          </p>
          <p
            class="d-flex justify-center font-weight-bold"
            style="font-size: 40px"
          >
            cùng BLOUSE CARE
          </p>
          <!-- <p class="d-flex justify-center text-body-1  font-weight-medium">
            Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet
            sint.
          </p> -->
        </v-card>
      </v-layout>

      <!-- section 5 -->
      <v-layout>
        <v-card
          class="d-flex justify-center"
          width="100%"
          height="312px"
          elevation="0"
        >
          <v-card
            class="d-flex flex-column mr-8"
            v-for="(item, index) in intro_list"
            :key="index"
            height="228px"
            width="280px"
            elevation="0"
          >
            <div class="d-flex justify-center">
              <v-card
                width="56px"
                height="56px"
                class="d-flex align-center"
                color="#FCFCFD"
                elevation="0"
              >
                <v-img
                  :src="item.img"
                  width="33px"
                  height="22px"
                  contain
                ></v-img>
              </v-card>
            </div>
            <v-card class="d-flex flex-column" height="140px" elevation="0">
              <p
                class="d-flex justify-center text-center text-h6 font-weight-bold"
              >
                {{ item.title }}
              </p>
              <p
                class="d-flex justify-center text-center font-weight-regular"
                style="color: #667085"
              >
                {{ item.content }}
              </p>
            </v-card>
            <v-btn
              elevation="0"
              color="#537DA5"
              text
              class="btn font-weight-medium text-body-1"
              >Khám phá ngay</v-btn
            >
          </v-card>
        </v-card>
      </v-layout>

      <!-- section 6 -->
      <v-layout>
        <v-card
          height="600px"
          width="100%"
          elevation="0"
          color="#FCFCFD"
          class="d-flex flex-column"
        >
          <!-- Container -->
          <v-card elevation="0" color="#FCFCFD" class="d-flex flex-column">
            <p
              class="d-flex justify-center mt-16 font-weight-bold text-body-1"
              style="color: #537da5"
            >
              Chuyên khoa
            </p>
            <p
              class="d-flex justify-center font-weight-bold"
              style="font-size: 40px"
            >
              Một số chuyên khoa nổi bật
            </p>
            <div class="d-flex justify-center">
              <v-card elevation="0" color="#FCFCFD" width="717px">
                <p
                  class="d-flex justify-center font-weight-normal text-body-1 text-center"
                  style="color: #667085"
                >
                  Amet minim mollit non deserunt ullamco est sit aliqua dolor do
                  amet sint. Velit officia consequat duis enim velit mollit.
                  Exercitation veniam consequat sunt nostrud amet.
                </p>
              </v-card>
            </div>
          </v-card>
          <!-- List Member -->
          <v-card
            elevation="0"
            color="#FCFCFD"
            class="d-flex justify-center mt-16"
          >
            <v-card
              width="183px"
              height="148px"
              class="d-flex justify-center align-center mr-6"
              v-for="(item, i) in special_list"
              :key="i"
              elevation="0"
              link
            >
              <div class="d-flex flex-column">
                <div class="d-flex justify-center">
                  <v-card
                    width="56px"
                    height="56px"
                    class="d-flex align-center"
                    color="#EEF2F6"
                    elevation="0"
                    rounded="circle"
                  >
                    <v-img
                      :src="item.img"
                      width="33px"
                      height="22px"
                      contain
                    ></v-img>
                  </v-card>
                </div>
                <p class="d-flex justify-center font-weight-medium mt-5">
                  {{ item.title }}
                </p>
              </div>
            </v-card>
          </v-card>
          <!-- Button view all -->
          <div class="d-flex justify-center mt-6">
            <v-btn
              width="122px"
              height="48px"
              class="white--text btn text-body-1 rounded-lg"
              elevation="0"
              color="#537DA5"
              >Xem tất cả
            </v-btn>
          </div>
        </v-card>
      </v-layout>
    </div>
  </v-app>
</template>

<script>
export default {
  data: () => ({
    show_search_menu: false,
    offset: true,
    items: [
      { title: "Click Me" },
      { title: "Click Me" },
      { title: "Click Me" },
      { title: "Click Me 2" }
    ],
    fast_book_dialog: false,
    card_list_2: [
      {
        image: require("@/assets/img/home/home_icon1.png"),
        label: "Chẩn đoán hình ảnh",
        name: "Chẩn đoán hình ảnh"
      },
      {
        image: require("@/assets/img/home/home_icon2.png"),
        label: "Cơ - Xương - Khớp",
        name: "Cơ - Xương - Khớp"
      },
      {
        image: require("@/assets/img/home/home_icon3.png"),
        label: "Nhi khoa",
        name: "Nhi khoa"
      },
      {
        image: require("@/assets/img/home/home_icon4.png"),
        label: "Sản - Phụ Khoa",
        name: "Sản - Phụ khoa"
      },
      {
        image: require("@/assets/img/home/home_icon5.png"),
        label: "Tai - Mũi - Họng",
        name: "Tai - Mũi - Họng"
      },
      {
        image: require("@/assets/img/home/home_icon6.png"),
        label: "Tim mạch",
        name: "Tim mạch"
      },
      {
        image: require("@/assets/img/home/home_icon7.png"),
        label: "Hô hấp",
        name: "Hô hấp và miễn dịch dị ứng lâm sàng"
      },
      {
        image: require("@/assets/img/home/home_icon8.png"),
        label: "Nội soi",
        name: "Nội soi"
      },
      {
        image: require("@/assets/img/home/home_icon9.png"),
        label: "Tiêu hóa gan mật",
        name: "Tiêu hóa gan mật"
      },
      {
        image: require("@/assets/img/home/home_icon10.png"),
        label: "Xét nghiệm",
        name: "Xét nghiệm"
      },
      {
        image: require("@/assets/img/home/home_icon11.png"),
        label: "Khoa ngoại",
        name: "Khoa ngoại"
      },
      {
        image: require("@/assets/img/home/home_icon12.png"),
        label: "Khoa tâm thần",
        name: "Tâm thần kinh"
      }
    ],

    poular_search_list: [
      "Khám tai mũi họng",
      "Khám sản phụ khoa",
      "Nội soi tiêu hóa",
      "Siêu âm 2D"
    ],
    service_list: [
      {
        img: require("@/assets/img/home/service1.svg"),
        title: "Gói Khám Gan nhiễm mỡ - Tổ hợp y tế MEDIPLUS",
        place: "Tổ Hợp Phòng Khám MEDIPLUS",
        price: "2.480.000 đ"
      },
      {
        img: require("@/assets/img/home/service2.svg"),
        title:
          "Gói khám tổng quát hệ tiêu hoá, gan mật tiêu chuẩn - Tổ hợp y tế MEDIPLUS",
        place: "Tổ Hợp Phòng Khám MEDIPLUS",
        price: "2.220.000 đ"
      },
      {
        img: require("@/assets/img/home/service3.svg"),
        title: "Gói Xét nghiệm tổng quát cho Nam - TTXN LABHOUSE",
        place: "Tổ Hợp Phòng Khám MEDIPLUS",
        price: "1.335.000 đ"
      },
      {
        img: require("@/assets/img/home/service4.svg"),
        title:
          "Test nhanh Cúm A,B cho người Nước ngoài tại Bệnh viện Đa khoa Bảo Sơn",
        place: "Tổ Hợp Phòng Khám MEDIPLUS",
        price: "2.480.000 đ"
      }
    ],
    intro_list: [
      {
        img: require("@/assets/img/home/service_intro1.svg"),
        title: "Đặt khám từ xa",
        content:
          "Bác sĩ của Blouse Care luôn sẵn sàng tư vấn và chăm sóc sức khỏe cho bạn mọi lúc mọi nơi qua video & chat."
      },
      {
        img: require("@/assets/img/home/service_intro2.svg"),
        title: "Đội ngũ chuyên gia và cơ sở y tế hàng đầu",
        content:
          "Dễ dàng kết nối với các bác sĩ ưu tú, tận tâm, có chuyên môn cao."
      },
      {
        img: require("@/assets/img/home/service_intro3.svg"),
        title: "Tiếp đón ưu tiên với nhiều quyền lợi đặc biệt",
        content:
          "Đặt khám hẹn trước tại các bệnh viện trung ương, phòng khám hàng đầu."
      },
      {
        img: require("@/assets/img/home/service_intro4.svg"),
        title: "Giá khám bằng với giá tại cơ sở y tế",
        content:
          "Không những vậy còn giúp bạn tiết kiệm thời gian và các chi phí khác."
      }
    ],
    special_list: [
      {
        img: require("@/assets/img/home/ThanKinh.svg"),
        title: "Thần kinh"
      },
      {
        img: require("@/assets/img/home/Nhi.svg"),
        title: "Nhi khoa"
      },
      {
        img: require("@/assets/img/home/CoXuongKhop.svg"),
        title: "Cơ - Xương - Khớp"
      },
      {
        img: require("@/assets/img/home/San.svg"),
        title: "Sản - Phụ khoa"
      },
      {
        img: require("@/assets/img/home/TaiMuiHong.svg"),
        title: "Tai - Mũi - Họng"
      },
      {
        img: require("@/assets/img/home/Tim.svg"),
        title: "Tim mạch"
      }
    ]
  }),

  methods: {
    login() {
      this.$router.push({ name: "Đăng nhập" });
    },
    signup() {
      this.$router.push({ name: "Đăng ký" });
    },
    getlink(index) {
      this.$router.push({ name: this.card_list_2[index].name });
    },
    getpage(name) {
      this.$router.push({ name: name });
    }
  },
  computed: {
    columns() {
      if (this.$vuetify.breakpoint.xl) {
        return 6;
      }

      if (this.$vuetify.breakpoint.lg) {
        return 6;
      }

      if (this.$vuetify.breakpoint.md) {
        return 4;
      }
      if (this.$vuetify.breakpoint.sm) {
        return 2;
      }

      return 1;
    },
    section1_title() {
      if (this.$vuetify.breakpoint.mdAndDown) {
        return 24;
      }
      return 40;
    }
  }
};
</script>

<style scoped>
.btn {
  text-transform: none;
}
</style>
