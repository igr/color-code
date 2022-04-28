# Example: Restaurants

The domain problem is lovely: table reservation in a restaurant. The domain is simple, yet it requires some effort to make it right. Use it on interviews:)

> ⚠️ The example is not complete (yet) and not entirely correct. It demonstrates how to organize a project.

## Project organization

The project is divided into modules:

+ `model` - domain model, pure DATA
+ `model-serializers` - serializers for `model` and various API inputs
+ `office-api` - thin definition of all the domain verbs, pure FUNCTIONs
+ `office` - implementation of `office-api`
+ `repo-api` - thin definition of repository API, pure FUNCTIONs
+ `repo-db` - implementation of `repo-api` on relational database; ACTIONs.
+ `boot` - bootstrapping of the application, wires everything and runs the application.

## Takeaways

+ Business logic consists of pure FUNCTIONs.
+ All ACTIONs are in implementation.
+ Clean separation of colors.
+ There are internal functions for the business logic.

## Things to do for real-world project

Business logic:

+ `MakeReservation` must be detect concurrent reservations. One way is to add a field `last_reservation_time` to the `Restaurant` entity.  
+ Consider that tables may be joined for larger parties.

Code:

+ Some code fragments should be generated using Kotlin KSP.
+ Add tests for FUNCTIONs.
+ Transform exceptions to proper HTTP responses.
