- ## Thread
  - [What is Multi-threading?](https://github.com/goodluck3301/android-interview/blob/main/Java/README.md#what-is-multi-threading)
  - [How to create a thread in Java?](https://github.com/goodluck3301/android-interview/blob/main/Java/README.md#how-to-create-a-thread-in-java) 
  - [Which way of creating threads is](https://github.com/goodluck3301/android-interview/blob/main/Java/README.md#which-way-of-creating-threads-is)
  - [Why wait, notify and notifyAll methods](https://github.com/goodluck3301/android-interview/blob/main/Java/README.md#why-wait-notify-and-notifyall-methods)
 
  
### What is Multi-threading?

Answer: Multi-threading is a process of executing two or more
threads concurrently, utilizing available CPU resources. A single
thread is a lightweight sub-process and the smallest unit of
processing. Threads are independent, if any exception occurs in one
thread, it does not affect other threads.
When we execute a Java program without making any separate
thread, then also our program runs on a thread called ‘main thread’.
There are 2 types of threads in an application, user thread and
daemon thread. When the application is first started, main thread is
the first user thread created. We can create multiple user threads
and daemon threads.
One thing to remember here is that, JVM does not have any control
on a thread’s execution. The thread execution is controlled by
Thread scheduler which is part of Operating System. A thread can
be assigned a priority using setPriority(int) method, where 1 is the
minimum and 10 is the maximum priority, however thread priority is
not guaranteed as it is platform dependent.
Multi-threading is used in a time-consuming task, one common
example is File Upload.

  ### How to create a thread in Java?

There are 2 ways to create a thread:
- By extending Thread class
- By implementing Runnable
interface

![image](https://user-images.githubusercontent.com/100533325/213198406-cc6e35b0-4114-4236-ae5c-b2767200ae78.png)

Here a Task class extends Thread class and overrides the run()
method which contains the business logic, then we make an object
of this Task and call the start() method, which starts the thread
execution. start() method internally calls run() method .

</br>
By implementing Runnable interface:

![image](https://user-images.githubusercontent.com/100533325/213198594-f36a4a8a-65fa-4465-8070-a265fc717280.png)

If you see the outputs of this and previous program, you will see that
they are different, because any thread can get a chance to execute
its run() method, when the CPU resources are available.

### Which way of creating threads is better: Thread class or Runnable interface ?

Answer: Implementing Runnable is always the preferred choice, see
the reasons below:
- As you know, Java does not allow multiple inheritance
through classes (because of Diamond problem
discussed in Question 9), so if you are creating threads
by extending Thread class then you will not be able to
extend any other class.
- When we are working with multi-threading, we are not
looking to overwrite any existing functionality of Thread
class, we just want to execute the code with multiple
threads, so in that way also, Runnable is a good choice.
- One more reason to choose Runnable is that, most
people don’t work with just Raw Threads, they use the
Executor framework that is provided from Java 5, that
separates the task from its execution and we can
execute Runnables using execute(Runnable Task)
method of Executor interface.</br>

### What will happen if I directly call the run() method and not the start() method to execute a thread ?

Answer: No. A thread can be started only once in its lifetime. If you
try to start a thread which has already been started, an
IllegalThreadStateException is thrown, which is a runtime exception.
A thread in runnable state or a dead thread cannot be restarted.

### Why wait, notify and notifyAll methods?
are defined in the Object class instead of Thread
class

Answer: This is another very famous multi-threading interview
question. The methods wait, notify and notifyAll are present in the
Object class, that means they are available to all class objects, as
Object class is the parent of all classes.
wait() method – it tells the current thread to release the lock and go
to sleep until some other thread enters the same monitor and calls
notify()

notify() method – wakes up the single thread that is waiting on the
same object’s monitor

notifyAll() method – wakes up all the threads that called wait() on the
same object

if these methods were in Thread class, then thread T1 must know
that another thread T2 is waiting for this particular resource, so T2
can be notified by something like T2.notify()
But in java, the object itself is shared among all the threads, so one
thread acquires the lock on this object’s monitor, runs the code and
while releasing the lock, it calls the notify or notifyAll method on the
object itself, so that any other thread which was waiting on the same
object’s monitor will be notified that now the shared resource is
available. That is why these methods are defined in the Object class.
Threads have no specific knowledge of each other. They can run
asynchronously and are independent. They do not need to know
about the status of other threads. They just need to call notify
method on an object, so whichever thread is waiting on that resource
will be notified.

Let’s consider this with a real-life example:
Suppose there is a petrol pump and it has a single washroom, the
key of which is kept at the service desk. This washroom is a shared
resource for all. To use this shared resource, the user must acquire
the key to the washroom lock. So, the user goes to service desk,
acquires the key, opens the door, locks it from the inside and use the
facility.
Meanwhile if another user arrives at the petrol pump and wants to
use the washroom, he goes to the washroom and found that it is
locked. He goes to the service desk and the key is not there because
it is with the current user. When the current user finishes, he unlocks
the door and returns the key to the service desk. He does not bother
about the waiting users. The service desk gives the key to waiting
user. If there are more than one user waiting to use the facility, then
they must form a queue.
Now, apply this analogy to Java, one user is one thread and the
washroom is the shared resource which the threads wish to execute.
The key will be synchronized keyword provided by Java, through
which thread acquires a lock for the code it wants to execute and
making other threads wait until the current thread finishes. Java will
not be as fair as the service station, because any of the waiting
threads may get a chance to acquire the lock, regardless of the order
in which the threads came. The only guarantee is that all the waiting
threads will get to use the shared resource sooner or later.
In this example, the lock can be acquired on the key object or the
service desk and none of them is a thread. These are the objects
that decide whether the washroom is locked or not.

### What is Locks?

For more granular control, we can utilize a lock. A lock (or monitor) is used to synchronize access to a
shared resource by associating the resource with the lock. A thread gets access to a shared resource by first
acquiring the lock associated with the resource. At any given time, at most one thread can hold the lock
and, therefore, only one thread can access the shared resource.</br>

A common use case for locks is when a resource is accessed from multiple places, but should be only
accessed by one thread oft) fime.This case is demonstrated in the code below. 

```java
public class LockedATM {

     private Lock lock;
     private int balance = 100;

     public LockedATM() {
         lock = new Reentrantlock();
     }

      public int withdraw(int value) {
          lock.lockQ;
          int temp = balance;
          try {
                Thread.sleep(100);
                temp = temp - value;
                Thread.sleep(100);
                balance = temp;
          } catch (interruptedException e) { }
          lock.unlockC);
          return temp;
     }

     public int deposit(int value) {
        lock.lockQ;
        int temp = balance;
        try {
            Thread.sleep(100); 
            temp = temp + value;
            Thread.sleep(300);
            balance = temp;
        } catch (InterruptedException e) { }
        lock.unlockQ;
        return temp;
    }
}
```

Of course, we've added code to intentionally slow down the execution of withdra w and deposit , as it
helps to illustrate the potential problems that can occur. You may not write code exactly like this, but the
situation it mirrors is very, very real. Using a lock will help protect a shared resource from being modified in
unexpected ways. 

### Deadlocks and Deadlock Prevention 

A deadlock is a situation where a thread is waiting for an object lock that another thread holds, and this
second thread is waiting for an object lock that the first thread holds (or an equivalent situation with several
threads). Since each thread is waiting for the other thread to relinquish a lock, they both remain waiting
forever. The threads are said to be deadlocked.
In order for a deadlock to occur, you must have all four of the following conditions met:

1. Mutual Exclusion: Only one process can access a resource at a given time. (Or, more accurately, there is
limited access to a resource, A deadlock could also occur if a resource has limited quantity.)

2. Hold and Wait: Processes already holding a resource can request additional resources, without relinquishing their current resources.

3. No Preemption:One process cannot forcibly remove another process'resource.

4. Circular lVa/f;Two or more processes form a circular chain where each process is waiting on another
resource in the chain.

Deadlock prevention entails removing any of the above conditions, but it gets tricky because many of these
conditions are difficult to satisfy. For instance, removing #1 is difficult because many resources can only
be used by one process at a time (e.g., printers).



[Ինչ է Thread-ը, multithreading - (Video AM)](https://www.youtube.com/watch?v=EYAHKHvm6uI)
