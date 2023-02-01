# Android RoadMap for Interview
Android, Java and Kotlin RoadMap

## [Java](https://github.com/goodluck3301/android-interview/tree/main/Java)
- [x] [Java vs Kotlin](https://www.youtube.com/watch?v=RPvEAcjVvBQ)
- [x] [Java Multithreading](https://github.com/goodluck3301/android-interview/tree/main/Java/MultiThreading)
- [x] [Thread Communication](https://github.com/goodluck3301/android-interview/tree/main/Java/MultiThreading)
- [x] Memory Managmend (Stack/Heap)
- [x] [Garbage Collection (Lang AM)](https://www.youtube.com/watch?v=-ehHDzjCd-s)
- [x] Extension Methods

## [Kotlin](https://github.com/goodluck3301/android-interview/tree/main/Kotin)
- [x] [Data Class](https://www.youtube.com/watch?v=L0VulZZPGbI)
- [x] [Data Class vs Class](https://www.youtube.com/watch?v=Z6xj7hta7Ac)
- [x] [Sealed Classes, Enum Classes, Sealed Interfaces](https://github.com/goodluck3301/android-interview/blob/main/Android/SealedClass/README.md#enum-class--sealed-class)
- [x] Inline Function
  - In Kotlin, the higher-order functions or lambda expressions, all stored as an object so memory allocation, for both function objects and classes, and virtual calls might introduce runtime overhead. Sometimes we can eliminate the memory overhead by inlining the lambda expression. In order to reduce the memory overhead of such higher-order functions or lambda expressions, we can use the inline keyword which ultimately requests the compiler to not allocate memory and simply copy the inlined code of that function at the calling place.
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
- [x] [Suspend Function](https://www.youtube.com/watch?v=yc_WfBp-PdE) 
- [x] Higher-Order Functions and Lambdas in Kotlin
- [x] Infix notation in Kotlin
- [x] Mastering Flow API in Kotlin
- [x] Kotlin Coroutines
- [x] [Data Structure(ex. map, hashMap, list...)](https://github.com/goodluck3301/data-structures-and-algorithms)
- [x] Garbage Collection
- [x] Extension Methods


## [Android](https://github.com/goodluck3301/android-interview/tree/main/Android)

- [x] [Android Components](https://github.com/goodluck3301/android-interview/tree/main/Android#android-components)
- [x] Core Android
- [x] Android Libraries
- [x] Android Architecture
- [x] Android Design Problem
- [x] [Android Testing (unit, ...](https://github.com/goodluck3301/android-interview/tree/main/Android#testing-android)
- [x] Android Tools And Technologies
- [x] Java and Kotlin
- [x] Kotlin Coroutines
- [x] [Multiprocess on Android](https://medium.com/@rotxed/going-multiprocess-on-android-52975ed8863c)
- [x] Kotlin Flow API
- [x] Jetpack Compose
- [x] [Profile Memory Usage](https://www.kodeco.com/books/android-debugging-by-tutorials/v1.0/chapters/10-profile-memory-usage)
- [x] [Data Structures and Algorithms](https://github.com/goodluck3301/data-structures-and-algorithms)
- [x] [MVVM](https://github.com/goodluck3301/android-interview/tree/main/Android/MVVM)


### Sites
[Amit Shekhar-Blog (Java/Kotlin/Android)](https://github.com/amitshekhariitbhu/android-interview-questions#core-android)</br>
[Java](https://www.javapedia.net/module/Java)


### Video
[Собеседование на должность Junior Android Developer. Вопросы и ответы](https://www.youtube.com/watch?v=odnGQh08b8Q)
