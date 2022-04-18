# ⬛ Context

Several times I've mentioned the _context_. We can think about the FUNCTION as operations on the Context value. Therefore, context is a container that holds a value.

The language itself can not represent the context. We have to build a utility class for it.

## One input

FUNCTION operates on **one** input.

> Waaaat?

Sounds crazy, but bear with me. Commonly, we used to pass many arguments to functions. However, if you think about it, they all represent a single _context_ function operates onto.

Yes, the consequence is that you are somewhat _forced_ to define input types of the function. Which is not a bad thing, after all. If it is a FUNCTION input, it deserves to have a domain name, right?

One attractive trait comes along: functions can be passed to the Context.

> ⚡️ Pro tip: either use Context or not. I am still pondering the value.

## Detect the input

As we said, FUNCTIONs arguments are _configuration_ and the _input_. When operating on the Context, FUNCTION has only one input; the rest of the arguments are configuration (and provided through e.g. constructor).

Which argument is the input depends on the usage and Context. Context helps in finding single input. Focus on WHAT your _verb_ operates onto. What do we change? The answer gives you the input type.

### Dependency injection

Obviously, DI of the dependencies is part of the configuration.

## Two contexts

What if we need to join two parallel context execution?

This is a valid question. If this happens in one function's body, then that is a signal you need to extract one context into a private function.

## Delayed context

Context implementation may be:

+ _immediate_ - executes right away;
+ _delayed_ - constructs flow of the operations (i.e. pipeline) and gets executed anytime later.

## Consequences

+ Each FUNCTION has one input.
+ You are forced to think about input types.
+ Business logic becomes just sequential context execution.
+ Separate internal business logic in private functions.

## Some questions

> Is the context monad?

Nope. It lacks monadic rules. There is no intention to introduce nothing more than just a value holder. 

> Is the context just... piping?

Yes.

> Are you nuts?

Well...
