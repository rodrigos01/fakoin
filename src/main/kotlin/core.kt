package com.rodrigos01.fakoin

import java.lang.Exception
import kotlin.reflect.KProperty

val resolvers = HashMap<Class<*>, Resolver<*>>()

@Suppress("UNCHECKED_CAST")
fun <T> resolve(clazz: Class<T>): T {
    val resolver = resolvers[clazz] as Resolver<T>?
    return resolver?.resolve() ?: throw Exception()
}

inline fun <reified T> get(): T {
    return resolve(T::class.java)
}

interface Resolver<T> {
    fun resolve() : T
}

class Factory<T>(val create: () -> T) : Resolver<T> {
    override fun resolve(): T {
        return create()
    }
}
inline fun <reified T> factory(noinline create: () -> T) {
    resolvers[T::class.java] = Factory(create)
}

class Singleton<T>(val create: () -> T) : Resolver<T> {

    private val instance: T by lazy { create() }

    override fun resolve(): T {
        return instance
    }
}
inline fun <reified T> singleton(noinline create: () -> T) {
    resolvers[T::class.java] = Singleton(create)
}

class Injector<T>(private val clazz: Class<T>) {

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return resolve(clazz)
    }
}
inline fun <reified T> inject() = Injector(T::class.java)
