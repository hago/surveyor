/*
 *  Copyright (c) 2021.
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.hagoapp.surveyor.rule;

import com.hagoapp.surveyor.RuleConfig;

public class RegexRuleConfig extends RuleConfig {
    public static final String REGEX_RULE_CONFIG_TYPE = "com.hagoapp.regex";
    private String pattern;
    private boolean caseSensitive;

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public boolean isCaseSensitive() {
        return caseSensitive;
    }

    public void setCaseSensitive(boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }

    @Override
    public int getParamCount() {
        return 1;
    }

    @Override
    public String getConfigType() {
        return REGEX_RULE_CONFIG_TYPE;
    }
}
