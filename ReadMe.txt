Hivolts - by Blake Novak, Sean Chapman, and Shivum Argawal
----------------------------------------------------------------------------------------------------------------------------
Intro: 
The Hivolts project was introduced to us about a month ago and our goal was to create a game in which the player is supposed to run away from moving objects called mhos. The player must avoid them and other fence obstacles while leading the mhos to hit the fences. If the player gets hit by the mho or the fence, they die. The game consists of a 12 by 12 grid with fences surrounding the game board. In addition, in the middle there are randomly generated locations for fences, mhos, and the player. 
----------------------------------------------------------------------------------------------------------------------------


How this code fulfills specifications:
Our code fulfills nearly every specification given to us. The program displays a game board with modern graphics, the locations of each object on the board are randomly generated without any overlapping. The player moves according to the specified controls in the assignment sheet. The mhos will chase the player, and are “killed” when they land on a fence. Once all the mhos are killed, a panel appears, indicating that the player has won, and asking whether or not the player would like to start a new game. Likewise, when mhos land on the player (or the player lands on a fence), the player is “killed” and a panel appears informing the player that they have lost and asking if they would like to start a new game. Below the board is a box that displays player controls, and game statistics such as games won, lost, and the amount of mhos remaining in the current game.


How this code does not fulfill specifications:
Unfortunately, this program does not notify the player when it is their turn. When the player moves, the positions of the Mhos are updated instantly, thus creating no waiting period between the player’s turns. Therefore, it was not necessary to add an indicator because it is always the player’s turn. Another specification that was not met was that the program was supposed to run in a JApplet. 


Current Bugs / Shortcomings:
* Mhos can move on top of each other. Occasionally, the “Enemies Remaining” counter below the board will display a number larger than the amount of enemies actually visible on the board. This is because the Mhos are able to move on top of each other, thus creating the appearance that it disappeared for no reason. This can be fixed by adding logic into the mhoMovement method that would check whether two mhos are about to move onto the same position, and prevent one of them from moving.
* Occasionally, when the user chooses to start a new game after dying, the player is instantly spawned on a mho or fence, thus causing the death panel to reappear again.

Overview of Code: 
The code works by using a 2d array to store the locations of the fences, mhos, and player. then, they are stored in their own arrays with the exact coordinates. Every time a key is pressed, it moves the character, then moves the mhos, then reprints/repaints the board to the most updated version. When the player loses or wins, a popup occurs, then either quits if the player wants to exit; or will run reDraw to redraw the 2d array and reset all the locations to this. 


Major Challenges:
The project was split into four key milestones.
* Logic behind the random generation of the board
* Printing the randomly generated board and all of the objects
* Implementation of player movement
* Implementation of Mho movement

All of these milestones took multiple days and extensive collaboration to properly create and refine. The most difficult milestone to complete was the implementation of Mho movement, as we constantly ran into roadblocks with getting it to work. As we continued to complete these goals, we also working and implementing the other project specifications, such as how objects interacted, game over panels, etc.


Acknowledgements:
Gregory Jerian - for a helpful walkthrough on how to use images for graphics


----------------------------------------------------------------------------------------------------------------------------
Changelog:
October 19, 2015
* Finalize project
* Added comments to where they are needed
* Documentation
* Attempts at bug fixes
October 18, 2015
* Modern graphics implemented
* Minor bug fixes
* Unsuccessful attempts at bug fixes
October 17, 2015
* Win panel implemented
* Mho movement complete
   * Bug: Mhos can still move on top of each other
* Mho death logic implemented
* Redraw method implemented - Creates new randomly generated board when player chooses to start new game
* Game statistics and controls displayed below board
October 15, 2015
* Player can no longer jump onto fences
* improvements to Mho movement
* Mho movement works, small bugs
October 13, 2015
* Player position fixed
* Correct number of mhos are now displayed
* Player death logic implemented
* Mho movement added - still very buggy
October 8, 2015
* Player movement works
* Board display fixed, but player is printed in top left of screen
* Not all mhos are displayed
October 1, 2015
* Mho movement implemented
* Board display still broken
September 29, 2015
* Fixed issue of Mhos spawning on top of each other
* Refined random generation
* Board display is broken - displays grid full of player icons
September 24, 2015
* Prints randomly generated board onto a frame
* Board is now visually displayed by program, but nothing on board can move
September 22, 2015
* Program prints randomly generated board in numbers to the console
----------------------------------------------------------------------------------------------------------------------------