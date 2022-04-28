/*
 *  Copyright (c) 2021.
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.hagoapp.surveyor;

import com.google.gson.GsonBuilder;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ConfigFactory {

    private static final Map<String, Class<? extends RuleConfig>> configurations = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);

    static {
        registerRuleConfiguration(ConfigFactory.class.getPackageName());
    }

    public static void registerRuleConfiguration(String packageName) {
        var types = new Reflections(packageName, Scanners.SubTypes).getSubTypesOf(RuleConfig.class);
        for (var type : types) {
            try {
                var instance = type.getConstructor().newInstance();
                var typeName = instance.getConfigType();
                if (configurations.containsKey(typeName)) {
                    var oldClz = configurations.get(typeName);
                    logger.warn("Configuration type {} is replaced by {} for same config type name {}",
                            oldClz.getCanonicalName(), type.getCanonicalName(), typeName);
                }
                configurations.put(typeName, type);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                logger.error("Error occurs while trying to instantiate {}", type.getCanonicalName());
            }
        }
    }

    public static RuleConfig createRuleConfig(String json) {
        var gson = new GsonBuilder().create();
        var baseClass = gson.fromJson(json, RuleConfig.class);
        var typeName = baseClass.getConfigType();
        var clz = configurations.get(typeName);
        if (clz == null) {
            logger.error("no rule config class found for name: {}", typeName);
            throw new UnsupportedOperationException(
                    String.format("no rule config class found for name: %s", typeName));
        }
        return gson.fromJson(json, clz);
    }
}
