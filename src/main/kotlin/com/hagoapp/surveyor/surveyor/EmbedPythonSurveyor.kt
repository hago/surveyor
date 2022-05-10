/*
 *  Copyright (c) 2021.
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.hagoapp.surveyor.surveyor

import com.hagoapp.surveyor.RuleConfig
import com.hagoapp.surveyor.rule.EmbedPythonRuleConfig

class EmbedPythonSurveyor : Surveyor {

    private lateinit var config: EmbedPythonRuleConfig

    override fun getSupportedConfigType(): String {
        return EmbedPythonRuleConfig.EMBED_PYTHON_RULE_CONFIG
    }

    override fun acceptConfiguration(ruleConfig: RuleConfig): Surveyor {
        if (ruleConfig !is EmbedPythonRuleConfig) {
            throw UnsupportedOperationException("Not an EmbedPythonRuleConfig but ${ruleConfig::class.java.canonicalName}")
        }
        config = ruleConfig
        return this
    }

    override fun process(params: MutableList<Any>): Boolean {
        TODO("Not yet implemented")
    }
}
