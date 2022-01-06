# Carpet RedCraft Addons

[![License](https://img.shields.io/github/license/MultiCoreNetwork/carpet-redcraft-addons.svg)](http://www.gnu.org/licenses/lgpl-3.0.html)
[![Issues](https://img.shields.io/github/issues/MultiCoreNetwork/carpet-redcraft-addons.svg)](https://github.com/MultiCoreNetwork/carpet-redcraft-addons/issues)
[![MC Versions](http://cf.way2muchnoise.eu/versions/For%20MC_carpet-redcraft-addons_all.svg)](https://www.curseforge.com/minecraft/mc-mods/carpet-redcraft-addons)
[![CurseForge](http://cf.way2muchnoise.eu/full_carpet-redcraft-addons_downloads.svg)](https://www.curseforge.com/minecraft/mc-mods/carpet-redcraft-addons)

[>>> ENGLISH <<<](https://github.com/MultiCoreNetwork/carpet-redcraft-addons/blob/master/README.md)

Un'estensione della [Carpet mod](https://github.com/gnembon/fabric-carpet) (fabric-carpet), una collezione di interessanti funzionalità e strumenti in stile Carpet Mod

Da usare con la Carpet Mod nella stessa versione di Minecraft. È suggerito usare versioni aggiornate della Carpet Mod non appena possibile

## Regole
- [anvilRepairCostLimit](#anvilRepairCostLimit)
- [armorStandEditor](#armorStandEditor)
- [betterItemFrames](#betterItemFrames)
- [craftableBricks](#craftableBricks)
- [colorableShulkers](#colorableShulkers)
- [commandLocatePlayer](#commandLocatePlayer)
- [commandLookme](#commandLookme)
- [commandPlayerme](#commandPlayerme)
- [commandSkull](#commandSkull)
- [commandStats](#commandStats)
- [commandSwapitem](#commandSwapitem)
- [commandTimebar](#commandTimebar)
- [commandWaystone](#commandWaystone)
- [craftableCobwebs](#craftableCobwebs)
- [craftableColoredBlocks](#craftableColoredBlocks)
- [craftableCorals](#craftableCorals)
- [craftableDeadBushes](#craftableDeadBushes)
- [craftableDeepslateOres](#craftableDeepslateOres)
- [craftableElytra](#craftableElytra)
- [craftableIce](#craftableIce)
- [craftableLargeFern](#craftableLargeFern)
- [craftableNetherWarts](#craftableNetherWarts)
- [craftablePackedIce](#craftablePackedIce)
- [craftablePlayerHead](#craftablePlayerHead)
- [craftableQuartz](#craftableQuartz)
- [craftableRedSand](#craftableRedSand)
- [craftableSand](#craftableSand)
- [craftableSculkSensor](#craftableSculkSensor)
- [craftableShulkerShells](#craftableShulkerShells)
- [craftableTallGrass](#craftableTallGrass)
- [craftableTuffAndCalcite](#craftableTuffAndCalcite)
- [crumbleConcrete](#crumbleConcrete)
- [disableOPsCommandFeedback](#disableOPsCommandFeedback)
- [dragonEggRespawns](#dragonEggRespawns)
- [dynamicLight](#dynamicLight)
- [endermanNoGrief](#endermanNoGrief)
- [fastRedstoneCrafting](#fastRedstoneCrafting)
- [floatingLadders](#floatingLadders)
- [graves](#graves)
- [horseStats](#horseStats)
- [huskDropsSand](#huskDropsSand)
- [illusionersSpawnInRaids](#illusionersSpawnInRaids)
- [invisibleItemframe](#invisibleItemframe)
- [lastDeathCompass](#lastDeathCompass)
- [lavaSponges](#lavaSponges)
- [moreWanderingTrades](#moreWanderingTrades)
- [pillagerLeash](#pillagerLeash)
- [placeablePlants](#placeablePlants)
- [protectionStacking](#protectionStacking)
- [prunedPlants](#prunedPlants)
- [quartzCraftingCompatibility](#quartzCraftingCompatibility)
- [redcraft](#redcraft)
- [redSandstoneCraftingCompatibility](#redSandstoneCraftingCompatibility)
- [renewableSporeblossom](#renewableSporeblossom)
- [repairableAnvil](#repairableAnvil)
- [reviveCoral](#reviveCoral)
- [ropes](#ropes)
- [sandstoneCraftingCompatibility](#sandstoneCraftingCompatibility)
- [silkyBlockstates](#silkyBlockstates)
- [sitAnywhere](#sitAnywhere)
- [slabToBlockCrafing](#slabToBlockCrafing)
- [specialNameTags](#specialNameTags)
- [spongesqueeze](#spongesqueeze)
- [stonecutterQuartz](#stonecutterQuartz)
- [stonecutterRedSandstone](#stonecutterRedSandstone)
- [stonecutterSandstone](#stonecutterSandstone)
- [stonecutterStone](#stonecutterStone)
- [stonecutterWood](#stonecutterWood)
- [treecapitator](#treecapitator)
- [villagerLeash](#villagerLeash)
- [zombieHorseSpawning](#zombieHorseSpawning)

## Regole da altre estensioni:
### [Gnembon's Carpet Extra](https://github.com/gnembon/carpet-extra/)
- [accurateBlockPlacement](#accurateBlockPlacement)
- [blockStateSyncing](#blockStateSyncing)
### [whoImT's Carpet Addons](https://github.com/whoImT/carpet-addons/)
- [carefulBreak](#carefulBreak)

--- 
## anvilRepairCostLimit

Permette di scegliere il massimo costo di riparazione all'interno dell'incudine

- Tipo: `int`
- Valore predefinito: `40`
- Opzioni suggerite: `40`, `1024`, `24791`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`
- Additional notes:
    - Devi scegliere valori da 1 a 32767

## armorStandEditor

**[BETA]**<br/>
Quandi si picchia un supporto per armature con una `zappa` entrerà in modalità modifica.<br/>
Quando un supporto per armatura è in modalità modifica, tu potrai:
- Premere tasto destro per mettergli l'oggetto impugnato in testa.
- Premere shift tasto destro per muovere il supporto per armatura in giro.<br/>
  Rilasciando il tasto shift, si applicheranno le modifiche.<br/>
  Cambiando slot dell'inventario, le modifiche verranno annullate.
- Premere tasto destro con una `zappa` per cambiare modalità di modifica:
  `PITCH`, `YAW`, `ROLL`, `TEST`
- Premere shift tasto destro con una `zappa` per muovere la parte del supporto per armatura che stai inquadrando.<br/>
  Rilasciando il tasto shift, si applicheranno le modifiche.<br/>
  Cambiando slot dell'inventario, le modifiche verranno annullate.
- Premere shift tasto destro con due `bastoni` per dare le braccia al supporto per armature.
- Premere shift tasto destro con una `lastra di pietra levigata` per togliere al supporto per armature la propria base.
- Premere shift tasto destro con una `piuma` per togliere la gravità al supporto per armature.
- Premere shift tasto destro con una `membrana di phantom` per rendere invisibile il supporto per armature.
- Premere shift tasto destro con una `occhio dell'end` per rendere di nuovo visibile il supporto per armature.
- Premere shift tasto destro con una `occhio dell'end` per rendere di nuovo visibile il supporto per armature.
- Premere shift tasto destro con una `fuoco da campeggio` per creare nuvole di fumo nella posizione del supporto per armature.
- Premere shift tasto destro con una `bottone di pietra` o un `bottone di pietra scura` per creare rendere piccolo il supporto per armature.
- Premere shift tasto destro con una `pedana a pressione di pietra` o un `pedana a pressione di pietra scura` per rendere nuovamente grande il supporto per armature.
- Premere shift tasto destro con una `polvere di vampo` per creare fiamme nella posizione del supporto per armature.


- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`


## betterItemFrames

Usano una membrana di phantom o un pannello di vetro su una cornice per oggetti, la renderà invisibile o fissa

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## colorableShulkers

Permette di colorare le Shulker

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `COMMAND`

## commandLocatePlayer

Permette di localizzare i giocatori

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `COMMAND`

## commandLookme

Aggiunge il comando /lookme per ruotare le entità

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `COMMAND`

## commandPlayerme

Permette ai giocatori non operatori di eseguire /player su sé stessi

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `COMMAND`

## commandSkull

Permette di avere accesso al comando /skull

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `COMMAND`

## commandStats

Permette di avere accesso al comando /stats

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `COMMAND`

## commandSwapitem

Permette di avere accesso al comando /swapitem

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `COMMAND`

## commandTimebar

Permette di avere accesso al comando /timebar

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `COMMAND`

## commandWaystone

Permette di avere accesso al comando /waystone

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `COMMAND`

## craftableBricks

Aggiunge retrocrafting del prismarino, dei mattoni e mattoni del Nether

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## craftableCobwebs

Permette di craftare le ragnatele

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## craftableColoredBlocks

Permette di craftare blocchi colorati partendo dalle altre varianti

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## craftableCorals

Permette di craftare i blocchi di corallo partendo da coralli o gorgonie

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## craftableDeadBushes

Permette di far esiccare gli arboscelli nella fornace

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## craftableDeepslateOres

Permette di craftare gli ore in deepslate nel banco da forgiatura

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## craftableElytra

Permette di craftare le elytra

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## craftableIce

Permette di craftare ghiaccio partendo dal ghiccio compatto

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## craftableLargeFern

Permette di craftare le felci alte

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## craftableNetherWarts

Permette di craftare verruche del nether partendo dal relativo blocco

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## craftablePackedIce

Permette di craftare il ghiaccio compatto partendo dalla versione blu

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## craftablePlayerHead

Permette di craftare le teste di giocatore partendo da una qualsiasi testa

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## craftableQuartz

Permette di craftare il quarzo partendo da una qualsiasi variante di blocco

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## craftableRedSand

Permette di craftare la sabbia rossa partendo dall'arenaria rossa

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## craftableSand

Permette di craftare la sabbia partendo dall'arenaria

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## craftableSculkSensor

Permette di craftare Sculk Sensor con endeperal e osservatori

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## craftableShulkerShells

Permette di craftare i gusci di shulker partendo da quelli di tartaruga e un fiore di chorus scoppiato

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## craftableTallGrass

Permette di craftare l'erba alta

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## craftableTuffAndCalcite

Permette di craftare il tufo e la calcite

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## crumbleConcrete

Permette di trasformare il calcestruzzo in polvere di calcestruzzo riempiendo una bottiglia d'acqua

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## disableOPsCommandFeedback

Disabilita il feedback dei coomandi per i giocatori OP di livello 1

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `FEATURE`, `REDCRAFT`

## dragonEggRespawns

Ogni drago dell'end rilascerà un uovo alla propria morte

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## dynamicLight

Impugnando blocchi luminosi, illuminerà intorno al giocatore

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## endermanNoGrief

Riduce il numero di blocchi che gli enderman possono raccogliere

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `DATAPACK`

## fastRedstoneCrafting

Aggiunge crafting alternativi di dispenser e repeater

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## floatingLadders

Permette di piazzare una scala a pioli fluttuante sotto ad una già piazzata

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## ghastNoGrief

I ghast non danneggeranno più il mondo

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## graves

Crea una tomba personale alla morte del giocatore

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## horseStats

Accovacciandosi e premendo il tasto destro su un cavallo/mulo/asino verranno riportatate in chat le statistiche dell'animale

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## huskDropsSand

Gli zombi secchi rilasciano sabbia alla loro morte

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `DATAPACK`

## illusionersSpawnInRaids

Permette agli illusionisti di generarsi all'interno delle invasioni (Necessita un riavvio)

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `DATAPACK`

## lastDeathCompass

Alla morte verrà consegnata una bussola che punta nel luogo di morte

Sarà possibile riottenere tale bussola premendo con il tasto destro una bussola mentre ci si accovaccia

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## lavaSponges

Le spugne funzioneranno anche con la lava, ma bruceranno.

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## moreWanderingTrades

Aggiunge nuovi scambi al mercante viandante

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## pillagerLeash

Permette di legare i pillager con le mani vuote

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## placeablePlants

Permette di piantare le piante su qualsiasi blocco con una superficie piana

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## protectionStacking

Permette di unire protezioni di tipo differente

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## prunedPlants

Permette di potare le piante

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## quartzCraftingCompatibility

Aggiunge maggiore compatibilità frai crafting dei blocchi di quarzo

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## redcraft

Gestisce il teletrasporto infradimensionale fra i 2 server

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## redSandstoneCraftingCompatibility

Aggiunge maggiore compatibilità fra i crafting dei blocchi di arenaria rossa

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## renewableSporeblossom

Permette di usare la farina d'ossa sul fiore sporifero

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## repairableAnvil

Permette di riparare l'incudine con i lingotti di ferro

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`

# reviveCoral

Permette di resuscitare i coralli con le bottiglie d'acqua

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## ropes

Permette di legare due staccionate

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## sandstoneCraftingCompatibility

Aggiunge maggiore compatibilità frai crafting dei blocchi di arenaria

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## silkyBlockstates

Permette di minare un blocco mantenendo il suo stato se viene utilizzato un attrezzo incantato con "Silky Blockstate"

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## sitAnywhere

Permette di piazzare le selle a terra

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## slabToBlockCrafing

Permette di craftare un blocco partendo da due lastre

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## specialNameTags

Aggiunge molte altre targhette speciali

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## spongesqueeze

Permette di spremere le spugne

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## stonecutterQuartz

Aggiunge i crafting dei blocchi di quarzo all'interno del tagliapietre

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## stonecutterRedSandstone

Aggiunge i crafting dei blocchi di arenaria rossa all'interno del tagliapietre

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## stonecutterSandstone

Aggiunge i crafting dei blocchi di arenaria all'interno del tagliapietre

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## stonecutterWood

Aggiunge i crafting dei derivati del legno all'interno del tagliapietre

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## treecapitator

Permette di abbattere un intero albero se tagliato alla base con un'ascia incantata con "TreeCapitator"

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## villagerLeash

Permette di legare i villager al guinzaglio

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## zombieHorseSpawning

I cavalli zombi possono generarsi nel mondo.
Se impostata ad halloweeen, si genereranno solo ad Halloween

- Tipo: `booleano`
- Valore predefinito: `false`
- Opzioni suggerite: `true`, `false`, `halloween`
- Categorie: `SURVIVAL`, `FEATURE`, `REDCRAFT`
- 
## accurateBlockPlacement
I client possono fornire un diverso sistema di piazzamento per i blocchi

Da Carpet Extra di Gnembon

* Type: `booleano`
* Default value: `false`
* Required options: `true`, `false`
* Categorie: `EXTRAS`, `SURVIVAL`

## blockStateSyncing
Risolve problemi di sincronizzazione delle informazioni in F3 per alcuni blocchi
Potrebbe aumentare il traffico di rete
Funziona con cactus, canne da zucchero, arboscelli, tramogge, distributori e gettatori

Da Carpet Extra di Gnembon

* Type: `booleano`
* Default value: `false`
* Required options: `true`, `false`
* Categorie: `EXTRAS`, `EXPERIMENTAL`

## carefulBreak
Piazza i blocchi direttamente nell'inventario quando rotti accovacciandosi

Da Carpet Addons di whoImT

* Type: `booleano`
* Default value: `false`
* Required options: `true`, `false`
* Categorie: `SURVIVAL`, `FEATURE`, `EXPERIMENTAL`, `ADDONS`
