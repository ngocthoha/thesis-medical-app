import service from "@/store/services/auth";

const state = {
  access_token: null,
  types: [],
  isLogin: false,
};

const mutations = {
  SET_TOKEN: (state, token) => {
    state.access_token = token;
  },
  SET_TYPE: (state, types) => {
    state.types = types;
  },
  SET_IS_LOGIN: (state, isLogin)=>{
    state.isLogin = isLogin
  }
};

const actions = {
  login({ commit }, params) {
    return service.login(params).then(({ data }) => {
      if (data.access_token != null && data.access_token != "") {
        commit("SET_TOKEN", data.access_token);
        commit("SET_TYPE", data.type);
        commit("SET_IS_LOGIN",true);
        // get the decoded payload and header
        //var decoded = jwt.decode(token, {complete: true});;
        console.log("login success");
      }
      else {
        commit("SET_TOKEN", "");
        commit("SET_IS_LOGIN", false);
        console.log("fail success");
      }
    });
  },

  logout({ commit }, params) {
    commit("SET_TOKEN", "");
    commit("SET_IS_LOGIN",false);
  }
};

const getters = {
  access_token: state => state.access_token,
  types: state => state.types,
  isLogin: state=>state.isLogin
};

export default {
  namespaced: true,
  state,
  mutations,
  actions,
  getters
};
