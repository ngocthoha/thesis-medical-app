import Vue from "vue";
import { VueAuthenticate } from "vue-authenticate";

import axios from "axios";
import VueAxios from "vue-axios";
Vue.use(VueAxios, axios);

const vueAuth = new VueAuthenticate(Vue.prototype.$http, {
  baseUrl: "http://127.0.0.1:9090/admin",
  tokenName: "access_token",
  loginUrl: "/user/login",
  registerUrl: "/user/register"
});

export default {
  state: {
    // isAuthenticated: localStorage.getItem("vue-authenticate.vueauth_access_token") !== null
    isAuthenticated: false
  },

  getters: {
    isAuthenticated(state) {
      return state.isAuthenticated;
    }
  },

  mutations: {
    isAuthenticated(state, payload) {
      state.isAuthenticated = payload.isAuthenticated;
    }
  },

  actions: {
    login(context, payload) {
      return vueAuth.login(payload.user, payload.requestOptions).then(response => {
        if(response.data.code == 0){
          context.commit("isAuthenticated", {
            isAuthenticated: vueAuth.isAuthenticated()
          });
        }
      });
    },

    // register(context, payload) {
    //   return vueAuth.register(payload.user, payload.requestOptions).then(response => {
    //     context.commit("isAuthenticated", {
    //       isAuthenticated: vueAuth.isAuthenticated()
    //     });
    //     router.push({name: "Home"});
    //   });
    // },

    logout(context, payload) {
      return vueAuth.logout().then(response => {
        context.commit("isAuthenticated", {
          isAuthenticated: vueAuth.isAuthenticated()
        });
      });
    }
  }
};
