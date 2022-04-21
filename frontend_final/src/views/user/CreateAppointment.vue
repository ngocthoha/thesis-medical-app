<template>
  <v-app>
    <div class="content" style="background-color: #F3FCFF">
      <v-row justify="center">
        <v-stepper class="my-10" alt-labels style="width:80%">
          <v-stepper-header>
            <v-stepper-step :complete="step.step1" step="1" color="#046792">
              <v-row justify="center">Chọn hồ sơ</v-row>
            </v-stepper-step>

            <v-spacer />

            <v-stepper-step :complete="step.step2" step="2" color="#046792">
              Chọn ngày khám
            </v-stepper-step>

            <v-spacer />

            <v-stepper-step :complete="step.step3" step="3" color="#046792">
              Chọn chuyên khoa khám
            </v-stepper-step>
            <v-spacer />

            <v-stepper-step :complete="step.step4" step="4" color="#046792">
              Chọn bác sĩ và khung giờ khám
            </v-stepper-step>
          </v-stepper-header>
        </v-stepper>
      </v-row>
      <div v-show="false">
        <select-profile-patient></select-profile-patient>
      </div>
      <div v-show="true">
        <select-date></select-date>
      </div>
    </div>
  </v-app>
</template>

<script>
import SelectProfilePatient from "./component/SelectProfilePatient.vue";
import SelectDate from "./component/SelectDate.vue";
export default {
  components: {
    SelectProfilePatient,
    SelectDate
  },
  data() {
    return {
      step: {
        step1: false,
        step2: false,
        step3: false,
        step4: false,
        step5: false
      }
    };
  }
};
</script>








