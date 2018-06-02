# Documentation (v0 - 2 juin 2018)

![UML Diagram](photo/uml.png)

```
projet
|-game.framework
|-game.sample
    |- edu.ricm3.game.tomatower
    |- maps
        |- defis  
        |- principale  
        |- magasin  
    |- sprites
```

## [TODO] Prochaines étapes
- Gestion du hit des entité, principalement définis pour les Living (classe(s) Weapon, appelle de la fonction hit des entités) ;
- Implémentation des défis (création maps, intéraction, etc) ;
- Implémentation du magasin (argent dans le jeu, possibilité d'acheter des tourelles, améliorations) ;
- Implémentation du comportement des entité (Tower, Mobs) (automates, fonctions step() des entités) + spawn des ennemis ;
- Animation des entités.


## MVC - model, view, controleur
### Model
**Gère le comportement des entités et la gestion globale du jeu.**

#### Remarque
Si vous avez besoin de recupérer un itérateur des mobs, des obstacles ou des tourelles, les fonctions sont implémentés mais commentées, il faudra donc :
- Décommenter les 3 variables correspondant au collestions de ces objects dans la classe Model ;
- Décommenter les 3 fonctions correspondant au getters des itérateurs (+ 1 fonctions récupérant un itérateur de toutes ces entités : Mobs, Inert, Tower) ;
- Décommenter dans les constructeurs de ces entités (Mobs, Tower, Inert) la ligne permettant d'ajouter à ces collections l'instance créer (ou l'ajouter si elle n'y ai pas).

### View
**Affiche les éléments à affiché sur la zone de jeu.**  
Appelle la fonctions paint de chaque cellule (qui appelle la fonction paint de chaque entités présente sur cette cellule).  
Appelle la fonction paint du crystal.
TODO: Expliqué le fonctionnement pour les différentes maps

### Controleur
**Gère les intéractions de l'utilisateur (clavier, souris)**  


## Les maps - zone de jeu principale, défis, magasin
Le jeu est constitué de trois map, seulement une est visible à la fois.
Les constitutions des maps (c'est à dire l'état initial des maps) sont définis dans les dossiers respectifs situés dans le dossier map.
Au début du jeu toutes les maps necessaires sont chargées, c'est à dire :
- la map de la zone de jeu principale ;
- toutes les maps de défis (la lecture des fichiers étant longue, il est préférable de toutes les chargés au début) ;
- la map du magasin.

**Les map sont des instances de la classe Map.**  
### Map
Une instance de Map est composées de :
- Une collection de cellule (instances de la classe Cell), un itérateur de toutes les cellules est diponible (getCellsIterator()) ;
- La dimension d'une cellule (**l'unité de mesure du jeu**) ;
- Le nombre de cellules vertical et horizontal ;
- La fonction initMap(String path) initialise la map à partir d'un fichier. La syntaxe supportée pour définir des maps est disponible dans la sous partie ci-dessous ;
- La fonction freeCell(Cell c) retourne vrai si la cellule contient aucune entité, faux sinon ;
- La fonction getEntityCell(Cell c) retourne la première entité présente sur la cellule (*à discuter du comportement de cette fonction*) ;
- La fonction getCell(int x, int y) retourne la cellule correspondant à la colonne x et la ligne y.

### Cell
Une cellule est composé de :
- Sa position (colonne, ligne) ;
- Les entités qui sont présente sur la cellule (ArrayList<Entity>), un itérateur de toutes les entités est disponible (getEntitiesIterator()) ;
- Des fonctions pour ajouter, enlever, recupérer des entités sont disponibles ;
- La fonction isFree() indique si la cellule contient des entités ou non ;
- La fonction paint() appelle la fonction paint() de chaque entité présente sur la cellule ;
- La fonction step() appelle la fonction step de chaque entité présente sur la cellule.

La map principale est toujours *actualisé* (fonction step() du model, cf: MVC.). Tandis que les maps défis et magazin sont actualisé seulement si ce sont la map courante (Cf : Model -> `current_map`).  
Seule la map courrante est affichée (méthode paint de View).

### Format des maps et syntaxe supportée (création de map)
Les maps sont définis par des fichier `.txt`. Chaque cellule correspondant à un identifiant :
- `N` : nothing ;
- Portals :
  - `Ps` : Portal vers le magazin (store);
  - `Pc` : Portal vers le défis (challenge) ;
  - `Pg` : Portal vers la zone principale (game) ;
- Obstacles :
  - `Os` : Pierre (stone) ;
  - `Ol` : Lac ; **Pas encore géré**
- `C` : Crystal (doit être au nombre de 4, situés en forme carré) ;
- `E` : Spawn ennemis (doit être au nombre de 4, situés en forme carré). **Pas encore géré**

**La fichier de la map principale doit obligatoirement contenir le spawn ennemis de 4 cases, le spawn du joueur, et le crystal de 4 cases.**  
**Le fichier de la map défis et du magazin doit obligatoirement contenir le spawn du joueur (après avoir pris le portail de la zone principale), et un portail vers la zone principale.**  

#### Exemple
```
E E N P
E E N N
N N C C
Os N C C
```
Ce fichier génère une map avec le spawn ennemis situé en haut à gauche, la spawn du joueur en haut à droite, un cailloux en bas à gauche et le crystal en bas à droite.  


## Les entités
### Attributs
- La cellule ou est est (la cellule peut être `null`, par exemple les tower dans le sac du joueur n'ont pas de cellule associée) ;
- Un boolean movement, vrai si l'entité peut se déplacer, faux sinon ;
- Un décimal scale définis entre [0, 1], correspondant au ratio de la taille de l'image par rapport à l'unité de référence, la taille d'une cellule ;
- Le sprite de l'image (BufferedImage[]) ;
- Le type de l'entité (Team, Ennemy, Obstacle, etc) ;
- Un boolean visible, true si l'entité doit être affiché, false sinon ;
### Fonctions
- La fonction paint affiche l'image (si elle est visible) ;
- Une fonction d'ajout de l'entité sur une cellule (addEntityOnCell(Cell c)) ;
- Une fonction de suppression de l'entité d'une cellule (removeEntityFromCell()) ;
- Les fonctions correspondant aux actions possibles (move, turn, pick, store, getbagEntity, throwAction, jump, power).



## Les sprites







end
