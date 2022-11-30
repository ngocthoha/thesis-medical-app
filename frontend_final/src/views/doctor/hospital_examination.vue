<template>
  <div>
    <v-card class="fill-height d-flex flex-column pa-8">
      <v-footer color="#3C5E7E" padless class="d-flex justify-center pa-5">
        <p class="ma-0 white--text" style="font-size: 20px">
          Lịch sử khám
        </p>
      </v-footer>
      <v-data-table
        v-model="selected"
        :headers="recordHeaders"
        :items="records"
        :single-select="true"
        item-key="id"
        show-select
        class="elevation-1"
        checkbox-color="#3C5E7E"
        :search="search"
        :loading="loading"
        :no-data-text="'Không có danh sách khám'"
        :items-per-page="1"
        :footer-props="{
          'items-per-page-options': [1, 20, 50]
        }"
        @pagination="pagination = $event"
        :server-items-length="totalPages"
      >
        <template v-slot:top>
          <div class="pa-6 d-flex flex-row align-center">
            <v-card outlined width="50%">
              <v-text-field
                v-model="search"
                label="Tìm kiếm"
                prepend-inner-icon="mdi-magnify"
                solo
                flat
                hide-details=""
              ></v-text-field>
            </v-card>
            <v-spacer></v-spacer>
            <v-btn
              class="mr-3 white--text btn font-weight-medium text-body-1"
              elevation="0"
              color="#476D92"
              :disabled="_.isEmpty(selected)"
              @click.stop="examinate()"
            >
              <v-icon medium class="mr-2">mdi-medication</v-icon>
              Khám</v-btn
            >
            <v-menu :close-on-content-click="false">
              <template v-slot:activator="{ on, attrs }">
                <v-icon v-bind="attrs" v-on="on" medium class="mr-2"
                  >mdi-filter-variant</v-icon
                >
              </template>
              <v-card width="300px">
                <div class="d-flex justify-center" style="color: #537DA5">
                  <p class="font-weight-bold mt-3">Lọc Kết Quả</p>
                </div>
                <v-divider></v-divider>
                <v-list>
                  <v-list-item class="mt-3">
                    <v-autocomplete
                      prepend-inner-icon="mdi-map-marker"
                      item-text="text"
                      item-value="text"
                      label="Địa Điểm"
                      clearable
                      dense
                      outlined
                      :menu-props="{ offsetY: true }"
                      placeholder="Tìm địa điểm"
                    ></v-autocomplete>
                  </v-list-item>
                  <div class="d-flex justify-center mt-3">
                    <v-btn
                      color="#537DA5"
                      elevation="0"
                      class="white--text btn font-weight-medium text-body-1"
                      width="90%"
                      style="margin: 0 auto"
                      @click="clearFilters()"
                    >
                      Bỏ lọc
                    </v-btn>
                  </div>
                </v-list>
              </v-card>
            </v-menu>
          </div>
        </template>
        <template v-slot:[`item.appointment.profile.fullName`]="{ item }">
          <div class="d-flex flex-row align-center">
            <p class="ma-0">
              {{ item.appointment.profile.lastName }}
              {{ item.appointment.profile.firstName }}
            </p>
          </div>
        </template>
        <template v-slot:[`item.appointment.profile.phone`]="{ item }">
          <div class="d-flex flex-row align-center">
            <p class="ma-0">
              {{ item.appointment.profile.phone | phone }}
            </p>
          </div>
        </template>
        <template v-slot:[`item.diagnose`]="{ item }">
          <div class="d-flex flex-row align-center">
            <p class="ma-0">
              {{ item.diagnose | empty }}
            </p>
          </div>
        </template>
        <template v-slot:[`item.prescribe`]="{ item }">
          <div class="d-flex flex-row align-center">
            <p class="ma-0">
              {{ item.prescribe | empty }}
            </p>
          </div>
        </template>
        <template v-slot:[`item.note`]="{ item }">
          <div class="d-flex flex-row align-center">
            <p class="ma-0">
              {{ item.note | empty }}
            </p>
          </div>
        </template>
        <template v-slot:[`item.bloodVessel`]="{ item }">
          <div class="d-flex flex-row align-center">
            <p class="ma-0">
              {{ item.bloodVessel | empty }}
            </p>
          </div>
        </template>
        <template v-slot:[`item.temperature`]="{ item }">
          <div class="d-flex flex-row align-center">
            <p class="ma-0">
              {{ item.temperature | empty }}
            </p>
          </div>
        </template>

        <template v-slot:[`item.bloodPressure`]="{ item }">
          <div class="d-flex flex-row align-center">
            <p class="ma-0">
              {{ item.bloodPressure | empty }}
            </p>
          </div>
        </template>

        <template v-slot:[`item.heartbeat`]="{ item }">
          <div class="d-flex flex-row align-center">
            <p class="ma-0">
              {{ item.heartbeat | empty }}
            </p>
          </div>
        </template>

        <template v-slot:[`item.height`]="{ item }">
          <div class="d-flex flex-row align-center">
            <p class="ma-0">
              {{ item.height | empty }}
            </p>
          </div>
        </template>

        <template v-slot:[`item.weight`]="{ item }">
          <div class="d-flex flex-row align-center">
            <p class="ma-0">
              {{ item.weight | empty }}
            </p>
          </div>
        </template>
      </v-data-table>
    </v-card>
  </div>
</template>

<script>
import axios from "axios";
const url = process.env.VUE_APP_ROOT_API;
export default {
  async mounted() {
    await this.fetchRecords();
  },

  data() {
    return {
      search: "",
      selected: [],
      recordHeaders: [
        {
          text: "Họ và tên",
          align: "start",
          sortable: false,
          value: "appointment.profile.fullName",
          filterable: true,
          width: "150px"
        },
        {
          text: "Số điện thoại",
          align: "start",
          sortable: false,
          value: "appointment.profile.phone",
          filterable: true,
          width: "150px"
        },
        {
          text: "Mã bệnh án",
          align: "start",
          sortable: false,
          value: "recordId",
          filterable: true
        },
        {
          text: "Chuẩn đoán",
          align: "start",
          sortable: false,
          value: "diagnose",
          filterable: true,
          width: "150px"
        },
        {
          text: "Chỉ định",
          align: "start",
          sortable: false,
          value: "prescribe",
          filterable: true,
          width: "150px"
        },
        {
          text: "Ghi chú",
          align: "start",
          sortable: false,
          value: "note",
          filterable: true,
          width: "150px"
        },
        {
          text: "Mạch (lần/phút)",
          align: "start",
          sortable: false,
          value: "bloodVessel",
          filterable: true,
          width: "170px"
        },
        {
          text: "Nhiệt độ (độ C)",
          align: "start",
          sortable: false,
          value: "temperature",
          filterable: true,
          width: "150px"
        },
        {
          text: "Huyết áp (mmHg)",
          align: "start",
          sortable: false,
          value: "bloodPressure",
          filterable: true,
          width: "170px"
        },
        {
          text: "Nhịp tim (lần/phút)",
          align: "start",
          sortable: false,
          value: "heartbeat",
          filterable: true,
          width: "180px"
        },
        {
          text: "Chiều cao (cm)",
          align: "start",
          sortable: false,
          value: "height",
          filterable: true,
          width: "150px"
        },
        {
          text: "Cân nặng (kg)",
          align: "start",
          sortable: false,
          value: "weight",
          filterable: true,
          width: "150px"
        }
      ],
      loading: false,
      loadingConfirm: false,
      records: [],
      selected_appointment: {
        id: "",
        code: 0,
        room: {
          id: "",
          name: "",
          link: null
        },
        profile: {
          id: "",
          profileNumber: 0,
          firstName: "",
          lastName: "",
          address: "",
          phone: "",
          email: "",
          dob: "",
          job: "",
          identityCard: "",
          healthInsurance: "",
          folk: "",
          gender: "",
          guardian: "",
          guardianPhone: "",
          guardianIdentityCard: "",
          relationship: "",
          relationshipWithPatient: "",
          imageUrl: null
        },
        doctor: {
          id: "",
          name: "",
          email: "",
          specialty: "",
          hospital: {
            id: "",
            name: "",
            address: {
              id: "",
              country: "",
              province: "",
              district: "",
              ward: "",
              address: ""
            },
            info: "",
            registrationNumber: 0,
            favorite: 0.0,
            mapImageUrl: null,
            imageUrl: "",
            isActive: true,
            hospitalHour: {
              id: "",
              mondayTime: "",
              mondayTimeBreak: "",
              tuesdayTime: "",
              tuesdayTimeBreak: "",
              wednesdayTime: "",
              wednesdayTimeBreak: "",
              thursdayTime: "",
              thursdayTimeBreak: "",
              fridayTime: "",
              fridayTimeBreak: "",
              saturdayTime: "",
              saturdayTimeBreak: "",
              sundayTime: "",
              sundayTimeBreak: ""
            }
          },
          level: "",
          bio: "",
          registrationNumber: 0,
          price: "",
          imageUrl: null,
          favorite: 0
        },
        service: null,
        date: "",
        time: "",
        symptom: "",
        files: [
          {
            id: "",
            imageUrl: "",
            type: ""
          }
        ],
        status: "",
        type: "",
        isPaid: true,
        fee: "",
        category: ""
      },
      all_appointment_list: [],
      exam_dialog: false,
      from_date_menu: false,
      from_date: "",
      to_date_menu: false,
      to_date: "",

      prescriptions_header: [
        {
          text: "Tên",
          align: "start",
          sortable: false,
          value: "name"
        },
        { text: "Số lượng", value: "mount", sortable: false },
        { text: "Liều", value: "use", sortable: false },
        { text: "Đơn vị uống", value: "unit", sortable: false }
      ],
      prescriptions: [],
      medicine_dialog: false,
      medicine_dialog_type: 1,
      store_medicine_list: [],
      medicine: {
        data: {
          name: "",
          id: ""
        },
        unit: "",
        morning: 0,
        afternoon: 0,
        evening: 0
      },
      medicine_select: [],
      test_type: [
        { name: "Xét nghiệm máu", key: "BLOOD_TEST" },
        { name: "Xét nghiệm nước tiểu", key: "URINE_TEST" },
        { name: "Xét nghiệm khác", key: "DIFFERENT_TEST" }
      ],
      test_select: "",
      test_file: [],
      test_add_list: [],
      image_select: "",
      image_analyst_type: [
        { name: "CT", key: "CT_SCAN" },
        { name: "X-quang", key: "X_RAY " },
        { name: "PET", key: "PET_SCAN" },
        { name: "Siêu âm", key: "SUPERSONIC" },
        { name: "MRI", key: "MRI" },
        { name: "Hình ảnh khác", key: "DIFFERENT_IMAGE" }
      ],
      image_analyst_list: [],
      image_file: [],
      submit_file_list: [],
      menu2: false,
      record: {
        diagnose: "",
        prescribe: "",
        note: "",
        reExaminationDate: "",
        height: "",
        weight: "",
        bloodVessel: "",
        temperature: "",
        bloodPressure: "",
        heartbeat: "",
        hospitalize: false
      },
      exam_confirm_dialog: false,
      image_file_accept: [
        "wmf",
        "webp",
        "tga",
        "emf",
        "ai",
        "tif",
        "bmp",
        "gif",
        "jpg",
        "ps",
        "jpeg",
        "ufo",
        "pdf",
        "eps",
        "svg",
        "png",
        "psd"
      ],
      params: {
        filters: [],
        sorts: [],
        page: 0,
        size: 1
      },
      totalPages: 0,
      pagination: {
        itemsPerPage: 1,
        page: 1
      }
    };
  },
  computed: {
    doctorId() {
      return this.$store.getters["auth/userId"];
    }
  },
  watch: {
    "pagination.page": {
      handler(val, newVal) {
        if (val != newVal) this.get_appointment();
      },
      deep: true
    },
    "pagination.itemsPerPage": {
      handler(val, newVal) {
        if (val != newVal) this.get_appointment();
      },
      deep: true
    }
  },
  methods: {
    async examinate() {
      const data = this.selected[0];
      this.selected_appointment = JSON.parse(JSON.stringify(data));
      this.selected_appointment.profile.dob = this.convert_date(
        data.profile.dob
      );
      switch (data.profile.gender) {
        case "MALE":
          this.selected_appointment.profile.gender = "Nam";
          break;
        case "FEMALE":
          this.selected_appointment.profile.gender = "Nữ";
          break;
        default:
          this.selected_appointment.profile.gender = "Khác";
        // code block
      }

      this.selected_appointment.profile.address = this.getAddress(data.profile);
      let token = this.$store.getters["auth/access_token"];

      const params = {
        token: token,
        data: {
          hospitalId: this.selected_appointment.doctor.hospital.id
        }
      };
      await this.$store.dispatch(
        "medicine/get_store_medicine_by_hospital",
        params
      );
      this.store_medicine_list = this.$store.getters[
        "medicine/store_medicine_by_hospital"
      ];
      this.exam_dialog = true;
    },

    removeTestFile(index) {
      this.test_add_list.splice(index, 1);
    },

    removeImageFile(index) {
      this.image_analyst_list.splice(index, 1);
    },

    addTestFile() {
      this.test_file.forEach(file => {
        this.test_add_list.push({
          type: { key: "", name: "" },
          file_name: file.name,
          data: file
        });
      });
    },

    addImageAnalystFile() {
      this.image_file.forEach(file => {
        this.image_analyst_list.push({
          type: { key: "", name: "" },
          file_name: file.name,
          data: file
        });
      });
    },

    async fetchRecords() {
      let token = this.$store.getters["auth/access_token"];
      axios.defaults.headers.common = {
        Authorization: `Bearer ${token}`
      };
      const cancelToken = axios.CancelToken.source().token;
      let params = this._.cloneDeep(this.params);
      params.page = this.pagination.page - 1;
      params.size = this.pagination.itemsPerPage;
      params.filters.push({
        key: "appointment.doctor.id",
        operator: "EQUAL_NESTED",
        field_type: "STRING",
        value: this.doctorId
      });
      this.loading = true;
      await axios
        .post(`${url}/api/doctor/records/search`, params, {
          cancelToken
        })
        .then(res => {
          this.records = res.data.results;
          this.totalPages = res.data?.meta?.totalPages;
        })
        .finally(() => {
          this.loading = false;
        });
    },

    convert_date(time) {
      let date = new Date(time);
      return (
        (date.getDate() > 9 ? date.getDate() : "0" + date.getDate()) +
        "/" +
        (date.getMonth() > 8
          ? date.getMonth() + 1
          : "0" + (date.getMonth() + 1)) +
        "/" +
        date.getFullYear()
      );
    },

    getAddress(profile) {
      if (profile.address == null) return "";
      return (
        profile.address.address +
        ", " +
        profile.address.ward +
        ", " +
        profile.address.district +
        ", " +
        profile.address.province
      );
    },

    async create_record() {
      this.loadingConfirm = true;
      let post_file_list = this.test_add_list.concat(this.image_analyst_list);
      await Promise.all(
        post_file_list.map(async file => {
          await this.post_file(file.data, file.type);
        })
      );
      let token = this.$store.getters["auth/access_token"];

      let medicine_list_submit = [];
      this.prescriptions.forEach(medicine => {
        medicine_list_submit.push({
          storeMedicineId: medicine.data.id,
          unit: medicine.unit,
          numMedicineInMorning: medicine.morning,
          numMedicineInAfternoon: medicine.afternoon,
          numMedicineInEvening: medicine.evening,
          dateStart: this.from_date,
          dateEnd: this.to_date
        });
      });
      const params = {
        token: token,
        data: {
          appointmentId: this.selected_appointment.id,
          diagnose: this.record.diagnose,
          prescribe: this.record.prescribe,
          note: this.record.note,
          reExaminationDate: this.record.reExaminationDate,
          height: this.record.height,
          weight: this.record.weight,
          bloodVessel: this.record.bloodVessel,
          temperature: this.record.temperature,
          bloodPressure: this.record.bloodPressure,
          heartbeat: this.record.heartbeat,
          hospitalize: this.record.hospitalize,
          medicines: medicine_list_submit,
          files: this.submit_file_list
        }
      };
      this.$store.dispatch("record/create_record", params);
      this.loadingConfirm = false;
      this.refresh_exam_data();
      this.exam_confirm_dialog = false;
      this.exam_dialog = false;
    },

    async post_file(file, type) {
      let form_data = new FormData();
      form_data.append("file", file);
      let token = this.$store.getters["auth/access_token"];

      axios.defaults.headers.common = {
        Authorization: `Bearer ${token}`
      };
      await axios
        .post(`${url}/api/files/upload`, form_data, {
          headers: {
            "Content-Type": "multipart/form-data"
          }
        })
        .then(response => {
          if (response.data.code === 200) {
            this.submit_file_list.push({
              imageUrl: response.data.results,
              type: type.key
            });
          }
        });
    },

    refresh_exam_data() {
      this.from_date = "";
      this.to_date = "";
      this.record.diagnose = "";
      this.record.prescribe = "";
      this.record.note = "";
      this.record.bloodVessel = "";
      this.record.bloodPressure = "";
      this.record.height = "";
      this.record.weight = "";
      this.record.temperature = "";
      this.record.hospitalize = false;
      this.record.reExaminationDate = "";
      this.record.heartbeat = "";
      this.prescriptions = [];
    },

    stop_examination() {
      this.refresh_exam_data();
      this.exam_dialog = false;
    },

    get_text_of_type_file(file) {
      let type_name = "";
      this.test_type.forEach(file_type => {
        if (file_type.key === file.type) type_name = file_type.name;
      });
      this.image_analyst_type.forEach(file_type => {
        if (file_type.key === file.type) type_name = file_type.name;
      });
      return type_name;
    },

    get_name_of_file(file) {
      let temp_array = file.imageUrl.split("/");
      return temp_array[temp_array.length - 1];
    },
    view_file(file) {
      window.open(file.imageUrl);
    },
    is_img_file(file) {
      let temp_array = file.imageUrl.split("/");
      let name_file = temp_array[temp_array.length - 1];
      let temp_array_1 = name_file.split(".");
      let result = false;
      if (this.image_file_accept.includes(temp_array_1[1])) result = true;
      return result;
    },
    down_load_file() {}
  }
};
</script>

<style scoped>
.btn {
  text-transform: none;
}
.v-data-table > .v-data-table__wrapper > table > tbody > tr > th,
.v-data-table > .v-data-table__wrapper > table > thead > tr > th,
.v-data-table > .v-data-table__wrapper > table > tfoot > tr > th {
  font-size: 20px !important;
}
.v-data-table {
  overflow-x: auto;
}
</style>
