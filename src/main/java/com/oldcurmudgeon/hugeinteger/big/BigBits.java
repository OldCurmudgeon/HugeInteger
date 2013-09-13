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

/**
 * Bits implementation using BigIntegers.
 *
 * @author OldCurmudgeon.
 */
public class BigBits extends Bits<Big> {
  // The actual bits.
  private final Big bits;

  public BigBits(BigInteger bits) {
    this.bits = new Big(bits);
  }

  @Override
  public Sparse.Iterator<Big,BigInteger> iterator() {
    return new BigBitsIterator(bits);
  }

  @Override
  public Sparse.Iterator<Big, BigInteger> reverseIterator() {
    return iterator();
  }

  @Override
  public BigInteger length() {
    return bits.length();
  }

  class BigBitsIterator extends Bits.BitsIterator {
    private Big it;

    private BigBitsIterator(Big bits) {
      it = bits;
    }

    @Override
    protected void getNext() {
      // Return just the one Big.
      next = it;
      it = null;
    }

  }
}
