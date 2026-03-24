# Jumpout Plugin for IntelliJ IDEA
Jump out of brackets, quotes, and other closing characters with **Tab** — just like the VS Code Tabout extension.

## What does it do?
When your cursor is right before a closing character, Tab jumps over it:
- `)` `}` `]` `"` `'` `` ` `` `>`

If there's no closing character, Tab works normally.

## Building
### Requirements
- JDK 17 or higher → https://adoptium.net/
- Gradle (automatically downloaded via the wrapper)

### Steps
```bash
# 1. Navigate to the project folder
cd jumpout

# 2. Build the plugin
./gradlew buildPlugin

# On Windows:
gradlew.bat buildPlugin
```

The `.jar` will be located at:
```
build/libs/jumpout-1.0.0.jar
```

## Installing in IntelliJ IDEA
1. Open IntelliJ IDEA
2. Go to **Settings** → **Plugins**
3. Click the gear icon ⚙️ → **Install Plugin from Disk...**
4. Select the `.jar` from `build/libs/`
5. Restart IntelliJ

## Changing the keybinding
The plugin uses **Tab** by default. Want a different key?

Go to **Settings** → **Keymap** → search for "Jumpout" → change the shortcut.


