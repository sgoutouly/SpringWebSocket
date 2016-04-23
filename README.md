# SpringWebSocket

## Configuration de WebSocket avec STOMP. Implique l'usage d'un broker pour router les message vers des destinataires différents (clients connectés, contrôleur applicatif ou autre) en fonction d'un entête de type destinataire placé dans l'enveloppe STOMP

### Mise en oeuvre d'un broker basique qui broadcaste les messages vers les clients abonnés au channel /topic et d'un channel applicatif (/app) sur lequel un contrrôleur (WebSocketController) va écouter les messages et les faire suivre à son tour après avoir ajouté un préfixe