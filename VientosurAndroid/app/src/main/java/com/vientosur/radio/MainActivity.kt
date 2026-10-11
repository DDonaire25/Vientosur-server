
package com.vientosur.radio

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView
import android.view.Gravity
import android.graphics.Typeface
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var statusText: TextView
    private lateinit var micButton: Button
    private lateinit var musicButton: Button

    private var micEnabled = false
    private var musicSelected = false

    private val audioPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { granted ->
            if (granted) {
                micEnabled = true
                micButton.text = "Desactivar micrófono"
                statusText.text =
                    "Micrófono autorizado. La transmisión aún no está conectada."
            } else {
                statusText.text =
                    "Permiso de micrófono denegado."
            }
        }

    private val musicPicker =
        registerForActivityResult(
            ActivityResultContracts.OpenDocument()
        ) { uri ->
            if (uri != null) {
                musicSelected = true
                contentResolver.takePersistableUriPermission(
                    uri,
                    android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
                musicButton.text = "Música seleccionada ✓"
                statusText.text =
                    "Archivo seleccionado. El reproductor se implementará en la siguiente etapa."
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL
            setPadding(32, 40, 32, 32)
        }

        val title = TextView(this).apply {
            text = "VIENTOSUR"
            textSize = 30f
            typeface = Typeface.DEFAULT_BOLD
            gravity = Gravity.CENTER
        }

        val subtitle = TextView(this).apply {
            text = "Tu radio, desde el teléfono"
            textSize = 16f
            gravity = Gravity.CENTER
            setPadding(0, 8, 0, 28)
        }

        statusText = TextView(this).apply {
            text = "Emisora detenida"
            textSize = 16f
            gravity = Gravity.CENTER
            setPadding(0, 12, 0, 24)
        }

        musicButton = Button(this).apply {
            text = "Seleccionar música MP3"
            setOnClickListener {
                musicPicker.launch(arrayOf("audio/mpeg", "audio/*"))
            }
        }

        micButton = Button(this).apply {
            text = "Activar micrófono"
            setOnClickListener {
                if (micEnabled) {
                    micEnabled = false
                    text = "Activar micrófono"
                    statusText.text =
                        "Micrófono desactivado. Transmisión aún no conectada."
                } else {
                    val granted = ContextCompat.checkSelfPermission(
                        this@MainActivity,
                        Manifest.permission.RECORD_AUDIO
                    ) == PackageManager.PERMISSION_GRANTED

                    if (granted) {
                        micEnabled = true
                        text = "Desactivar micrófono"
                        statusText.text =
                            "Micrófono autorizado. La transmisión aún no está conectada."
                    } else {
                        audioPermissionLauncher.launch(
                            Manifest.permission.RECORD_AUDIO
                        )
                    }
                }
            }
        }

        val musicVolumeLabel = TextView(this).apply {
            text = "Volumen de música"
            textSize = 15f
            setPadding(0, 12, 0, 0)
        }

        val musicVolume = SeekBar(this).apply {
            max = 100
            progress = 70
            contentDescription = "Volumen de música"
        }

        val micVolumeLabel = TextView(this).apply {
            text = "Volumen de micrófono"
            textSize = 15f
            setPadding(0, 12, 0, 0)
        }

        val micVolume = SeekBar(this).apply {
            max = 100
            progress = 80
            contentDescription = "Volumen de micrófono"
        }

        val startButton = Button(this).apply {
            text = "INICIAR EMISIÓN"
            setOnClickListener {
                statusText.text =
                    "Interfaz preparada. Falta implementar la mezcla de audio y la conexión Icecast."
            }
        }

        val stopButton = Button(this).apply {
            text = "DETENER EMISIÓN"
            setOnClickListener {
                statusText.text = "Emisora detenida"
            }
        }

        layout.addView(title)
        layout.addView(subtitle)
        layout.addView(statusText)
        layout.addView(
            musicButton,
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layout.addView(
            micButton,
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layout.addView(musicVolumeLabel)
        layout.addView(
            musicVolume,
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layout.addView(micVolumeLabel)
        layout.addView(
            micVolume,
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layout.addView(
            startButton,
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layout.addView(
            stopButton,
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        setContentView(layout)
    }
}
