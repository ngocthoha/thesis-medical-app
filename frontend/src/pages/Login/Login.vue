<template>
  <div style="height: 100%">
    <v-container fluid full-height style="height: 100%">
      <v-row justify="center">
        <v-col class="d-flex justify-end" cols="12" sm="8" md="4">
          <v-card outlined class="ma-auto" style="width: 600px">
            <v-card-title></v-card-title>
            <v-form>
              <v-card-title>Wellcome Back!</v-card-title>
              <v-card-text>
                <span>Enter your credentials below to get started.</span>
              </v-card-text>
              <v-card-text>
                <v-text-field
                  name="username"
                  label="username"
                  id="username"
                  v-model="user.username"
                  dense
                ></v-text-field>
                <v-text-field
                  v-model="user.password"
                  name="password"
                  label="password"
                  id="password"
                  :append-icon="isHidePassword ? 'mdi-eye-off' : 'mdi-eye'"
                  :type="isHidePassword ? 'password' : 'text'"
                  @click:append="() => (isHidePassword = !isHidePassword)"
                ></v-text-field>

                <v-row>
                  <v-col cols="6">
                    <v-checkbox
                      label="Remember Me"
                      v-model="value"
                      value="value"
                      class="mt-0 pt-0"
                    ></v-checkbox>
                  </v-col>
                  <v-col cols="6">
                    <a class="float-right text-decoration-underline"
                      >Forgot Password</a
                    >
                  </v-col>
                </v-row>
              </v-card-text>
              <v-card-actions>
                <v-btn color="primary" dark depressed block @click="submit"
                  >Login</v-btn
                >
              </v-card-actions>
            </v-form>
            <v-card-text>
              <div class="text-center">
                Don't have an account yet?
                <a href="">Sign up</a>
              </div>
            </v-card-text>
          </v-card>
        </v-col>
        <!-- <v-col class="d-flex align-center justify-start pl-0" cols="6">
          <div class="d-flex login-img">
            <img src="@/assets/img/login-image.png" alt="" />
          </div>
        </v-col> -->
      </v-row>
    </v-container>
  </div>
</template>

<script>
export default {
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      isHidePassword: true,
    };
  },

  computed: {
    isAuthenticated: function () {
      return this.$store.getters.isAuthenticated;
    },
  },

  methods: {
    async submit() {
      const user = {
        username: this.user.username,
        password: this.user.password,
      };
      await this.$store.dispatch("auth/login", user);

      if (this.$store.getters["auth/access_token"] != null) {
        console.log(this.$store.getters["auth/access_token"]);
        this.$router.push({ name: "Đặt lịch khám theo ngày" });
      }
    },
  },
};
</script>
<style lang="scss" scoped>
html,
body {
  height: 100% !important;
  margin: 0;
}
.container {
  background-color: #f5f5f5;
}
.login-img {
  width: 500px;
  height: 455px;
}
</style>
