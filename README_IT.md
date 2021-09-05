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
- [stonecutterQuartz](#stonecutterQuartz)
- [stonecutterRedSandstone](#stonecutterRedSandstone)
- [stonecutterSandstone](#stonecutterSandstone)
- [stonecutterStone](#stonecutterStone)
- [stonecutterWood](#stonecutterWood)
- [treecapitator](#treecapitator)
- [villagerLeash](#villagerLeash)

## anvilRepairCostLimit

Permette di scegliere il massimo costo di riparazione all'interno dell'incudine

- Type: `int`
- Default value: `40`
- Suggested options: `40`, `1024`, `24791`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`
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


- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`


## betterItemFrames

Usano una membrana di phantom o un pannello di vetro su una cornice per oggetti, la renderà invisibile o fissa

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## colorableShulkers

Permette di colorare le Shulker

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `COMMAND`

## commandLocatePlayer

Permette di localizzare i giocatori

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `COMMAND`

## commandLookme

Aggiunge il comando /lookme per ruotare le entità

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `COMMAND`

## commandPlayerme

Permette ai giocatori non operatori di eseguire /player su sé stessi

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `COMMAND`

## commandSkull

Permette di avere accesso al comando /skull

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `COMMAND`

## commandStats

Permette di avere accesso al comando /stats

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `COMMAND`

## commandSwapitem

Permette di avere accesso al comando /swapitem

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `COMMAND`

## commandTimebar

Permette di avere accesso al comando /timebar

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `COMMAND`

## commandWaystone

Permette di avere accesso al comando /waystone

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `COMMAND`

## craftableBricks

Aggiunge retrocrafting del prismarino, dei mattoni e mattoni del Nether

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## craftableCobwebs

Permette di craftare le ragnatele

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## craftableColoredBlocks

Permette di craftare blocchi colorati partendo dalle altre varianti

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## craftableCorals

Permette di craftare i blocchi di corallo partendo da coralli o gorgonie

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## craftableDeadBushes

Permette di far esiccare gli arboscelli nella fornace

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## craftableDeepslateOres

Permette di craftare gli ore in deepslate nel banco da forgiatura

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## craftableElytra

Permette di craftare le elytra

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## craftableIce

Permette di craftare ghiaccio partendo dal ghiccio compatto

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## craftableLargeFern

Permette di craftare le felci alte

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## craftableNetherWarts

Permette di craftare verruche del nether partendo dal relativo blocco

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## craftablePackedIce

Permette di craftare il ghiaccio compatto partendo dalla versione blu

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## craftablePlayerHead

Permette di craftare le teste di giocatore partendo da una qualsiasi testa

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## craftableQuartz

Permette di craftare il quarzo partendo da una qualsiasi variante di blocco

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## craftableRedSand

Permette di craftare la sabbia rossa partendo dall'arenaria rossa

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## craftableSand

Permette di craftare la sabbia partendo dall'arenaria

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## craftableSculkSensor

Permette di craftare Sculk Sensor con endeperal e osservatori

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## craftableShulkerShells

Permette di craftare i gusci di shulker partendo da quelli di tartaruga e un fiore di chorus scoppiato

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## craftableTallGrass

Permette di craftare l'erba alta

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## craftableTuffAndCalcite

Permette di craftare il tufo e la calcite

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## crumbleConcrete

Permette di trasformare il calcestruzzo in polvere di calcestruzzo riempiendo una bottiglia d'acqua

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## disableOPsCommandFeedback

Disabilita il feedback dei coomandi per i giocatori OP di livello 1

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `FEATURE`, `REDCRAFT`

## dragonEggRespawns

Ogni drago dell'end rilascerà un uovo alla propria morte

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## endermanNoGrief

Riduce il numero di blocchi che gli enderman possono raccogliere

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `DATAPACK`

## fastRedstoneCrafting

Aggiunge crafting alternativi di dispenser e repeater

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## floatingLadders

Permette di piazzare una scala a pioli fluttuante sotto ad una già piazzata

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## ghastNoGrief

I ghast non danneggeranno più il mondo

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## graves

Crea una tomba personale alla morte del giocatore

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## horseStats

Accovacciandosi e premendo il tasto destro su un cavallo/mulo/asino verranno riportatate in chat le statistiche dell'animale

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## huskDropsSand

Gli zombi secchi rilasciano sabbia alla loro morte

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `DATAPACK`

## illusionersSpawnInRaids

Permette agli illusionisti di generarsi all'interno delle invasioni (Necessita un riavvio)

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `DATAPACK`

## lastDeathCompass

Alla morte verrà consegnata una bussola che punta nel luogo di morte

Sarà possibile riottenere tale bussola premendo con il tasto destro una bussola mentre ci si accovaccia

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## lavaSponges

Le spugne funzioneranno anche con la lava, ma bruceranno.

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## moreWanderingTrades

Aggiunge nuovi scambi al mercante viandante

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## pillagerLeash

Permette di legare i pillager con le mani vuote

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## placeablePlants

Permette di piantare le piante su qualsiasi blocco con una superficie piana

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## protectionStacking

Permette di unire protezioni di tipo differente

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## prunedPlants

Permette di potare le piante

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## quartzCraftingCompatibility

Aggiunge maggiore compatibilità frai crafting dei blocchi di quarzo

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## redcraft

Gestisce il teletrasporto infradimensionale fra i 2 server

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## redSandstoneCraftingCompatibility

Aggiunge maggiore compatibilità fra i crafting dei blocchi di arenaria rossa

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## renewableSporeblossom

Permette di usare la farina d'ossa sul fiore sporifero

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## repairableAnvil

Permette di riparare l'incudine con i lingotti di ferro

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

# reviveCoral

Permette di resuscitare i coralli con le bottiglie d'acqua

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## ropes

Permette di legare due staccionate

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## sandstoneCraftingCompatibility

Aggiunge maggiore compatibilità frai crafting dei blocchi di arenaria

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## silkyBlockstates

Permette di minare un blocco mantenendo il suo stato se viene utilizzato un attrezzo incantato con "Silky Blockstate"

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## sitAnywhere

Permette di piazzare le selle a terra

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## slabToBlockCrafing

Permette di craftare un blocco partendo da due lastre

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## specialNameTags

Aggiunge molte altre targhette speciali

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## stonecutterQuartz

Aggiunge i crafting dei blocchi di quarzo all'interno del tagliapietre

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## stonecutterRedSandstone

Aggiunge i crafting dei blocchi di arenaria rossa all'interno del tagliapietre

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## stonecutterSandstone

Aggiunge i crafting dei blocchi di arenaria all'interno del tagliapietre

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## stonecutterWood

Aggiunge i crafting dei derivati del legno all'interno del tagliapietre

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`, `DATAPACK`

## treecapitator

Permette di abbattere un intero albero se tagliato alla base con un'ascia incantata con "TreeCapitator"

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## villagerLeash

Permette di legare i villager al guinzaglio

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## accurateBlockPlacement
I client possono fornire un diverso sistema di piazzamento per i blocchi

Da Carpet Extra di Gnembon

* Type: `boolean`
* Default value: `false`
* Required options: `true`, `false`
* Categories: `EXTRAS`, `SURVIVAL`

## blockStateSyncing
Risolve problemi di sincronizzazione delle informazioni in F3 per alcuni blocchi
Potrebbe aumentare il traffico di rete
Funziona con cactus, canne da zucchero, arboscelli, tramogge, distributori e gettatori

Da Carpet Extra di Gnembon

* Type: `boolean`
* Default value: `false`
* Required options: `true`, `false`
* Categories: `EXTRAS`, `EXPERIMENTAL`

## updateSuppressionCrashFix
Risolve i crash del server dovuti alla soppressione di aggiornamenti

Da Carpet Extra di Gnembon 

* Type: `boolean`
* Default value: `false`
* Required options: `true`, `false`
* Categories: `BUGFIX`, `EXTRAS`

## carefulBreak
Piazza i blocchi direttamente nell'inventario quando rotti accovacciandosi

Da Carpet Addons di whoImT

* Type: `boolean`
* Default value: `false`
* Required options: `true`, `false`
* Categories: `SURVIVAL`, `FEATURE`, `EXPERIMENTAL`, `ADDONS`
