<template>
  <v-app class="d-flex flex-column">
    <v-row justify="center" style="background-color: #f5f7fa;">
      <v-card width="80%" height="700px" elevation="6">
        <v-row justify="center" class="mt-10"
          ><strong class="mr-5 mt-2" style="color: #046792"
            >Tìm nhanh chuyên khoa</strong
          >
          <v-text-field
            v-model="search"
            class="shrink"
            solo
            dense
          ></v-text-field
        ></v-row>
        <v-row justify="center" class="mt-5">
          <v-list
            width="600px"
            height="300px"
            style="overflow-y:auto;"
            elevation="1"
            :class="'toolbar-custom'"
          >
            <v-list-item-group v-model="model" active-class="blue--text">
              <v-list-item v-for="(item, i) in filteredList" :key="i">
                <!-- <v-list-item-icon>
                  <v-icon v-text="item.icon"></v-icon>
                </v-list-item-icon> -->
                <v-list-item-content>
                  <v-list-item-title v-text="item.name"></v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </v-list-item-group> </v-list
        ></v-row>
        <v-row justify="center" class="mt-5">
          <v-col cols="5">
            <v-row class="mt-5 ml-2">
              <v-btn elevation="1" color="#E6F0F5" class="mr-3" @click="BackTo"
                ><strong style="color: #3C89AB;">Quay lại</strong></v-btn
              >
              <v-btn elevation="1" color="#eceefb" @click="NextTo"
                ><strong style="color: #6e6fd9;">Chọn</strong></v-btn
              >
            </v-row>
          </v-col>
        </v-row>
      </v-card>
    </v-row>
  </v-app>
</template>

<script>
export default {
  computed: {
    filteredList: function() {
      return this.opd_list.filter(OPD =>
        OPD.name.toLowerCase().includes(this.search.toLowerCase())
      );
    }
  },
  data: () => ({
    search: "",
    opd_list: [
      {
        name: "Chẩn đoán hình ảnh"
      },
      {
        name: "Cơ - Xương - Khớp"
      },
      {
        name: "Nhi khoa"
      },
      {
        name: "Sản - Phụ khoa"
      },
      {
        name: "Tai - Mũi - Họng"
      },
      {
        name: "Tim mạch"
      },
      {
        name: "Hô hấp và miễn dịch dị ứng lâm sàng"
      },
      {
        name: "Nội soi"
      },
      {
        name: "Tiêu hóa gan mật"
      },
      {
        name: "Xét nghiệm"
      },
      {
        name: "Khoa ngoại"
      },
      {
        name: "Tâm thần kinh"
      }
    ],
    model: 1
  }),
  methods: {
    BackTo() {
      this.$emit("invisible", false);
    },
    NextTo() {
      this.$emit("invisible", true);
    }
  }
};
</script>

<style scoped>
.toolbar-custom::-webkit-scrollbar {
  width: 15px;
}

/* .toolbar-custom::-webkit-scrollbar-track {
  background: white;
  border-left: 1px solid #dadada;
} */

.toolbar-custom::-webkit-scrollbar-thumb {
  background: #b0b0b0;
  border: solid 3px #e6e6e6;
  border-radius: 7px;
}

.toolbar-custom::-webkit-scrollbar-thumb:hover {
  background: #2196f3;
}

.toolbar-custom::-webkit-scrollbar {
  width: 15px;
}

.dark::-webkit-scrollbar-track {
  background: #202020;
  border-left: 1px solid #2c2c2c;
}

/* .dark::-webkit-scrollbar-thumb {
  background: #3e3e3e;
  border: solid 3px #202020;
  border-radius: 7px;
} */

.dark::-webkit-scrollbar-thumb:hover {
  background: white;
}
</style>
