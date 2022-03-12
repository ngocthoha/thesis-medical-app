<template>
  <div class="content">
    <div class="md-layout">
      <div
        class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-20"
      >
        <div class="nutThaoTac">
          <div>
            <md-button
              class="md-raised md-primary"
              style="background-color: #448aff !important"
              >Tạo Hồ sơ</md-button
            >
          </div>
          <div>
            <md-button
              class="md-raised md-accent"
              style="background-color: #ff5252 !important"
              >Xóa hồ sơ</md-button
            >
          </div>

          <h6><b>Lọc danh sách hồ sơ</b></h6>
          <p>Ngày bắt đầu</p>
          <md-datepicker
            v-model="selectedDate1"
            md-immediately
            style="width: 80%; margin-left: auto; margin-right: auto"
          />
          <p>Ngày kết thúc</p>
          <md-datepicker
            v-model="selectedDate2"
            md-immediately
            style="width: 80%; margin-left: auto; margin-right: auto"
          />
          <md-button class="md-raised">Lọc danh sách</md-button>
        </div>
      </div>

      <div
        class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-75"
      >
        
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: "HoSoBenhNhan",
  data() {
    return {
      selectedDate1: new Date(),
      selectedDate2: new Date(),
    };
  },
};
</script>

<style lang="scss" scoped>
.nutThaoTac {
  background-color: #ffffff;
  text-align: center;
  border-radius: 15px;
  box-shadow: 1px 1px 1px 1px #b8b5b5;
  margin-top: 23px;
}
</style>
