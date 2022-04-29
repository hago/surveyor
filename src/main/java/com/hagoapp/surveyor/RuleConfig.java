/*
 *  Copyright (c) 2021.
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.hagoapp.surveyor;

import java.util.List;

public class RuleConfig {
    private String configType;
    private boolean nullable;

    public String getConfigType() {
        return configType;
    }

    public int getParamCount() {
        return -1;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setConfigType(String configType) {
        this.configType = configType;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }
}
