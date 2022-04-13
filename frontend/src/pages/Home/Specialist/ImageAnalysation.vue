<template>
  <div class="content">

    <v-footer color="#DCF5FF" height="auto" :padless="padless" class="mb-10">
      <v-col cols="3">icon</v-col>
      <v-col cols="5">
        <v-row justify="center" no-gutters>
          <v-btn
            v-for="link in links"
            :key="link.label"
            color="#046792"
            text
            rounded
            class="my-2"
            @click="getpage(link.name)"
          >
            {{ link.label }}
          </v-btn>
        </v-row></v-col
      >
      <v-col cols="4">
        <v-row justify="end" no-gutters>
          <v-btn
            color="#046792"
            rounded
            class="my-2 white--text"
            @click="login"
          >
            Đăng nhập
          </v-btn>
          <v-btn
            color="#046792"
            rounded
            class="my-2 white--text"
            @click="signup"
          >
            Đăng ký
          </v-btn>
        </v-row></v-col
      >
    </v-footer>
    <div class="contentpage">
      <v-breadcrumbs :items="items"></v-breadcrumbs>
      <br>

      <p class="text-center display-1 font-weight-bold blue--text">
        Chẩn đoán hình ảnh
      </p>

      <br>

      <p class="text-left title font-weight-bold red--text mb-6">
        I. Giới thiệu chung
      </p>

      <p class="text-justify body-1">
        Chẩn đoán hình ảnh là một trong những một chuyên khoa không thể thiếu
        trong dịch vụ khám, chữa bệnh. Ra đời ngay từ những ngày đầu thành lập,
        Khoa Chẩn đoán hình ảnh của phòng khám Bệnh viện Đại học Y Dược 1 quy tụ
        đội ngũ bác sĩ, kỹ thuật viên giàu kinh nghiệm và được đầu tư trang
        thiết bị hiện tại từ các thương hiệu nổi tiếng như GE, Fuji, Hologic,
        Hitachi… Chúng tôi luôn đảm bảo cung cấp các dịch vụ thăm khám chẩn đoán
        hình ảnh tốt nhất, đạt hiệu quả cao trong chẩn đoán và điều trị…
      </p>
      <p class="text-justify body-1">
        Khoa chẩn đoán hình ảnh của Phòng khám gồm:
      </p>
      <p class="text-justify body-1">
        - 01 phòng chụp cộng hưởng từ – MRI 1.5 Tesla
      </p>
      <p class="text-justify body-1">
        - 01 phòng chụp cắt lớp vi tính – CT Scanner 128
      </p>
      <p class="text-justify body-1">- 02 phòng chụp X-quang kỹ thuật số</p>
      <p class="text-justify body-1">
        - 01 phòng đo loãng xương bằng phương pháp DEXA (DXA)
      </p>
      <p class="text-justify body-1">- 05 phòng siêu âm</p>

      <p class="text-left title font-weight-bold red--text mb-6">II. Dịch vụ</p>

      <p class="text-justify body-1">
        Khoa Chẩn đoán hình ảnh thực hiện các kỹ thuật tạo ảnh y học để chẩn
        đoán bệnh và theo dõi hiệu quả điều trị. Các kỹ thuật được thực hiện tại
        khoa Chẩn đoán hình ảnh của Phòng khám Bệnh viện Đại học Y Dược 1 bao
        gồm:
      </p>
      <p class="text-left body-1 font-weight-bold mb-3">1. Siêu Âm</p>
      <p class="text-justify body-1">
        Siêu âm là phương pháp cận lâm sàng cho thấy hình ảnh y học về cấu trúc
        bên trong cơ thể người. Đây là kỹ thuật phổ biến, hiệu quả và an toàn,
        được áp dụng để chẩn đoán bệnh nội khoa và sản khoa.
      </p>
      <p class="text-justify body-1">- Siêu âm bụng tổng quát</p>
      <p class="text-justify body-1">- Siêu âm tuyến giáp</p>
      <p class="text-justify body-1">- Siêu âm gân, khớp và khảo sát mô mềm</p>
      <p class="text-justify body-1">
        - Siêu âm Doppler động mạch cảnh và mạch máu chi dưới
      </p>

      <p class="text-left body-1 font-weight-bold mb-3">
        2. Chụp X-quang kỹ thuật số
      </p>
      <p class="text-justify body-1">
        Chụp X-quang là phương pháp được sử dụng phổ biến trong chẩn đoán bệnh
        tật, đặc biệt là các bệnh lý về xương khớp.
      </p>
      <p class="text-justify body-1">
        - Chụp X-quang ngực, xương khớp, cột sống, xoang…
      </p>

      <p class="text-left body-1 font-weight-bold mb-3">
        3. Chụp cắt lớp điện toán đa lát cắt – CT Scanner 128
      </p>
      <p class="text-justify body-1">
        Chụp CT hay còn gọi là chụp cắt lớp vi tính sử dụng chùm tia X. Dựa vào
        các lát cắt, chụp CT có thể cho ra hình ảnh y học sắc nét hơn so với
        X-quang, tránh bỏ sót những tổn thương bên trong cơ thể.
      </p>
      <p class="text-justify body-1">
        - Kỹ thuật chụp CT thường quy: khảo sát các bệnh lý về vùng bụng – chậu,
        vùng ngực, sọ não, xoang, cột sống, chi trên, chi dưới, mạch máu.
      </p>
      <p class="text-justify body-1">
        - Chụp CT có tiêm thuốc cản quang để chẩn đoán và đánh giá chuyên sâu.
      </p>
      <p class="text-left body-1 font-weight-bold mb-3">
        4. Chụp cộng hưởng từ MRI
      </p>
      <p class="text-justify body-1">
        Kỹ thuật chụp MRI cho ra hình ảnh y học có độ phân giải cao, có giá trị
        cao trong chẩn đoán, đánh giá được các tổn thương khuất mà các phương
        pháp khác không thực hiện được.
      </p>
      <p class="text-justify body-1">
        MRI được sử dụng để kiểm tra tình trạng sọ não; cột sống các đoạn: cổ,
        ngực, thắt lưng và toàn bộ cột sống; bụng; vùng chậu; các loại mạch máu;
        vú; xương khớp: chụp toàn bộ các khớp, xương và mô mềm chi trên, chi
        dưới.
      </p>


<br>

      <p class="text-center display-1 font-weight-bold blue--text">
      Các chuyên khoa khác
    </p>

    <br>
    <br>

  

    <v-carousel height="200">
      <template v-for="(item, index) in card_list_2">
        <v-carousel-item
          v-if="(index + 1) % columns === 1 || columns === 1"
          :key="index"
        >
          <v-row justify="center">
            <template v-for="(n, i) in columns">
              <template v-if="+index + i < card_list_2.length">
                <v-col :key="i" justify="center" align="center" cols = 12/columns>
                  <v-card
                    class="mx-2"
                    :key="index"
                    width="200px"
                    height="134px"
                    hover="true"
                    style="background-color: #e6f0f5"
                    @click="getlink(+index + i)"
                  >
                    <v-row justify="center" class="mt-3">
                      <v-avatar height="48px" width="48px" color="white" class="mt-3">
                        <img :src="card_list_2[+index + i].image" alt="" />
                      </v-avatar>
                    </v-row>
                    <br>
                    <v-row justify="center" class="mt-2">
                      <strong
                        class="font-weight-regular ml-2"
                        style="color: #046792; font-size: 16px"
                        >{{ card_list_2[+index + i].label }}</strong
                      >
                    </v-row>
                  </v-card>
                </v-col>
              </template>
            </template>
          </v-row>
        </v-carousel-item>
      </template>
    </v-carousel>


    </div>


    <v-footer class="mt-15">
      <v-row justify="center" class="mt-9 mb-9">
        <v-col cols="2 mr-5">
          <v-row justify="center "
            ><p class="body-1 font-weight-bold blue--text">Về trang web</p>
          </v-row>
          <v-row justify="center"
            ><p class="text-justify body-1">
              Được xây dựng bằng VueJs và tạo ra từ chính công sức của tập thể.
            </p></v-row
          >
        </v-col>

        <v-col cols="2">
          <v-row justify="center"
            ><p class="body-1 font-weight-bold blue--text">Ngôn ngữ sử dụng</p>
          </v-row>
          <v-row justify="center"><p class="text-justify body-1">Vue</p></v-row>
          <v-row justify="center"
            ><p class="text-justify body-1">HTML</p></v-row
          >
          <v-row justify="center"><p class="text-justify body-1">CSS</p></v-row>
          <v-row justify="center"><p class="text-justify body-1">Js</p></v-row>
        </v-col>
        <v-col cols="2">
          <v-row justify="center"
            ><p class="body-1 font-weight-bold blue--text">Đối tượng sử dụng</p>
          </v-row>
          <v-row justify="center"
            ><p class="text-justify body-1">Bệnh nhân</p></v-row
          >
          <v-row justify="center"
            ><p class="text-justify body-1">Quản lí</p></v-row
          >
          <v-row justify="center"
            ><p class="text-justify body-1">Bác sĩ</p></v-row
          >
          <v-row justify="center"
            ><p class="text-justify body-1">Nhân viên</p></v-row
          >
        </v-col>
        <v-col cols="2">
          <v-row justify="center "
            ><p class="body-1 font-weight-bold blue--text">Liên hệ</p>
          </v-row>
          <v-row justify="center"
            ><p class="text-justify body-1">Email: t3@gmail.com</p></v-row
          >
          <v-row justify="center"
            ><p class="text-justify body-1">SĐT: 0123456798</p></v-row
          >
          <v-row justify="center"
            ><p class="text-justify body-1">
              Địa chỉ: 22 abc Dĩ An Bình Dương
            </p></v-row
          >
        </v-col>
      </v-row>
    </v-footer>
    
  </div>
</template>

<script>
export default {
  data: () => ({
    links:[
      {
        label: "Trang chủ",
        name: "home"
      },
      {
        label: "Giới thiệu",
        name: "Giới thiệu",
      },
        {
        label: "Quy trình",
        name: "Quy trình",
      },
      {
        label: "Dịch vụ",
        name: "Dịch vụ",
      },
        {
        label: "Liên hệ",
        name: "Liên hệ",
      },
],
   
    items: [
      {
        text: "Home",
        disabled: false,
        href: "../",
      },
      {
        text: "Chuyên khoa",
        disabled: false,
        href: "./",
      },
      {
        text: "Chẩn đoán hình ảnh",
        disabled: true,
      },
    ],

 
   card_list_2: [
      {
        image: require("@/assets/img/home/home_icon1.png"),
        label: "Chẩn đoán hình ảnh",
        name: "Chẩn đoán hình ảnh",
      },
      {
        image: require("@/assets/img/home/home_icon2.png"),
        label: "Cơ - Xương - Khớp",
       name: "Cơ - Xương - Khớp",
      },
      {
        image: require("@/assets/img/home/home_icon3.png"),
        label: "Nhi khoa",
          name: "Nhi khoa",
      },
      {
        image: require("@/assets/img/home/home_icon4.png"),
        label: "Sản - Phụ Khoa",
         name: "Sản - Phụ khoa",
      },
      {
        image: require("@/assets/img/home/home_icon5.png"),
        label: "Tai - Mũi - Họng",
        name: "Tai - Mũi - Họng",
      },
      {
        image: require("@/assets/img/home/home_icon6.png"),
        label: "Tim mạch",
        name: "Tim mạch",
      },
      {
        image: require("@/assets/img/home/home_icon7.png"),
        label: "Hô hấp",
        name: "Hô hấp và miễn dịch dị ứng lâm sàng",
      },
      {
        image: require("@/assets/img/home/home_icon8.png"),
        label: "Nội soi",
        name: "Nội soi",
      },
      {
        image: require("@/assets/img/home/home_icon9.png"),
        label: "Tiêu hóa gan mật",
        name: "Tiêu hóa gan mật",
      },
      {
        image: require("@/assets/img/home/home_icon10.png"),
        label: "Xét nghiệm",
        name: "Xét nghiệm",
      },
      {
        image: require("@/assets/img/home/home_icon11.png"),
        label: "Khoa ngoại",
        name: "Khoa ngoại",
      },
      {
        image: require("@/assets/img/home/home_icon12.png"),
        label: "Khoa tâm thần",
        name: "Tâm thần kinh",
      },
    ],
  }),
 methods: {
    login() {
      this.$router.push({ name: "Đăng nhập" });
    },
    signup() {
      this.$router.push({ name: "Đăng ký" });
    },
    getlink(index){
   
      this.$router.push({ name: this.card_list_2[index].name });
    },
    getpage(name){
       this.$router.push({ name: name });
    }
},
  computed: {
    columns() {
      if (this.$vuetify.breakpoint.xl) {
        return 6;
      }

      if (this.$vuetify.breakpoint.lg) {
        return 4;
      }

      if (this.$vuetify.breakpoint.md) {
        return 3;
      }

      return 1;
    },
  },
};
</script>

<style scoped>
.contentpage {
  width: 80%;
  margin-left: auto;
  margin-right: auto;
}
</style>
