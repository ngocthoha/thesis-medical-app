<template>
  <div class="content">

    <div class="contentpage">
      <v-breadcrumbs :items="items"></v-breadcrumbs>
      <br>

      <p class="text-center display-1 font-weight-bold blue--text">
XÉT NGHIỆM
      </p>

      <br>

      <p class="text-left title font-weight-bold red--text mb-6">
        I. Giới thiệu chung
      </p>

      <p class="text-justify body-1">
    Phục vụ các dịch vụ xét nghiệm và tư vấn theo yêu cầu, các hợp đồng khám sức khỏe ngoại viện cho các cơ quan, xí nghiệp…
      </p>

       <p class="text-justify body-1">Nội kiểm tra chất lượng thường xuyên và tham gia các chương trình Ngoại kiểm tra chất lượng quốc tế: Sinh Hóa, Miễn dịch, Huyết học, Đông máu, Nước tiểu, Định nhóm máu…
</p>
       

       
     

      <p class="text-left title font-weight-bold red--text mb-6">Thực hiện các xét nghiệm:</p>
     
      <p class="text-justify body-1">
    Tất cả các chỉ số sinh hóa trong máu; xét nghiệm sinh hóa, tế bào nước tiểu, các dịch chất; xét nghiệm điện di, khí máu động mạch…Các xét nghiệm huyết học – truyền máu, cung cấp máu và các chế phẩm máu… Xét nghiệm đông cầm máu, ký sinh trùng sốt rét…Tất cả các xét nghiệm miễn dịch: huyết thanh chẩn đoán vi sinh -Virus, xét nghiệm về hormon, chất chuyển hóa, tim mạch, chất chỉ thị ung thư, thuốc gây nghiện, thuốc điều trị…
      </p>

       <p class="text-justify body-1">
    Các xét nghiệm sinh học phân tử : Khoa xét nghiệm là một trong những cơ sở đầu tiên trong cả nước ứng dụng hệ thống chẩn đoán sinh học phân tử hiện đại và đạt chuẩn quốc tế. Khoa thực hiện xét nghiệm chẩn đoán các tác nhân vi sinh vật gây bệnh thường gặp: HBV CAP T48, HCV CAP T48, HBV-DNA, HBV genotype, HBV đột biến kháng Lamivudine, HCV-RNA, HCV genotype, PCR lao, HPV genotype, …
      </p>

      <p class="text-left title font-weight-bold red--text mb-6">Cơ sở vật chất – Trang thiết bị</p>
<p class="text-justify body-1">Khoa được trang bị đầy đủ các hệ thống trang thiết bị xét nghiệm hiện đại về sinh hóa, miễn dịch, nước tiểu, huyết học, đông máu, truyền máu, sinh học phân tử…</p>
<p class="text-justify body-1">Toàn bộ qui trình hoạt động xét nghiệm đã được hoàn toàn tự động hóa, vi tính hóa: Nối mạng toàn bộ từ thu ngân – tiếp nhận – phòng xét nghiệm – phòng khám – lầu trại. Kết quả xét nghiệm được trả cho bệnh nhân và lưu trữ qua mạng bệnh viện.</p>
      

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
        text: "Xét nghiệm",
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
