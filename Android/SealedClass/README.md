## enum class & sealed class

An ```enum``` class is a special data type that enables for a variable to be a set of predefined constants. An ```enum``` class is defined using the enum keyword, followed by the name of the enumeration and a list of enumeration constants in curly braces {}. Here's an example:

```k
enum class Color {
    RED,
    GREEN,
    BLUE
}
```

A ```sealed``` class, on the other hand, is a special kind of class in Kotlin that is used to restrict the class hierarchy. Sealed classes are defined using the ```sealed``` keyword followed by the ```class``` keyword, followed by the name of the class. Here's an example:

```kt
sealed class Shape {
    class Circle(val radius: Double): Shape()
    class Rectangle(val width: Double, val height: Double): Shape()
    class Triangle(val base: Double, val height: Double): Shape()
}
```



Full example ```sealed class```

```kt
sealed class Shape {
    data class Circle(val radius: Double): Shape()
    data class Rectangle(val width: Double, val height: Double): Shape()
    data class Triangle(val base: Double, val height: Double): Shape()
}

fun calculateArea(shape: Shape): Double {
    return when (shape) {
        is Shape.Circle -> Math.PI * shape.radius * shape.radius
        is Shape.Rectangle -> shape.width * shape.height
        is Shape.Triangle -> 0.5 * shape.base * shape.height
    }
}

fun main() {
    val circle = Shape.Circle(5.0)
    val rectangle = Shape.Rectangle(10.0, 20.0)
    val triangle = Shape.Triangle(10.0, 20.0)

    println("Circle area: ${calculateArea(circle)}")
    println()
    println("Rectangle area: ${calculateArea(rectangle)}")
    println("Triangle area: ${calculateArea(triangle)}")
}
```

In Android development, sealed classes can be used to model complex data structures or to represent a limited set of UI states. The primary benefit of using sealed classes is that they provide better type safety and make your code more readable and maintainable.

For example, you can use a sealed class to represent different states of a UI component, such as a button:

```kt
sealed class ButtonState {
    object Loading: ButtonState()
    object Success: ButtonState()
    data class Error(val message: String): ButtonState()
}
```

In this example, the sealed class ```ButtonState``` represents the three possible states of a button: ```Loading```, ```Success```, and ```Error```. The ```Loading``` and ```Success``` states are represented by objects, while the ```Error``` state is represented by a data class that contains an error message.

You can then use this sealed class in your view model to represent the current state of the button:

```kt
class MyViewModel: ViewModel() {
    val buttonState = MutableLiveData<ButtonState>()
    //...
}
```
Finally, in your fragment or activity, you can observe the ```buttonState``` live data and update the UI accordingly:

```kt
class MyFragment: Fragment() {
    private lateinit var viewModel: MyViewModel
    //...
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.buttonState.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is ButtonState.Loading -> showLoading()
                is ButtonState.Success -> showSuccess()
                is ButtonState.Error -> showError(state.message)
            }
        })
    }
    //...
}
```
In this example, the ```when``` expression in the observer is used to determine the current state of the button and update the UI accordingly. This makes your code more readable and maintainable.

- Usedfull sites
  - [Sealed Classes](https://medium.com/jesus-medina/sealed-classes-and-sealed-interfaces-advanced-kotlin-a8f6de0c9eaf)
  - [Sealed Classes VS. Enum Classes VS. Sealed Interfaces - When to Use Which?](https://www.youtube.com/watch?v=kLJRZpRhX1o)
  - [Kotlin: Урок 17. Sealed Классы](https://www.youtube.com/watch?v=kJpsqBRUQeY)
