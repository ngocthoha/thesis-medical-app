import Vue from 'vue';
import Vuex from 'vuex';
import auth from "./modules/auth";
import profile from "./modules/profile";
import appointment from "./modules/appointment";
import scheduledoctor from "./modules/scheduledoctor";
import record from "./modules/record";
Vue.use(Vuex);

export default new Vuex.Store({
    modules: {
      auth,
      profile,
      appointment,
      scheduledoctor,
      record
    }
});