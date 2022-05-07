/*
 *  Copyright (c) 2021.
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.hagoapp.surveyor

import com.hagoapp.surveyor.surveyor.NumberRangeRuleSurveyor
import com.hagoapp.surveyor.surveyor.RegexRuleSurveyor
import com.hagoapp.surveyor.rule.NumberBoundary
import com.hagoapp.surveyor.rule.NumberRangeRuleConfig
import com.hagoapp.surveyor.rule.OptionsRuleConfig
import com.hagoapp.surveyor.rule.RegexRuleConfig
import com.hagoapp.surveyor.surveyor.OptionsRuleSurveyor
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
        Triple("regexrule.json", RegexRuleConfig::class.java, RegexRuleSurveyor::class.java),
        Triple(
            Constants.NUMBER_RANGE_SAMPLE_CONFIG_EMPTY, NumberRangeRuleConfig::class.java,
            NumberRangeRuleSurveyor::class.java
        ),
        Triple(
            Constants.NUMBER_RANGE_SAMPLE_CONFIG_LOWER, NumberRangeRuleConfig::class.java,
            NumberRangeRuleSurveyor::class.java
        ),
        Triple(
            Constants.NUMBER_RANGE_SAMPLE_CONFIG_UPPER, NumberRangeRuleConfig::class.java,
            NumberRangeRuleSurveyor::class.java
        ),
        Triple(
            Constants.NUMBER_RANGE_SAMPLE_CONFIG_BOTH, NumberRangeRuleConfig::class.java,
            NumberRangeRuleSurveyor::class.java
        ),
        Triple(
            Constants.OPTIONS_SAMPLE_BASIC, OptionsRuleConfig::class.java,
            OptionsRuleSurveyor::class.java
        ),
        Triple(
            Constants.OPTIONS_SAMPLE_NULLABLE, OptionsRuleConfig::class.java,
            OptionsRuleSurveyor::class.java
        ),
        Triple(
            Constants.OPTIONS_SAMPLE_NULL_OPTION, OptionsRuleConfig::class.java,
            OptionsRuleSurveyor::class.java
        ),
        Triple(
            Constants.OPTIONS_SAMPLE_CASE_INSENSITIVE, OptionsRuleConfig::class.java,
            OptionsRuleSurveyor::class.java
        )
    )

    @Test
    fun testLoadProcessor() {
        for (case in cases) {
            val f = File(baseDirectory, case.first)
            FileInputStream(f).use {
                val cfg = SurveyorFactory.createRuleConfig(it)
                Assertions.assertEquals(case.second, cfg::class.java)
                val processor = SurveyorFactory.createSurveyor(cfg)
                Assertions.assertEquals(case.third, processor::class.java)
            }
        }
    }

    private val numberRangeCases = listOf(
        Triple(Constants.NUMBER_RANGE_SAMPLE_CONFIG_EMPTY, null, null),
        Triple(Constants.NUMBER_RANGE_SAMPLE_CONFIG_LOWER, NumberBoundary(-2.0, false), null),
        Triple(Constants.NUMBER_RANGE_SAMPLE_CONFIG_UPPER, null, NumberBoundary(99.123, true)),
        Triple(
            Constants.NUMBER_RANGE_SAMPLE_CONFIG_BOTH,
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
