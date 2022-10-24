<template>
  <v-card class="fill-height d-flex flex-column pa-8">
    <v-footer color="#3C5E7E" padless class="d-flex justify-center pa-5">
      <p class="ma-0 white--text" style="font-size: 20px">
        Danh sách khám sắp tới
      </p>
    </v-footer>
    <v-data-table
      v-model="selected"
      :headers="headers_appointment"
      :items="list_appointment"
      :single-select="true"
      item-key="id"
      show-select
      class="elevation-1"
      checkbox-color="#3C5E7E"
    >
      <template v-slot:top>
        <div class="pa-6 d-flex flex-row">
          <v-btn
            elevation="0"
            color="#537DA5"
            outlined
            class="btn font-weight-medium"
            >Hôm nay</v-btn
          >
          <v-btn class="ml-3 btn font-weight-medium" color="#537DA5" outlined
            >Tất cả</v-btn
          >
          <v-spacer></v-spacer>
          <v-btn
            class="mr-3 white--text font-weight-medium btn"
            elevation="0"
            color="#476D92"
            @click.stop="examinate()"
            >Khám</v-btn
          >
          <v-btn
            class="mr-3 white--text font-weight-medium btn"
            elevation="0"
            color="#667085"
            >Chuyển lịch</v-btn
          >
        </div>
      </template>
      <template v-slot:[`item.room`]="{ item }">
        <div class="d-flex flex-row align-center">
          <p class="ma-0">{{ item.room.name }}</p>
          <v-icon v-if="item.room.type === 'online'" class="ml-2"
            >mdi-video-outline</v-icon
          >
        </div>
      </template>
    </v-data-table>

    <v-dialog v-model="exam_dialog" fullscreen>
      <v-card>
        <v-footer
          color="#314E6A"
          class="white--text d-flex flex-row"
          height="50px"
          tile
        >
          <p class="ma-0">Giao diện khám bệnh</p>
          <v-spacer></v-spacer>
          <v-btn color="#314E6A" elevation="0" icon
            ><v-icon color="white" @click.stop="exam_dialog = false"
              >mdi-close</v-icon
            ></v-btn
          >
        </v-footer>
        <v-card elevation="0">
          <v-tabs color="#314E6A">
            <v-tab> Thông tin khám </v-tab>
            <v-tab> Lịch sử khám </v-tab>
            <v-tab> Khám bệnh</v-tab>
            <v-tab>Kê đơn</v-tab>
            <!-- profile -->
            <v-tab-item>
              <v-card flat class="d-flex flex-column pa-6">
                <!-- last name, first name, dob -->
                <div class="d-flex flex-row justify-space-between">
                  <v-card width="30%" elevation="0" class="d-flex flex-column">
                    <p class="text-body-2 ma-0 font-weight-medium">
                      Họ và tên lót:
                    </p>
                    <v-text-field
                      readonly
                      solo
                      flat
                      background-color="#EEF2F6"
                    ></v-text-field>
                  </v-card>
                  <v-card width="30%" elevation="0" class="d-flex flex-column">
                    <p class="text-body-2 ma-0 font-weight-medium">Tên:</p>
                    <v-text-field
                      readonly
                      solo
                      flat
                      background-color="#EEF2F6"
                    ></v-text-field>
                  </v-card>
                  <v-card width="30%" elevation="0" class="d-flex flex-column">
                    <p class="text-body-2 ma-0 font-weight-medium">Ngày sinh</p>
                    <v-text-field
                      readonly
                      solo
                      flat
                      background-color="#EEF2F6"
                    ></v-text-field>
                  </v-card>
                </div>

                <!-- job, gender, phone -->
                <div class="d-flex flex-row justify-space-between">
                  <v-card width="30%" elevation="0" class="d-flex flex-column">
                    <p class="text-body-2 ma-0 font-weight-medium">
                      Nghề nghiệp:
                    </p>
                    <v-text-field
                      readonly
                      solo
                      flat
                      background-color="#EEF2F6"
                    ></v-text-field>
                  </v-card>
                  <v-card width="30%" elevation="0" class="d-flex flex-column">
                    <p class="text-body-2 ma-0 font-weight-medium">
                      Giới tính:
                    </p>
                    <v-text-field
                      readonly
                      solo
                      flat
                      background-color="#EEF2F6"
                    ></v-text-field>
                  </v-card>
                  <v-card width="30%" elevation="0" class="d-flex flex-column">
                    <p class="text-body-2 ma-0 font-weight-medium">
                      Số điện thoại
                    </p>
                    <v-text-field
                      readonly
                      solo
                      flat
                      background-color="#EEF2F6"
                    ></v-text-field>
                  </v-card>
                </div>

                <!-- email, address -->
                <div class="d-flex flex-row justify-space-between">
                  <v-card width="30%" elevation="0" class="d-flex flex-column">
                    <p class="text-body-2 ma-0 font-weight-medium">Email:</p>
                    <v-text-field
                      readonly
                      solo
                      flat
                      background-color="#EEF2F6"
                    ></v-text-field>
                  </v-card>
                  <v-card width="65%" elevation="0" class="d-flex flex-column">
                    <p class="text-body-2 ma-0 font-weight-medium">Địa chỉ:</p>
                    <v-text-field
                      readonly
                      solo
                      flat
                      background-color="#EEF2F6"
                    ></v-text-field>
                  </v-card>
                </div>

                <!-- appointment profile -->
                <p class="font-weight-bold text-body-1">Thông tin đặt khám</p>
                <!--  -->
                <div class="d-flex flex-row justify-space-between">
                  <v-card width="65%" elevation="0" class="d-flex flex-column">
                    <p class="text-body-2 ma-0 font-weight-medium">
                      Triệu chứng:
                    </p>
                    <v-textarea
                      readonly
                      solo
                      flat
                      background-color="#EEF2F6"
                    ></v-textarea>
                  </v-card>
                </div>
                <p class="text-body-2 font-weight-medium">
                  Kết quả xét nghiệm:
                </p>
                <p class="text-body-2 font-weight-medium">
                  Chuẩn đoán hình ảnh:
                </p>
              </v-card>
            </v-tab-item>
            <!-- history -->
            <v-tab-item>
              <v-card flat>
                <v-card-text>
                  <p>
                    Morbi nec metus. Suspendisse faucibus, nunc et pellentesque
                    egestas, lacus ante convallis tellus, vitae iaculis lacus
                    elit id tortor. Sed mollis, eros et ultrices tempus, mauris
                    ipsum aliquam libero, non adipiscing dolor urna a orci.
                    Curabitur ligula sapien, tincidunt non, euismod vitae,
                    posuere imperdiet, leo. Nunc sed turpis.
                  </p>

                  <p>
                    Suspendisse feugiat. Suspendisse faucibus, nunc et
                    pellentesque egestas, lacus ante convallis tellus, vitae
                    iaculis lacus elit id tortor. Proin viverra, ligula sit amet
                    ultrices semper, ligula arcu tristique sapien, a accumsan
                    nisi mauris ac eros. In hac habitasse platea dictumst. Fusce
                    ac felis sit amet ligula pharetra condimentum.
                  </p>

                  <p>
                    Sed consequat, leo eget bibendum sodales, augue velit cursus
                    nunc, quis gravida magna mi a libero. Nam commodo suscipit
                    quam. In consectetuer turpis ut velit. Sed cursus turpis
                    vitae tortor. Aliquam eu nunc.
                  </p>

                  <p>
                    Etiam ut purus mattis mauris sodales aliquam. Ut varius
                    tincidunt libero. Aenean viverra rhoncus pede. Duis leo.
                    Fusce fermentum odio nec arcu.
                  </p>

                  <p class="mb-0">
                    Donec venenatis vulputate lorem. Aenean viverra rhoncus
                    pede. In dui magna, posuere eget, vestibulum et, tempor
                    auctor, justo. Fusce commodo aliquam arcu. Suspendisse enim
                    turpis, dictum sed, iaculis a, condimentum nec, nisi.
                  </p>
                </v-card-text>
              </v-card>
            </v-tab-item>
            <!-- examination -->
            <v-tab-item>
              <v-card flat class="pa-6 d-flex flex-column">
                <p class="d-flex justify-center font-weight-bold">KHÁM BỆNH</p>
                <div class="d-flex flex-row justify-space-between">
                  <v-card width="30%" elevation="0" class="d-flex flex-column">
                    <p class="text-body-2 ma-0 font-weight-medium">
                      Chuẩn đoán:
                    </p>
                    <v-textarea
                      solo
                      flat
                      background-color="#EEF2F6"
                    ></v-textarea>
                  </v-card>
                  <v-card width="30%" elevation="0" class="d-flex flex-column">
                    <p class="text-body-2 ma-0 font-weight-medium">Chỉ định:</p>
                    <v-textarea
                      solo
                      flat
                      background-color="#EEF2F6"
                    ></v-textarea>
                  </v-card>
                  <v-card width="30%" elevation="0" class="d-flex flex-column">
                    <p class="text-body-2 ma-0 font-weight-medium">Ghi chú:</p>
                    <v-textarea
                      solo
                      flat
                      background-color="#EEF2F6"
                    ></v-textarea>
                  </v-card>
                </div>
              </v-card>
            </v-tab-item>
            <!-- međicine -->
            <v-tab-item>
              <v-card flat class="pa-6 d-flex flex-column align-center">
                <p class="d-flex mt-6 justify-center font-weight-bold">
                  KÊ ĐƠN
                </p>
                <div class="d-flex flex-row justify-space-between">
                  <!-- from date -->
                  <v-card class="d-flex flex-column" width="30%" elevation="0">
                    <p class="mb-2 font-weight-medium text-body-2">Từ ngày:</p>
                    <v-card class="d-flex flex-row align-center" elevation="0">
                      <v-menu
                        ref="from_date_menu"
                        v-model="from_date_menu"
                        :close-on-content-click="false"
                        :return-value.sync="from_date"
                        transition="scale-transition"
                        offset-y
                        min-width="auto"
                      >
                        <template v-slot:activator="{ on, attrs }">
                          <v-combobox
                            flat
                            v-model="from_date"
                            hide-details=""
                            readonly
                            v-bind="attrs"
                            v-on="on"
                            solo
                            background-color="#EEF2F6"
                            full-width
                            append-icon="mdi-calendar-outline"
                          ></v-combobox>
                        </template>
                        <v-date-picker
                          v-model="from_date"
                          no-title
                          scrollable
                          locale="vi"
                          color="#537DA5"
                        >
                          <v-spacer></v-spacer>
                          <v-btn
                            text
                            color="primary"
                            @click="from_date_menu = false"
                          >
                            Cancel
                          </v-btn>
                          <v-btn
                            text
                            color="primary"
                            @click="$refs.from_date_menu.save(from_date)"
                          >
                            OK
                          </v-btn>
                        </v-date-picker>
                      </v-menu>
                    </v-card>
                  </v-card>
                  <!-- to date -->
                  <v-card class="d-flex flex-column" width="30%" elevation="0">
                    <p class="mb-2 font-weight-medium text-body-2">Đến ngày:</p>
                    <v-card class="d-flex flex-row align-center" elevation="0">
                      <v-menu
                        ref="to_date_menu"
                        v-model="to_date_menu"
                        :close-on-content-click="false"
                        :return-value.sync="to_date"
                        transition="scale-transition"
                        offset-y
                        min-width="auto"
                      >
                        <template v-slot:activator="{ on, attrs }">
                          <v-combobox
                            flat
                            v-model="to_date"
                            hide-details=""
                            readonly
                            v-bind="attrs"
                            v-on="on"
                            solo
                            background-color="#EEF2F6"
                            full-width
                            append-icon="mdi-calendar-outline"
                          ></v-combobox>
                        </template>
                        <v-date-picker
                          v-model="to_date"
                          no-title
                          scrollable
                          locale="vi"
                          color="#537DA5"
                        >
                          <v-spacer></v-spacer>
                          <v-btn
                            text
                            color="primary"
                            @click="to_date_menu = false"
                          >
                            Cancel
                          </v-btn>
                          <v-btn
                            text
                            color="primary"
                            @click="$refs.to_date_menu.save(to_date)"
                          >
                            OK
                          </v-btn>
                        </v-date-picker>
                      </v-menu>
                    </v-card>
                  </v-card>
                  <!-- empty card -->
                  <v-card width="30%" elevation="0"></v-card>
                </div>
                <p class="mb-2 mt-5 font-weight-medium text-body-2">
                  Danh sách thuốc:
                </p>
                <v-card class="pa-6" color="#EEF2F6" width="65%" elevation="0">
                  <v-data-table
                    :headers="headers"
                    :items="desserts"
                    class="elevation-1 font-weight-medium"
                    hide-default-footer
                  >
                    <template v-slot:[`item.actions`]="{ item }">
                      <v-icon small @click.stop="deleteItem(item)">
                        mdi-delete
                      </v-icon>
                    </template>
                  </v-data-table>
                </v-card>

                <div class="d-flex flex-row justify-space-between mt-5">
                  <v-card width="30%" elevation="0" class="d-flex flex-column">
                    <p class="text-body-2 ma-0 font-weight-medium">
                      Tên thuốc:
                    </p>
                    <v-text-field
                      solo
                      flat
                      background-color="#EEF2F6"
                    ></v-text-field>
                  </v-card>
                  <v-card width="30%" elevation="0" class="d-flex flex-column">
                    <p class="text-body-2 ma-0 font-weight-medium">Liều:</p>
                    <v-text-field
                      solo
                      flat
                      background-color="#EEF2F6"
                    ></v-text-field>
                  </v-card>
                  <v-card width="30%" elevation="0" class="d-flex flex-column">
                    <p class="text-body-2 ma-0 font-weight-medium">Tổng số:</p>
                    <v-text-field
                      solo
                      flat
                      background-color="#EEF2F6"
                    ></v-text-field>
                  </v-card>
                </div>

                <div class="d-flex flex-row justify-space-between">
                  <v-card width="30%" elevation="0" class="d-flex flex-column">
                    <p class="text-body-2 ma-0 font-weight-medium">
                      Sáng uống:
                    </p>
                    <v-text-field
                      solo
                      flat
                      background-color="#EEF2F6"
                    ></v-text-field>
                  </v-card>
                  <v-card width="30%" elevation="0" class="d-flex flex-column">
                    <p class="text-body-2 ma-0 font-weight-medium">
                      Chiều uống:
                    </p>
                    <v-text-field
                      solo
                      flat
                      background-color="#EEF2F6"
                    ></v-text-field>
                  </v-card>
                  <v-card width="30%" elevation="0" class="d-flex flex-column">
                    <p class="text-body-2 ma-0 font-weight-medium">Tối uống:</p>
                    <v-text-field
                      solo
                      flat
                      background-color="#EEF2F6"
                    ></v-text-field>
                  </v-card>
                </div>

                <div class="d-flex flex-row justify-space-between">
                  <v-card width="30%" elevation="0" class="d-flex flex-column">
                    <p class="text-body-2 ma-0 font-weight-medium">
                      Đơn vị uống:
                    </p>
                    <v-text-field
                      solo
                      flat
                      background-color="#EEF2F6"
                    ></v-text-field>
                  </v-card>
                </div>
                <div class="d-flex flex-row justify-center">
                  <v-btn
                    color="#537DA5"
                    class="white--text btn font-weight-medium text-body-2"
                    width="30%"
                    elevation="0"
                    >Thêm thuốc</v-btn
                  >
                </div>
              </v-card>
            </v-tab-item>
          </v-tabs>
        </v-card>
      </v-card>
    </v-dialog>
  </v-card>
</template>

<script>
export default {
  data() {
    return {
      selected: [],
      headers_appointment: [
        {
          text: "Họ và tên đệm",
          align: "start",
          sortable: false,
          value: "lastName",
        },
        { text: "Tên", value: "firstName" },
        { text: "Ngày sinh", value: "dob" },
        { text: "Giới tính", value: "gender" },
        { text: "Ngày khám", value: "date_medical_examination" },
        { text: "Khung giờ khám", value: "time" },
        { text: "Phòng", value: "room", sortable: false },
      ],
      list_appointment: [
        {
          id: "1",
          firstName: "Thanh",
          lastName: "Nguyễn Duy",
          dob: "03/03/2000",
          gender: "Nam",
          date_medical_examination: "20/10/2022",
          time: "13:00-14:00",
          room: {
            type: "online",
            name: "H1",
            link: "https://www.google.com/",
          },
        },
        {
          id: "2",
          firstName: "Thọ",
          lastName: "Hà Ngọc",
          dob: "03/03/2003",
          gender: "Nam",
          date_medical_examination: "20/10/2022",
          time: "13:00-14:00",
          room: {
            type: "offline",
            name: "H1",
            link: "https://www.google.com/",
          },
        },
        {
          id: "3",
          firstName: "Thúy",
          lastName: "Nguyễn Thị",
          dob: "03/03/2004",
          gender: "Nữ",
          date_medical_examination: "20/10/2022",
          time: "13:00-14:00",
          room: {
            type: "offline",
            name: "H1",
            link: "https://www.google.com/",
          },
        },
      ],
      exam_dialog: false,
      from_date_menu: false,
      from_date: "",
      to_date_menu: false,
      to_date: "",

      headers: [
        {
          text: "Tên",
          align: "start",
          sortable: false,
          value: "name",
        },
        { text: "Số lượng", value: "mount", sortable: false },
        { text: "Liều", value: "fat", sortable: false },
        { text: "Tác vụ", value: "actions", sortable: false },
      ],
      desserts: [
        {
          name: "Frozen Yogurt",
          mount: 159,
          fat: 6.0,
        },
        {
          name: "Ice cream sandwich",
          mount: 237,
          fat: 9.0,
        },
      ],
    };
  },
  methods: {
    examinate() {
      this.exam_dialog = true;
      console.log(item.firstName);
    },
  },
};
</script>

<style scoped>
.btn {
  text-transform: none;
}
.v-data-table > .v-data-table__wrapper > table > tbody > tr > th,
.v-data-table > .v-data-table__wrapper > table > thead > tr > th,
.v-data-table > .v-data-table__wrapper > table > tfoot > tr > th {
  font-size: 20px !important;
}
</style>
