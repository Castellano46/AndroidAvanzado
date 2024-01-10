package com.keepcoding.androidavanzado.example

import android.util.Log

interface Animal {
    fun alimentarse()
    fun paseo()

}

interface Volador{

    fun volar()
}

class Perro(): Animal{
    override fun alimentarse() {
        Log.d("HOLA", "El pájaro está comiendo")
    }

    override fun paseo(){

    }


}

class Caballo(): Animal{
    override fun alimentarse() {
        Log.d("HOLA", "El pájaro está comiendo")
    }

    override fun paseo(){

    }

}

class Pajaro(): Animal, Volador{
    override fun alimentarse() {
        Log.d("HOLA", "El pájaro está comiendo")

    }

    override fun paseo() {
        TODO("Not yet implemented")
    }

    override fun volar() {
        Log.d("HOLA", "El pájaro está volando")
    }

}

fun irDePaseo(perro: Perro){
    Log.d("HOLA", "Paseo con ${perro.paseo()}")
}

fun irDePaseo(caballo: Caballo){
    Log.d("HOLA", "Paseo con ${caballo.paseo()}")
}

fun irDePaseo(animal: Animal){
    Log.d("HOLA", "Paseo con ${animal.paseo()}")

}

fun main() {
    val caballo = Caballo()
    irDePaseo(caballo)
}
