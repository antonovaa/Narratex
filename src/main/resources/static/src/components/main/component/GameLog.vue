<template>
  <div id="gameLog">
    <h1>{{game}}</h1>
    <button v-on:click="Change(0)" class="btn">Log played games</button>
    <button v-on:click="Change(1)" class="btn">Log crashed games</button>
    <br/>
    <select v-model="selectedContractor">
      <option v-for="option in optionsContractors" v-bind:value="option.contractor_id">
        {{ option.name }}
      </option>
    </select>
    <select v-model="selectedGame">
      <option v-for="option in optionsGames" v-bind:value="option.games_id">
        {{ option.game_name }}
      </option>
    </select>
    дата начала игры <input id="date_begin" type="date" v-model="dateSearchBegin">
    дата конца игры <input id="date_end" type="date" v-model="dateSearchEnd">
    <button class="btn" v-on:click="DoFilter()">Отфильтровать</button>
    <div v-if="show_log">
      <p>Количество Игр: {{countGames}}, количество игроков в играх {{countPlayers}}</p>
      <div style="height: 700px; overflow-y: scroll;"><table class="table table-dark">
        <thead>
        <tr>
          <th scope="col">id</th>
          <th scope="col">Game Name</th>
          <th scope="col">Mac Address</th>
          <th scope="col">Game start</th>
          <th scope="col">Game end</th>
          <th scope="col">Play start</th>
          <th scope="col">Number player</th>
          <th scope="col">Server IP</th>
          <th scope="col">Info</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(value, index) in rowsLog ">
          <td>{{index+1}}</td>
          <td>{{value.game_name}}</td>
          <td>{{value.mac_addr}}</td>
          <td>{{value.game_start}}</td>
          <td>{{value.game_end}}</td>
          <td>{{value.play_start}}</td>
          <td>{{value.player_count}}</td>
          <td>{{value.server_ip}}</td>
          <td>{{value.place_game}}</td>
        </tr>
        </tbody>
      </table></div>
    </div>
    <div v-else style="height: 700px; overflow-y: scroll;">
      <table class="table table-dark">
        <thead>
        <tr>
          <th scope="col">id</th>
          <th scope="col">Game start</th>
          <th scope="col">Game Name</th>
          <th scope="col">Player IP</th>
          <th scope="col">Server IP</th>
          <th scope="col">Crash player</th>
          <th scope="col">Crash server</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(v, index) in rowsCrash ">
          <td>{{index+1}}</td>
          <td>{{v.game_date}}</td>
          <td>{{v.game_name}}</td>
          <td>{{v.player_ip}}</td>
          <td>{{v.server_ip}}</td>
          <td>
            <div v-if="v.log_player!==null&&v.log_player!==''&&v.log_player!==' '">
              <button class="btn btn-primary"
                      v-on:click="ShowModalWindow(0,index)">show log
              </button>
            </div>
          </td>
          <td>
            <div v-if="v.log_server!==null&&v.log_server!==''&&v.log_server!==' '">
              <button class="btn btn-primary"
                      v-on:click="ShowModalWindow(1,index)">show log
              </button>
            </div>
          </td>
        </tr>
        </tbody>
      </table>
    </div>


    <div id="myModal" class="modal">

      <!-- Modal content -->
      <div class="modal-content">
        <span class="close" v-on:click="closeModalWindow()">&times;</span>
        <p v-for="inf in info">{{inf}}</p>
      </div>

    </div>

  </div>
</template>

<script>


  export default {
    data() {
      return {
        rowsLog: [],
        rowsCrash: [],
        dateSearchBegin: '',
        dateSearchEnd: '',
        gameSearch: '',
        cvn: '',
        temp: '',
        show_log: true,
        info: [],
        gameName: '',
        contractorName: '',
        selectedGame: 0,
        optionsGames: [],
        selectedContractor: 0,
        optionsContractors: [],
        countGames:0,
        countPlayers:0
      }
    },
    watch: {
      rowsLog: function (v) {
        this.countGames=0;
        this.countPlayers=0;
        for (let i = 0; i < v.length; i++) {

          if(v[i].game_end.substring(0,6)==="Finish")
          {
            this.countGames++;
            this.countPlayers+=v[i].player_count;
          }
        }


      }
    },
    components: {},
    computed: {
      game: function () {
        this.temp = this.$store.state.currentGameSearch;
        console.log(this.temp);
        return this.temp;
      }
    },
    methods: {
      getTable: function (url, type, b, e, sg, sc) {
        axios.get('/' + url + '?&date_begin=' + b + '&date_end=' + e + '&contractor_id=' + sc + '&game_id=' + sg, {
          headers: {
            'Content-Type': 'application/json;charset=UTF-8'
          }
        })
          .then((req) => {
            if (url === 'showLogList') {
              this.rowsLog = req.data;
            }
            else {
              this.rowsCrash = req.data;
            }
            console.log(this.rowsLog);
          })
          .catch((req) => {
            console.log(req.data)
          });
      },
      DoFilter: function () {
        if (this.show_log) {
          this.getTable("showLogList", this.temp, this.dateSearchBegin, this.dateSearchEnd, this.selectedGame, this.selectedContractor);
        }
        else {
          this.getTable("showCrashList", this.temp, this.dateSearchBegin, this.dateSearchEnd, this.selectedGame, this.selectedContractor);
        }
      },
      Change: function (a) {
        if (a === 0) {
          this.show_log = true;
        }
        if (a === 1) {
          this.show_log = false;
        }


      },
      ShowModalWindow: function (type, index) {
        document.getElementById('myModal').style.display = "block";
        if (type === 0) {
          this.info = this.rowsCrash[index].log_player.split('$$');
        }
        else {
          this.info = this.rowsCrash[index].log_server.split('$$');
        }
      },
      closeModalWindow: function () {
        document.getElementById('myModal').style.display = "none";
      },
      windowOnClick: function (event) {
        if (event.target == document.getElementById('myModal')) {
          document.getElementById('myModal').style.display = "none";
        }
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

    },


    mounted() {
      this.$nextTick(function () {
        window.addEventListener('click', this.windowOnClick);
        //Init
        this.windowOnClick()
      })

    },

    beforeDestroy() {
      window.removeEventListener('click', this.windowOnClick);
    }

  }
</script>

<style>


  .modal {
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0, 0, 0); /* Fallback color */
    background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
  }

  /* Modal Content/Box */
  .modal-content {
    background-color: #fefefe;
    margin: 15% auto; /* 15% from the top and centered */
    padding: 20px;
    border: 1px solid #888;
    width: 80%; /* Could be more or less, depending on screen size */
  }

  /* The Close Button */
  .close {
    color: #aaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
  }

  .close:hover,
  .close:focus {
    color: black;
    text-decoration: none;
    cursor: pointer;
  }
</style>
