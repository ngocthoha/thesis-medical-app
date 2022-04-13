import service from "@/store/services/rooms";

const state = {
    list_rooms: [],
}

const mutations = {
    GET_LIST_ROOM: (state, list) => {
        state.list_rooms = list;
      },
};

const actions = {
    get_room({ commit }, params) {
        return service.get_room_list(params).then(({ data }) => {
          if (data.code == 1) {
            commit("GET_LIST_ROOM", data.results);
          } else {
            console.log("error");
          }
        });
      },
};

const getters = {
    list_rooms: (list_rooms) => state.list_rooms,
};

const rooms = {
    namespaced: true,
    state,
    getters,
    actions,
    mutations,
  };
  
  export default rooms;