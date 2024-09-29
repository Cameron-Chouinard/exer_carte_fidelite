[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/evLSEpf-)
# Exercice 2 - Application Carte Fidélité - 5%
## Consignes

Vous devez créer une application Android (Kotlin) qui simulera une application de carte de fidélité.

Vous utiliserez Room pour faire la persistance des données.

Vous devez également bloquer l’orientation au mode « portrait » seulement.

Votre application doit avoir une apparence soignée et « professionnelle ».


### Liste
À l’ouverture de l’application, vous afficherez la liste des cartes de fidélité.

Chaque cellule de la liste doit avoir l’apparence d’une carte de fidélité.

Chaque carte doit afficher :
  - Le nom du commerce.
  - Le numéro de la carte.
  - Une icône associée au type de commerce (restaurant, divertissement, épicerie ou autre).
  - La couleur de fond de la carte doit être celle choisie.

Finalement, dans la barre de l’application, vous devez mettre un bouton d’ajout de carte (voir section
Ajout).


### Ajout
Le bouton dans la barre de l’application permettra d’ajouter une nouvelle carte. Toutefois, l’ajout se
fera directement et sans interface. Vous devez donc ajouter une carte avec des données générées de
façon aléatoires. Vous pourrez trouver des Fakers pour Kotlin sur internet. La carte doit être
sauvegardée en base de données et la liste doit se rafraîchir dès l’ajout.

Une carte devra avoir les informations suivantes :
  - Le nom du commerce.
  - Le numéro de la carte.
  - Un type de commerce (restaurant, divertissement, épicerie ou autre).
  - Une couleur.

### Modification et détails
À la sélection d’une carte, vous devez faire afficher les détails et il sera possible de modifier
l’ensemble des données de la carte. Les modifications doivent être sauvegardées en base de données
et la liste doit être rafraîchie lors du retour selon les techniques vues en classe.

Vous devez également valider les entrées et bloquer la navigation si une donnée est invalide. Vous
trouverez une piste de solution ici : 
https://developer.android.com/guide/navigation/navigationcustom-back

De plus, vous devrez faire afficher un code barre qui représente le numéro de la carte. Vous devez
utiliser le standard Code 39.

![Exemple de code barre](exemple/barcode.png)

Pour générer le code barre, vous pouvez choisir la technique qui vous convient. N’oubliez pas que les
changements des données doivent également mettre à jour le code barre.

Finalement, un bouton « Supprimer » permettra d’effacer la carte. On retournera à la liste directement
après la suppression.


**Pensez à bien découper votre code afin de respecter les principes orientés objet et à utiliser les
éléments Kotlin vus en classe.  

Attention au plagiat et à la présentation de votre code. Ne pas oublier les commentaires et le Lint.  
Vous êtes autorisé d'utiliser le code partagé en classe.**  

## Remise
- Le travail doit se faire **en équipe de 2**, sauf sur approbation préalable du professeur.  
- La remise doit être sur Léa ou sur Github Classroom l’endroit approprié.  
- La remise doit se faire avant le 26 septembre 2024 à 23:59.  


#### Auteurs
Gabriel T. St-Hilaire  
Ajusté par Vincent Beauregard

