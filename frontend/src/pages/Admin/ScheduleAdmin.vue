<template>
  <div class="content">
    <v-card>
      <v-data-table
        v-model="selected"
        show-select
        :headers="headers"
        :items="desserts"
         item-key="id"
        sort-by="date"
   
        class="elevation-1"
        :search="search"
      >
        <template v-slot:top>
          <v-toolbar flat>
            <v-btn
              color="primary"
              dark
              class="mb-2"
              @click="dialoggetnew = true"
            >
              Thêm lịch trình
            </v-btn>
            <v-spacer></v-spacer>
            <v-text-field
              v-model="search"
              append-icon="mdi-magnify"
              label="Tìm kiếm"
              single-line
              hide-details
            ></v-text-field>
            <v-spacer></v-spacer>
            <div v-if="selected.length == 1">
              <v-btn
                tile
                color="success"
                style="margin-right: 20px"
                @click="editItem"
              >
                <v-icon left> mdi-pencil </v-icon>
                Chỉnh sửa
              </v-btn>
            </div>
            <div v-else>
              <v-btn tile color="success" style="margin-right: 20px" disabled>
                <v-icon left> mdi-pencil </v-icon>
                Chỉnh sửa
              </v-btn>
            </div>
            <div v-if="selected.length > 0">
              <v-btn tile color="error" @click="dialogDelete = true">
                <v-icon left> mdi-delete</v-icon>
                Xóa
              </v-btn>
            </div>
            <div v-else>
              <v-btn tile color="error" disabled>
                <v-icon left> mdi-delete</v-icon>
                Xóa
              </v-btn>
            </div>
            <v-dialog v-model="dialoggetnew" max-width="300px" persistent>
              <v-card>
                <v-card-title>
                  <span class="text-h5">Tạo lịch trình mới</span>
                </v-card-title>

                <v-card-text>
                  <v-container>
                    <v-form ref="form">
                      <v-menu
                        v-model="menuSchedule"
                        :close-on-content-click="false"
                        :nudge-right="40"
                        transition="scale-transition"
                        offset-y
                        min-width="auto"
                      >
                        <template v-slot:activator="{ on, attrs }">
                          <v-text-field
                            v-model="getnew.date"
                            label="Chọn ngày"
                            prepend-icon="mdi-calendar"
                            readonly
                            v-bind="attrs"
                            v-on="on"
                          ></v-text-field>
                        </template>
                        <v-date-picker
                          v-model="getnew.date"
                          @input="menuSchedule = false"
                        ></v-date-picker>
                      </v-menu>
                      <v-autocomplete
                        label="Chọn bác sĩ"
                        v-model="getnew.doctor"
                        :items="doctorselect"
                        item-text="name"
                        item-value="id"
                        dense
                        filled
                      ></v-autocomplete>
                      <v-autocomplete
                        label="Chọn thời gian"
                        v-model="getnew.times"
                        :items="timeselect"
                        dense
                        filled
                      ></v-autocomplete>
                      <v-autocomplete
                        label="Chọn phòng"
                        v-model="getnew.room"
                        :items="roomselect"
                        dense
                        filled
                      ></v-autocomplete>
                    </v-form>
                  </v-container>
                </v-card-text>

                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn color="blue darken-1" text @click="closegetnew">
                    Hủy
                  </v-btn>
                  <v-btn color="blue darken-1" text @click="save"> Lưu </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </v-toolbar>
        </template>
      </v-data-table>

      <v-dialog v-model="dialogDelete" max-width="600px" persistent>
        <v-card>
          <v-card-title class="text-h5">
            Bạn có muốn xóa lịch trình? <br />
          </v-card-title>

          <v-card-text>
            <v-simple-table>
              <template v-slot:default>
                <thead>
                  <tr>
                    <th class="text-left">STT</th>
                    <th class="text-left">Bác sĩ</th>
                    <th class="text-left">Ngày</th>

                    <th class="text-left">Thời gian</th>
                    <th class="text-left">Phòng</th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="(scheduleItem, k) in selected" :key="k">
                    <td>{{ k + 1 }}</td>
                    <td>{{ scheduleItem.doctor.name }}</td>
                    <td>{{ scheduleItem.date }}</td>
                    <td>{{ scheduleItem.times }}</td>
                    <td>{{ scheduleItem.room }}</td>
                  </tr>
                </tbody>
              </template>
            </v-simple-table>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue darken-1" text @click="closeDelete">Hủy</v-btn>
            <v-btn color="blue darken-1" text @click="deleteItemConfirm"
              >Xóa</v-btn
            >
            <v-spacer></v-spacer>
          </v-card-actions>
        </v-card>
      </v-dialog>

      <v-dialog v-model="dialogedit" max-width="300px" persistent>
        <v-card>
          <v-card-title>
            <span class="text-h5">Chỉnh sửa thông tin</span>
          </v-card-title>

          <v-card-text>
            <v-container>
                  <v-form ref="form">
                      <v-menu
                        v-model="menuedit"
                        :close-on-content-click="false"
                        :nudge-right="40"
                        transition="scale-transition"
                        offset-y
                        min-width="auto"
                      >
                        <template v-slot:activator="{ on, attrs }">
                          <v-text-field
                            v-model="editedItem.date"
                            label="Chọn ngày"
                            prepend-icon="mdi-calendar"
                            readonly
                            v-bind="attrs"
                            v-on="on"
                          ></v-text-field>
                        </template>
                        <v-date-picker
                          v-model="editedItem.date"
                          @input="menuedit = false"
                        ></v-date-picker>
                      </v-menu>
                      <v-autocomplete
                        label="Chọn bác sĩ"
                        v-model="editedItem.doctor"
                        :items="doctorselect"
                        item-text="name"
                        item-value="id"
                        dense
                        filled
                      ></v-autocomplete>
                      <v-autocomplete
                        label="Chọn thời gian"
                        v-model="editedItem.times"
                        :items="timeselect"
                        dense
                        filled
                      ></v-autocomplete>
                      <v-autocomplete
                        label="Chọn phòng"
                        v-model="editedItem.room"
                        :items="roomselect"
                        dense
                        filled
                      ></v-autocomplete>
                    </v-form>
            </v-container>
          </v-card-text>

          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue darken-1" text @click="close"> Hủy </v-btn>
            <v-btn color="blue darken-1" text @click="saveedit"> Lưu </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-card>


  </div>
</template>

<script>
export default {
  data: () => ({
    menuedit: false,
    menuSchedule: false,
    timeselect: [
      "7:00-8:00",
      "8:00-9:00",
      "9:00-10:00",
      "10:00-11:00",
      "11:00-12:00",
      "12:00-13:00",
      "14:00-15:00",
      "15:00-16:00",
    ],
    roomselect: ["H1", "H2", "H3", "H4", "H5"],
    // doctorselect: ["Dr.A", "Dr.B", "Dr.C", "Dr.D", "Dr.E"],
     doctorselect: [{
         name: "Dr.A",
          id: "1",
     },
     {
         name: "Dr.B",
          id: "2",
     },
     {
         name: "Dr.C",
          id: "3",
     }],

       
    
 
    search: "",
    selected: [],
    dialoggetnew: false,
    dialogDelete: false,
    dialogedit: false,
    indexdelete: null,
    indexedit: null,
    headers: [
      {
        text: "Ngày",
        align: "start",
        sortable: true,
        value: "date",
      },
      { text: "Thời gian", value: "times", sortable: false },
      { text: "Phòng", value: "room", sortable: false },
      { text: "Bác sĩ", value: "doctor.name", sortable: false },
    ],
    desserts: [
      {
        id: 1,
        date: "2022-04-07",
        times: "9:00-10:00",
        room: "H1",
        doctor: {
          name: "Dr.A",
          id: "1",
        },
      },
      {
        id: 2,
        date: "2022-04-08",
        times: "10:00-11:00",
        room: "H2",
        doctor: {
          name: "Dr.B",
          id: "2",
        },
        
      },
      {
        id: 3,
        date: "2022-04-09",
        times: "11:00-12:00",
        room: "H1",
        doctor: {
          name: "Dr.B",
          id: "2",
        },
      }
    ],

    editedItem: {
      date: null,
      times: null,
      room: null,
      doctor: {
        name: null,
        id: null,
      },
    },

    getnew: {
      date: null,
      times: null,
      room: null,
      doctor: {
        name: null,
        id: null,
      },
    },
  }),

  watch: {},
  created() {},
  methods: {
    editItem() {
      this.dialogedit = true;

      this.editedItem.date = this.selected[0].date;
      this.editedItem.times = this.selected[0].times;
      this.editedItem.room = this.selected[0].room;
      this.editedItem.doctor = this.selected[0].doctor;
      this.editedItem.doctor.name = this.selected[0].doctor.name;
      this.editedItem.doctor.id = this.selected[0].doctor.id;
    },

    deleteItemConfirm() {},
    close() {
      this.dialogedit = false;
    },
    closegetnew() {
      this.$refs.form.reset();
      this.dialoggetnew = false;
    },
    closeDelete() {
      this.dialogDelete = false;
    },
    save() {},
    saveedit() {},
  },
};
</script>
