
 var stompClient;
 var subscription;

  function init() {
    websocket = new WebSocket("ws://localhost:8080/servicesSocket");
    websocket.onclose = function(evt) { writeToScreen("DISCONNECTED"); };
    websocket.onerror = function(evt) { writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data) };

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

  function doSend(message) {
    writeToScreen("SENT: " + message);
    stompClient.send("/topic/chat", {}, message);
  }

  function writeToScreen(message) {
    var pre = document.createElement("p");
    pre.style.wordWrap = "break-word";
    pre.innerHTML = message;
    document.getElementById("output").appendChild(pre);
  }

  window.addEventListener("load", init, false);