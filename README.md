# WaveFormCollapse
I used the wave form collapse technique to generate terrain with a twist.  Instead of hard set rules I added degree of randomness when updating tiles around a collapsed tile.  I did so by increasing the probabilty of a certain tile collapsing into a state by "taking" those states which collapsed into 0. 

The rules right now are in the tile method where the switch is.  If you want to mess around with them go ahead all the positive probablilities must sum to 1 and the negative must be (-1,0) but there is not summing constraint.  Let me know if you get cool results.  Some pictures are in the main folder.

Thanks,
Nick
