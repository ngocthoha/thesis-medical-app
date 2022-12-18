<template>
  <v-card class="d-flex flex-column pa-8">
    <!-- header -->
    <v-divider></v-divider>
    <v-card elevation="0 pa-5">
      <p class="font-weight-bold ma-0" style="font-size: 24px">
        Quản lý phòng
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
        :items="listRoom"
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
          text: "Tên Phòng",
          align: "start",
          sortable: false,
          value: "name"
        },

        {
          text: "Bệnh viện",
          value: "hospital.name",
          sortable: false
        },

        {
          text: "Link trực tuyến",
          value: "link",
          sortable: false
        }
      ],
      listRoom: [
        {
          name: "H1",
          hospital: {
            name: "Bệnh Viện Đại Học Y Dược HCM"
          },
          link: "https://meet.google.com/eoq-kwje-jsu"
        },
        {
          name: "H2",
          hospital: {
            name: "Bệnh Viện Đại Học Y Dược HCM"
          }
        },
        {
          name: "H3",
          hospital: {
            name: "Bệnh Viện Đại Học Y Dược HCM"
          },
          link: "https://meet.google.com/eoq-hdsd-dqa"
        },
        {
          name: "H4",
          hospital: {
            name: "Bệnh Viện Đại Học Y Dược HCM"
          },
          link: "https://meet.google.com/eoq-dda1-had"
        },
        {
          name: "H5",
          hospital: {
            name: "Bệnh Viện Đại Học Y Dược HCM"
          }
        },
        {
          name: "H6",
          hospital: {
            name: "Bệnh Viện Đại Học Y Dược HCM"
          },
          link: "https://meet.google.com/eoq-ddaa-jsu"
        }
      ]
    };
  }
};
</script>
