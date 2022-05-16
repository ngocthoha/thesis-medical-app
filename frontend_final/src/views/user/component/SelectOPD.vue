<template>
  <v-app class="d-flex flex-column">
    <v-row justify="center" style="background-color: #f5f7fa;">
      <v-card width="80%" height="700px" elevation="6">
        <v-row justify="center" class="mt-16">
          <v-col cols="9">
            <v-row class="mt-5 ml-2">
              <v-btn
                elevation="1"
                color="#E6F0F5"
                class="mr-3"
                @click="backToProfile"
                ><strong style="color: #3C89AB;">Quay lại</strong></v-btn
              >
              <v-btn elevation="1" color="#eceefb"
                ><strong style="color: #6e6fd9;">Chọn</strong></v-btn
              >
            </v-row>
          </v-col>
        </v-row>
      </v-card>
    </v-row>
  </v-app>
</template>
