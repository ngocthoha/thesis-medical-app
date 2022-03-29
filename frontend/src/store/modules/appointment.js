
import service from '@/store/services/appointment';

const state ={
    speciality_list: [],
};

const mutations = {
  SET_SPECIALITY_LIST: (state,list)=>{
    state.speciality_list = list
  }
};

const actions ={
    speciality_list({commit},params){
      return service.speciality_list(params).then(({data})=>{
        commit('SET_SPECIALITY_LIST',data)
    });
  },
}

const getters ={
    speciality_list: speciality_list => state.speciality_list,
};

const appointment ={
  namespaced: true,
  state,
  getters,
  actions,
  mutations,
};

export default appointment;