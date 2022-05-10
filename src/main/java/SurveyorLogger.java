/*
 *  Copyright (c) 2021.
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SurveyorLogger {
    private static final Logger logger = LoggerFactory.getLogger(SurveyorLogger.class.getPackageName());

    public static Logger getLogger() {
        return logger;
    }
}
