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

class EmbedJythonTest {

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

    private data class Case(
        val variableNames: List<String>,
        val variables: List<Any?>,
        val expected: Boolean
    )

    private val cases = mapOf(
        Constants.EMBED_JYTHON_SAMPLE_BASIC to listOf(
            Case(
                listOf("input"),
                listOf(-1),
                false
            ),
            Case(
                listOf("input"),
                listOf(0),
                true
            )
        )
    )

    private fun createSurveyor(configFile: String): Surveyor {
        FileInputStream(File(baseDirectory, configFile)).use {
            return SurveyorFactory.createSurveyor(it)
        }
    }

    @Test
    fun testJythonScript() {
        for ((configFile, jythonCases) in cases) {
            createSurveyor(configFile).use {
                createSurveyor(configFile).use { surveyor ->
                    for (case in jythonCases) {
                        val l = mutableListOf<Any?>()
                        for (i in case.variableNames.indices) {
                            l.add(case.variableNames[i])
                            l.add(case.variables[i])
                        }
                        val ret = surveyor.process(l)
                        Assertions.assertEquals(case.expected, ret)
                    }
                }
            }
        }
    }
}
