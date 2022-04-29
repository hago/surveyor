/*
 *  Copyright (c) 2021.
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.hagoapp.surveyor.processor;

import com.hagoapp.surveyor.RuleConfig;
import com.hagoapp.surveyor.rule.RegexRuleConfig;

import java.util.regex.Pattern;

public class RegexRuleProcessor implements RuleConfigProcessor<String> {

    private static final RegexRuleConfig configInstance = new RegexRuleConfig();

    private RegexRuleConfig config;
    private Pattern pattern;

    @Override
    public String getSupportedConfigType() {
        return configInstance.getConfigType();
    }

    @Override
    public RuleConfigProcessor<String> acceptConfiguration(RuleConfig ruleConfig) {
        if (!(ruleConfig instanceof RegexRuleConfig)) {
            throw new IllegalArgumentException("Not a regex rule config");
        }
        config = (RegexRuleConfig) ruleConfig;
        pattern = Pattern.compile(config.getPattern());
        return this;
    }

    @Override
    public boolean process(String... params) {
        if ((params == null) || (params.length == 0)) {
            if (config.isNullable()) {
                return true;
            }
            throw new IllegalArgumentException("input must not be null");
        }
        var m = pattern.matcher(params[0]);
        return m.matches();
    }
}
