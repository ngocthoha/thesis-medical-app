<template>
  <div>
    <v-card width="922px" elevation="4" class="pb-8">
      <div>
        <!-- header -->
        <v-card height="132" class="px-8" elevation="0">
          <p class="font-weight-bold mb-3 pt-5" style="font-size: 24px">
            Hồ sơ sức khỏe
          </p>
          <p style="color: #667085" class="text-body-1">
            Theo dõi quá trình bạn sử dụng dịch vụ tại Blouse Care
          </p>
        </v-card>
        <v-divider
          style="border-color: rgba(16, 24, 40, 0.03) !important"
        ></v-divider>
        <!-- body -->
        <v-card class="pa-8" elevation="0">
          <v-timeline>
            <v-timeline-item
              v-for="(year, i) in years"
              :key="i"
              :color="year.color"
              small
            >
              <template v-slot:opposite>
                <span
                  :class="`headline font-weight-bold ${year.color}--text`"
                  v-text="year.year"
                ></span>
              </template>
              <div class="py-4">
                <h2
                  :class="`headline font-weight-light mb-4 ${year.color}--text`"
                >
                  Lorem ipsum
                </h2>
                <div>
                  Lorem ipsum dolor sit amet, no nam oblique veritus. Commune
                  scaevola imperdiet nec ut, sed euismod convenire principes at.
                  Est et nobis iisque percipit, an vim zril disputando
                  voluptatibus, vix an salutandi sententiae.
                </div>
              </div>
            </v-timeline-item>
          </v-timeline>
        </v-card>
      </div>
    </v-card>
  </div>
</template>
<script>
export default {
  data: () => ({
    years: [
      {
        color: "cyan",
        year: "1960"
      },
      {
        color: "green",
        year: "1970"
      },
      {
        color: "pink",
        year: "1980"
      },
      {
        color: "amber",
        year: "1990"
      },
      {
        color: "orange",
        year: "2000"
      }
    ]
  })
};
</script>
