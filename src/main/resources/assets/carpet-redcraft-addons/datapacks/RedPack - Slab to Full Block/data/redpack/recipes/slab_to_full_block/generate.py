import os, json, re

current_directory = os.path.dirname(os.path.abspath(__file__))

os.chdir(current_directory)

'''slabs = [
    "oak_slab",
    "spruce_slab",
    "birch_slab",
    "jungle_slab",
    "acacia_slab",
    "dark_oak_slab",
    "crimson_slab",
    "warped_slab",
    "stone_slab",
    "smooth_stone_slab",
    "stone_brick_slab",
    "sandstone_slab",
    "purpur_slab",
    "quartz_slab",
    "red_sandstone_slab",
    "brick_slab",
    "cobblestone_slab",
    "nether_brick_slab",
    "petrified_oak_slab",
    "prismarine_slab",
    "prismarine_brick_slab",
    "dark_prismarine_slab",
    "polished_granite_slab",
    "smooth_red_sandstone_slab",
    "mossy_stone_brick_slab",
    "polished_diorite_slab",
    "mossy_cobblestone_slab",
    "end_stone_brick_slab",
    "smooth_sandstone_slab",
    "smooth_quartz_slab",
    "granite_slab",
    "andesite_slab",
    "red_nether_brick_slab",
    "polished_andesite_slab",
    "diorite_slab",
    "cut_sandstone_slab",
    "cut_red_sandstone_slab",
    "blackstone_slab",
    "polished_blackstone_brick_slab",
    "polished_blackstone_slab"
]'''
slabs = [
   "cut_copper_slab",
   "waxed_cut_copper_slab",
   "lightly_weathered_cut_copper_slab",
   "waxed_lightly_weathered_cut_copper_slab",
   "semi_weathered_cut_copper_slab",
   "waxed_semi_weathered_cut_copper_slab",
   "weathered_cut_copper_slab"
]

def content(slab):
    return {
        "type": "minecraft:crafting_shaped",
        "pattern": ["#","#"],
        "key": {"#": {"item": slab}},
        "result": {
            "item": "minecraft:"+slab.replace('_slab','')+input(slab+' -> '+slab.replace('_slab','')),
            "count": 1
        }
    }

for slab in slabs:
    with open(current_directory + os.sep + slab + '.json','w') as file:
        file.write(json.dumps(content(slab), indent = 4))
