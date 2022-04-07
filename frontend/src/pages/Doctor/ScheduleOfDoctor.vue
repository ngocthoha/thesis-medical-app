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
        <br />
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
        </div>
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
          <v-expansion-panel v-for="(item, i) in schedule" :key="i">
            <v-expansion-panel-header
              >{{ item.times }}
            </v-expansion-panel-header>
            <v-expansion-panel-content
              v-for="(infoApment, j) in item.appointment"
              :key="j"
            >
              <div class="rowinfo">
                <div class="infoID">
                  <p>{{ infoApment.stt }}</p>
                </div>
                <div class="infoName">
                  <p>{{ infoApment.profile.name }}</p>
                </div>
                <div class="infoRoom">
                  <p>{{ infoApment.room }}</p>
                </div>

                <div class="infoChitiet">
                  <button @click="get(i, j)"><b>Chi tiết</b></button>
                </div>
                <div class="taoBenhAn">
                  <button @click="getbenhan(i, j)">
                    <b>Tạo bệnh án</b>
                  </button>
                </div>
              </div>
              <v-divider></v-divider>
            </v-expansion-panel-content>
          </v-expansion-panel>
        </v-expansion-panels>

        <v-dialog v-model="dialog" scrollable max-width="800px" persistent>
          <v-card>
            <v-card-title>Thông tin bệnh nhân</v-card-title>
            <v-divider></v-divider>
            <v-card-text
              style="
                height: auto;
                margin-left: auto;
                margin-right: auto;
                width: auto;
              "
            >
              <h5>
                <b
                  >Ngày {{ record.appointment.date }}, Thời gian {{ record.appointment.time }},
                  Phòng số {{ record.appointment.room }}</b
                >
              </h5>

              <h5><b>Họ và tên: </b>{{ record.appointment.profile.name }}</h5>
              <h5><b>Triệu chứng: </b>{{ record.appointment.symptom }}</h5>
              <h5><b>Mô tả chi tiết: </b>{{ record.appointment.description }}</h5>
              <h5>
                <b
                  >Thời gian diễn ra tình trạng trên:
                  {{ record.appointment.timeSituation }}</b
                >
              </h5>
              <h5><b>Đã tự điều trị bệnh: </b>{{ record.appointment.selfTreatment }}</h5>
            </v-card-text>
            <v-divider></v-divider>
            <v-card-actions style="flex-direction: row-reverse">
              <v-btn
                color="red"
                text
                :right="true"
                @click="dialog = false"
                style="flex-direction: row"
              >
                Đóng
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>

        <v-dialog
          v-model="dialogBenhAn"
          max-width="1200px"
          max-height="auto"
          persistent
        >
          <v-form ref="form">
            <v-card>
              <v-card-title style="justify-content: space-between">
                <span class="text-h5">TẠO BỆNH ÁN</span>
                <v-btn
                  color="#ff5252"
                  @click="closeForm"
                  style="width: 40px; color: white"
                >
                  Đóng
                </v-btn>
              </v-card-title>

             <v-card-text>
                  <div>
                    <p
                      style="
                        display: flex;
                        width: 30%;
                        margin: 0px;
                        float: left;
                      "
                    >
                      <b style="margin-top: 20px; margin-right: 20px"
                        >BÁC SĨ: </b
                      ><v-text-field
                        style="width: 50%; margin: 0px"
                        v-model="record.appointment.doctor.name"
                      ></v-text-field>
                     
                    </p>
                    <p
                      style="
                        display: flex;
                        width: 35%;
                        margin: 0px;
                        float: left;
                      "
                    >
                      <b style="margin-top: 20px; margin-right: 20px"
                        >NGÀY KHÁM:
                      </b>
                      <v-menu
                        v-model="menu12"
                        :close-on-content-click="false"
                        :nudge-right="40"
                        transition="scale-transition"
                        offset-y
                        min-width="auto"
                      >
                        <template v-slot:activator="{ on, attrs }">
                          <v-text-field
                            v-model="record.appointment.date"
                            prepend-icon="mdi-calendar"
                            readonly
                            v-bind="attrs"
                            v-on="on"
                          ></v-text-field>
                        </template>
                        <v-date-picker
                          v-model="record.appointment.date"
                          @input="menu12 = false"
                        ></v-date-picker>
                      </v-menu>
                    </p>
                    <p style="display: flex; width: 35%; margin: 0px">
                      <b style="margin-top: 20px; margin-right: 20px"
                        >THỜI GIAN:
                      </b>
                      <v-autocomplete
                        style="margin-top: 10px"
                        v-model="record.appointment.time"
                        :items="timeselect"
                        dense
                        filled
                      ></v-autocomplete>
                    </p>
                  </div>
                </v-card-text>

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

                    <v-stepper-step step="3" editable> Kê đơn </v-stepper-step>
                  </v-stepper-header>

                  <v-stepper-items>
                    <v-stepper-content :key="`${1}-content`" :step="1">
                      <v-card class="mb-12" color="white" height="auto">
                        <div class="gop">
                          <div class="gop0">
                            <div class="gop1">
                              <div class="textform">
                                <p>1. Họ và tên:</p>
                               
                                 <v-text-field
                       v-model="record.appointment.profile.name"
                   
                      ></v-text-field>
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
                                    <template v-slot:activator="{ on, attrs }">
                                      <v-text-field
                                        v-model="record.appointment.profile.dob"
                                        prepend-icon="mdi-calendar"
                                        readonly
                                        v-bind="attrs"
                                        v-on="on"
                                      ></v-text-field>
                                    </template>
                                    <v-date-picker
                                      @input="menu11 = false"
                                      v-model="record.appointment.profile.dob"
                                    ></v-date-picker>
                                  </v-menu>
                                </div>
                              </div>
                              <div class="textform">
                                <p>3. Giới tính:</p>
                                <div class="gioitinh">
                                  <v-radio-group
                                    row
                                    v-model="record.appointment.profile.gender"
                                  >
                                    <v-radio label="Nam" value="Nam"></v-radio>
                                    <v-radio label="Nữ" value="Nữ"></v-radio>
                                  </v-radio-group>
                                </div>
                              </div>
                              <div class="textform">
                                <p>4. Dân tộc:</p>
                                <div class="autocom">
                                  <v-toolbar color="white">
                                    <v-autocomplete
                                      v-model="record.appointment.profile.folk"
                                      :items="states"
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
                               
                                    <v-text-field
                        v-model="
                                    record.appointment.profile.healthInsurance
                                  "
                   
                      ></v-text-field>
                              </div>
                              <div class="textform">
                                <p>6. Nghề nghiệp</p>
                              
                                <v-text-field v-model="record.appointment.profile.job"></v-text-field>
                              </div>

                              <div class="textform">
                                <p>7. Địa chỉ:</p>
                             
                                <v-text-field v-model="record.appointment.profile.address"></v-text-field>
                              </div>

                              <div class="textform">
                                <p>8. Tên người giám hộ(Nếu có)</p>
                              
                                <v-text-field v-model="record.appointment.profile.protector"></v-text-field>
                              </div>
                              <div class="textform">
                                <p>9. SĐT Liên lạc:</p>
                           
                                <v-text-field v-model="
                                    record.appointment.profile.phoneNumber
                                  "></v-text-field>
                              </div>

                              <div class="textform">
                                <p>10. CMND:</p>
                              
                                <v-text-field v-model="
                                    record.appointment.profile.identityCard
                                  "></v-text-field>
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
                                    v-model="record.pathological"
                                    clearable
                                    clear-icon="mdi-close-circle"
                                    label="Biểu hiện của bệnh"
                                    background-color="grey lighten-2"
                                    color="cyan"
                                    rows="1"
                                    auto-grow
                                    style="border-radius: 5px; padding: 0px 0px"
                                  ></v-textarea>
                                </v-container>
                              </div>

                              <div class="textpage2">
                                <p>2. Tiền sử bệnh bản thân:</p>
                                <v-container fluid>
                                  <v-textarea
                                    v-model="record.personalMedicalHistory"
                                    clearable
                                    clear-icon="mdi-close-circle"
                                    label="Các bệnh đã mắc"
                                    background-color="grey lighten-2"
                                    color="cyan"
                                    auto-grow
                                    rows="1"
                                    style="border-radius: 5px; padding: 0px 0px"
                                  ></v-textarea>
                                </v-container>
                              </div>
                              <div class="textpage2">
                                <p>3. Tiền sử bệnh gia đình:</p>
                                <v-container fluid>
                                  <v-textarea
                                    v-model="record.familyMedicalHistory"
                                    clearable
                                    clear-icon="mdi-close-circle"
                                    label="Tiền sử bệnh gia đình"
                                    background-color="grey lighten-2"
                                    color="cyan"
                                    auto-grow
                                    rows="1"
                                    style="border-radius: 5px; padding: 0px 0px"
                                  ></v-textarea>
                                </v-container>
                              </div>
                              <div class="textpage2">
                                <p>4. Khám xét toàn thân:</p>
                                <v-container fluid>
                                  <v-textarea
                                    v-model="record.bodyInspection"
                                    clearable
                                    clear-icon="mdi-close-circle"
                                    label="Kết quả khám toàn thân"
                                    background-color="grey lighten-2"
                                    color="cyan"
                                    auto-grow
                                    rows="1"
                                    style="border-radius: 5px; padding: 0px 0px"
                                  ></v-textarea>
                                </v-container>
                              </div>
                          
                                <div class="ketquatongquat" style="display: flex;">
                                  <div class="inline" style="display:flex; width:25%;">
                                  <p style="width:50px !important; margin-top:10px;">Mạch:</p> 
                                  <v-text-field v-model="record.bloodVessel" style="width:20%;padding:0px;"></v-text-field>
                                  <p style="width:60px !important; margin-top:10px;">lần/phút</p>
                                   </div>
                                  
                                  
                                
                             

                                   <div class="inline" style="display:flex; width:25%;">
                                  <p style="width:50px !important; margin-top:10px;">Nhiệt độ:</p> 
                                  <v-text-field v-model="record.temperature" style="width:20%;padding:0px;"></v-text-field>
                                  <p style="width:60px !important; margin-top:10px;">độ C</p>
                                   </div>

                                    <div class="inline" style="display:flex; width:25%;">
                                  <p style="width:50px !important; margin-top:10px;">Huyết áp:</p> 
                                  <v-text-field  v-model="record.bloodPressure" style="width:20%;padding:0px;"></v-text-field>
                                  <p style="width:60px !important; margin-top:10px;">mmHg</p>
                                   </div>

                                       <div class="inline" style="display:flex; width:25%;">
                                  <p style="width:50px !important; margin-top:10px;">Nhịp tim:</p> 
                                  <v-text-field v-model="record.heartbeat" style="width:20%;padding:0px;"></v-text-field>
                                  <p style="width:60px !important; margin-top:10px;">lần/phút</p>
                                   </div>
                            
                                 
                                
                                 
                                </div>
                       
                              <div class="textpage2">
                                <p>5. Khám xét các bộ phận</p>
                                <v-container fluid>
                                  <v-textarea
                                    v-model="record.partsInspection"
                                    clearable
                                    clear-icon="mdi-close-circle"
                                    label="Kết quả khám bộ phận"
                                    background-color="grey lighten-2"
                                    color="cyan"
                                    auto-grow
                                    rows="1"
                                    style="border-radius: 5px; padding: 0px 0px"
                                  ></v-textarea>
                                </v-container>
                              </div>

                              <div class="textpage2">
                                <p>6. Tóm tắt kết quả lâm sàng:</p>
                                <v-container fluid>
                                  <v-textarea
                                    v-model="record.summary"
                                    clearable
                                    clear-icon="mdi-close-circle"
                                    label="Chuẩn đoán lâm sàng"
                                    background-color="grey lighten-2"
                                    color="cyan"
                                    auto-grow
                                    rows="1"
                                    style="border-radius: 5px; padding: 0px 0px"
                                  ></v-textarea>
                                </v-container>
                              </div>

                              <div class="textpage2">
                                <p>7. Hình ảnh bổ sung:</p>
                                <v-file-input
                                  v-model="record.files"
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
                                <v-checkbox
                                  v-model="record.hospitalize"
                                  label="Yều cầu nhập viện"
                                ></v-checkbox>
                              </div>

                              <div class="textpage2">
                                <p>KHOA ĐIỀU TRỊ:</p>
                                <v-autocomplete
                                  v-model="record.facultyTreatment"
                                  :items="dskhoa"
                                  dense
                                  filled
                                ></v-autocomplete>
                              </div>

                              <div class="textpage2">
                                <p>KÊ ĐƠN THUỐC:</p>
                                <div class="textpage3">
                                  <div class="textpage4">
                                    <div
                                      class="ketquatongquat"
                                      v-for="(thuoc_1, k) in record.medicines"
                                      :key="k"
                                    >
                                      <p style="width:100%; text-align: left;:">
                                        <i> {{ k + 1 }}</i>
                                      </p>
                                      <v-autocomplete
                                        v-model="thuoc_1.nameMedicine"
                                        :items="dsthuoc"
                                        dense
                                        filled
                                        label="Tên thuốc"
                                      ></v-autocomplete>
                                      <div class="combosoluongloai">
                                        <div class="soluong">
                                          <p style="float:left; margin-top:15px; margin-right:10px;">Số lượng:</p>
                                       
                                          <v-text-field  v-model="thuoc_1.numberMedicine" style="width:30%;"></v-text-field>
                                        </div>

                                        <div class="loai">
                                          <v-autocomplete
                                            v-model="thuoc_1.typeMedicine"
                                            :items="dsloai"
                                            dense
                                            filled
                                            label="loại"
                                          ></v-autocomplete>
                                        </div>
                                      </div>
                                      <div class="cachdung">
                                        <v-autocomplete
                                          v-model="thuoc_1.useMedicine"
                                          :items="dscachdung"
                                          dense
                                          chips
                                          small-chips
                                          label="Cách dùng"
                                          multiple
                                          solo
                                        ></v-autocomplete>
                                      </div>
                                      <v-btn
                                        block
                                        @click="removedonthuoc(medicines, k)"
                                      >
                                        Xóa
                                      </v-btn>

                                      <v-divider
                                        style="margin-bottom: 20px"
                                      ></v-divider>
                                    </div>
                                  </div>
                                </div>

                                <div class="controls">
                                  <v-btn
                                    block
                                    style="width: 100%"
                                    @click="addnewdonthuoc(medicines)"
                                  >
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
                                    v-model="record.notes"
                                    clearable
                                    clear-icon="mdi-close-circle"
                                    background-color="grey lighten-2"
                                    color="cyan"
                                    auto-grow
                                    rows="3"
                                    style="border-radius: 5px; padding: 0px 0px"
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
                                  <template v-slot:activator="{ on, attrs }">
                                    <v-text-field
                                      v-model="record.reExaminationDate"
                                      prepend-icon="mdi-calendar"
                                      readonly
                                      v-bind="attrs"
                                      v-on="on"
                                    ></v-text-field>
                                  </template>
                                  <v-date-picker
                                    v-model="record.reExaminationDate"
                                    @input="menu10 = false"
                                  ></v-date-picker>
                                </v-menu>
                              </div>
                            </div>
                          </div>
                        </div>
                      </v-card>

                      <v-btn text @click="beforeStep(3)"> Quay Lại </v-btn>
                      <v-btn color="primary"> Lưu bệnh án </v-btn>
                    </v-stepper-content>
                  </v-stepper-items>
                </v-stepper>
            </v-card>
          </v-form>
        </v-dialog>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      nulltime: null,
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
      panel: [],
      menu11: false,
      menu10: false,
      datetaikham: null,
      items: [],
      search1: null,
      e1: 1,
      dialog: false,
      dialogBenhAn: false,
      picker: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
        .toISOString()
        .substr(0, 10),
      states: ["Kinh", "Thái", "Mường", "Dao", "GiaRai"],
      note1: [
        {
          num: 5,
          time: "10:00-11:00",
        },
      ],
      note2: [
        {
          num: 8,
          time: "10-03-2022",
        },
      ],
      record:  {
      id: null,
      idrecord: null,
      appointment: {
        id: null,
        stt: null,
        room: null,
        profile: {
          id: null,
          profileNumber: null,
          name: null,
          address: null,
          phoneNumber: null,
          dob: null,
          job: null,
          identityCard: null,
          healthInsurance: null,
          folk: null,
          gender: null,
          protector: null,
        },
        doctor: {
          id: null,
          name: null,
          specialty: null,
          level: null,
        },
        date: null,
        time: null,
        symptom: null,
        description: null,
        timeSituation: null,
        selfTreatment: null,
        files: [],
      },
      pathological: null,
      personalMedicalHistory: null,
      familyMedicalHistory: null,
      bodyInspection: null,
      bloodVessel: null,
      temperature: null,
      bloodPressure: null,
      heartbeat: null,
      summary: null,
      partsInspection: null,
      hospitalize: null,
      facultyTreatment: null,
      medicines: [
        {
          nameMedicine: null,
          numberMedicine: null,
          typeMedicine: null,
          useMedicine: null,
        },
      ],
      notes: null,
      reExaminationDate: null,
      files: [],
    },
      schedule: [
        {
          times: "10:00-11:00",
          appointment: [
            {
              id: "f4f98f18-4baa-4104-850f-d0930d55a80d",
              stt: 1,
              room: "H2",
              profile: {
                id: "849dc832-7b53-4a5b-9b04-a930e11f8649",
                profileNumber: "1",
                name: "Văn Ba",
                address: "address",
                phoneNumber: "phone",
                dob: "2022-03-30",
                job: "job",
                identityCard: "identity",
                healthInsurance: "healthy",
                folk: "Kinh",
                gender: "Nam",
                protector: "protector",
              },
              doctor: {
                id: "71d404a9-9acd-4814-9d50-3a9b63ede068",
                name: "John",
                specialty: "specialty",
                level: "level",
              },
              date: "2022-03-29",
              time: "10:00-11:00",
              symptom: "dau tim",
              description: "dau tim",
              timeSituation: "20",
              selfTreatment: true,
              files: [],
            },
            {
              id: "12348f18-4baa-4104-850f-d0930d55a80d",
              stt: 2,
              room: "H6",
              profile: {
                id: "1234c832-7b53-4a5b-9b04-a930e11f8649",
                profileNumber: "2",
                name: "Ân Xá",
                address: "addressaaaaaaaaa",
                phoneNumber: "phoneaaaaa",
                dob: "2022-03-30",
                job: "jobaaaaa",
                identityCard: "identityaaaa",
                healthInsurance: "healthyaaaa",
                folk: "Kinh",
                gender: "Nam",
                protector: "protectoraaaaaa",
              },
              doctor: {
                id: "123404a9-9acd-4814-9d50-3a9b63ede068",
                name: "Johnaaaa",
                specialty: "specialty",
                level: "level",
              },
              date: "2022-03-29",
              time: "10:00-11:00",
              symptom: "dau tim",
              description: "dau tim",
              timeSituation: "20",
              selfTreatment: true,
              files: [],
            },
            {
              id: "12348f18-4baa-4104-850f-d0930d55a80d",
              stt: 2,
              room: "H6",
              profile: {
                id: "1234c832-7b53-4a5b-9b04-a930e11f8649",
                profileNumber: "2",
                name: "ÂĐà phật",
                address: "addressaaaaaaaaa",
                phoneNumber: "phoneaaaaa",
                dob: "2022-03-30",
                job: "jobaaaaa",
                identityCard: "identityaaaa",
                healthInsurance: "healthyaaaa",
                folk: "Kinh",
                gender: "Nam",
                protector: "protectoraaaaaa",
              },
              doctor: {
                id: "123404a9-9acd-4814-9d50-3a9b63ede068",
                name: "J",
                specialty: "specialty",
                level: "level",
              },
              date: "2022-03-29",
              time: "10:00-11:00",
              symptom: "dau tim",
              description: "dau tim",
              timeSituation: "20",
              selfTreatment: true,
              files: [],
            },
          ],
        },
        {
          times: "11:00-12:00",
          appointment: [
            {
              id: "f4f98f18-4baa-4104-850f-d0930d55a80d",
              stt: 1,
              room: "H2",
              profile: {
                id: "849dc832-7b53-4a5b-9b04-a930e11f8649",
                profileNumber: "1",
                name: "Văn Ba",
                address: "address",
                phoneNumber: "phone",
                dob: "2022-03-30",
                job: "job",
                identityCard: "identity",
                healthInsurance: "healthy",
                folk: "Kinh",
                gender: "Nam",
                protector: "protector",
              },
              doctor: {
                id: "71d404a9-9acd-4814-9d50-3a9b63ede068",
                name: "John",
                specialty: "specialty",
                level: "level",
              },
              date: "2022-03-29",
              time: "10:00-11:00",
              symptom: "dau tim",
              description: "dau tim",
              timeSituation: "20",
              selfTreatment: true,
              files: [],
            },
            {
              id: "12348f18-4baa-4104-850f-d0930d55a80d",
              stt: 2,
              room: "H6",
              profile: {
                id: "1234c832-7b53-4a5b-9b04-a930e11f8649",
                profileNumber: "2",
                name: "Ân Xá",
                address: "addressaaaaaaaaa",
                phoneNumber: "phoneaaaaa",
                dob: "2022-03-30",
                job: "jobaaaaa",
                identityCard: "identityaaaa",
                healthInsurance: "healthyaaaa",
                folk: "Kinh",
                gender: "Nam",
                protector: "protectoraaaaaa",
              },
              doctor: {
                id: "123404a9-9acd-4814-9d50-3a9b63ede068",
                name: "Johnaaaa",
                specialty: "specialty",
                level: "level",
              },
              date: "2022-03-29",
              time: "11:00-12:00",
              symptom: "dau tim",
              description: "dau tim",
              timeSituation: "20",
              selfTreatment: true,
              files: [],
            },
            {
              id: "12348f18-4baa-4104-850f-d0930d55a80d",
              stt: 2,
              room: "H6",
              profile: {
                id: "1234c832-7b53-4a5b-9b04-a930e11f8649",
                profileNumber: "2",
                name: "cc",
                address: "addressaaaaaaaaa",
                phoneNumber: "phoneaaaaa",
                dob: "2022-03-30",
                job: "jobaaaaa",
                identityCard: "identityaaaa",
                healthInsurance: "healthyaaaa",
                folk: "Kinh",
                gender: "Nam",
                protector: "protectoraaaaaa",
              },
              doctor: {
                id: "123404a9-9acd-4814-9d50-3a9b63ede068",
                name: "J",
                specialty: "specialty",
                level: "level",
              },
              date: "2022-03-29",
              time: "11:00-12:00",
              symptom: "dau tim",
              description: "dau tim",
              timeSituation: "20",
              selfTreatment: true,
              files: [],
            },
          ],
        },
      ],

      donthuoc: [
        {
          tenthuoc: null,
          soluongthuoc: null,
          loaisudung: null,
          cachdungthuoc: [],
        },
      ],

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
      dscachdung: [
        "Sáng-1v",
        "Sáng-2v",
        "Trưa-1v",
        "Trưa-2v",
        "Tối-1v",
        "Tối-2v",
      ],
      cachdung: null,
      loai: null,
      dsloai: ["Viên", "Vỉ", "Hộp"],
      hami: 0,
      hamj: 0,
    };
  },
  watch: {
    search1(val) {
      val && val !== this.select;
    },
  },
  methods: {
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
    addnewdonthuoc(donthuoc) {
      this.donthuoc.push({});
    },
    removedonthuoc(donthuoc, k) {
      donthuoc.splice(k, 1);
    },
    all() {
      this.panel = [...Array(this.schedule.length).keys()].map((k, i) => i);
    },
    none() {
      this.panel = [];
    },
    closeForm() {
      this.$refs.form.reset();
      this.dialogBenhAn = false;
    },
    get(i, j) {
      this.dialog = true;
      this.hami = i;
      this.hamj = j;

      this.record.appointment.times = this.schedule[i].times;
      this.record.appointment.id = this.schedule[i].appointment[j].id;
      this.record.appointment.stt = this.schedule[i].appointment[j].stt;
      this.record.appointment.room = this.schedule[i].appointment[j].room;

      this.record.appointment.profile.id = this.schedule[i].appointment[j].profile.id;
      this.record.appointment.profile.profileNumber =
        this.schedule[i].appointment[j].profile.profileNumber;
      this.record.appointment.profile.name = this.schedule[i].appointment[j].profile.name;
      this.record.appointment.profile.address =
        this.schedule[i].appointment[j].profile.address;
      this.record.appointment.profile.phoneNumber =
        this.schedule[i].appointment[j].profile.phoneNumber;
      this.record.appointment.profile.dob = this.schedule[i].appointment[j].profile.dob;
      this.record.appointment.profile.job = this.schedule[i].appointment[j].profile.job;
      this.record.appointment.profile.identityCard =
        this.schedule[i].appointment[j].profile.identityCard;
      this.record.appointment.profile.healthInsurance =
        this.schedule[i].appointment[j].profile.healthInsurance;
      this.record.appointment.profile.folk = this.schedule[i].appointment[j].profile.folk;
      this.record.appointment.profile.gender =
        this.schedule[i].appointment[j].profile.gender;
      this.record.appointment.profile.protector =
        this.schedule[i].appointment[j].profile.protector;

      this.record.appointment.doctor.id = this.schedule[i].appointment[j].doctor.id;
      this.record.appointment.doctor.name = this.schedule[i].appointment[j].doctor.name;
      this.record.appointment.doctor.specialty =
        this.schedule[i].appointment[j].doctor.specialty;
      this.record.appointment.doctor.level = this.schedule[i].appointment[j].doctor.level;

      this.record.appointment.date = this.schedule[i].appointment[j].date;
      this.record.appointment.time = this.schedule[i].appointment[j].time;
      this.record.appointment.symptom = this.schedule[i].appointment[j].symptom;
      this.record.appointment.description = this.schedule[i].appointment[j].description;
      this.record.appointment.timeSituation =
        this.schedule[i].appointment[j].timeSituation;
      this.record.appointment.selfTreatment =
        this.schedule[i].appointment[j].selfTreatment;
      this.record.appointment.files = this.schedule[i].appointment[j].files;
    },
    getbenhan(i, j) {
      this.dialogBenhAn = true;
      this.hami = i;
      this.hamj = j;

      this.record.appointment.times = this.schedule[i].times;
      this.record.appointment.id = this.schedule[i].appointment[j].id;
      this.record.appointment.stt = this.schedule[i].appointment[j].stt;
      this.record.appointment.room = this.schedule[i].appointment[j].room;

      this.record.appointment.profile.id = this.schedule[i].appointment[j].profile.id;
      this.record.appointment.profile.profileNumber =
        this.schedule[i].appointment[j].profile.profileNumber;
      this.record.appointment.profile.name = this.schedule[i].appointment[j].profile.name;
      this.record.appointment.profile.address =
        this.schedule[i].appointment[j].profile.address;
      this.record.appointment.profile.phoneNumber =
        this.schedule[i].appointment[j].profile.phoneNumber;
      this.record.appointment.profile.dob = this.schedule[i].appointment[j].profile.dob;
      this.record.appointment.profile.job = this.schedule[i].appointment[j].profile.job;
      this.record.appointment.profile.identityCard =
        this.schedule[i].appointment[j].profile.identityCard;
      this.record.appointment.profile.healthInsurance =
        this.schedule[i].appointment[j].profile.healthInsurance;
      this.record.appointment.profile.folk = this.schedule[i].appointment[j].profile.folk;
      this.record.appointment.profile.gender =
        this.schedule[i].appointment[j].profile.gender;
      this.record.appointment.profile.protector =
        this.schedule[i].appointment[j].profile.protector;

      this.record.appointment.doctor.id = this.schedule[i].appointment[j].doctor.id;
      this.record.appointment.doctor.name = this.schedule[i].appointment[j].doctor.name;
      this.record.appointment.doctor.specialty =
        this.schedule[i].appointment[j].doctor.specialty;
      this.record.appointment.doctor.level = this.schedule[i].appointment[j].doctor.level;

      this.record.appointment.date = this.schedule[i].appointment[j].date;
      this.record.appointment.time = this.schedule[i].appointment[j].time;
      this.record.appointment.symptom = this.schedule[i].appointment[j].symptom;
      this.record.appointment.description = this.schedule[i].appointment[j].description;
      this.record.appointment.timeSituation =
        this.schedule[i].appointment[j].timeSituation;
      this.record.appointment.selfTreatment =
        this.schedule[i].appointment[j].selfTreatment;
      this.record.appointment.files = this.schedule[i].appointment[j].files;
    },

    async getAppointmentList(){
      console.log(this.picker);
      // const params = {
      //   token: this.$store.getters["auth/access_token"],
      //   data: {
      //     date:this.picker,
      //   }
      // };
      // await this.$store.dispatch("appointment/getAppointment_byDate_Doctor", params);
      // this.schedule = this.$store.getters["appointment/doctor_appointment_list"];
    }
  },
};
</script>

<style lang="scss" scoped>
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
