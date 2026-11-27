# Réponses TP MovieFinder — Séances 1 à 3

## Séance 1 — Atelier 1.4 (Structure du projet)

- **Q1. Balise indiquant l'écran lancé au démarrage :** Dans `AndroidManifest.xml`, c'est la balise `<activity>` qui contient un `<intent-filter>` avec `<action android:name="android.intent.action.MAIN" />` et `<category android:name="android.intent.category.LAUNCHER" />`.
- **Q2. `minSdk`, `targetSdk` et `compileSdk` :**
    - `minSdk` (API 24 dans ce projet) : version minimale d'Android requise pour installer et faire tourner l'application.
    - `targetSdk` : version d'Android pour laquelle l'application est ciblée et testée (comportements système attendus).
    - `compileSdk` : version du SDK Android utilisée par le compilateur pour construire l'application.
- **Q3. Dossier `res/mipmap-*` :** Il contient les icônes de lancement de l'application déclinées selon les densités d'écran (`mdpi`, `hdpi`, `xhdpi`, `xxhdpi`, `xxxhdpi`) afin d'afficher une icône nette et adaptée à chaque résolution d'appareil.

## Séance 2 — Rappel des livrables effectués

- Liste de films affichée avec `RecyclerView`, `MovieAdapter` et `ViewBinding` (`FakeData` avec 10 films).
- Filtrage local insensible à la casse avec message si aucun résultat (`msg_no_result`).
- Ressources alternatives configurées : passage à 2 colonnes en paysage (`values-land/integers.xml` avec `span_count = 2` vs `1` en portrait), traduction anglaise dans `strings.xml` et adaptation au mode sombre.
- Validation et tag Git `seance-2` poussé sur le dépôt distant.

## Séance 3 — Atelier 3.1 (Cycle de vie de `MainActivity`)

| Action | Callbacks observés |
| :--- | :--- |
| Lancement de l'application | `onCreate`, `onStart`, `onResume` |
| Bouton Home | `onPause`, `onStop` |
| Retour à l'application depuis les applications récentes | `onStart`, `onResume` |
| Bouton Retour | `onPause`, `onStop`, `onDestroy` |
| **Rotation de l'écran** | `onPause`, `onStop`, `onDestroy`, `onCreate`, `onStart`, `onResume` |

- **Observation importante :** À la rotation, l'activité est détruite puis recréée (`onDestroy` puis `onCreate`). L'utilisation de `MoviesViewModel` et `LiveData` permet de conserver la liste filtrée à travers la recréation de l'activité.
```[cite: 2, 3, 4, 8, 12, 13, 14, 15, 16]

### Ce qu'il faut faire juste après avoir collé ce texte

<Steps>
  <Step title="Enregistrer reponses.md à la racine du projet">
    Vérifie que le fichier s'appelle **`reponses.md`** à la racine du projet **MovieFinder**, colle le code ci-dessus, puis appuie sur **Ctrl + S**[cite: 3, 21].
    *Verification:* Le contenu complet apparaît dans l'éditeur sans indicateur de modification non enregistrée[cite: 3, 21].
  </Step>
  <Step title="Vérifier l'application sur l'émulateur">
    Ouvre l'écran des favoris (étoile en haut à droite) pour confirmer qu'**Inception** s'affiche bien, puis ferme et relance l'application pour vérifier qu'il reste présent[cite: 19, 20].
    *Verification:* **Inception** est toujours visible dans la liste des favoris après redémarrage de l'application[cite: 20, 21].
  </Step>
  <Step title="Committer et pousser le tag seance-3 dans le Terminal">
    Dans l'onglet **Terminal** d'Android Studio, exécute les commandes suivantes[cite: 7, 21] :
    ```bash
    git add .
    git commit -m "Seance 3: navigation, ViewModel, favoris"
    git push
    git tag seance-3
    git push origin seance-3
    ```
    *Verification:* `git status` indique qu'il n'y a plus de changements en attente et `git tag -l` affiche `seance-3`[cite: 21].
  </Step>
</Steps>

<ElicitationsGroup message="Une fois ces étapes faites :">
  <Elicitation label="Tag seance-3 poussé" query="J'ai collé le texte dans reponses.md et poussé le tag seance-3 sur GitHub."/>
  <Elicitation label="Erreur Git dans Terminal" query="J'ai une erreur dans le Terminal quand je lance les commandes git."/>
  <Elicitation label="Passer à la Séance 4" query="La Séance 3 est bien clôturée, on démarre la Séance 4."/>
</ElicitationsGroup>