<template>
  <v-row justify="center">
    <v-expansion-panels accordion>
      <v-expansion-panel v-for="(doctor, index) in listDoctor" :key="index">
        <v-expansion-panel-header>
          <div class="md-layout">
            {{ doctor.doctor.level }}. {{ doctor.doctor.name }}
          </div>
        </v-expansion-panel-header>
        <div class="md-layout doctor-room">Phòng {{ doctor.room }}</div>
        <v-expansion-panel-content>
          <v-btn-toggle
            v-model="time_select"
            v-for="(time, time_index) in doctor.times"
            :key="time_index"
            tile
            color="brown darken-1"
            group
          >
            <!-- <v-btn
              v-if="time == 1"
              value="1"
              @click="
                setSelectDoctor(doctor.doctor.name, doctor.room, '7:00-8:00')
              "
              >7:00-8:00</v-btn
            >
            <v-btn
              v-if="time == 2"
              value="2"
              @click="
                setSelectDoctor(doctor.doctor.name, doctor.room, '8:00-9:00')
              "
              >8:00-9:00</v-btn
            >
            <v-btn
              v-if="time == 3"
              value="3"
              @click="
                setSelectDoctor(doctor.doctor.name, doctor.room, '9:00-10:00')
              "
              >9:00-10:00</v-btn
            >
            <v-btn
              v-if="time == '10-11'"
              value="4"
              @click="
                setSelectDoctor(doctor.doctor.name, doctor.room, '10:00-11:00')
              "
              >10:00-11:00</v-btn
            >
            <v-btn
              v-if="time == '11-12'"
              value="5"
              @click="
                setSelectDoctor(doctor.doctor.name, doctor.room, '13:00-14:00')
              "
              >13:00-14:00</v-btn
            > -->
            <v-btn
              :value="time"
              @click="
                setSelectDoctor(doctor.doctor.name, doctor.room, time)
              "
              >{{time}}</v-btn
            >
          </v-btn-toggle>
        </v-expansion-panel-content>
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

  props:{
    listDoctor:Array
  },

  data() {
    return {
      time_select: null,
      doctorSelection: null,
      // listDoctor: [
      //   // {
      //   //   id: 3,
      //   //   date: "16",
      //   //   room: "H2 á",
      //   //   times: ["10-11", "11-12"],
      //   //   doctor: {
      //   //     id: "3e2c4b76-d826-4202-a987-7108047f7fb8",
      //   //     name: "John",
      //   //     specialty: "NHI KHOA",
      //   //     level: "level",
      //   //   },
      //   // },
      // ],
    };
  },

  methods: {
    setSelectDoctor(doctor_name, doctor_room, doctor_time) {
      this.doctorSelection = {
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
</style>
