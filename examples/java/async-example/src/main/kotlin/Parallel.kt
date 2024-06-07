import kotlinx.coroutines.*
import kotlin.system.*

fun main() = runBlocking {
    val time1 = measureTimeMillis { sequential() }
    println("Sequential completed in $time1 ms")

    val time2 = measureTimeMillis { parallel() }
    println("Parallel completed in $time2 ms")
}

private suspend fun sequential() {
    val one = doSomethingUsefulOne()
    val two = doSomethingUsefulTwo()
    println("The answer is ${one + two}")
}

private suspend fun parallel() {
    coroutineScope {
        val one = async { doSomethingUsefulOne() }
        val two = async { doSomethingUsefulTwo() }
        println("The answer is ${one.await() + two.await()}")
    }
}

suspend fun doSomethingUsefulOne(): Int {
    delay(1000L) // pretend we are doing something useful here
    return 13
}

suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L) // pretend we are doing something useful here, too
    return 29
}