import os, json, re, urllib.request

current_directory = os.path.dirname(os.path.abspath(__file__))

os.chdir(current_directory)

blocks = []
slabs = []

with urllib.request.urlopen("https://raw.githubusercontent.com/Arcensoth/mcdata/master/processed/reports/blocks/simplified/data.json") as url:
    data = json.loads(url.read().decode())
    blocks = data.keys()
    for block in blocks:
        if '_slab' in block:
            slabs.append(block.replace('minecraft:',''))

def find_block(slab):
    if 'minecraft:'+slab.replace('_slab','') in blocks:
        return slab.replace('_slab','')
    elif 'minecraft:'+slab.replace('_slab','_planks') in blocks:
        return slab.replace('_slab','_planks')
    elif 'minecraft:'+slab.replace('_slab','_block') in blocks:
        return slab.replace('_slab','_block')
    elif 'minecraft:'+slab.replace('_slab','s') in blocks:
        return slab.replace('_slab','s')
    else:
        return None

def content(slab):
    block = find_block(slab)
    if block == None:
        return None
    else:
        return {
            "type": "minecraft:crafting_shaped",
            "pattern": ["#","#"],
            "key": {"#": {"item": "minecraft:"+slab}},
            "result": {
                "item": "minecraft:"+block,
                "count": 1
            }
        }

def advancement(slab):
    return {
        "parent": "minecraft:recipes/root",
        "rewards": {
           "recipes": [
               "carpet-redcraft-addons:slab_to_full_block/"+slab
           ]
       },
       "criteria": {
           "has_the_recipe": {
               "trigger": "minecraft:recipe_unlocked",
               "conditions": {
                   "recipe": "carpet-redcraft-addons:slab_to_full_block/"+slab
               }
           },
           "has_item": {
               "trigger": "minecraft:inventory_changed",
               "conditions": {
                   "items": [
                       {
                           "item":slab
                       }
                   ]
               }
           }
       },
       "requirements": [
           [
               "has_item",
               "has_the_recipe"
           ]
       ]
   }

for slab in slabs:
    c = content(slab)
    if c == None:
        print('Errore con il blocco '+slab)
        continue
    else:
        with open(slab + '.json','w') as file:
            file.write(json.dumps(c, indent = 4))
        with open('../../advancements/slab_to_full_block/recipes/' + slab + '.json','w') as file:
            file.write(json.dumps(advancement(slab), indent = 4))