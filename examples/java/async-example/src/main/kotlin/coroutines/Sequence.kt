package coroutines

val fibonacciSeq: Sequence<Int> = sequence {
    yield(1)
    yield(1)
    var a = 1; var b = 1

    while (true) {
        val result = a + b
        yield(result)
        a = b; b = result
    }
}

fun main() = fibonacciSeq.take(10).forEach { print("$it ") }