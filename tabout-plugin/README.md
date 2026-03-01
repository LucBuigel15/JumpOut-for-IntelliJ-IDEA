# Tabout Plugin for IntelliJ IDEA

Spring met **Tab** uit haakjes, quotes en andere sluitende tekens — net als de VS Code Tabout extensie.

## Wat doet het?

Als je cursor vlak voor een sluitend teken staat, springt Tab erover heen:
- `)` `)` `}` `]` `"` `'` `` ` `` `>`

Als er geen sluitend teken staat, werkt Tab gewoon normaal.

## Bouwen

### Vereisten
- JDK 17 of hoger → https://adoptium.net/
- Gradle (wordt automatisch gedownload via de wrapper)

### Stappen

```bash
# 1. Ga naar de projectmap
cd tabout-plugin

# 2. Bouw de plugin
./gradlew buildPlugin

# Op Windows:
gradlew.bat buildPlugin
```

De `.jar` staat daarna in:
```
build/libs/tabout-plugin-1.0.0.jar
```

## Installeren in IntelliJ IDEA

1. Open IntelliJ IDEA
2. Ga naar **Settings** → **Plugins**
3. Klik op het tandwiel-icoontje ⚙️ → **Install Plugin from Disk...**
4. Selecteer de `.jar` uit `build/libs/`
5. Herstart IntelliJ

## Keybinding aanpassen

De plugin gebruikt standaard **Tab**. Wil je een andere toets?

Ga naar **Settings** → **Keymap** → zoek op "Tabout" → pas de snelkoppeling aan.
