<template>
  <div>
    <v-card width="922px" elevation="1" class="pb-8">
      <v-expand-transition>
        <appointment-list
          v-show="!visible_appointment_detail"
          v-on:viewDetail="view_appointment_detail"
        ></appointment-list>
      </v-expand-transition>
      <appointment-detail
        v-on:appointmentDetailClose="close_view_appointment_detail"
        v-if="visible_appointment_detail"
      ></appointment-detail>
    </v-card>
  </div>
</template>

<script>
import appointmentList from "./component/appointment_history/appointment_list.vue";
import appointmentDetail from "./component/appointment_history/appointment_detail.vue";
export default {
  components: {
    appointmentList,
    appointmentDetail
  },
  data() {
    return {
      visible_appointment_detail: false,
      appointment_detail: null
    };
  },
  methods: {
    view_appointment_detail(appointment) {
      this.appointmentDetail = appointment;
      this.visible_appointment_detail = true;
    },

    close_view_appointment_detail() {
      this.appointmentDetail = null;
      this.visible_appointment_detail = false;
    }
  }
};
</script>
