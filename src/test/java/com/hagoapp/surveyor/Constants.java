/*
 *  Copyright (c) 2021.
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.hagoapp.surveyor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Constants {
    private static final Logger logger = LoggerFactory.getLogger(Constants.class);

    public static Logger getLogger() {
        return logger;
    }

    public static final String TEST_BASE_DIRECTORY = "test.dir";
    public static final String CONFIG_TEST_BASE_DIRECTORY = "test.config.dir";
}