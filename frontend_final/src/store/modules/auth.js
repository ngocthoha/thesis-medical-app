import service from "@/store/services/auth";
import jwt_decode from "jwt-decode";

const state = {
  access_token: null,
  types: [],
  isLogin: false,
  is_signup_submit_success: false,
  is_verify_submit_success: false,
  username: null,
  isDoctor: false,
  isUser: false,
  userId: null
};

const mutations = {
  SET_TOKEN: (state, token) => {
    state.access_token = token;
  },
  SET_TYPE: (state, types) => {
    state.types = types;
  },
  SET_IS_LOGIN: (state, isLogin) => {
    state.isLogin = isLogin;
  },
  SET_SIGNUP_SUBMIT: (state, is_signup_submit_success) => {
    state.is_signup_submit_success = is_signup_submit_success;
  },

  SET_VERIFY_SUBMIT: (state, is_verify_submit_success) => {
    state.is_verify_submit_success = is_verify_submit_success;
  },

  SET_USERNAME: (state, username) => {
    state.username = username;
  },

  SET_IS_DOCTOR: (state, isDoctor) => {
    state.isDoctor = isDoctor;
  },

  SET_IS_USER: (state, isUser) => {
    state.isUser = isUser;
  },

  SET_USER_ID: (state, userId) => {
    state.userId = userId;
  }
};

const actions = {
  login({ commit }, params) {
    return service.login(params).then(({ data }) => {
      if (data.access_token != null && data.access_token != "") {
        var decoded = jwt_decode(data.access_token);
        localStorage.setItem("username", decoded.sub);
        let isDoctor = (decoded.roles || []).some(r => r === "ROLE_DOCTOR");
        let isUser = (decoded.roles || []).some(r => r === "ROLE_USER");
        let userId = (decoded.roles || []).filter(
          r => !["ROLE_DOCTOR", "ROLE_USER", "ROLE_ADMIN"].includes(r)
        );
        commit("SET_USERNAME", decoded.sub);
        commit("SET_USER_ID", userId[0]);
        commit("SET_IS_DOCTOR", isDoctor);
        commit("SET_IS_USER", isUser);
        commit("SET_TOKEN", data.access_token);
        commit("SET_TYPE", data.type);
        commit("SET_IS_LOGIN", true);
        // get the decoded payload and header
        //var decoded = jwt.decode(token, {complete: true});;
        console.log("login success");
      } else {
        commit("SET_TOKEN", "");
        commit("SET_IS_LOGIN", false);
        console.log("login fail");
      }
    });
  },

  logout({ commit }, params) {
    commit("SET_TOKEN", "");
    commit("SET_IS_LOGIN", false);
  },

  signup({ commit }, params) {
    return service.signup(params).then(({ data }) => {
      if (data.code == 201) {
        commit("SET_SIGNUP_SUBMIT", true);
        console.log("signup success, verify otp");
      } else {
        console.log("signup fail");
      }
    });
  },

  verify_signup({ commit }, params) {
    return service.verify_signup(params).then(({ data }) => {
      if (data.code == 200) {
        commit("SET_VERIFY_SUBMIT", true);
        console.log("signup success, verify otp");
      } else {
        console.log("signup fail");
      }
    });
  }
};

const getters = {
  access_token: state => state.access_token,
  types: state => state.types,
  isLogin: state => state.isLogin,
  is_signup_submit_success: state => state.is_signup_submit_success,
  is_verify_submit_success: state => state.is_verify_submit_success,
  username: state => state.username,
  isDoctor: state => state.isDoctor,
  isUser: state => state.isUser,
  userId: state => state.userId
};

export default {
  namespaced: true,
  state,
  mutations,
  actions,
  getters
};
