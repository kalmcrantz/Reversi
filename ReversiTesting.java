/**
 * Tests Reversi
 * @author Kimberly Almcrantz
 */

import org.junit.*;
import static org.junit.Assert.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//Tests Reversi

public class ReversiTesting {
  
  @Test
  public void testEmptySpace() {
     
    Reversi a = new Reversi();
    a.setVisible(false);
    
    //Test 0 (is an empty space) and middle (middle of board)
    assertTrue("Incorrectly says the empty space is taken", a.emptySpace(a.getButtonArray()[2][2]));
    //Test 1 (space is taken by player 1 or player 2)
    assertFalse("Incorrectly says the space taken by player 1 is empty", a.emptySpace(a.getButtonArray()[3][3]));
    assertFalse("Incorrectly says the space taken by player 2 is empty", a.emptySpace(a.getButtonArray()[4][3]));
    //Test first (first in row and column)
    assertTrue("Incorrectly says is not an empty space if first in row and column", a.emptySpace(a.getButtonArray()[0][0]));
    //Test last (last in row and column
    assertTrue("Incorrectly says it is not an empty space if last in row and column", a.emptySpace(a.getButtonArray()[7][7]));
  }
  
  @Test
  public void testOpposites() {  
    
    Reversi a = new Reversi();
    a.setVisible(false);
    
    //Testing middle in all directions
    //Test many (touching many tiles)
    assertTrue("Incorrectly claims the tile to the left is not of the opponent's color", a.oppositeColorToLeft(true, a.getButtonArray()[3][5]));
    assertTrue("Incorrectly claims the tile to the right is not of the opponent's color", a.oppositeColorToRight(true, a.getButtonArray()[4][2]));
    assertTrue("Incorrectly claims the tile above is not of the opponent's color", a.oppositeColorUp(true, a.getButtonArray()[5][3]));
    assertTrue("Incorrectly claims the tile below is not of the opponent's color", a.oppositeColorDown(true, a.getButtonArray()[2][4]));
    
    //Test 1 (only touching 1 tile)
    assertTrue("Incorrectly claims the tile above and to the left is not of the opponent's color", a.oppositeColorUpLeft(true, a.getButtonArray()[5][4]));
    assertTrue("Incorrectly claims the tile above and to the right is not of the opponent's color", a.oppositeColorUpRight(true, a.getButtonArray()[5][2]));
    assertTrue("Incorrectly claims the tile below and to the left is not of the opponent's color", a.oppositeColorDownLeft(true, a.getButtonArray()[2][5]));
    assertTrue("Incorrectly claims the tile below and to the right is not of the opponent's color", a.oppositeColorDownRight(true, a.getButtonArray()[2][3]));
    
    //Test first and 0
    assertFalse("Incorrectly claims the tile to the right is the opponent's color when the tile is surrounded by blank pieces", a.oppositeColorToRight(true, a.getButtonArray()[0][0]));
    //Test last and 0
    assertFalse("Incorrectly claims the tile to the left is the opponent's color when the tile is surrounded by blank pieces", a.oppositeColorToLeft(true, a.getButtonArray()[7][7]));
    
    //Testing false cases
    assertFalse("Incorrectly claims the tile to the left is the opponent's color", a.oppositeColorToLeft(false, a.getButtonArray()[3][5]));
    assertFalse("Incorrectly claims the tile above is the opponent's color", a.oppositeColorToRight(false, a.getButtonArray()[4][2]));
  }
  
  @Test
  public void testMatches() {
    
    Reversi a = new Reversi();
    a.setVisible(false);
    
    //creates a partially made board
    a.getButtonArray()[4][1].setBackground(a.getPlayerColor(false));
    a.getButtonArray()[3][2].setBackground(a.getPlayerColor(false));
    a.getButtonArray()[1][3].setBackground(a.getPlayerColor(true));
    a.getButtonArray()[2][3].setBackground(a.getPlayerColor(true));
    a.getButtonArray()[2][5].setBackground(a.getPlayerColor(false));
    a.getButtonArray()[3][5].setBackground(a.getPlayerColor(true));
    
    //test 0 (no match) and last (last in row and column)
    assertEquals("Does not return -1 when there is no match to the left", -1, a.leftMatch(true, a.getButtonArray()[7][7]));
    //test 1 and middle in all directions
    assertEquals("Incorrectly gives location of another white tile to the left", 3, a.leftMatch(false, a.getButtonArray()[4][5]));
    assertEquals("Incorrectly gives location of another black tile to the right", 4, a.rightMatch(true, a.getButtonArray()[4][2]));
    assertEquals("Incorrectly gives location of another white tile above", 2, a.upMatch(false, a.getButtonArray()[4][5]));
    assertEquals("Incorrectly gives location of another black tile below", 3, a.downMatch(true, a.getButtonArray()[1][5]));
    assertEquals("Incorrectly gives number of white tiles between space clicked and another black tile in diagonal line above and to the left", 1, a.upLeftMatch(true, a.getButtonArray()[4][5]));
    assertEquals("Incorrectly gives number of white tiles between space clicked and another black tile in diagonal line above and to the right", 2, a.upRightMatch(true, a.getButtonArray()[5][0]));
    assertEquals("Incorrectly gives number of black tiles between space clicked and another white tile in diagonal line below and to the left", 1, a.downLeftMatch(false, a.getButtonArray()[1][4]));
    assertEquals("Incorrectly gives number of black tiles between space clicked and another white tile in diagonal line below and to the right", 1, a.downRightMatch(false, a.getButtonArray()[1][2]));
    //Test first because it's the first row and  many (multiple tiles between)
    assertEquals("Incorrectly gives location of another white tile when multiple black tiles are between", 4, a.downMatch(false, a.getButtonArray()[0][3]));
    //Test last
  }
  
  @Test
  public void testGetRow() {
    
    Reversi a = new Reversi();
    a.setVisible(false);
    //Test first
    assertEquals("Incorrectly returns the row index of the first row", 0, a.getRow(a.getButtonArray()[0][5]));
    //Test middle
    assertEquals("Incorrectly returns the row index of a row in the middle", 3, a.getRow(a.getButtonArray()[3][4]));
    //Test last
    assertEquals("Incorrectly returns the row index of the last row", 7, a.getRow(a.getButtonArray()[7][7]));
    //Test zero
    assertEquals("Doesn't return -1 if the button was not found in the array", -1, a.getRow(new JButton()));
  }
  
  @Test
  public void testGetColumn() {
    
    Reversi a = new Reversi();
    a.setVisible(false);
    //test first
    assertEquals("Incorrectly returns the column index of the first column", 0, a.getColumn(a.getButtonArray()[5][0]));
    //test middle
    assertEquals("Incorrectly returns the column index of a middle column", 5, a.getColumn(a.getButtonArray()[0][5]));
    //test last
    assertEquals("Incorrectly returns the column index of the last column", 7, a.getColumn(a.getButtonArray()[5][7]));
    //test 0
    assertEquals("Doesn't return -1 if the button was not found in the array", -1, a.getRow(new JButton()));
  }
  
  @Test
  public void testVariousBoardSizes() {
    
    //Default board size of 8x8
    Reversi board8x8 = new Reversi();
    //Board size of 3x3
    Reversi board3x3 = new Reversi(3);
    //Board size of height 5 and width 7
    Reversi board5x7 = new Reversi(5, 7);
    
    board8x8.setVisible(false);
    board3x3.setVisible(false);
    board5x7.setVisible(false);
    
    //default board
    assertEquals("Incorrectly gives height of default, 8x8 board", 8, board8x8.getButtonArray().length);
    assertEquals("Incorrectly gives the width of default, 8x8 board", 8, board8x8.getButtonArray()[0].length);
    //customized square board
    assertEquals("Incorrectly gives height of square customized board", 3, board3x3.getButtonArray().length);
    assertEquals("Incorrectly gives the width of square customized board", 3, board3x3.getButtonArray()[0].length);
    //customized board
    assertEquals("Incorrectly gives height of customized board", 5, board5x7.getButtonArray().length);
    assertEquals("Incorrectly gives the width of customized board", 7, board5x7.getButtonArray()[0].length);
    
  }
  
  @Test
  public void testGetPlayerColor() {
    
    Reversi a = new Reversi();
    a.setVisible(false);
    
    assertEquals("Incorrectly gets player 1's color", Color.black, a.getPlayerColor(true));
    assertEquals("Incorrectly gets player 2's color", Color.white, a.getPlayerColor(false));
  }
  
  @Test
  public void testGetOpponentColor() {
    
    Reversi a = new Reversi();
    a.setVisible(false);
    
    assertEquals("Incorrectly gets player 2's color", Color.white, a.getOpponentColor(true));
    assertEquals("Incorrectly gets player 1's color", Color.black, a.getOpponentColor(false));
  }
  
  @Test
  public void testMoveAllowed() {
    
    Reversi a = new Reversi();
    a.setVisible(false);
    
    //creates a partially played board
    a.getButtonArray()[4][1].setBackground(a.getPlayerColor(false));
    a.getButtonArray()[3][2].setBackground(a.getPlayerColor(false));
    a.getButtonArray()[1][3].setBackground(a.getPlayerColor(true));
    a.getButtonArray()[2][3].setBackground(a.getPlayerColor(true));
    a.getButtonArray()[2][5].setBackground(a.getPlayerColor(false));
    a.getButtonArray()[3][5].setBackground(a.getPlayerColor(true));
    
    //Test middle (middle of board)
    //Test many (allowed in multiple directions)
    assertTrue("Incorrectly says the move is not allowed when it is allowed to the left", a.moveAllowed(false, a.getButtonArray()[4][5]));
    //Test one (allowed in one direction) 
    assertTrue("Incorrectly says the move is not allowed when it is allowed to the right", a.moveAllowed(true, a.getButtonArray()[4][2]));
    assertTrue("Incorrectly says the move is not allowed when it is allowed above", a.moveAllowed(false, a.getButtonArray()[4][5]));
    assertTrue("Incorrectly says the move is not allowed when it is allowed below",  a.moveAllowed(true, a.getButtonArray()[1][5]));
    assertTrue("Incorrectly says the move is not allowed when it is allowed above and to the left", a.moveAllowed(true, a.getButtonArray()[4][5]));
    assertTrue("Incorrectly says the move is not allowed when it is allowed above to the right", a.moveAllowed(true, a.getButtonArray()[5][0]));
    assertTrue("Incorrectly says the move is not allowed when it is allowed below to the left",  a.moveAllowed(false, a.getButtonArray()[1][4]));
    assertTrue("Incorrectly says the move is not allowed when it is allowed below to the right", a.moveAllowed(false, a.getButtonArray()[1][2]));
    
    //Test first and 0 (not allowed in any direction)
    assertFalse("Incorrectly says that the move is allowed when the button is the first column and row", a.moveAllowed(false, a.getButtonArray()[0][0]));
    //Test last and 0 (not allowed in any direction)
    assertFalse("Incorrectly says the move is allowed when the button is the last column and row", a.moveAllowed(false, a.getButtonArray()[7][7]));
  }
  
  @Test
  public void testAvailableMove() {
    
    Reversi a = new Reversi(2);
    Reversi b = new Reversi();
    Reversi c = new Reversi(3);
    //creates a partially played board where player 2 does not have an available move
    c.getButtonArray()[0][2].setBackground(a.getPlayerColor(true));
    c.getButtonArray()[1][2].setBackground(a.getPlayerColor(false));
    c.getButtonArray()[2][1].setBackground(a.getPlayerColor(true));
    a.setVisible(false);
    b.setVisible(false);
    c.setVisible(false);    
    
    //Test when there are no available tiles to click
    assertFalse("Incorrectly claims there is an available move for player 1 when there are no available tiles", a.availableMove(true));
    assertFalse("Incorrectly claims there is an availalbe move for player 2 when there is no available tiles", a.availableMove(false));
    //Test when there are available moves
    assertTrue("Incorrectly claims there is no available move for player 1", b.availableMove(true));
    assertTrue("Incorrectly claims there is no availabe move for player 2", b.availableMove(false));
    //Test when there are no available move, even though there are tiles not clicked
    assertFalse("Incorrectly claims there is an available move for player 2 when none of the available tiles are allowed moves", c.availableMove(false));
  }
  
  @Test
  public void testChangeColor() {
    
    Reversi a = new Reversi ();
    a.setVisible(false);
    
    //Changes the first tile to player 1's color
    a.changeColor(true, a.getButtonArray()[0][0]);
    assertEquals("The first tile's background does not equal player 1's color", a.getPlayerColor(true), a.getButtonArray()[0][0].getBackground());
    
    //Changes the last tile to player 2's color
    a.changeColor(false, a.getButtonArray()[7][7]);
    assertEquals("The last tile's background does not equal player 2's color", a.getPlayerColor(false), a.getButtonArray()[7][7].getBackground());
  }
}  