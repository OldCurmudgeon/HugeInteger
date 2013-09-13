/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oldcurmudgeon.hugeinteger.big;

import java.math.BigInteger;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Paul Caswell
 */
public class BigTest {
  static final BigInteger bigA = new BigInteger(new byte[]{1, 0, 1, 1, 0, 0, 1});
  static final Big a = new Big(BigInteger.ZERO, bigA);
  static final BigInteger bigB = new BigInteger(new byte[]{1, 0, 1, 1, 0, 0, 1, 0, 0, 0});
  static final Big b = new Big(BigInteger.valueOf(64), bigB);
  static final Big[] c = new Big[]{
    new Big(BigInteger.valueOf(0), bigA),
    new Big(BigInteger.valueOf(1), bigA),
    new Big(BigInteger.valueOf(2), bigA),
    new Big(BigInteger.valueOf(3), bigA),
    new Big(BigInteger.valueOf(4), bigA),
    new Big(BigInteger.valueOf(5), bigA),
    new Big(BigInteger.valueOf(6), bigA),
    new Big(BigInteger.valueOf(7), bigA),
    new Big(BigInteger.valueOf(8), bigA),};

  public BigTest() {
  }

  @BeforeClass
  public static void setUpClass() {
    System.out.println("bigA = " + bigA.toString(2));
    System.out.println("bigB = " + bigB.toString(2));
    System.out.println("a = " + a);
    System.out.println("b = " + b);
    for ( int i = 0; i < c.length; i++ ) {
      System.out.println("c["+i+"] = " + c[i]+" index="+c[i].index()+" length="+c[i].length()+" value="+c[i].value());
    }
  }

  /**
   * Test of index method, of class Big.
   */
  @Test
  public void testIndex() {
    for ( int i = 0; i < c.length; i++ ) {
      assertEquals("c["+i+"].index()", c[i].index(), BigInteger.valueOf((i/8)*8));
    }
  }

  /**
   * Test of length method, of class Big.
   */
  @Test
  public void testLength() {
    for ( int i = 0; i < c.length; i++ ) {
      assertEquals("c["+i+"].length()", c[i].length(), BigInteger.valueOf(bigA.bitLength()+(i%8)));
    }
  }

  /**
   * Test of value method, of class Big.
   */
  @Test
  public void testValue() {
    for ( int i = 0; i < c.length; i++ ) {
      assertEquals("c["+i+"].value()", c[i].value().shiftLeft(c[i].index().intValue()), bigA.shiftLeft(i));
    }
  }

  /**
   * Test of equals method, of class Big.
   */
  @Test
  public void testEquals() {
    for ( int i = 0; i < c.length; i++ ) {
      assertTrue("c["+i+"].equals()",c[i].equals(new Big(c[i])));
    }
  }

}