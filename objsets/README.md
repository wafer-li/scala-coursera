# Objsets

<!-- MDTOC maxdepth:6 firsth1:1 numbering:0 flatten:0 bullets:0 updateOnSave:1 -->

[Objsets](#objsets)   
&emsp;[1. Background](#1-background)   
&emsp;[2. `filter()`](#2-filter)   
&emsp;[2. `union`](#2-union)   
&emsp;[3. `mostRetweeted`](#3-mostretweeted)   
&emsp;[4. `decendingByRetweeted`](#4-decendingbyretweeted)   
&emsp;[5. `GoogleVsApple`](#5-googlevsapple)   

<!-- /MDTOC -->

## 1. Background

This is the assignment of week 3.

This assignment is to implement the president binary tree.

Although there are already the implementation of `include()` and `remove()`.

There are still some functions to be implemented.


## 2. `filter()`

`filter()` is a function that return a **new set** which contain the elements of the original set which satisfy the predicate.

Therefore, we need to **traverse** the whole set to build the new set

Therefore, we need a acumulator function to assist our main `filter()` function.

There is already the `filterAcc()` function.

But something important is to think **how do we traverse the set**.

Well,

1. When we call the `filterAcc()` from `Empty`, then return the `acc` itself
2. When the `elem` satisfy the predicate, we include the element into the `acc`

And then, we need to traverse the set, but the retrun value is only one.

We can just traverse the left tree and then traverse the right tree. And then union the left and right.

But it needs the `union` funtion, which we are not implemented.

And when you use the `union` method to do the `filter`, it will likely have the bad performance.(will explain later on)

Therefore, we need to do it another way.

Let's focus on what the `acc` is, so, the `acc` is the currrent set we already built up.

And, at the leaf node of the tree, it will just return the `acc`.

Therefore, eventually, the `filterAcc` return the `acc` **itself**.

And then, we can use this property to achieve the **traversal**.

So, how to traverse the set?

We can simply say we return the `left.filterAcc`, but the `acc` itself is the `right.filterAcc`.

That's say we traverse the `left`, but we already built up the `right`.

Therefore, when the function get evaluated, it will first built up the `right` and then built up the `left`


## 2. `union`

There is the toxicity of this implementation.

While in the course vedio, the professor show the implementation of the `IntSet`.

Most of the students will use it as the model, but there is a problem of that implementation and cannot use in the assignment.

The `union` function of the `IntSet` is called as _post insert_.

> There is a [post](http://ethanp.github.io/blog/2014/05/16/scala-union-beauty/) describe this property

The main purpose of the _post insert_ is to maintain the order of the `IntSet`, which is the **binary search tree**

But we don't need the order in our `TweetSet`, actually the implementation of post insert will lead to the exponential time complexity.

It is better and needed to use another implementation.

So, why the post insert sucks?

```scala
((left union right) union that) incl elem
```

Well, in the `union` function, when do we end our traveral?

It will ends at when the **caller** of the `union` is `Empty`.

Therefore, if you want to end the traversal as soon as possible, you need to make sure the **caller** will **always decrease**.

But in the implementation above, the caller of the second `union` will **not always decrease**.

Because the `(left union right)` will **grow size** but **not decrease size**.

When we built up a new node by including a elem at the leaf node, it will traverse back.

And when it traverse back, it will be the `newTree union that`, and this call will **continue to traverse down**.

Therefore, we come up the principals of `union`:

1. The left side of `union` is either `left` or `right`
2. The left side of `union` is **not** the `union`

So, how we achieve that?

We can just simply modify the association of the call:

```scala
(left union (right union that)) incl elem
```

Or we could just use the `filterAcc`

```scala
that.filterAcc(t => true, this)
```

## 3. `mostRetweeted`

Just like the `filter`, we use the auxiliary function `mostRetweetedAcc(most: Tweet)` function to assist our implementation.

And how do we traverse the set, it is the same like the `filterAcc`:

We return the `left` but with the `acc` of `right`

## 4. `decendingByRetweeted`

With the `mostRetweeted`, it is easy to implement this method.

Just start with the `Con`, containing the `mostRetweeted`, and then remove the `mostRetweeted` and traverse the rest of the set.

## 5. `GoogleVsApple`

With all the utils, it is not hard to implement the `GoogleVsApple`.

It is just the hint:

We want the tweets which containing at least one of the keyword of the `List`.

It is just that :

We want the tweets that the `List` **exists** a keyword, that is contained by the tweet.
