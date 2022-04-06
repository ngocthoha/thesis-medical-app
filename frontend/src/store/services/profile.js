import axios from "axios";

const url = "http://13.40.194.0:8080";

function profile_list(params) {
  axios.defaults.headers.common = { Authorization: `Bearer ${params.token}` };
  return axios.get(`${url}/api/profiles`, {}).then((response) => {
    return {
      data: response.data,
    };
  });
}

function add_new_profile(params) {
  axios.defaults.headers.common = { Authorization: `Bearer ${params.token}` };
  return axios.post(`${url}/api/profiles`, params.data).then((response) => {
    return {
      data: response.data,
    };
  });
}

export default {
  profile_list,
  add_new_profile,
};
