import java.lang.ArithmeticException
import kotlin.Exception

fun calculateBrakingDistance(v1: String, a: String): Int {
    return try {
        val brakingDistance = -1 * v1.toInt() * v1.toInt() / (2 * a.toInt())
        brakingDistance
    } catch (e: ArithmeticException) {
        println("The car does not slow down!")
        -1
    } catch (e: Exception) {
        println(e.message)
        -1
    }
}