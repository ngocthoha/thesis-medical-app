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
          <v-col v-for="(item, i) in 5" :key="i" cols="12">
            <v-row class="my-1" justify="center" width="100%"
              ><v-card width="80%" class="pa-4"
                ><v-row width="100%"
                  ><v-col class="pa-2" cols="5">
                    <strong>Ngày khám: 12/12/2020</strong></v-col
                  ><v-col class="pa-2" cols="5">
                    <strong>Tình trạng: chưa khám </strong></v-col
                  >
                  <v-col class="pa-2" cols="2">
                    <v-btn class="mx-2" fab dark small color="primary">
                      <v-icon dark> mdi-table-eye </v-icon>
                    </v-btn></v-col
                  ></v-row
                ><v-row width="100%"
                  ><v-col class="pa-2" cols="5">
                    <strong> Chuyên Khoa: TIM MẠCH</strong></v-col
                  ></v-row
                ><v-row width="100%"
                  ><v-col class="pa-2" cols="5">
                    <strong>Bệnh nhân: Nguyễn Duy Thanh</strong></v-col
                  ></v-row
                ></v-card
              ></v-row
            ></v-col
          >
        </v-card></v-col
      >
      <v-col cols="4"
        ><v-card width="100%" min-height="400">
          <v-footer padless width="100%" color="primary" class="white--text">
            <v-col class="text-center" cols="12">
              <strong>Tìm kiếm lịch hẹn</strong>
            </v-col>
          </v-footer>
          <label>Chọn hồ sơ bệnh nhân</label>
          <v-select
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
            ><v-btn color="primary"> Tìm kiếm </v-btn></v-row
          >
        </v-card>
      </v-col>
    </div>
  </div>
</template>

<script>
export default {
  data: () => ({
    profile_patient_list: [],
    start_date: "",
    end_date: "",
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
  },
};
</script>
