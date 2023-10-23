package br.edu.ifpb.foto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.net.Uri
import android.content.ContentValues

class MainActivity : AppCompatActivity() {
    private lateinit var foto: ImageView
    private lateinit var botao: Button
    private lateinit var imageUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.foto = findViewById(R.id.ivFoto)
        this.botao = findViewById(R.id.bTirarFoto)

        this.botao.setOnClickListener {
            val values = ContentValues()
            values.put(MediaStore.Images.Media.TITLE, "Nova Imagem")
            values.put(MediaStore.Images.Media.DESCRIPTION, "Imagem capturada pela câmera")
            imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)!!

            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
            startActivityForResult(intent, CAMERA_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            foto.setImageURI(imageUri)
        }
    }

    companion object {
        private const val CAMERA_REQUEST_CODE = 100
    }

}