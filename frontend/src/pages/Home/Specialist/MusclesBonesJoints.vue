<template>
  <div class="content">

    <div class="contentpage">
      <v-breadcrumbs :items="items"></v-breadcrumbs>
      <br>

      <p class="text-center display-1 font-weight-bold blue--text">
CƠ – XƯƠNG – KHỚP
      </p>

      <br>

      <p class="text-left title font-weight-bold red--text mb-6">
        I. Giới thiệu chung
      </p>

      <p class="text-justify body-1">
      Các bệnh Cơ – Xương – Khớp là một nhóm bệnh thường gặp nhất trong mọi nhóm bệnh, có nguyên nhân và cơ chế bệnh sinh phức tạp, diễn tiến kéo dài liên quan đến nhiều bệnh lý nội ngoại khoa.
      </p>

       <p class="text-justify body-1">Các bệnh Cơ – Xương – Khớp rất phổ biến trên thế giới và cũng đang có xu hướng gia tăng nhanh ở Việt Nam.</p>
       <p class="text-justify body-1">Khoa Cơ – Xương – Khớp thuộc Phòng khám  với đội ngũ bác sĩ giàu kinh nghiệm đã và đang làm việc tại các bệnh viện lớn như (Bệnh viện Đại học Y Dược TP.HCM, Chợ Rẫy…). Khoa Cơ – Xương – Khớp của phòng khám quản lý, theo dõi và điều trị hiệu quả các bệnh lý về xương khớp, giúp người bệnh giảm bớt đau đớn và nhanh chóng hòa nhịp với cuộc sống hàng ngày.</p>

       
     

      <p class="text-left title font-weight-bold red--text mb-6">II. Hoạt động chuyên môn</p>

      <p class="text-justify body-1">
      Khoa Cơ – Xương – Khớp thuộc Phòng khám  hiện khám và điều trị các bệnh lý gồm:
      </p>

       <p class="text-justify body-1">
     - Các bệnh khớp thoái hóa: Thoái hóa khớp gối, thoái hóa cột sống, thoái hóa khớp háng, thoái hóa đa khớp, hoại tử chỏm xương đùi…
      </p>

       <p class="text-justify body-1">
      - Bệnh lý phần mềm và rối loạn ngoài khớp: Viêm gân cơ chóp xoay, viên gân gấp ngón tay (ngón tay cò súng), viêm mỏm trâm quay, viêm lồi cầu cánh tay, viêm cân gan chân, hội chứng ống cổ tay, thoát vị đĩa đệm…
      </p>

       <p class="text-justify body-1">
     - Các bệnh khớp tự miễn hoặc bệnh tổ chức liên kết: Viêm khớp dạng thấp, lupus, viêm cột sống dính khớp, xơ cứng bì, viêm đa cơ, bệnh still, viêm khớp vảy nến…
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
        text: "Cơ xương khớp",
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
