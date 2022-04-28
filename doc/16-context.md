# ⬛ Context

Several times I've mentioned the _context_. We can think about the FUNCTION as operations on the Context value. Therefore, context is a container that holds value.

Context could be expressed:

+ with a custom class (see `Ctx` and `CtxD`), that serves as a container instance.
+ function body itself is a context.

## Container context class

When we explicitly use context with a container class, we put the initial content inside and define the ordered set of transformations of the initial value:

```text
input -> func1 -> func2 -> func3 -> func4 -> output
```

An extreme version of the context is one that only accepts functions with a _single_ argument.

While this idea seems interesting, it is hard to keep up with it in the real world. The consequence is that you are somewhat _forced_ to define input types of the function. Which is not a bad thing, after all. While I like to think like that, it does not always make sense.

The cool thing about container context is that you may have different modes of execution:

+ _immediate_ - executes right away;
+ _delayed_ - constructs flow of the operations (i.e. pipeline) and gets executed anytime later.

Another cool thing is that business logic becomes just sequential context execution.

Context class does not have to be a monad.

## Function body as a context 

This is my preferred way of thinking about the context. The function body itself is a context.

When you think in such a way, specific rules emerge:

+ the context is never changed - you just build it up; by adding final references to calculated values.
+ fetch, then process - first bring all the values (build the context), then process it fluently.
