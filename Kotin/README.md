# Kotlin
 - Compiling Kotlin code

What is Kotlin all about? It’s a new programming language targeting the Java platform. Kotlin is concise, safe, pragmatic, and focused on interoperability with Java
code. It can be used almost everywhere Java is used today: for server-side development, Android apps, and much more. Kotlin works great with all existing Java
libraries and frameworks and runs with the same level of performance as Java.

- ## Compiling Kotlin code

Kotlin source code is normally stored in files with the extension .kt. The Kotlin compiler analyzes the source code and generates .class files, just like the Java compiler
does. The generated .class files are then packaged and executed using the standard
procedure for the type of application you’re working on. In the simplest case, you can use the kotlinc command to compile your code from the command line and use the
java command to execute your code:
```
kotlinc <source file or directory> -include-runtime -d <jar name>
java -jar <jar name>
```
A simplified description of the Kotlin build process is shown in figure 1.1.

![image](https://user-images.githubusercontent.com/100533325/213219391-98f005d5-fe10-4acf-9e3f-01886124c8be.png)

Code compiled with the Kotlin compiler depends on the Kotlin runtime library. It contains the definitions of Kotlin’s own standard library classes and the extensions that
Kotlin adds to the standard Java APIs. The runtime library needs to be distributed with
your application.
 In most real-life cases, you’ll be using a build system such as Maven, Gradle, or Ant
to compile your code. Kotlin is compatible with all those build systems, and we’ll discuss the details in appendix A. All of those build systems also support mixed-language
projects that combine Kotlin and Java in the same codebase. In addition, Maven and
Gradle take care of including the Kotlin runtime library as a dependency of your
application. 

- ## Summary
  - Kotlin is statically typed and supports type inference, allowing it to maintain
correctness and performance while keeping the source code concise.
  - Kotlin supports both object-oriented and functional programming styles,
enabling higher-level abstractions through first-class functions and simplifying
testing and multithreaded development through the support of immutable
values.
  - It works well for server-side applications, fully supporting all existing Java frameworks and providing new tools for common tasks such as HTML generation and
persistence
  - It works for Android as well, thanks to a compact runtime, special compiler support for Android APIs, and a rich library providing Kotlin-friendly functions for
common Android development tasks
  - It’s free and open source, with full support for the major IDEs and build systems.
  - Kotlin is pragmatic, safe, concise, and interoperable, meaning it focuses on
using proven solutions for common tasks, preventing common errors such as
NullPointerExceptions, supporting compact and easy-to-read code, and
providing unrestricted integration with Java.

- ## Custom accessors

This section shows you how to write a custom implementation of a property accessor.
Suppose you declare a rectangle that can say whether it’s a square. You don’t need to
store that information as a separate field, because you can check whether the height is
equal to the width on the go:

![image](https://user-images.githubusercontent.com/100533325/213221976-cc3befb0-be43-49ae-b0b0-2c2c588161ba.png)

The property isSquare doesn’t need a field to store its value. It only has a custom
getter with the implementation provided. The value is computed every time the property is accessed.

 Note that you don’t have to use the full syntax with curly braces; you could write
```get() = height == width```, as well. The invocation of such a property stays the same:

```kt
>>> val rectangle = Rectangle(41, 43)
>>> println(rectangle.isSquare)
false
```
If you need to access this property from Java, you call the isSquare method as before.
 You might ask whether it’s better to declare a function without parameters or a property with a custom getter. Both options are similar: There is no difference in implementation or performance; they only differ in readability. Generally, if you describe the
characteristic (the property) of a class, you should declare it as a property.
 In chapter 4, we’ll present more examples that use classes and properties, and we’ll
look at the syntax to explicitly declare constructors. If you’re impatient in the meantime, you can always use the Java-to-Kotlin converter. Now let’s examine briefly how Kotlin code is organized on disk before we move on to discuss other language features


- ## Using “when” to deal with enum classes

Do you remember how children use mnemonic phrases to memorize the colors of the
rainbow? Here’s one: “Richard Of York Gave Battle In Vain!” Imagine you need a
function that gives you a mnemonic for each color (and you don’t want to store this
information in the enum itself). In Java, you can use a switch statement for this. The
corresponding Kotlin construct is when.
 Like if, when is an expression that returns a value, so you can write a function
with an expression body, returning the when expression directly. When we talked
about functions at the beginning of the chapter, we promised an example of a multiline function with an expression body. Here’s such an example.

![image](https://user-images.githubusercontent.com/100533325/213223898-96696ae1-d754-4874-8725-2eec0e26c133.png)

The code finds the branch corresponding to the passed color value. Unlike in Java,
you don’t need to write break statements in each branch (a missing break is often a
cause for bugs in Java code). If a match is successful, only the corresponding branch is
executed. You can also combine multiple values in the same branch if you separate
them with commas

![image](https://user-images.githubusercontent.com/100533325/213224162-1d99b1f2-3fdd-4a38-9651-377d97336952.png)

These examples use enum constants by their full name, specifying the Color enum
class name. You can simplify the code by importing the constant values

![image](https://user-images.githubusercontent.com/100533325/213224360-ba897138-423c-446f-8f27-8d14f39bf33e.png)


The when construct in Kotlin is more powerful than Java’s switch. Unlike switch,
which requires you to use constants (enum constants, strings, or number literals) as
branch conditions, when allows any objects. Let’s write a function that mixes two colors if they can be mixed in this small palette. You don’t have lots of options, and you
can easily enumerate them all.

![image](https://user-images.githubusercontent.com/100533325/213225651-c0c6c072-7902-4298-a80d-0a18297cc694.png)

If colors c1 and c2 are RED and YELLOW (or vice versa), the result of mixing them is
ORANGE, and so on. To implement this, you use set comparison. The Kotlin standard
library contains a function setOf that creates a Set containing the objects specified
as its arguments. A set is a collection for which the order of items doesn’t matter; two
sets are equal if they contain the same items. Thus, if the sets setOf(c1, c2) and
setOf(RED, YELLOW) are equal, it means either c1 is RED and c2 is YELLOW, or vice
versa. This is exactly what you want to check.
 The when expression matches its argument against all branches in order until
some branch condition is satisfied. Thus setOf(c1, c2) is checked for equality: first
with setOf(RED, YELLOW) and then with other sets of colors, one after another. If
none of the other branch conditions is satisfied, the else branch is evaluated.
 Being able to use any expression as a when branch condition lets you write concise
and beautiful code in many cases. In this example, the condition is an equality check;
next you’ll see how the condition may be any Boolean expression.

- ## Making functions easier to call

Now that you know how to create a collection of elements, let’s do something straightforward: print its contents. Don’t worry if this seems overly simple; along the way,
you’ll meet a bunch of important concepts.
 Java collections have a default toString implementation, but the formatting of
the output is fixed and not always what you need:

![image](https://user-images.githubusercontent.com/100533325/213226679-965b64e0-7824-4361-a286-1175fc1eb4be.png)

Imagine that you need the elements to be separated by semicolons and surrounded by
parentheses, instead of the brackets used by the default implementation: (1; 2; 3).
To solve this, Java projects use third-party libraries such as Guava and Apache Commons, or reimplement the logic inside the project. In Kotlin, a function to handle this
is part of the standard library.
 In this section, you’ll implement this function yourself. You’ll begin with a straightforward implementation that doesn’t use Kotlin’s facilities for simplifying function
declarations, and then you’ll rewrite it in a more idiomatic style.
 The joinToString function shown next appends the elements of the collection
to a StringBuilder, with a separator between them, a prefix at the beginning, and a
postfix at the end.

![image](https://user-images.githubusercontent.com/100533325/213226804-d1967242-411c-4119-a09e-4773db99b7e4.png)

The function is generic: it works on collections that contain elements of any type. The
syntax for generics is similar to Java. (A more detailed discussion of generics will be
the subject of chapter 9.)
 Let’s verify that the function works as intended:
 
 ```kt
>>> val list = listOf(1, 2, 3)
>>> println(joinToString(list, "; ", "(", ")"))
(1; 2; 3)
 ```
 The implementation is fine, and you’ll mostly leave it as is. What we’ll focus on is the
declaration: how can you change it to make calls of this function less verbose? Maybe
you could avoid having to pass four arguments every time you call the function. Let’s
see what you can do.

- ## Utility functions as extensions

Now you can write the final version of the joinToString function. This is almost
exactly what you’ll find in the Kotlin standard library.

![image](https://user-images.githubusercontent.com/100533325/213227951-34b641dd-dc5b-4e45-8f33-821c30f745f1.png)

You make it an extension to a collection of elements, and you provide default values
for all the arguments. Now you can invoke joinToString like a member of a class:

```kt
>>> val list = arrayListOf(1, 2, 3)
>>> println(list.joinToString(" "))
123
```
Because extension functions are effectively syntactic sugar over static method calls,
you can use a more specific type as a receiver type, not only a class. Let’s say you want
to have a join function that can be invoked only on collections of strings.


![image](https://user-images.githubusercontent.com/100533325/213228353-3caa8d72-893d-44a3-943b-16388aea63a4.png)

Calling this function with a list of objects of another type won’t work:

```kt
>>> listOf(1, 2, 8).join()
Error: Type mismatch: inferred type is List<Int> but Collection<String>
was expected.
```

The static nature of extensions also means that extension functions can’t be overridden in subclasses. Let’s look at an example. 

- ## Declaring a mutable extension property

![image](https://user-images.githubusercontent.com/100533325/213229405-3f7cb8ea-a054-4cc1-b7b6-dbe261fd9497.png)

You access extension properties exactly like member properties:

```kt
>>> println("Kotlin".lastChar)
n
>>> val sb = StringBuilder("Kotlin?")
>>> sb.lastChar = '!'
>>> println(sb)
Kotlin!
```

Note that when you need to access an extension property from Java, you should
invoke its getter explicitly: ```StringUtilKt.getLastChar("Java")```.
 We’ve discussed the concept of extensions in general. Now let’s return to the topic
of collections and look at a few more library functions that help you handle them, as
well as language features that come up in those functions.




 


