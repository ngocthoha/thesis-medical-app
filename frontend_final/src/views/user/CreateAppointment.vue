<template>
  <v-app>
    <!-- <v-sheet width="100%" color="#f8f9fd">
      <v-row justify="center">
        <v-slide-group show-arrows>
          <v-slide-item v-for="(item, index) in links" :key="index">
            <div>
              <v-card
                color="#eceefb"
                class="pa-2 mx-10 mb-5"
                height="100px"
                width="100px"
                hover="true"
                ><v-row justify="center"
                  ><v-icon large color="purple darken-2" class="my-5">
                    mdi-arrow-up-bold-box-outline</v-icon
                  ></v-row
                ></v-card
              >
              <v-row justify="center" style="font-size: 11px">{{
                item.name
              }}</v-row>
            </div>
          </v-slide-item>
        </v-slide-group>
      </v-row>
    </v-sheet> -->
    <div class="content" style="background-color: #F3FCFF">
      <v-card width="100%" height="120px" color="#eceefb">
        <!-- <v-img v-bind:src="item.icon"> </v-img> -->
        <!-- <strong class="font-weight-black teal--text">
          {{ item.title }}
        </strong> -->
        <v-row justify="center">
          <v-img
            v-bind:src="item.icon"
            max-height="100px"
            max-width="100px"
          ></v-img>
          <strong
            class="display-1 ml-5 mt-7 font-weight-black deep-purple--text text-h3"
          >
            {{ item.title }}
          </strong>
          <!-- <v-img height="100px" width="100px" v-bind:src="item.icon"> </v-img> -->
          <!-- <v-img height="100px" width="100px" v-bind:src="item.icon"> </v-img>
          <strong class="display-1 py-4 my-0 font-weight-black teal--text">
            {{ item.title }}
          </strong> -->
        </v-row>
      </v-card>
      <!-- <v-footer width="100%" height="100px" color="#eceefb">
        <v-img height="100px" width="100px" v-bind:src="item.icon"> </v-img>
      </v-footer> -->
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
      <Transition name="slide-fade">
        <div v-if="show">
          <select-profile-patient
            @select-complete="profileSelect"
          ></select-profile-patient>
        </div>
      </Transition>
      <Transition name="slide-fade">
        <div v-show="!show">
          <select-date
            ref="select-date"
            @back-to-profile="dateSelect"
          ></select-date>
        </div>
      </Transition>
      <div v-show="false">
        <select-profile-patient></select-profile-patient>
      </div>
      <div v-show="false">
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
      item: {
        icon: `${require(`@/assets/img/service/timetable.png`)}`,
        title: "Đặt lịch khám theo ngày",
        subChips: ["Tiện lợi", "Nhanh chóng"],
        linkDetails: "",
        color: "#e7f0f5"
      },

      step: {
        step1: false,
        step2: false,
        step3: false,
        step4: false,
        step5: false
      },
      show: true
    };
  },

  methods: {
    profileSelect(check) {
      console.log(check);
      this.show = false;
    },

    dateSelect() {
      console.log("back to profile from parent");
      this.show = true;
    }
  }
};
</script>

<style scoped>
.slide-fade-enter-active {
  transition: all 1s ease;
}
.slide-fade-leave-active {
  transition: all 0.8s cubic-bezier(1, 0.5, 0.8, 1);
}
.slide-fade-enter, .slide-fade-leave-to
/* .slide-fade-leave-active below version 2.1.8 */ {
  transform: translateX(10px);
  opacity: 0;
}
</style>
