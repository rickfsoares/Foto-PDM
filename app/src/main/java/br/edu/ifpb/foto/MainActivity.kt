package br.edu.ifpb.foto

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    private lateinit var foto: ImageView
    private lateinit var botao: Button

    private val temporizador = Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.foto = findViewById(R.id.ivFoto)
        this.botao = findViewById(R.id.bTirarFoto)

        this.botao.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            if (intent.resolveActivity(packageManager) != null) {
                startActivityForResult(intent, CAMERA_REQUEST_CODE)
            } else {
                // cruza os dedos para não dar errado
                Log.e("APP-FOTO", "Nenhum aplicativo de câmera disponível")
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            foto.setImageBitmap(imageBitmap)

            temporizador.postDelayed({
                finish()
            }, 1000)


        }
    }

    companion object {
        private const val CAMERA_REQUEST_CODE = 100
    }
}