<template>
  <v-card class="fill-height d-flex flex-column pa-8">
    <v-footer color="#3C5E7E" padless class="d-flex justify-center pa-5">
      <p class="ma-0 white--text" style="font-size: 20px">
        Danh sách khám sắp tới
      </p>
    </v-footer>
    <v-data-table
      v-model="selected"
      :headers="headers"
      :items="list_appointment"
      :single-select="singleSelect"
      item-key="id"
      show-select
      class="elevation-1"
      checkbox-color="#3C5E7E"
    >
      <template v-slot:top>
        <div class="pa-3">
          <v-btn>Hủy</v-btn>
        </div>
        <v-switch
          v-model="singleSelect"
          label="Chọn một"
          class="pa-3"
          color="#537DA5"
        ></v-switch>
      </template>
      <template v-slot:[`item.actions`]="{ item }">
        <!-- <v-icon small class="mr-2" @click="editItem(item)">
          mdi-pencil
        </v-icon>
        <v-icon small @click="deleteItem(item)">
          mdi-delete
        </v-icon> -->

        <v-btn color="#537DA5" elevation="0" class="btn"
          ><p
            class="ma-0 font-weight-medium"
            style="color:white"
            @click.stop="examinate(item)"
          >
            Khám
          </p></v-btn
        >
      </template>
    </v-data-table>

    <v-dialog v-model="exam_dialog" width="600px">
      <v-card>a</v-card>
    </v-dialog>
  </v-card>
</template>

<script>
export default {
  data() {
    return {
      singleSelect: false,
      selected: [],
      headers: [
        {
          text: "Họ và tên đệm",
          align: "start",
          sortable: false,
          value: "lastName"
        },
        { text: "Tên", value: "firstName" },
        { text: "Ngày sinh", value: "dob" },
        { text: "Giới tính", value: "gender" },
        { text: "Ngày khám", value: "date_medical_examination" },
        { text: "", value: "actions", sortable: false }
      ],
      list_appointment: [
        {
          id: "1",
          firstName: "Thanh",
          lastName: "Nguyễn Duy",
          dob: "03/03/2000",
          gender: "Nam",
          date_medical_examination: "20/10/2022"
        },
        {
          id: "2",
          firstName: "Thọ",
          lastName: "Hà Ngọc",
          dob: "03/03/2003",
          gender: "Nam",
          date_medical_examination: "20/10/2022"
        },
        {
          id: "3",
          firstName: "Thúy",
          lastName: "Nguyễn Thị",
          dob: "03/03/2004",
          gender: "Nữ",
          date_medical_examination: "20/10/2022"
        }
      ],
      exam_dialog: false
    };
  },
  methods: {
    examinate(item) {
      this.exam_dialog = true;
    }
  }
};
</script>

<style scoped>
.btn {
  text-transform: none;
}
</style>
