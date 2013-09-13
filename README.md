HugeInteger
===========

Huge integer library.

The idea here is to store integers as sparse arrays.

Maths can be performed by iteration across two arrays generating a new result by combining the bits.

Sparse
======

A simple interface for elements in a sparse array. There is an index, a length and a value.

Additional Iterator interfaces are also defined that allow the user to interrogate the iterator for the current index and length.

There is also a matching Iterable.

Sparse is a generic class that can be applied to any object. Both the type of the value and the type of the index and length are generic. The index and length must be Numbers.

