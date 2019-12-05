import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    player: [],  
  },
  getters: {

  },
  mutations: {
    SAFE_PLAYER_INFO(state, player){
      state.player = player;
    }
  },
  actions: {
    SavePlayerInfo({commit}, player ){
      commit('SAFE_PLAYER_INFO', player);
    }
  },
  modules: {
  }
})
