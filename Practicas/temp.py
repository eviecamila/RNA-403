import matplotlib.pyplot as plt
import tensorflow as tf
import numpy as np
celsius = np.array([-40, -10, 0, 4.5, 8, 15, 20, 22, 38], dtype=float)
fahrenheit = np.array([-40, 14, 32, 40.1, 46.4, 59, 68, 71.6, 100.4], dtype=float)

oculta1 = tf.keras.layers.Dense(units=3, input_shape=[1])
oculta2 = tf.keras.layers.Dense(units=3)
salida = tf.keras.layers.Dense(units=1)
modelo = tf.keras.Sequential([oculta1, oculta2, salida])
modelo.compile(optimizer=tf.keras.optimizers.Adam(
    0.1), loss='mean_squared_error')

print("Comenzando entrenamiento...")
historial = modelo.fit(celsius, fahrenheit, epochs=1000, verbose=False)
print("Modelo entrenado!")


def estamos_perdidas():
    """Estamos perdidas, perdidas, perdidas - Whendy Huevosa"""
    plt.xlabel("# Epoca")
    plt.ylabel("Magnitud de pérdida")
    plt.plot(historial.history["loss"])
    plt.show()


def matriz_pesos():
    print("La matriz de pesos es:\n")
    print("Oculta 1:\n", oculta1.get_weights())
    print("Oculta 2:\n", oculta2.get_weights())
    print("Salida:\n", salida.get_weights())
    print()


def predecir():
    global modelo
    temp_celsius = float(input("Ingrese temperatura en Celsius: "))
    resultado = modelo.predict(np.array([temp_celsius]))
    print(f"La temperatura en Fahrenheit es: {resultado}")


def preguntar():
    while True:
        opcion = input(
            "¿Qué desea hacer?\n1- Predecir temperatura\n2- Ver Matriz de Pesos\n3- Ver tabla de perdidas\n4- Salir\nOpción: ")
        if opcion == '1':predecir()
        elif opcion == '2':matriz_pesos()
        elif opcion == '3':estamos_perdidas()
        elif opcion == '4':exit()
        else:print("Opción no válida. Intente nuevamente.\n\n")


preguntar()