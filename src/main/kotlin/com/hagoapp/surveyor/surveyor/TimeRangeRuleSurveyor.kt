package com.hagoapp.surveyor.surveyor

import com.hagoapp.surveyor.RuleConfig
import com.hagoapp.surveyor.rule.TimeRangeRuleConfig

class TimeRangeRuleSurveyor : Surveyor {

    private lateinit var config: TimeRangeRuleConfig

    override fun getSupportedConfigType(): String {
        return TimeRangeRuleConfig.TIME_RANGE_CONFIG
    }

    override fun acceptConfiguration(ruleConfig: RuleConfig): Surveyor {
        if (ruleConfig !is TimeRangeRuleConfig) {
            throw UnsupportedOperationException("A TimeRangeRuleConfig is expected while ${config::class.java} is given")
        }
        config = ruleConfig
        return this
    }

    override fun process(params: MutableList<Any>): Boolean {
        TODO("Not yet implemented")
    }
}
