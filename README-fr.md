[🇬🇧](/README.md "Anglais")

# 🛠 Tools Optimizer 🛠

## Description du problème

Les machines à commande numérique comportent un magasin d'outil qui permet de stocker tous les outils nécessaires à l'exécution d'une gamme. Un changeur d'outils permet d'interchanger l'outil en place sur la machine avec un outil du magasin. Il existe différents types de magasins et de changeurs d'outils. Nous retiendrons pour cette étude les magasins circulaires à mouvements bidirectionnels avec un changeur d'outils qui effectue des permutations d'outils.

![Diagram](/img/diagram.png?raw=true "Diagram")

Ce fonctionnement implique que le changement des outils peut se faire partiellement en temps masqué. En effet, durant la période pendant laquelle l'outil en place est utilisé, le magasin peut tourner pour amener l'outil suivant. Il est à noter qu'un outil ne reste pas forcément à la place qu'il avait initialement.

L'arrivée de machines d'usinage à grande vitesse et l'apparition de gammes d'usinage longues (en terme de nombre d'opérations) et comportant des opérations brèves ne permet plus d'effectuer les changements d'outils en temps masqué. Dès lors, le temps mis par la machine pour changer les outils devient un temps non productif et non négligeable par rapport au temps total d'usinage.

La position des outils dans le magasin influence le temps de changement d'outils. On peut donc se demander quel est la "bonne" position des outils dans le magasin qui permette de réduire les temps de changements d'outils.

## Objectif du projet

Ce projet consiste à créer un outil permettant, pour une gamme donnée, d'optimiser la position des outils dans le magasin d'une machine. Cet outil sera basé sur la méthode du recuit simulé.