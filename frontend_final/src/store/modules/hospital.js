import service from "@/store/services/hospital";

const state = {
   hospital_all_data:[]
};

const mutations = {
    SET_HOSPITAL_ALL_DATA:(state, hospital_all_data)=>{
        state.hospital_all_data = hospital_all_data
      },
};

const actions = {
    read_all_hospital({ commit }) {
        return service.read_all_hospital().then(({ data }) => {
            if (data.code == 200) {
              commit("SET_HOSPITAL_ALL_DATA", data.results);
            }
        });
    }
};


const getters = {
    hospital_all_data: state => state.hospital_all_data
};


export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters
};