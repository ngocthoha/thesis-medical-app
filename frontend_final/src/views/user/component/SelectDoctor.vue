<template>
  <v-app class="d-flex flex-column">
    <v-row justify="center" style="background-color: #f5f7fa;">
      <v-card width="80%" height="700px" elevation="6">
        <v-row justify="center">
          <v-expansion-panels flat style="width: 500px">
            <v-expansion-panel
              v-for="(doctor, index) in listDoctor"
              :key="index"
            >
              <div class="panel-header">
                <v-expansion-panel-header>
                  <div class="md-layout font-weight-bold">
                    {{ doctor.doctor.level }}. {{ doctor.doctor.name }}
                  </div>
                </v-expansion-panel-header>
                <div class="md-layout doctor-room">
                  Phòng {{ doctor.room.name }}
                </div>
                <v-expansion-panel-content>
                  <v-btn-toggle
                    v-model="time_select"
                    v-for="(time, time_index) in doctor.times"
                    :key="time_index"
                    color="blue"
                  >
                    <!-- <v-btn
                      outlined
                      color="#046792"
                      :value="time"
                      @click="
                        setSelectDoctor(
                          doctor.doctor.id,
                          doctor.doctor.name,
                          doctor.room.name,
                          time
                        )
                      "
                      >{{ time }}</v-btn
                    > -->
                    <v-btn
                      elevation="1"
                      color="#E6F0F5"
                      :value="time"
                      style="border:none"
                      @click="
                        setSelectDoctor(
                          doctor.doctor.id,
                          doctor.doctor.name,
                          doctor.room.name,
                          time
                        )
                      "
                      ><strong style="color: #3C89AB;">{{
                        time
                      }}</strong></v-btn
                    >
                  </v-btn-toggle>
                </v-expansion-panel-content>
              </div>
              <!-- <v-expansion-panel-header>
          <div class="md-layout">
            {{ doctor.doctor.level }}. {{ doctor.doctor.name }}
          </div>
        </v-expansion-panel-header> -->
            </v-expansion-panel>
          </v-expansion-panels>
        </v-row>
        <v-row justify="center" class="mt-5">
          <v-col cols="5">
            <v-row class="mt-5 ml-2">
              <v-btn elevation="1" color="#E6F0F5" class="mr-3" @click="BackTo"
                ><strong style="color: #3C89AB;">Quay lại</strong></v-btn
              >
              <v-btn elevation="1" color="#eceefb" @click="NextTo"
                ><strong style="color: #6e6fd9;">Chọn</strong></v-btn
              >
            </v-row>
          </v-col>
        </v-row>
      </v-card>
    </v-row>
  </v-app>
</template>

<script>
export default {
  //   mounted() {
  //     this.getDoctorList();
  //   },

  //   props: {
  //     listDoctor: Array
  //   },

  data() {
    return {
      time_select: null,
      doctorSelection: null,
      listDoctor: [
        {
          id: 3,
          date: "16",
          room: "H2 á",
          times: ["10:00-11:00", "11:00-12:00"],
          doctor: {
            id: "3e2c4b76-d826-4202-a987-7108047f7fb8",
            name: "John",
            specialty: "NHI KHOA",
            level: "level"
          }
        }
      ]
    };
  },

  methods: {
    setSelectDoctor(doctor_id, doctor_name, doctor_room, doctor_time) {
      this.doctorSelection = {
        id: doctor_id,
        name: doctor_name,
        room: doctor_room,
        time: doctor_time
      };
    },
    BackTo() {
      this.$emit("invisible", false);
    },
    NextTo() {
      this.$emit("invisible", true);
    }
  }
};
</script>
<style lang="scss" scoped>
$color-pack: false;
.doctor-room {
  margin-left: 50px;
}
.panel-header {
  border-style: solid;
  border-color: #ffdfcc;
  margin: 1px;
}
</style>
