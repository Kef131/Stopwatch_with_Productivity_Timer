class MyStack<T>(data: List<T>) {
    val items = data.toMutableList()
    fun push(data: T) = items.add(data)
    fun pop(): T = items.removeLast()
}