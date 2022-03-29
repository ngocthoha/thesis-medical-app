<template>
  <div class="content">
    <div class="md-layout">
      <div
        class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-30"
      >
        <div class="lich">
          <v-app id="inspire" style="max-height: 400px">
            <v-row justify="center" style="margin: 20px 0px">
              <v-date-picker
                v-model="picker"
                elevation="10"
                flat
              ></v-date-picker>
            </v-row>
          </v-app>
        </div>
        <br />
        <h5><b>Sự kiện sắp diễn ra</b></h5>
        <br />
        <div class="thongbao">
          <md>
            <md-card v-for="(note, index) in note1" :key="index">
              <md-card-header>
                <div class="md-title">{{ note.num }} Bệnh nhân đặt lịch</div>
              </md-card-header>

              <md-card-content> Thời gian: {{ note.time }} </md-card-content>

              <md-card-actions>
                <md-button>Xem thêm</md-button>
              </md-card-actions>
            </md-card>
          </md>
        </div>
        <br />
        <br />
        <div class="thongbao">
          <md>
            <md-card v-for="(note, index) in note2" :key="index">
              <md-card-header>
                <div class="md-title">Tổng số bệnh nhân khám</div>
              </md-card-header>

              <md-card-content>
                <p>Ngày: {{ note.time }}</p>
                Số lượng: {{ note.num }}
              </md-card-content>

              <md-card-actions>
                <md-button>Xem thêm</md-button>
              </md-card-actions>
            </md-card>
          </md>
        </div>
      </div>

      <div
        class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-65"
      >
        <v-app id="inspire">
          <v-card>
            <v-card-title>
              Lịch làm việc của bác sĩ
              <v-spacer></v-spacer>
              <v-text-field
                v-model="search"
                append-icon="mdi-magnify"
                label="Tìm kiếm..."
                single-line
                hide-details
              ></v-text-field>
            </v-card-title>
            <v-data-table
              :headers="headers"
              :items="desserts"
              :search="search"
              show-expand
              class="elevation-1"
              :expanded.sync="expanded"
            >
              <!-- <template v-slot:expanded-item="{ headers, item }">
                <td :colspan="headers.length" style="text-align: center">
                  <p style="margin-top: 20px">
                    Thông tin thêm: {{ item.chitiet }}
                  </p>

                  <v-dialog v-model="dialog" scrollable max-width="800px">
                    <template v-slot:activator="{ on, attrs }">
                      <v-btn
                        color="#333"
                        dark
                        v-bind="attrs"
                        v-on="on"
                        style="margin: 0px 20px 10px 20px"
                      >
                        Chi tiết
                      </v-btn>
                    </template>
                    <v-card>
                      <v-card-title>Thông tin bệnh nhân</v-card-title>
                      <v-divider></v-divider>
                      <v-card-text style="height: auto">
                        <h5>
                          <b>Ngày {{ item.date }}  ,   {{ item.time }} , Phòng số {{ item.room }} </b>
                        </h5>
                   
                        <h5>
                          <b>ID: {{ item.id }}</b>
                        </h5>
                       
                        <h5><b>Họ và tên: </b> {{ item.name }}</h5>
                        <h5><b>Triệu chứng: </b> {{ item.trieuchung }}</h5>
                        <h5><b>Mô tả chi tiết: </b> {{ item.chitiet }}</h5>
                        <h5>
                          <b>Thời gian diễn ra tình trạng trên: </b> {{item.timebenh}}
                        </h5>
                        <h5><b>Đã tự điều trị bệnh: </b> {{item.tudieutri}}</h5>
                      </v-card-text>
                      <v-divider></v-divider>
                      <v-card-actions>
                        <v-btn
                          color="red"
                          text 
                          :right= true
                          @click="dialog = false"
                          style="flex-direction: row;"
                         
                        >
                          Đóng
                        </v-btn>
                      </v-card-actions>
                    </v-card>
                  </v-dialog>

                  <v-btn
                    color="primary"
                    dark
                    v-bind="attrs"
                    v-on="on"
                    style="margin: 0px 20px 10px 20px"
                  >
                    Tạo hồ sơ bệnh án
                  </v-btn>
                </td>
              </template> -->
            </v-data-table>
          </v-card>
        </v-app>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  created(){
    this.getScheduleList();
  },
  data() {
    return {
      schedule_list: [],
      dialogm1: "",
      dialog: false,
      expanded: [],
      singleExpand: false,
      picker: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
        .toISOString()
        .substr(0, 10),
      search: "",
      headers: [
        // {
        //   text: "ID",
        //   align: "start",
        //   value: "id",
        // },
        { text: "Ngày", value: "date" },
        { text: "Thời gian", value: "time" },
        // { text: "Họ và tên", value: "name" },
        { text: "Phòng", value: "room" },
        // { text: "", value: "data-table-expand" },
      ],
      desserts: [
        // {
        //   id: 1,
        //   date: "2022-03-29",
        //   time: "9:00-10:00",
        //   room: "H2-201",
        //   name: "A",
        //   trieuchung: "Đau bụng",
        //   chitiet: "Đau từng cơn sau khi ăn,thường xuyên buồn nôn và sốt",
        //   timebenh: "1 tuần",
        //   tudieutri: "Có",
        // },
        // {
        //   id: 2,
        //   date: "2022-03-29",
        //   time: "9:00-10:00",
        //   room: "H2-201",
        //   name: "B",
        //   trieuchung: "Đau bụng",
        //   chitiet: "Đau nhiều lần vào ban đêm, khó chịu trong người",
        //   timebenh: "1 tuần",
        //   tudieutri: "Có",
        // },
        // {
        //   id: 3,
        //   date: "2022-03-30",
        //   time: "9:00-10:00",
        //   room: "H2-201",
        //   name: "C",
        //   trieuchung: "Đau bụng",
        //   chitiet: "Đau nhiều lần vào ban đêm, khó chịu trong người",
        //   timebenh: "1 tuần",
        //   tudieutri: "Có",
        // },
      ],
      note1: [
        {
          num: 5,
          time: "10:00-11:00",
        },
      ],
      note2: [
        {
          num: 8,
          time: "10-03-2022",
        },
      ],
    };
  },
  methods:{
    async getScheduleList(){
        const params = {
        token: this.$store.getters["auth/access_token"],
      };

      await this.$store.dispatch("scheduledoctor/schedule_list",params);
      this.desserts = this.$store.getters["scheduledoctor/schedule_list"];
    }
  }
};
</script>

<style lang="scss" scoped>
.thongbao .md-card .md-title {
  font-size: 16px;
  text-align: center;
  color: #ffffff;
}

.thongbao .md-card .md-card-header {
  box-shadow: 0 0 0 0;
  background-color: #448aff;
}

.thongbao .md-button:hover {
  background-color: #e0e0e0 !important;
  color: #333 !important;
}

.danhsachkham .md-icon-image svg {
  flex: 0;
}

.danhsachkham .md-table-cell {
  padding: 15px 13px;
}

.md-dialog .md-dialog-container {
  max-width: 768px;
}

.md-tabs {
  margin-bottom: 24px;
}

.thongtinkham {
  padding: 10px 40px;
}
.hosobenhnhan {
  text-align: center;
}

.lich .theme--light.v-card {
  max-height: 350px;
}
</style>
