<template>
  <div>
    <!-- avarta -->
    <v-card width="100%" class="d-flex flex-row" height="236px" flat>
      <v-card width="50%" flat>
        <p class="font-weight-medium">Hình đại diện</p>
        <v-card width="278px" flat>
          <p class="text-body-2 font-weight-medium" style="color: #667085">
            Chọn hình đại diện mới và thả vào khung đăng ảnh để thay đổi ảnh đại
            diện
          </p>
        </v-card>
      </v-card>
      <v-card width="50%" flat class="d-flex flex-row align-center">
        <v-avatar size="64">
          <v-img contain :src="avt_img" ref="avt" />
        </v-avatar>
        <v-card
          class="d-flex align-center justify-center ml-8"
          width="100%"
          color="#FCFCFD"
          height="100%"
        >
          <div class="d-flex flex-column">
            <div class="d-flex justify-center">
              <v-card
                class="d-flex align-center justify-center pa-2"
                rounded="circle"
                color="#EEF2F6"
              >
                <v-icon color="#537DA5" large @click="$refs.file.click()"
                  >mdi-cloud-upload-outline
                </v-icon>
                <input
                  type="file"
                  ref="file"
                  accept="image/*"
                  style="display: none"
                  @change="previewFiles"
                />
              </v-card>
            </div>
            <p class="d-flex font-weight-medium">
              Click to upload or drag and drop
            </p>
            <p class="d-flex text-body-2" style="color: #667085">
              SVG, PNG, JPG or GIF (max 800x400px)
            </p>
          </div>
        </v-card>
      </v-card>
    </v-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      avt_img: require("@/assets/img/user/profile/avatar1.svg")
    };
  },
  methods: {
    previewFiles(event) {
      //console.log(event.target.files);
      var file_1 = event.target.files[0];
      var reader = new FileReader();
      reader.addEventListener(
        "load",
        () => {
          // convert image file to base64 string
          this.avt_img = reader.result;
        },
        false
      );
      if (file_1) {
        reader.readAsDataURL(file_1);
      } else {
        this.avt_img = require("@/assets/img/user/profile/avatar1.svg");
      }
    }
  }
};
</script>
