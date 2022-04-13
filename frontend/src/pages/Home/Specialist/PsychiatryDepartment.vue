<template>
  <div class="content">

    <div class="contentpage">
      <v-breadcrumbs :items="items"></v-breadcrumbs>
      <br>

      <p class="text-center display-1 font-weight-bold blue--text">
TÂM THẦN KINH
      </p>

      <br>

      <p class="text-left title font-weight-bold red--text mb-6">
        I. Giới thiệu chung
      </p>

      <p class="text-justify body-1">
   Ngày nay, các vấn đề về sức khỏe tâm thần đang ngày càng phổ biến và phức tạp, trong đó trầm cảm và rối loạn lo âu chiếm một tỷ lệ lớn hơn cả. Khoa Tâm Thần kinh thuộc Phòng khám Bệnh viện Đại học Y Dược 1 được thành lập nhằm giúp người bệnh được chẩn đoán, điều trị, tư vấn về sức khỏe tâm thần và các rắc rối về tâm lý trong cuộc sống hàng ngày.
      </p>

       <p class="text-justify body-1">Bác sĩ chuyên khoa Tâm thần kinh được đào tạo chuyên sâu về tâm thần và tâm lý có thể điều trị các vấn đề tâm thần bằng cách kê đơn thuốc hoặc kết hợp với các liệu pháp điều trị tâm lý khác, giúp người bệnh hiểu rõ tình trạng của mình và có phương hướng thay đổi hiệu quả.</p>
       

       
     

      <p class="text-left title font-weight-bold red--text mb-6">II. Hoạt động chuyên môn</p>
      <p class="text-left body-1 font-weight-bold mb-3">1. Khám và điều trị các bệnh thuộc chuyên khoa tâm thần:</p>
      <p class="text-justify body-1">
    - Bệnh lý về rối loạn cảm xúc: trầm cảm, hưng cảm, rối loạn cảm xúc lưỡng cực và các rối loạn tâm trạng khác;
      </p>

       <p class="text-justify body-1">
     - Rối loạn lo âu, bao gồm cả rối loạn hoảng sợ, rối loạn ám ảnh cưỡng chế;
      </p>

       <p class="text-justify body-1">
   - Khám và chẩn đoán bệnh lý loạn thần do sử dụng chất: rượu, heroin, amphetamine, metamphetamine.
      </p>

       <p class="text-justify body-1">
  - Sợ hãi hoặc lo lắng dữ dội về một thay đổi nào đó trong cơ thể: tăng cân, đau bụng, rụng tóc…;
      </p>
      
       <p class="text-left body-1 font-weight-bold mb-3">2. Tư vấn tâm lý và bệnh lý: </p>
      <p class="text-justify body-1">
   - Tư vấn tâm lý do gặp cú sốc về tai nạn, xã hội, gia đình hoặc các mối quan hệ;
      </p>

       <p class="text-justify body-1">
   - Tư vấn tâm lý về những cảm xúc: lo lắng, căng thẳng, phiền muộn, ám ảnh sợ hãi…;
      </p>

       <p class="text-justify body-1">
 - Tư vấn các bệnh lý rối loạn cảm xúc, rối loạn lo âu, stress…;
      </p>

       <p class="text-justify body-1">
 - Tư vấn cai nghiện thuốc lá, rượu bia hoặc các chất khác.
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
        text: "Tâm thần khoa",
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
