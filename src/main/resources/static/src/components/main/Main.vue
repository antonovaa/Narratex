<template>
  <div id="general">
    <component v-bind:is="this.currentView" :number="cvn" >    </component>

  </div>
</template>

<script>

  import GameLog from './component/GameLog.vue'
  import Contractors from './component/Contractors.vue'
  import Games from './component/Games.vue'
  import InitGames from './component/InitGames.vue'


  export default {
    data() {
      return {
        temp:'',
        cvn:''
      }
    },
    components:{
      'game-log':GameLog,
      'contractors':Contractors,
      'games':Games,
      'init-games':InitGames
    },
    computed:{
      currentView:function() {
        this.cvn=this.$store.state.currentViewNumber;
        if (this.cvn === 1) {return 'contractors';}
        if (this.cvn === 2) {return 'games';}
        if (this.cvn === 3) {return 'game-log';}
        if (this.cvn === 4) {return 'init-games';}
      }
    }
  }
</script>

<style>


</style>
