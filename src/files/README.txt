**EXPLICATIONS SUR LES FICHIERS FOURNIS ET ELEMENTS DE SOLUTION**

*distances.txt*
Ce fichier contient la liste des distances en km de 29 villes entre elles. Le fichier peut être vu comme un tableau de 29 lignes sur 30 colonnes. La première colonne contient les noms des villes. Chaque ligne contient la liste des distances d'une ville à toutes les autres, dans le même ordre. Par exemple, la distance donnée entre Biarritz et Angers est 518.

*membres_APLI.txt*
Ce fichier contient la liste des membres de l'APLI ainsi que la ville où réside le membre. Il s'agit toujours d'une des 29 villes de la liste, et ce n'est jamais Vélizy où seul le président habite.

*Scénarios*
Chaque scénario est une liste de transactions entre membres devant avoir lieu, sous la forme
                 membre1 -> membre2
Les scénarios sont garantis acircuitiques. En particulier, il ne peut pas y avoir de contraintes de villes du type "A avant B ET B avant A" qui obligeraient à passer deux fois dans une des villes. Notez que plus il y a de ventes, et moins il y a de solutions.



*scenario_0*
Ce scenario vous sert d'entraînement. Nous expliquons ici rapidement comment procéder. Le scénario contient uniquement les 3 lignes

    Sabelettenote -> Kokiyas
    Kokiyas -> Chenipan
    Sablaireaunote -> Kokiyas

En regardant dans le fichier des membres on observe que les villes où résident ces membres sont :

    Chenipan Grenoble
    Kokiyas Paris
    Sabelettenote Tours
    Sablaireaunote Lyon

Le scénario impose donc des contraintes

    Tours AVANT Paris
    Paris AVANT Grenoble
    Lyon AVANT Paris

On doit réaliser un tour qui part de Vélizy et y revient et passe par les villes Tours, Paris, Lyon, Grenoble en respectant les contraintes précédentes. 

Un ordonnancement sur les contraintes précédentes donne les deux solutions :
     1) Tours, Lyon, Paris, Grenoble
     2) Lyon, Tours, Paris, Grenoble
Les tours complets correspondants sont
    1) Vélizy, Tours, Lyon, Paris, Grenoble, Vélizy
    2) Vélizy, Lyon, Tours, Paris, Grenoble, Vélizy
qui donnent des longueurs totales:
    1) 215 + 432 + 470 + 557 + 592 = 2266 km
    2) 479 + 432 + 234 + 557 + 592 = 2294 km

La première solution est donc optimale.


*scenario 1.1*
22 solutions
meilleure solution 4558 km avec le tour 
    Velizy Amiens Angers Biarritz Bordeaux Brest Calais Cherbourg Clermond_Fd Grenoble Dijon Velizy

*scenario 1.2*
meilleure solution 5279 km

*scenario 2.1*
meilleure solution 14287 km

*scenario 2.2*
pas de données fournies
