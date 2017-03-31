# Funsets

<!-- MDTOC maxdepth:6 firsth1:1 numbering:0 flatten:0 bullets:0 updateOnSave:1 -->

[Funsets](#funsets)   
&emsp;[1. Background](#1-background)   
&emsp;[2. Create a Singleton Set](#2-create-a-singleton-set)   
&emsp;[2. Basic method implementation](#2-basic-method-implementation)   
&emsp;[3. Advanced method implementation](#3-advanced-method-implementation)   
&emsp;&emsp;[3.1 `forAll()`](#31-forall)   
&emsp;&emsp;[3.2 `exists()`](#32-exists)   
&emsp;&emsp;[3.3 `map()`](#33-map)   

<!-- /MDTOC -->

## 1. Background

This is the assignemnt of the week 2

The main purpose is to implement the infinite set.

So, how to implement the infinite set?

Since we cannot really manage the elements of the infinite set, we can only test if an element is in the set.

For instance, we can easily check if the integer is less than 0, if the integer is less than 0, then it is in the **negative integer set**

Therefore, we can use the `contains()` function as the character function of the set.

That is how the set is predefined: `type Set = Int => Boolean`

## 2. Create a Singleton Set

Before our beginning to code, let's see what is the **singleton set**.

That is easy, the singleton set is the set contains **only one element**

Since we are using the function itself as the set, how we define the set only contains one element?

Simple, we just using the given element as the standars.

A element belongs to the singleton set, only when it is equal to the given element

## 2. Basic method implementation

After having the background knowleage, we can now implement our basic set function, such as

1. `contains()`

    > Since we use the `contains()` function itself as the character funtion of the set
    > The `constains()` funcion will just call the set itself is sufficient

2. `union()`

    > The union of two sets, is a set that contains the element of **either** two set

3. `intersect()`

    > The intersect of twoe set, is a set that contain the element of **both** two set
4. `diff()`

    > The diff of two set, is a set that which element is belong to one set but not belong to the other

Those function is easy to implement just using the character funcion itself.

## 3. Advanced method implementation

With the basic method, we can now continue to implement the advanced function which will be harder to implement.

### 3.1 `forAll()`

```scala
def forAll(s: Set, p: Int => Boolean)
```

`forAll()` will test whether **all** the element of the set match the predicate.

But we cannot test all the element of the infinite set.

Therefore, we set a bound, in the assignment will be $[-1000, 1000]$.

If all the elements within the bound satisfy the predicate, we will convince that all the element in the infinite set will satisfy the pridicate.

The assignment itself provide a handy tail recursion version of the implementation.

It will be not difficult to implement the `forAll()` method.

But, something important need to be noticed is that,

the `s` and `p` is actually **the same type**.

That's said, the `p` **is also the set**.

Therefore, we can use the basic set function to do some auxiliary jobs.

### 3.2 `exists()`

```scala
def exists(s: Set, p: Int => Boolean)
```

This is the most difficult function to implement in this week.

This function is actually test whether

The set **has at least one element** that satisfy the predicate.

We could just use the auxiliary nested function just like the `forAll()`.

But the assignment needs us to use the `forAll()` to implement the `exists()`.

It seems that there is no connection between **exists** and **any**.

Let's think contrary, the negation of the original stament is:

**No element in the set satisfy the predicate**

that is,

**All the element in the set satisfy the negation of the predicate**

It now contains the **All** statement, and it makes use of the `forAll()` function

Therefore, we can implement the `exists()` as follow:

```scala
def exists(s: Set, p: Int => Boolean) = !forAll(s, (e) => !p(e))
```

> There is another thing need to be noticed:  
> What is the param of `p`? (`p` is a funciton takes `Int` and return `Boolean`)  
> Actually, the param of `p` is **the element of the set**  

But it is not simplified as possible.

As I mentioned above, the `p` has **the same type of `s`**,

therefore, we can think furtthe her:

**All the element in the set satisfy the negation of the predicate**

that is :

There is **no intersect** between `s` and `p`

and that is

**All the element of `s` is in the diff of `s` and `p`**

More clearly:

All element of `s` is in the set which contains the element in `s` but not in `p`

Finally, we come to the simplified answer:

```scala
def exists(s: Set, p: Int => Boolean) = !forAll(s, diff(s, p))
```

### 3.3 `map()`

`map()` funtion is the transform function;

which will transform the set element into another form.

Under the hood, it will create the new set which contains the transform elements

As our set is a function, the `map()` function is just return a function that check if the param satisfy the new predicate.

That is to check if the set exists(contains) the element that fits the predicate, which check the incomming param is equal to the transformed element.

Therefore, the answer comes out:

```scala
def map(s: Set, f: Int => Int) =
    x => exists(s, elem => x==f(elem))
```
