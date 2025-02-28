# Magic-Square-Puzzle-Game
This project was divided into three parts: first, a magic square generator was created to produce an n×n matrix where each row, column, and diagonal sums to the same value. Next, the generator was adapted into an interactive console-based puzzle game where the magic square is scrambled and the player must restore it, with a move counter tracking their progress. Finally, a concise report (under 2 pages) was prepared, detailing the design, implementation, and testing of the solution.
Design & Implementation

    Magic Square Generation:
    The initial component uses the Siamese method to construct the magic square. This algorithm iteratively fills the matrix to ensure that every row, column, and diagonal sums to a constant value.

    Puzzle Game Development:
    To create the puzzle, a scrambling algorithm was implemented. The algorithm selects an element in the matrix and swaps it with one of its non-diagonal neighbors, ensuring a shuffled yet solvable puzzle. The game includes a move counter and intuitive grid labeling (with rows numbered and columns assigned letters) to assist players in navigating the puzzle. Due to the labeling system, the game supports magic squares up to 26×26.

    Assumptions & Design Considerations:
    It was assumed that players have basic literacy and familiarity with magic squares. The design emphasizes simplicity and user intuitiveness, with the console interface clearly displaying the grid and input prompts. The entire gameplay loop is maintained using a while loop until the win condition (correct sums across rows, columns, and diagonals) is met.
