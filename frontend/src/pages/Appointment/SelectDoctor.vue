<template>
  <v-row justify="center">
    <v-expansion-panels accordion>
      <v-expansion-panel v-for="(item, i) in 5" :key="i">
        <v-expansion-panel-header>
          <div class="md-layout">BSCKII. Lâm Phương Nam</div>
        </v-expansion-panel-header>
        <div class="md-layout doctor-room">Phòng 48</div>
        <v-expansion-panel-content>
          <v-btn-toggle v-model="text" tile color="brown darken-1" group>
            <v-btn value="left">7:00-8:00</v-btn>

            <v-btn value="center">8:00-9:00</v-btn>

            <v-btn value="right">9:00-10:00</v-btn>

            <v-btn value="justify">11:00-12:00</v-btn>
          </v-btn-toggle>
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels>
  </v-row>
</template>
<style lang="scss" scoped>
@import "~vuetify/src/styles/main.sass";
$color-pack: false;
.doctor-room {
  margin-left: 50px;
}
</style>
