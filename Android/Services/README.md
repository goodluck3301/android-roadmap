# Android Services
- ##### What are Android Services?
- ##### Why we should use services?
- ##### Types of Services
  - ##### Foreground
  - ##### Background
  - ##### Bound
- ##### Lifecycle of Service
- ##### Started Services
- ##### Bound Services
- ##### What is the differences between Started Service and Bound Service?
- ##### Intent Service
- ##### Creating a Service


- ## What are Android Services?

Service is the one of the critical application component which can perform long-running operations in the background. It may continue running for time, even user dealing with to another applications. Moreover, main android components can bind to service to interact with it, also you can perform interprocess communication.

- ## Why we should use services?

As I mentioned before, Developers should use services for long-running operations such as handle network transactions, play music, perform file I/O, or interact with a content provider, all from the background.
</br></br>
Beware! Services run in the main thread of in hosting process, not create its own thread, so developers should run any blocking operations on the separate thread(manage yourself) for avoiding Application Not Responding (ANR) errors.

- ## Types of Services

These are the three different types of services:

![image](https://user-images.githubusercontent.com/100533325/217604725-ed36e31c-9fa1-419d-90ae-009353be13bd.png)

  - ### Foreground
  
Type of services that perform operations in the background that is noticeable for the users. This kind of services must display a Notification and It should continue running even user is not dealing with the app. Likewise, Foreground services have own lifecycle that independent from activity or fragment that they were created. Examples of applications that will use foreground services can be listed as follows:</br>
 -Music Player App(notification of the current song)</br>
 -Fitness App(show the distance that user has traveled)</br></br>
 
 Beware! The WorkManager API offers the flexible and nearly same way as foreground services. In many cases, developers should prefer using WorkManager instead of Foreground services.
 
   - ### Background
   
Type of services that perform operations in the background that is not giving any information to user via notification. Background services have own lifecycle that independent from activity or fragment that they were created. For example, developers can use background services to compact its storage.</br></br>


Beware! If you targets API level 26 or higher, system imposes restrictions on running background services. For example, developers should not access location informations from the background. Alternatively, schedule tasks using WorkManager.


   - ### Bound
   
Type of services that offers a client-server interface that allows components(Activity, content provider and service can bind to the Bound service) to interact with the service, send requests, receive results, and even do so across processes with interprocess communication (IPC).</br>

Bound services have no own lifecycle.That’s why, they use the lifecycle of the activity or fragment they were bounded. Moreover, bound services can only run when application component is bounded to it. Likewise, multiple components can bind to service at once, but when all of them unbind, the service is destroyed.

- ## Lifecycle of Service

In the section so far, we talked about how services work, service types and their examples. Now we will review the lifecycles of services to better understand their working structure.

![image](https://user-images.githubusercontent.com/100533325/217605451-1c6087fc-ed7c-47bc-85ae-5880bf6f07d4.png)

As we can see in the example, we have two kinds of service lifecycle.


  - ### Started Services

A service becomes started only when an application component calls ```startService()```.
</br>

Once this service starts, it runs in the background even if the component that created it destroys(run indefinitely). This service can be stopped only in one of the two cases:

- By using the ```stopService()``` method.
- By stopping itself using the ```stopSelf()``` method.

  - ### Bound Services
  
A service is bound only if an application component binds to it using ```bindService()```.When all clients unbind from bound service by calling ```unBindService()``` function, service ends with onUnbind ```onDestroy()``` functions.
</br>
Beware! Although the two types of lifecycles seem separate from each other, our service can work in two ways. It can be started (to run indefinitely) and also allow binding. It’s simply a matter of whether you implement a couple of callback methods: ```onStartCommand()``` to allow components to start it and ```onBind()``` to allow binding.

- ## What is the differences between Started Service and Bound Service?

![image](https://user-images.githubusercontent.com/100533325/217606291-01c20396-4169-424b-8571-a2eba5bd38c2.png)


- ## Intent Service

IntentService is an extension of the ```Service``` component class that handles asynchronous requests (expressed as ```Intent```) on demand. It handles each ```Intent``` one by one with the help of a worker thread(not using main thread) and stops automatically when the work is done. All the requests are controlled using a single worker thread so they may be sometimes time-consuming. However, they do not interfere with the main loop of the application.


```
To use it, extend IntentService and implement onHandleIntent(android.content.Intent). 
IntentService will receive the Intents, launch a worker thread, and stop the service as appropriate.
```
```Beware!``` This class was deprecated in ```API level 30```. Consider using WorkManager or JobIntentService, which uses jobs instead of services when running on ```Android 8.0``` or higher.


- ## Creating a Service

1-First of all, you must declare your service in your application’s manifest file.

```xml
<manifest ... >
  ...
  <application ... >
      <service android:name=".ExampleService" />
      ...
  </application>
</manifest>
```

2-Extend your class.

```java
public class ExampleService extends Service {
    int startMode;       // indicates how to behave if the service is killed
    IBinder binder;      // interface for clients that bind
    boolean allowRebind; // indicates whether onRebind should be used

    @Override
    public void onCreate() {
        // The service is being created
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // The service is starting, due to a call to startService()
        return startMode;
    }
    @Override
    public IBinder onBind(Intent intent) {
        // A client is binding to the service with bindService()
        return binder;
    }
    @Override
    public boolean onUnbind(Intent intent) {
        // All clients have unbound with unbindService()
        return allowRebind;
    }
    @Override
    public void onRebind(Intent intent) {
        // A client is binding to the service with bindService(),
        // after onUnbind() has already been called
    }
    @Override
    public void onDestroy() {
        // The service is no longer used and is being destroyed
    }
}
```

#### References:

- [Services overview](https://developer.android.com/guide/components/services)
