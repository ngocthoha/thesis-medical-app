<template>
  <div class="content">
    <div class="md-layout select-date">
      <v-date-picker
        v-model="date"
        full-width
        class="mt-4"
        locale="vi-cn"
        color="green lighten-1"
      ></v-date-picker>
    </div>
  </div>
</template>

<style lang="scss">
.select-date {
  padding: 50px;
}
</style>

<script>
export default {
  data() {
    return {
      date: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
        .toISOString()
        .substr(0, 10),
    };
  },
};
</script>
