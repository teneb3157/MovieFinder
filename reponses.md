# Réponses - Séance 1 (MovieFinder)

## Q1. Balise de l'activité de lancement dans le manifeste
Dans `AndroidManifest.xml`, la balise `<activity>` qui contient `<intent-filter>` avec `<action android:name="android.intent.action.MAIN" />` et `<category android:name="android.intent.category.LAUNCHER" />` indique l'écran lancé au démarrage de l'application.

## Q2. minSdk, targetSdk et compileSdk
- `minSdk` : version minimale d'Android supportée pour installer et exécuter l'application (ici API 24).
- `targetSdk` : version d'Android pour laquelle l'application a été testée et ciblée (comportements système attendus).
- `compileSdk` : version du SDK Android utilisée par Gradle pour compiler le code et les ressources.

## Q3. Dossier res/mipmap-* et plusieurs versions
Le dossier `res/mipmap-*` contient principalement les icônes de lancement de l'application (`ic_launcher`, etc.). Il existe plusieurs versions (`mdpi`, `hdpi`, `xhdpi`, `xxhdpi`, `xxxhdpi`) correspondant aux différentes densités d'écran afin d'afficher une icône nette sans flou ni gaspillage mémoire selon l'appareil.

## Observations (rotation et debug)
- Rotation de l'écran : l'activité est recréée et l'état non sauvegardé de l'interface est réinitialisé.
- Debug : le point d'arrêt sur `Log.d` permet de mettre l'exécution en pause, d'inspecter la variable `query` et d'avancer ligne par ligne avec `Step Over`.
