package com.hagoapp.surveyor.rule

import com.hagoapp.surveyor.RuleConfig

class NumberRangeRuleConfig : RuleConfig() {

    companion object {
        const val NUMBER_RANGE_CONFIG = "com.hagoapp.number.range"
    }

    val lowerBoundary: NumberBoundary? = null
    val upperBoundary: NumberBoundary? = null

    override fun getConfigType(): String {
        return NUMBER_RANGE_CONFIG
    }
}
