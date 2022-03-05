<template>
  <div class="content">
    <div class="md-layout select-date">
            <!-- calendar -->
      <div class="calendar" v-if="calendar">
        <div class="calendar-row-month">
        </div>
            <!-- head -->
        <div class="calendar-row head">
          <div v-for="(item, index) in dateOfWeek" :key="index" class="row-item">
            <strong v-text="item" />
          </div>
        </div>
            <!-- body -->
        <div
        v-for="(row, row_index) in calendar"
        :key="row_index"
        class="calendar-row"
        >
          <div
            v-for="(cell, cell_index) in row"
            :key="cell_index"
            class="row-item" @click="changeBackgroundRow(cell_index)"
          >
            <span v-text="cell ? cell : ''" />  
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss">
.calendar {
  margin:auto;
  //padding: 0 1em;
  .calendar-row {
    display: flex;
    &.head {
      & > div {
        height: 64px;
        //color: #036666;
        color: white;
        //background-color: #99E2B4;
        background-color: #4caf50;
        opacity: 0.87;
        border: thin solid #78C6A3;
        display: flex;
        align-items: center;
        justify-content: center;
      }
    }

    .row-item {
      flex-grow: 1;
      
      position: relative;
      border-top: none;
      border: thin solid #78C6A3;
      
      min-width: 100px;
      width: calc(100% / 7);
   	  height: 100px;
      background: #FFFFFF;
      display:inline-block;
      
      & > span {
        position: absolute;
        color: #7184a3;
        text-align: center;
        font-size: 16px;
        height: 27px;
        width: 27px;
        padding: 3px;
      }
    }

    .row-item:hover{
      &>span{
        background-color: #F9C74F;
        border-radius: 50%;
      }
      cursor: pointer;
    }
    
    
  }
  border: thin solid #78C6A3;
}
.select-date{
  padding: 50px;
}
.continue-button{
  float: right;
}

</style>

<script>

export default {
  data() {
    return {
      dateOfWeek: ["Chủ nhật", "Thứ hai", "Thứ ba", "Thứ tư", "Thứ năm", "Thứ sáu", "Thứ bảy"],
      calendar: null,
    };
  },
  created() {
    // sau khi khởi tạo component thì mình gọi hàm buildCalendar này để hiển thị lịch tháng 1 năm 2021
    this.buildCalendar(3,2022)
  },
  methods: {
    // lấy ngày đầu và ngày cuối trong tháng
    getRangeMonth(numbMonth, numbYear) {
      numbMonth = Number(numbMonth) || new Date().getMonth() + 1;
      numbYear = Number(numbYear) || new Date().getFullYear();
      return {
        firstDay: new Date(`${numbYear}-${numbMonth}-1`),
        lastDay: new Date(`${numbYear}-${numbMonth}-${new Date(numbYear, numbMonth, 0).getDate()}`)
      };
    },
    buildCalendar(month, year) {
      let arr = [[]]
      // lấy ngày đầu và ngày cuối trong tháng
      let { firstDay, lastDay } = this.getRangeMonth(month, year);
      // đếm ngày
      let countDay = 1;
      // nếu countDay lớn hơn số ngày cuối trong tháng thì mình dừng vòng lặp
      for (let i = 1; countDay <= lastDay.getDate(); i++) {
        for (let j = 0; j < 7; j++) {
          // first loop
          if (i == 1) {
            if (j < firstDay.getDay()) arr[arr.length - 1].push(0);
            else {
              arr[arr.length - 1].push(countDay);
              countDay++;
            }
          } else {
            if (countDay > lastDay.getDate()) arr[arr.length - 1].push(0);
            else {
              arr[arr.length - 1].push(countDay);
              countDay++;
            }
          }
        }
        if (countDay > lastDay.getDate()) break;
        arr.push([]);
      }
      this.calendar = arr
    },
    changeBackgroundRow: function(cell_index){
        var buttons = document.getElementsByClassName('row-item');
        console.log(buttons);
        var span = buttons[cell_index].getElementsByTagName('span');
        console.log("amount of span" + span.length);
        //span[0].style.backgroundColor = 'blue';
    }

  }
    
};
</script>