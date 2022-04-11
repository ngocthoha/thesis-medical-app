import service from "@/store/services/auth";

const state = {
  access_token: null,
};

const mutations = {
  SET_TOKEN: (state, token) => {
    state.access_token = token;
  },
};

const actions = {
  login({ commit }, params) {
    return service.login(params).then(({ data }) => {
      if (data.access_token != null && data.access_token != "") {
        commit("SET_TOKEN", data.access_token);
        console.log("login success");
      } else {
        console.log("fail success");
      }
    });
  },
};

const getters = {
  access_token: (access_token) => state.access_token,
};

const auth = {
  namespaced: true,
  state,
  getters,
  actions,
  mutations,
};

export default auth;
