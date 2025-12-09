

## Note to self

when to use `notifyAll()`? everywhere?

can nurse recruit orderlies for returning to foyer before treatment starts but after patient enters treatment room?
```dtd
Patient 1 (S) enters treatment room.
Nurse 1 releases 3 orderlies (8 free).
Nurse 1 recruits 3 orderlies (5 free).
Patient 1 (S) treatment started.
```
## report ideas

keep a counter for both producer and consumer: 
if the difference between two counts increases,
then this process' bottleneck exists... 


## BUG!!!

when two patients are waiting between foyer and triage, 
they occupy six orderlies, 
at the same time, one patient is in triage waiting for being transferred to treatment room,
at this moment, a deadlock occurs because none of them can be transferred due to insufficient orderlies


come up with a math expression that involves the number of rooms, each time duration;
use them to express the number of nurses and orderlies required


```dtd
Specialist enters treatment room.
Patient 1 (S) admitted to ED.
Patient 1 (S) allocated to Nurse 1
Nurse 1 recruits 3 orderlies (5 free).
Patient 1 (S) leaves Foyer.
Patient 1 (S) enters triage.
Nurse 1 releases 3 orderlies (8 free).
Nurse 1 recruits 3 orderlies (5 free).
Patient 1 (S) leaves triage.
Patient 1 (S) enters treatment room.
Nurse 1 releases 3 orderlies (8 free).
Patient 1 (S) treatment started.
Nurse 1 recruits 3 orderlies (5 free).
Patient 2 admitted to ED.
Patient 2 allocated to Nurse 3
Nurse 3 recruits 3 orderlies (2 free).
Patient 2 leaves Foyer.
Patient 2 enters triage.
Nurse 3 releases 3 orderlies (5 free).
Patient 1 (S) treatment complete.
Specialist leaves treatment room.
Patient 1 (S) leaves treatment room.
Patient 3 (S) admitted to ED.
Patient 3 (S) allocated to Nurse 2
Nurse 2 recruits 3 orderlies (2 free).
Patient 3 (S) leaves Foyer.
Patient 1 (S) enters Foyer.
Nurse 1 releases 3 orderlies (5 free).
Nurse 3 recruits 3 orderlies (2 free).
Patient 2 leaves triage.
Nurse 1 releases Patient 1 (S)
Patient 3 (S) enters triage.
Nurse 2 releases 3 orderlies (5 free).
Specialist enters treatment room.
Nurse 2 recruits 3 orderlies (2 free).
Patient 3 (S) leaves triage.
Patient 3 (S) enters treatment room.
Nurse 2 releases 3 orderlies (5 free).
Nurse 2 recruits 3 orderlies (2 free).
Patient 3 (S) treatment started.
Patient 4 admitted to ED.
Patient 1 (S) discharged from ED.
Patient 2 enters Foyer.
Nurse 3 releases 3 orderlies (5 free).
Patient 4 allocated to Nurse 1
Nurse 3 releases Patient 2
Nurse 1 recruits 3 orderlies (2 free).
Patient 4 leaves Foyer.
Patient 2 discharged from ED.
Patient 5 (S) admitted to ED.
Patient 5 (S) allocated to Nurse 3
Patient 4 enters triage.
Nurse 1 releases 3 orderlies (5 free).
Nurse 3 recruits 3 orderlies (2 free).
Patient 5 (S) leaves Foyer.
Patient 6 admitted to ED.
Patient 3 (S) treatment complete.
Specialist leaves treatment room.
Patient 3 (S) leaves treatment room.
Patient 3 (S) enters Foyer.
Nurse 2 releases 3 orderlies (5 free).
Nurse 2 releases Patient 3 (S)
Nurse 1 recruits 3 orderlies (2 free).
Patient 6 allocated to Nurse 2
Patient 3 (S) discharged from ED.
Patient 4 leaves triage.
Patient 5 (S) enters triage.
Nurse 3 releases 3 orderlies (5 free).
Nurse 2 recruits 3 orderlies (2 free).
Patient 6 leaves Foyer.
Patient 7 admitted to ED.
Patient 4 enters Foyer.
Nurse 1 releases 3 orderlies (5 free).
Nurse 1 releases Patient 4
Patient 7 allocated to Nurse 1
Nurse 1 recruits 3 orderlies (2 free).
Patient 7 leaves Foyer.
Patient 4 discharged from ED.
Specialist enters treatment room.
Patient 8 admitted to ED.
```