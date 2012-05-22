The IFn protocol provides the invoke function, which is implemented by all applicative forms in Clojure. The invokation of a function transforms an argument into a return value. Clojure does not provide any standard means of uninvoking a function, to get the arguments it was called on from its return value. 

The uninvokable protocol fills this role by providing an uninvoke function to implementations. The bijection record implements both invoke and uninvoke, so it can be called forwards and backwards. One simple type of bijection is the involution, which is a bijection that is the product of disjoint transpositions:

```clj
(bijection. - -)
(bijection. reverse reverse)
```

Function graphs that do not intersect horizontally are all bijections, so all non-horizontal lines are bijections. The file lines.clj provides an effective implementation of all lines functions:

```clj
(def inc (line 1 1))
(def double (line 2 0))
```

The set of all lines under composition forms a group. This is described by the compose-lines function. The group of all real numbers under addition and the group of all non zero real numbers under multiplication are both subgroups of the lines group. In general lines characterize all field operations except for multiplication by zero, which is the single irreversible field operation.

```clj
(= (compose-lines) (line 1 0)) 
```

Groups are defined by the automorphisms of an object, because automorphisms are the only type of bijection that is composable. The automorphisms of a set are permutations, which include the reverse involution.