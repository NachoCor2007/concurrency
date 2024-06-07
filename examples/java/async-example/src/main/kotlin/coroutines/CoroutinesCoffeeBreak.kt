package coroutines

import kotlinx.coroutines.*
import org.example.chatWithColleagues
import org.example.drink
import java.util.concurrent.atomic.AtomicInteger

val coffeeNumber = AtomicInteger(0)

suspend fun makeCoffeeCoroutine(): String {
    println("Making Coffee")
    delay(2000)  // Using delay from kotlinx.coroutines instead of Thread.sleep
    return "coffee ${coffeeNumber.incrementAndGet()}"
}

fun coffeeBreakWithCoroutines() = runBlocking {
    launch {
        val coffee = makeCoffeeCoroutine()
        drink(coffee)

    }
    chatWithColleagues()
}

fun main() {
    for (i in 1..3)
        coffeeBreakWithCoroutines()

}