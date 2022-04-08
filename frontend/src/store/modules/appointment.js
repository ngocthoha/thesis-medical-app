import service from "@/store/services/appointment";

import service from "@/store/services/appointment";

const state = {
  speciality_list: [],
  doctors_list: [],
  doctor_appointment_list: [],
};

const mutations = {
  SET_SPECIALITY_LIST: (state, list) => {
    state.speciality_list = list;
  },

  SET_DOCTOR_LIST: (state, list) => {
    state.doctors_list = list;
  },

  SET_DOCTOR_APPOINTMENT_LIST: (state, list) => {
    state.doctor_appointment_list = list;
  },
};

const actions = {
  speciality_list({ commit }, params) {
    return service.speciality_list(params).then(({ data }) => {
      commit("SET_SPECIALITY_LIST", data);
    });
  },

  getDoctorList_byDateAndSpeciality({ commit }, params) {
    return service
      .getDoctorList_byDateAndSpeciality(params)
      .then(({ data }) => {
        commit("SET_DOCTOR_LIST", data);
      });
  },

  createAppointment({ commit }, params) {
    return service.createAppointment(params).then(({ data }) => {
      console.log("add new appointment success");
    });
  },

  getAppointment_byDate_Doctor({ commit }, params) {
    return service.getAppointment_byDate_Doctor(params).then(({ data }) => {
      commit("SET_DOCTOR_APPOINTMENT_LIST", data);
    });
  },
};

const getters = {
  speciality_list: (speciality_list) => state.speciality_list,
  doctors_list: (doctors_list) => state.doctors_list,
  doctor_appointment_list: (doctor_appointment_list) =>
    state.doctor_appointment_list,
};

const appointment = {
  namespaced: true,
  state,
  getters,
  actions,
  mutations,
};

export default appointment;
