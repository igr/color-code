# 🟪 BUILDER

BUILDER is a special case of DATA constructor implemented as a mutable STATE.

+ Exists only for building DATA, in a very short scope.
+ BUILDER has a fluent interface.
+ BUILDER produces only DATA.

```kotlin
Point.Builder()
  .x(2)
  .y(5)
  .build()
```

BUILDER may also be a factory.
