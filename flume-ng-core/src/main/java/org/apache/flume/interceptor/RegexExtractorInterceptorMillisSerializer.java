/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.flume.interceptor;

import org.apache.commons.lang.StringUtils;
import org.apache.flume.Context;
import org.apache.flume.conf.ComponentConfiguration;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.google.common.base.Preconditions;

/**
 * Serializer that converts the passed in value into milliseconds using the
 * specified formatting pattern
 */
public class RegexExtractorInterceptorMillisSerializer implements
        RegexExtractorInterceptorSerializer {

    private DateTimeFormatter formatter;

    @Override
    public void configure(Context context) {
        String pattern = context.getString("pattern");
        Preconditions.checkArgument(!StringUtils.isEmpty(pattern),
                "Must configure with a valid pattern");
        formatter = DateTimeFormat.forPattern(pattern);
    }

    @Override
    public String serialize(String value) {
        DateTime dateTime = formatter.parseDateTime(value);
        return Long.toString(dateTime.getMillis());
    }

    @Override
    public void configure(ComponentConfiguration conf) {
        // NO-OP...
    }
}
