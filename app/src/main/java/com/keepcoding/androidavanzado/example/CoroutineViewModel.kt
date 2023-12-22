package com.keepcoding.androidavanzado.example

import android.content.res.Resources.Theme
import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoroutineViewModel: ViewModel() {


    fun lanzarCorutinaConLaunch1(){
        Log.d("HOLA", "3") // T = 0
        val job = GlobalScope.launch {
            corrutina1()
        }
        Log.d("HOLA", "4") // T = 1

    }

    fun lanzarCorutinaConLaunch2(){
        Log.d("HOLA", "3") // T = 0
        val job = GlobalScope.launch {
            val result = corrutinaConResultado()

            Log.d("HOLA", result.toString()) // T = 1
        }
        Log.d("HOLA", "4") // T = 1

    }

    fun lanzarCorutinaConLaunch3(){
        GlobalScope.launch {
            Log.d("HOLA", "3") // T = 0
            val resultadoCorrutina = withContext(Dispatchers.Default) {
                Log.d("HOLA", "4") // T = 0
                val result = corrutinaConResultado()
                return@withContext result
            }
            Log.d("HOLA", resultadoCorrutina.toString()) // 1 T = 1
        }
    }

    fun lanzarCorutinaConLaunch4(){
        GlobalScope.launch {
            Log.d("HOLA", "1") // T = 0 0
            val resultadoCorrutina1 = withContext(Dispatchers.Default) {
                Log.d("HOLA", "2") // T = 0 0 0
                Thread.sleep(1000)
                Log.d("HOLA", "3") // T = 1 1 1
                1234
            }

            val resultadoCorrutina2 = withContext(Dispatchers.Default) {
                Log.d("HOLA", "4") // T = 1 0 1
                Thread.sleep(1000)
                Log.d("HOLA", "5") // T = 1 1 2
                4321
            }
            Log.d("HOLA", resultadoCorrutina1.toString()) // 0 1 2
            Log.d("HOLA", resultadoCorrutina2.toString()) // 0 1 2
        }
    }

    fun lanzarCorutinaConLaunch5(){
        GlobalScope.launch {
            Log.d("HOLA", "1") // T = 0
            val resultadoCorrutina1 = async(Dispatchers.Default) {
                Log.d("HOLA", "2") // T =
                Thread.sleep(1000)
                Log.d("HOLA", "3") // T =
                1234
            }

            val resultadoCorrutina2 = async(Dispatchers.Default) {
                Log.d("HOLA", "4") // T =
                Thread.sleep(1000)
                Log.d("HOLA", "5") // T =
                4321
            }
            Log.d("HOLA", resultadoCorrutina1.await().toString()) // 0
            Log.d("HOLA", resultadoCorrutina2.await().toString()) // 0 1 2
        }
    }

    fun lanzarCorutinaConLaunch6(){
        GlobalScope.launch {
            Log.d("HOLA", "1") // T = 0
            val resultadoCorrutina1 = async(Dispatchers.Default) {
                Log.d("HOLA", "2") // T = 0
                Thread.sleep(1000)
                Log.d("HOLA", "3") // T = 1
                1234
            }

            val resultadoCorrutina2 = async(Dispatchers.Default) {
                Log.d("HOLA", "4") // T = 0
                Thread.sleep(2000)
                Log.d("HOLA", "5") // T = 2
                4321
            }
            Log.d("HOLA", resultadoCorrutina1.await().toString()) // T = 1
            Log.d("HOLA", resultadoCorrutina2.await().toString()) // T = 2
        }
    }

    fun lanzarCorutinaConLaunch7(){
        GlobalScope.launch {
            Log.d("HOLA", "1") // T = 0
            val resultadoCorrutina1 = async(Dispatchers.Default) {
                Log.d("HOLA", "2") // T = 0
                Thread.sleep(1000)
                Log.d("HOLA", "3") // T = 1
                1234
            }

            val resultadoCorrutina2 = async(Dispatchers.Default) {
                Log.d("HOLA", "4") // T = 0
                Thread.sleep(2000)
                Log.d("HOLA", "5") // T = 2
                4321
            }

            Log.d("HOLA", resultadoCorrutina2.await().toString()) // T = 2
            Log.d("HOLA", resultadoCorrutina1.await().toString()) // T = 2
        }
    }

    fun lanzarCorutinaConLaunch8(){
        GlobalScope.launch {
            Log.d("HOLA", "1") // T = 0
            val resultadoCorrutina1 = async(Dispatchers.Default) {
                Log.d("HOLA", "2") // T = 0
                Thread.sleep(1000)
                Log.d("HOLA", "3") // T = 1
                1234
            }

            val resultadoCorrutina2 = withContext(Dispatchers.Default) {
                Log.d("HOLA", "4") // T = 0
                Thread.sleep(2000)
                Log.d("HOLA", "5") // T = 2
                4321
            }

            Log.d("HOLA", resultadoCorrutina2.toString()) // T = 2
            Log.d("HOLA", resultadoCorrutina1.await().toString()) // T = 2
        }
    }

    fun lanzarCorutinaConLaunch9(){
        GlobalScope.launch {
            Log.d("HOLA", "1") // T = 0
            val resultadoCorrutina1 = async(Dispatchers.Default) {
                Log.d("HOLA", "2") // T = 0
                Thread.sleep(5000)
                Log.d("HOLA", "3") // T = 5
                1234
            }

            val resultadoCorrutina2 = withContext(Dispatchers.Default) {
                Log.d("HOLA", "4") // T = 0
                Thread.sleep(2000)
                Log.d("HOLA", "5") // T = 2
                4321
            }

            Log.d("HOLA", resultadoCorrutina2.toString()) // T = 2
            Log.d("HOLA", resultadoCorrutina1.await().toString()) // T = 5
        }
    }

    fun lanzarCorutinaConLaunch10(){
        GlobalScope.launch {
            Log.d("HOLA", "1") // T = 0

            val resultadoCorrutina2 = withContext(Dispatchers.Default) {
                Log.d("HOLA", "4") // T = 0
                Thread.sleep(2000)
                Log.d("HOLA", "5") // T = 2
                4321
            }

            val resultadoCorrutina1 = async(Dispatchers.Default) {
                Log.d("HOLA", "2") // T = 0 2
                Thread.sleep(5000)
                Log.d("HOLA", "3") // T = 5 7
                1234
            }

            Log.d("HOLA", resultadoCorrutina2.toString()) // T = 2 2
            Log.d("HOLA", resultadoCorrutina1.await().toString()) // T = 5 7
        }
    }

    fun lanzarCorutinaConLaunch(){
        GlobalScope.launch {
            Log.d("HOLA", "1") // T = 0

            val resultadoCorrutina1 = async(Dispatchers.Default) {
                Log.d("HOLA", "2") // T = 0
                Thread.sleep(3000)
                Log.d("HOLA", "3") // T = 3
                val resultado1 = async(Dispatchers.IO) {
                    Log.d("HOLA", "6") // T = P3 G3
                    Thread.sleep(1000)
                    Log.d("HOLA", "7") // T = P4 G4
                    1000
                }

                val resultado2 = async(Dispatchers.IO) {
                    Log.d("HOLA", "8") // T = P3 G0
                    Thread.sleep(2000)
                    Log.d("HOLA", "9") // T = P5 G2
                    2000
                }

                val resultado3 = withContext(Dispatchers.IO) {
                    Log.d("HOLA", "10") // T = P3 G0
                    Thread.sleep(3000)
                    Log.d("HOLA", "11") // T = P6 G3
                    3000
                }

                resultado1.await() + resultado2.await() + resultado3
            }

            val resultadoCorrutina2 = withContext(Dispatchers.Default) {
                Log.d("HOLA", "4") // T = 0
                launch(Dispatchers.IO) {
                    Thread.sleep(2000)
                }
                Log.d("HOLA", "5") // T = 0
                4321
            }

            Log.d("HOLA", resultadoCorrutina2.toString()) // T = 2
            Log.d("HOLA", resultadoCorrutina1.await().toString()) // T = P6 G6
        }
    }


    suspend fun corrutina1(){
        Log.d("HOLA", "1") // T = 0
        Thread.sleep(1000)
        Log.d("HOLA", "2") // T = 1
    }

    suspend fun corrutinaConResultado(): Int {
        Thread.sleep(1000)
        return 1234
    }

}
