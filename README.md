# SpringWebSocket

## Configuration de WebSocket avec STOMP.

### Implique l'usage d'un broker pour router, les message vers des destinataires différents
(clients connectés, contrôleur applicatif ou autre) en fonction d'un entête de type destinataire placé dans
l'enveloppe STOMP
Ici on se contentra de router les messages vers les clients connectés
