package ru.tomsk.pear.tetris.test;

import ru.tomsk.pear.tetris.TetrisShapeContainer;
import junit.framework.TestCase;

public class TetrisShapeContainerTest extends TestCase {
	
	private TetrisShapeContainer _shapes;
	
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		
		_shapes = new TetrisShapeContainer();
	}
	
	public void testShapeList() 
	{
		assertFalse("Collection of shapes is not empty", _shapes.getShapesCollection().isEmpty());
	}
	
	public void testShapeInUse()
	{
		byte[][] shape = _shapes.getShapeCanvas();		
		assertNotNull("Can get shape from collection", shape);
		byte[][] sameShape = _shapes.getShapeCanvas();
		assertNotSame("Shape copy on each request", shape, sameShape);
		
		_shapes.changeShapeInUseIndex();
		byte[][] nextShape = _shapes.getShapeCanvas();
		assertFalse("Get different shapes after changeShapeInUse", compareShapes(shape, nextShape));
	}
	
	private boolean compareShapes(byte[][] shape1, byte[][] shape2)
	{
		if (shape1.length != shape2.length || shape1[0].length != shape2[0].length) {
			return false;
		}
		for(int i = 0; i < shape1.length; i++) {
			for(int j = 0; j < shape1[0].length; j++) {
				if (shape1[i][j] != shape2[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	public void testRotateShape()
	{
		byte[][] line1 = {{0,1,0},{0,1,0},{0,1,0}};
		byte[][] line2 = {{0,0,0},{1,1,1},{0,0,0}};
		int lineIndex = 0;
		_shapes.setShapeInUse(lineIndex);
		assertTrue("Shape like vertical line", compareShapes(line1, _shapes.getShapeCanvas()));
		_shapes.rotateShapeInUse();
		assertTrue("Shape like horizontal line", compareShapes(line2, _shapes.getShapeCanvas()));
				
		byte[][] horse1 = {{1,0,0},{1,1,1},{0,0,0}};
		byte[][] horse2 = {{1,1,0},{1,0,0},{1,0,0}};
		byte[][] horse3 = {{1,1,1},{0,0,1},{0,0,0}};
		byte[][] horse4 = {{0,1,0},{0,1,0},{1,1,0}};
		int horseIndex = 4;
		_shapes.setShapeInUse(horseIndex);
		assertTrue("Shape like usual horse", compareShapes(horse1, _shapes.getShapeCanvas()));
		_shapes.rotateShapeInUse();
		assertTrue("Shape like usual onse rotated horse", compareShapes(horse2, _shapes.getShapeCanvas()));
		_shapes.rotateShapeInUse();
		assertTrue("Shape like usual twice rotated horse", compareShapes(horse3, _shapes.getShapeCanvas()));
		_shapes.rotateShapeInUse();
		assertTrue("Shape like usual thrice rotated horse", compareShapes(horse4, _shapes.getShapeCanvas()));
		
		byte[][] z1 = {{0,1,1},{1,1,0},{0,0,0}};
		byte[][] z2 = {{1,0,0},{1,1,0},{0,1,0}};
		int zIndex = 2;
		_shapes.setShapeInUse(zIndex);
		assertTrue("Shape like usual z", compareShapes(z1, _shapes.getShapeCanvas()));
		_shapes.rotateShapeInUse();
		assertTrue("Shape like usual onse rotated z", compareShapes(z2, _shapes.getShapeCanvas()));
		
	}
}
