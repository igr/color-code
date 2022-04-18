# Code colors rules

Primary colors:

```
🟦 == calm, unchanged, stable
🟨 == pure, light, combined
🟥 == contagious, changes other colors
```

## Green is good

```
DATA + FUNCTION -> 🟦 + 🟨 == 🟩
```

Code greens as much as possible.

## Red spreads

```
DATA + STATE -> 🟦 + 🟥 == 🟪
```

Purple means **pay attention**. STATE naturally operates with DATA (receives or emits).

BUILDER, for example, is somewhere between STATE and DATA (holds a state temporarily until it creates DATA); hence it's violet.

```
FUNCTION + STATE -> 🟨 + 🟥 == ACTION 🟧  
```

Orange means **warning**.

ACTION is not pure. It is contaminated by the STATE (side-effect).

```
FUNCTION + ACTION -> 🟨 + 🟧 == ACTION 🟧  
```

STATE is contagious, so ACTION is contagious too. FUNCTION that uses ACTION becomes an ACTION.

## Avoid white

```
OOP == 🟦 + 🟥 + 🟨 == ⬜️
```

White means **nothing**, as a mix of everything. Avoid. Traditional OOP is white, as it mixes all the concepts in one place.
