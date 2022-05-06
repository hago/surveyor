/*
 *  Copyright (c) 2021.
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.hagoapp.surveyor.rule

data class NumberBoundary(
    val value: Double,
    val inclusive: Boolean
) {
    fun lt(v: Double): Boolean {
        return if (inclusive) value <= v else value < v
    }

    fun gt(v: Double): Boolean {
        return if (inclusive) value >= v else value > v
    }
}
