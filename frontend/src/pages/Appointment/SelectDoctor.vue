<template>
  <v-row justify="center">
    <v-expansion-panels accordion>
      <v-expansion-panel v-for="(doctor, index) in listDoctor" :key="index">
        <v-expansion-panel-header>
          <div class="md-layout">{{ doctor.name }}</div>
        </v-expansion-panel-header>
        <div class="md-layout doctor-room">Phòng {{ doctor.room }}</div>
        <v-expansion-panel-content>
          <v-btn-toggle
            v-model="time_select"
            v-for="(time, time_index) in doctor.time_available"
            :key="time_index"
            tile
            color="brown darken-1"
            group
          >
            <v-btn
              v-if="time == 1"
              value="1"
              @click="setSelectDoctor(doctor.name, doctor.room, '7:00-8:00')"
              >7:00-8:00</v-btn
            >
            <v-btn
              v-if="time == 2"
              value="2"
              @click="setSelectDoctor(doctor.name, doctor.room, '8:00-9:00')"
              >8:00-9:00</v-btn
            >
            <v-btn
              v-if="time == 3"
              value="3"
              @click="setSelectDoctor(doctor.name, doctor.room, '9:00-10:00')"
              >9:00-10:00</v-btn
            >
            <v-btn
              v-if="time == 4"
              value="4"
              @click="setSelectDoctor(doctor.name, doctor.room, '10:00-11:00')"
              >10:00-11:00</v-btn
            >
            <v-btn
              v-if="time == 5"
              value="5"
              @click="setSelectDoctor(doctor.name, doctor.room, '13:00-14:00')"
              >13:00-14:00</v-btn
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
  data() {
    return {
      time_select: null,
      listDoctor: [
        {
          name: "BSCKII. Lâm Phương Nam",
          room: "48",
          time_available: [1, 2, 3, 4, 5],
        },
        {
          name: "BSCKII. Lê Tố",
          room: "49",
          time_available: [1, 2, 3, 4, 5],
        },
        {
          name: "BSCKII. Nguyễn Hoàng",
          room: "50",
          time_available: [1, 2, 3, 4, 5],
        },
      ],
      doctorSelection: null,
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
