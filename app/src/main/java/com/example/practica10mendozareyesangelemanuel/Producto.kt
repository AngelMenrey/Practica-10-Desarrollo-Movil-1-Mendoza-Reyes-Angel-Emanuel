package com.example.practica10mendozareyesangelemanuel


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView

class Producto : AppCompatActivity() {

    private lateinit var description: TextView
    private lateinit var imagen: ImageView
    private lateinit var barra: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_producto)

        description = findViewById(R.id.textDescripcion)
        imagen = findViewById(R.id.imagenProducto)
        barra = findViewById(R.id.pbCircular)
        barra.visibility = View.GONE

        val infoRecibida = intent.extras
        val nombre: String?
        val precio: Int?
        val numImagen: Int?

        if(infoRecibida != null){
            nombre = infoRecibida.getString("nombre")
            precio = infoRecibida.getInt("precio")
            numImagen = infoRecibida.getInt("numImagen")
            description.text = "Nombre del Producto: ${nombre}\n\n" +
                    "Precio del producto: $${precio}.00"
            when(numImagen){
                1 -> imagen.setImageResource(R.drawable.img1)
                2 -> imagen.setImageResource(R.drawable.img2)
                3 -> imagen.setImageResource(R.drawable.img3)
                4 -> imagen.setImageResource(R.drawable.img4)
                5 -> imagen.setImageResource(R.drawable.img5)
                6 -> imagen.setImageResource(R.drawable.img6)
            }
        }else{
            nombre = "Null"
            precio = 0
            numImagen=1
            description.text = "Producto: \n${nombre}\n" +
                    "Precio: $${precio}"
        }
    }
}