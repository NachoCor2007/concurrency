import java.lang.Thread.sleep
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Future
import java.util.concurrent.atomic.AtomicInteger

var coffeeNumber = AtomicInteger(0)

fun main() {
    println("=== Synchronous Coffee Break ===")
    for (i in 1..3) coffeeBreak()

    println("=== Asynchronous Coffee Break ===")
    for (i in 1..3) asyncCoffeeBreak()
    sleep(2000)

    println("=== Asynchronous Coffee Break with Futures ===")
    for (i in 1..3) futureCoffeeBreak()
    sleep(2000)

    println("=== Asynchronous Coffee Break with Futures & Accept===")
    for (i in 1..3) futureCoffeeBreak2()
    sleep(2000)

    println("=== Asynchronous Coffee Break Combining Futures  ===")
    for (i in 1..3) futureCoffeeBreakBlended()
}

fun coffeeBreak() {
    val coffee = makeCoffee()
    drink(coffee)
    chatWithColleagues()
}

fun chatWithColleagues() {
    println("Bla bla bla...")
    sleep(1000)
}

fun drink(coffee: String) {
    println("Drinking $coffee")
    sleep(1000)
}

fun makeCoffee(): String {
    println("Making Coffee")
    sleep(1000)
    return "coffee ${coffeeNumber.incrementAndGet()}"
}


fun makeCoffeeAsync(coffeeDone: (String) -> Unit) {
    val t = Thread {
        println("Making Coffee")
        sleep(2000)
        coffeeDone("coffee ${coffeeNumber.incrementAndGet()}")
    }
    t.start()
}

fun asyncCoffeeBreak() {
    makeCoffeeAsync { coffee ->
        drink(coffee)
    }
    chatWithColleagues()
}

fun futureCoffeeBreak() {
    val f: Future<String> = makeCoffeeFuture()
    chatWithColleagues()
    drink(f.get())
}

fun makeCoffeeFuture(): CompletableFuture<String> {
    return CompletableFuture.supplyAsync {
        println("Making Coffee")
        sleep(2000)
        "coffee ${coffeeNumber.incrementAndGet()}"
    }
}

fun futureCoffeeBreak2() {
    val f = makeCoffeeFuture()
    f.thenAccept { coffee ->
        drink(coffee)
    }
    .handle { _, exception ->
        println("Failed with $exception")
    }
    chatWithColleagues()
}

fun futureCoffeeBreakBlended() {
    val f1 = makeCoffeeFuture()
    val f2 = makeCoffeeFuture()
    val combinedFuture = f1.thenCombine(f2) { result1, result2 ->
        "$result1 blended with $result2"
    }
    combinedFuture.thenAccept { c ->
        drink(c)
    }
    chatWithColleagues()
}