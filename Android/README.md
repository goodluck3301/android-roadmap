# Android

- ### Android Components 
In Android, a component is a fundamental building block of an Android app. The four main components in Android are:

Activities: An activity represents a single screen in an app. It is responsible for handling user interactions, displaying data, and performing other tasks.

Services: A service is a background task that runs independently of the app's UI. It can perform tasks such as playing music, downloading data, or running network operations.

Broadcast Receivers: A broadcast receiver is a component that listens for and responds to system-wide broadcast announcements. For example, a broadcast receiver can be used to handle incoming SMS messages or listen for changes in the device's battery level.

Content Providers: A content provider is a component that allows different apps to share data with one another. It acts as a bridge between an app's data and the rest of the system.

All these components are defined in the AndroidManifest.xml file. They can be triggered by Intents, which are objects that describe the action to be performed. A developer can use these components in any combination to create a functional application.

- ## [Kotlin Coroutine (Scope Difference: CoroutineScope, GlobalScope, etc.)](https://medium.com/@pramahalqavi/several-types-of-kotlin-coroutine-scope-difference-coroutinescope-globalscope-etc-9f086cd40173)

Coroutine is one of tools in Android development to achieve asynchronous code execution. As we know asynchronous or non-blocking programming is pretty important part for the development. In order to run a coroutine we need to run it in a scope called ```CoroutineScope```. This ```CoroutineScope``` help us to track running coroutine, cancel the unused coroutine to avoid memory leak. There are several scope other than ```CoroutineScope``` that we want to talk about.</br>

### CoroutineScope</br>

Suppose we want to run an asynchronous long running task. If the task goes well then it will finish and terminated as expected. But if the task somehow doesn’t go well, keep running for a long time and it’s still running when the user doesn’t intend to use it anymore then we need to stop it at some point because we don’t want the task to waste user’s cpu and memory resource. In coroutine we can avoid this problem by keep track the task and limit its lifetime using CoroutineScope. By tracking running coroutine using CoroutineScope we can cancel the task when we don’t need it to be running anymore.

```kt
class ExampleActivity: AppCompatActivity() {
  //...
  private lateinit var mCoroutineScope: CoroutineScope
  //...
  private fun coroutineTest() {
      mCoroutineScope = CoroutineScope(Dispatchers.Main)
      mCoroutineScope.launch {
        println("loading..")
        delay(3000)
        println("job is done")
      }
  }
  override fun onDestroy() {
    super.onDestroy()
    if(::mCoroutineScope.isInitialized && mCoroutineScope.isActive){                    
      mCoroutineScope.cancel() 
    }
}
```

We define a variable called ```mCoroutineScope``` inside class and we define it with whatever ```CoroutineContext``` we want when we want to launch a coroutine in the scope, in this case we use ```Dispatchers.Main```. Then when we want to cancel the coroutine inside the scope we can simply call ```mCoroutine.cancel()```. On the code snippet above, we will cancel running coroutine inside ```mCoroutineScope``` when activity is destroyed.

What if we want to cancel only some coroutines and retain some other coroutines inside the scope? We can define Job using launch and cancel it whenever we want. Job is actually coroutine itself. Coroutine is represented by a Job. Every time you call launch you will return a Job instance.</br>

What if we want to cancel only some coroutines and retain some other coroutines inside the scope? We can define ```Job``` using ```launch``` and cancel it whenever we want. ```Job``` is actually coroutine itself. Coroutine is represented by a ```Job```. Every time you call ```launch``` you will return a ```Job``` instance.

```kt
//...
private lateinit var mJob1: Job
private lateinit var mJob2: Job
//...
private fun coroutineTest() {
  mCoroutineScope = CoroutineScope(Dispatchers.Main)
  mJob1 = mCoroutineScope.launch {
    println("loading..")
    delay(3000)
    println("job 1 is done")
  }
  mJob2 = mCoroutineScope.launch {
    println("loading..")
    delay(3000)
    println("job 2 is done")
  }
}
private fun cancelJob1() {
  if (::mJob1.isInitialized && mJob1.isActive) {
    println("job 1 is canceled")
    mJob1.cancel()
  }
}
```

Suppose we call ```coroutineTest()``` method as defined above. Inside the method we launch job 1 and job 2 within ```mCoroutineScope```. In this case if we want to cancel all the job inside ```mCoroutineScope``` we can call ```mCoroutineScope.cancel()``` but if we want to cancel only job 1 we can simply call ```mJob.cancel()```.

### GlobalScope</br>

```GlobalScope``` is a top level ```CoroutineScope``` that is operating as long as the application is alive. We usually use this when we want to launch a running task on application scope so that the task will remains alive until the app killed. Since it’s alive along with application lifetime, ```GlobalScope``` is a singleton object. It’s actually same as ```CoroutineScope``` but the syntax doesn’t have ```CoroutineContext``` on its parameter. By default it will have default ```CoroutineContext``` which is ```Dispatchers.Default``` except we define ```CoroutineContext``` on the ```launch``` parameter.

```kt
GlobalScope.launch(Dispatchers.Main) {
  println("loading..")
  delay(3000)
  println("job is done")
}
```
We can see the usage example on above snippet code. As we can see unlike ```CoroutineScope```, ```GlobalScope``` doesn’t have ```CoroutineContext``` constructor. If we want to cancel running task on the ```GlobalScope```, we can simply use methods same as ```CoroutineScope``` by using cancel method or ```cancel``` the job individually.


- ### Materials
  - [MVVM (Videos [RU])](https://www.youtube.com/watch?v=qEKsLJ8FYes&list=PLY8G5DMG6TiMlF-iZmLSnrThvZQHuSpt2)
  - [Шаблон MVVM по-простому.Lifecycle.](https://www.youtube.com/watch?v=JKoAeOaeV6k)
