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
        <div class="danhsachkham">
          <md-table
            v-model="searched"
            md-sort="stt"
            md-sort-order="asc"
            md-card
            md-fixed-header
          >
            <md-table-toolbar>
              <div class="md-toolbar-section-start">
                <h1 class="md-title">Danh sách đặt lịch khám online</h1>
              </div>

              <md-field md-clearable class="md-toolbar-section-end">
                <md-input
                  placeholder="Tìm kiếm ..."
                  v-model="search"
                  @input="searchOnTable"
                />
              </md-field>
            </md-table-toolbar>

            <md-table-empty-state
              md-label="Không tìm thấy kết quả"
              :md-description="`Không có bệnh nhân '${search}'. Vui lòng kiểm tra lại`"
            >
            </md-table-empty-state>

            <md-table-row slot="md-table-row" slot-scope="{ item }">
              <md-table-cell md-label="STT" md-sort-by="stt" md-numeric>{{
                item.stt
              }}</md-table-cell>
              <md-table-cell md-label="Thời gian" md-sort-by="time">{{
                item.time
              }}</md-table-cell>
              <md-table-cell md-label="Họ và tên" md-sort-by="name">{{
                item.name
              }}</md-table-cell>
              <md-table-cell md-label="Triệu chứng" md-sort-by="kham">{{
                item.kham
              }}</md-table-cell>
              <md-table-cell md-label=""
                ><p
                  style="cursor: pointer; color: #448aff"
                  @click="showDialog = true"
                >
                  Chi tiết
                </p></md-table-cell
              >
            </md-table-row>
          </md-table>
        </div>
      </div>
    </div>

    <div class="chitiet">
      <md-dialog :md-active.sync="showDialog">
        <md-dialog-title>Chi tiết</md-dialog-title>

        <br />
        <md-tabs class="md-primary" md-alignment="centered">
          <md-tab id="tab-home" md-label="Thông tin khám">
            <div class="thongtinkham">
              <h5><b>Thời gian khám: </b> 9:00-10:00</h5>
              <h5><b>Họ và tên: </b> Nguyễn Văn A</h5>
              <h5><b>Triệu chứng: </b> Đau dạ dày</h5>
              <h5>
                <b>Mô tả chi tiết: </b> Đau từng cơn sau khi ăn, thường xuyên
                buồn nôn và sốt
              </h5>
              <h5><b>Thời gian diễn ra tình trạng trên: </b> 1 tuần</h5>
              <h5><b>Đã tự điều trị bệnh: </b> Có</h5>
            </div>
          </md-tab>
          <md-tab id="tab-pages" md-label="Hồ sơ bệnh nhân">
            <div class="hosobenhnhan">
              <h4><b>Chưa có hồ sơ!</b></h4>
              <md-button class="md-raised md-primary">Tạo hồ sơ mới</md-button>
            </div>
          </md-tab>
        </md-tabs>

        <md-dialog-actions>
          <md-button class="md-primary" @click="showDialog = false"
            >Close</md-button
          >
        </md-dialog-actions>
      </md-dialog>
    </div>
  </div>
</template>

<script>
const toLower = (text) => {
  return text.toString().toLowerCase();
};

const searchByName = (items, term) => {
  if (term) {
    return items.filter(
      (item) =>
        toLower(item.name).includes(toLower(term)) +
        toLower(item.time).includes(toLower(term)) +
        toLower(item.kham).includes(toLower(term))
    );
  }

  return items;
};

export default {
  name: "LabeledDatepicker",
  data() {
    return {
      picker: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
        .toISOString()
        .substr(0, 10),

      showDialog: false,

      search: null,
      searched: [],
      users: [
        {
          stt: 1,
          name: "Nguyễn Văn A",
          time: "9:00-10:00",
          kham: "Đau dạ dày",
        },
        {
          stt: 2,
          name: "Nguyễn Văn B",
          time: "9:00-10:00",
          kham: "Đau ruột thừa",
        },
        {
          stt: 3,
          name: "Nguyễn Văn C",
          time: "9:00-10:00",
          kham: "Đau vùng bụng",
        },
        {
          stt: 4,
          name: "Nguyễn Văn D",
          time: "9:00-10:00",
          kham: "Đau dạ dày",
        },
        {
          stt: 5,
          name: "Nguyễn Văn E",
          time: "9:00-10:00",
          kham: "Đau dạ dày",
        },
        {
          stt: 6,
          name: "Trần Thị A",
          time: "10:00-11:00",
          kham: "Đau dạ dày",
        },
        {
          stt: 7,
          name: "Trần Thị B",
          time: "10:00-11:00",
          kham: "Đau dạ dày",
        },
        {
          stt: 8,
          name: "Trần Thị C",
          time: "10:00-11:00",
          kham: "Đau dạ dày",
        },
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
  methods: {
    searchOnTable() {
      this.searched = searchByName(this.users, this.search);
    },
  },
  created() {
    this.searched = this.users;
  },
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
