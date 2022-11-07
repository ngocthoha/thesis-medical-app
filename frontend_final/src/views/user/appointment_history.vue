<template>
  <div>
    <v-card width="922px" elevation="4" class="pb-8">
      <appointment-list v-show="true"></appointment-list>
      <appointment-detail v-if="false"></appointment-detail>
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
  }
};
</script>
