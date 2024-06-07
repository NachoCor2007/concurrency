package coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    for (i in 0..50_000) { // launch a lot of coroutines
        launch {
            println("Hello $i!")
            delay(5000L)
        }
    }
}
