import service from "@/store/services/auth";

const state = {
  access_token: null,
  types: []
};

const mutations = {
  SET_TOKEN: (state, token) => {
    state.access_token = token;
  },
  SET_TYPE: (state, types) => {
    state.types = types;
  }
};

const actions = {
  login({ commit }, params) {
    return service.login(params).then(({ data }) => {
      if (data.access_token != null && data.access_token != "") {
        commit("SET_TOKEN", data.access_token);
        commit("SET_TYPE", data.type);
        // get the decoded payload and header
        //var decoded = jwt.decode(token, {complete: true});;
        console.log("login success");
      } else {
        commit("SET_TOKEN", "");
        console.log("fail success");
      }
    });
  }
};

const getters = {
  access_token: state => state.access_token,
  types: state => state.types
};

export default {
  namespaced: true,
  state,
  mutations,
  actions,
  getters
};
