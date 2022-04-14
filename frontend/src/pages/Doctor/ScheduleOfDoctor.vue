<template>
  <div class="content">
    <div class="md-layout">
      <div
        class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-30"
      >
        <div class="lich">
          <v-app id="inspire" style="max-height: 400px">
            <v-row justify="center" style="margin: 20px 0px">
              <v-date-picker
                v-model="picker"
                elevation="10"
                flat
                @click:date="getAppointmentList"
              ></v-date-picker>
            </v-row>
          </v-app>
        </div>
        <!-- <br />
        <h5><b>Sự kiện sắp diễn ra</b></h5>
        <br />
        <div class="thongbao">
          <md>
            <md-card v-for="(note, index) in note1" :key="index">
              <md-card-header>
                <div class="md-title">{{ note.num }} Bệnh nhân đặt lịch</div>
              </md-card-header>

              <md-card-content> Thời gian: {{ note.time }} </md-card-content>

              <md-card-actions>
                <md-button>Xem thêm</md-button>
              </md-card-actions>
            </md-card>
          </md>
        </div>
        <br />
        <br />
        <div class="thongbao">
          <md>
            <md-card v-for="(note, index) in note2" :key="index">
              <md-card-header>
                <div class="md-title">Tổng số bệnh nhân khám</div>
              </md-card-header>

              <md-card-content>
                <p>Ngày: {{ note.time }}</p>
                Số lượng: {{ note.num }}
              </md-card-content>

              <md-card-actions>
                <md-button>Xem thêm</md-button>
              </md-card-actions>
            </md-card>
          </md>
        </div> -->
      </div>

      <div
        class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-65"
      >
        <div>
          <h5>
            <b>Danh sách khám online trong ngày {{ picker }}</b>
          </h5>
        </div>
        <md-button
          class="md-primary"
          style="
            background-color: rgb(68 138 255) !important;
            margin-right: 20px;
            color: #ffffff !important;
          "
          @click="all"
          >Mở tất cả</md-button
        >
        <md-button
          class="md-primary"
          style="
            background-color: rgb(68 138 255) !important;
            margin-right: 20px;
            color: #ffffff !important;
          "
          @click="none"
          >Đóng tất cả</md-button
        >

        <md-button
          class="md-primary"
          style="
            background-color: rgb(68 138 255) !important;
            margin-right: 20px;
            color: #ffffff !important;
          "
          >Chuyển lịch</md-button
        >

        <br />
        <br />
        <v-expansion-panels focusable v-model="panel" multiple>
          <v-expansion-panel v-for="(item, i) in appointmentList" :key="i">
            <v-expansion-panel-header
              >{{ item.time }}
            </v-expansion-panel-header>
            <v-expansion-panel-content
              v-for="(appointment, j) in item.appointments"
              :key="j"
            >
              <v-container>
                <v-row>
                  <v-col cols="2" class="align-self-center"
                    ><span class="tag mr-2">STT:</span>
                    {{ appointment.stt }}</v-col
                  >
                  <v-col cols="4" class="align-self-center"
                    ><span class="tag mr-2">Họ tên:</span>
                    {{ appointment.profile.name }}</v-col
                  >
                  <v-col cols="2" class="align-self-center"
                    ><span class="tag mr-2">Phòng:</span>
                    {{ appointment.room.name }}</v-col
                  >
                  <v-col cols="3" class="d-flex">
                    <v-btn
                      color="success"
                      @click="openAppointmentDetail(appointment)"
                    >
                      Chi tiết
                    </v-btn>
                    <v-btn
                      class="ml-2"
                      color="primary"
                      @click="openDialogCreateRecord(appointment)"
                    >
                      Tạo bệnh án
                    </v-btn>
                  </v-col>
                </v-row>
              </v-container>
              <v-divider></v-divider>
            </v-expansion-panel-content>
          </v-expansion-panel>
        </v-expansion-panels>

        <v-dialog v-model="dialog" scrollable max-width="800px" persistent>
          <v-card>
            <v-card-title style="justify-content: space-between">
              <span>Thông tin bệnh nhân</span>
              <v-btn icon @click="closeDialogDetail()">
                <v-icon>mdi-close</v-icon>
              </v-btn>
            </v-card-title>
            <v-divider></v-divider>
            <v-card-text>
              <v-row>
                <v-col cols="6">
                  <v-card flat>
                    <v-card-text>
                      <div class="row">
                        <span class="col-4">Họ và tên:</span>
                        <strong class="col-8"
                          ><span>
                            {{ _.get(appointment, "profile.name") }}</span
                          ></strong
                        >
                      </div>
                      <div class="row">
                        <span class="col-4">Giới tính:</span>
                        <strong class="col-8"
                          ><span>{{
                            _.get(appointment, "profile.gender")
                          }}</span></strong
                        >
                      </div>
                      <div class="row">
                        <span class="col-4">Ngày sinh:</span>
                        <strong class="col-8"
                          ><span>{{
                            _.get(appointment, "profile.dob")
                          }}</span></strong
                        >
                      </div>
                      <div class="row">
                        <span class="col-4">Số điện thoại:</span>
                        <strong class="col-8"
                          ><span>{{
                            _.get(appointment, "profile.phoneNumber")
                          }}</span></strong
                        >
                      </div>
                      <div class="row">
                        <span class="col-4">Địa chỉ:</span>
                        <strong class="col-8"
                          ><span>{{
                            _.get(appointment, "profile.address")
                          }}</span></strong
                        >
                      </div>
                      <div class="row">
                        <span class="col-4">Nghề nghiệp:</span>
                        <strong class="col-8"
                          ><span>{{
                            _.get(appointment, "profile.job")
                          }}</span></strong
                        >
                      </div>
                      <div class="row">
                        <span class="col-4">CCCD:</span>
                        <strong class="col-8"
                          ><span>{{
                            _.get(appointment, "profile.identityCard")
                          }}</span></strong
                        >
                      </div>
                      <div class="row">
                        <span class="col-4">Bảo hiểm y tế:</span>
                        <strong class="col-8"
                          ><span>{{
                            _.get(appointment, "profile.healthInsurance")
                          }}</span></strong
                        >
                      </div>
                      <div class="row">
                        <span class="col-4">Dân tộc:</span>
                        <strong class="col-8"
                          ><span>{{
                            _.get(appointment, "profile.folk")
                          }}</span></strong
                        >
                      </div>
                      <div class="row">
                        <span class="col-4">Người bảo hộ:</span>
                        <strong class="col-8"
                          ><span>{{
                            _.get(appointment, "profile.protector")
                          }}</span></strong
                        >
                      </div>
                    </v-card-text>
                  </v-card>
                </v-col>
                <v-col cols="6">
                  <v-card flat>
                    <v-card-text>
                      <div class="row">
                        <span class="col-5">Mã bệnh nhân:</span>
                        <strong class="col-7"
                          ><span>
                            {{
                              _.get(appointment, "profile.profileNumber")
                            }}</span
                          ></strong
                        >
                      </div>
                      <div class="row">
                        <span class="col-5">Ngày khám:</span>
                        <strong class="col-7"
                          ><span>
                            {{ _.get(appointment, "date") }}</span
                          ></strong
                        >
                      </div>
                      <div class="row">
                        <span class="col-5">Số thứ tự:</span>
                        <strong class="col-7"
                          ><span>{{ _.get(appointment, "stt") }}</span></strong
                        >
                      </div>
                      <div class="row">
                        <span class="col-5">Phòng:</span>
                        <strong class="col-7"
                          ><span>{{
                            _.get(appointment, "room.name")
                          }}</span></strong
                        >
                      </div>
                      <div class="row">
                        <span class="col-5">Link phòng:</span>
                        <strong class="col-7"
                          ><span>{{
                            _.get(appointment, "room.link") || "--"
                          }}</span></strong
                        >
                      </div>
                      <div class="row">
                        <span class="col-5">Triệu chứng:</span>
                        <strong class="col-7"
                          ><span>{{
                            _.get(appointment, "symptom")
                          }}</span></strong
                        >
                      </div>
                      <div class="row">
                        <span class="col-5">Mô tả chi tiết:</span>
                        <strong class="col-7"
                          ><span>{{
                            _.get(appointment, "description")
                          }}</span></strong
                        >
                      </div>
                      <div class="row">
                        <span class="col-5">Thời gian diễn ra:</span>
                        <strong class="col-7"
                          ><span>{{
                            _.get(appointment, "timeSituation")
                          }}</span></strong
                        >
                      </div>
                      <div class="row">
                        <span class="col-5">Tự điều trị:</span>
                        <strong class="col-7"
                          ><span>{{
                            _.get(appointment, "selfTreatment")
                          }}</span></strong
                        >
                      </div>
                      <div class="row">
                        <span class="col-5">Ngày tạo:</span>
                        <strong class="col-7"
                          ><span>{{
                            _.get(appointment, "createdDate")
                          }}</span></strong
                        >
                      </div>
                    </v-card-text>
                  </v-card>
                </v-col>
              </v-row>
            </v-card-text>
            <v-divider></v-divider>
            <v-card-actions style="flex-direction: row-reverse">
              <v-btn depressed color="error" @click="closeDialogDetail()">
                Đóng
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>

        <v-bottom-sheet v-model="dialogRecord" scrollable persistent>
          <v-form ref="form">
            <v-card height="80vh">
              <v-card-title style="justify-content: space-between">
                <span>Tạo Bệnh Án</span>
                <div>
                  <v-btn
                    outlined
                    tile
                    color="primary"
                    class="mr-2"
                    @click="backStep()"
                    :disabled="stepper === 1"
                    >Quay lại</v-btn
                  >
                  <v-btn
                    :disabled="stepper === 3"
                    tile
                    color="primary"
                    class="mr-2"
                    @click="nextStep()"
                    >Tiếp tục</v-btn
                  >
                  <v-btn
                    :disabled="stepper !== 3"
                    tile
                    color="primary"
                    @click="saveRecord"
                    :loading="loading"
                  >
                    Lưu bệnh án
                  </v-btn>
                  <v-btn icon @click="closeForm()">
                    <v-icon>mdi-close</v-icon>
                  </v-btn>
                </div>
              </v-card-title>
              <v-card-text class="overflow-y-auto body-content">
                <v-stepper v-model="stepper">
                  <v-stepper-header>
                    <v-stepper-step :complete="stepper > 1" step="1"
                      >Thông tin bệnh nhân
                    </v-stepper-step>

                    <v-divider></v-divider>

                    <v-stepper-step :complete="stepper > 2" step="2">
                      Chuẩn đoán bệnh
                    </v-stepper-step>

                    <v-divider></v-divider>

                    <v-stepper-step step="3"> Kê đơn </v-stepper-step>
                  </v-stepper-header>

                  <v-stepper-items>
                    <v-stepper-content flat step="1">
                      <v-card flat>
                        <v-card-text height="70%">
                          <v-row>
                            <v-col cols="3">
                              <v-card flat>
                                <v-card-title primary-title>
                                  Thông tin lịch hẹn
                                </v-card-title>
                                <v-card-text>
                                  <div class="row">
                                    <span class="col-5">Mã bệnh nhân:</span>
                                    <strong class="col-7"
                                      ><span>
                                        {{
                                          _.get(
                                            appointment,
                                            "profile.profileNumber"
                                          )
                                        }}</span
                                      ></strong
                                    >
                                  </div>
                                  <div class="row">
                                    <span class="col-5">Ngày khám:</span>
                                    <strong class="col-7"
                                      ><span>
                                        {{ _.get(appointment, "date") }}</span
                                      ></strong
                                    >
                                  </div>
                                  <div class="row">
                                    <span class="col-5">Số thứ tự:</span>
                                    <strong class="col-7"
                                      ><span>{{
                                        _.get(appointment, "stt")
                                      }}</span></strong
                                    >
                                  </div>
                                  <div class="row">
                                    <span class="col-5">Phòng:</span>
                                    <strong class="col-7"
                                      ><span>{{
                                        _.get(appointment, "room.name")
                                      }}</span></strong
                                    >
                                  </div>
                                  <div class="row">
                                    <span class="col-5">Link phòng:</span>
                                    <strong class="col-7"
                                      ><span>{{
                                        _.get(appointment, "room.link") || "--"
                                      }}</span></strong
                                    >
                                  </div>
                                  <div class="row">
                                    <span class="col-5">Ngày tạo:</span>
                                    <strong class="col-7"
                                      ><span>{{
                                        _.get(appointment, "createdDate") ||
                                        "--"
                                      }}</span></strong
                                    >
                                  </div>
                                </v-card-text>
                              </v-card>
                            </v-col>
                            <v-col cols="9">
                              <v-card class="mb-6" flat>
                                <v-card-title>Thông tin cá nhân</v-card-title>
                                <v-card-text>
                                  <v-row>
                                    <v-col cols="3">
                                      <v-text-field
                                        v-model="appointment.profile.name"
                                        label="Họ và tên"
                                        readonly
                                      ></v-text-field>
                                    </v-col>
                                    <v-col cols="3">
                                      <v-text-field
                                        v-model="appointment.profile.gender"
                                        label="Giới tính"
                                        readonly
                                      ></v-text-field>
                                    </v-col>
                                    <v-col cols="3">
                                      <v-text-field
                                        v-model="appointment.profile.dob"
                                        label="Ngày sinh"
                                        append-icon="mdi-calendar"
                                        readonly
                                      ></v-text-field>
                                    </v-col>
                                    <v-col cols="3">
                                      <v-text-field
                                        v-model="
                                          appointment.profile.phoneNumber
                                        "
                                        label="Số điện thoại"
                                        append-icon="smartphone"
                                        readonly
                                      ></v-text-field>
                                    </v-col>
                                  </v-row>
                                  <v-row>
                                    <v-col cols="3">
                                      <v-text-field
                                        v-model="appointment.profile.address"
                                        label="Địa chỉ"
                                        append-icon="business"
                                        readonly
                                      ></v-text-field>
                                    </v-col>
                                    <v-col cols="3">
                                      <v-text-field
                                        v-model="appointment.profile.job"
                                        label="Nghề nghiệp"
                                        readonly
                                      ></v-text-field>
                                    </v-col>
                                    <v-col cols="3">
                                      <v-text-field
                                        v-model="
                                          appointment.profile.identityCard
                                        "
                                        label="CCCD"
                                        readonly
                                      ></v-text-field>
                                    </v-col>
                                    <v-col cols="3">
                                      <v-text-field
                                        v-model="
                                          appointment.profile.healthInsurance
                                        "
                                        label="Bảo hiểm y tế"
                                        readonly
                                      ></v-text-field>
                                    </v-col>
                                  </v-row>
                                  <v-row>
                                    <v-col cols="3">
                                      <v-text-field
                                        v-model="appointment.profile.folk"
                                        label="Dân tộc"
                                        readonly
                                      ></v-text-field>
                                    </v-col>
                                    <v-col cols="3">
                                      <v-text-field
                                        v-model="appointment.profile.protector"
                                        label="Người bảo hộ"
                                        readonly
                                      ></v-text-field>
                                    </v-col>
                                    <v-col cols="3"> </v-col>
                                    <v-col cols="3"> </v-col>
                                  </v-row>
                                </v-card-text>
                              </v-card>
                              <v-card flat>
                                <v-card-title>Triệu chứng</v-card-title>
                                <v-card-text>
                                  <v-row>
                                    <v-col cols="3">
                                      <v-textarea
                                        outline
                                        rows="1"
                                        v-model="appointment.symptom"
                                        label="Triệu chứng"
                                        readonly
                                      ></v-textarea>
                                    </v-col>
                                    <v-col cols="3">
                                      <v-textarea
                                        outline
                                        rows="1"
                                        v-model="appointment.description"
                                        label="Mô tả chi tiết"
                                        readonly
                                      ></v-textarea>
                                    </v-col>
                                    <v-col cols="3">
                                      <v-text-field
                                        v-model="appointment.timeSituation"
                                        label="Thời gian diễn ra"
                                        append-icon="mdi-calendar"
                                        readonly
                                      ></v-text-field>
                                    </v-col>
                                    <v-col cols="3">
                                      <v-checkbox
                                        v-model="appointment.selfTreatment"
                                        :label="'Đã tự điều trị'"
                                        readonly
                                      ></v-checkbox>
                                    </v-col>
                                  </v-row>
                                </v-card-text>
                              </v-card>
                            </v-col>
                          </v-row>
                        </v-card-text>
                      </v-card>
                    </v-stepper-content>

                    <v-stepper-content flat step="2">
                      <v-card flat class="mb-12">
                        <v-card-title></v-card-title>
                        <v-card-text>
                          <v-row>
                            <v-col cols="3">
                              <v-textarea
                                v-model="record.pathological"
                                label="Quá trình bệnh lí"
                                rows="2"
                              ></v-textarea>
                            </v-col>
                            <v-col cols="3">
                              <v-textarea
                                v-model="record.personalMedicalHistory"
                                label="Tiền sử bệnh bản thân"
                                rows="2"
                              ></v-textarea>
                            </v-col>
                            <v-col cols="3">
                              <v-textarea
                                v-model="record.familyMedicalHistory"
                                label="Tiền sử bệnh gia đình"
                                rows="2"
                              ></v-textarea>
                            </v-col>
                            <v-col cols="3">
                              <v-textarea
                                v-model="record.bodyInspection"
                                label="Khám xét toàn thân"
                                rows="2"
                              ></v-textarea>
                            </v-col>
                          </v-row>
                          <v-row>
                            <v-col cols="3">
                              <v-text-field
                                v-model="record.bloodVessel"
                                label="Mạch (lần/phút)"
                                placeholder="Mạch"
                              ></v-text-field>
                            </v-col>
                            <v-col cols="3">
                              <v-text-field
                                v-model="record.temperature"
                                label="Nhiệt độ (độ C)"
                                placeholder="Nhiệt độ"
                              ></v-text-field>
                            </v-col>
                            <v-col cols="3">
                              <v-text-field
                                v-model="record.bloodPressure"
                                label="Huyết áp (mmHg)"
                                placeholder="Huyết áp"
                              ></v-text-field>
                            </v-col>
                            <v-col cols="3">
                              <v-text-field
                                v-model="record.heartbeat"
                                label="Nhịp tim (lần/phút)"
                                placeholder="Nhịp tim"
                              ></v-text-field>
                            </v-col>
                          </v-row>

                          <v-row>
                            <v-col cols="3">
                              <v-textarea
                                v-model="record.partsInspection"
                                label="Khám xét các bộ phận"
                                rows="2"
                              ></v-textarea>
                            </v-col>
                            <v-col cols="3">
                              <v-textarea
                                v-model="record.summary"
                                label="Tóm tắt kết quả lâm sàng"
                                rows="2"
                              ></v-textarea>
                            </v-col>
                            <v-col cols="3"> </v-col>
                            <v-col cols="3"> </v-col>
                          </v-row>
                          <v-row>
                            <v-col cols="3">
                              <v-checkbox
                                v-model="record.hospitalize"
                                label="Yêu cầu nhập viện"
                              ></v-checkbox>
                            </v-col>
                            <v-col cols="3">
                              <v-select
                                :disabled="!record.hospitalize"
                                v-model="record.facultyTreatment"
                                label="Khoa điều trị"
                                :items="specialties"
                              ></v-select>
                            </v-col>
                            <v-col cols="3"> </v-col>
                            <v-col cols="3"> </v-col>
                          </v-row>
                          <v-row>
                            <v-col cols="6">
                              <v-file-input
                                v-model="files"
                                color="primary lighten-1"
                                counter
                                label=""
                                multiple
                                placeholder="File đính kèm"
                                prepend-icon="mdi-paperclip"
                                height="40"
                                outlined
                                :show-size="1000"
                              >
                                <template v-slot:selection="{ index, text }">
                                  <v-chip
                                    v-if="index < 2"
                                    color="primary lighten-1"
                                    dark
                                    label
                                    small
                                  >
                                    {{ text }}
                                  </v-chip>

                                  <span
                                    v-else-if="index === 2"
                                    class="
                                      text-overline
                                      grey--text
                                      text--darken-3
                                      mx-2
                                    "
                                  >
                                    +{{ files.length - 2 }} File(s)
                                  </span>
                                </template>
                              </v-file-input>
                            </v-col>
                            <v-col cols="6"> </v-col>
                          </v-row>
                        </v-card-text>
                      </v-card>
                    </v-stepper-content>

                    <v-stepper-content step="3">
                      <v-card height="80vh" flat class="mb-12">
                        <v-card-text>
                          <v-btn
                            class="mb-3"
                            color="primary"
                            @click="addMedicine"
                            :disabled="panelMedicine.length > 0"
                            ><v-icon right dark class="mr-1">
                              add_circle_outline </v-icon
                            >Thêm đơn thuốc</v-btn
                          >
                          <v-expansion-panels
                            class="mt-3"
                            v-model="panelMedicine"
                            multiple
                          >
                            <v-expansion-panel
                              v-for="(medicine, index) in medicineList"
                              :key="index"
                              class="mt-3"
                            >
                              <v-expansion-panel-header>
                                {{ medicine.name }}
                              </v-expansion-panel-header>
                              <v-expansion-panel-content>
                                <v-form
                                  ref="form"
                                  v-model="valid"
                                  lazy-validation
                                >
                                  <v-row>
                                    <v-col cols="3"
                                      ><v-select
                                        :items="medicineNameList"
                                        v-model="medicine.name"
                                        label="Tên thuốc"
                                        placeholder="Chọn tên thuốc"
                                        :rules="[
                                          (v) =>
                                            !!v || 'Tên thuốc là bắt buộc!',
                                        ]"
                                      ></v-select
                                    ></v-col>
                                    <v-col cols="3"
                                      ><v-text-field
                                        v-model="medicine.number"
                                        label="Số lượng"
                                        placeholder="Nhập số lượng"
                                        type="number"
                                      ></v-text-field
                                    ></v-col>
                                    <v-col cols="3"
                                      ><v-select
                                        :items="medicineTypeList"
                                        v-model="medicine.type"
                                        label="Loại"
                                      ></v-select
                                    ></v-col>
                                    <v-col cols="3"
                                      ><v-text-field
                                        v-model="medicine.use"
                                        label="Cách sử dụng"
                                        placeholder="Nhập cách sử dụng"
                                      ></v-text-field
                                    ></v-col>
                                  </v-row>
                                  <v-row class="justify-end">
                                    <v-btn
                                      tile
                                      color="error"
                                      outlined
                                      @click="removeMedicine(index)"
                                      >Xóa</v-btn
                                    >
                                    <v-btn
                                      class="ml-2"
                                      tile
                                      color="primary"
                                      :disabled="!valid"
                                      @click="saveMedicine(index)"
                                      >Lưu</v-btn
                                    >
                                  </v-row>
                                </v-form>
                              </v-expansion-panel-content>
                            </v-expansion-panel>
                          </v-expansion-panels>
                          <v-row>
                            <v-col cols="6">
                              <v-textarea
                                v-model="record.notes"
                                clearable
                                clear-icon="mdi-close-circle"
                                label="Lưu ý"
                                rows="2"
                              ></v-textarea>
                            </v-col>
                            <v-col cols="6"> </v-col>
                          </v-row>
                          <v-row>
                            <v-col cols="3">
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
                                    v-model="record.reExaminationDate"
                                    label="Ngày tái khám"
                                    append-icon="mdi-calendar"
                                    readonly
                                    v-bind="attrs"
                                    v-on="on"
                                  ></v-text-field>
                                </template>
                                <v-date-picker
                                  v-model="record.reExaminationDate"
                                  @input="menu2 = false"
                                ></v-date-picker>
                              </v-menu>
                            </v-col>
                          </v-row>
                        </v-card-text>
                      </v-card>
                    </v-stepper-content>
                  </v-stepper-items>
                </v-stepper>
              </v-card-text>
            </v-card>
          </v-form>
        </v-bottom-sheet>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
const url = process.env.VUE_APP_ROOT_API;
export default {
  data() {
    return {
      appointmentList: [],
      appointment: {},
      initialAppointment: {
        id: undefined,
        stt: undefined,
        room: {
          id: undefined,
          name: undefined,
          link: undefined,
        },
        profile: {
          id: undefined,
          profileNumber: undefined,
          name: undefined,
          address: undefined,
          phoneNumber: undefined,
          dob: undefined,
          job: undefined,
          identityCard: undefined,
          healthInsurance: undefined,
          folk: undefined,
          gender: undefined,
          protector: undefined,
        },
        doctor: {
          id: undefined,
          name: undefined,
          specialty: undefined,
          level: undefined,
        },
        date: undefined,
        time: undefined,
        symptom: undefined,
        description: undefined,
        timeSituation: undefined,
        selfTreatment: undefined,
        files: [],
        status: undefined,
        createdDate: undefined,
      },
      panel: [],
      panelMedicine: [],
      stepper: 1,
      dialog: false,
      dialogRecord: false,
      picker: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
        .toISOString()
        .substr(0, 10),
      record: {},
      initialRecord: {
        pathological: undefined,
        personalMedicalHistory: undefined,
        familyMedicalHistory: undefined,
        bodyInspection: undefined,
        bloodVessel: undefined,
        temperature: undefined,
        bloodPressure: undefined,
        heartbeat: undefined,
        summary: undefined,
        partsInspection: undefined,
        hospitalize: undefined,
        facultyTreatment: undefined,
        medicines: [],
        notes: undefined,
        reExaminationDate: undefined,
        files: [],
      },
      medicine: {
        name: undefined,
        number: undefined,
        type: undefined,
        use: undefined,
      },
      medicineList: [],
      specialties: [],
      medicineNameList: ["Medicine A", "Medicine B", "Medicine C"],
      medicineTypeList: ["Viên", "Vỉ", "Hộp"],
      files: [],
      loading: false,
      valid: true,
      menu2: false,
    };
  },
  mounted() {
    this.appointment = JSON.parse(JSON.stringify(this.initialAppointment));
    this.record = JSON.parse(JSON.stringify(this.initialRecord));
    this.getSpecialties();
  },
  methods: {
    nextStep() {
      this.stepper++;
    },
    backStep() {
      if (this.stepper == 1) return;
      this.stepper--;
    },
    all() {
      this.panel = [...Array(this.schedule.length).keys()].map((k, i) => i);
    },
    none() {
      this.panel = [];
    },
    closeForm() {
      this.dialogRecord = false;
      this.appointment = JSON.parse(JSON.stringify(this.initialAppointment));
      this.record = JSON.parse(JSON.stringify(this.initialRecord));
      this.stepper = 1;
      this.medicineList = [];
    },

    openAppointmentDetail(appointment) {
      this.dialog = true;
      Object.assign(this.appointment, appointment);
    },

    openDialogCreateRecord(appointment) {
      this.dialogRecord = true;
      Object.assign(this.appointment, appointment);
    },

    async getAppointmentList() {
      const params = {
        token: this.$store.getters["auth/access_token"],
        data: {
          date: this.picker,
        },
      };
      await this.$store.dispatch(
        "appointment/getAppointment_byDate_Doctor",
        params
      );
      this.appointmentList =
        this.$store.getters["appointment/doctor_appointment_list"];
    },
    closeDialogDetail() {
      this.dialog = false;
      this.appointment = {};
    },
    async getSpecialties() {
      axios.get(`${url}/api/specialties`).then((res) => {
        this.specialties = res.data.results;
      });
    },
    addMedicine() {
      if (!this.panelMedicine.length && !this.medicineList.length) {
        this.panelMedicine = [0];
      } else {
        console.log(this.medicineList.length);
        this.panelMedicine = [this.medicineList.length];
      }
      let medicine = { ...this.medicine };
      this.medicineList.push(medicine);
    },
    saveMedicine(index) {
      if (!this.$refs[`form`][index].validate()) {
        return;
      }
      this.record.medicines = [...this.medicineList];
      this.panelMedicine = [];
    },
    removeMedicine(index) {
      this.medicineList.splice(index, 1);
      this.panelMedicine = [];
    },
    async saveRecord() {
      const formData = new FormData();

      formData.append("appointmentId", this.appointment.id);
      formData.append("pathological", this.record.pathological);
      formData.append(
        "personalMedicalHistory",
        this.record.personalMedicalHistory
      );
      formData.append("familyMedicalHistory", this.record.familyMedicalHistory);
      formData.append("bodyInspection", this.record.bodyInspection);
      formData.append("bloodVessel", this.record.bloodVessel);
      formData.append("temperature", this.record.temperature);
      formData.append("bloodPressure", this.record.bloodPressure);
      formData.append("heartbeat", this.record.heartbeat);
      formData.append("summary", this.record.summary);
      formData.append("partsInspection", this.record.partsInspection);
      formData.append("hospitalize", "True");
      formData.append("facultyTreatment", this.record.facultyTreatment);
      formData.append("notes", this.record.notes);
      formData.append("reExaminationDate", this.record.reExaminationDate);
      const medicineList = JSON.stringify(this.medicineList);
      formData.append("medicines", medicineList);
      this.files.forEach((file) => {
        formData.append("files", file);
      });
      this.loading = true;
      const params = {
        token: this.$store.getters["auth/access_token"],
        data: formData,
      };
      await this.$store
        .dispatch("record/create_record", params)
        .then(() => {
          this.closeForm();
        })
        .finally(() => {
          this.loading = false;
        });
    },
  },
};
</script>

<style lang="scss" scoped>
.tag {
  font-weight: 400;
}
.body-content {
  height: calc(80vh - 64px) !important;
}
.thongbao .md-card .md-title {
  font-size: 16px;
  text-align: center;
  color: #ffffff;
}

.thongbao .md-card .md-card-header {
  box-shadow: 0 0 0 0;
  background-color: #448aff;
}

.thongbao .md-button:hover {
  background-color: #e0e0e0 !important;
  color: #333 !important;
}

.danhsachkham .md-icon-image svg {
  flex: 0;
}

.danhsachkham .md-table-cell {
  padding: 15px 13px;
}

.md-dialog .md-dialog-container {
  max-width: 768px;
}

.md-tabs {
  margin-bottom: 24px;
}

.thongtinkham {
  padding: 10px 40px;
}
.hosobenhnhan {
  text-align: center;
}

.lich .theme--light.v-card {
  max-height: 350px;
}

.rowinfo {
  width: 100%;
  display: flex;
  margin: 10px 0px;
  text-align: center;
}

.rowinfo p {
  margin: 0px;
}

.infoID {
  width: 10%;
}

.infoName {
  width: 30%;
}

.infoRoom {
  width: 30%;
}

.infoChitiet {
  width: 15%;
}

.taoBenhAn {
  width: 15%;
}

.infoChitiet button {
  background-color: white;

  color: #4caf50;
  padding: 2px 6px;
  border-radius: 5px;
}

.infoChitiet button:hover {
  background-color: #4caf50;
  color: white;

  border-color: white;
}

.taoBenhAn button {
  background-color: white;

  color: #448aff;
  padding: 2px 6px;
  border-radius: 5px;
}

.taoBenhAn button:hover {
  background-color: #448aff;
  color: white;
  border-color: white;
}

.gop {
  width: 100%;
  background-color: rgba(201, 198, 195, 0.671);
}
.gop0 {
  width: 90%;

  margin-left: auto;
  margin-right: auto;
  background-color: #ffffff;
}
.gop1 {
  width: 100%;
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

.page2_0 {
  width: 100%;
  background-color: rgba(201, 198, 195, 0.671);
}
.page2_1 {
  width: 90%;

  margin-left: auto;
  margin-right: auto;
  background-color: #ffffff;
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
.ketquatongquat {
  text-align: center;
}
.ketquatongquat p {
  display: inline;
}
.ketquatongquat input {
  width: 60px;
  background-color: #e0e0e0;
  margin: 0px 5px;
  padding: 2px 8px;
}

.soluong {
  width: 150px;
  height: 52px;
  float: left;
}
.soluong input {
  height: 50px;
  font-size: 16px;
  color: #333;
  padding: 0px 10px;
}
.loai {
  width: 30%;
  float: left;
  margin-right: 30px;
}

.cachdung {
  width: 90%;
  margin: 10px 20px;
}
</style>
