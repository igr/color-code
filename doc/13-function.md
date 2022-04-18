# 🟨 FUNCTION

FUNCTION is a first-class citizen.

FUNCTION transforms **one** input into **one** output. FUNCTION transformations may be interpreted as _context_ mappings from one state into another. Simple as: `(A) -> B`.

> Wait, one input?

Sure, FUNCTION may have multiple arguments. However, we may consider multiple arguments as one argument (even have a type for it). You can find out more about this idea later. 

## On naming

+ FUNCTION is a verb.

## On arguments

FUNCTIONs arguments can be:

+ _explicit_ - part of functions signature.
+ _implicit_ - an external reference used directly in the function body. 

_Explicit_ arguments may be:

+ **configuration** - define the behaviour, provide external dependencies.
+ **input** - pure input used in functions operation. 

_Implicit_ arguments are always **configuration**.

> ⚡️ Pro tip: It may be tricky to distinguish between configuration and the input arguments. Using CONTEXT helps. 

## Function declaration != FUNCTION

This should be obvious, but let's emphasize it. FUNCTION is a reference to a function; i.e., its instance. You can define FUNCTION by, e.g.:

+ anonymous instance referenced by `val`,
+ using `object`,
+ having an instance of functional type,
+ or inline lambda that calls declared function.

> ⚡️ Pro tip: Try to minimize anonymous instances when they do not contribute to the domain vocabulary.
