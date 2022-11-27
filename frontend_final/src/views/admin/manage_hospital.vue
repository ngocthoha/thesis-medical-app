<template>
  <v-card class="d-flex flex-column pa-8">
    <!-- header -->
    <v-divider></v-divider>
    <v-card elevation="0 pa-5">
      <p class="font-weight-bold ma-0" style="font-size: 24px">
        Quản lý bệnh viện
      </p>
    </v-card>
    <v-divider class="mb-5"></v-divider>
    <!-- operation -->
    <v-card class="d-flex flex-row mb-5 justify-space-between" elevation="0">
      <v-card outlined width="30%">
        <v-text-field
          label="Tìm kiếm"
          prepend-inner-icon="mdi-magnify"
          solo
          flat
          hide-details=""
        ></v-text-field>
      </v-card>
      <v-card class="d-flex flex-row" elevation="0">
        <v-btn icon @click="open_add_dialog()" class="mr-3">
          <v-icon>mdi-plus</v-icon>
        </v-btn>
        <v-btn icon @click="open_edit_dialog()" class="mr-3">
          <v-icon>mdi-pencil-outline</v-icon>
        </v-btn>
        <v-btn icon @click="add_dialog = true">
          <v-icon>mdi-filter-variant</v-icon>
        </v-btn>
      </v-card>
    </v-card>
    <!-- table data -->
    <v-card outlined>
      <v-data-table
        :headers="headers"
        :items="desserts"
        :items-per-page="5"
        class="elevation-0"
        show-select
      ></v-data-table>
    </v-card>
    <!-- some dialog here -->
    <!-- add/edit dialog -->
    <v-dialog width="800" v-model="submit_dialog">
      <v-card height="600" class="pa-5 d-flex flex-column">
        <!-- header -->
        <p v-if="is_add_dialog" class="font-weight-bold text-h6">Thêm</p>
        <p v-else class="font-weight-bold text-h6">Chỉnh sửa</p>

        <!-- list field item -->
        <div class="d-flex flex-wrap justify-space-between">
          <!-- field -->
          <div class="d-flex flex-column mb-5">
            <!-- label -->
            <p class="mb-2">Tên Bệnh viện</p>
            <v-card outlined width="350">
              <v-text-field flat solo hide-details=""></v-text-field>
            </v-card>
          </div>
          <!-- field -->
          <div class="d-flex flex-column mb-3">
            <!-- label -->
            <p class="mb-2">Tên Bệnh viện</p>
            <v-card outlined width="350">
              <v-text-field flat solo hide-details=""></v-text-field>
            </v-card>
          </div>
          <div class="d-flex flex-column mb-3">
            <!-- label -->
            <p class="mb-2">Tên Bệnh viện</p>
            <v-card outlined width="350">
              <v-text-field flat solo hide-details=""></v-text-field>
            </v-card>
          </div>
        </div>

        <v-spacer></v-spacer>
        <!-- submit/cancle -->
        <v-card width="100%" class="d-flex flex-row justify-space-between">
          <v-btn
            elevation="0"
            outlined
            color="#D0D5DD"
            @click="submit_dialog = false"
            class="btn"
            ><p class="ma-0 font-weight-bold" style="color:#667085">
              Hủy
            </p></v-btn
          >
          <v-btn
            v-if="is_add_dialog"
            class="btn white--text"
            color="#537DA5"
            elevation="0"
            >Thêm mới</v-btn
          >
          <v-btn v-else class="btn white--text" color="#537DA5" elevation="0"
            >Lưu chỉnh sửa</v-btn
          >
        </v-card>
      </v-card>
    </v-dialog>
  </v-card>
</template>

<script>
export default {
  data() {
    return {
      //show dialog
      submit_dialog: false,
      is_add_dialog: true,
      //table
      headers: [
        {
          text: "Tên",
          align: "start",
          sortable: false,
          value: "name"
        },
        { text: "Địa chỉ", value: "calories" },
        { text: "Fat (g)", value: "fat" },
        { text: "Carbs (g)", value: "carbs" },
        { text: "Protein (g)", value: "protein" },
        { text: "Iron (%)", value: "iron" }
      ],
      desserts: [
        {
          name: "Frozen Yogurt",
          calories: 159,
          fat: 6.0,
          carbs: 24,
          protein: 4.0,
          iron: "1%"
        }
      ]
    };
  },
  methods: {
    open_add_dialog() {
      this.is_add_dialog = true;
      this.submit_dialog = true;
    },

    open_edit_dialog() {
      this.is_add_dialog = false;
      this.submit_dialog = true;
    }
  }
};
</script>

<style scoped>
.btn {
  text-transform: none;
}
</style>
