<template>
<v-app>
  <div class="content">
    <!-- section 1 -->
    <v-card width="100%" class="d-flex flex-row justify-sm-center" height="500px" color="#EEF2F6">
      <v-card color="#EEF2F6"  width="564px" style="margin-top: 108px; margin-left: 102px" elevation="0">
        <div>
          <div class="d-flex flex-column">
            <v-card width="564px" height="96px" color="#EEF2F6" elevation="0"><p class="font-weight-bold" style="font-size:40px; color:#537DA5">BLOUSE CARE | Ứng dụng đặt lịch khám 
online hàng đầu</p></v-card>
        </div>
        <br/>
        <div class="d-flex flex-column">
            <v-card width="564px" height="96px" color="#EEF2F6" elevation="0" class="d-flex flex-column"><p class="font-weight-medium" style="color:#667085">Người tiêu dùng thông thái, hãy khám bệnh thông minh. Bạn là người tiêu dùng thông thái, bạn yêu gia đình hơn chính bản thân mình. Hãy cùng IVIE chăm sóc sức khỏe cho bản thân và gia đình để mỗi ngày bên nhau là một ngày ý nghĩa.</p></v-card>
        </div>
        <div class="d-flex flex-column">
          <v-btn height="48px" width="155px" color="#537DA5" class="white--text text-body-1" style="text-transform: none;">Đặt khám ngay</v-btn>
        </div>
        </div>
      </v-card>
      <v-sheet class="rounded-circle" height="72px" width="72px" color="#BACBDC" style="margin-top: 268px; margin-left: 123px;"></v-sheet>
      <v-card color="#EEF2F6" elevation="0" class="d-flex align-end" style="margin-left: 73px">
         <v-img src="@/assets/img/home/Rectangle39.svg" style="margin-left: 74px; position: absolute" ></v-img>
        <v-img class="d-flex" src="@/assets/img/home/toppng1.svg" style="position: relative">
        </v-img>
      </v-card>
       <v-sheet class="rounded-circle" height="116px" width="116px" color="#BACBDC" style="margin-top: 12px; margin-left: 56px;"></v-sheet>
    </v-card>
    
     <!-- section 2 -->
    <v-layout>
    <v-card class="d-flex flex-column justify-center" width="100%" height="296px">
      <p class="d-flex justify-center font-weight-bold mb-0" style="font-size: 40px">Tra cứu nhanh thông tin sức khỏe</p>
      <!---->
      <v-card elevation="0" class="d-flex justify-center" >
        <v-card elevation="0" class="d-flex align-center mt-8" width="720px" height="76px" outlined >
        <v-card elevation="0" ><p class="ma-0 font-weight-medium text-body-1 ml-3" style="color: #667085">Search Title</p></v-card> 
          <v-spacer />
        <v-divider inset vertical></v-divider>
        <v-menu>
          <template v-slot:activator="{ on, attrs }">
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
        <v-btn color="#537DA5" elevation="0" class="white--text btn font-weight-medium text-body-1 mr-3">Tìm kiếm <v-img class="ml-3" src="@/assets/img/home/search_icon.svg"></v-img></v-btn>
        </v-card>
      </v-card>
      <!---->
      <v-card elevation="0" class="d-flex justify-center">
        <v-card elevation="0" class="d-flex align-center mt-5" width="720px">
          <p class="ma-0 mr-4 font-weight-medium text-body-1">Tìm kiếm phổ biến:</p>
          <v-card v-for="(item,i) in poular_search_list" :key="i" class="py-1 px-2 mr-2 font-weight-medium text-body-2" elevation="0" color="#F9FAFB" dark style="border: 1px solid #D0D5DD; color:#667085">{{item}}</v-card>
        </v-card>
      </v-card>
    </v-card>
    </v-layout>

     <!-- section 3 -->
     <v-layout>
      <v-card class="d-flex flex-column justify-center" width="100%" height="441px" color="#FCFCFD"> 
        <v-card elevation="0" color="#FCFCFD" class="d-flex justify-center" width="564px" height="200px" style="margin-left: 102px">
          <v-card class="d-flex">
            <v-card elevation="0" color="#FCFCFD" width="200px">
            Dịch vụ nổi bật
          </v-card>
          <v-card elevation="0" color="#FCFCFD"  width="200px">
            Dịch vụ nổi bật
          </v-card>
          </v-card>
        </v-card>
      </v-card>
     </v-layout>

     
    <v-footer color="#FFFFFF" height="auto" :padless="padless">
      <v-col cols="12">
        <v-row justify="center" class="mt-8 mb-4"
          ><strong
            class="font-weight-medium"
            style="color: #046792; font-size: 36px"
            >Hãy cùng tìm hiểu về các chuyên khoa mà chúng tôi đang có</strong
          ></v-row
        ></v-col
      >
      <v-col cols="12">
        <v-row justify="center" class="mb-4"
          ><strong
            class="font-weight-regular"
            style="color: #a3a3a3; font-size: 16px"
            >Đội ngũ bác sĩ giàu kinh nghiệm trong chẩn đoán và điều trị
            bệnh</strong
          ></v-row
        ></v-col
      >
      <v-col cols="12">
        <v-carousel height="200" class="mb-8" hide-delimiters show-arrows-on-hover>
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
                        <br />
                        <v-row justify="center" class="mt-2">
                          <strong
                            class="font-weight-bold ml-2"
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
      </v-col>
      <v-row justify="center" class="mt-8 mb-8">
        <v-col cols ="4">
          <v-img
             
              max-height="362"
              max-width="511"
              src="@/assets/img/home/topic1.png"
          ></v-img>
        </v-col>
        <v-col cols ="4" class="my-auto">
         <p class = "text-left display-1 font-weight-bold blue--text">Bác sĩ có chuyên môn cao</p> 
         <p class = "text-justify subtitle-1  ">Phòng khám  quy tụ đội ngũ chuyên gia, bác sĩ, dược sĩ và điều dưỡng có trình độ chuyên môn cao, tay nghề giỏi, luôn tận tâm và chu đáo với người bệnh.</p> 
        <p class = "text-justify subtitle-1 ">Phòng khám luôn đặt người bệnh là trọng tâm, và cam kết mang đến dịch vụ chăm sóc sức khỏe tốt nhất cho quý khách hàng.</p>
        </v-col>
      </v-row>

       <v-row justify="center" class="mt-8 mb-8">
        
        <v-col cols ="4" class="my-auto">
         <p class = "text-left display-1 font-weight-bold blue--text">Khám sức khoẻ tổng quát cá nhân</p> 
         <p class = "text-justify subtitle-1  ">Phòng khám được đầu tư cơ sở vật chất khang trang và trang thiết bị máy móc hiện đại của các thương hiệu hàng đầu thế giới.</p> 
        <p class = "text-justify subtitle-1 ">Đáp ứng nhu cầu khám sức khoẻ toàn diện cho khách hàng ở mọi lứa tuổi, cho các cá nhân và công ty, cũng như chẩn đoán và điều trị ngoại trú các chuyên khoa nêu trên với mức chi phí hợp lý.</p>
        </v-col>
        <v-col cols ="4">
          <v-img
             
              max-height="362"
              max-width="511"
              src="@/assets/img/home/topic2.png"
          ></v-img>
        </v-col>
      </v-row>
    </v-footer>

  <!-- <v-footer>
    <v-row justify="center" class="mt-9 mb-9">
      <v-col cols="2 mr-5" >
        <v-row justify="center "><p class="body-1 font-weight-bold blue--text">Về trang web</p> </v-row>
        <v-row justify="center"><p class="text-justify body-1">Được xây dựng bằng VueJs và tạo ra từ chính công sức của tập thể.</p></v-row>
         
      </v-col>

      <v-col cols="2">
        <v-row justify="center" ><p class="body-1 font-weight-bold blue--text">Ngôn ngữ sử dụng</p> </v-row>
        <v-row justify="center"><p class="text-justify body-1">Vue</p></v-row>
        <v-row justify="center"><p class="text-justify body-1">HTML</p></v-row>
        <v-row justify="center"><p class="text-justify body-1">CSS</p></v-row>
        <v-row justify="center"><p class="text-justify body-1">Js</p></v-row>

      </v-col>
       <v-col cols="2" >
        <v-row justify="center" ><p class="body-1 font-weight-bold blue--text">Đối tượng sử dụng</p> </v-row>
        <v-row justify="center"><p class="text-justify body-1">Bệnh nhân</p></v-row>
        <v-row justify="center"><p class="text-justify body-1">Quản lí</p></v-row>
        <v-row justify="center"><p class="text-justify body-1">Bác sĩ</p></v-row>
         <v-row justify="center"><p class="text-justify body-1">Nhân viên</p></v-row>
        
      </v-col>
       <v-col cols="2" >
        <v-row justify="center " ><p class="body-1 font-weight-bold blue--text">Liên hệ</p> </v-row>
        <v-row justify="center"><p class="text-justify body-1">Email: t3@gmail.com</p></v-row>
        <v-row justify="center"><p class="text-justify body-1">SĐT: 0123456798</p></v-row>
        <v-row justify="center"><p class="text-justify body-1">Địa chỉ: 22 abc Dĩ An Bình Dương</p></v-row>
      </v-col>
    </v-row>
  </v-footer> -->
  </div>
  </v-app>
</template>

<script>


export default {
    
  data: () => ({

    // links: ["Trang chủ", "Giới thiệu", "Quy trình", "Dịch vụ", "Liên hệ"],
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
    card_link: [
      {
        head: "Bác sĩ",
        body: "Đặt lịch hẹn",
      },
      {
        head: "Khám bệnh",
        body: "Với các chuyên bác sĩ",
      },
      {
        head: "Chuyên khoa",
        body: "Các khoa khám hiện có",
      },
      {
        head: "Bài viết",
        body: "Tìm hiểu về bệnh",
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

    poular_search_list:["Khám tai mũi họng","Khám sản phụ khoa","Nội soi tiêu hóa","Siêu âm 2D"]
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
  },
};
</script>

<style scoped>
.btn {
  text-transform: none;
}
</style>
