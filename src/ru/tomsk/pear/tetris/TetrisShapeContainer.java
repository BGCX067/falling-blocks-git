package ru.tomsk.pear.tetris;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class TetrisShapeContainer {
	
	private ArrayList<byte[][]> _shapesCollection;
	private int _shapeInUseIndex;
	
	private Random _rand;
	
	
	public TetrisShapeContainer()
	{
		_shapesCollection = new ArrayList<byte[][]>();
		_rand = new Random(Calendar.getInstance().getTimeInMillis());
		
		initialize();
	}
	
	private void initialize()
	{
		byte[][][] shapes = { 
			{	
				{0,1,0},
				{0,1,0},
				{0,1,0}
			},
			{	
				{1,1,0},
				{0,1,1},
				{0,0,0}
			},
			{	
				{0,1,1},
				{1,1,0},
				{0,0,0}
			},
			{	
				{0,1,0},
				{1,1,1},
				{0,0,0}
			},
			{	
				{1,0,0},
				{1,1,1},
				{0,0,0}
			},
			{	
				{0,0,1},
				{1,1,1},
				{0,0,0}
			},
			{	
				{1,1,0},
				{1,1,0},
				{0,0,0}
			},
		};
		for(byte[][] shape : shapes) {
			_shapesCollection.add(shape);
		}
		
		changeShapeInUseIndex();
	}
	
	public ArrayList<byte[][]> getShapesCollection()
	{
		return _shapesCollection;
	}
	
	public byte[][] getShapeCanvas()
	{
		byte[][] shape = _shapesCollection.get(_shapeInUseIndex);
		byte[][] copy = new byte[shape.length][shape[0].length];
		for(int i = 0; i < shape.length; i++) {
			for(int j = 0; j < shape.length; j++) {
				copy[i][j] = shape[i][j];
			}	
		}
		
		return copy;
	}
	
	public void changeShapeInUseIndex()
	{
		_shapeInUseIndex = _rand.nextInt(_shapesCollection.size());
		
	}
	
	public void setShapeInUse(int index)
	{
		_shapeInUseIndex = index;
	}
	
	public void rotateShapeInUse()
	{
		//stub
	}
}
