<template>
  <div id="header">

    <nav class="navbar navbar-inverse bg-inverse">
      <div class="container-fluid">
        <div>
          <form class="form-inline">
            <button class="btn btn-sm align-middle btn-info" v-on:click="openMainForm(1)" type="button">
              Contractors
            </button>
            <button class="btn btn-sm align-middle btn-info" v-on:click="openMainForm(2)" type="button">
              Games
            </button>
            <button class="btn btn-sm align-middle btn-info" v-on:click="openMainForm(3)" type="button">
              Game Log
            </button>
            <button class="btn btn-sm align-middle btn-info" v-on:click="openMainForm(4)" type="button">
              Init Games
            </button>
          </form>
        </div>
        <div>
          <form class="form-inline">
            <input class="form-control mr-sm-2" type="text" placeholder="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
          </form>
        </div>
      </div>
    </nav>

  </div>
</template>

<script>
  //  import { bus } from '../../main.js'

  export default {
    data() {
      return {
        message: '',
        allMessages: '',
        keyG:''
      }
    },
    methods: {
      openMainForm: function (a) {
          this.$store.dispatch('setCurrentView', a);
        }
    },
    computed: {
      countTest: function () {
        return this.$store.state.count;
      },
      show: function () {
        return this.$store.state.isAuthorised === 1;
      },
      myName: function () {
        return this.$store.state.name;
      }
    },
  }
</script>

<style>

  .btn{
    margin-left: 20px;
  }
  #header {
    height: 100px;
    background-image: url("https://static.wixstatic.com/media/4fad7a_09139bf1f6204fd39caca0b57c615798~mv2.jpg/v1/crop/x_0,y_200,w_1920,h_822/fill/w_980,h_354,al_c,q_80,usm_0.66_1.00_0.01/4fad7a_09139bf1f6204fd39caca0b57c615798~mv2.webp");
  }


</style>
