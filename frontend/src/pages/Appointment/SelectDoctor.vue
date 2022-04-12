<template>
  <v-row justify="center">
    <v-expansion-panels flat>
      <v-expansion-panel v-for="(doctor, index) in listDoctor" :key="index">
        <div class="panel-header">
          <v-expansion-panel-header>
            <div class="md-layout font-weight-bold">
              {{ doctor.doctor.level }}. {{ doctor.doctor.name }}
            </div>
          </v-expansion-panel-header>
          <div class="md-layout doctor-room">Phòng {{ doctor.room.name }}</div>
          <v-expansion-panel-content>
            <v-btn-toggle
              v-model="time_select"
              v-for="(time, time_index) in doctor.times"
              :key="time_index"
              color="green lighten-1"
            >
              <v-btn
                outlined
                color="green lighten-1"
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
    <div class="content">{{ this.doctorSelection }}</div>
  </v-row>
</template>

<script>
export default {
  mounted() {
    this.getDoctorList();
  },

  props: {
    listDoctor: Array,
  },

  data() {
    return {
      time_select: null,
      doctorSelection: null,
      // listDoctor: [
      //   {
      //     id: 3,
      //     date: "16",
      //     room: "H2 á",
      //     times: ["10-11", "11-12"],
      //     doctor: {
      //       id: "3e2c4b76-d826-4202-a987-7108047f7fb8",
      //       name: "John",
      //       specialty: "NHI KHOA",
      //       level: "level",
      //     },
      //   },
      // ],
    };
  },

  methods: {
    setSelectDoctor(doctor_id, doctor_name, doctor_room, doctor_time) {
      this.doctorSelection = {
        id: doctor_id,
        name: doctor_name,
        room: doctor_room,
        time: doctor_time,
      };
    },
  },
};
</script>
<style lang="scss" scoped>
$color-pack: false;
.doctor-room {
  margin-left: 50px;
}
.panel-header {
  border-style: solid;
  border-color: #66bb6a;
  margin: 1px;
}
</style>
