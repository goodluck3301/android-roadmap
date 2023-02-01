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
    ...
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
