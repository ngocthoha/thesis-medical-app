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
      <template v-slot:[`item.profile.dob`]="{ item }">
        <div class="d-flex flex-row align-center">
          <p class="ma-0">{{ convert_date(item.profile.dob) }}</p>
        </div>
      </template>
      <template v-slot:[`item.date`]="{ item }">
        <div class="d-flex flex-row align-center">
          <p class="ma-0">{{ convert_date(item.date) }}</p>
        </div>
      </template>
      <template v-slot:[`item.room`]="{ item }">
        <div class="d-flex flex-row align-center">
          <p class="ma-0">{{ item.room.name }}</p>
          <v-icon v-if="item.type === 'ONLINE'" class="ml-2"
            >mdi-video-outline</v-icon
          >
        </div>
      </template>
    </v-data-table>
    <v-dialog v-model="exam_confirm_dialog" width="400">
      <v-card height="150px" class="d-flex flex-column">
        <div class="d-flex flex-column mt-8 ml-8">
          <p style="font-size: 24px" class="font-weight-bold">
            Xác nhận hoàn tất khám
          </p>
        </div>
        <div class="d-flex flex-row justify-space-between ml-8 mr-8">
          <v-btn
            class="btn-not-transform font-weight-medium"
            width="160px"
            outlined
            color="#667085"
            text
            @click="exam_confirm_dialog = false"
            >Huỷ bỏ</v-btn
          >
          <v-btn
            class="btn-not-transform white--text font-weight-medium"
            width="160px"
            color="#537DA5"
            elevation="0"
            @click="create_record"
            >Hoàn tất</v-btn
          >
        </div>
      </v-card>
    </v-dialog>
    <v-dialog v-model="exam_dialog" fullscreen v-if="exam_dialog">
      <v-card>
        <v-footer
          color="#314E6A"
          class="white--text d-flex flex-row"
          height="50px"
          tile
        >
          <p class="ma-0">Giao diện khám bệnh</p>
          <v-spacer></v-spacer>
          <v-btn
            color="white"
            class="mr-5"
            elevation="0"
            outlined
            @click.stop="exam_confirm_dialog = true"
          >
            Lưu
          </v-btn>
          <v-btn
            color="white"
            elevation="0"
            outlined
            @click.stop="stop_examination()"
          >
            Hủy
          </v-btn>
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
                      class="text-body-2"
                      v-model="selected_appointment.profile.lastName"
                      readonly
                      solo
                      flat
                      background-color="#EEF2F6"
                    ></v-text-field>
                  </v-card>
                  <v-card width="30%" elevation="0" class="d-flex flex-column">
                    <p class="text-body-2 ma-0 font-weight-medium">Tên:</p>
                    <v-text-field
                      class="text-body-2"
                      v-model="selected_appointment.profile.firstName"
                      readonly
                      solo
                      flat
                      background-color="#EEF2F6"
                    ></v-text-field>
                  </v-card>
                  <v-card width="30%" elevation="0" class="d-flex flex-column">
                    <p class="text-body-2 ma-0 font-weight-medium">Ngày sinh</p>
                    <v-text-field
                      class="text-body-2"
                      v-model="selected_appointment.profile.dob"
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
                      class="text-body-2"
                      v-model="selected_appointment.profile.job"
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
                      class="text-body-2"
                      v-model="selected_appointment.profile.gender"
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
                      class="text-body-2"
                      v-model="selected_appointment.profile.phone"
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
                      class="text-body-2"
                      v-model="selected_appointment.profile.email"
                      readonly
                      solo
                      flat
                      background-color="#EEF2F6"
                    ></v-text-field>
                  </v-card>
                  <v-card width="65%" elevation="0" class="d-flex flex-column">
                    <p class="text-body-2 ma-0 font-weight-medium">Địa chỉ:</p>
                    <v-text-field
                      class="text-body-2"
                      v-model="selected_appointment.profile.address"
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
                      class="text-body-2"
                      v-model="selected_appointment.symptom"
                      readonly
                      solo
                      flat
                      background-color="#EEF2F6"
                    ></v-textarea>
                  </v-card>
                </div>
                <p class="text-body-2 font-weight-medium">
                  Hình ảnh đính kèm:
                </p>
                <div class="d-flex flex-wrap">
                  <v-card
                    elevation="0"
                    class="d-flex flex-row pa-2 mr-2"
                    color="#EEF2F6"
                    v-for="file in selected_appointment.files"
                    :key="file.id"
                  >
                    <div class="mr-5">
                      <p class="ma-0 text-body-2 font-weight-medium">
                        {{ get_text_of_type_file(file) }}
                      </p>
                      <p class="ma-0 text-body-2">
                        {{ get_name_of_file(file) }}
                      </p>
                    </div>
                    <v-icon
                      class="mr-2"
                      @click="view_file(file)"
                      v-if="is_img_file(file)"
                      >mdi-eye-outline</v-icon
                    >
                    <v-icon>mdi-download-outline</v-icon>
                  </v-card>
                </div>
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
                      v-model="record.diagnose"
                      solo
                      flat
                      background-color="#EEF2F6"
                    ></v-textarea>
                  </v-card>
                  <v-card width="30%" elevation="0" class="d-flex flex-column">
                    <p class="text-body-2 ma-0 font-weight-medium">Chỉ định:</p>
                    <v-textarea
                      v-model="record.prescribe"
                      solo
                      flat
                      background-color="#EEF2F6"
                    ></v-textarea>
                  </v-card>
                  <v-card width="30%" elevation="0" class="d-flex flex-column">
                    <p class="text-body-2 ma-0 font-weight-medium">Ghi chú:</p>
                    <v-textarea
                      v-model="record.note"
                      solo
                      flat
                      background-color="#EEF2F6"
                    ></v-textarea>
                  </v-card>
                </div>
                <p class="d-flex justify-center font-weight-bold">
                  THÊM KẾT QUẢ CẬN LÂM SÀNG
                </p>
                <div class="d-flex flex-row justify-space-between mt-5">
                  <v-card width="45%" class="d-flex flex-column" elevation="0">
                    <!-- test results type -->
                    <div
                      class="d-flex flex-row justify-space-between align-center"
                    >
                      <p class="font-weight-medium text-body-2 ma-0">
                        Kết quả xét nghiệm
                      </p>
                      <v-card width="221" elevation="0">
                        <v-combobox
                          spellcheck="false"
                          v-model="test_select"
                          :items="test_type"
                          item-text="name"
                          item-value="key"
                          solo
                          dense
                          flat
                          append-icon="mdi-chevron-down"
                          item-color="light-blue darken-4"
                          placeholder="Chọn loại xét nghiệm"
                          hide-details=""
                          background-color="#EEF2F6"
                        ></v-combobox>
                      </v-card>
                    </div>
                    <!-- add test result  -->
                    <v-card
                      class="my-5 pa-2 d-flex flex-row"
                      color="#EEF2F6"
                      elevation="0"
                    >
                      <v-file-input
                        placeholder="Vui lòng chọn hình ảnh"
                        solo
                        dense
                        prepend-inner-icon="mdi-link-variant"
                        clear-icon=""
                        prepend-icon=""
                        hide-details=""
                        flat
                        background-color="#EEF2F6"
                        v-model="test_file"
                      ></v-file-input>
                      <v-btn
                        class="btn white--text text-body-2"
                        color="#537DA5"
                        elevation="0"
                        @click="addTestFile"
                        >Tải lên</v-btn
                      >
                    </v-card>

                    <!-- list add test result -->
                    <div
                      class="d-flex flex-wrap justify-space-between align-center mb-5"
                      v-for="(image, index) in test_add_list"
                      :key="index"
                    >
                      <p
                        class="font-weight-medium text-body-2 ma-0"
                        style="color: #667085"
                      >
                        {{ image.type_name }}
                      </p>
                      <v-card
                        min-width="50%"
                        outlined
                        class="pa-3 d-flex flex-row"
                      >
                        <v-icon class="mr-2">mdi-image-outline</v-icon>
                        <v-card min-width="50%" elevation="0">
                          <p class="ma-0 text-body-2" style="">
                            {{ image.file_name }}
                          </p>
                        </v-card>

                        <v-spacer></v-spacer>
                        <v-icon @click="removeTestFile(index)"
                          >mdi-close</v-icon
                        >
                      </v-card>
                    </div>
                  </v-card>
                  <v-card width="45%" class="d-flex flex-column" elevation="0">
                    <!-- Image analysation type -->
                    <div
                      class="d-flex flex-row justify-space-between align-center"
                    >
                      <p class="font-weight-medium text-body-2 ma-0">
                        Chuẩn đoán hình ảnh
                      </p>
                      <v-card width="221" elevation="0">
                        <v-combobox
                          spellcheck="false"
                          v-model="image_select"
                          :items="image_analyst_type"
                          item-text="name"
                          item-value="key"
                          solo
                          dense
                          flat
                          append-icon="mdi-chevron-down"
                          item-color="light-blue darken-4"
                          placeholder="Chọn loại hình ảnh"
                          hide-details=""
                          background-color="#EEF2F6"
                        ></v-combobox>
                      </v-card>
                    </div>
                    <!-- add image analyst  -->
                    <v-card
                      color="#EEF2F6"
                      class="my-5 pa-2 d-flex flex-row"
                      elevation="0"
                    >
                      <v-file-input
                        placeholder="Vui lòng chọn hình ảnh"
                        solo
                        dense
                        prepend-inner-icon="mdi-link-variant"
                        clear-icon=""
                        prepend-icon=""
                        hide-details=""
                        flat
                        background-color="#EEF2F6"
                        v-model="image_file"
                      ></v-file-input>
                      <v-btn
                        class="btn white--text text-body-2"
                        color="#537DA5"
                        elevation="0"
                        @click="addImageAnalystFile"
                        >Tải lên</v-btn
                      >
                    </v-card>

                    <!-- list add image analyst-->
                    <div
                      class="d-flex flex-wrap justify-space-between align-center mb-5"
                      v-for="(image, index) in image_analyst_list"
                      :key="index + 3"
                    >
                      <p
                        class="font-weight-medium text-body-2 ma-0"
                        style="color: #667085"
                      >
                        {{ image.type_name }}
                      </p>
                      <v-card
                        min-width="50%"
                        outlined
                        class="pa-3 d-flex flex-row"
                      >
                        <v-icon class="mr-2">mdi-image-outline</v-icon>
                        <v-card min-width="50%" elevation="0">
                          <p class="ma-0 text-body-2" style="">
                            {{ image.file_name }}
                          </p>
                        </v-card>

                        <v-spacer></v-spacer>
                        <v-icon @click="removeImageFile(index)"
                          >mdi-close</v-icon
                        >
                      </v-card>
                    </div>
                  </v-card>
                </div>
              </v-card>
            </v-tab-item>
            <!-- međicine -->
            <v-tab-item>
              <v-card flat class="pa-6 d-flex flex-column">
                <p class="d-flex mt-6 justify-center font-weight-bold">
                  KÊ ĐƠN
                </p>
                <div class="d-flex flex-row justify-space-around">
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
                </div>
                <div class="d-flex flex-column align-center">
                  <v-card width="80%" elevation="0">
                    <p class="mb-2 mt-5 font-weight-medium text-body-2">
                      Danh sách thuốc:
                    </p>
                  </v-card>

                  <v-card
                    class="pa-6"
                    color="#EEF2F6"
                    width="80%"
                    elevation="0"
                  >
                    <div class="d-flex flex-row pb-6">
                      <v-btn
                        class="btn mr-3 white--text"
                        color="#537DA5"
                        elevation="0"
                        @click.stop="open_add_medicine_dialog"
                        >Thêm</v-btn
                      >
                      <v-btn
                        class="btn mr-3 white--text"
                        color="#667085"
                        elevation="0"
                        @click.stop="open_edit_medicine_dialog"
                        >Chỉnh sửa</v-btn
                      >
                      <v-btn
                        class="btn white--text"
                        color="#F04438"
                        elevation="0"
                        @click="remove_medicine_to_prescriptions"
                        >Xóa</v-btn
                      >
                    </div>
                    <v-data-table
                      :headers="prescriptions_header"
                      :items="prescriptions"
                      class="elevation-1"
                      single-select
                      show-select
                      v-model="medicine_select"
                      item-key="data.name"
                      checkbox-color="#3C5E7E"
                      hide-default-footer
                      no-data-text="Đơn thuốc trống"
                    >
                      <template v-slot:[`item.use`]="{ item }">
                        <div class="d-flex flex-row align-center">
                          <p class="ma-0">
                            {{ convert_to_use(item) }}
                          </p>
                        </div>
                      </template>
                      <template v-slot:[`item.name`]="{ item }">
                        <div class="d-flex flex-row align-center">
                          <p class="ma-0">
                            {{ item.data.name }}
                          </p>
                        </div>
                      </template>
                    </v-data-table>
                  </v-card>
                </div>

                <v-dialog v-model="medicine_dialog" width="500">
                  <v-card class="d-flex flex-column pa-8">
                    <v-card elevation="0" class="d-flex flex-column mb-5">
                      <p class="text-body-2 ma-0 font-weight-medium">
                        Tên thuốc:
                      </p>

                      <v-combobox
                        spellcheck="false"
                        v-model="medicine.data"
                        :items="store_medicine_list"
                        item-text="name"
                        solo
                        flat
                        append-icon="mdi-chevron-down"
                        item-color="light-blue darken-4"
                        placeholder="Chọn thuốc"
                        hide-details=""
                        background-color="#EEF2F6"
                      ></v-combobox>
                    </v-card>
                    <v-card elevation="0" class="d-flex flex-column">
                      <p class="text-body-2 ma-0 font-weight-medium">
                        Đơn vị uống:
                      </p>
                      <v-text-field
                        solo
                        flat
                        background-color="#EEF2F6"
                        v-model="medicine.unit"
                      ></v-text-field>
                    </v-card>
                    <v-card elevation="0" class="d-flex flex-column">
                      <p class="text-body-2 ma-0 font-weight-medium">
                        Sáng uống:
                      </p>
                      <v-text-field
                        type="number"
                        solo
                        flat
                        background-color="#EEF2F6"
                        v-model="medicine.morning"
                      ></v-text-field>
                    </v-card>
                    <v-card elevation="0" class="d-flex flex-column">
                      <p class="text-body-2 ma-0 font-weight-medium">
                        Chiều uống:
                      </p>
                      <v-text-field
                        type="number"
                        solo
                        flat
                        v-model="medicine.afternoon"
                        background-color="#EEF2F6"
                      ></v-text-field>
                    </v-card>
                    <v-card elevation="0" class="d-flex flex-column">
                      <p class="text-body-2 ma-0 font-weight-medium">
                        Tối uống:
                      </p>
                      <v-text-field
                        type="number"
                        solo
                        flat
                        background-color="#EEF2F6"
                        v-model="medicine.evening"
                      ></v-text-field>
                    </v-card>
                    <v-card class="d-flex" elevation="0">
                      <v-btn
                        class="btn white--text"
                        color="#F04438"
                        elevation="0"
                        @click.stop="medicine_dialog = false"
                        >Hủy</v-btn
                      >
                      <v-spacer></v-spacer>
                      <v-btn
                        v-if="medicine_dialog_type == 1"
                        color="#6D91B3"
                        class="white--text btn font-weight-medium"
                        elevation="0"
                        @click="add_medicine_to_prescriptions"
                        >Thêm</v-btn
                      >
                      <v-btn
                        v-if="medicine_dialog_type == 2"
                        color="#6D91B3"
                        class="white--text btn font-weight-medium"
                        elevation="0"
                        @click="medicine_dialog = false"
                        >Chỉnh sửa</v-btn
                      >
                    </v-card>
                  </v-card>
                </v-dialog>
              </v-card>
            </v-tab-item>
          </v-tabs>
        </v-card>
      </v-card>
    </v-dialog>
  </v-card>
</template>

<script>
import axios from "axios";
const url = process.env.VUE_APP_ROOT_API;
export default {
  async created() {
    await this.get_appointment();
    this.list_appointment = this.all_appointment_list;
  },

  data() {
    return {
      selected: [],
      headers_appointment: [
        {
          text: "Họ và tên đệm",
          align: "start",
          sortable: false,
          value: "profile.lastName"
        },
        { text: "Tên", value: "profile.firstName" },
        { text: "Ngày sinh", value: "profile.dob" },
        { text: "Ngày khám", value: "date" },
        { text: "Khung giờ khám", value: "time" },
        { text: "Phòng", value: "room", sortable: false }
      ],
      list_appointment: [],
      selected_appointment: {
        id: "",
        code: 0,
        room: {
          id: "",
          name: "",
          link: null
        },
        profile: {
          id: "",
          profileNumber: 0,
          firstName: "",
          lastName: "",
          address: "",
          phone: "",
          email: "",
          dob: "",
          job: "",
          identityCard: "",
          healthInsurance: "",
          folk: "",
          gender: "",
          guardian: "",
          guardianPhone: "",
          guardianIdentityCard: "",
          relationship: "",
          relationshipWithPatient: "",
          imageUrl: null
        },
        doctor: {
          id: "",
          name: "",
          email: "",
          specialty: "",
          hospital: {
            id: "",
            name: "",
            address: {
              id: "",
              country: "",
              province: "",
              district: "",
              ward: "",
              address: ""
            },
            info: "",
            registrationNumber: 0,
            favorite: 0.0,
            mapImageUrl: null,
            imageUrl: "",
            isActive: true,
            hospitalHour: {
              id: "",
              mondayTime: "",
              mondayTimeBreak: "",
              tuesdayTime: "",
              tuesdayTimeBreak: "",
              wednesdayTime: "",
              wednesdayTimeBreak: "",
              thursdayTime: "",
              thursdayTimeBreak: "",
              fridayTime: "",
              fridayTimeBreak: "",
              saturdayTime: "",
              saturdayTimeBreak: "",
              sundayTime: "",
              sundayTimeBreak: ""
            }
          },
          level: "",
          bio: "",
          registrationNumber: 0,
          price: "",
          imageUrl: null,
          favorite: 0
        },
        service: null,
        date: "",
        time: "",
        symptom: "",
        files: [
          {
            id: "",
            imageUrl: "",
            type: ""
          }
        ],
        status: "",
        type: "",
        isPaid: true,
        fee: "",
        category: ""
      },
      all_appointment_list: [],
      exam_dialog: false,
      from_date_menu: false,
      from_date: "",
      to_date_menu: false,
      to_date: "",

      prescriptions_header: [
        {
          text: "Tên",
          align: "start",
          sortable: false,
          value: "name"
        },
        { text: "Số lượng", value: "mount", sortable: false },
        { text: "Liều", value: "use", sortable: false },
        { text: "Đơn vị uống", value: "unit", sortable: false }
      ],
      prescriptions: [],
      medicine_dialog: false,
      medicine_dialog_type: 1,
      store_medicine_list: [],
      medicine: {
        data: {
          name: "",
          id: ""
        },
        unit: "",
        morning: 0,
        afternoon: 0,
        evening: 0
      },
      medicine_select: [],
      test_type: [
        { name: "Xét nghiệm máu", key: "BLOOD_TEST" },
        { name: "Xét nghiệm nước tiểu", key: "URINE_TEST" },
        { name: "Xét nghiệm khác", key: "DIFFERENT_TEST" }
      ],
      test_select: "",
      test_file: {},
      test_add_list: [],
      image_select: "",
      image_analyst_type: [
        { name: "CT", key: "CT_SCAN" },
        { name: "X-quang", key: "X_RAY " },
        { name: "PET", key: "PET_SCAN" },
        { name: "Siêu âm", key: "SUPERSONIC" },
        { name: "MRI", key: "MRI" },
        { name: "Hình ảnh khác", key: "DIFFERENT_IMAGE" }
      ],
      image_analyst_list: [],
      image_file: {},
      submit_file_list: [],
      record: {
        diagnose: "",
        prescribe: "",
        note: "",
        reExaminationDate: ""
      },
      exam_confirm_dialog: false,
      image_file_accept: [
        "wmf",
        "webp",
        "tga",
        "emf",
        "ai",
        "tif",
        "bmp",
        "gif",
        "jpg",
        "ps",
        "jpeg",
        "ufo",
        "pdf",
        "eps",
        "svg",
        "png",
        "psd"
      ]
    };
  },
  methods: {
    async examinate() {
      const data = this.selected[0];
      this.selected_appointment = JSON.parse(JSON.stringify(data));
      this.selected_appointment.profile.dob = this.convert_date(
        data.profile.dob
      );
      switch (data.profile.gender) {
        case "MALE":
          this.selected_appointment.profile.gender = "Nam";
          break;
        case "FEMALE":
          this.selected_appointment.profile.gender = "Nữ";
          break;
        default:
          this.selected_appointment.profile.gender = "Khác";
        // code block
      }

      this.selected_appointment.profile.address = this.getAddress(data.profile);
      let token = this.$store.getters["auth/access_token"];

      const params = {
        token: token,
        data: {
          hospitalId: this.selected_appointment.doctor.hospital.id
        }
      };
      await this.$store.dispatch(
        "medicine/get_store_medicine_by_hospital",
        params
      );
      this.store_medicine_list = this.$store.getters[
        "medicine/store_medicine_by_hospital"
      ];
      this.exam_dialog = true;
    },
    open_add_medicine_dialog() {
      this.medicine_dialog_type = 1;
      this.medicine = {
        data: "",
        unit: "",
        count: 0,
        morning: 0,
        afternoon: 0,
        evening: 0
      };
      this.medicine_dialog = true;
    },
    open_edit_medicine_dialog() {
      this.medicine_dialog_type = 2;
      this.medicine = this.medicine_select[0];
      this.medicine_dialog = this.medicine_dialog = true;
    },

    convert_to_use(item) {
      let use = "";

      if (item.morning != 0) {
        if (use.length == 0) {
          use = use + "Sáng: " + item.morning;
        } else {
          use = use + ", Sáng: " + item.morning;
        }
      }

      if (item.afternoon != 0) {
        if (use.length == 0) {
          use = use + "Chiều: " + item.afternoon;
        } else {
          use = use + ", Chiều: " + item.afternoon;
        }
      }
      if (item.evening != 0) {
        if (use.length == 0) {
          use = use + "Tối: " + item.evening;
        } else {
          use = use + ", Tối: " + item.evening;
        }
      }
      return use;
    },
    add_medicine_to_prescriptions() {
      this.prescriptions.push({
        data: this.medicine.data,
        unit: this.medicine.unit,
        morning: this.medicine.morning,
        afternoon: this.medicine.afternoon,
        evening: this.medicine.evening
      });
      this.medicine_dialog = false;
    },

    remove_medicine_to_prescriptions() {
      let index = this.prescriptions.indexOf(this.medicine_select[0]);

      this.prescriptions.splice(index, 1);
      this.medicine_dialog = false;
    },

    removeTestFile(index) {
      this.test_add_list.splice(index, 1);
    },

    removeImageFile(index) {
      this.image_analyst_list.splice(index, 1);
    },

    addTestFile() {
      this.test_add_list.push({
        type: this.test_select.key,
        type_name: this.test_select.name,
        file_name: this.test_file.name,
        data: this.test_file
      });
    },

    addImageAnalystFile() {
      this.image_analyst_list.push({
        type: this.image_select.key,
        type_name: this.image_select.name,
        file_name: this.image_file.name,
        data: this.image_file
      });
    },

    async get_appointment() {
      let token = this.$store.getters["auth/access_token"];
      let data = {
        date: "2022-11-06"
      };
      const param = {
        token: token,
        data: data
      };
      await this.$store.dispatch(
        "appointment/get_appointment_by_doctor",
        param
      );
      this.all_appointment_list = this.$store.getters[
        "appointment/doctor_appointment_list"
      ];
    },

    convert_date(time) {
      let date = new Date(time);
      return (
        (date.getDate() > 9 ? date.getDate() : "0" + date.getDate()) +
        "/" +
        (date.getMonth() > 8
          ? date.getMonth() + 1
          : "0" + (date.getMonth() + 1)) +
        "/" +
        date.getFullYear()
      );
    },

    getAddress(profile) {
      if (profile.address == null) return "";
      return (
        profile.address.address +
        ", " +
        profile.address.ward +
        ", " +
        profile.address.district +
        ", " +
        profile.address.province
      );
    },

    async create_record() {
      let post_file_list = this.test_add_list.concat(this.image_analyst_list);
      await Promise.all(
        post_file_list.map(async file => {
          await this.post_file(file.data, file.type);
        })
      );
      let token = this.$store.getters["auth/access_token"];

      let medicine_list_submit = [];
      this.prescriptions.forEach(medicine => {
        medicine_list_submit.push({
          storeMedicineId: medicine.data.id,
          unit: medicine.unit,
          numMedicineInMorning: medicine.morning,
          numMedicineInAfternoon: medicine.afternoon,
          numMedicineInEvening: medicine.evening,
          dateStart: this.from_date,
          dateEnd: this.to_date
        });
      });
      const params = {
        token: token,
        data: {
          appointmentId: this.selected_appointment.id,
          diagnose: this.record.diagnose,
          prescribe: this.record.prescribe,
          reExaminationDate: this.record.reExaminationDate,
          medicines: medicine_list_submit,
          files: this.submit_file_list
        }
      };
      this.$store.dispatch("record/create_record", params);
      this.refresh_exam_data();
      this.exam_confirm_dialog = false;
      this.exam_dialog = false;
    },

    async post_file(file, type) {
      let form_data = new FormData();
      form_data.append("file", file);
      let token = this.$store.getters["auth/access_token"];

      axios.defaults.headers.common = {
        Authorization: `Bearer ${token}`
      };
      await axios
        .post(`${url}/api/files/upload`, form_data, {
          headers: {
            "Content-Type": "multipart/form-data"
          }
        })
        .then(response => {
          if (response.data.code === 200) {
            this.submit_file_list.push({
              imageUrl: response.data.results,
              type: type
            });
          }
        });
    },

    refresh_exam_data() {
      this.from_date = "";
      this.to_date = "";
      this.record.diagnose = "";
      this.record.prescribe = "";
      this.record.note = "";
      this.record.reExaminationDate = "";
      this.prescriptions = [];
    },

    stop_examination() {
      this.refresh_exam_data();
      this.exam_dialog = false;
    },

    get_text_of_type_file(file) {
      let type_name = "";
      this.test_type.forEach(file_type => {
        if (file_type.key === file.type) type_name = file_type.name;
      });
      this.image_analyst_type.forEach(file_type => {
        if (file_type.key === file.type) type_name = file_type.name;
      });
      return type_name;
    },

    get_name_of_file(file) {
      let temp_array = file.imageUrl.split("/");
      return temp_array[temp_array.length - 1];
    },
    view_file(file) {
      window.open(file.imageUrl);
    },
    is_img_file(file) {
      let temp_array = file.imageUrl.split("/");
      let name_file = temp_array[temp_array.length - 1];
      let temp_array_1 = name_file.split(".");
      let result = false;
      if (this.image_file_accept.includes(temp_array_1[1])) result = true;
      return result;
    },
    down_load_file() {}
  }
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
