# Simple chess engine

## Description

The project is a simple chess engine written in Java using the Minimax algorithm. The engine features a graphical user interface that allows for player vs player (PvP) or player vs AI (PvA) gameplay. Additionally, there's an option to load an initial game state using the -fen option.

## Features
* Minimax: A basic Minimax algorithm has been implemented to choose the best move in a given game state.
* Graphical User Interface: A simple graphical interface for interactive chess gameplay.
* Command-Line Options:
* * pvp: Play in player vs player mode.
  * pva: Play against AI mode.
  * fen: Load an initial game state using FEN notation.

## Requirements
* Java 8 or newer
* Java graphical library (JavaFX)

# Installation and Running
1. Clone the Repository
```
git clone https://github.com/SzymonLiszewski/chess.git
```
2.Compilation
```
cd chess
javac Main.java
```
2.Execution
* Player vs Player (PvP) mode:
```
java Main -pvp
```
* Player vs AI (PvA) mode:
```
java Main -pva
```
* Load an initial game state using FEN notation:
```
java Main -fen [fen_string]
```

## Future Planned Features
* improved heuristic
* Improve the graphical interface: Add additional features and enhancements to the user interface.
