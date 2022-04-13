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
                      @click="dialog = true"
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
              width="60%"
              transition="dialog-bottom-transition"
              scrollable
            >
              <v-card>
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
                      <v-row justify="center">Chuyên khoa</v-row>
                      <v-row justify="center">Nhi</v-row>
                    </v-col>
                    <v-col cols="3"
                      ><v-row justify="center">Bác sĩ</v-row
                      ><v-row justify="center">Doctor</v-row></v-col
                    >
                    <v-col cols="3"
                      ><v-row justify="center">Ngày khám</v-row
                      ><v-row justify="center">2022-04-21</v-row></v-col
                    >
                    <v-col cols="3"
                      ><v-row justify="center">Phòng</v-row
                      ><v-row justify="center">H2</v-row></v-col
                    >
                  </v-row>
                  <!-- <v-row><v-btn>Vào phòng khám</v-btn></v-row> -->
                </v-footer
                >
                 <v-footer color="white" height="100px">
                   <v-row><v-col cols="12">STT</v-col></v-row>
                  <v-row justify="center"><v-btn>Vào phòng khám</v-btn></v-row>
                 </v-footer
                >

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
      {
        date: "1111",
        doctor: {
          specialty: "Nhi",
        },
        profile: {
          name: "john",
        },
        status: "ACTIVE",
      },
    ],
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

    // async getAppointmentlist() {
    //   console.log(this.profile_id_selection);
    //   const params = {
    //     token: this.$store.getters["auth/access_token"],
    //     data: {
    //       profileId: this.profile_id_selection,
    //     },
    //   };

    //   await this.$store.dispatch(
    //     "appointment/getAppointment_byProfileId",
    //     params
    //   );
    //   this.appointment_list =
    //     this.$store.getters["appointment/user_appointment_list"];
    //   console.log(this.appointment_list);
    // },
  },
};
</script>
