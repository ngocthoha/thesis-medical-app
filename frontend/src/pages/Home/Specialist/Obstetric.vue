<template>
  <div class="content">

    <div class="contentpage">
      <v-breadcrumbs :items="items"></v-breadcrumbs>
      <br>

      <p class="text-center display-1 font-weight-bold blue--text">
SẢN PHỤ KHOA
      </p>

      <br>

      <p class="text-left title font-weight-bold red--text mb-6">
        I. Giới thiệu chung
      </p>

      <p class="text-justify body-1">
     Chuyên khoá Sản phụ khoa của Phòng khám  là đơn vị thăm khám, tầm soát và điều trị các bệnh lý phụ khoa toàn diện và chuyên sâu, giúp giải tỏa nỗi lo lắng cho phụ nữ về các bệnh phụ khoa, ngăn chặn những hậu quả ảnh hưởng đến sức khỏe sinh sản và sức khỏe giới tính cho phụ nữ.
      </p>

       <p class="text-justify body-1">Đối với Sản khoa phòng khám đầu tư nhiều trang thiết bị hiện đại cùng đội ngũ bác sĩ tận tâm, dày dạn kinh nghiệm giúp kiểm tra và theo dõi tình trạng sức khỏe của sản phụ và thai nhi từ những ngày đầu cho tới cuối thai kỳ; hỗ trợ và đem lại sự an tâm cho mẹ và bé “vượt cạn” an toàn.</p>
       

       
     

      <p class="text-left title font-weight-bold red--text mb-6">II. Các dịch vụ của chuyên khoa Sản phụ khoa</p>
      <p class="text-left body-1 font-weight-bold mb-3">1. Sản khoa</p>
      <p class="text-justify body-1">
     Các dịch vụ được thực hiện tại Sản khoa của Phòng khám  bao gồm:
      </p>

       <p class="text-justify body-1">
     - Chẩn đoán và điều trị các trường hợp thai bệnh lý ở 3 tháng đầu thai kỳ như: dọa sảy thai, nhiễm độc thai nghén, thai lưu, thai ngoài tử cung, thai trứng…
      </p>

       <p class="text-justify body-1">
    - Tư vấn tiền sản và thực hiện các kỹ thuật khảo sát để phát hiện các bất thường trong giai đoạn sớm của thai kỳ, đặc biệt là các bệnh lý di truyền.
      </p>

       <p class="text-justify body-1">
     - Theo dõi sức khỏe mẹ, sức khỏe và sự phát triển của bé trong suốt thai kỳ. 
      </p>
      
       <p class="text-left body-1 font-weight-bold mb-3">2. Phụ khoa </p>
      <p class="text-justify body-1">
     Các dịch vụ khám Phụ khoa được thực hiện tại Phòng khám  bao gồm:
      </p>

       <p class="text-justify body-1">
    - Viêm nhiễm phụ khoa thường gặp (viêm âm hộ - âm đạo, viêm tử cung phần phụ…).
      </p>

       <p class="text-justify body-1">
  - Chẩn đoán và điều trị các khối u phụ khoa lành tính (u xơ tử cung, u nang buồng trứng, lạc nội mạc tử cung…).
      </p>

       <p class="text-justify body-1">
 - Tầm soát, chẩn đoán và điều trị các loại ung thư phụ khoa (ung thư cổ tử cung, ung thư nội mạc tử cung, ung thư buồng trứng…).
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


    
  </div>
</template>

<script>
export default {
  data: () => ({
   
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
        text: "Sản phụ khoa",
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
  
    getlink(index) {
      this.$router.push({ name: this.card_list_2[index].name });
    },
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
