<template>
  <!-- <div class="select-date">
    <v-date-picker
      v-model="date"
      width="800px"
      class="mt-4"
      locale="vi-cn"
      color="green lighten-1"
    ></v-date-picker>
  </div> -->
  <v-app class="d-flex flex-column">
    <v-row justify="center" style="background-color: #F3FCFF;">
      <v-col cols="9">
        <v-date-picker
          v-model="date"
          full-width
          class="mt-4"
          locale="vi-cn"
          color="#3685a8"
        ></v-date-picker>
        <v-row class="mt-5 ml-2">
          <v-btn elevation="1" color="#E6F0F5" class="mr-3"
            ><strong style="color: #3C89AB;">Quay lại</strong></v-btn
          >
          <v-btn elevation="1" color="#eceefb"
            ><strong style="color: #6e6fd9;">Chọn</strong></v-btn
          >
        </v-row>
      </v-col>
    </v-row>
  </v-app>
</template>

<script>
import View from "../../../layouts/default/View.vue";
export default {
  components: { View },
  data() {
    return {
      date: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
        .toISOString()
        .substr(0, 10)
    };
  }
};
</script>
