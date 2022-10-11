<template>
  <v-row class="fill-height">
    <v-col>
      <v-sheet height="64">
        <v-toolbar flat color="#314E6A">
          <v-btn outlined class="mr-4" color="white" @click="setToday" c>
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
              <v-btn outlined color="white" v-bind="attrs" v-on="on">
                <span>{{ typeToLabel[type] }}</span>
                <v-icon right>
                  mdi-menu-down
                </v-icon>
              </v-btn>
            </template>
            <v-list>
              <v-list-item @click="type = 'day'">
                <v-list-item-title>Ngày</v-list-item-title>
              </v-list-item>
              <v-list-item @click="type = 'week'">
                <v-list-item-title>Tuần</v-list-item-title>
              </v-list-item>
              <v-list-item @click="type = 'month'">
                <v-list-item-title>Tháng</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
        </v-toolbar>
      </v-sheet>
      <v-sheet height="600">
        <v-calendar
          locale="vi"
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
          :event-margin-bottom="'2'"
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
    </v-col>
  </v-row>
</template>

<script>
export default {
  data: () => ({
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
            time: "12:30-13:30"
          },
          {
            type: "offline",
            name: "Khám tại viện",
            time: "13:30-14:30"
          },
          {
            type: "online",
            name: "Tư vấn online",
            time: "13:30-14:30"
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
          console.log(true);
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
