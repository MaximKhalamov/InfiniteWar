from PIL import Image
import numpy as np

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

    result.save(output_path)
    print(f"Saved with transparency to {output_path}")

# === MAIN ===

color_from_x = get_average_color("x_invar.png")

gradient = create_gradient(color_from_x)
apply_gradient_with_alpha("y_ingot.png", gradient, "ingotInvar.png")
