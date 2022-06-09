# Carpet RedCraft Addons

[![License](https://img.shields.io/github/license/MultiCoreNetwork/carpet-redcraft-addons.svg)](http://www.gnu.org/licenses/lgpl-3.0.html)
[![Issues](https://img.shields.io/github/issues/MultiCoreNetwork/carpet-redcraft-addons.svg)](https://github.com/MultiCoreNetwork/carpet-redcraft-addons/issues)
[![MC Versions](http://cf.way2muchnoise.eu/versions/For%20MC_carpet-redcraft-addons_all.svg)](https://www.curseforge.com/minecraft/mc-mods/carpet-redcraft-addons)
[![CurseForge](http://cf.way2muchnoise.eu/full_carpet-redcraft-addons_downloads.svg)](https://www.curseforge.com/minecraft/mc-mods/carpet-redcraft-addons)

[>>> ITALIANO <<<](https://github.com/MultiCoreNetwork/carpet-redcraft-addons/blob/master/README_IT.md)

A [Carpet mod](https://github.com/gnembon/fabric-carpet) (fabric-carpet) extension, a collection of carpet mod style useful tools and interesting features

Use with carpet mod in the same Minecraft version. Use newer carpet mod versions whenever possible

## Rules
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
- [craftableLargeFern](#craftableLargeFern)
- [craftableNetherWarts](#craftableNetherWarts)
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
- [ghastNoGrief](#ghastNoGrief)
- [graves](#graves)
- [horseStats](#horseStats)
- [huskDropsSand](#huskDropsSand)
- [illusionersSpawnInRaids](#illusionersSpawnInRaids)
- [lavaSponges](#lavaSponges)
- [lastDeathCompass](#lastDeathCompass)
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
- [unpackableIce](#unpackableIce)
- [villagerLeash](#villagerLeash)
- [zombieHorseSpawning](#zombieHorseSpawning)

## Rules from other Extensions
### [Gnembon's Carpet Extra](https://github.com/gnembon/carpet-extra/)
- [accurateBlockPlacement](#accurateBlockPlacement)
- [blockStateSyncing](#blockStateSyncing)
### [whoImT's Carpet Addons](https://github.com/whoImT/carpet-addons/)
- [carefulBreak](#carefulBreak)

---
## anvilRepairCostLimit

Allows to choose the anvil repair cost limit

- Type: `int`
- Default value: `40`
- Suggested options: `40`, `1024`, `24791`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`
- Additional notes:
    - You must choose a value from 1 to 32767

## armorStandEditor 

**[BETA]**<br/>
When punching an ArmorStand with a `hoe` it will go in edit mode.<br/>
When an ArmorStand is in edit mode, you can: 
- Right click it to put the item you're holding on its head.
- Shift right click to move the ArmorStand around.<br/>
  Releasing the shift button it will apply the changes.<br/>
  Changing slot it will undo the changes.
- Right click with a `hoe` to change editing mode:<br/>
  `PITCH`, `YAW`, `ROLL`, `TEST`
- Shift right click with a `hoe` to move the ArmorStand's part you are looking at.<br/>
  Releasing the shift button it will apply the changes.<br/>
  Changing slot it will undo the changes.
- Shift right click with two `sticks` to give arms to the ArmorStand.
- Shift right click with a `smooth stone slab` to remove to the ArmorStand its base plate.
- Shift right click with a `feather` to remove gravity to the ArmorStand.
- Shift right click with a `phantom_membrane` to make the ArmorStand invisible.
- Shift right click with a `ender_eye` to make the ArmorStand visible again.
- Shift right click with a `campfire` or a `soul campfire` to summon smoke particle at ArmorStand position.
- Shift right click with a `stone button` or a `blackstone button` to make the ArmorStand small.
- Shift right click with a `stone pressure plate` or a `blackstone pressure plate` to make the ArmorStand big again.
- Shift right click with a `blaze powder` to summon fire particle at ArmorStand position.


- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## betterItemFrames

Right clicking with a Phantom Membrane or Glass Pane on an Item Frame will make it invisible or fixed

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## colorableShulkers

Allows to dye Shulkers

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## commandLocatePlayer

Allow you to locate player

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `COMMAND`

## commandLookme

Adds the /lookme command to rotate entities

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `COMMAND`

## commandPlayerme

Allows deopped player to run /player on themselves

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `COMMAND`

## commandSkull

Allows to have access to the /skull command

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `COMMAND`

## commandStats

Allows to have access to the /stats command

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `COMMAND`

## commandSwapitem

Allows to have access to the /swapitem command

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `COMMAND`

## commandTimebar

Allows to have access to the /timebar command

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `COMMAND`

## commandWaystone

Allows to have access to the /waystone command

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `COMMAND`

## craftableBricks

Adds reverse crafting of Prismarine and Brick, normal and nether variant

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`

## craftableCobwebs

Allows to craft Cobwebs

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`

## craftableColoredBlocks

Allows to use colored blocks to craft other color variants

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`

## craftableCorals

Allows to craft Coral Blocks with 4 Coral Fans and Corals

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`

## craftableDeadBushes

Allows to smelt saplings to get Dead Bushes

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`

## craftableDeepslateOres

You can craft deepslate ore in smithing table

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`

## craftableElytra

Allows to craft Elytras

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`

## craftableLargeFern

Allows to craft Large Ferns

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`

## craftableNetherWarts

Allows to craft Nether Warts from Nether Warts Block

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`

## craftablePlayerHead

Allows to craft Player Head from any kind of head

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`

## craftableQuartz

Allows to craft Quartz from any kind of Quartz Block

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`

## craftableRedSand

Allows to craft Red Sand from Red Sandstone

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`

## craftableSand

Allows to craft Red Sand from Sandstone

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`

## craftableSculkSensor

Allow to craft Sculk Sensor with endeperals and observers

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`

## craftableShulkerShells

Allows to craft Shulker Shells from Turle Shells and Popped Chorus Fruits

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`

## craftableTallGrass

Allows to craft Tall Grass

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`

## craftableTuffAndCalcite

Allows to craft Tuff and Calite

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`

## crumbleConcrete

Allow you to transform concrete to concrete powder filling a glass bottle

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## disableOPsCommandFeedback

Disable Command Feedback for OPs level 1

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `FEATURE`, `REDCRAFT`

## dragonEggRespawns

Ender Dragon always spawns a new dragon egg

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## dynamicLight

Holding a light source will light around the player

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## endermanNoGrief

Reduced the blocks that an Enderman can hold

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## fastRedstoneCrafting

Adds fast crafting for Dispenser and Repeater

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`

## floatingLadders

Allows to place ladders under other ladders

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## ghastNoGrief

Ghasts will no grief the world

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## graves

Create personal graves when the player dies

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## horseStats

Right clicking while sneaking on a horse/donkey/mule, will display the stats of the mob in the chat

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## huskDropsSand

Husks drop sand on death

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## illusionersSpawnInRaids

Allows illusioners to spawn in raids (It needs restart)

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## lastDeathCompass

Allows to right click a compass to make it point towards the last death point

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## lavaSponges

Sponges work on lava too, but they will burn

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## moreWanderingTrades

Adds more trades to the Wandering Trader

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## pillagerLeash

Allows to tie pillager with empty hands

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## placeablePlants

Allows to place plants on all blocks with flat top surface

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## protectionStacking

Allows to stack multiple protection types

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## prunedPlants

Allows to prune plants with shears

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## quartzCraftingCompatibility

Adds crafting compatibility with all types of Quartz Block

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`

## redcraft

Portals in RedCraft2 area will link to redcraft2 dimensions

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## redSandstoneCraftingCompatibility

Adds crafting compatibility with all types of Red Sandstone

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`

## renewableSporeblossom

Allow to use bonemeal on Spore blossoms

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## repairableAnvil

Allow to repair anvil with iron ingot

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## reviveCoral

Allow to revive dead coral with water bottle

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## ropes

Allows to leash a rope beetween two fences

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## sandstoneCraftingCompatibility

Adds crafting compatibility with all types of Sandstone

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`

## silkyBlockstates

Allows to mine a block keeping the blockstate if using a Silky Blocstate tool

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## sitAnywhere

Allows to place saddles on the ground

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## slabToBlockCrafing

Allows to use 2 slabs to craft the block back

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`

## specialNameTags

Adds many special Name Tags

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## spongesqueeze

Allow to squeeze and wet sponge on cauldron

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## stonecutterQuartz

Adds stonecutter craftings for all types of Quartz Block

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`

## stonecutterRedSandstone

Adds stonecutter craftings for all types of Red Sandstone

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`

## stonecutterSandstone

Adds stonecutter craftings for all types of Sandstone

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`

## stonecutterWood

Adds stonecutter craftings for all types of Wooden Block

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`

## treecapitator

Allows to mine an entire tree mining a single block if using a TreeCapitator axe

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`
- 
## unpackableIce

Allow to unpack PackedIce & BlueIce using the stonecutter

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`, `CRAFTING`

## villagerLeash

Allows to leash villagers

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## zombieHorseSpawning

Zombie horses can spawn in the world
With halloween: it will spawn only during Halloween

- Type: `boolean`
- Default value: `false`
- Suggested options: `true`, `false`, `halloween`
- Categories: `SURVIVAL`, `FEATURE`, `REDCRAFT`

## accurateBlockPlacement
Client can provide alternative block placement.

From Gnembon's Carpet Extra

* Type: `boolean`
* Default value: `false`
* Required options: `true`, `false`
* Categories: `EXTRAS`, `SURVIVAL`

## blockStateSyncing
Fixes block states in F3 debug mode not updating for some blocks.  
May cause increased network traffic.  
Works with cactus, sugar cane, saplings, hoppers, dispensers and droppers.

From Gnembon's Carpet Extra

* Type: `boolean`
* Default value: `false`
* Required options: `true`, `false`
* Categories: `EXTRAS`, `EXPERIMENTAL`  

## carefulBreak
Places the mined block in the player inventory when sneaking.

From whoImT's Carpet Addons

* Type: `boolean`
* Default value: `false`
* Required options: `true`, `false`
* Categories: `SURVIVAL`, `FEATURE`, `EXPERIMENTAL`, `ADDONS`
