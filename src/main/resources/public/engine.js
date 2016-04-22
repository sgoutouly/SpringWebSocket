
 var stompClient;
 var subscription;

  function init() {

    websocket = new WebSocket("ws://localhost:8080/servicesSocket");
    websocket.onclose = function(evt) { writeToScreen("DISCONNECTED"); };
    websocket.onerror = function(evt) { writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data) };

    document.getElementById("sendToChatButton").addEventListener("click",
      function() {
        doSendToChat(document.getElementById('message').value);
        document.getElementById('message').value = '';
      }
      ,false
    );


    document.getElementById("sendToAppButton").addEventListener("click",
      function() {
        doSendToApplication(document.getElementById('message').value);
        document.getElementById('message').value = '';
      }
      ,false
    );

    stompClient = Stomp.over(websocket);
    stompClient.connect({},
      function() {
        writeToScreen("CONNECTED");
        subscribeToChat();
      },
      function(error) {
        writeToScreen("ERREUR DE CONNEXION : " + error.headers.message);
      });
  }

  function subscribeToChat() {
    subscription = stompClient.subscribe("/topic/chat",
      function(message) {
        writeToScreen('<span style="color: blue;">RESPONSE: ' + message.body + '</span>')
      });
  }

  function doSendToChat(message) {
    writeToScreen("SENT: " + message);
    stompClient.send("/topic/chat", {}, message);
  }

    function doSendToApplication(message) {
      writeToScreen("SENT TO APPLICATION: " + message);
      stompClient.send("/app/touch", {}, message);
    }

  function writeToScreen(message) {
    var pre = document.createElement("p");
    pre.style.wordWrap = "break-word";
    pre.innerHTML = message;
    document.getElementById("output").appendChild(pre);
  }

  window.addEventListener("load", init, false);