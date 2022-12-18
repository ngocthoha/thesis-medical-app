<template>
  <v-card class="d-flex flex-column pa-8">
    <!-- header -->
    <v-divider></v-divider>
    <v-card elevation="0 pa-5">
      <p class="font-weight-bold ma-0" style="font-size: 24px">
        Quản lý lịch của bác sĩ
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
        <v-btn icon class="mr-3" @click="remove_dialog = true">
          <v-icon>mdi-trash-can-outline</v-icon>
        </v-btn>
        <v-btn icon>
          <v-icon>mdi-filter-variant</v-icon>
        </v-btn>
      </v-card>
    </v-card>
    <!-- table data -->
    <v-card outlined>
      <v-data-table
        v-model="selected"
        :headers="headers"
        :items="listMedicine"
        :items-per-page="5"
        class="elevation-0"
        show-select
        :footer-props="{ itemsPerPageText: 'Số hàng' }"
      >
      </v-data-table>
    </v-card>
  </v-card>
</template>
<script>
export default {
  data() {
    return {
      headers: [
        {
          text: "Ngày",
          align: "start",
          sortable: false,
          value: "date"
        },

        {
          text: "Khung giờ khám",
          value: "time",
          sortable: false
        },
        {
          text: "Loại hình khám",
          value: "type",
          sortable: false
        },
        {
          text: "Bác sĩ",
          value: "doctor.name",
          sortable: false
        },
        {
          text: "Bệnh viện",
          value: "hospital.name",
          sortable: false
        }
      ],
      listMedicine: [
        {
          date: "09/12/2022",
          time: "8:00 - 9:00. 9:00 - 10:00, 10:00 - 11:00",

          hospital: {
            name: "Bệnh Viện Đại Học Y Dược HCM"
          },
          type: "Trực tuyến",
          doctor: {
            name: "Đinh Ngọc Sơn"
          }
        },
        {
          date: "09/12/2022",
          time: "8:00 - 9:00. 9:00 - 10:00, 10:00 - 11:00",

          hospital: {
            name: "Bệnh Viện Đại Học Y Dược HCM"
          },
          type: "Tại viện",
          doctor: {
            name: "Đinh Ngọc Sơn"
          }
        },
        {
          date: "09/12/2022",
          time: "8:00 - 9:00. 9:00 - 10:00, 10:00 - 11:00",

          hospital: {
            name: "Bệnh Viện Hữu Nghị Việt Đức"
          },
          type: "Trực tuyến",
          doctor: {
            name: "Nguyễn Văn Linh"
          }
        },
        {
          date: "09/12/2022",
          time: "8:00 - 9:00. 9:00 - 10:00, 10:00 - 11:00",

          hospital: {
            name: "Bệnh Viện Hữu Nghị Việt Đức"
          },
          type: "Tại viện",
          doctor: {
            name: "Nguyễn Văn Linh"
          }
        },
        {
          date: "09/12/2022",
          time: "8:00 - 9:00. 9:00 - 10:00, 10:00 - 11:00",
          type: "Tại viện",
          hospital: {
            name: "Bệnh Viện VINMEC"
          },
          doctor: {
            name: "Martina Pellegrino"
          }
        }
      ]
    };
  }
};
</script>
