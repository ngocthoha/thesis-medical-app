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
       TIÊU HOÁ GAN MẬT
      </p>

      <br>

      <p class="text-left title font-weight-bold red--text mb-6">
        I. Giới thiệu chung
      </p>

      <p class="text-justify body-1">
        Khoa Tiêu hóa - Gan Mật thuộc Phòng khám  với đội ngũ Bác sĩ giàu kinh nghiệm của   và được trang bị hệ thống nội soi tiêu hóa Olympus hiện đại, máy siêu âm, phòng xét nghiệm H.P qua hơi thở…, giúp các bác sĩ có những chẩn đoán đúng, phát hiện biến đổi do ung thư ở giai đoạn sớm. Từ đó đưa ra liệu pháp điều trị kịp thời, giúp bệnh nhân rút ngắn thời gian, tiết kiệm chi phí và có được kết quả điều trị hiệu quả nhất. 
      </p>
     

      <p class="text-left title font-weight-bold red--text mb-6">Hoạt động chuyên môn</p>

      <p class="text-justify body-1">
       Khoa Nội Tiêu hóa – Gan Mật tiếp nhận tất cả các trường hợp khám ngoại trú, chẩn đoán và điều trị gần hết các bệnh liên quan đến tiêu hóa, gan, mật, cụ thể là:
      </p>

       <p class="text-justify body-1">
       - Điều trị và theo dõi: viêm gan siêu vi B, C, viêm gan do rượu và các nguyên nhân khác, theo các hướng dẫn mới nhất của Hội Gan Mật Hoa Kỳ.
      </p>

       <p class="text-justify body-1">
       - Chẩn đoán và điều trị các bệnh lý ống tiêu hóa từ thực quản, dạ dày, ruột non, đại tràng như: bệnh crohn, viêm loét dạ dày – tá tràng có hoặc không nhiễm HP, viêm loét đại trực tràng, xuất huyết tiêu hóa, viêm thực quản trào ngược, polyp thực quản – dạ dày, polyp đại trực tràng, ung thư đường tiêu hóa…
      </p>

       <p class="text-justify body-1">
       - Hướng dẫn bệnh nhân theo dõi và điều trị bệnh lý phổ biến nhất của hệ tiêu hóa: hội chứng ruột đại tràng kích thích.
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
        text: "Tiêu hóa gan mật",
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
