import matplotlib.pyplot as plt

# Definir los datos para el diagrama
capas = ['Capa de Entrada', 'Capa Oculta 1', 'Capa Oculta 2', 'Capa de Salida']
neuronas_por_capa = [3, 3, 3, 1]

# Definir los pesos y sesgos para cada neurona
pesos_sesgos = {
    'Capa Oculta 1': {
        'Neurona 1': {'Pesos': [0.49, 0.22, -0.13], 'Sesgo': 3.98},
        'Neurona 2': {'Pesos': [3.98, 3.91, 0.63], 'Sesgo': 3.91},
        'Neurona 3': {'Pesos': [-1.57, 0.40, 0.90], 'Sesgo': -0.73}
    },
    'Capa Oculta 2': {
        'Neurona 1': {'Pesos': [-1.58, 0.19, -0.34], 'Sesgo': -3.93},
        'Neurona 2': {'Pesos': [-0.37, 0.14, 0.43], 'Sesgo': 2.50},
        'Neurona 3': {'Pesos': [-0.73, 0.00, -0.00], 'Sesgo': -0.73}
    },
    'Capa de Salida': {
        'Neurona de Salida': {'Pesos': [-1.60, 0.34, -0.00], 'Sesgo': 3.70}
    }
}

# Crear la figura y los ejes
fig, ax = plt.subplots(figsize=(10, 6))

# Graficar las capas y neuronas como bolitas
for i, capa in enumerate(capas):
    for j in range(neuronas_por_capa[i]):
        ax.scatter(i, j, s=300, color='blue')  # s es el tamaño de las bolitas
        # Agregar etiquetas de pesos y sesgos para cada neurona
        if capa in pesos_sesgos:
            neurona = f'Neurona {j + 1}' if j != 0 else 'Neurona de Salida'
            datos = pesos_sesgos[capa].get(neurona, {})
            pesos = ', '.join([str(p) for p in datos.get('Pesos', [])])
            sesgo = datos.get('Sesgo', '')
            ax.text(i, j, f'Pesos: {pesos}\nSesgo: {sesgo}', ha='center', va='center', fontsize=8)

# Dibujar las flechas entre las neuronas
for i in range(len(capas) - 1):
    for j in range(neuronas_por_capa[i]):
        for k in range(neuronas_por_capa[i + 1]):
            ax.arrow(i, j, 1, k - j, head_width=0.1, head_length=0.1, fc='gray', ec='gray')

# Configurar el título y los ejes
ax.set_title('Diagrama de la Red Neuronal')
ax.set_xlabel('Capa')
ax.set_ylabel('Neurona')

# Configurar los límites de los ejes
ax.set_xlim(-0.5, len(capas) - 0.5)
ax.set_ylim(-0.5, max(neuronas_por_capa) - 0.5)

# Mostrar el diagrama
plt.tight_layout()
plt.show()
