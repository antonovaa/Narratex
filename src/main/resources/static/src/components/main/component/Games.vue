<template>
  <div id="games">
    <h1>Игры</h1>
    <br/>
    <table class="table table-dark">
      <thead>
      <tr>
        <th scope="col">id</th>
        <th scope="col">Game Name</th>
      </tr>
      </thead>
      <tbody id="game">
      <tr v-for="(value, index) in games" v-on:click="choseGame($event,value.games_id)">
        <td>{{value.games_id}}</td>
        <td>{{value.game_name}}</td>
      </tr>
      </tbody>
    </table>
    <div>
      <button class="btn btn-danger" v-if="games_id!==0" v-on:click="deleteGame()">Удалить Игру Контрагента</button>
      <div>
        <input v-model="game"/>
        <button class="btn btn-info" v-show="game" v-on:click="addGame()">Добавить Игру</button>
      </div>
    </div>
  </div>
</template>

<script>


  export default {
    data() {
      return {
        contractorName: '',
        game:'',
        games_id:0,
        games:[],
        children:[],
      }
    },
    created:function(){
      this.getGames();
    },
    methods:{
      choseGame:function(el,v){
        this.children=document.getElementById("game").children;
        for(let i=0;i<this.children.length;i++)
        {
          this.children[i].style.background = "#212529";
        }
        this.games_id=v;
        el.target.parentElement.style.background="#080b2e";

      },
      addGame:function () {
          axios.get('/addGame?gameName='+this.game, {
            headers: {
              'Content-Type': 'application/json;charset=UTF-8'
            }
          })
            .then((req) => {
              this.getGames();
              console.log("success "+req);
            })
            .catch((req) => {
              console.log("error "+req)
            });
      },
      getGames:function () {
        axios.get('/getGames', {
      headers: {
        'Content-Type': 'application/json;charset=UTF-8'
      }
    })
      .then((req) => {
        this.games=req.data;
        console.log("success loaded "+req);
      })
      .catch((req) => {
        console.log("error loaded "+req)
      });
  },
      deleteGame:function(){
        axios.get('/deleteGame?games_id='+this.games_id, {
          headers: {
            'Content-Type': 'application/json;charset=UTF-8'
          }
        })
          .then((req) => {
            this.getGames();
          })
          .catch((req) => {
            console.log("error loaded "+req)
          });
        this.getContractors();
      },
    }
  }
</script>

<style>


</style>
