package com.hagoapp.surveyor.rule

import com.hagoapp.surveyor.RuleConfig

/**
 * This class defines configuration for a surveyor that check whether value is in a give list of options.
 *
 * @property options    available options
 * @author  Chaojun Sun
 * @since   0.1
 */
class OptionsRuleConfig : RuleConfig() {

    companion object {
        const val OPTIONS_RULE_CONFIG = "com.hagoapp.options"
    }

    var options = mutableListOf<String>()

    override fun getConfigType(): String {
        return OPTIONS_RULE_CONFIG
    }
}
