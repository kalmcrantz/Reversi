import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/** 
 * Plays the game Reversi
 * @author Kimberly Almcrantz
 */

public class Reversi extends JFrame implements ActionListener {
  
  /******************************************************FIELDS*****************************************************/
  
  /**
   * Stores the color pallet in first pop up window
   */ 
  private JColorChooser colorChooser;
  
  /**
   * Stores the first pop up window where players choose their color
   */ 
  private JFrame colorChooserFrame;
  
  /**
   * Stores the panel of buttons for players to choose their color
   */ 
  private JPanel playerPanel;
  
  /**
   * Stores the color choosing button for player 1
   */ 
  private JButton player1Button;
  
  /**
   * Stores the color choosing button for player 2
   */ 
  private JButton player2Button;
  
  /**
   * Stores the button that starts to play the game
   */ 
  private JButton playGameButton;
  
  /** 
   * Stores player 1's color
   */ 
  private Color player1Color;
  
  /**
   * Stores player 2's color
   */ 
  private Color player2Color;
  
  /**
   * Stores the toolkit
   */ 
  private Toolkit tk = Toolkit.getDefaultToolkit();
  
  /** 
   * Stores the dimension from the toolkit
   */ 
  private Dimension d = tk.getScreenSize();
  
  /** 
   * Board's height
   */
  private int height;
  
  /** 
   * Board's width
   */
  private int width;
  
  /**
   * Stores the array of JButtons
   */
  private JButton [][] buttonArray;
  
  /**
   * Stores the board panel
   */
  private JPanel board;
  
  /**
   * Says which player is playing
   */
  private boolean player1 = true;
  
  /**
   * Stores the text area that displays which player's turn
   */ 
  JButton whichPlayer;
  
  /** 
   * Stores the font for the text windows
   */
  Font font = new Font("SansSerif", 1, 18);
  
  /*********************************************************CONSTRUCTORS*********************************************************/
  
  /** 
   * Creates a standard 8x8 board
   */ 
  public Reversi() {
    
    //Creates the first pop up windown to choose colors
    colorChooserFrame = new JFrame();
    playerPanel = new JPanel(new GridLayout (1, 2));
    player1Button = new JButton("Player 1: Click color then click here");
    player2Button = new JButton("Player 2: Click color then click here");
    playGameButton = new JButton ("PLAY GAME!");
    player1Button.addActionListener(new PlayerButtonActionListener());
    player2Button.addActionListener(new PlayerButtonActionListener());
    playGameButton.addActionListener(new PlayGameActionListener());
    player1Button.setBackground(Color.white);
    player2Button.setBackground(Color.white);
    playGameButton.setBackground(Color.white);
    Container container = colorChooserFrame.getContentPane();
    colorChooser = new JColorChooser();
    colorChooser.setPreviewPanel(new JPanel());
    container.add(colorChooser, "North");
    playerPanel.add(player1Button, -1);
    playerPanel.add(player2Button, -1);
    container.add(playerPanel, "Center");
    container.add(playGameButton, "South");
    colorChooserFrame.setSize(500, 500);  
    //sets the height and width of the board
    this.height = 8;
    this.width = 8;
    //creates the board and array of buttons
    board = new JPanel(new GridLayout(height, width));
    Container c = this.getContentPane();
    c.add(board, "Center");
    buttonArray = new JButton [height][width];
    int count = 1;
    for (int i = 0; i < getButtonArray().length; i++) {
      for (int j = 0; j < getButtonArray()[i].length; j++) {
        getButtonArray() [i][j] = new JButton();
        getButtonArray()[i][j].setBackground(Color.lightGray);
        getButtonArray()[i][j].addActionListener(this);
        board.add(getButtonArray()[i][j], count - 1);
        board.setVisible(true);
        count++;
      }
    }
    //Sets sizes of windows
    board.setSize(500, 500);
    this.setSize(700,700);
    //Creates the top display that tells whose turn it is
    whichPlayer = new JButton();
    whichPlayer.setSize(10, 500);
    c.add(whichPlayer, "North");
    whichPlayer.setText("It is player 1's turn");
    whichPlayer.setFont(font);
    //Sets location in middle of board and visible
    this.setLocation(d.width/2 - 350, d.height/2 -350);
    colorChooserFrame.setLocation(d.width/2 -250, d.height/2 -250);
    this.setVisible(true);
    colorChooserFrame.setVisible(true);
    
    //sets default player's colors
    player1Color = Color.black;
    player2Color = Color.white;
    
    //sets center colors
    getButtonArray()[getButtonArray().length/2 - 1][getButtonArray()[0].length/2 - 1].setBackground(getPlayerColor(player1));
    getButtonArray()[getButtonArray().length/2][(getButtonArray()[0].length/2)].setBackground(getPlayerColor(player1));
    getButtonArray()[getButtonArray().length/2][getButtonArray()[0].length/2 - 1].setBackground(getPlayerColor(!player1));
    getButtonArray()[getButtonArray().length/2 - 1][(getButtonArray()[0].length/2)].setBackground(getPlayerColor(!player1));
    try {
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    }
    catch (Exception e) {
    }
  }
  
  /**
   * Creates a square board of specified size (largest size 25x25)
   * @param boardsize The side length of the square board
   */
  public Reversi(int boardsize) {
    
    if (boardsize > 1 && boardsize < 26) {
      //Creates the first pop up window to choose colors
      colorChooserFrame = new JFrame();
      playerPanel = new JPanel(new GridLayout (1, 2));
      player1Button = new JButton("Player 1: Click color then click here");
      player2Button = new JButton("Player 2: Click color then click here");
      playGameButton = new JButton ("PLAY GAME!");
      player1Button.addActionListener(new PlayerButtonActionListener());
      player2Button.addActionListener(new PlayerButtonActionListener());
      playGameButton.addActionListener(new PlayGameActionListener());
      player1Button.setBackground(Color.white);
      player2Button.setBackground(Color.white);
      playGameButton.setBackground(Color.white);
      Container container = colorChooserFrame.getContentPane();
      colorChooser = new JColorChooser();
      colorChooser.setPreviewPanel(new JPanel());
      container.add(colorChooser, "North");
      playerPanel.add(player1Button, -1);
      playerPanel.add(player2Button, -1);
      container.add(playerPanel, "Center");
      container.add(playGameButton, "South");
      colorChooserFrame.setSize(500, 500); 
      //Sets the board height and width
      this.height = boardsize;
      this.width = boardsize;
      //Creates the game board and array of buttons
      board = new JPanel(new GridLayout(height, width));
      Container c = this.getContentPane();
      c.add(board, "Center");
      buttonArray = new JButton [height][width];
      int count = 1;
      for (int i = 0; i < getButtonArray().length; i++) {
        for (int j = 0; j < getButtonArray()[i].length; j++) {
          getButtonArray() [i][j] = new JButton();
          getButtonArray()[i][j].setBackground(Color.lightGray);
          getButtonArray()[i][j].addActionListener(this);
          board.add(getButtonArray()[i][j], count - 1);
          board.setVisible(true);
          count++;
        }
      }
      //Sets sizes of windows
      board.setSize(500, 500);
      this.setSize(700,700);
      //Creates the top display that tells whose turn it is
      whichPlayer = new JButton();
      whichPlayer.setSize(500, 100);
      c.add(whichPlayer, "North");
      whichPlayer.setText("It is player 1's turn");
      whichPlayer.setFont(font);
      //Sets location in middle of board and visible
      this.setLocation(d.width/2 - 350, d.height/2 -350);
      colorChooserFrame.setLocation(d.width/2 -250, d.height/2 -250);
      this.setVisible(true);
      colorChooserFrame.setVisible(true);
      try {
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
      }
      catch (Exception e) {
      }
    }
    else {
      JFrame errorSize = new JFrame();
      JButton errorButton = new JButton ("Sorry, the board size you have entered cannot be accommodated");
      errorSize.add(errorButton, "Center");
      errorButton.setBackground(Color.red);
      errorButton.setFont(font);
      errorSize.setSize(700, 150);
      errorSize.setLocation(d.width/2 - 350, d.height/2 - 75);
      errorSize.setVisible(true);
    }
  }
  
  /** 
   * Creates a rectangular board of specified size and places pieces as close to center as possible (largest size 25x25)
   * @param height The height of the board
   * @param width The width of the board
   */ 
  public Reversi(int height, int width) {
    
    if (height > 1 && height < 26 && width > 1 && height < 26) {
      //Creates the first pop up window that chooses player's colors
      colorChooserFrame = new JFrame();
      playerPanel = new JPanel(new GridLayout (1, 2));
      player1Button = new JButton("Player 1: Click color then click here");
      player2Button = new JButton("Player 2: Click color then click here");
      playGameButton = new JButton ("PLAY GAME!");
      player1Button.addActionListener(new PlayerButtonActionListener());
      player2Button.addActionListener(new PlayerButtonActionListener());
      playGameButton.addActionListener(new PlayGameActionListener());
      player1Button.setBackground(Color.white);
      player2Button.setBackground(Color.white);
      playGameButton.setBackground(Color.white);
      Container container = colorChooserFrame.getContentPane();
      colorChooser = new JColorChooser();
      colorChooser.setPreviewPanel(new JPanel());
      container.add(colorChooser, "North");
      playerPanel.add(player1Button, -1);
      playerPanel.add(player2Button, -1);
      container.add(playerPanel, "Center");
      container.add(playGameButton, "South");
      colorChooserFrame.setSize(500, 500);  
      //Sets the height and width
      this.height = height;
      this.width = width;
      //Creates the game board and array of buttons
      board = new JPanel(new GridLayout(height, width));
      Container c = this.getContentPane();
      c.add(board, "Center");
      buttonArray = new JButton [height][width];
      int count = 1;
      for (int i = 0; i < getButtonArray().length; i++) {
        for (int j = 0; j < getButtonArray()[i].length; j++) {
          getButtonArray() [i][j] = new JButton();
          getButtonArray()[i][j].setBackground(Color.lightGray);
          getButtonArray()[i][j].addActionListener(this);
          board.add(getButtonArray()[i][j], count - 1);
          board.setVisible(true);
          count++;
        }
      }
      //Sets sizes of windows
      board.setSize(75*width, 75*height + 100);
      this.setSize(700, 700);
      //Creates the top display that tells whose turn it is
      whichPlayer = new JButton();
      whichPlayer.setSize(500, 100);
      c.add(whichPlayer, "North");
      whichPlayer.setText("It is player 1's turn");
      whichPlayer.setFont(font);
      //Sets location in middle of board and sets visible
      this.setLocation(d.width/2 - 350, d.height/2 -350);
      colorChooserFrame.setLocation(d.width/2 -250, d.height/2 -250);
      this.setVisible(true);
      colorChooserFrame.setVisible(true);
      try {
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
      }
      catch (Exception e) {
      }
    }
    else {
      JFrame errorSize = new JFrame();
      JButton errorButton = new JButton ("Sorry, the board size you have entered cannot be accommodated");
      errorSize.add(errorButton, "Center");
      errorButton.setBackground(Color.red);
      errorButton.setFont(font);
      errorSize.setSize(700, 150);
      errorSize.setLocation(d.width/2 - 350, d.height/2 - 75);
      errorSize.setVisible(true);
    }
  }
  
  /*********************************************************METHODS*********************************************************/
  
  /** 
   * If the tile clicked is allowed, turns over necessary tiles and changes to next player
   * @param e Information about the event that happened to the button
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    
    //Gets the button that was clicked
    JButton b = (JButton)e.getSource();
    //Changes tiles in all directions if allowed then goes to next player
    if (moveAllowed(player1, b)) {
      changeColor(player1, b);
      changeLeft(player1, b);
      changeRight(player1, b);
      changeUp(player1, b);
      changeDown(player1, b);
      changeUpLeft(player1, b);
      changeUpRight(player1, b);
      changeDownLeft(player1, b);
      changeDownRight(player1, b);
      player1 = !player1;
    }
    //Checks for available moves
    //If no available moves for both players, determines winner
    //If no available move for current player, switches to next player
    if (!availableMove(player1) && !availableMove(!player1))
      determineWinner();
    else if (!availableMove(player1)) {
      noAvailableMovePopUp(player1);
      player1 = !player1;
    }
    //Changes top display of whose turn it is
    if (player1) {
      whichPlayer.setText("It is player 1's turn");
      whichPlayer.setBackground(getPlayerColor(true));
    }
    else {
      whichPlayer.setText("It is player 2's turn");
      whichPlayer.setBackground(getPlayerColor(false));
    }
  }
  
  /** 
   * Determines who the winner of the game is and produces a pop up message
   */
  public void determineWinner() {
    
    //Setting up pop up window for whose turn it is
    JFrame f = new JFrame();
    JButton winner = new JButton();
    JButton p1 = new JButton();
    JButton p2 = new JButton();
    JPanel scorePanel = new JPanel(new GridLayout (1, 2));
    scorePanel.add(p1, -1);
    scorePanel.add(p2, -1);                                   
    winner.setFont(font);
    p1.setFont(font);
    p2.setFont(font);
    Container c = f.getContentPane();
    Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension d = tk.getScreenSize();
    c.add(winner, "North");
    c.add(scorePanel, "Center");
    f.setSize(500, 100);
    f.setLocation(d.width/2 - 250, d.height/2 - 250);
    //Counts all the tiles for player 1 and player 2
    int player1Count = 0;
    int player2Count = 0;
    for (int i = 0; i < getButtonArray().length; i++) {
      for (int j = 0; j < getButtonArray()[i].length; j++) {
        if (getButtonArray()[i][j].getBackground().equals(getPlayerColor(true)))
          player1Count++;
        else if (getButtonArray()[i][j].getBackground().equals(getPlayerColor(false)))
          player2Count++;
      }
    }
    p1.setText("Player 1: " + player1Count);
    p2.setText("Player 2: " + player2Count);
    //Pop up window for who the winner is and turns the window the color of the winning player
    if (player1Count == player2Count){
      winner.setText("It's a tie!");
      whichPlayer.setText("It's a tie!");
    }
    else if (player1Count > player2Count) {
      winner.setText("Player 1 wins!");
      whichPlayer.setText("Player 1 wins!");
    }
    else {
      winner.setText("Player 2 wins!");
      whichPlayer.setText("Player 2 wins!");
    }
    f.setVisible(true);
  }
  
  /**
   * Dislays a pop up window telling which player does not have an available move
   * @param p If player1
   */  
  public void noAvailableMovePopUp(boolean p) {
    JFrame f = new JFrame();        //Stores the pop up colorChooserFrame
    JButton t = new JButton();      //Stores button that says who has no available move
    JButton ok = new JButton("OK"); //Stores the OK button
    t.setFont(font);
    ok.setFont(font);
    ok.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        f.dispose();
      }
    });
    Container c = f.getContentPane();
    c.add(t, "Center");
    c.add(ok, "South");
    f.setSize(500, 100);
    t.setBackground(Color.red);
    f.setLocation(d.width/2 - 250, d.height/2 - 250);
    if (p)
      t.setText("There are no available moves for player 1");
    else
      t.setText("There are no available moves for player 2");
    f.setVisible(true);
  }
  
  /**
   * Determines if there is an available move for the next player
   * @param p If player1
   * @return Whether or not a move is available
   */ 
  public boolean availableMove(boolean p) {
    for (int i = 0; i < getButtonArray().length; i++) {
      for (int j = 0; j < getButtonArray()[i].length; j++) {
        if (emptySpace(getButtonArray()[i][j])) {
          if (oppositeColorToLeft(p, getButtonArray()[i][j]) && leftMatch(p, getButtonArray()[i][j]) != -1) 
            return true;
          else if (oppositeColorToRight(p, getButtonArray()[i][j]) && rightMatch(p, getButtonArray()[i][j]) != -1) 
            return true;
          else if (oppositeColorUp(p, getButtonArray()[i][j]) && upMatch(p, getButtonArray()[i][j]) != -1) 
            return true;
          else if (oppositeColorDown(p, getButtonArray()[i][j]) && downMatch(p, getButtonArray()[i][j]) != -1) 
            return true;
          else if (oppositeColorUpLeft(p, getButtonArray()[i][j]) && upLeftMatch(p, getButtonArray()[i][j]) != -1)
            return true;
          else if (oppositeColorUpRight(p, getButtonArray()[i][j]) && upRightMatch(p, getButtonArray()[i][j]) != -1) 
            return true;
          else if (oppositeColorDownLeft(p, getButtonArray()[i][j]) && downLeftMatch(p, getButtonArray()[i][j]) != -1)
            return true;
          else if (oppositeColorDownRight(p, getButtonArray()[i][j]) && downRightMatch(p, getButtonArray()[i][j]) != -1)
            return true;
        }
      }
    }
    return false;
  }
  
  /**
   * Determines if there current move is allowed
   * @param p If player1
   * @param a The button that was clicked
   * @return Whether or not a move is allowed
   */ 
  public boolean moveAllowed (boolean p, JButton a) {
    if (emptySpace(a)) {
      if (oppositeColorToLeft(p, a) && leftMatch(p, a) != -1) 
        return true;
      else if (oppositeColorToRight(p, a) && rightMatch(p, a) != -1) 
        return true;
      else if (oppositeColorUp(p, a) && upMatch(p, a) != -1) 
        return true;
      else if (oppositeColorDown(p, a) && downMatch(p, a) != -1) 
        return true;
      else if (oppositeColorUpLeft(p, a) && upLeftMatch(p, a) != -1)
        return true;
      else if (oppositeColorUpRight(p, a) && upRightMatch(p, a) != -1) 
        return true;
      else if (oppositeColorDownLeft(p, a) && downLeftMatch(p, a) != -1)
        return true;
      else if (oppositeColorDownRight(p, a) && downRightMatch(p, a) != -1)
        return true;
      else 
        return false;
    }
    else {
      return false;
    }
  }
  
  /**
   * Determines if the space clicked has not been clicked before
   * @param a The button that was clicked
   * @return Whether or not the space has not been clicked before
   */ 
  public boolean emptySpace(JButton a) {
    if (a.getBackground().equals(Color.lightGray)) {
      return true;
    }
    else {
      return false;
    }
  }
  
  /****************************************METHODS THAT CHANGE THE COLOR OF TILES*********************************************/
  
  /** 
   * Changes the background color of the button to the player's color
   * @param p If the player is player1 or not
   * @param a The button that was clicked
   */
  public void changeColor(boolean p, JButton a) {
    a.setBackground(getPlayerColor(p));
  }
  
  /** 
   * Changes the background color of the buttons to the left if allowed
   * @param p If the player is player1 or not
   * @param a The button that was clicked
   */
  public void changeLeft(boolean p, JButton a) {
    if (oppositeColorToLeft(p, a) && leftMatch(p, a) != -1) {
      int end = leftMatch(p, a);
      for (int j = end; j < getColumn(a); j++) 
        changeColor(p, getButtonArray()[getRow(a)][j]);
    }
  }
  
  /** 
   * Changes the background color of the buttons to the right if allowed
   * @param p If the player is player1 or not
   * @param a The button that was clicked
   */
  public void changeRight(boolean p, JButton a) {
    if (oppositeColorToRight(p, a) && rightMatch(p, a) < getButtonArray()[getRow(a)].length) {
      int end = rightMatch(p, a);
      for (int j = getColumn(a) + 1; j <= end; j++) 
        changeColor(p, getButtonArray()[getRow(a)][j]);
    }
  }
  
  /** 
   * Changes the background color of the buttons above if allowed
   * @param p If the player is player1 or not
   * @param a The button that was clicked
   */
  public void changeUp(boolean p, JButton a) {
    if (oppositeColorUp(p, a) && upMatch(p, a) != -1) {
      int end = upMatch(p, a);
      for (int i = end; i < getRow(a); i++) 
        changeColor(p, getButtonArray()[i][getColumn(a)]);
    }
  }
  
  /** 
   * Changes the background color of the buttons below if allowed
   * @param p If the player is player1 or not
   * @param a The button that was clicked
   */
  public void changeDown(boolean p, JButton a) {
    if (oppositeColorDown(p, a) && downMatch(p, a) != -1) {
      int end = downMatch(p, a);
      for (int i = getRow(a) + 1; i <= end; i++) 
        changeColor(p, getButtonArray()[i][getColumn(a)]);
    }
  }
  
  /** 
   * Changes the background color of the buttons above and to the left if allowed
   * @param p If the player is player1 or not
   * @param a The button that was clicked
   */
  public void changeUpLeft(boolean p, JButton a) {
    if (oppositeColorUpLeft(p, a) && upLeftMatch(p, a) != -1) {
      int num = upLeftMatch(p, a);
      for (int count = 1; count <= num + 1; count++) 
        changeColor(p, getButtonArray()[getRow(a) - count][getColumn(a) - count]);
    }
  }
  
  /** 
   * Changes the background color of the buttons above and to the right if allowed
   * @param p If the player is player1 or not
   * @param a The button that was clicked
   */
  public void changeUpRight(boolean p, JButton a) {
    if (oppositeColorUpRight(p, a) && upRightMatch(p, a) != -1) {
      int num = upRightMatch(p, a);
      for (int count = 1; count <= num + 1; count++) 
        changeColor(p, getButtonArray()[getRow(a) - count][getColumn(a) + count]);
    }
  }
  
  /** 
   * Changes the background color of the buttons down and to the left if allowed
   * @param p If the player is player1 or not
   * @param a The button that was clicked
   */
  public void changeDownLeft(boolean p, JButton a) {
    if (oppositeColorDownLeft(p, a) && downLeftMatch(p, a) != -1) {
      int num = downLeftMatch(p, a);
      for (int count = 1; count <= num + 1; count++) 
        changeColor(p, getButtonArray()[getRow(a) + count][getColumn(a) - count]);
    }
  }
  
  /** 
   * Changes the background color of the buttons down and to the right if allowed
   * @param p If the player is player1 or not
   * @param a The button that was clicked
   */
  public void changeDownRight(boolean p, JButton a) {
    if (oppositeColorDownRight(p, a) && downRightMatch(p, a) != -1) {
      int num = downRightMatch(p, a);
      for (int count = 1; count <= num + 1; count++) 
        changeColor(p, getButtonArray()[getRow(a) + count][getColumn(a) + count]);
    }
  }
  
  /*****************************************METHODS THAT DETERMINE IF PLAYER'S COLOR MATCHES IN LINE***********************************/
  
  /**
   * Determines if there is a tile to the left that matches the player's color (therefore the tile(s) between need to be flipped)
   * @param p Which player played the move
   * @param a The button that was clicked
   * @return The column index of the tile to the left that has the player's color, if no tile returns -1
   */ 
  public int leftMatch (boolean p, JButton a) {
    for (int j = getColumn(a) - 2; j >= 0; j--) {
      if (getButtonArray()[getRow(a)][j].getBackground().equals(Color.lightGray))
        return -1;
      else if (getButtonArray()[getRow(a)][j].getBackground().equals(getPlayerColor(p)))
        return j;
    }
    return -1;
  }
  
  /**
   * Determines if there is a tile to the right that matches the player's color (therefore the tile(s) between need to be flipped)
   * @param p Which player played the move
   * @param a The button that was clicked
   * @return The column index of the tile to the right that is the player's color, if no tile, returns the length of the row
   */ 
  public int rightMatch (boolean p, JButton a) {
    for (int j = getColumn(a) + 2; j < getButtonArray()[getRow(a)].length; j++) {
      if (getButtonArray()[getRow(a)][j].getBackground().equals(Color.lightGray))
        return -1;
      else if (getButtonArray()[getRow(a)][j].getBackground().equals(getPlayerColor(p)))
        return j;
    }
    return -1;
  }
  
  /**
   * Determines if there is a tile above that matches the player's color (therefore the tile(s) between need to be flipped)
   * @param p Which player played the move
   * @param a The button that was clicked
   * @return The row index of the tile above that is the player's color, if no tile returns -1
   */ 
  public int upMatch (boolean p, JButton a) {
    for (int i = getRow(a) - 2; i >= 0; i--) {
      if (getButtonArray()[i][getColumn(a)].getBackground().equals(Color.lightGray))
        return -1;
      else if (getButtonArray()[i][getColumn(a)].getBackground().equals(getPlayerColor(p)))
        return i;
    }
    return -1;
  }
  
  /**
   * Determines if there is a tile to the below that matches the player's color (therefore the tile(s) between need to be flipped)
   * @param p Which player played the move
   * @param a The button that was clicked
   * @return The row index of the tile below that is the player's color, if no tile returns the length of the column
   */ 
  public int downMatch (boolean p, JButton a) {
    for (int i = getRow(a) + 2; i < getButtonArray().length; i++) {
      if (getButtonArray()[i][getColumn(a)].getBackground().equals(Color.lightGray))
        return -1;
      else if (getButtonArray()[i][getColumn(a)].getBackground().equals(getPlayerColor(p)))
        return i;
    }
    return -1;
  }
  
  /**
   * Determines if there is a tile diagonally above and to the left that matches the player's color (therefore the tile(s) between need to be flipped)
   * @param p Which player played the move
   * @param a The button that was clicked
   * @return The number of opponent's tiles diagonally that need to be flipped
   */ 
  public int upLeftMatch (boolean p, JButton a) {
    int count = 1;
    for (int i = getRow(a) - 2, j = getColumn(a) - 2; i >= 0 && j >= 0; i--, j--) {
      if (getButtonArray()[i][j].getBackground().equals(Color.lightGray)) 
        return -1;
      else if (getButtonArray()[i][j].getBackground().equals(getPlayerColor(p))) 
        return count;
      count++;
    }
    return -1;
  }
  
  /**
   * Determines if there is a tile diagonally above and to the right that matches the player's color (therefore the tile(s) between need to be flipped)
   * @param p Which player played the move
   * @param a The button that was clicked
   * @return The number of opponent's tiles diagonally that need to be flipped
   */ 
  public int upRightMatch (boolean p, JButton a) {
    int count = 1;
    for (int i = getRow(a) - 2, j = getColumn(a) + 2; i >= 0 && j < getButtonArray()[getRow(a)].length; i--, j++) {
      if (getButtonArray()[i][j].getBackground().equals(Color.lightGray)) 
        return -1;
      else if (getButtonArray()[i][j].getBackground().equals(getPlayerColor(p))) 
        return count;
      count++;
    }
    return -1;
  }
  
  /**
   * Determines if there is a tile diagonally below and to the left that matches the player's color (therefore the tile(s) between need to be flipped)
   * @param p Which player played the move
   * @param a The button that was clicked
   * @return The number of opponent's tiles diagonally that need to be flipped
   */ 
  public int downLeftMatch (boolean p, JButton a) {
    int count = 1;
    for (int i = getRow(a) + 2, j = getColumn(a) - 2; i < getButtonArray().length && j >= 0; i++, j--) {
      if (getButtonArray()[i][j].getBackground().equals(Color.lightGray)) 
        return -1;
      else if (getButtonArray()[i][j].getBackground().equals(getPlayerColor(p))) 
        return count;
      count++;
    }
    return -1;
  }
  
  /**
   * Determines if there is a tile diagonally below and to the right that matches the player's color (therefore the tile(s) between need to be flipped)
   * @param p Which player played the move
   * @param a The button that was clicked
   * @return The number of opponent's tiles diagonally that need to be flipped
   */ 
  public int downRightMatch (boolean p, JButton a) {
    int count = 1;
    for (int i = getRow(a) + 2, j = getColumn(a) + 2; i < getButtonArray().length && j < getButtonArray()[getRow(a)].length; i++, j++) {
      if (getButtonArray()[i][j].getBackground().equals(Color.lightGray)) 
        return -1;
      else if (getButtonArray()[i][j].getBackground().equals(getPlayerColor(p))) 
        return count;
      count++;
    }
    return -1;
  }
  
  
  /*********************************************METHODS THAT DETERMINE OPPOSITE COLOR AROUND**************************************/
  
  /**
   * Determines if the tile to the left of the tile clicked is of the opponent's color
   * @param p Which player clicked the tile
   * @param a The button that was clicked
   * @return Whether or not the tile to the left is the opponent's color
   */
  public boolean oppositeColorToLeft (boolean p, JButton a) {
    if (getColumn(a) != 0) {
      if (getButtonArray()[getRow(a)][getColumn(a) - 1].getBackground().equals(getOpponentColor(p))) 
        return true;
      else 
        return false;
    }
    else
      return false;
  }
  /**
   * Determines if the tile to the right of the tile clicked is of the opponent's color
   * @param p Which player clicked the tile
   * @param a The button that was clicked
   * @return Whether or not the tile to the right is the opponent's color
   */ 
  public boolean oppositeColorToRight (boolean p, JButton a) {
    if (getColumn(a) != getButtonArray()[getRow(a)].length - 1) {
      if (getButtonArray()[getRow(a)][getColumn(a) + 1].getBackground().equals(getOpponentColor(p))) 
        return true;
      else 
        return false;
    }
    else
      return false;
  }
  
  /**
   * Determines if the tile directly above the tile clicked is of the opponent's color
   * @param p Which player clicked the tile
   * @param a The button that was clicked
   * @return Whether or not the tile above is the opponent's color
   */ 
  public boolean oppositeColorUp (boolean p, JButton a) {
    if (getRow(a) != 0) {
      if (getButtonArray()[getRow(a) - 1][getColumn(a)].getBackground().equals(getOpponentColor(p))) 
        return true;
      else 
        return false;
    }
    else
      return false;
  }
  
  /**
   * Determines if the tile directly below the tile clicked is of the opponent's color
   * @param p Which player clicked the tile
   * @param a The button that was clicked
   * @return Whether or not the tile below is the opponent's color
   */ 
  public boolean oppositeColorDown (boolean p, JButton a) {
    if (getRow(a) != getButtonArray().length - 1) {
      if (getButtonArray()[getRow(a) + 1][getColumn(a)].getBackground().equals(getOpponentColor(p))) 
        return true;
      else 
        return false;
    }
    else
      return false;
  }
  
  /**
   * Determines if the tile diagonally above and to the left of the tile clicked is of the opponent's color
   * @param p Which player clicked the tile
   * @param a The button that was clicked
   * @return Whether or not the tile diagonally above and to the left is opponent's color
   */ 
  public boolean oppositeColorUpLeft (boolean p, JButton a) {
    if (getRow(a) != 0 && getColumn(a) != 0) {
      if (getButtonArray()[getRow(a) - 1][getColumn(a) - 1].getBackground().equals(getOpponentColor(p))) 
        return true;
      else
        return false;
    }
    else
      return false;
  }
  
  /**
   * Determines if the tile diagonally above and to the right of the tile clicked of the opponent's color
   * @param p Which player clicked the tile
   * @param a The button that was clicked
   * @return Whether or not the tile diagonally above and to the right is opponent's color
   */ 
  public boolean oppositeColorUpRight (boolean p, JButton a) {
    if (getRow(a) != 0 && getColumn(a) < getButtonArray()[getRow(a)].length - 1) {
      if (getButtonArray()[getRow(a) - 1][getColumn(a) + 1].getBackground().equals(getOpponentColor(p)))
        return true;
      else
        return false;
    }
    else 
      return false;
  }
  
  /**
   * Determines if the tile diagonally below and to the left of the tile clicked is of the opponent's color
   * @param p Which player clicked the tile
   * @param a The button that was clicked
   * @return Whether or not the tile diagonally below and to the left is opponent's color
   */ 
  public boolean oppositeColorDownLeft (boolean p, JButton a) {
    if (getRow(a) < getButtonArray().length - 1 && getColumn(a) != 0) {
      if (getButtonArray()[getRow(a) + 1][getColumn(a) - 1].getBackground().equals(getOpponentColor(p)))
        return true;
      else
        return false;
    }
    else
      return false;
  }
  
  /**
   * Determines if the tile diagonally below and to the right of the tile clicked is of the opponent's color
   * @param p Which player clicked the tile
   * @param a The button that was clicked
   * @return Whether or not the tile diagonally below and to the right is opponent's color
   */ 
  public boolean oppositeColorDownRight (boolean p, JButton a) {
    if (getRow(a) < getButtonArray().length - 1 && getColumn(a) < getButtonArray()[getRow(a)].length - 1) {
      if (getButtonArray()[getRow(a) + 1][getColumn(a) + 1].getBackground().equals(getOpponentColor(p)))
        return true;
      else
        return false;
    }
    else
      return false;
  }
  
  /********************************************************METHODS THAT GET SOMETHING**********************************************/
  
  /**
   * Returns the index of the column the pushed button is found in
   * @param a The button that was clicked
   * @return The index of the column the button is in
   */ 
  public int getColumn(JButton a) {
    int realj = -1;
    for (int i = 0; i < getButtonArray().length; i++) {
      for (int j = 0; j < getButtonArray()[i].length; j++) {
        if (getButtonArray()[i][j].equals(a))
          realj = j;
      }
    }
    return realj;
  }
  
  /**
   * Returns the index of the row the pushed button is found in
   * @param a The button that was clicked
   * @return The index of the row the button is in
   */ 
  public int getRow(JButton a) {
    int reali = -1;
    for (int i = 0; i < getButtonArray().length; i++) {
      for (int j = 0; j < getButtonArray()[i].length; j++) {
        if (getButtonArray()[i][j].equals(a))
          reali = i;
      }
    }
    return reali;
  }
  
  /** 
   * Determines the opponents color
   * @param p If the player is player1 or not
   * @return The color of the opponent
   */
  public Color getOpponentColor(boolean p) {
    return getPlayerColor(!p);
  }
  
  /**
   * Determines the player's color
   * @param p If the player is player1
   * @return The color of the player
   */
  public Color getPlayerColor(boolean p) {
    if (p)
      return player1Color;
    else
      return player2Color;
  }
  
  /**
   * Gets the array of buttons
   * @return The array of buttons
   */
  public JButton[][] getButtonArray() {
    return this.buttonArray;
  }
  
  /*********************************************************INNER CLASSES*********************************************************/
  
  /**
   * Inner class for setting player's colors by changing their button background
   */ 
  private class PlayerButtonActionListener implements ActionListener {
    
    @Override
    public void actionPerformed (ActionEvent e) {
      JButton b = (JButton)e.getSource();
      b.setBackground(colorChooser.getColor());
    }
  }
  
  /**
   * Inner class for starting the game and setting up the board with repsective colors
   */ 
  private class PlayGameActionListener implements ActionListener {
    
    @Override 
    public void actionPerformed (ActionEvent e) {
      
      //creates pop up
      JButton b = (JButton)e.getSource();
      player1Color = player1Button.getBackground();
      player2Color = player2Button.getBackground();
      JFrame f = new JFrame();
      JButton errorButton = new JButton();
      f.getContentPane().add(errorButton, "Center");
      f.setSize(400, 100);
      f.setLocation(d.width/2 - 200, d.height/2 - 50);
      if (player1Color.equals(player2Color)) {
        //Displays pop up window saying player's cannot have same color
        errorButton.setText("Players cannot have the same color");
        f.setVisible(true);
      }
      else if (player1Color.equals(Color.lightGray) || player2Color.equals(Color.lightGray)) {
        errorButton.setText("Cannot choose same color as background (light grey)");
        f.setVisible(true);
      }
      else {
        //Closes the window
        colorChooserFrame.dispose();
        f.dispose();
        //Sets up middle squares
        getButtonArray()[getButtonArray().length/2 - 1][getButtonArray()[0].length/2 - 1].setBackground(getPlayerColor(player1));
        getButtonArray()[getButtonArray().length/2][(getButtonArray()[0].length/2)].setBackground(getPlayerColor(player1));
        getButtonArray()[getButtonArray().length/2][getButtonArray()[0].length/2 - 1].setBackground(getPlayerColor(!player1));
        getButtonArray()[getButtonArray().length/2 - 1][(getButtonArray()[0].length/2)].setBackground(getPlayerColor(!player1));
        //Sets up display of whose turn it is to player 1
        whichPlayer.setBackground(getPlayerColor(true));
      }
    }
  }  
  
  /*********************************************************MAIN METHOD*********************************************************/
  
  /**
   * Main method
   */ 
  public static void main(String[] args) {
    try {
      if (args.length == 0)
        new Reversi();
      else if (args.length == 1) 
        new Reversi(Integer.parseInt(args[0]));
      else if (args.length == 2)
        new Reversi(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
      else
        System.out.println("Can only accomodate 0, 1, or 2 inputs");
    }
    catch (NumberFormatException e) {
      System.out.println("Can only accommodate ints");
    }
  }
}