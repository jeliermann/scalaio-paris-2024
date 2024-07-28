# Cardinalité et types algébriques, comptez pour tout tester

*Préparé pour le [scalaio 2024](https://scala.io/?conference=paris-2024)*

Redécouvrons les structures bien connues au travers de leurs mécaniques fondamentales !
Au menu :

- Equivalence de nos enums et case class avec les opérations élémentaires (somme et produits)
- Compter nos implémentations pour un TDD exhaustif
- Cardinalité infini, comment la contourner efficacement ?

## Description

Modéliser notre donnée au travers de types algébriques ?  
Derrière ce nom obscur (non, les mathématiques ne font pas peur) se cachent nos enum et case class familières aux
intérêts bien connus (fonction pure, pattern matching & co).  
Mais pourquoi ça fonctionne ?  
Démystifions la cardinalité, (re)découvrons l'addition et la multiplication depuis longtemps oubliées, et comment
l'arithmétique élémentaire permet de préparer du TDD solide et exhaustif.  
Et parce que la théorie c'est bien, mais qu'en pratique un champ 'item description' a trop de valeurs pour pouvoir les
compter sur les doigts, voyons ensemble comment compter concrètement des ensembles infinis… Ou plutôt des techniques
secrètes pour faire semblant de les compter correctement !

## Exécution

Deux packages `usecase.initial` et `usecase.adapted` décrivent respectivement un cas d'usage d'implémentation de
fonction dont le nombre d'entrée possible est fini, puis le même cas, adapté pour rendre ce nombre de paramètres
infini.  
Deux tests associés peuvent être trouvés dans le répertoire de test.
