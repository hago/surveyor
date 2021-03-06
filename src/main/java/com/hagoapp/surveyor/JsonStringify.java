/*
 * Copyright (c) 2020.
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.hagoapp.surveyor;

import com.google.gson.GsonBuilder;

public interface JsonStringify {

    default String toJson() {
        return new GsonBuilder().serializeNulls().setPrettyPrinting().create().toJson(this);
    }
}
