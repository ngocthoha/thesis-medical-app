<template>
  <div>
    <!-- header section -->
    <v-card
      width="100%"
      height="132px"
      elevation="0"
      class="d-flex justify-space-between"
    >
      <div class="d-flex align-center ml-8">
        <div class="d-flex flex-column">
          <p class="font-weight-bold mb-3" style="font-size: 24px">
            Hồ sơ khám bệnh
          </p>
          <p style="color: #667085" class="text-body-1">
            Chọn và cài đặt hồ sơ mà bạn muốn theo dõi sức khoẻ
          </p>
        </div>
      </div>
      <div class="d-flex align-center mr-8">
        <v-btn
          color="#537DA5"
          class="white--text btn-not-transform text-body-2"
          @click="createProfile"
          ><v-icon left>mdi-account-plus-outline</v-icon> Thêm mới hồ sơ</v-btn
        >
      </div>
    </v-card>
    <v-divider
      style="border-color: rgba(16, 24, 40, 0.03) !important"
    ></v-divider>
    <!-- remove profile dialog -->
    <v-dialog v-model="dialog" width="408px">
      <v-card height="192px" class="d-flex flex-column">
        <div class="d-flex flex-column mt-8 ml-8">
          <p style="font-size: 24px" class="font-weight-bold">
            Xác nhận xoá hồ sơ
          </p>
          <p style="color: #667085">
            Bạn có chắc chắn muốn xoá hồ sơ này không?
          </p>
        </div>
        <div class="d-flex flex-row justify-space-between ml-8 mr-8">
          <v-btn
            class="btn-not-transform font-weight-medium"
            width="160px"
            outlined
            color="#667085"
            text
            @click="dialog = false"
            >Huỷ bỏ</v-btn
          >
          <v-btn
            class="btn-not-transform white--text font-weight-medium"
            width="160px"
            color="#F04438"
            elevation="0"
            @click="dialog = false"
            >Xoá hồ sơ</v-btn
          >
        </div>
      </v-card>
    </v-dialog>
    <!-- list profile -->
    <div class="d-flex justify-center mt-8">
      <v-card width="858px" elevation="0">
        <v-expansion-panels accordion tile flat>
          <v-expansion-panel
            v-for="profile in profile_list"
            :key="profile.id"
            class="mb-4 rounded-lg"
            style="border: #f2f4f7 1px solid"
          >
            <v-expansion-panel-header>
              <v-card class="d-flex flex-row align-center" elevation="0">
                <v-avatar size="40">
                  <img src="@/assets/img/home/appbar/avatar.png" />
                </v-avatar>
                <div class="d-flex flex-column ml-3">
                  <p class="ma-0 font-weight-bold text-body-1">
                    {{ profile.lastName }}{{ profile.firstName }}
                  </p>
                  <p
                    class="ma-0 font-weight-normal text-body-2"
                    style="color: #667085"
                  >
                    {{ profile.relationship }}
                  </p>
                </div>
              </v-card>
              <!-- button control -->
              <div class="d-flex justify-end">
                <v-btn
                  fab
                  dark
                  small
                  elevation="0"
                  color="#EEF2F6"
                  width="32px"
                  height="32px"
                  class="d-flex justify-center mr-5"
                  @click="editProfile"
                >
                  <v-icon small color="#537DA5"> mdi-pencil-outline </v-icon>
                </v-btn>
                <v-btn
                  fab
                  dark
                  small
                  elevation="0"
                  color="#FEF3F2"
                  width="32px"
                  height="32px"
                  class="d-flex justify-center mr-5"
                  @click.stop="dialog = true"
                >
                  <v-icon small color="#F04438">
                    mdi-account-remove-outline
                  </v-icon>
                </v-btn>
              </div>
              <template v-slot:actions>
                <v-card
                  elevation="0"
                  rounded="circle"
                  color="#FCFCFD"
                  width="32px"
                  height="32px"
                  class="d-flex justify-center"
                >
                  <v-icon small> mdi-chevron-down </v-icon>
                </v-card>
              </template>
            </v-expansion-panel-header>

            <v-expansion-panel-content class="pa-0">
              <!-- user information -->
              <div class="d-flex flex-column">
                <!-- email -->
                <v-divider style="border-color: #f2f4f7 !important"></v-divider>
                <v-card
                  class="d-flex flec-row justify-space-between align-center"
                  elevation="0"
                  height="72px"
                >
                  <p class="ma-0 font-weight-medium" style="color: #667085">
                    Email
                  </p>
                  <p class="ma-0 font-weight-medium">
                    {{ profile.email }}
                  </p>
                </v-card>
                <!-- phone -->
                <v-divider style="border-color: #f2f4f7 !important"></v-divider>
                <v-card
                  class="d-flex flec-row justify-space-between align-center"
                  elevation="0"
                  height="72px"
                >
                  <p class="ma-0 font-weight-medium" style="color: #667085">
                    Số điện thoại
                  </p>
                  <p class="ma-0 font-weight-medium">
                    {{ profile.phone }}
                  </p>
                </v-card>
                <!-- address -->
                <v-divider style="border-color: #f2f4f7 !important"></v-divider>
                <v-card
                  class="d-flex flec-row justify-space-between align-center"
                  elevation="0"
                  height="72px"
                >
                  <p class="ma-0 font-weight-medium" style="color: #667085">
                    Địa chỉ
                  </p>
                  <p class="ma-0 font-weight-medium">
                    {{ getAddress(profile) }}
                  </p>
                </v-card>
                <!-- gender -->
                <v-divider style="border-color: #f2f4f7 !important"></v-divider>
                <v-card
                  class="d-flex flec-row justify-space-between align-center"
                  elevation="0"
                  height="72px"
                >
                  <p class="ma-0 font-weight-medium" style="color: #667085">
                    Giới tính
                  </p>
                  <p class="ma-0 font-weight-medium">{{ profile.gender }}</p>
                </v-card>
                <!-- birthday -->
                <v-divider style="border-color: #f2f4f7 !important"></v-divider>
                <v-card
                  class="d-flex flec-row justify-space-between align-center"
                  elevation="0"
                  height="72px"
                >
                  <p class="ma-0 font-weight-medium" style="color: #667085">
                    Ngày sinh
                  </p>
                  <p class="ma-0 font-weight-medium">{{ profile.dob }}</p>
                </v-card>
                <!-- identify -->
                <v-divider style="border-color: #f2f4f7 !important"></v-divider>
                <v-card
                  class="d-flex flec-row justify-space-between align-center"
                  elevation="0"
                  height="72px"
                >
                  <p class="ma-0 font-weight-medium" style="color: #667085">
                    Số CMND/CCCD
                  </p>
                  <p class="ma-0 font-weight-medium">
                    {{ profile.identityCard }}
                  </p>
                </v-card>
                <!-- job -->
                <v-divider style="border-color: #f2f4f7 !important"></v-divider>
                <v-card
                  class="d-flex flec-row justify-space-between align-center"
                  elevation="0"
                  height="72px"
                >
                  <p class="ma-0 font-weight-medium" style="color: #667085">
                    Nghề nghiệp
                  </p>
                  <p class="ma-0 font-weight-medium">{{ profile.job }}</p>
                </v-card>
                <!-- country -->
                <v-divider style="border-color: #f2f4f7 !important"></v-divider>
                <v-card
                  class="d-flex flec-row justify-space-between align-center"
                  elevation="0"
                  height="72px"
                >
                  <p class="ma-0 font-weight-medium" style="color: #667085">
                    Quốc gia
                  </p>
                  <p class="ma-0 font-weight-medium">{{ profile.country }}</p>
                </v-card>
                <!-- ethnic -->
                <v-divider style="border-color: #f2f4f7 !important"></v-divider>
                <v-card
                  class="d-flex flec-row justify-space-between align-center"
                  elevation="0"
                  height="72px"
                >
                  <p class="ma-0 font-weight-medium" style="color: #667085">
                    Dân tộc
                  </p>
                  <p class="ma-0 font-weight-medium">{{ profile.folk }}</p>
                </v-card>
              </div>
            </v-expansion-panel-content>
          </v-expansion-panel>
        </v-expansion-panels>
      </v-card>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      dialog: false,
    };
  },

  props: {
    profile_list: Array,
  },

  methods: {
    createProfile() {
      this.$emit("OpenCreateProfile");
    },

    editProfile() {
      this.$emit("OpenEditProfile");
    },
    getAddress(profile) {
      return (
        profile.country +
        ", " +
        profile.province +
        ", " +
        profile.town +
        ", " +
        profile.commune +
        ", " +
        profile.detailedAddress
      );
    },
  },
};
</script>

<style scoped>
.btn-not-transform {
  text-transform: none;
}
</style>
