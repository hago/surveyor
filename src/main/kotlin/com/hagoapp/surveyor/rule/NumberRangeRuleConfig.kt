package com.hagoapp.surveyor.rule

import com.hagoapp.surveyor.RuleConfig

class NumberRangeRuleConfig : RuleConfig() {

    companion object {
        const val NUMBER_RANGE_CONFIG = "com.hagoapp.number.range"
    }

    data class NumberBoundary(
        val value: Double,
        val inclusive: Boolean
    ) {
        fun lt(v: Double): Boolean {
            return if (inclusive) value <= v else value < v
        }

        fun gt(v: Double): Boolean {
            return if (inclusive) value >= v else value > v
        }
    }

    val lowerBoundary: NumberBoundary? = null
    val upperBoundary: NumberBoundary? = null

    override fun getConfigType(): String {
        return NUMBER_RANGE_CONFIG
    }
}
