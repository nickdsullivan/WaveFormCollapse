# WaveFormCollapse
I used the waveform collapse technique to generate terrain with a twist.  Instead of hard-set rules, I added a degree of randomness when updating tiles around a collapsed tile.  I did so by increasing the probability of a certain tile collapsing into a state by "taking" those states that collapsed into 0. 

The rules right now are in the tile method where the switch is.  If you want to mess around with them go ahead all the positive probabilities must sum to 1 and the negative must be (-1,0) but there is no summing constraint.  Let me know if you get cool results.  Some pictures are in the main folder.


