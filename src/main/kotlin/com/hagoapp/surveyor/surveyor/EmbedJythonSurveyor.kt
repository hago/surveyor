/*
 *  Copyright (c) 2021.
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.hagoapp.surveyor.surveyor

import com.hagoapp.surveyor.RuleConfig
import com.hagoapp.surveyor.rule.EmbedJythonRuleConfig

class EmbedJythonSurveyor : Surveyor {

    private lateinit var config: EmbedJythonRuleConfig

    override fun getSupportedConfigType(): String {
        return EmbedJythonRuleConfig.EMBED_PYTHON_RULE_CONFIG
    }

    override fun acceptConfiguration(ruleConfig: RuleConfig): Surveyor {
        if (ruleConfig !is EmbedJythonRuleConfig) {
            throw UnsupportedOperationException("Not an EmbedPythonRuleConfig but ${ruleConfig::class.java.canonicalName}")
        }
        config = ruleConfig
        return this
    }

    override fun process(params: MutableList<Any>): Boolean {
        TODO("Not yet implemented")
    }
}
