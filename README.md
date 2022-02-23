# DD-DM

## MLlib sur Spark


## note du prof

Attention à l'operation collect :
Elle permet de récupérer des données dans le cluster sur la machiine maître en array.
Mais une fois réalisé le parrallélisme est terminé.
conseil : rester le plus longtemps possible sur la rdd et faire le collect à la fin
