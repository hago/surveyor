/*
 *  Copyright (c) 2021.
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.hagoapp.surveyor

import com.hagoapp.surveyor.processor.NumberRangeRuleProcessor
import com.hagoapp.surveyor.processor.RegexRuleProcessor
import com.hagoapp.surveyor.rule.NumberBoundary
import com.hagoapp.surveyor.rule.NumberRangeRuleConfig
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
        Triple("regexrule.json", RegexRuleConfig::class.java, RegexRuleProcessor::class.java),
        Triple(
            "numberrangerule_null.json", NumberRangeRuleConfig::class.java,
            NumberRangeRuleProcessor::class.java
        ),
        Triple(
            "numberrangerule_lower.json", NumberRangeRuleConfig::class.java,
            NumberRangeRuleProcessor::class.java
        ),
        Triple(
            "numberrangerule_upper.json", NumberRangeRuleConfig::class.java,
            NumberRangeRuleProcessor::class.java
        ),
        Triple(
            "numberrangerule_both.json", NumberRangeRuleConfig::class.java,
            NumberRangeRuleProcessor::class.java
        )
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

    private val numberRangeCases = listOf(
        Triple("numberrangerule_null.json", null, null),
        Triple("numberrangerule_lower.json", NumberBoundary(-2.0, false), null),
        Triple("numberrangerule_upper.json", null, NumberBoundary(99.123, true)),
        Triple(
            "numberrangerule_both.json",
            NumberBoundary(-2.0, true),
            NumberBoundary(99.123, true)
        )
    )

    @Test
    fun testNumberRangeConfig() {
        for (case in numberRangeCases) {
            logger.debug("test $case")
            val f = File(baseDirectory, case.first)
            FileInputStream(f).use {
                val cfg = SurveyorFactory.createRuleConfig(it)
                Assertions.assertTrue(cfg is NumberRangeRuleConfig)
                val nc = cfg as NumberRangeRuleConfig
                Assertions.assertEquals(case.second, nc.lowerBoundary)
                Assertions.assertEquals(case.third, nc.upperBoundary)
            }
        }
    }
}
