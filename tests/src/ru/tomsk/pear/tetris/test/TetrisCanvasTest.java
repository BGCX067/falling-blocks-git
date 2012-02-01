package ru.tomsk.pear.tetris.test;

import java.util.Arrays;
import java.util.Random;

import ru.tomsk.pear.tetris.TetrisCanvas;
import ru.tomsk.pear.tetris.TetrisShape;
import junit.framework.TestCase;

/**
 * @author Akato
 *
 */
public class TetrisCanvasTest extends TestCase {
	
	TetrisCanvasTestable _canvas;
	byte[][] _testCanvasMatrix;
	
	protected void setUp() throws Exception
	{
		super.setUp();
		
		_canvas = new TetrisCanvasTestable();
		_canvas.start();
		
		_testCanvasMatrix = newMatrix();
		
	}
	
	private byte[][] newMatrix()
	{
		byte[][] newMatrix = new byte[TetrisCanvasTestable.CANVAS_H][TetrisCanvasTestable.CANVAS_W];
		for(byte[] row : newMatrix) {
			Arrays.fill(row, (byte) 0);
		}
		
		return newMatrix;
	}
	
	public void testOnCreate()
	{
		assertTrue("Canvas erased at the start of the game", compareMatrix(_testCanvasMatrix, _canvas.getCanvas()));
		assertNotNull("Canvas object has shape object at the start", _canvas.getShape());
	}
	
	private boolean compareMatrix(byte[][] expected, byte[][] actual)
	{
		for(int i = 0; i < TetrisCanvasTestable.CANVAS_H; i++) {
			for(int j = 0; j < TetrisCanvasTestable.CANVAS_W; j++) {
				if (expected[i][j] != actual[i][j]) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public void testEraseCanvas() 
	{
		_canvas.fillCanvasRandom();
		_canvas.eraseCanvas();
		
		assertTrue("Canvas fully erased", compareMatrix(_testCanvasMatrix, _canvas.getCanvas()));
	}
		
	public void testStart()
	{
		_canvas.start();
		assertTrue(compareMatrix(_testCanvasMatrix, _canvas.getCanvas()));
		assertEquals(0, _canvas.getScores());
	}
	
	public void testCloneCanvas()
	{
		int i = 1;
		for(byte[] row : _testCanvasMatrix) {
			if ((i % 2) == 0) {
				Arrays.fill(row, (byte) 0);
			}
			i++;
		}
		
		_canvas.setCanvasMatrix(_testCanvasMatrix);	
		assertTrue("Canvases has the same content", compareMatrix(_testCanvasMatrix, _canvas.getCanvas()));
		assertNotSame("CloneMatrix method creates new matrix object(deep copy)", _canvas.getCanvasWithoutCopying(), _canvas.getCanvas());
	}
	
	public void testGameOver()
	{
		fillCanvas();
		for(byte[] row : _testCanvasMatrix) {
			row[0] = 0;
		}
		
		_canvas.setCanvasMatrix(_testCanvasMatrix);
		assertTrue("Game overs when blocks reached top of the canvas", _canvas.isGameOver());
	}
	
	private void fillCanvas()
	{
		for(byte[] row : _testCanvasMatrix) {
			Arrays.fill(row, (byte) 1);
		}
	}
	
	public void testEraseLine()
	{
		fillCanvas();
		
		int i = 1;
		int uncompleteLines = 5;
		for(byte[] row : _testCanvasMatrix) {
			if (uncompleteLines >= i) {
				row[0] = 0;
			}
			i++;
		}
		
		_canvas.setCanvasMatrix(_testCanvasMatrix);
		_canvas.countScores();
		byte[][] canvasAfterEraseLine = _canvas.getCurCanvas();
		byte[] pattern = new byte[TetrisCanvasTestable.CANVAS_W];
		Arrays.fill(pattern, (byte) 1);
		pattern[0] = 0;
		
		boolean isEqual = true;		
		for(i = TetrisCanvasTestable.CANVAS_H - uncompleteLines; i < TetrisCanvasTestable.CANVAS_H; i++) {
			if (!Arrays.equals(canvasAfterEraseLine[i], pattern)) {
				isEqual = false;
			}
		}
		assertTrue("Completed lines erased", isEqual);
		
		Arrays.fill(pattern, (byte) 0);
		assertTrue("Blocks falled down by correct range of lines", Arrays.equals(canvasAfterEraseLine[TetrisCanvasTestable.CANVAS_H - uncompleteLines - 1], pattern));
	}
	
	/*
	public void testShapeMove()	{}
		
	public void testGetActualizedMatrix() {}
	
	public void testQuickFall() {}
	*/
}


/**
 * Helper class for testing
 * @author Akato
 *
 */
class TetrisCanvasTestable extends TetrisCanvas
{
	public void fillCanvasRandom()
	{
		Random rand = new Random();
		
		eraseCanvas();
		for(byte[] row : canvas) {
			if (rand.nextInt(10) > 4) {
				Arrays.fill(row, (byte) 0);
			}
		}
	}
	
	public TetrisShape getShape()
	{
		return shape;
	}
	
	public void setCanvasMatrix(byte[][] newCanvas) 
	{
		for(int i = 0; i < TetrisCanvasTestable.CANVAS_H; i++) {
			for(int j = 0; j < TetrisCanvasTestable.CANVAS_W; j++) {
				canvas[i][j] = newCanvas[i][j];
			}
		}
	}
	
	public byte[][] getCanvasWithoutCopying()
	{
		return canvas;
	}
}
