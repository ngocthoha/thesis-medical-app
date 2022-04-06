import axios from "axios";

const url = "http://13.40.194.0:8080";

function speciality_list(params) {
  axios.defaults.headers.common = { Authorization: `Bearer ${params.token}` };
  return axios.get(`${url}/api/specialties`, {}).then((response) => {
    return {
      data: response.data,
    };
  });
}

function getDoctorList_byDateAndSpeciality(params) {
  axios.defaults.headers.common = { Authorization: `Bearer ${params.token}` };
  return axios
    .get(`${url}/api/schedules/doctors`, { params: params.data })
    .then((response) => {
      return {
        data: response.data,
      };
    });
}

export default {
  speciality_list,
  getDoctorList_byDateAndSpeciality,
};
