## [Observer Pattern](https://in-kotlin.com/design-patterns/observer/)

![image](https://user-images.githubusercontent.com/100533325/215146138-8e018f85-771a-455f-b6b5-787b55699430.png)

To demonstrate the usage of this design pattern we will describe a simple use case from the real world. Imagine a temperature sensor which is measuring the temperature every x – seconds. The number is arbitrary, but we are not really in control of it. We have a small screen that displays the temperature input from the sensor. To do so, it must ask the sensor about the temperature.</br>

The monitor must call the sensor repeatedly to show the correct temperature. The problem is obvious: the monitor needs to time The monitor must call the sensor repeatedly to show the correct temperature. The problem is obvious: the monitor needs to time perfectly with the sensor’s reading rate. If it is faster (the monitor asks more often than the temperature changes) it uses too many resources. If it is slower, the temperature might be not updated fast enough. The currently described system is a so-called “Pull – Mechanism”.

```kt
open class Subject {
    private var observers = mutableListOf<Observer>()

    fun callObservers() {
        for(obs in observers) obs.update()
    }

    fun attach(obs : Observer) {
        observers.add(obs)
    }

    fun detach(obs : Observer) {
        observers.remove(obs)
    }
}

interface Observer {
    fun update()
}

class Sensor : Subject() {
    var temperature: Int = 0
        set(value) {
            field = value
            callObservers()
        }
}

class Monitor(val sensor: Sensor) : Observer {
    init {
        sensor.attach(this)
    }
    
    override fun update() {
        val newTemperature = sensor.temperature
        println("update Monitor")
    }

}

fun main() {

    val sensor = Sensor()
    val monitor = Monitor(sensor)

    sensor.temperature = 5
}
```

### In Android

![image](https://user-images.githubusercontent.com/100533325/215146854-07e78860-003b-4208-8a81-2d6d643297fe.png)


```kt
class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var text: TextView
    private lateinit var otherText: TextView

    private var wasClicked = MutableLiveData<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.myButton)
        text = findViewById(R.id.myTextView)
        otherText = findViewById(R.id.otherTextView)

        button.setOnClickListener {
            wasClicked.value = true
        }

        wasClicked.observe(this, {
            text.text = "Clicked"
        })

        wasClicked.observe(this, {
            otherText.text = "Also Clicked"
        })

    }
}
```
