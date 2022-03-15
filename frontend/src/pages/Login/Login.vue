<template>
  <div class="content">
    <div class="md-layout">
      <div
        class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-60"
      >
        <div class="login-section col">
          <h3>Đăng nhập</h3>
          <div class="login-section-body">
            <label for="">Tên đăng nhập</label>
            <input v-model="user.username" type="text" name="" id="username" />
            <label for="">Mật khẩu</label>
            <input
              v-model="user.password"
              type="password"
              name=""
              id="password"
            />
            <md-button class="login-button" @click="submit"
              >Đăng nhập</md-button
            >
          </div>
        </div>
        <div class="login-img col">
          <img
            src="@/assets/img/login-image.png"
            alt="doctor"
            class="login-img"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
    };
  },

  methods: {
    async submit() {
      axios.defaults.headers.common["Access-Control-Allow-Origin"] = "*";
      // let response = await axios.post(
      //   "http://13.40.75.147:8080/api/login",
      //   this.user,
      //   {
      //     headers: {
      //       "Content-Type": "application/x-www-form-urlencoded",
      //     },
      //   }
      // );

      let response = await axios.post(
        "http://13.40.75.147:8080/api/login",
        new URLSearchParams({
          username: this.user.username,
          password: this.user.password,
        })
      );

      console.log(response.data.access_token);
    },
  },
};
</script>
<style lang="scss" scoped>
.md-layout-item {
  background: white;
  margin-top: 100px;
  margin-left: auto;
  margin-right: auto;
  padding: 0;
}
.content .login-img {
  width: 50%px;
  height: 417px;
  float: right;
}
.col {
  width: 50%;
  float: left;
}
.login-section label {
  display: block;
}
.login-button {
  display: block;
}
.login-section h3 {
  text-align: center;
  margin-bottom: 20px;
}
.login-section-body {
  margin-left: 30%;
}

input,
select,
textarea {
  border-style: solid;
}
</style>
