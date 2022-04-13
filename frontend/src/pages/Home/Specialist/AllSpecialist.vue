<template>
  <div class="content">
    <p class="text-center display-1 font-weight-bold blue--text my-8">
      Tất cả các khoa
    </p>

    <v-row justify="center"
      ><v-card
        class="mx-2"
        v-for="(image_card, index) in card_list_2"
        :key="index"
        width="200px"
        height="134px"
        hover="true"
        style="background-color: #e6f0f5"
        @click="getlink(index)"
      >
        <v-row justify="center" class="mt-2">
          <v-avatar height="48px" width="48px" color="white">
            <img :src="image_card.image" alt="" />
          </v-avatar>
        </v-row>
        <br>
        <v-row justify="center" class="mt-2">
          <strong
            class="font-weight-regular ml-2"
            style="color: #046792; font-size: 16px"
            >{{ image_card.label }}</strong
          >
        </v-row>
      </v-card>
    </v-row>
  </div>
</template>

<script>
export default {
  data: () => ({
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
};
</script>
