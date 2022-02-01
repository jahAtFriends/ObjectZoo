# Object Zoo

In this project, you will practice the skills involved in Object Oriented Programming.
To achieve this, you will create a Zoo with a few different types of animals:

* Cats
* Lions
* Deer
* Moose, and
* Chimpanzee

For each of these and the Zoo you will need to design a _class_ with the appropriate
fields, methods, and constructors.

## Class Descriptions

Below is the _API_ for each class you will need to design for this project.
You will also need the Javadocs for the included `Color` class.

### Zoo
All fields of the Zoo should be _private_. it should also implement the 
following API.

```java
public int countAnimls(){} //returns the number of animals in the Zoo.
public void addCat(Cat x){} //Adds the given Cat to the Zoo.
public void addLion(Lion x){} //Adds the given Lion to the Zoo.
public void addDeer(Deer x){} //Adds the given Deer to the Zoo.
public void addMoose(Moose x){} //Adds the given Moose to the Zoo.
public void addChimpanzee(Chimpanzee x){} //Adds the given Deer to the Zoo.
public Moose mateMoose(Moose a, Moose b){} //Returns a new Moose of random sex and a Color the average of the two parent Moose.
public ArrayList<Object> getAllAnimlas(){} //Returns an ArrayList containing all the animals in the Zoo.
```

### Animals
Animals should also have only private fields. They should also _all_ have the following methods:

```java
public Color getColor(){} //Returns the Color of the animal.
public int getWeight(){} //Returns the Weight of the given animal.
public void eat(int w){} //Causes the animal to eat and increases its weight by w.
public boolean isMale(){} //Returns if the animal is male.
public boolean isFemale(){} //For gender equality returns if the animal is female.
public void speak(){} //Returns the speech of the animal according to its definition.
public static int population(){} returns the number of animals of that class (or its subclasses) that have been created.
```

#### Speak() method
The following is a description of how each animal should speak.
* Cat prints "Meow!" to the terminal.
* Lion prints "Roar!" to the terminal.
* Deer prints "Deer Sounds!" to the terminal.
* Moose prints "Moose Sounds!" to the terminal.
* Chimpanzee prints "Oo oo aa aa!" to the terminal.

Printing is always done on _its own line_.

#### Overriding Methods
When one method overrides the method of a parent class, the `@Override` tag should be used
directly above the method heading. The `speak()` method in particular should always override
its superclass (when there is one).
