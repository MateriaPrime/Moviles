package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etCorreo = findViewById<EditText>(R.id.etPass)
        val etPass = findViewById<EditText>(R.id.etPass)
        val btnIngresar = findViewById<Button>(R.id.btnIngresar)

        val maxintentos: Int=3
        var intentos: Int=0
        val correoValido = "example@mail.com"
        val passValido = "password"

        btnIngresar.setOnClickListener {
            val nombre=etNombre.text.toString().trim()
            val correo=etCorreo.text.toString().trim()
            val pass=etPass.text.toString()
            if (nombre.isEmpty()||correo.isEmpty()||pass.isEmpty()){
                Toast.makeText(this,"Debes ingresar todos los campos", Toast.LENGTH_LONG).show()
                ++intentos
                } else {
                    if (correo.equals(correoValido)&&pass.equals(passValido)){
                        Toast.makeText(this, "Ingreso correcto", Toast.LENGTH_LONG).show()
                        val i= Intent(this, welcomeActivity::class.java)
                        startActivity(i)

                    } else {
                        Toast.makeText(this, "Credenciales invalidas",Toast.LENGTH_LONG).show()
                        ++intentos
                    }
            }
            if (intentos>=maxintentos){
                Toast.makeText(this,"Has intentado acceder demasiadas veces. Aplicacion bloqueada",
                    Toast.LENGTH_LONG).show()
                btnIngresar.isEnabled=false
            }
        }

    }
}