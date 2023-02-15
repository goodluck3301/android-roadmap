# Android app modularization with Clean Architecture

It often happens that we have to deal with projects that grow out of proportion, causing serious problems like difficult with the maintainability, readability, scalability. So, one thing we can do is modularize our project, in order to structure them and avoid these problems.</br>

### Modularization and Clean Architecture

The idea behind modularization is to create independent and specific modules, in order to facilitate both its development and its maintenance. In order to implement it, we will follow [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) as the reference architecture. 
It separates the elements into layers, the idea is to encapsulate the business logic but keeps it separate from the other layers.
One of the most important thing is about code dependencies, the inner layers doesn’t know nothing of the methods on the outer layers, so the code dependencies can only move from the outer layers inward.
</br>Taking a look at the layers we have:

- Presentation layer, it implements the user interface, the user interacts directly with this layer, it contains controllers and view models;
- Domain layer, it contains the business logic, also it is responsible for processing the data from the data layer to the presentation layer and viceversa;
- Data layer, it interacts with external services like network or database;
            

![image](https://user-images.githubusercontent.com/100533325/219096194-c1f02671-5167-4607-b434-8bd1e687096c.png)

### Strategies

We can adopt different strategies to modularize our project following the clean architecture:
            
### Module by layer

Creating a module for each layer of the Clean Architecture.
            
![image](https://user-images.githubusercontent.com/100533325/219096502-b94e99d2-e110-4fa2-a674-35ae3ac7e581.png)

Here, you can follow HotelNow project for the module by layers implementation:
            
- https://github.com/Barros9/HotelNow
            
### Module by feature
            
Each feature has its own module, in this case separate between modules that are features (like the home or detail page) and modules that are libraries that are more specific modules (like the UI or navigation).
            
![image](https://user-images.githubusercontent.com/100533325/219096932-b1c64f8e-1acf-489b-bcf5-3f819543275a.png)

            
Here, you can follow BeerApp project for the module by feature implementation:

- https://github.com/Barros9/BeerApp
            
### Module by feature with layer modules
            
You should even implement a modularization by layers inside each module. Each feature module should be separated in layers (data, domain, presentation).
            
            
### Pro and Cons
            
Let’s see pro and cons adopting the modularization in general and in the specifics strategies.
            
### Pro
            
- Code readable, modularization helps to keep your files a lot smaller with a good separations;
- Easier refactoring, because we are following a strict structure it’s better organize code and refactoring if it is necessary;
- Less Gradle build times, because only the changed module are builded;
- Collaboration, working in a team it could be possible that each developer is working on a single module;        
- Code is easier to test, because tests can be more precise, testing less code, it could be easier smells problems;
- Re-Use, avoid redundant code because a module could be re-used;
            
### Cons
            
- Set up, it could be hard setting up the whole project, also it requires time;
- Gradle config, there could be a lot of problems with the gradle configuration, mostly to handle dependencies between modules;
- A lot of code, in general you have to write a lot of code to set a modularization project;
- Unfamiliar with architecture, all the developers should be aligned, each modules should be implemented in the same way;
            
### Module by layer — Pro and Cons
There is a proper separation following the clean architecture, but you can’t reuse your module also if the project grows, it could be hard to maintain each module.
            
### Module by feature — Pro and Cons
You can reuse the module and working on it without merge conflicts, but each module is not properly separated because you can access from the other modules.
            
### Final thoughts
Modularization is great way to implement your projects, but it could be taught to set, mostly if you don’t have a precise idea in mind. Also there is no winning strategy, a lot depends on the project itself, but if you have evaluated everything, you will be able to make the most of it, especially in managing new features and potential refactors.            


## Tutorials

- [Уроки по архитектуре (clean architecture в Android)](https://youtube.com/playlist?list=PLeF3l86ZMVkLQbdRL6Ra4cr_cmPROj94y)
