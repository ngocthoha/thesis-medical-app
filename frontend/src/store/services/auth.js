import axios from "axios";

// const url = "http://13.40.75.147:8080";
const url = process.env.VUE_APP_ROOT_API;
function login(params) {
  return axios
    .post(
      `${url}/api/login`,
      new URLSearchParams({
        username: params.username,
        password: params.password,
      })
    )
    .then((response) => {
      return {
        data: response.data,
      };
    });
}

export default {
  login,
};
