# PROJET 1 : LANCEUR DE DÉS

## OBJECTIF DU PROJET

Créer une application Android permettant à l’utilisateur de lancer un ou plusieurs dés, avec des
options de personnalisation (nombre de faces, nombre de dés, tri des résultats, affichage de la
somme). L’application doit être entièrement développée en **Kotlin** avec **Jetpack Compose**.

L’accent est mis sur :

- La **conception d’une interface claire et intuitive**,
- La **gestion de l’état et des événements**,
- L’**adaptation à l’orientation de l’écran** (portrait/paysage),
- L’**utilisation d’images pour représenter les résultats** (remise 2).
- **Vous êtes libres de choisir les composants d’interface les plus appropriés**
    - l’important est que les options soient **accessibles, compréhensibles, et fonctionnelles**.
 
---

## ÉCHÉANCIER

### **Remise 1 — Avancement (UI statique)**

- **À remettre au plus tart le** : 28 septembre 2025 (avant minuit)
- **Méthode de remise** :
    - dépôt Git sur GitHub partagé avec l'utilisateur `profdenis`
    - nome du dépôt : `projet1_1234567`, en remplaçant `1234567` par votre numéro d'étudiant
    - **notes** : 
      - un seul dépôt Git pour le deux remises
      - le dernier commit sur la branche principale avant la limite va
        être utilisée pour la correction, à moins d'entente particulière (retard, extension ...)
- **Objectif** : Interface graphique complète, sans logique fonctionnelle.
    - Tous les éléments d’interface doivent être présents et bien organisés.
    - Les événements doivent contenir **au minimum un `Log.d()` ou `Log.i()`** : **pas de fonctions vides !**
    - Les valeurs affichées peuvent être fictives (ex. : résultats fixes, somme statique).

### **Remise 2 — Version finale (fonctionnelle, avec images et responsive)**

- **À remettre au plus tard le** : 26 octobre 2025 (avant minuit)
- **Méthode de remise** :
    - dépôt Git sur GitHub partagé avec l'utilisateur `profdenis`
    - **notes** : 
      - un seul dépôt Git pour le deux remises
      - le dernier commit sur la branche principale avant la limite va
        être utilisée pour la correction, à moins d'entente particulière (retard, extension ...)

- **Objectif** : Application entièrement fonctionnelle, avec :
    - Génération aléatoire des dés
    - Affichage d’**images correspondant aux résultats**
    - Fonctionnalités de tri et calcul
    - **Support du mode portrait ET paysage**

---

## EXIGENCES D’INTERFACE (REMISE 1 - UI STATIQUE)

L’interface doit permettre à l’utilisateur de configurer et visualiser les éléments suivants. **Le choix des composants
est libre** (ex. : `Button`, `RadioButton`, `Switch`, `TextField`, `Slider`, `DropdownMenu`, `SegmentedButton`, etc.) :

1. **Titre de l’application** (clair et visible — ex. : “Lanceur de Dés”)

2. **Bouton principal “Lancer les dés”**
   -Doit être bien visible, centré ou mis en évidence.

3. **Configuration du nombre de dés**
    - Doit permettre de choisir entre 1 et 6 dés (minimum 1).
    - Exemples possibles : `Slider`, `TextField` numérique, groupe de boutons (1, 2, 3...), menu.
    - 

4. **Configuration du nombre de faces par dé**
    - Doit permettre de choisir au minimum : **d4, d6, d8, d10, d12, d20**.
    - Exemples possibles : Boutons radio, menu déroulant, grille de boutons cliquables.

5. **Option de tri des résultats**
    - Doit permettre de choisir entre :
        - Aucun tri (ordre d’apparition)
        - Tri croissant
        - Tri décroissant
    - Exemples possibles : Boutons radio, `Switch`, menu.

6. **Zone d’affichage des résultats (statique pour la remise 1)**
    - Doit afficher :
        - Les valeurs des dés (ex. : “3, 5, 2”)
        - La somme totale (ex. : “Somme : 10”)
    - Peut être sous forme de texte seul pour la remise 1.

7. **Design global**
    - Utilisation de `Column`, `Row`, `Spacer`, `Card`, `Surface`, etc. pour une interface propre et
      hiérarchisée.
    - Espacement, lisibilité, contraste.

---

## FONCTIONNALITÉS ATTENDUES (REMISE 2 — VERSION FINALE)

1. **Génération aléatoire des dés**
    - Au clic sur “Lancer”, générer un résultat aléatoire pour chaque dé selon le nombre de faces sélectionné.
    - Utiliser `Random.nextInt(1, nombreFaces + 1)`.

2. **Affichage dynamique avec images**
    - Remplacer ou accompagner les chiffres textuels par des **images correspondant à la valeur du dé**.
    - Ex. : valeur “4” → afficher `dice_4.png`.
    - Stocker les images dans `res/drawable/`.
    - Utiliser `Image(painter = painterResource(id = R.drawable.dice_X))`.
    - **Alternative** : utiliser une image de fonds, et superposer un nombre sur cette image.

3. **Tri dynamique des résultats**
    - Appliquer le tri sélectionné après chaque lancer :
        - Croissant : `list.sorted()`
        - Décroissant : `list.sortedDescending()`
        - Aucun : garder l’ordre de génération

4. **Calcul et affichage de la somme**
    - Mettre à jour la somme après chaque lancer.
    - L’afficher clairement (ex. : “Somme : 15”).

5. **Responsive design — Portrait & Paysage**
    - L’application doit s’adapter visuellement et fonctionnellement aux deux orientations.
    - En paysage : on peut réorganiser les dés en ligne, déplacer les contrôles sur le côté, ou ajuster les tailles.
        - Utiliser `LocalConfiguration.current.orientation`, `BoxWithConstraints`, ou des layouts
          conditionnels.

6. **Gestion de l’état**
    - Utiliser `remember { mutableStateOf(...) }` pour stocker les résultats, options, etc.
    - L’interface doit se mettre à jour automatiquement.

7. **Validation minimale**
    - Empêcher un nombre de dés inférieur à 1.
    - S’assurer que le nombre de faces est valide (≥ 4).

---

## CONCEPTS À DÉMONTRER — CRITÈRES D’ÉVALUATION

| Critère                                                       | Remise 1 (UI) | Remise 2 (Fonctionnelle) |
|---------------------------------------------------------------|---------------|--------------------------|
| Structure UI Compose (layouts, espacement, hiérarchie)        | ✅             | ✅                        |
| Choix de composants adaptés et justifiés (liberté contrôlée)  | ✅             | ✅                        |
| Présence de logs dans les événements (pas de fonctions vides) | ✅             | ✅                        |
| Génération aléatoire et calculs                               | ❌             | ✅                        |
| Affichage d’images selon les résultats                        | ❌             | ✅                        |
| Tri dynamique des résultats                                   | ❌             | ✅                        |
| Responsive : portrait ET paysage                              | ❌             | ✅                        |
| Mise à jour réactive de l’UI (état)                           | ❌             | ✅                        |
| Design clair, lisible, ergonomique                            | ✅             | ✅                        |

