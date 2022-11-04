import axios from "axios";

const url = process.env.VUE_APP_ROOT_API;
function speciality_list(params) {
  axios.defaults.headers.common = { Authorization: `Bearer ${params.token}` };
  return axios.get(`${url}/api/specialties`, {}).then(response => {
    return {
      data: response.data
    };
  });
}

function getDoctorList_byDateAndSpeciality(params) {
  axios.defaults.headers.common = { Authorization: `Bearer ${params.token}` };
  return axios
    .get(`${url}/api/schedules/doctors`, { params: params.data })
    .then(response => {
      return {
        data: response.data
      };
    });
}

function createAppointment(params) {
  axios.defaults.headers.common = { Authorization: `Bearer ${params.token}` };
  return axios.post(`${url}/api/appointments`, params.data).then(response => {
    return {
      data: response.data
    };
  });
}

function getAppointment_byDate_Doctor(params) {
  axios.defaults.headers.common = { Authorization: `Bearer ${params.token}` };
  return axios
    .get(`${url}/api/appointments/doctor`, { params: params.data })
    .then(response => {
      return {
        data: response.data
      };
    });
}

function getAppointment_byProfileId(params) {
  axios.defaults.headers.common = { Authorization: `Bearer ${params.token}` };
  return axios
    .get(`${url}/api/appointments`, { params: params.data })
    .then(response => {
      return {
        data: response.data
      };
    });
}

function get_doctor_schedule_by_date(params) {
  return axios
    .get(`${url}/api/schedules`, { params: params })
    .then(response => {
      return {
        data: response.data
      };
    });
}

export default {
  speciality_list,
  getDoctorList_byDateAndSpeciality,
  createAppointment,
  getAppointment_byDate_Doctor,
  getAppointment_byProfileId,
  get_doctor_schedule_by_date
};
