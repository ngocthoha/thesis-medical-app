import service from "@/store/services/appointment";

const state = {
  speciality_list: [],
  doctors_list: [],
  doctor_appointment_list: [],
  user_appointment_list: [],
  make_appointment_doctor_select: {},
  make_appointment_doctor_schedule: [],
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

  SET_USER_APPOINTMENT_LIST: (state, list) => {
    state.user_appointment_list = list;
  },

  SET_MAKE_APPOINTMENT_DOCTOR_SELECT: (
    state,
    make_appointment_doctor_select
  ) => {
    state.make_appointment_doctor_select = make_appointment_doctor_select;
  },
};

const actions = {
  speciality_list({ commit }, params) {
    return service.speciality_list(params).then(({ data }) => {
      if (data.code == 1) {
        commit("SET_SPECIALITY_LIST", data.results);
      } else {
        console.log("error");
      }
    });
  },

  getDoctorList_byDateAndSpeciality({ commit }, params) {
    return service
      .getDoctorList_byDateAndSpeciality(params)
      .then(({ data }) => {
        if (data.code == 1) {
          commit("SET_DOCTOR_LIST", data.results);
        } else {
          console.log("error");
        }
      });
  },

  createAppointment({ commit }, params) {
    return service.createAppointment(params).then(({ data }) => {
      console.log("add new appointment success");
    });
  },

  getAppointment_byDate_Doctor({ commit }, params) {
    return service.getAppointment_byDate_Doctor(params).then(({ data }) => {
      if (data.code == 1) {
        commit("SET_DOCTOR_APPOINTMENT_LIST", data.results);
      } else {
        console.log("error");
      }
    });
  },

  getAppointment_byProfileId({ commit }, params) {
    return service.getAppointment_byProfileId(params).then(({ data }) => {
      if (data.code == 1) {
        commit("SET_USER_APPOINTMENT_LIST", data.results);
      } else {
        console.log("error");
      }
    });
  },

  set_doctor_select_to_make_appointment({ commit }, params) {
    commit("SET_MAKE_APPOINTMENT_DOCTOR_SELECT", params);
  },

  get_doctor_schedule({ commit }, params) {
    return service.getAppointment_byProfileId(params).then(({ data }) => {
      if (data.code == 1) {
        commit("SET_USER_APPOINTMENT_LIST", data.results);
      } else {
        console.log("error");
      }
    });
  },
};

const getters = {
  speciality_list: (state) => state.speciality_list,
  doctors_list: (state) => state.doctors_list,
  doctor_appointment_list: (state) => state.doctor_appointment_list,
  user_appointment_list: (state) => state.user_appointment_list,
  make_appointment_doctor_select: (state) =>
    state.make_appointment_doctor_select,
};

export default {
  namespaced: true,
  state,
  mutations,
  actions,
  getters,
};
