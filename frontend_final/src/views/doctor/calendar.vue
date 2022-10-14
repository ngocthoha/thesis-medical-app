<template>
  <v-card class="fill-height d-flex flex-column pa-8">
    <!-- header -->
    <v-card width="100%" class="mb-5" elevation="0">
      <v-btn
        class="mr-4 btn font-weight-medium"
        color="#667085"
        outlined
        @click.stop="add_dialog = true"
      >
        <v-icon>mdi-calendar-plus-outline</v-icon>
        Tạo lịch mới
      </v-btn>
      <v-btn class="mr-4 btn font-weight-medium" color="#537DA5" outlined>
        <v-icon>mdi-calendar-edit-outline</v-icon>
        Chỉnh sửa lịch
      </v-btn>

      <!-- add dialog -->
      <v-dialog v-model="add_dialog" max-width="800">
        <v-toolbar color="#475467" height="64" class="white--text"
          >Tạo lịch làm việc</v-toolbar
        >
        <v-card class="d-flex flex-column" tile>
          <v-card height="600" class="pa-6 d-flex flex-column">
            <!-- select date -->
            <p class="mb-2 font-weight-medium text-body-2">Chọn ngày:</p>
            <v-card class="d-flex flex-row align-center" elevation="0">
              <v-icon @click="menu = true" class="mr-3"
                >mdi-calendar-outline</v-icon
              >
              <v-card outlined width="100%">
                <v-menu
                  ref="menu"
                  v-model="menu"
                  :close-on-content-click="false"
                  :return-value.sync="dates"
                  transition="scale-transition"
                  offset-y
                  min-width="auto"
                >
                  <template v-slot:activator="{ on, attrs }">
                    <v-combobox
                      flat
                      v-model="dates"
                      hide-details=""
                      multiple
                      chips
                      small-chips
                      readonly
                      v-bind="attrs"
                      v-on="on"
                      solo
                      append-icon=""
                    ></v-combobox>
                  </template>
                  <v-date-picker
                    v-model="dates"
                    multiple
                    no-title
                    scrollable
                    locale="vi"
                    color="#537DA5"
                  >
                    <v-spacer></v-spacer>
                    <v-btn text color="primary" @click="menu = false">
                      Cancel
                    </v-btn>
                    <v-btn text color="primary" @click="$refs.menu.save(dates)">
                      OK
                    </v-btn>
                  </v-date-picker>
                </v-menu>
              </v-card>
            </v-card>
            <!--seclect timeframe -->
            <p class="mt-2 mb-2 font-weight-medium text-body-2">Chọn ca:</p>
            <div class="d-flex flex-wrap justify-start">
              <v-item-group v-model="selected" class="ma-0" multiple>
                <v-item
                  v-for="(item, idx) in affternoon_time"
                  :key="idx"
                  v-slot="{ active, toggle }"
                  :value="item"
                >
                  <v-btn
                    :outlined="active ? false : true"
                    color="#667085"
                    @click="toggle"
                    class="mb-5 mx-3"
                    active-class="white--text"
                    elevation="0"
                    :style="active ? 'border: 1px #667085 solid' : ''"
                  >
                    <p
                      class="ma-0 font-weight-medium"
                      :style="active ? 'color:white' : ''"
                    >
                      {{ item }}
                    </p>
                  </v-btn>
                </v-item>
              </v-item-group>
            </div>
            <!-- patient count  -->
            <div class="d-flex flex-row align-center">
              <p
                class=" d-flex ma-0 align-center font-weight-medium text-body-2"
              >
                Số lương bệnh nhân mỗi khung giờ:
              </p>
              <v-card outlined class="ml-3" width="60">
                <v-combobox
                  :items="patient_count"
                  append-icon=""
                  solo
                  flat
                  hide-details=""
                  spellcheck="false"
                  color="#667085"
                  item-color="blue-grey darken-1"
                ></v-combobox>
              </v-card>
            </div>
            <div class="d-flex flex-row align-center mt-2">
              <p
                class="d-flex ma-0 align-center font-weight-medium text-body-2"
              >
                Chọn phòng:
              </p>
              <v-card outlined class="ml-3">
                <v-combobox
                  :items="patient_count"
                  append-icon=""
                  solo
                  flat
                  hide-details=""
                  spellcheck="false"
                  color="#667085"
                  item-color="blue-grey darken-1"
                ></v-combobox>
              </v-card>
            </div>

            <!-- type  -->
            <div class="d-flex flex-row align-center mt-2">
              <p
                class="d-flex ma-0 align-center font-weight-medium text-body-2"
              >
                Chọn loại hình khám:
              </p>
              <v-card outlined class="ml-3">
                <v-combobox
                  :items="service_type"
                  append-icon=""
                  solo
                  flat
                  hide-details=""
                  spellcheck="false"
                  color="#667085"
                  item-color="blue-grey darken-1"
                ></v-combobox>
              </v-card>
            </div>
            <v-spacer></v-spacer>
            <div class="d-flex flex-row">
              <v-btn
                class="btn mr-5 white--text"
                color="#98A2B3"
                elevation="0"
                @click="add_dialog = false"
                >Hủy</v-btn
              >
              <v-btn
                class="btn white--text"
                color="#667085"
                elevation="0"
                @click="add_dialog = false"
                >Tạo mới</v-btn
              >
            </div>
          </v-card>
        </v-card>
      </v-dialog>
    </v-card>
    <!-- calendar -->
    <v-card width="100%">
      <v-sheet height="64">
        <v-toolbar flat color="#314E6A">
          <v-btn outlined class="mr-4 btn" color="white" @click="setToday" c>
            Hôm nay
          </v-btn>
          <v-btn fab text small color="white" @click="prev">
            <v-icon small>
              mdi-chevron-left
            </v-icon>
          </v-btn>
          <v-btn fab text small color="white" @click="next">
            <v-icon small>
              mdi-chevron-right
            </v-icon>
          </v-btn>
          <v-toolbar-title v-if="$refs.calendar" class="white--text">
            {{ $refs.calendar.title }}
          </v-toolbar-title>

          <v-spacer></v-spacer>
          <v-menu bottom right>
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                outlined
                color="white"
                v-bind="attrs"
                v-on="on"
                class="btn"
              >
                <span>{{ typeToLabel[type] }}</span>
                <v-icon right>
                  mdi-menu-down
                </v-icon>
              </v-btn>
            </template>
            <v-list>
              <v-list-item-group v-model="type" color="#314E6A">
                <v-list-item key="day" value="day">
                  <v-list-item-title>Ngày</v-list-item-title>
                </v-list-item>
                <v-list-item key="week" value="week">
                  <v-list-item-title>Tuần</v-list-item-title>
                </v-list-item>
                <v-list-item key="month" value="month">
                  <v-list-item-title>Tháng</v-list-item-title>
                </v-list-item>
              </v-list-item-group>
            </v-list>
          </v-menu>
        </v-toolbar>
      </v-sheet>
      <v-sheet height="600">
        <v-calendar
          locale="vi-sa"
          ref="calendar"
          v-model="focus"
          :events="events"
          :event-color="getEventColor"
          :type="type"
          :short-intervals="false"
          :short-months="false"
          :short-weekdays="false"
          :event-text-color="get_event_text_color"
          @click:event="showEvent"
          @click:more="viewDay"
          @click:date="viewDay"
          :event-overlap-mode="'column'"
          @change="updateRange"
          event-more-text="Nhiều hơn"
          :event-overlap-threshold="30"
        ></v-calendar>
        <v-menu
          v-model="selectedOpen"
          :close-on-content-click="false"
          :activator="selectedElement"
          offset-x
        >
          <v-card min-width="800" flat>
            <v-toolbar :color="selectedEvent.color" dark>
              <v-toolbar-title v-html="selectedEvent.name"></v-toolbar-title>
              <v-spacer></v-spacer>
              <v-btn icon>
                <v-icon>mdi-pencil</v-icon>
              </v-btn>
            </v-toolbar>
            <v-card-text>
              <span v-html="selectedEvent.details"></span>
            </v-card-text>
            <v-card-actions>
              <v-btn text color="secondary" @click="selectedOpen = false">
                Cancel
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-menu>
      </v-sheet>
    </v-card>
  </v-card>
</template>

<script>
export default {
  data: () => ({
    add_dialog: false,
    menu: false,
    dates: [],
    selected: null,
    affternoon_time: [
      "07:00 - 08:00",
      "08:00 - 09:00",
      "09:00 - 10:00",
      "10:00 - 11:00",
      "11:00 - 12:00",
      "13:00 - 14:00",
      "14:00 - 15:00",
      "15:00 - 16:00",
      "16:00 - 17:00",
      "17:00 - 18:00"
    ],
    patient_count: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
    patient_select: 1,
    service_type: ["Tại phòng khám", "Trực tuyến"],
    service_edit_selection: "Tại phòng khám",
    focus: "",
    type: "month",
    typeToLabel: {
      month: "Tháng",
      week: "Tuần",
      day: "Ngày"
    },
    selectedEvent: {},
    selectedElement: null,
    selectedOpen: false,
    events: [],
    colors: [
      //   "#476D92"
      "#475467"
      //   "indigo",
      //   "deep-purple",
      //   "cyan",
      //   "green",
      //   "orange",
      //   "grey darken-1"
    ],

    colors: {
      online: { color: "#98A2B3", text: "white" },
      offline_color: { color: "#6D91B3", text: "white" }
    },
    names: [
      "Meeting",
      "Holiday",
      "PTO",
      "Travel",
      "Event",
      "Birthday",
      "Conference",
      "Party"
    ],

    doctor_calendar: [
      {
        date: "2022-10-11",
        event_in_day: [
          {
            type: "online",
            name: "Tư vấn online",
            time: "12:00-13:00"
          },
          {
            type: "offline",
            name: "Khám tại viện",
            time: "13:00-14:00"
          },
          {
            type: "online",
            name: "Tư vấn online",
            time: "13:00-14:00"
          }
        ]
      }
    ]
  }),
  mounted() {
    this.$refs.calendar.checkChange();
  },
  methods: {
    viewDay({ date }) {
      this.focus = date;
      this.type = "day";
    },
    getEventColor(event) {
      return event.color;
    },
    get_event_text_color(event) {
      return event.text_color;
    },
    setToday() {
      this.focus = "";
    },
    prev() {
      this.$refs.calendar.prev();
    },
    next() {
      this.$refs.calendar.next();
    },
    showEvent({ nativeEvent, event }) {
      const open = () => {
        this.selectedEvent = event;
        this.selectedElement = nativeEvent.target;
        requestAnimationFrame(() =>
          requestAnimationFrame(() => (this.selectedOpen = true))
        );
      };

      if (this.selectedOpen) {
        this.selectedOpen = false;
        requestAnimationFrame(() => requestAnimationFrame(() => open()));
      } else {
        open();
      }

      nativeEvent.stopPropagation();
    },
    updateRange({ start, end }) {
      const events = [];

      const min = new Date(`${start.date}T00:00:00`);
      const max = new Date(`${end.date}T23:59:59`);
      const days = (max.getTime() - min.getTime()) / 86400000;
      const eventCount = this.rnd(days, days + 20);

      //   for (let i = 0; i < eventCount; i++) {
      //     const allDay = this.rnd(0, 3) === 0;
      //     const firstTimestamp = this.rnd(min.getTime(), max.getTime());
      //     const first = new Date(firstTimestamp - (firstTimestamp % 900000));
      //     const secondTimestamp = this.rnd(2, allDay ? 288 : 8) * 900000;
      //     const second = new Date(first.getTime() + secondTimestamp);
      //     console.log(first.toLocaleString());
      //     console.log(second.toLocaleString());
      //     events.push({
      //       name: this.names[this.rnd(0, this.names.length - 1)],
      //       start: first,
      //       end: second,
      //       color: this.colors[this.rnd(0, this.colors.length - 1)],
      //       timed: true
      //     });
      //   }
      this.doctor_calendar.forEach(calendar => {
        const day = new Date(calendar.date);
        if (day >= min && day <= max) {
          calendar.event_in_day.forEach(event => {
            const time_frame = event.time.split("-");
            const start_string = `${calendar.date}T${time_frame[0]}:00`;
            const end_string = `${calendar.date}T${time_frame[1]}:00`;
            const color =
              event.type === "online"
                ? this.colors.online
                : this.colors.offline_color;
            events.push({
              name: event.name,
              start: new Date(start_string),
              end: new Date(end_string),
              color: color.color,
              text_color: color.text,
              timed: true,
              details: {
                max_count: 5,
                current_count: 3
              }
            });
          });
        }
      });

      this.events = events;
    },
    rnd(a, b) {
      return Math.floor((b - a + 1) * Math.random()) + a;
    },
    intervalFormatter(locale, getOptions) {
      return locale.time;
    }
  }
};
</script>

<style scoped>
.btn {
  text-transform: none;
}
</style>
