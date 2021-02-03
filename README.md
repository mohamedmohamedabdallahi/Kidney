
# Documentation rapide

### La Classe DirectDon

Implémentation de l'algorithme 1 (direct donation).

Les méthodes :
- `match` renvoie la liste de notre matching.
- `printMatching` affiche la liste de notre matching.

On implemente le test de cet algorithme dans la class `Test1`

### La Classe KidExchange

Implémentation de l'algorthm 3 (Cycles and Chains Matching Algorithm).

Les méthodes :
- `match` prend en argument :
    -`preferences` (la liste des listes de prefrece des patients).
    -`rule` qui contient soit le rule A ou B.
    et renvoie la liste de notre matching.
- `printMatching` affiche la liste de notre matching.
- `getCycleOrChainA` reutrn un cycle s'il y'en a, si non  return la chaine w choisit par la regle de selection `A`.
- `getCycleOrChainA` return un cycle s'il y'en a, si non  return la chaine w choisit par la regle de selection `B`.
- `compairPiriority` prend en argument deux chaine w return `true` si la premiere chaine est prioritaire au sens de règle `A` sinon return `false`.

On implemente le test de cet algorithme dans la classe `Test4`
