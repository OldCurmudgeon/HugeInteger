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

/**
 * Something that has an index, a length and a value.
 *
 * We can then string these together leaving gaps between
 * them to allow for sparse functions to perform.
 *
 * Big implements Sparse<BigInteger,BigInteger> i.e. it is
 * a BigInteger with BigInteger offset and length.
 *
 * Length will rarely get bigger than int. I use I here because
 * that makes the maths much easier at little cost. Remember
 * that it returns the BIT length.
 *
 * We can also create a SparseIterable and a SparseIterator for
 * anything implementing Sparse.
 *
 * @author OldCurmudgeon.
 */
public interface Sparse<T, I extends Number> {
  
  /**
   * Gives the bit index.
   * 
   * @return The index of the lowest bit.
   */
  public I index();

  /**
   * Gives the bit length.
   * 
   * @return The number of bits in this number.
   */
  public I length();

  /**
   * Gives the value.
   * 
   * @return The value at this bit position.
   */
  public T value();

  /**
   * Defines a sparse stream of bits to perform maths over.
   *
   * It should be possible to step through two streams of bits
   * at once and do the maths on them.
   *
   * Underneath, it should be possible to ask the stream to skip
   * uninteresting sequences of bits such as all zeros.
   *
   * Going forward I hope to perform the actual math using lambdas
   * and closures but for now we will merely iterate.
   *
   * T is the type of each part. I is the type of the index.
   */
  public interface Iterator<T, I extends Number> extends java.util.Iterator<T> {
    /**
     * Return the index of the current value,
     * or that of the first value if it has not
     * as yet been collected.
     */
    public I index();

    /**
     * Return the length of the current value,
     * or that of the first value if it has not
     * as yet been collected.
     */
    public I length();

  }

  /**
   * An Iterable across sparse items.
   */
  public interface Iterable<T extends Sparse<?, I>, I extends Number> extends java.lang.Iterable<T> {
  }
}
