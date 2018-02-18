package com.denwehrle.boilerplate.factory

import java.util.*
import java.util.concurrent.ThreadLocalRandom

/**
 * @author Dennis Wehrle
 */
class DataFactory {

    companion object {

        fun randomString(): String {
            return java.util.UUID.randomUUID().toString()
        }

        fun randomDouble(): Double {
            return ThreadLocalRandom.current().nextDouble(-90.0, 90.0)
        }

        fun randomInt(): Int {
            return ThreadLocalRandom.current().nextInt(0, 1000 + 1)
        }

        fun randomLong(): Long {
            return randomInt().toLong()
        }

        fun randomBoolean(): Boolean {
            return Math.random() < 0.5
        }

        fun randomDate(): Date {
            return Date()
        }

        fun randomFloat(): Float {
            return ThreadLocalRandom.current().nextFloat()
        }
    }
}