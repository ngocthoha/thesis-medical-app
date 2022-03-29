<template>
  <div class="content">
    <div class="md-layout">
      <div
        class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-20"
      >
        <div class="nutThaoTac">
          <div>
            <!-- <md-button
              class="md-raised md-primary"
              style="background-color: #448aff !important"
              >Tạo Hồ sơ</md-button
            > -->

            <v-dialog v-model="dialog" max-width="1200px" max-height="auto">
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                  color="#448aff"
                  dark
                  class="mb-2"
                  v-bind="attrs"
                  v-on="on"
                  style="margin: 23px 0px"
                >
                  Tạo bệnh án mới
                </v-btn>
              </template>
              <v-card>
                <v-card-title>
                  <span class="text-h5">{{ formTitle }}</span>
                </v-card-title>

                <v-card-text>
                  <!-- Start Tạo hồ sơ ở đây -->
                  <!-- Start Tạo hồ sơ ở đây -->
                  <!-- Start Tạo hồ sơ ở đây -->

                  <div class="mhs">
                    <p><b>HỒ SƠ BỆNH ÁN: </b></p>
                    <input v-model="editedItem.id" />

                  </div>

                  <p><b>BÁC SĨ: NGUYỄN VĂN A</b></p>
                  <div class="mhs">
                    <p><b>NGÀY KHÁM: </b></p>
                    <input v-model="editedItem.start" />
                    <p><b> THỜI GIAN: </b></p>
                  </div>
                  

                  <!-- <v-app id="inspire">  -->

                  <div>
                    <v-stepper non-linear v-model="e1">
                      <v-stepper-header>
                        <v-stepper-step editable step="1"
                          >Thông tin bệnh nhân
                        </v-stepper-step>

                        <v-divider></v-divider>

                        <v-stepper-step editable step="2">
                          Chuẩn đoán bệnh
                        </v-stepper-step>

                        <v-divider></v-divider>

                        <v-stepper-step step="3" editable>
                          Kê đơn
                        </v-stepper-step>
                      </v-stepper-header>

                      <v-stepper-items>
                        <v-stepper-content :key="`${1}-content`" :step="1">
                          <v-card class="mb-12" color="white" height="auto">
                            <div class="gop">
                              <div class="gop0">
                                <div class="gop1">
                                  <div class="textform">
                                    <p>1. Họ và tên:</p>
                                    <input
                                      v-model="editedItem.name"
                                      type="text"
                                      placeholder="Nhập họ và tên..."
                                    />
                                  </div>

                                  <div class="textform">
                                    <p>2. Năm sinh:</p>
                                    <div class="namsinh">
                                      <v-menu
                                        v-model="menu11"
                                        :close-on-content-click="false"
                                        :nudge-right="40"
                                        transition="scale-transition"
                                        offset-y
                                        min-width="auto"
                                      >
                                        <template
                                          v-slot:activator="{ on, attrs }"
                                        >
                                          <v-text-field
                                            v-model="editedItem.year"
                                            prepend-icon="mdi-calendar"
                                            readonly
                                            v-bind="attrs"
                                            v-on="on"
                                          ></v-text-field>
                                        </template>
                                        <v-date-picker
                                          v-model="editedItem.year"
                                          @input="menu11 = false"
                                        ></v-date-picker>
                                      </v-menu>
                                    </div>
                                  </div>
                                  <div class="textform">
                                    <p>3. Giới tính:</p>
                                    <div class="gioitinh">
                                      <v-radio-group
                                        v-model="editedItem.gioitinh"
                                        row
                                      >
                                        <v-radio
                                          label="Nam"
                                          value="Nam"
                                        ></v-radio>
                                        <v-radio
                                          label="Nữ"
                                          value="Nữ"
                                        ></v-radio>
                                      </v-radio-group>
                                    </div>
                                  </div>
                                  <div class="textform">
                                    <p>4. Dân tộc:</p>
                                    <div class="autocom">
                                      <v-toolbar color="white">
                                        <v-autocomplete
                                          v-model="select"
                                          :loading="loading"
                                          :items="items"
                                          :search-input.sync="search1"
                                          cache-items
                                          class="mx-4"
                                          flat
                                          hide-no-data
                                          hide-details
                                          label="Dân tộc..."
                                          solo-inverted
                                        ></v-autocomplete>
                                      </v-toolbar>
                                    </div>
                                  </div>
                                  <div class="textform">
                                    <p>5. Mã số BHYT(Nếu có):</p>
                                    <input
                                      type="text"
                                      placeholder="Nhập MSBHYT..."
                                    />
                                  </div>

                                  <div class="textform">
                                    <p>6. Nghề nghiệp</p>
                                    <input
                                      type="text"
                                      placeholder="Nhập tên nghề nghiệp..."
                                    />
                                  </div>

                                  <div class="textform">
                                    <p>7. Địa chỉ:</p>
                                    <input
                                      type="text"
                                      placeholder="Nhập địa chỉ..."
                                    />
                                  </div>

                                  <div class="textform">
                                    <p>8. Tên người giám hộ(Nếu có)</p>
                                    <input
                                      type="text"
                                      placeholder="Nhập tên người giám hộ..."
                                    />
                                  </div>
                                  <div class="textform">
                                    <p>9. SĐT Liên lạc:</p>
                                    <input
                                      type="text"
                                      placeholder="Nhập SĐT..."
                                    />
                                  </div>

                                  <div class="textform">
                                    <p>10. CMND:</p>
                                    <input
                                      type="text"
                                      placeholder="Nhập CMND"
                                    />
                                  </div>
                                </div>
                              </div>
                            </div>
                          </v-card>
                          <v-btn color="primary" @click="nextStep(1)">
                            Tiếp tục
                          </v-btn>
                        </v-stepper-content>

                        <v-stepper-content :key="`${2}-content`" :step="2">
                          <v-card class="mb-12" color="white" height="auto">
                            <div class="page2_0">
                              <div class="page2_1">
                                <div class="page2_2">
                                  <div class="textpage2">
                                    <p>1. Quá trình bệnh lí:</p>
                                    <v-container fluid>
                                      <v-textarea
                                        clearable
                                        clear-icon="mdi-close-circle"
                                        label="Biểu hiện của bệnh"
                                        background-color="grey lighten-2"
                                        color="cyan"
                                        rows="1"
                                        auto-grow
                                        style="
                                          border-radius: 5px;
                                          padding: 0px 0px;
                                        "
                                      ></v-textarea>
                                    </v-container>
                                  </div>

                                  <div class="textpage2">
                                    <p>2. Tiền sử bệnh bản thân:</p>
                                    <v-container fluid>
                                      <v-textarea
                                        clearable
                                        clear-icon="mdi-close-circle"
                                        label="Các bệnh đã mắc"
                                        background-color="grey lighten-2"
                                        color="cyan"
                                        auto-grow
                                        rows="1"
                                        style="
                                          border-radius: 5px;
                                          padding: 0px 0px;
                                        "
                                      ></v-textarea>
                                    </v-container>
                                  </div>
                                  <div class="textpage2">
                                    <p>3. Tiền sử bệnh gia đình:</p>
                                    <v-container fluid>
                                      <v-textarea
                                        clearable
                                        clear-icon="mdi-close-circle"
                                        label="Tiền sử bệnh gia đình"
                                        background-color="grey lighten-2"
                                        color="cyan"
                                        auto-grow
                                        rows="1"
                                        style="
                                          border-radius: 5px;
                                          padding: 0px 0px;
                                        "
                                      ></v-textarea>
                                    </v-container>
                                  </div>
                                  <div class="textpage2">
                                    <p>4. Khám xét toàn thân:</p>
                                    <v-container fluid>
                                      <v-textarea
                                        clearable
                                        clear-icon="mdi-close-circle"
                                        label="Kết quả khám toàn thân"
                                        background-color="grey lighten-2"
                                        color="cyan"
                                        auto-grow
                                        rows="1"
                                        style="
                                          border-radius: 5px;
                                          padding: 0px 0px;
                                        "
                                      ></v-textarea>
                                    </v-container>
                                  </div>
                                  <div class="textpage2">
                                    <div class="ketquatongquat">
                                      <p>Mạch:</p>
                                      <input type="text" />
                                      <p>lần/phút</p>
                                      <p style="color: white">______</p>
                                      <p>Nhiệt độ:</p>
                                      <input type="text" />
                                      <p>độ C</p>
                                      <p style="color: white">______</p>
                                      <p>Huyết áp:</p>
                                      <input type="text" />
                                      <p>mmHg</p>
                                      <p style="color: white">______</p>
                                      <p>Nhịp tim:</p>
                                      <input type="text" />
                                      <p>lần/phút</p>
                                      <br />
                                      <p
                                        style="
                                          color: white;
                                          margin-bottom: 20px;
                                        "
                                      >
                                        .
                                      </p>
                                    </div>
                                  </div>
                                  <div class="textpage2">
                                    <p>5. Khám xét các bộ phận</p>
                                    <v-container fluid>
                                      <v-textarea
                                        clearable
                                        clear-icon="mdi-close-circle"
                                        label="Kết quả khám bộ phận"
                                        background-color="grey lighten-2"
                                        color="cyan"
                                        auto-grow
                                        rows="1"
                                        style="
                                          border-radius: 5px;
                                          padding: 0px 0px;
                                        "
                                      ></v-textarea>
                                    </v-container>
                                  </div>

                                  <div class="textpage2">
                                    <p>6. Tóm tắt kết quả lâm sàng:</p>
                                    <v-container fluid>
                                      <v-textarea
                                        v-model="editedItem.symptom"
                                        clearable
                                        clear-icon="mdi-close-circle"
                                        label="Chuẩn đoán lâm sàng"
                                        background-color="grey lighten-2"
                                        color="cyan"
                                        auto-grow
                                        rows="1"
                                        style="
                                          border-radius: 5px;
                                          padding: 0px 0px;
                                        "
                                      ></v-textarea>
                                    </v-container>
                                  </div>

                                  <div class="textpage2">
                                    <p>7. Hình ảnh bổ sung:</p>
                                    <v-file-input
                                      label="File input"
                                      filled
                                      multiple
                                      prepend-icon="mdi-camera"
                                    ></v-file-input>
                                  </div>
                                </div>
                              </div>
                            </div>
                          </v-card>

                          <v-btn text @click="beforeStep(2)"> Quay lại </v-btn>
                          <v-btn color="primary" @click="nextStep(2)">
                            Tiếp tục
                          </v-btn>
                        </v-stepper-content>

                        <v-stepper-content :key="`${3}-content`" :step="3">
                          <v-card class="mb-12" color="white" height="auto">
                            <div class="page2_0">
                              <div class="page2_1">
                                <div class="page2_2">
                                  <div class="textpage2">
                                    <p>CHUẨN ĐOÁN NHẬP VIỆN:</p>
                                    <v-radio-group row>
                                      <v-radio
                                        label="Yêu cầu nhập viện"
                                        value="Yes"
                                      ></v-radio>
                                      <v-radio
                                        label="Không nhập viện"
                                        value="No"
                                      ></v-radio>
                                    </v-radio-group>
                                  </div>

                                  <div class="textpage2">
                                    <p>KHOA ĐIỀU TRỊ:</p>
                                    <v-autocomplete
                                      v-model="khoa"
                                      :items="dskhoa"
                                      dense
                                      filled
                                    ></v-autocomplete>
                                  </div>

                                  <div class="textpage2">
                                    <p>KÊ ĐƠN THUỐC:</p>
                                    <div class="textpage3">
                                      <div class="textpage4">
                                        <div class="ketquatongquat">
                                          <p>1.</p>
                                          <v-autocomplete
                                            v-model="thuoc"
                                            :items="dsthuoc"
                                            dense
                                            filled
                                            label="Tên thuốc"
                                          ></v-autocomplete>
                                          <div class="combosoluongloai">
                                            <div class="soluong">
                                              <p>Số lượng:</p>
                                              <input
                                                type="text"
                                                style="margin: 0px 10px"
                                              />
                                            </div>

                                            <div class="loai">
                                              <v-autocomplete
                                                v-model="loai"
                                                :items="dsloai"
                                                dense
                                                filled
                                                label="loại"
                                              ></v-autocomplete>
                                            </div>
                                          </div>

                                          <v-autocomplete
                                            v-model="namethuoc"
                                            :items="dsnamethuoc"
                                            dense
                                            chips
                                            small-chips
                                            label="Cách dùng"
                                            multiple
                                            solo
                                          ></v-autocomplete>
                                          <v-divider
                                            style="margin-bottom: 20px"
                                          ></v-divider>
                                        </div>
                                      </div>
                                    </div>

                                    <div class="controls">
                                      <v-btn block style="width: 100%">
                                        Thêm mới
                                      </v-btn>
                                    </div>
                                    <v-divider
                                      style="margin-bottom: 20px"
                                    ></v-divider>
                                  </div>

                                  <div class="textpage2">
                                    <p>LƯU Ý:</p>
                                    <v-container fluid>
                                      <v-textarea
                                        clearable
                                        clear-icon="mdi-close-circle"
                                        background-color="grey lighten-2"
                                        color="cyan"
                                        auto-grow
                                        rows="3"
                                        style="
                                          border-radius: 5px;
                                          padding: 0px 0px;
                                        "
                                      ></v-textarea>
                                    </v-container>
                                  </div>

                                  <div class="textpage2">
                                    <p>NGÀY TÁI KHÁM:</p>

                                    <v-menu
                                      v-model="menu10"
                                      :close-on-content-click="false"
                                      :nudge-right="40"
                                      transition="scale-transition"
                                      offset-y
                                      min-width="auto"
                                    >
                                      <template
                                        v-slot:activator="{ on, attrs }"
                                      >
                                        <v-text-field
                                          v-model="datetaikham"
                                          prepend-icon="mdi-calendar"
                                          readonly
                                          v-bind="attrs"
                                          v-on="on"
                                        ></v-text-field>
                                      </template>
                                      <v-date-picker
                                        v-model="datetaikham"
                                        @input="menu10 = false"
                                      ></v-date-picker>
                                    </v-menu>
                                  </div>
                                </div>
                              </div>
                            </div>
                          </v-card>

                          <v-btn text @click="beforeStep(3)"> Quay Lại </v-btn>
                        </v-stepper-content>
                      </v-stepper-items>
                    </v-stepper>
                  </div>
                  <!-- </v-app>  -->

                  <!-- End Tạo hồ sơ ở đây -->
                  <!-- End Tạo hồ sơ ở đây -->
                  <!-- End Tạo hồ sơ ở đây -->
                </v-card-text>

                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn color="blue darken-1" text @click="close">
                    Hủy bỏ
                  </v-btn>
                  <v-btn color="blue darken-1" text @click="save">
                    Lưu thay đổi
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </div>

          <P><b>LỌC HỒ SƠ THEO NGÀY</b></P>
          <p>Ngày bắt đầu</p>
          <div class="ngaybatdau">
            <v-menu
              v-model="menu2"
              :close-on-content-click="false"
              :nudge-right="40"
              transition="scale-transition"
              offset-y
              min-width="auto"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-text-field
                  v-model="computedDateFormatted"
                  prepend-icon="mdi-calendar"
                  readonly
                  v-bind="attrs"
                  v-on="on"
                ></v-text-field>
              </template>
              <v-date-picker
                v-model="date"
                @input="menu2 = false"
              ></v-date-picker>
            </v-menu>
          </div>

          <p>Ngày kết thúc</p>
          <div class="ngayketthuc">
            <v-menu
              v-model="menu3"
              :close-on-content-click="false"
              :nudge-right="40"
              transition="scale-transition"
              offset-y
              min-width="auto"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-text-field
                  v-model="computedDateFormatted1"
                  prepend-icon="mdi-calendar"
                  readonly
                  v-bind="attrs"
                  v-on="on"
                ></v-text-field>
              </template>
              <v-date-picker
                v-model="date1"
                @input="menu3 = false"
              ></v-date-picker>
            </v-menu>
          </div>

          <md-button class="md-raised" style="background-color: #333 !important"
            >Lọc danh sách</md-button
          >
        </div>
      </div>

      <div
        class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-75"
      >
        <v-app>
          <v-data-table
            :headers="headers"
            :items="desserts"
            :search="search"
            sort-by="id"
            class="elevation-1"
          >
            <template v-slot:top>
              <v-toolbar flat>
                <v-toolbar-title>Danh sách bệnh án</v-toolbar-title>
                <v-divider class="mx-4" inset vertical></v-divider>
                <v-spacer></v-spacer>
                <v-text-field
                  v-model="search"
                  append-icon="mdi-magnify"
                  label="Tìm kiếm..."
                  single-line
                  hide-details
                ></v-text-field>

                <v-dialog v-model="dialogDelete" max-width="500px">
                  <v-card>
                    <v-card-title class="text-h5"
                      >Bạn chắc chắn muốn xóa hồ sơ này!</v-card-title
                    >
                    <v-card-actions>
                      <v-spacer></v-spacer>
                      <v-btn color="blue darken-1" text @click="closeDelete"
                        >Hủy thao tác</v-btn
                      >
                      <v-btn
                        color="blue darken-1"
                        text
                        @click="deleteItemConfirm"
                        >Xác nhận</v-btn
                      >
                      <v-spacer></v-spacer>
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
            <template v-slot:no-data>
              <v-btn color="primary" @click="initialize"> Reset </v-btn>
            </template>
          </v-data-table>
        </v-app>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data: () => ({
    date: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
      .toISOString()
      .substr(0, 10),
    date1: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
      .toISOString()
      .substr(0, 10),

    menu2: false,
    menu3: false,
    dialog: false,
    dialogDelete: false,
    search: "",
    headers: [
      {
        text: "Mã hồ sơ",
        align: "start",
        value: "id",
      },
      { text: "Họ và tên", value: "name" },
      { text: "Giới tính", value: "gioitinh" },
      { text: "Năm sinh", value: "year" },
      { text: "Triệu chứng", value: "symptom" },
      { text: "Ngày khởi tạo", value: "start" },
      { text: "Chỉnh sửa/Xóa", value: "actions", sortable: false },
    ],
    desserts: [],
    editedIndex: -1,
    editedItem: {
      id: 0,
      name: "",
      gioitinh: "",
      year: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
        .toISOString()
        .substr(0, 10),
      symptom: "",
      start: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
        .toISOString()
        .substr(0, 10),
    },
    defaultItem: {
      id: 0,
      name: "",
      gioitinh: "",
      year: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
        .toISOString()
        .substr(0, 10),
      symptom: "",
      start: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
        .toISOString()
        .substr(0, 10),
    },

    menu10: false,
    menu11: false,
    datetaikham: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
      .toISOString()
      .substr(0, 10),
    datenamsinh: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
      .toISOString()
      .substr(0, 10),
    thuoc: null,
    dsthuoc: ["Thuốc A", "Thuốc B"],
    khoa: null,
    dskhoa: [
      "Khoa Nội",
      "Khoa Ngoại",
      "Khoa Phụ Sản",
      "Khoa Nhi",
      "Khoa Truyền Nhiễm",
      "Khoa Cấp Cứu",
      "Khoa Hồi Sức và Chống Độc",
      "Khoa Ung Bướu",
    ],
    dsnamethuoc: [
      "Sáng-1v",
      "Sáng-2v",
      "Trưa-1v",
      "Trưa-2v",
      "Tối-1v",
      "Tối-2v",
    ],
    namethuoc: null,
    loai: null,
    dsloai: ["Viên", "Vỉ", "Hộp"],
    value: null,
    radios: null,
    e1: 1,
    steps: 3,
    loading: false,
    items: [],
    search1: null,
    select: null,
    states: ["Kinh", "Thái", "Mường", "Dao", "GiaRai"],
  }),
  computed: {
    formTitle() {
      return this.editedIndex === -1 ? "Tạo bệnh án mới" : "Chỉnh sửa bệnh án";
    },
    computedDateFormatted() {
      return this.formatDate(this.date);
    },
    computedDateFormatted1() {
      return this.formatDate(this.date1);
    },
  },
  watch: {
    dialog(val) {
      val || this.close();
    },
    dialogDelete(val) {
      val || this.closeDelete();
    },

    steps(val) {
      if (this.e1 > val) {
        this.e1 = val;
      }
    },
    search1(val) {
      val && val !== this.select && this.querySelections(val);
    },
  },
  created() {
    this.initialize();
  },
  methods: {
    initialize() {
      this.desserts = [
        {
          id: 1,
          name: "Nguyễn Ngọc A",
          gioitinh: "Nam",
          year: "2022-03-18",
          symptom: "Loét dạ dày",
          start: "13-03-2022",
        },
        {
          id: 2,
          name: "Nguyễn Ngọc B",
          gioitinh: "Nữ",
          year: "2022-03-16",
          symptom: "Viêm ruột thừa",
          start: "14-02-2022",
        },
        {
          id: 3,
          name: "Nguyễn Ngọc C",
          gioitinh: "Nam",
          year: "2022-03-16",
          symptom: "Loét dạ dày",
          start: "13-03-2022",
        },
        {
          id: 4,
          name: "Nguyễn Ngọc D",
          gioitinh: "Nữ",
          year: "2022-03-16",
          symptom: "Viêm ruột thừa",
          start: "14-02-2022",
        },
        {
          id: 5,
          name: "Nguyễn Ngọc E",
          gioitinh: "Nam",
          year: "2022-03-16",
          symptom: "Loét dạ dày",
          start: "13-03-2022",
        },
        {
          id: 6,
          name: "Nguyễn Ngọc F",
          gioitinh: "Nữ",
          year: "2022-03-16",
          symptom: "Viêm ruột thừa",
          start: "14-02-2022",
        },
        {
          id: 7,
          name: "Nguyễn Ngọc G",
          gioitinh: "Nam",
          year: "2022-03-16",
          symptom: "Loét dạ dày",
          start: "13-03-2022",
        },
        {
          id: 8,
          name: "Nguyễn Ngọc H",
          gioitinh: "Nữ",
          year: "2022-03-16",
          symptom: "Viêm ruột thừa",
          start: "14-02-2022",
        },
        {
          id: 9,
          name: "Nguyễn Ngọc I",
          gioitinh: "Nam",
          year: "2022-03-16",
          symptom: "Loét dạ dày",
          start: "13-03-2022",
        },
        {
          id: 10,
          name: "Nguyễn Ngọc J",
          gioitinh: "Nữ",
          year: "2022-03-16",
          symptom: "Viêm ruột thừa",
          start: "14-02-2022",
        },
        {
          id: 11,
          name: "Nguyễn Ngọc K",
          gioitinh: "Nam",
          year: "2022-03-16",
          symptom: "Loét dạ dày",
          start: "13-03-2022",
        },
        {
          id: 12,
          name: "Nguyễn Ngọc L",
          gioitinh: "Nữ",
          year: "2022-03-16",
          symptom: "Viêm ruột thừa",
          start: "14-02-2022",
        },
      ];
    },
    editItem(item) {
      this.editedIndex = this.desserts.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialog = true;
    },
    deleteItem(item) {
      this.editedIndex = this.desserts.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialogDelete = true;
    },
    deleteItemConfirm() {
      this.desserts.splice(this.editedIndex, 1);
      this.closeDelete();
    },
    close() {
      this.dialog = false;
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      });
    },
    closeDelete() {
      this.dialogDelete = false;
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      });
    },
    save() {
      if (this.editedIndex > -1) {
        Object.assign(this.desserts[this.editedIndex], this.editedItem);
      } else {
        this.desserts.push(this.editedItem);
      }
      this.close();
    },
    formatDate(date) {
      if (!date) return null;

      const [year, month, day] = date.split("-");
      return `${day}-${month}-${year}`;
    },

    nextStep(n) {
      if (n === this.steps) {
        this.e1 = 3;
      } else {
        this.e1 = n + 1;
      }
    },
    beforeStep(n) {
      if (n === this.steps) {
        this.e1 = n - 1;
      } else {
        this.e1 = 1;
      }
    },
    querySelections(v) {
      this.loading = true;
      // Simulated ajax query
      setTimeout(() => {
        this.items = this.states.filter((e) => {
          return (e || "").toLowerCase().indexOf((v || "").toLowerCase()) > -1;
        });
        this.loading = false;
      }, 500);
    },
  },
};
</script>

<style lang="scss" scoped>
.nutThaoTac {
  background-color: #ffffff;
  text-align: center;
  border-radius: 15px;
  box-shadow: 1px 1px 1px 1px #b8b5b5;
}

.ngaybatdau .v-input {
  max-width: 70%;
  margin-left: auto;
  margin-right: auto;
}

.ngayketthuc .v-input {
  max-width: 70%;
  margin-left: auto;
  margin-right: auto;
}

.textform {
  width: 70%;
  margin-left: auto;
  margin-right: auto;
  justify-content: center;
  align-items: center;
  flex-direction: row;
  align-content: center;
}
.textform p {
  width: 15%;
  height: 34px;
  font-size: 16px;
  margin: 20px 0px 10px 0px;
  min-width: 250px;
  align-items: center;
}
.textform .inputtext {
  min-width: 300px;
  height: 40px;
}

.textform input {
  background-color: #ffffff;
  color: #000000;
  height: 38px;
  margin: 20px 0px 10px 0px;
  border-radius: 15px;
  width: 100%;
  font-size: 16px;
  padding: 10px 20px;
  box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
}

.textform input:focus {
  box-shadow: rgb(255, 255, 255) 0px 3px 8px;
  border-style: ridge;
  border-color: rgb(104, 101, 101);
}

.autocom {
  height: 64px;
  overflow: hidden;
  background-color: #ffffff;
  margin: 0 0;
}
.gop {
  width: 100%;
  background-color: rgba(201, 198, 195, 0.671);
}
.gop0 {
  width: 50%;
  min-width: 1000px;
  margin-left: auto;
  margin-right: auto;
  background-color: #ffffff;
}
.gop1 {
  width: 100%;
}

.textpage2 {
  margin: 0px 20px;
  width: auto;
}

.textpage2 p {
  width: 15%;
  height: 34px;
  font-size: 16px;
  margin: 0px 0px;
  min-width: 250px;
  align-items: center;
}
.ketquatongquat input {
  width: 60px;
  background-color: rgb(223, 215, 215);
  height: 30px;
  border-radius: 5px;
  color: #333;
}

.ketquatongquat p {
  width: 50px;
  display: inline;
}

.page2_0 {
  width: 100%;
  background-color: rgba(201, 198, 195, 0.671);
}
.page2_1 {
  width: 50%;
  min-width: 1000px;
  margin-left: auto;
  margin-right: auto;
  background-color: #ffffff;
}
.combosoluongloai {
  width: 100%;
  display: flex;
}
.soluong {
  width: 150px;
  height: 52px;
}
.soluong input {
  height: 50px;
  font-size: 16px;
  color: #333;
  padding: 0px 10px;
}
.loai {
  width: 30%;
}

.namsinh {
  width: 300px;
}
.gioitinh {
  width: 300px;
}
.mhs p {
  width: auto;
}
.mhs input {
  height: 17px;
  font-size: 15px;
  margin: 2px 5px;
}
.mhs {
  display: flex;
}
</style>
