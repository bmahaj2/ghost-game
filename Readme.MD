Ghost-game

* Git Repo: https://github.com/bmahaj2/ghost-game

* Explanation of Gameplay (from Wikipedia):

     A player is chosen at random to start the game, and begins by naming any letter of the alphabet.
     Players then take turns to add letters to this fragment, with the aim being to avoid completing an actual word.
     The player whose turn it is may - instead of adding a letter - challenge the previous player to prove that the current fragment is actually the beginning of a word.
     If the challenged player can name such a word, the challenger loses the round; otherwise the challenged player loses the round. If a player bluffs, or completes a word without other players noticing, then play continues.
     The word list is downloaded from https://github.com/freebsd/freebsd/blob/master/share/dict/web2 and added to project at resources/dictionary.txt.

* Requirements:

    1) Java 8
    2) An IDE that can run java project with maven as build tool (Intellij IDE was used to build the project).

* Instructions to run:

    Application.java is the main entry point of the project. Run Application.main() to start the project.
    (ghost-game/src/main/java/Application.java)

* Basic Assumptions:

    1) The game only considers words of the dictionary whose length is greater than 2 and adds them to a Trie.
    2) Game is played between a human player and computer.

* Data structures:

    I have used Trie data structure to create a dictionary.

* Winning Strategy

    Both the players aim being to avoid completing an actual word. Computer selects a character that maximises its chances of winning.
    Computer looks for a word that ends when player1 makes a move. For e.g. Computer selects a word yawnful, such that player1 enters the last l.
    But while forming the word yawnful, the game has to go through yawn. Computer needs to make sure that its not the one who's entering n in yawn.
    If computer is the one entering n, then it will discard the word yawnful.

