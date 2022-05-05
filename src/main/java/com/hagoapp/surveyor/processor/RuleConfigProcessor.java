/*
 *  Copyright (c) 2021.
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.hagoapp.surveyor.processor;

import com.hagoapp.surveyor.RuleConfig;

import java.util.List;

public interface RuleConfigProcessor<T> {
    String getSupportedConfigType();

    RuleConfigProcessor<T> acceptConfiguration(RuleConfig ruleConfig);

    boolean process(List<T> params);
}
