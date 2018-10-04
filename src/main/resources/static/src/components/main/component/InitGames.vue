<template>
  <div id="initGames">
    <h1>Инициализированные игры контрагентов</h1>
    <br/>
    <select v-model="contractorId">
      <option v-for="option in optionsContractors" v-bind:value="option.contractor_id">
        {{ option.name }}
      </option>
    </select>
    <button class="btn" v-on:click="getInitGames()">Отфильтровать</button>
    <table class="table table-dark">
      <thead>
      <tr>
        <th scope="col">id</th>
        <th scope="col">Key Game</th>
        <th scope="col">Info Game(place or other info)</th>
        <th scope="col">Mac address</th>
        <th scope="col">Game Name</th>
        <th scope="col">Contractor Name</th>
        <th scope="col">is Allowed play</th>
        <th scope="col">Действие</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(value, index) in infoAll">
        <td>{{value.id}}</td>
        <td>{{value.key_game}}</td>
        <td>{{value.place_game}}</td>
        <td>{{value.mac_address}}</td>
        <td>{{value.game_name}}</td>
        <td>{{value.contragent_name}}</td>
        <td>{{value.isallowed}}</td>
        <td>
          <button v-show="value.key_game!=='KeyGeneratedForOnceContractor'" v-on:click="changeResolution(value.id)"><p v-if="value.isallowed">Заблокировать</p>
            <p v-else>Разблокировать</p></button>
        </td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>


  export default {
    data() {
      return {
        infoAll: [],
        contractorId:0,
        optionsContractors:[]
      }
    },
    created: function () {

      axios.get('/getContractors', {
        headers: {
          'Content-Type': 'application/json;charset=UTF-8'
        }
      })
        .then((req) => {
          this.optionsContractors = req.data;
        })
        .catch((req) => {
          console.log("error loaded " + req)
        });

      this.getInitGames();
    },
    methods: {
      changeResolution:function(init_contractor_game_mac_key_id){
        axios.get('/changeResolution?init_contractor_game_mac_key_id='+init_contractor_game_mac_key_id, {
          headers: {
            'Content-Type': 'application/json;charset=UTF-8'
          }
        })
          .then((req) => {
            this.getInitGames();
          })
          .catch((req) => {
            console.log("error loaded "+req)
          });
      },
      getInitGames: function () {
        axios.get('/getInitContractorGame?contractorId='+this.contractorId, {
          headers: {
            'Content-Type': 'application/json;charset=UTF-8'
          }
        })
          .then((req) => {
            this.infoAll = req.data;
            console.log("success loaded " + req);
          })
          .catch((req) => {
            console.log("error loaded " + req)
          });
      }
    }
  }
</script>

<style>


</style>
