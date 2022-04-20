import service from "@/store/services/record";

const state = {};

const mutations = {};

const actions = {
  create_record({ commit }, params) {
    return service.create_record(params).then(({ data }) => {
      console.log("add new appointment success");
    });
  }
};

const getters = {};

export default {
  namespaced: true,
  state,
  mutations,
  actions,
  getters
};
