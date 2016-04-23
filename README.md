# SpringWebSocket

## Step 3 : Configuration de WebSocket avec STOMP + channel applicatif

Mise en oeuvre d'un broker basique qui broadcaste les messages vers les clients abonnés au channel /topic et d'un channel applicatif (/app) sur lequel un contrrôleur (WebSocketController) va écouter les messages et les faire suivre à son tour après avoir ajouté un préfixe