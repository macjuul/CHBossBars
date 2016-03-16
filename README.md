# CHBossBars
Adds support for the 1.9 Bukkit BossBar API

Latest release: [download v1.0.0](https://github.com/macjuul/CHBossBars/releases/tag/v1.0.0.1)

## Functions:
**create_bossbar(String barID, String text, [Array options])** - Create a new bossbar with the given ID and text. An optional options array can be included to directly change some of the bar's settings.

**delete_bossbar(String barID)** - Deleted the given bossbar

**set_pbossbar([String player], String barID)** - Sets the bar active for the given player. This function might sound a bit misleading, since it's more like you "add" the bar to the player. A player can actually have up to about 4 bars at once.

**remove_pbossbar[String player], String barID)** - Removed a bossbar from the player.

**set_bossbar_options(String barId, Array options | String barId, String option, String value)** - Update one or multiple bar settings. the settings include COLOR, STYLE, TITLE and PROGRESS

**get_bossbar_options(String barId)** - Get the options of the given bossbar

**set_bossbar_visibility(String barId, Boolean visible)** - Sets if the bar is visibile or not, unlike deleting a bar, it will simply no longer render when hidden.

**get_bossbar_visibility(String barId)** - Returns true if the bossbar is visibile, otherwise false

**get_bossbars()** - Returns an array of exiting bossbars
