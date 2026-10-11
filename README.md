# Vientosur para Android — versión inicial 0.1.0

Proyecto nativo Android en Kotlin. Esta primera entrega implementa una pantalla de configuración Icecast, validación de campos y guardado local de host, puerto, punto de montaje y usuario.

## Importante: alcance actual

Esta versión **todavía no transmite audio** ni comprueba que el servidor exista. No introduzcas una contraseña real en una instalación de prueba compartida. La contraseña se mantiene solo en el campo de pantalla y no se guarda en preferencias.

## Estructura

- `app/src/main/java/com/vientosur/radio/MainActivity.kt`: interfaz y validación.
- `app/src/main/AndroidManifest.xml`: actividad y permiso de Internet.
- `app/build.gradle.kts`: configuración de Android/Kotlin.
- `.github/workflows/android.yml`: compilación automática del APK mediante GitHub Actions.

## Compilar usando solo un teléfono Android

Una opción es utilizar GitHub desde el navegador móvil para ejecutar la compilación en la nube.

1. Crea una cuenta o inicia sesión en GitHub.
2. Crea un repositorio nuevo llamado `Vientosur`.
3. Sube todos los archivos y carpetas de este proyecto conservando su estructura. Puedes descomprimir el ZIP y usar la carga de archivos del sitio web de GitHub; si la carga móvil de carpetas no funciona, usa GitHub Codespaces o un editor web compatible.
4. Confirma los archivos en la rama `main`.
5. Abre la pestaña **Actions** del repositorio y selecciona **Build Vientosur Android APK**.
6. Pulsa **Run workflow** si no se ha iniciado automáticamente tras el primer `push`.
7. Cuando termine correctamente, abre la ejecución y descarga el artefacto `Vientosur-debug-apk`.
8. Descomprime el artefacto en el teléfono y abre `app-debug.apk`. Android puede pedir autorización para instalar aplicaciones de esa fuente.

## Configuración de ejemplo

- Servidor: nombre de host de tu proveedor Icecast, sin `http://`.
- Puerto: el que te haya indicado el proveedor, frecuentemente `8000`.
- Punto de montaje: exactamente el que exista en el servidor, por ejemplo `/stream.mp3`.
- Usuario: el que requiera tu configuración Icecast; puede ser `source`, pero no siempre.
- Contraseña: la contraseña de emisión correspondiente.

No publiques credenciales en repositorios, capturas, registros de compilación ni mensajes.

## Siguientes hitos

1. Probar la conectividad real con el servidor.
2. Añadir captura del micrófono y codificación de audio.
3. Implementar la conexión de fuente Icecast y el envío continuo de audio.
4. Añadir servicio en primer plano para mantener la emisión con la pantalla apagada.
5. Proteger las credenciales con almacenamiento seguro y probar la reconexión.
