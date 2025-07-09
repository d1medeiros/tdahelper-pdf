## states
```mermaid
flowchart LR
    Idle-->|by awaken time|Sleeping
    Idle-->|by withoutEat time|Hungry
    Idle-->|by times|Dead
    Sleeping-->|tracking time over|Idle
    Sleeping-->|tracking time over - by withoutEat time|Hungry
    Sleeping-->|by times|Dead
    Hungry-->|by withoutEat time|Idle
    Hungry-->|by withoutEat time|Starving
    Hungry-->|by withoutEat time|Dead
    Starving-->|by withoutEat time|Idle
    Starving-->|by withoutEat time|Dead
```

