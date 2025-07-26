from pathlib import Path
import os
import shutil


SCRIPT_PATH             = Path(__file__).resolve()
SCRIPT_DIR              = SCRIPT_PATH.parent
SRC_DIR                 = SCRIPT_DIR / f"../resources_generated"
DEST_DIR                = SCRIPT_DIR / f"../resources"


def merge_directories(src_dir, dst_dir):
    for root, dirs, files in os.walk(src_dir):
        rel_path = os.path.relpath(root, src_dir)
        target_root = os.path.join(dst_dir, rel_path)

        os.makedirs(target_root, exist_ok=True)

        for file in files:
            src_file = os.path.join(root, file)
            dst_file = os.path.join(target_root, file)

            if not os.path.exists(dst_file):
                shutil.copy2(src_file, dst_file)
                print(f"Copied  : {dst_file}")
            else:
                print(f"Existed : {dst_file}")


if __name__ == "__main__":
    merge_directories(SRC_DIR, DEST_DIR)
