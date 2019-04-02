/*
 * Copyright (c) 2019 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

package org.glassfish.json;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Utility class for checking of IEEE-754 standard in big numbers
 */
public class BigNumberUtil {

    // 53 bits IEEE754 double precision mantissa length
    private static final int MAX_UNSCALED_BIT_LENGTH = 53;

    // Max unscaled value Number.MAX_SAFE_INTEGER (2**53)-1
    private static final long MAX_UNSCALED_VALUE = 9007199254740991L;

    // Min unscaled value -(2**53)+1
    private static final long MIN_UNSCALED_VALUE = -9007199254740991L;

    /**
     * Checks whether the value of {@link BigDecimal} matches format IEEE-754
     *
     * @param value value which is going to be checked
     * @return true if value matches format IEEE-754
     */
    public static boolean isIEEE754(BigDecimal value) {
        int unscaledValueBitLength = value.unscaledValue().abs().bitLength();
        // Number whose bit length is more than 53 or is not in range is considered as non IEEE 754-2008 binary64 compliant
        return unscaledValueBitLength <= MAX_UNSCALED_BIT_LENGTH;
        //TODO: checks for exponent.
    }

    /**
     * Checks whether the value of {@link BigInteger} matches format IEEE-754
     *
     * @param value value which is going to be checked
     * @return true if value matches format IEEE-754
     */
    public static boolean isIEEE754(BigInteger value) {
        // Number whose bit length is less or equal to 53 is considered as non IEEE 754-2008 binary64 compliant
        return isIEEE754(value.longValue());
    }

    /**
     * Checks whether the value of {@link Long} matches format IEEE-754
     *
     * @param value value which is going to be checked
     * @return true if value matches format IEEE-754
     */
    public static boolean isIEEE754(Long value) {
        return value >= MIN_UNSCALED_VALUE && value <= MAX_UNSCALED_VALUE;
    }



}
