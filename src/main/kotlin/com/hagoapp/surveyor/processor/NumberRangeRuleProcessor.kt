package com.hagoapp.surveyor.processor

import com.hagoapp.surveyor.RuleConfig
import com.hagoapp.surveyor.rule.NumberRangeRuleConfig

class NumberRangeRuleProcessor : RuleConfigProcessor<Double> {

    private lateinit var config: NumberRangeRuleConfig

    override fun getSupportedConfigType(): String {
        return NumberRangeRuleConfig.NUMBER_RANGE_CONFIG
    }

    override fun acceptConfiguration(ruleConfig: RuleConfig): RuleConfigProcessor<Double> {
        if (ruleConfig !is NumberRangeRuleConfig) {
            throw UnsupportedOperationException("Not a number range config")
        }
        config = ruleConfig
        return this
    }

    override fun process(params: MutableList<Double>): Boolean {
        val p = params.toList()
        return when {
            p.isEmpty() -> true
            config.lowerBoundary == null && config.upperBoundary == null -> true
            config.lowerBoundary == null -> p.all { config.upperBoundary!!.gt(it) }
            config.upperBoundary == null -> p.all { config.lowerBoundary!!.lt(it) }
            else -> p.all { config.lowerBoundary!!.lt(it) && config.upperBoundary!!.gt(it) }
        }
    }
}
