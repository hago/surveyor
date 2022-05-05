/*
 *  Copyright (c) 2021.
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.hagoapp.surveyor

import com.hagoapp.surveyor.processor.RegexRuleProcessor
import com.hagoapp.surveyor.rule.RegexRuleConfig
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import java.io.File
import java.io.FileInputStream

class SurveyorFactoryTest {

    companion object {
        private var baseDirectory: String = "./tests/sampleconfig"

        @BeforeAll
        fun init() {
            val props = System.getProperties()
            if (props.containsKey(Constants.CONFIG_TEST_BASE_DIRECTORY)) {
                baseDirectory = props[Constants.CONFIG_TEST_BASE_DIRECTORY].toString()
            }
        }
    }

    private val logger = Constants.getLogger()
    private val cases = listOf(
        Triple("regexrule.json", RegexRuleConfig::class.java, RegexRuleProcessor::class.java)
    )

    @Test
    fun testLoadProcessor() {
        for (case in cases) {
            val f = File(baseDirectory, case.first)
            FileInputStream(f).use {
                val cfg = SurveyorFactory.createRuleConfig(it)
                Assertions.assertEquals(case.second, cfg::class.java)
                val processor = SurveyorFactory.createRuleProcessor(cfg)
                Assertions.assertEquals(case.third, processor::class.java)
            }
        }
    }
}
