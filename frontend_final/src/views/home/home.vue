<template>
<v-app>
  <div class="content">
    <v-footer color="#DCF5FF" height="auto" :padless="padless">
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
        </v-row></v-col>
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

    
    <v-footer color="#DCF5FF" height="500px" :padless="padless">
      <v-col cols="12">
        <v-row justify="center">
          <strong
            class="font-weight-medium"
            style="color: #046792; font-size: 20px"
            >Điều trị chuyên nghiệp</strong
          >
        </v-row></v-col
      >
      <v-col cols="12">
        <v-row justify="center"
          ><strong
            class="font-weight-medium"
            style="color: #046792; font-size: 64px"
            >Hỗ Trợ Khám Bệnh Online</strong
          ></v-row
        ></v-col
      >
      <v-col cols="12">
        <v-row justify="center"
          ><strong
            class="font-weight-medium"
            style="color: #68a4be; font-size: 16px"
            >Đặt lịch 24/7 - Tư vấn và khám bệnh nhanh chóng - Hoàn toàn miễn
            phí - Theo dõi lịch hẹn</strong
          ></v-row
        ></v-col
      >
      <v-col cols="12">
        <v-row justify="center">
          <v-card color="white" width="721px" class="rounded-pill"
            ><input
              type="text"
              v-model="search"
              placeholder="..."
              style="width: 80%"
              class="ml-5"
            />
            <v-btn color="#3685A8" rounded class="my-2 white--text">
              <v-icon small>fas fa-search</v-icon>
              Tìm kiếm
            </v-btn>
          </v-card></v-row
        ></v-col
      ><v-col cols="8">
        <v-row justify="center"
          ><strong
            class="font-weight-medium"
            style="color: #046792; font-size: 16px"
            >Bạn đang tìm kiếm điều gì?</strong
          ></v-row
        >
      </v-col>
      <v-col cols="12">
        <v-row justify="center"
          ><v-card
            class="mx-2"
            v-for="(item, index) in card_link"
            :key="index"
            width="184px"
            height="84px"
            hover="true"
            style="
              background: linear-gradient(180deg, #ffdfcc 0%, #ffeee6 100%);
            "
          >
            <strong
              class="font-weight-medium ml-2"
              style="color: #046792; font-size: 16px"
              >{{ item.head }}</strong
            ><br /><strong
              class="font-weight-regular ml-2"
              style="color: #046792; font-size: 16px"
              >{{ item.body }}</strong
            >
          </v-card></v-row
        >
      </v-col>
    </v-footer>
    <v-footer color="white" height="auto" :padless="padless">
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
        <v-carousel height="200" class="mb-8">
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

  <v-footer>
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
  </v-footer>
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


