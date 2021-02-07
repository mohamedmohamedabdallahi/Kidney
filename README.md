
# Documentation rapide

### La Classe DirectDon

Implémentation de l'algorithme 1 (direct donation).

Les méthodes :
- `match` renvoie la liste de notre matching.
- `printMatching` affiche la liste de notre matching.

On implemente le test de cet algorithme dans la class `Test1`

### La classe GreedyMatching 

Implementation de l'algorithm 2 (greedy matching)

- `match` prend en argument la liste d'adjacence et renvoie la liste de notre matching.
- `printMatching` affiche la liste de notre matching.

On implemente le test de cet algorithme dans la class `Test2`

### La Classe KidExchange

Implémentation de l'algorthm 3 (Cycles and Chains Matching Algorithm).

Les méthodes :
- `match` prend en argument :
    -`preferences` (la liste des listes de prefrece des patients).
    -`rule` qui contient la chaine de character `"A"` (resp `"B"`) pour la regle A (resp B).
    Et renvoie la liste de notre matching.
- `printMatching` affiche la liste de notre matching.
- `getCycleOrChainA` reutrn un cycle s'il y'en a, si non  return la chaine w choisit par la regle de selection `A`.
- `getCycleOrChainA` return un cycle s'il y'en a, si non  return la chaine w choisit par la regle de selection `B`.
- `compairPiriority` prend en argument deux chaine w return `true` si la premiere chaine est prioritaire au sens de règle `A` sinon return `false`.

On implemente le test de cet algorithme dans la classe `Test4`

### La Classe InfeasiblePath 

Implémentation de l'agorithm 4 (Minimal infeasible path)

Les méthodes :
- `pathes`return la list des path de longeur K+1
- `printPaths`affiche les chemins calculés 

On implemente le test de cet algorithme dans la classe `Test9`

### La Class BranchAndBound 

Implémentation de l'agorithm 5 (Branch-and-bound)

Les méthodes : 
- `runBranchAndBound` prend en argumant un obje de la class MatchingContraints et return la matrice d'affectation 
- `RecursivBranchAndBound` fais la procedure recursive pour trouver la solution optimal
- `printTheMatching`prend en argument la matrice d'affectation (solution de brancheAndBound) pour afficher le matching

On utilise la bibliotheque CSS qui implement la methode simplex

On implemente le test de cet algorithme dans la classe `Test11`

### La Class MatchingContraints

Implémentation des contraintes de notre probleme `LP`

La méthode : 
-  `getContraits` a partir des six conditions qu'on a (voir question 10), return l'objet contraint

### La Classe Sumilation

Implémentation de programe de simulation.

Les méthodes :
- `blood` renvoie la liste des types de sang pour les patients ou donneurs, prend comme entrer int n qui est le nombre de patient a generer.
-`nbrtransplatation` renvoie le nombre de transplatation d'un algorithme, prend en entrer Liste de matching et le nombre de kidney de waitingliste compatible avec des patient dans le waitinglist .
- `printSumilation` affiche les resultats de la sumilation de notre Sumilation.


On implemente le test de cet algorithme dans la class `Test13`





