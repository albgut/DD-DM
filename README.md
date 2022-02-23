# DD-DM

## MLlib sur Spark

enregistrer le fichier **mnist8m.bz2** de https://www.csie.ntu.edu.tw/~cjlin/libsvmtools/datasets/multiclass.html#mnist8m dans le répertoire data du projet. Renommez le **big_data**.

## Naive bayes

## note du prof

Attention à l'operation collect :
Elle permet de récupérer des données dans le cluster sur la machiine maître en array.
Mais une fois réalisé le parrallélisme est terminé.
conseil : rester le plus longtemps possible sur la rdd et faire le collect à la fin
