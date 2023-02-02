### Inline Function
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
