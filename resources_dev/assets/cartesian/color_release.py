from PIL import Image
import numpy as np
import os

directory_path = "./"

def get_average_color(image_path):
    img = Image.open(image_path).convert("RGB")
    np_img = np.array(img)
    avg_color = np_img.mean(axis=(0, 1))
    return tuple(int(c) for c in avg_color)

def create_gradient(color):
    """Создаёт градиент от чёрного → цвет → белый"""
    gradient = []

    # Первая половина: чёрный -> цвет
    for i in range(128):
        factor = i / 127
        r = int(color[0] * factor)
        g = int(color[1] * factor)
        b = int(color[2] * factor)
        gradient.append((r, g, b))

    # Вторая половина: цвет -> белый
    for i in range(128):
        factor = i / 127
        r = int(color[0] + (255 - color[0]) * factor)
        g = int(color[1] + (255 - color[1]) * factor)
        b = int(color[2] + (255 - color[2]) * factor)
        gradient.append((r, g, b))

    return gradient

def apply_gradient_with_alpha(grayscale_path, gradient, output_path):
    gray_img = Image.open(grayscale_path).convert("LA")  # L = luminance, A = alpha
    width, height = gray_img.size
    result = Image.new("RGBA", (width, height))

    gray_pixels = gray_img.load()
    result_pixels = result.load()

    for y in range(height):
        for x in range(width):
            luminance, alpha = gray_pixels[x, y]
            r, g, b = gradient[luminance]
            result_pixels[x, y] = (r, g, b, alpha)

    # return result

    result.save(output_path)
    print(f"Saved with transparency to {output_path}")

# === MAIN ===

fg_files    = []
color_files = []
item_files  = []
bg_files    = []


for filename in os.listdir(directory_path):
    if filename.startswith("x_"):
        color_files.append(filename)
    elif filename.startswith("y_"):
        item_files.append(filename)
    elif filename.startswith("z_"):
        bg_files.append(filename)


print(color_files)
print(item_files)
print(bg_files)


for color_file in color_files:
    color_name = color_file[2:-4]

    # os.mkdir(f"./result/{color_name}")
    for item_file in item_files:
        color_from_x = get_average_color(color_file)

        gradient = create_gradient(color_from_x)

        item_name = item_file[2:-4]

        if f"z_{item_name}_0.png"  in bg_files:
            file_name = f"./stage_1/{item_name}{color_file[2:].capitalize()}"
            apply_gradient_with_alpha(item_file, gradient, file_name)

            fg = Image.open(file_name).convert("RGBA")
            
            for bg_file in bg_files:
                if bg_file.startswith(f"z_{item_name}"):

                    fg = Image.open(file_name).convert("RGBA")
                    bg = Image.open(bg_file).convert("RGBA")

                    # Приводим фон к размеру переднего плана, если нужно
                    if bg.size != fg.size:
                        bg = bg.resize(fg.size)

                    # Альфа-композитинг: накладываем foreground на background
                    result = Image.alpha_composite(bg, fg)

                    save_path = f"./result/{color_name}/{color_name}_{item_name}{bg_file[-6:-4]}.png"
                    result.save(save_path)


                    # Every item save here
                    save_path = f"./result/0_everything/{color_name}_{item_name}{bg_file[-6:-4]}.png"
                    result.save(save_path)


                    print(f"Saved composite to {save_path}")

        else:
            apply_gradient_with_alpha(item_file, gradient, f"./result/{color_name}/{color_name}_{item_name}.png")
            apply_gradient_with_alpha(item_file, gradient, f"./result/0_everything/{color_name}_{item_name}.png")






