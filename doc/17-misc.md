# Misc topics

## Minimize the public function argument count

+ `one` - great
+ `two` - ok
+ `three` or more - avoid

## Distinguish function configuration from function input

Focus on WHAT your _verb_ operates onto. What do we change, transform? The answer gives you the _input_. Everything else is probably a configuration.

Obviously, DI of the dependencies is part of the configuration.

## Use modules to organize code and divide between concerns

Simple as that, yet powerful.

I wish IDE and tools work more smoothly with modules.

## First split abstractions, then implementations

A common approach is to group all services in one module (package), repository class in another, etc. Avoid this. Instead, separate abstractions first, and then different verbs and repo.

## API layer may have different transport implementation

API invocations could be replaceable without changing the implementation. For example, API invocation could be:

+ direct function call (in a monolith application).
+ remote call, when implementation is defined in remote (micro)service.

In other words, modules and API layer allows extracting a module into a separate microservice.