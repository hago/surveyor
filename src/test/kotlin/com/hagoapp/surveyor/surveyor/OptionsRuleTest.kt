/*
 *  Copyright (c) 2021.
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.hagoapp.surveyor.surveyor

import com.hagoapp.surveyor.Constants
import com.hagoapp.surveyor.SurveyorFactory
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import java.io.File
import java.io.FileInputStream

class OptionsRuleTest {

    companion object {
        private var baseDirectory: String = "./tests/sampleconfig"
        private val logger = Constants.getLogger()

        @BeforeAll
        private fun load() {
            val props = System.getProperties()
            if (props.containsKey(Constants.CONFIG_TEST_BASE_DIRECTORY)) {
                baseDirectory = props[Constants.CONFIG_TEST_BASE_DIRECTORY].toString()
            }
        }
    }

    private data class Case(
        val value: String?,
        val expect4Basic: Boolean,
        val expect4Nullable: Boolean,
        val expect4NullOption: Boolean,
        val expect4CaseInsensitive: Boolean
    )

    private val cases = listOf(
        Case(
            null,
            expect4Basic = false,
            expect4Nullable = false,
            expect4NullOption = true,
            expect4CaseInsensitive = false
        ),
        Case(
            "ABC",
            expect4Basic = false,
            expect4Nullable = false,
            expect4NullOption = false,
            expect4CaseInsensitive = true
        ),
        Case(
            "abc",
            expect4Basic = true,
            expect4Nullable = true,
            expect4NullOption = true,
            expect4CaseInsensitive = true
        )
    )

    private fun createSurveyor(filename: String): Surveyor {
        return FileInputStream(File(baseDirectory, filename)).use {
            SurveyorFactory.createSurveyor(it)
        }
    }

    @Test
    fun testOptionsBasic() {
        for (case in cases) {
            val surveyor = createSurveyor(Constants.OPTIONS_SAMPLE_BASIC)
            val r = surveyor.process(listOf(case.value))
            Assertions.assertEquals(case.expect4Basic, r)
        }
    }

    @Test
    fun testOptionsNullable() {
        for (case in cases) {
            val surveyor = createSurveyor(Constants.OPTIONS_SAMPLE_NULLABLE)
            val r = surveyor.process(listOf(case.value))
            Assertions.assertEquals(case.expect4Nullable, r)
        }
    }

    @Test
    fun testOptionsNullOption() {
        for (case in cases) {
            logger.debug("test $case")
            val surveyor = createSurveyor(Constants.OPTIONS_SAMPLE_NULL_OPTION)
            val r = surveyor.process(listOf(case.value))
            Assertions.assertEquals(case.expect4NullOption, r)
        }
    }

    @Test
    fun testOptionsCaseInsensitive() {
        for (case in cases) {
            val surveyor = createSurveyor(Constants.OPTIONS_SAMPLE_CASE_INSENSITIVE)
            val r = surveyor.process(listOf(case.value))
            Assertions.assertEquals(case.expect4CaseInsensitive, r)
        }
    }
}
