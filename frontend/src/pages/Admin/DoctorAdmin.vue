<template>
  <div class="content">
    <v-card>
      <v-data-table
        v-model="selected"
        show-select
        :headers="headers"
        :items="desserts"
        item-key="id"
        sort-by="name"
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
              Thêm bác sĩ
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
            <v-btn tile color="success">
              <v-icon left> mdi-pencil </v-icon>
              Tools
            </v-btn>
            <v-dialog v-model="dialoggetnew" max-width="600px" persistent>
              <v-card>
                <v-card-title>
                  <span class="text-h5">Tạo bác sĩ mới</span>
                </v-card-title>

                <v-card-text>
                  <v-container>
                    <v-form ref="form"> 

                          <v-row>
                      <v-col cols="12" sm="6" md="6">
                        <v-text-field
                          label="Họ và tên"
                          v-model="getnew.name"
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12" sm="6" md="6">
                        <v-text-field
                          label="Chuyên khoa"
                          v-model="getnew.chuyenkhoa"
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12" sm="6" md="6">
                        <v-text-field
                          label="Số điện thoại"
                          v-model="getnew.phonenumber"
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12" sm="6" md="6">
                        <v-text-field
                          label="Email"
                          v-model="getnew.email"
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12" sm="6" md="6">
                        <v-menu
                          ref="menu"
                          v-model="menu"
                          :close-on-content-click="false"
                          :return-value.sync="getdate"
                          transition="scale-transition"
                          offset-y
                          min-width="auto"
                        >
                          <template v-slot:activator="{ on, attrs }">
                            <v-text-field
                              v-model="getnew.startdate"
                              label="Ngày bắt đầu"
                              prepend-icon="mdi-calendar"
                              readonly
                              v-bind="attrs"
                              v-on="on"
                            ></v-text-field>
                          </template>
                          <v-date-picker
                            v-model="getnew.startdate"
                            no-title
                            scrollable
                          >
                            <v-spacer></v-spacer>
                            <v-btn text color="primary" @click="menu = false">
                              Đóng
                            </v-btn>
                            <v-btn
                              text
                              color="primary"
                              @click="$refs.menu.save(getnew.startdate)"
                            >
                              Chọn
                            </v-btn>
                          </v-date-picker>
                        </v-menu>
                      </v-col>
                    </v-row>
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
        <template v-slot:[`item.actions`]="{ item }">
          <v-icon small class="mr-2" @click="editItem(item)">
            mdi-pencil
          </v-icon>
          <v-icon small @click="deleteItem(item)"> mdi-delete </v-icon>
        </template>
      </v-data-table>

      <v-dialog v-model="dialogDelete" max-width="400px" persistent>
        <v-card>
          <v-card-title class="text-h5">
            Bạn có muốn xóa bác sĩ? <br />
          </v-card-title>

          <v-card-text>
            <v-text-field
              v-model="dlItem.name"
              label="Họ và tên"
              readonly
            ></v-text-field>
            <br />
            <v-text-field
              label="Chuyên khoa"
              v-model="dlItem.chuyenkhoa"
              readonly
            ></v-text-field>
            <br />
            <v-text-field
              label="Số điện thoại"
              v-model="dlItem.phonenumber"
              readonly
            ></v-text-field>
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

      <v-dialog v-model="dialogedit" max-width="600px" persistent>
        <v-card>
          <v-card-title>
            <span class="text-h5">Chỉnh sửa thông tin</span>
          </v-card-title>

          <v-card-text>
            <v-container>
              <v-row>
                <v-col cols="12" sm="6" md="6">
                  <v-text-field
                    v-model="editedItem.name"
                    label="Họ và tên"
                  ></v-text-field>
                </v-col>
                <v-col cols="12" sm="6" md="6">
                  <v-text-field
                    label="Chuyên khoa"
                    v-model="editedItem.chuyenkhoa"
                  ></v-text-field>
                </v-col>
                <v-col cols="12" sm="6" md="6">
                  <v-text-field
                    label="Số điện thoại"
                    v-model="editedItem.phonenumber"
                  ></v-text-field>
                </v-col>
                <v-col cols="12" sm="6" md="6">
                  <v-text-field
                    v-model="editedItem.email"
                    label="Email"
                  ></v-text-field>
                </v-col>
                <v-col cols="12" sm="6" md="6">
                  <v-menu
                    ref="menu"
                    v-model="menu1"
                    :close-on-content-click="false"
                    :return-value.sync="getdate"
                    transition="scale-transition"
                    offset-y
                    min-width="auto"
                  >
                    <template v-slot:activator="{ on, attrs }">
                      <v-text-field
                        v-model="editedItem.startdate"
                        label="Ngày bắt đầu"
                        prepend-icon="mdi-calendar"
                        readonly
                        v-bind="attrs"
                        v-on="on"
                      ></v-text-field>
                    </template>
                    <v-date-picker v-model="getdate1" no-title scrollable>
                      <v-spacer></v-spacer>
                      <v-btn text color="primary" @click="menu = false">
                        Đóng
                      </v-btn>
                      <v-btn text color="primary"> Chọn </v-btn>
                    </v-date-picker>
                  </v-menu>
                </v-col>
              </v-row>
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
    search: "",
    selected: [],
    getdate: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
      .toISOString()
      .substr(0, 10),
    menu: false,
    getdate1: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
      .toISOString()
      .substr(0, 10),
    menu1: false,
    dialoggetnew: false,
    dialogDelete: false,
    dialogedit: false,
    indexdelete: null,
    indexedit: null,
    headers: [
      {
        text: "Tên",
        align: "start",
        sortable: false,
        value: "name",
      },
      { text: "Chuyên khoa", value: "chuyenkhoa", sortable: false },
      { text: "Số điện thoại", value: "phonenumber", sortable: false },
      { text: "Email", value: "email", sortable: false },
      { text: "Ngày bắt đầu", value: "startdate", sortable: false },
      { text: "Công cụ", value: "actions", sortable: false },
    ],
    desserts: [
      {
        id: 1,
        name: "A",
        chuyenkhoa: "Nhi",
        phonenumber: "0123456789",
        email: "abc@gmail.com",
        startdate: "2020-03-31",
      },
      {
        id: 2,
        name: "B",
        chuyenkhoa: "Nội",
        phonenumber: "0123456789",
        email: "abc@gmail.com",
        startdate: "2019-03-31",
      },
    ],

    editedItem: {
      id: null,
      name: "",
      chuyenkhoa: "",
      phonenumber: "",
      startdate: "",
      email: "",
    },
    dlItem: {
      id: null,
      name: "",
      chuyenkhoa: "",
      phonenumber: "",
      startdate: "",
      email: "",
    },
    getnew: {
      id: null,
      name: null,
      chuyenkhoa: null,
      phonenumber: null,
      email: null,
      startdate: null,
    },

  }),

  watch: {},
  created() {},
  methods: {
    editItem(item) {
      this.dialogedit = true;
      this.indexedit = this.desserts.indexOf(item);

      this.editedItem.id = this.desserts[this.indexedit].id;
      this.editedItem.chuyenkhoa = this.desserts[this.indexedit].chuyenkhoa;
      this.editedItem.phonenumber = this.desserts[this.indexedit].phonenumber;
      this.editedItem.email = this.desserts[this.indexedit].email;
      this.editedItem.startdate = this.desserts[this.indexedit].startdate;
      this.editedItem.name = this.desserts[this.indexedit].name;
    },
    deleteItem(item) {
      this.dialogDelete = true;
      this.indexdelete = this.desserts.indexOf(item);

      this.dlItem.id = this.desserts[this.indexdelete].id;
      this.dlItem.chuyenkhoa = this.desserts[this.indexdelete].chuyenkhoa;
      this.dlItem.phonenumber = this.desserts[this.indexdelete].phonenumber;
      this.dlItem.email = this.desserts[this.indexdelete].email;
      this.dlItem.startdate = this.desserts[this.indexdelete].startdate;
      this.dlItem.name = this.desserts[this.indexdelete].name;
    },
    deleteItemConfirm() {},
    close() {
      this.dialogedit = false;
    },
    closegetnew() {
      this.$refs.form.reset()
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
