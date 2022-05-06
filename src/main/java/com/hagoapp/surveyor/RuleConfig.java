/*
 *  Copyright (c) 2021.
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.hagoapp.surveyor;

import java.util.List;

/**
 * The base class to define configuration for a surveyor.
 *
 * @author Chaojun Sun
 * @since 0.1
 */
public class RuleConfig {
    private String configType;
    private boolean nullable;

    public String getConfigType() {
        return configType;
    }

    public int getParamCount() {
        return -1;
    }

    /**
     * Get the subjects to be handled can be null or not. If true, a null value will result a true result for
     * <code>process</code> operation of a surveyor.
     *
     * @return true for all null values passed to surveyor will result true for  <code>process</code> operation of a
     * surveyor, otherwise false
     */
    public boolean isNullable() {
        return nullable;
    }

    public void setConfigType(String configType) {
        this.configType = configType;
    }

    /**
     * Set the subjects to be handled can be null or not. If true, a null value will result a true result for
     * <code>process</code> operation of a surveyor.
     *
     * @param nullable true for all null values passed to surveyor will result true for  <code>process</code> operation
     *                 of a surveyor, otherwise false
     */
    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }
}
