<template>
  <div id="main-content" class="container">
    <div class="row">
      <div class="col-md-6">
        <div class="form-group">
          {{connected}}
          <label for="connect">WebSocket connection:</label>
          <!-- <button id="connect" class="btn btn-default" v-on:click="connect">Connect</button> -->
          <!-- <button v-if="connected" class="btn btn-default" v-on:click="disconnect">Disconnect</button> -->
        </div>
      </div>
      <div class="col-md-6">
        <div class="form-group">
          <label for="name">What is your name?</label>
          <input type="text" v-model="Name" class="form-control" placeholder="Your name here..." />
        </div>
        <button v-if="connected" class="btn btn-default" v-on:click="sendName">Send</button>
      </div>
    </div>
    <div class="row">
      <div class="col-md-12">
        <table v-if="connected" class="table table-striped">
          <thead>
            <tr>
              <th>Greetings</th>
            </tr>
          </thead>
          <tbody id="greetings">
            <tr v-for="(greeting, index) in greetings" v-bind:key="greeting+index">
              <td>{{greeting.message}}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import SockJS from "sockjs-client";
import Stomp from "stompjs";

export default {
  name: "Login",
  components: {},
  data: function() {
    return {
      Name: "",
      stompClient: null,
      connected: false,
      greetings: []
    };
  },
  methods: {
    // connect() {
    //   var socket = new SockJS("http://localhost:8080/gs-guide-websocket");
    //   this.stompClient = Stomp.over(socket);
    //   this.stompClient.connect({}, frame => {

    //     console.log("Connected: " + frame);

    //     this.stompClient.subscribe("/topic/greetings", greeting => {
    //       this.showGreeting(JSON.parse(greeting.body).content);
    //     });
    //   });

    //   console.log("Connected!");
    //   this.connected = true;
    // },
    // disconnect() {
    //   if (this.stompClient !== null) {
    //     this.stompClient.disconnect();
    //   }
    //   this.connected = false;
    //   console.log("Disconnected");
    // },
    sendName() {
      this.stompClient.send(
        "/app/hello",
        {},
        JSON.stringify({ name: this.Name })
      );
    },
    showGreeting(message) {
      var greeting = {
        message: message
      };
      this.greetings.push(greeting);
    }
  },
  created: function() {
    var socket = new SockJS("http://localhost:8080/gs-guide-websocket");
    this.stompClient = Stomp.over(socket);
    this.stompClient.connect({}, frame => {
      console.log("Connected: " + frame);

      this.stompClient.subscribe("/topic/greetings", greeting => {
        this.showGreeting(JSON.parse(greeting.body).content);
      });
    });

    console.log("Connected!");
    this.connected = true;
  },
  destroyed: function() {
    if (this.stompClient !== null) {
      this.stompClient.disconnect();
    }
    this.connected = false;
    console.log("Disconnected");
  }
};
</script>