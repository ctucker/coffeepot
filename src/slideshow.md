class: center, middle, title

# Coffee pots with Guice

---

# Getting started


Check out the repo from:

.large-code[`github.com/ctucker/coffeepot`]

* Start from the master branch (other branches are WIP that we may get to)


---

# The code

In the project right now there are classes to represent a coffee
machine:

* A `CoffeeMaker`, which depends on:
   * A `BrewingDevice`, implemeted by a `Percolator`
   * A `Warmer`, implemented by a `SensingWarmer`, which depends on
	 * A `Heater`, implemented by HeatingElement
	 * A `Sensor`, implemented by PotSensor

The code is all in `CoffeeMaker.java`, with a test in
`CoffeeMakerTest.java` that wires everything up manually.

---

# The (initial) goal

Wire the code up with Guice, rather than doing it manually.

* Guice lets you specify *bindings* in a *module*
* It uses those to know how to create types in an `@Inject`-annotated
  constructor
* You get the root of your object graph (`CoffeeMaker`) from the
  *injector*

---

# The next goal

Write a new implementation of `Sensor` called `WeightSensor` (it
doesn't have to do anything, we just want a different type).

Change the `SensingWarmer` to use an annotation on the `Sensor`
argument to indicate what type of `Sensor` you want, and set up your
module to support that.

---

# And another...

Now lets make two `CoffeeMaker`s: one that uses the `WeightSensor` and
one that uses the `PotSensor`.

For this, you'll need to look up the Robot Legs documentation on the
Guice site.

* Basic approach is to annotate the root type
* Declare a (configurable) private module for bindings
* Install two private modules, one for each type

This is a bit tricky!

