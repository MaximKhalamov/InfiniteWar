from pathlib import Path
import shutil
import json

WIP_TEXTURE             = "wip_texture"
MODID                   = "infinitewar"


SCRIPT_PATH             = Path(__file__).resolve()
SCRIPT_DIR              = SCRIPT_PATH.parent
TARGET_DIR              = SCRIPT_DIR    / f"../resources_generated"

ASSETS_DIR              = TARGET_DIR    / f"assets/{MODID}"
DATA_DIR                = TARGET_DIR    / f"data/{MODID}"

LANG_DIR                = ASSETS_DIR    / "lang"
MODELS_DIR              = ASSETS_DIR    / "models"
RECIPES_DIR             = DATA_DIR      / "recipes"
TEXTURES_DIR            = ASSETS_DIR    / "textures"
BLOCKSTATES_DIR         = ASSETS_DIR    / "blockstates"

WIP_SRC_TEXTURE_PATH    = SCRIPT_DIR   / "wip_texture.png"
WIP_SRC_TEXTURE_BLOCK_PATH = SCRIPT_DIR   / "wip_texture_block.png"

DEFINITIONS_PATH        = SCRIPT_DIR    / "../definitions.json"
LANG_PATH               = LANG_DIR      / "en_us.json"


def clear_generated_dir():
    if not TARGET_DIR.exists():
        TARGET_DIR.mkdir(parents=True)
        print(f"Directory created: {TARGET_DIR}")
    else:
        for item in TARGET_DIR.iterdir():
            if item.is_dir():
                shutil.rmtree(item)
            else:
                item.unlink()
        print(f"Cleared all contents inside: {TARGET_DIR}")


def create_dirs():
    LANG_DIR.mkdir(parents=True)
    (MODELS_DIR / "item").mkdir(parents=True)
    (MODELS_DIR / "block").mkdir(parents=True)
    RECIPES_DIR.mkdir(parents=True)
    (TEXTURES_DIR / "item").mkdir(parents=True)
    (TEXTURES_DIR / "block").mkdir(parents=True)
    BLOCKSTATES_DIR.mkdir(parents=True)


def add_lang_definition(definition, lang_dictionary):
    name        = definition["name"]
    eng_name    = definition["engName"]
    type_name   = definition["type"]

    lang_dictionary[f"{type_name}.{MODID}.{name}"] = f"{eng_name}"


def make_recipe(definition):
    with open(f"{RECIPES_DIR / definition["name"]}.json", "w", encoding="utf-8") as f:
        f.write(
f'''{{
  "type": "minecraft:crafting_shaped",
  "pattern": [
    "AAA",
    "ABA",
    "AAA"
  ],
  "key": {{
    "A": {{ "item": "minecraft:diamond" }},
    "B": {{ "item": "minecraft:apple" }}
  }},
  "result": {{
    "item": "{MODID}:{definition["name"]}"
  }}
}}'''
        )


def make_model(definition):

# ============== ITEM MODELS ==============
    if definition["type"] == "item":
        with open(f"{MODELS_DIR / "item" / definition["name"]}.json", "w", encoding="utf-8") as f:
            f.write(
f'''{{
  "parent": "item/generated",
  "textures": {{
    "layer0": "{MODID}:item/{definition['name']}"
  }}
}}'''
        )
        shutil.copy(WIP_SRC_TEXTURE_PATH, TEXTURES_DIR / "item" / f"{definition['name']}.png")


# ============== BLOCK MODELS ==============
    elif definition["type"] == "block":
        with open(f"{MODELS_DIR / "block" / definition["name"]}.json", "w", encoding="utf-8") as f:
            f.write(
f'''{{
  "parent": "block/cube_all",
  "textures": {{
    "all": "{MODID}:block/{definition['name']}"
  }}
}}'''
        )

        with open(f"{MODELS_DIR / "item" / definition["name"]}.json", "w", encoding="utf-8") as f:
            f.write(
f'''{{
  "parent": "{MODID}:block/{definition["name"]}"
}}'''
        )

        with open(f"{BLOCKSTATES_DIR / definition["name"]}.json", "w", encoding="utf-8") as f:
            f.write(
f'''{{
  "variants": {{
    "": {{ "model" : "{MODID}:block/{definition['name']}" }}
  }}
}}'''
        )
        shutil.copy(WIP_SRC_TEXTURE_BLOCK_PATH, TEXTURES_DIR / "block" / f"{definition['name']}.png")


# Open json file definitions.json with all of the items


if __name__ == "__main__":
    with open(DEFINITIONS_PATH, "r", encoding="utf-8") as f:
        definitions_array = json.load(f)

    clear_generated_dir()
    create_dirs()

    lang_dictionary = {}

    for definition in definitions_array:
        add_lang_definition(definition, lang_dictionary)

        if definition["genRecipe"]:
            make_recipe(definition)

        if definition["genModel"]:
            make_model(definition)


    with open(LANG_PATH, 'w', encoding='utf-8') as f:
        json.dump(lang_dictionary, f, ensure_ascii=False, indent=4)
