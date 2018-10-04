<template>
  <div id="contractor">
    <h1>Контрагенты</h1>
    <br/>
    <div>
      <table class="table table-dark">
        <thead>
        <tr>
          <th scope="col">id</th>
          <th scope="col">Contractor Name</th>
          <th scope="col">Contact Information (place,location,phone...)</th>
        </tr>
        </thead>
        <tbody id="allContractors">
        <tr v-for="(value, index) in contractors" v-on:click="choseContractor($event,value.contractor_id)">
          <td>{{value.contractor_id}}</td>
          <td>{{value.name}}</td>
          <td>{{value.place}}</td>
        </tr>
        </tbody>
      </table>
    </div>

      Имя: <input v-model="contractorName"/>
      Контактная информация: <input v-model="contractorPlace"/>
      <button class="btn btn-info" v-if="contractorName&&contractorPlace" v-on:click="addContractor()">Добавить
        Контрагента
      </button>
      <button class="btn btn-danger" v-if="contractor_id!==0" v-on:click="deleteContractor()">Удалить Контрагента
      </button>
    <br/>
    <div v-show="contractor_id!==0">
      Игры у Контрагента:
      <table class="table table-dark">
        <thead>
        <tr>
          <th scope="col">id</th>
          <th scope="col">Game Name</th>
        </tr>
        </thead>
        <tbody id="allGames">
        <tr v-for="(value, index) in games" v-on:click="choseGame($event,value.contractor_games_id,value.game_name)">
          <td>{{value.contractor_games_id}}</td>
          <td>{{value.game_name}}</td>
        </tr>
        </tbody>
      </table>
      <div>
        <select v-model="selectedGame">
          <option v-for="option in optionsGames" v-bind:value="option.games_id">
            {{ option.game_name }}
          </option>
        </select>
        <span>Выбрано: {{ selectedGame }}</span>
        <button v-if="selectedGame" class="btn btn-info" v-on:click="addContractorGames()">Добавить Игру к Контрагенту</button>
        <button class="btn btn-danger" v-if="contractor_games_id!==0" v-on:click="deleteContractorGames()">Удалить Игру
          Контрагента
        </button>
      </div>
      <div v-if="contractor_games_id!==0">
        <div v-if="existedKey!==''">{{existedKey}} on {{countClients}} clients</div>
        <div v-else>
          <input v-model="countClients"/>
          <button v-on:click="generateKeyGame()">Generate Key for this contractor and game</button>
          <br/>
          <div v-if="gameName.toUpperCase()==='Constantine'.toUpperCase()"><input type="checkbox" v-model="isConstantine">Generate Key for ConstantineOnce</div>
        </div>
      </div>
    </div>
    </div>
</template>

<script>


  export default {
    data() {
      return {
        contractorName: '',
        contractorPlace: '',
        contractors: [],
        games: [],
        gameName:'',
        selectedGame: '',
        optionsGames: [],
        existedKey: '',
        countClients: 0,
        children: [],
        contractor_games_id: 0,
        contractor_id: 0,
        activeEl: {},
        isConstantine:false
      }
    },
    methods: {
      getContractorGames: function () {
        axios.get('/getContractorGames?contractor_id=' + this.contractor_id, {
          headers: {
            'Content-Type': 'application/json;charset=UTF-8'
          }
        })
          .then((req) => {
            this.games = req.data;
          })
          .catch((req) => {
            console.log("error loaded " + req)
          });
      },
      choseContractor: function (el, v) {
        this.children = document.getElementById("allContractors").children;
        for (let i = 0; i < this.children.length; i++) {
          this.children[i].style.background = "#212529";
        }



        this.contractor_id = v;
        el.target.parentElement.style.background = "#080b2e";
        this.contractor_games_id = 0;
        this.getContractorGames();

        let ch = document.getElementById("allGames").children;
        for (let i = 0; i < ch.length; i++) {
          ch[i].style.background = "#212529";
        }
      },
      choseGame: function (el, v,gn) {
        this.children = document.getElementById("allGames").children;
        for (let i = 0; i < this.children.length; i++) {
          this.children[i].style.background = "#212529";
        }
        this.contractor_games_id = v;
        this.gameName=gn;
        el.target.parentElement.style.background = "#080b2e";

      },
      addContractor: function () {
        axios.get('/addContractor?contractorName=' + this.contractorName + '&contractorPlace=' + this.contractorPlace, {
          headers: {
            'Content-Type': 'application/json;charset=UTF-8'
          }
        })
          .then((req) => {
            this.contractors.push({name: this.contractorName, place: this.contractorPlace});
          })
          .catch((req) => {
            console.log("error loaded " + req)
          });
        this.getContractors();
      },
      deleteContractorGames: function () {
        axios.get('/deleteContractorGames?contractor_games_id=' + this.contractor_games_id, {
          headers: {
            'Content-Type': 'application/json;charset=UTF-8'
          }
        })
          .then((req) => {
            console.log("success addContractorGames");
            this.getContractorGames();
          })
          .catch((req) => {
            console.log("error loaded " + req)
          });
      },
      addContractorGames: function () {
        axios.get('/addContractorGames?contractor_id=' + this.contractor_id + '&games_id=' + this.selectedGame, {
          headers: {
            'Content-Type': 'application/json;charset=UTF-8'
          }
        })
          .then((req) => {
            console.log("success addContractorGames");
            this.getContractorGames();
          })
          .catch((req) => {
            console.log("error loaded " + req)
          });
      },
      deleteContractor: function () {
        axios.get('/deleteContractor?contractor_id=' + this.contractor_id, {
          headers: {
            'Content-Type': 'application/json;charset=UTF-8'
          }
        })
          .then((req) => {
            console.log(req.data)
          })
          .catch((req) => {
            console.log("error loaded " + req)
          });
        this.getContractors();
      },
      generateKeyGame: function () {
        axios.get('/generateAndSaveMd5Key?contractor_games_id=' + this.contractor_games_id + '&count=' + this.countClients+ '&isConstantine=' +this.isConstantine, {
          headers: {
            'Content-Type': 'application/json;charset=UTF-8'
          }
        })
          .then((req) => {
            console.log("this key");
            if (req.data !== "error") {
              this.existedKey = req.data;
            }
            else {
              this.existedKey = '';
            }
          })
          .catch((req) => {
            console.log("error loaded " + req)
          });
      },
      getContractors: function () {
        axios.get('/getContractors', {
          headers: {
            'Content-Type': 'application/json;charset=UTF-8'
          }
        })
          .then((req) => {
            console.log("test");
            this.contractors = req.data;
          })
          .catch((req) => {
            console.log("error loaded " + req)
          });
      },
      getGames: function () {
        axios.get('/getGames', {
          headers: {
            'Content-Type': 'application/json;charset=UTF-8'
          }
        })
          .then((req) => {
            this.optionsGames = req.data;
          })
          .catch((req) => {
            console.log("error loaded " + req)
          });
      }
    },
    watch: {
      contractor_games_id: function (v) {
        if (v !== 0) {
          axios.get('/getMd5Key?contractor_games_id=' + v, {
            headers: {
              'Content-Type': 'application/json;charset=UTF-8'
            }
          })
            .then((req) => {

              if (req.data.key_game) {
                this.existedKey = req.data.key_game;
                this.countClients = req.data.count;
              }
              else {
                this.existedKey = '';

              }
              console.log("this key: " + this.existedKey);
            })
            .catch((req) => {
              console.log("error loaded " + req);
              this.existedKey = '';
            });
        }
      }
    },

    created: function () {

      this.getContractors();
      this.getGames();

    },
  }
</script>

<style>
  .activeTr {
    background-color: #080b2e;
  }


</style>
