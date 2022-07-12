<template>
  <v-app class="d-flex flex-column">
    <v-row justify="center" style="background-color: #f5f7fa;">
      <v-card width="80%" height="700px" elevation="1">
        <v-row justify="center" class="mt-16">
          <v-expansion-panels
            flat
            style="width: 700px; overflow-y:auto; height: 420px;"
            :class="'toolbar-custom'"
            class="elevation-1 pa-3"
          >
            <v-expansion-panel
              v-for="(doctor, index) in listDoctor"
              :key="index"
              class="mb-5"
            >
              <div class="panel-header">
                <v-expansion-panel-header>
                  <div class="font-weight-bold" style="color:#046792">
                    {{ doctor.doctor.level }}. {{ doctor.doctor.name }}
                  </div>
                </v-expansion-panel-header>
                <div class="doctor-room mb-1">Phòng {{ doctor.room.name }}</div>
                <v-expansion-panel-content
                  style="border-top: solid; border-color: #ffdfcc6e; "
                >
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
                      color="white"
                      :value="time"
                      style="border:none"
                      class="mt-3 mr-3"
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
          room: { name: "H2" },
          times: ["10:00-11:00", "11:00-12:00"],
          doctor: {
            id: "3e2c4b76-d826-4202-a987-7108047f7fb8",
            name: "John",
            specialty: "NHI KHOA",
            level: "level"
          }
        },
        {
          id: 4,
          date: "16",
          room: { name: "H2" },
          times: ["10:00-11:00", "11:00-12:00"],
          doctor: {
            id: "3e2c4b76-d826-4202-a987-7108047f7fb8",
            name: "Simon",
            specialty: "NHI KHOA",
            level: "level"
          }
        },
        {
          id: 5,
          date: "16",
          room: { name: "H2" },
          times: ["10:00-11:00", "11:00-12:00"],
          doctor: {
            id: "3e2c4b76-d826-4202-a987-7108047f7fb8",
            name: "Simon",
            specialty: "NHI KHOA",
            level: "level"
          }
        },
        {
          id: 6,
          date: "16",
          room: { name: "H2" },
          times: ["10:00-11:00", "11:00-12:00"],
          doctor: {
            id: "3e2c4b76-d826-4202-a987-7108047f7fb8",
            name: "Simon",
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
  margin-left: 25px;
  font-size: 14px;
}
.panel-header {
  border-style: solid;
  border-color: #ffdfcc6e;
  border-radius: 5px;
}
.toolbar-custom::-webkit-scrollbar {
  width: 15px;
}

.toolbar-custom::-webkit-scrollbar-thumb {
  background: #b0b0b0;
  border: solid 3px white;
  border-radius: 7px;
}

.toolbar-custom::-webkit-scrollbar-thumb:hover {
  background: #fec3a6;
  //border: solid 3px #ffdfcc;
}

.toolbar-custom::-webkit-scrollbar {
  width: 15px;
}

.dark::-webkit-scrollbar-track {
  background: #202020;
  border-left: 1px solid #2c2c2c;
}

.dark::-webkit-scrollbar-thumb:hover {
  background: white;
}

.theme--light.v-expansion-panels .v-expansion-panel {
  background-color: #fff4ee;
}

.theme--light.v-btn-toggle:not(.v-btn-toggle--group) {
  background: #fff4ee;
}
</style>
