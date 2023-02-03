- ## Extension Function
Kotlin provides the ability to extend a class or an interface with new functionality without having to inherit from the class or use design patterns such as Decorator. This is done via special declarations called extensions.
</br>
For example, you can write new functions for a class or an interface from a third-party library that you can't modify. Such functions can be called in the usual way, as if they were methods of the original class. This mechanism is called an extension function. There are also extension properties that let you define new properties for existing classes.
</br></br>
To declare an extension function, prefix its name with a receiver type, which refers to the type being extended. The following adds a ```swap``` function to ```MutableList<Int>```:
```kt
fun MutableList<Int>.swap(index1: Int, index2: Int) {
    val tmp = this[index1] // 'this' corresponds to the list
    this[index1] = this[index2]
    this[index2] = tmp
}
```
The ```this``` keyword inside an extension function corresponds to the receiver object (the one that is passed before the dot). Now, you can call such a function on any ```MutableList<Int>```:

```kt
val list = mutableListOf(1, 2, 3)
list.swap(0, 2) // 'this' inside 'swap()' will hold the value of 'list'
```
This function makes sense for any ```MutableList<T>```, and you can make it generic:

```kt
fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
    val tmp = this[index1] // 'this' corresponds to the list
    this[index1] = this[index2]
    this[index2] = tmp
}
```

You need to declare the generic type parameter before the function name to make it available in the receiver type expression. For more information about generics, see [generic functions](https://kotlinlang.org/docs/generics.html).




- ## Inline Function
In Kotlin, the higher-order functions or lambda expressions, all stored as an object so memory allocation, for both function objects and classes, and virtual calls might introduce runtime overhead. Sometimes we can eliminate the memory overhead by inlining the lambda expression. In order to reduce the memory overhead of such higher-order functions or lambda expressions, we can use the inline keyword which ultimately requests the compiler to not allocate memory and simply copy the inlined code of that function at the calling place.

```kt
  fun main(args: Array<String>) {  
      inlineFunction({ println("calling inline functions")})  
  }  
  
  inline fun inlineFunction(myFun: () -> Unit ) {  
    myFun()  
      print("code inside inline function")  
  }  
```
   
```kt
   fun main(args: Array<String>){
      println("Main function starts")
      inlinedFunc({ println("Lambda expression 1")
      return },      // inlined function allow return
                   // statement in lambda expression
                   // so, does not give compile time error
 
      { println("Lambda expression 2")} )
 
      println("Main function ends")
    }
    
    // inlined function
    inline fun inlinedFunc( lmbd1: () -> Unit, lmbd2: () -> Unit  ) { 
      lmbd1()
      lmbd2()
    }
```
