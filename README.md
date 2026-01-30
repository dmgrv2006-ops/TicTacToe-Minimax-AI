# Tic-Tac-Toe with Minimax AI

A console-based Tic-Tac-Toe game featuring an unbeatable AI opponent powered by the Minimax algorithm with Alpha-Beta pruning.

## Features
- Unbeatable AI using Minimax algorithm with Alpha-Beta pruning
- Player choice: play as X or O
- Input validation for move positions
- Win/draw detection
- Clean console interface with numbered board positions

## How to Run
1. Run: `java Main`
2. Choose your mark (X or O)
3. Enter numbers 1-9 to place your mark

## Technical Details
- Algorithm: Minimax with Alpha-Beta pruning
- Evaluation: +10 for AI win, -10 for player win, 0 for draw
- Depth-based scoring: prefers faster wins and slower losses
- Performance: ~95% reduction in nodes evaluated compared to standard Minimax
  - Without pruning: ~500,000 nodes (empty board)
  - With pruning: ~20,000 nodes (empty board)

## How It Works
The AI explores all possible game states recursively:
1. **Minimax**: Evaluates each possible move by simulating the game tree
2. **Maximizing**: AI chooses moves that maximize its score
3. **Minimizing**: Assumes opponent chooses moves that minimize AI's score
4. **Alpha-Beta Pruning**: Cuts off branches that cannot affect the final decision

## What I Learned
- Implementing recursive game tree algorithms
- Understanding Alpha-Beta pruning optimization
- Managing game state with place/undo operations
- Balancing algorithm efficiency with code clarity

## Future Improvements
- Add move ordering for better pruning efficiency
- Create GUI with JavaFX
- Add game statistics tracking
