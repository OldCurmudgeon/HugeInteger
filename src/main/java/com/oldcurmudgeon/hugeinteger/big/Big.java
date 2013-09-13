/*
 * Copyright 2013 OldCurmudgeon.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.oldcurmudgeon.hugeinteger.big;

import java.math.BigInteger;
import java.util.Objects;

/**
 * A packet to define a section of bits that fit into a BigInteger.
 *
 * Both the object and the index are big.
 *
 * Implements Sparse but remember that the index (and length)
 * are in bits.
 *
 * @author OldCurmudgeon.
 */
public class Big implements Sparse<BigInteger, BigInteger> {
  // The granuality.
  private static final int G = 8;
  private static final BigInteger BG = BigInteger.valueOf(G);
  // A zero for me.
  public static final Big ZERO = new Big(BigInteger.ZERO);
  // My sparse value.
  private final BigInteger index;
  private final BigInteger value;

  public Big(BigInteger index, BigInteger value) {
    // Ensure equals works.
    // Shift to get the lowest bit at 0.
    BigInteger shift = index.add(BigInteger.valueOf(value.getLowestSetBit()));
    // Record index and value.
    // Make index a multiple of 8.
    this.index = shift.divide(BG).multiply(BG);
    this.value = value.shiftLeft(index.subtract(this.index).intValue());
  }

  public Big(long index, BigInteger value) {
    this(BigInteger.valueOf(index), value);
  }

  public Big(long index, long value) {
    this(BigInteger.valueOf(index), BigInteger.valueOf(value));
  }

  public Big(BigInteger value) {
    this(BigInteger.ZERO, value);
  }

  public Big(long value) {
    this(BigInteger.valueOf(value));
  }

  @Override
  public BigInteger index() {
    return index;
  }

  @Override
  public BigInteger length() {
    return BigInteger.valueOf(value.bitLength());
  }

  @Override
  public BigInteger value() {
    return value;
  }

  @Override
  public String toString() {
    return toString(2);
  }

  public String toString(int base) {
    return ("[" + index.toString() + "]:" + value.toString(base));
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Big) {
      Big it = (Big) o;
      /* 
       * All Bigs are forced to have the bottom byte non-zero so if both index and value are the same
       * then the number is the same.
       */
      return it.index == index
              && it.value == value;
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 17 * hash + Objects.hashCode(this.index);
    hash = 17 * hash + Objects.hashCode(this.value);
    return hash;
  }

  public static void main(String[] args) {
    BigInteger bigA = new BigInteger(new byte[]{1, 0, 1, 1, 0, 0, 0, 1});
    Big a = new Big(BigInteger.ZERO, bigA);
    System.out.println("bigA = " + bigA.toString(2) + " a = " + a);
    BigInteger bigB = new BigInteger(new byte[]{1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0});
    Big b = new Big(BigInteger.valueOf(64), bigB);
    System.out.println("bigB = " + bigB.toString(2) + " b = " + b);
  }

}
