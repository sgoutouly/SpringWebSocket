# SpringWebSocket

## Configuration de WebSocket avec STOMP

### Implique l'usage d'un broker pour router les messages vers des destinataires de différentes natures (clients connectés, contrôleurs applicatifs ou autre)

L'entête du message STOMP contient un header de type destination

Ici on se contentra de router les messages vers les clients connectés
