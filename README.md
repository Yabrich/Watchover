# Watchover – Plugin de Modération Minecraft

**Watchover** est un plugin de modération complet pour serveurs Minecraft (Spigot/Paper 1.21+) conçu pour fournir aux équipes de modération des outils efficaces, rapides et discrets. Il propose un mode modération dédié, des outils de surveillance, un système de gestion du chat, ainsi que plusieurs fonctionnalités utilitaires pour faciliter le travail des modérateurs.

---

# 📦 Fonctionnalités principales

## 🛡️ Mode Modération (Watchover)

Le mode Watchover permet aux modérateurs d'activer un environnement optimisé pour la modération.

### Fonctionnalités incluses :

* Activation d’un mode modération avec `/wo`
* Accès rapide aux outils de modération
* Modération discrète des joueurs
* Téléportation rapide
* Tracking des joueurs
* Invisibilité
* Accès aux outils d’inspection

Ce mode est conçu pour améliorer la réactivité et la discrétion du staff.

---

## 👁️ Invisibilité et surveillance

### Invisibilité :

* `/vanish` → Invisibilité vis-à-vis des joueurs
* `/staffvanish` → Invisibilité vis-à-vis des joueurs et des staffs

### Vision nocturne :

* `/nv` → Active ou désactive la vision nocturne

### Tracking :

* `/track <pseudo>` → Affiche la position relative d'un joueur
* `/track cancel` → Stop le tracking

### Coordonnées joueur :

* `/playerxyz <pseudo>` → Affiche les coordonnées d'un joueur

---

## ❄️ Freeze joueur

Permet d’empêcher un joueur de bouger pour vérification.

Commande :

```
/freeze <pseudo>
```

Effets :

* Empêche les déplacements
* Empêche certaines interactions
* Utile pour les contrôles

---

## 💬 Modération du chat

Watchover fournit plusieurs outils pour gérer le chat :

### Clear du chat :

```
/chatclear
```

Alias : `/cc`

---

### Activation / désactivation du chat :

```
/chat enable
/chat disable
```

---

### Mode lent (slowmode) :

```
/chat slowmode (tps en seconde)
```

Permet de limiter la fréquence des messages.

---

## 📡 Chat staff

Communication privée entre les membres du staff :

```
/staffchat <message>
```

Alias :

```
/sc
```

---

## 🕵️ Spy commandes

Permet de voir les commandes exécutées par les joueurs :

```
/spycmd
```

Alias :

```
/cmdspy
```

---

## 🚨 Alertes

Envoie une alerte sur l'écran d'un joueur :

```
/alert <pseudo>
```

---

## 📩 Système de report

Permet aux joueurs de signaler d’autres joueurs :

```
/report <pseudo> <raison>
```

---

## ❓ Système d’aide staff

Permet aux joueurs de poser une question au staff :

```
/helpme <question>
```

Réponse staff :

```
/ans <pseudo> <réponse>
```

---

## 📍 Téléportation et utilitaires

### Téléportation au spawn :

```
/spawn
```

### Téléportation d'un joueur à notre localisation :

```
/s <pseudo>
```

### Informations sur un item :

```
/id
```

---

# 🔐 Permissions

Voici la liste complète des permissions utilisées par Watchover :

| Permission               | Description                  |
|--------------------------|------------------------------|
| wo.watchover             | Accès au /wo                 |
| wo.watchoverstaff        | Accès au /wo staff           |
| wo.vanish                | Accès au /vanish             |
| wo.vanish.seeothers      | Voir les staffs invisibles   |
| wo.staffvanish           | Accès au /staffvanish        |
| wo.staffvanish.seeothers | Voir les staff invisibles    |
| wo.nightvision           | Accès au /nv                 |
| wo.spawn                 | Accès au /spawn              |
| wo.id                    | Accès au /id                 |
| wo.s                     | Accès au /s                  |
| wo.alert                 | Accès au /alert              |
| wo.freeze                | Accès au /freeze             |
| wo.spycmd                | Accès au /spycmd             |
| wo.track                 | Accès au /track              |
| wo.playerxyz             | Accès au /playerxyz          |
| wo.chat                  | Accès au /chat et /chatclear |
| wo.helpme                | Accès au /helpme             |
| wo.helpme.ans            | Accès au /ans                |
| wo.report                | Accès au /report             |
| wo.staffchat             | Accès au chat staff          |

---

# ⚙️ Compatibilité

* Minecraft : 1.21+
* Serveur : Spigot / Paper
* API : Bukkit / Spigot API

---

# 📁 Installation

1. Télécharger le fichier `.jar`
2. Placer dans le dossier :

```
/plugins/
```

3. Redémarrer le serveur

---

# 👨‍💻 Auteur

Développé par **Yabrich**

---

# 🧩 Objectif du plugin

Watchover vise à fournir un environnement de modération :

* rapide
* discret
* efficace
* centralisé

Il est particulièrement adapté aux serveurs avec une équipe de modération active.

---

# 📌 Version

Version actuelle : `2.0`

---

# 📜 Licence

Projet privé – Tous droits réservés
