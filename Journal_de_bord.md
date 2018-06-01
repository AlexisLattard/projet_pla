# Journal de bord

## Vendredi 1 Juin :


### Design et conception
- Design sur le personnage principal, les monstres, les tourelles, entre autres  
![Monstre n°1](https://piskel-imgstore-b.appspot.com/img/612a3ce3-65a4-11e8-b96a-f7f0e10bc042.gif)
![Monstre n°2](https://piskel-imgstore-b.appspot.com/img/b46e6b14-65a4-11e8-852c-f7f0e10bc042.gif)
![Monstre n°3](https://piskel-imgstore-b.appspot.com/img/f53313e8-65a4-11e8-b852-f7f0e10bc042.gif)
![Mini-boss n°1](https://piskel-imgstore-b.appspot.com/img/f1583880-65ad-11e8-a4d6-f7f0e10bc042.gif)
![Coffre recompense defi n°1](https://piskel-imgstore-b.appspot.com/img/10200245-65ad-11e8-b2f6-f7f0e10bc042.gif)
- Développement en pixel art à l'aide de l'outil piskel
- Définition des Défis (carte sur le côté gauche)
- Finitions des maquettes de Défis


### Codage
- Début de l'IHM avec la Home Page
- Finitions de l'implémentation des différentes classes principales (voir diagramme UML) (Player, Entité, Mobs, Cristal)
- Implémentations des fonctions de base du modèle MVC pour ces classes (paint, move, etc)
- Amélioration de la fonction de création d'entités à partir d'un fichier
- Gestion des "colisions" entre les entités
- Début du developpement des fonctions pick, store, throw et getEntity

Voici une démonstration  du la création de map à partir d'un fichier, du personnage contrôlé par le clavier, la gestion de colision avec les obstacles (télés) et la possibilié de prendre et poser une tourelle (devant soi) :  
![Démonstration 1 juin](https://media.giphy.com/media/1qdONHokwpG59VLy40/giphy.gif)



## Jeudi 31 Mai :

- Prise en main de Swing, création de la map à l’aide d’un fichier texte
- Mouvement d’une entité sur cette map
- Mise en place du Gantt (planning)
- Création des mockups via MockFlow
- Modification du diagramme de classe
- Implémentation du diagramme de classe

![Diagramme UML](https://image.ibb.co/eFeXHd/PLA_UML.png)

## Mercredi 30 Mai :

- Création du diagramme de classe, création en brainstorming de toutes les  “entitées” (monstres, tourelles, personnage principal, obstacles,  armes) Voir Diagramme de classe ci dessous
- Début de réflexion sur Pop() -> Kamikaze et Wizz() -> Téléportation
- Description du “Bag” comme une collection/tableau d’entité
- Discussion à propos de la classe Weapon, ou ajout de classes pour chaque nouvelle tourelle et nouveaux monstres
- Discussion à propos des comportements sur les tourelles -> Récupérer tous les comportements qui existe puis les assigner aléatoirement à chaque tourelles. Cela permet la récupération/changement de comportement d’une tourelle en cours de partie
-Liste des tâches + Attribution

## Mardi 29 Mai :

Nous avons commencé à déterminer les différents concepts qui feront notre jeu (cf. Presentation jeu). Nous avons réfléchi à la manière de le rendre unique grâce aux automates, et à comment l’implémenter de façon permettant qu’on puisse facilement intégrer les automates des autres groupes.
