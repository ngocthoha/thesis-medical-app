import axios from "axios";

const url = "http://13.40.194.0:8080";

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
