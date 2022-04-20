<template>
  <div class="content">
    <div class="md-layout">
      <v-footer padless width="100%" color="primary" class="mb-10 white--text">
        <v-col class="text-center" cols="12">
          <strong>Danh sách đặt lịch hẹn</strong>
        </v-col>
      </v-footer>
      <v-col cols="8"
        ><v-card width="100%" min-height="500">
          <!-- <v-col align-self="center" cols="12"><v-card width="80%" outlined color="green">Hello</v-card></v-col> -->
          <v-col
            v-for="(appointment, index) in appointment_list"
            :key="index"
            cols="12"
          >
            <v-row class="my-1" justify="center" width="100%"
              ><v-card width="80%" class="pa-4"
                ><v-row width="100%"
                  ><v-col class="pa-2" cols="5">
                    <strong>Ngày khám: {{ appointment.date }}</strong></v-col
                  ><v-col class="pa-2" cols="5">
                    <strong v-if="appointment.status === 'ACTIVE'"
                      >Tình trạng: chưa khám
                    </strong>
                    <strong v-if="appointment.status === 'COMPLETE'"
                      >Tình trạng: đã khám
                    </strong></v-col
                  >
                  <v-col class="pa-2" cols="2">
                    <v-btn
                      class="mx-2"
                      fab
                      dark
                      small
                      color="primary"
                      @click="selectAppointment(appointment)"
                    >
                      <v-icon dark> mdi-table-eye </v-icon>
                    </v-btn></v-col
                  ></v-row
                ><v-row width="100%"
                  ><v-col class="pa-2" cols="5">
                    <strong>
                      Chuyên Khoa: {{ appointment.doctor.specialty }}</strong
                    ></v-col
                  ></v-row
                ><v-row width="100%"
                  ><v-col class="pa-2" cols="5">
                    <strong
                      >Bệnh nhân: {{ appointment.profile.name }}</strong
                    ></v-col
                  ></v-row
                ></v-card
              ></v-row
            >
            <v-dialog
              v-model="dialog"
              width="800px"
              transition="dialog-bottom-transition"
              scrollable
            >
              <v-card v-model="appointment_selection">
                <v-toolbar dark color="primary" height="50px">
                  <v-col cols="1">
                    <v-btn icon dark @click="dialog = false">
                      <v-icon>mdi-close</v-icon>
                    </v-btn>
                  </v-col>
                  <v-col cols="9"
                    ><v-row justify="center"
                      ><v-toolbar-title>Thông tin khám</v-toolbar-title></v-row
                    ></v-col
                  >
                  <v-spacer></v-spacer>
                </v-toolbar>
                <v-footer color="white" height="100px">
                  <v-row>
                    <v-col cols="3">
                      <v-row justify="center"
                        ><p class="font-weight-medium">Chuyên khoa</p></v-row
                      >
                      <v-row justify="center">{{
                        appointment_selection.doctor.specialty
                      }}</v-row>
                    </v-col>
                    <v-col cols="3"
                      ><v-row justify="center"
                        ><p class="font-weight-medium">Bác sĩ</p></v-row
                      ><v-row justify="center">{{
                        appointment_selection.doctor.name
                      }}</v-row></v-col
                    >
                    <v-col cols="3"
                      ><v-row justify="center"
                        ><p class="font-weight-medium">Ngày khám</p></v-row
                      ><v-row justify="center">{{
                        appointment_selection.date
                      }}</v-row>
                      <v-row justify="center">{{
                        appointment_selection.time
                      }}</v-row></v-col
                    >
                    <v-col cols="3"
                      ><v-row justify="center"
                        ><p class="font-weight-medium">Phòng</p></v-row
                      ><v-row justify="center">{{
                        appointment_selection.room.name
                      }}</v-row></v-col
                    >
                  </v-row>
                  <!-- <v-row><v-btn>Vào phòng khám</v-btn></v-row> -->
                </v-footer>
                <v-footer color="white" class="pa-10">
                  <v-row>
                    <v-col cols="8" class="ml-10">
                      <v-row class="mb-10">
                        <p class="font-weight-medium mr-1">STT:</p>
                        {{ appointment_selection.stt }}</v-row
                      >
                      <v-row>
                        <p class="font-weight-medium mr-1">Triệu chứng:</p>
                        {{ appointment_selection.symptom }}</v-row
                      >
                      <v-row>
                        <p class="font-weight-medium mr-1">Mô tả chi tiết:</p>
                        {{ appointment_selection.description }}</v-row
                      >
                      <v-row>
                        <p class="font-weight-medium mr-1">
                          Thời gian diễn ra tình trạng:
                        </p>
                        {{ appointment_selection.timeSituation }}</v-row
                      >
                      <v-row
                        v-if="appointment_selection.selfTreatment === true"
                      >
                        <p class="font-weight-medium mr-1">
                          Đã tự điều trị bệnh:
                        </p>
                        có
                      </v-row>
                      <v-row v-else
                        ><p class="font-weight-medium mr-1">
                          Đã tự điều trị bệnh:
                        </p>
                        chưa</v-row
                      >
                      <v-row
                        ><p class="font-weight-medium mr-1">
                          Kết quả xét nghiêm trước đó:
                        </p>
                      </v-row>
                    </v-col>
                    <v-col cols="3">
                      <v-row class="my-5">
                        <v-btn color="primary" @click="gotoRoom"
                          >Vào khám</v-btn
                        ></v-row
                      >
                      <v-row class="my-5">
                        <v-btn color="primary">Bệnh án</v-btn></v-row
                      >
                    </v-col>
                  </v-row>
                </v-footer>
                <v-toolbar dark color="primary" height="50px">
                  <v-col cols="12"
                    ><v-row justify="center"
                      ><v-toolbar-title
                        >Thông tin bệnh nhân</v-toolbar-title
                      ></v-row
                    ></v-col
                  >
                  <v-spacer></v-spacer>
                </v-toolbar>
                <v-footer color="white" class="pa-10">
                  <v-row>
                    <v-col cols="6">
                      <v-row
                        ><p class="font-weight-medium mr-1">Họ và tên:</p>
                        {{ appointment_selection.profile.name }}
                      </v-row>
                      <v-row
                        ><p class="font-weight-medium mr-1">Ngày sinh:</p>
                        {{ appointment_selection.profile.dob }}
                      </v-row>
                      <v-row
                        ><p class="font-weight-medium mr-1">Giới tính:</p>
                        {{ appointment_selection.profile.gender }}</v-row
                      >
                      <v-row
                        ><p class="font-weight-medium mr-1">Số điện thoại:</p>
                        {{ appointment_selection.profile.phoneNumber }}</v-row
                      >
                    </v-col>
                    <v-col cols="6">
                      <v-row
                        ><p class="font-weight-medium mr-1">CMND:</p>
                        {{ appointment_selection.profile.identityCard }}</v-row
                      >
                      <v-row
                        ><p class="font-weight-medium mr-1">Dân tộc:</p>
                        {{ appointment_selection.profile.folk }}</v-row
                      >
                      <v-row
                        ><p class="font-weight-medium mr-1">Nghề nghiệp:</p>
                        {{ appointment_selection.profile.job }}</v-row
                      >
                    </v-col>
                  </v-row>
                </v-footer>
                <div style="flex: 1 1 auto"></div>
              </v-card>
            </v-dialog>
          </v-col>
        </v-card>
      </v-col>
      <v-col cols="4"
        ><v-card width="100%" min-height="400">
          <v-footer padless width="100%" color="primary" class="white--text">
            <v-col class="text-center" cols="12">
              <strong>Tìm kiếm lịch hẹn</strong>
            </v-col>
          </v-footer>
          <label>Chọn hồ sơ bệnh nhân</label>
          <v-select
            v-model="profile_id_selection"
            :items="profile_patient_list"
            item-text="name"
            item-value="id"
            label="Solo field"
            solo
          ></v-select>
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
                v-model="start_date"
                label="Ngày bắt đầu"
                prepend-icon="mdi-calendar"
                readonly
                v-bind="attrs"
                v-on="on"
              ></v-text-field>
            </template>
            <v-date-picker
              v-model="start_date"
              @input="menu2 = false"
              locale="vi-cn"
            ></v-date-picker>
          </v-menu>
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
                v-model="end_date"
                label="Ngày kết thúc"
                prepend-icon="mdi-calendar"
                readonly
                v-bind="attrs"
                v-on="on"
              ></v-text-field>
            </template>
            <v-date-picker
              v-model="end_date"
              @input="menu2 = false"
              locale="vi-cn"
            ></v-date-picker>
          </v-menu>
          <v-row class="mt-5" justify="center"
            ><v-btn color="primary" @click="getAppointmentlist">
              Tìm kiếm
            </v-btn></v-row
          >
        </v-card>
      </v-col>
    </div>
  </div>
</template>

<script>
export default {
  data: () => ({
    dialog: false,
    profile_patient_list: [],
    start_date: "",
    end_date: "",
    profile_id_selection: "",
    appointment_list: [
      // {
      //   id: "0ef258bf-2f25-4c10-adee-70abe7a7da7c",
      //   stt: 1,
      //   room: {
      //     id: "b7170875-0bd8-4e22-b8fb-67f8e070971d",
      //     name: "H2",
      //     link: null,
      //   },
      //   profile: {
      //     id: "da254d38-5430-4862-9937-1d44e5efea22",
      //     profileNumber: 1,
      //     name: "john",
      //     address: "address",
      //     phoneNumber: "phone",
      //     dob: "2022-04-12T00:53:16.226+00:00",
      //     job: "job",
      //     identityCard: "identity",
      //     healthInsurance: "healthy",
      //     folk: "folk",
      //     gender: "gender",
      //     protector: "protector",
      //   },
      //   doctor: {
      //     id: "92cfcb8e-b815-46b9-b7bf-b016ed843fd2",
      //     name: "Doctor",
      //     specialty: "Nhi Khoa",
      //     level: "CKI",
      //   },
      //   date: "2022-04-13",
      //   time: "10:00-11:00",
      //   symptom: "aaa",
      //   description: "ddd",
      //   timeSituation: "12 tuần",
      //   selfTreatment: true,
      //   files: [],
      //   status: "ACTIVE",
      //   createdDate: "2022-04-12T07:38:30.786+00:00",
      // },
    ],

    appointment_selection: {
      id: "",
      stt: 1,
      room: {
        id: "",
        name: "",
        link: null,
      },
      profile: {
        id: "",
        profileNumber: 1,
        name: "",
        address: "",
        phoneNumber: "",
        dob: "",
        job: "",
        identityCard: "",
        healthInsurance: "",
        folk: "",
        gender: "",
        protector: "",
      },
      doctor: {
        id: "",
        name: "",
        specialty: "",
        level: "",
      },
      date: "",
      time: "",
      symptom: "",
      description: "",
      timeSituation: "",
      selfTreatment: true,
      files: [],
      status: "",
      createdDate: "",
    },
  }),

  created() {
    this.getProfileList();
  },
  methods: {
    async getProfileList() {
      const params = {
        token: this.$store.getters["auth/access_token"],
      };

      await this.$store.dispatch("profile/profile_list", params);
      this.profile_patient_list = this.$store.getters["profile/profile_list"];
      //console.log(this.profile_patient_list);
    },

    async getAppointmentlist() {
      console.log(this.profile_id_selection);
      const params = {
        token: this.$store.getters["auth/access_token"],
        data: {
          profileId: this.profile_id_selection,
        },
      };

      await this.$store.dispatch(
        "appointment/getAppointment_byProfileId",
        params
      );
      this.appointment_list =
        this.$store.getters["appointment/user_appointment_list"];
      console.log(this.appointment_list);
    },
    selectAppointment(appointment) {
      this.dialog = true;
      this.appointment_selection = appointment;
    },

    gotoRoom() {
      window.open("https://www.google.com/");
    },
  },
};
</script>
