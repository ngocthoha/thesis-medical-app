<template>
  <div class="content">

    <div class="contentpage">
      <v-breadcrumbs :items="items"></v-breadcrumbs>
      <br>

      <p class="text-center display-1 font-weight-bold blue--text">
TIM MẠCH
      </p>

      <br>

      <p class="text-left title font-weight-bold red--text mb-6">
        I. Giới thiệu chung
      </p>

      <p class="text-justify body-1">
       Trực tiếp thăm khám và điều trị tại khoa Nội Tim mạch là đội ngũ các Bác sĩ, chuyên gia đầu ngành của  TP. HCM. Ngoài chuyên môn cao, các bác sĩ luôn mang đến cho bệnh nhân cảm giác yên tâm và được tư vấn đầy đủ theo từng bệnh lý.
       Phòng khám  1 được trang bị cơ sở vật chất và các thiết bị hiện đại chuyên về tim mạch giúp phát hiện chính xác và điều trị bệnh nhanh chóng, bao gồm:
      </p>

       <p class="text-justify body-1">- 02 Phòng đo điện tâm đồ (ECG);</p>
       <p class="text-justify body-1">- Phòng chụp cắt lớp vi tính (128 lát cắt);</p>
       <p class="text-justify body-1">- Phòng chụp cộng hưởng từ (MRI) tim;</p>
       <p class="text-justify body-1">- Phòng siêu âm tim, siêu âm Doppler màu động mạch, tĩnh mạch. </p>
       
     

      <p class="text-left title font-weight-bold red--text mb-6">II. Các dịch vụ thực hiện tại chuyên khoa Tim Mạch</p>

      <p class="text-justify body-1">
      - Khám, chẩn đoán và điều trị các bệnh lý tim mạch: Tăng huyết áp, rối lọan lipid máu, xơ vữa động mạch, các bệnh lý mạch vành, bệnh tim thiếu máu cục bộ, bệnh cơ tim, các bệnh lý van tim, suy tim, nhiễm trùng tim, các bệnh lý mạch máu ngoại biên mạn tính,...
      </p>

       <p class="text-justify body-1">
       - Khám và chẩn đoán các triệu chứng do rối loạn nhịp tim gây nên như: ngất, thoáng ngất, cơn chóng mặt không tìm thấy nguyên nhân, cơn hồi hộp trống ngực, cơn khó thở, đau ngực, mệt không rõ nguyên nhân, tai biến mạch não nghi ngờ do cơn rung nhĩ, hay cuồng nhĩ...
      </p>

       <p class="text-justify body-1">
      - Phát hiện các rối loạn tim mạch không triệu chứng ở người bệnh nhồi máu cơ tim, suy tim, bệnh tim phì đại...
      </p>

       <p class="text-justify body-1">
       - Kiểm tra nhịp tim, kiểm tra lưu lượng máu đến cơ tim, chẩn đoán cơn đau tim, kiểm tra những vấn đề bất thường của tim (ví dụ cơ tim dày hơn bình thường).
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
        text: "Tim mạch",
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
