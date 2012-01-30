/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.tomsk.pear.tetris.test;

import ru.tomsk.pear.tetris.Tetris;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.Keyboard.Key;
import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;


/**
 *
 * @author
 * akato
 */
public class TetrisTest extends ActivityInstrumentationTestCase2<Tetris> {
    
    private Tetris _tetris;
    
    
    public TetrisTest() 
    {
        super("ru.tomsk.pear.tetris", Tetris.class);
    }
    
    @Override
    protected void setUp() throws Exception 
    {
        super.setUp();
        _tetris = getActivity();  
    }
    
    public void testActivity()
    {
        assertNotNull(_tetris);
    }
    
    public void testGame()
    {
    	assertNotNull(_tetris.getGame());
    }
    
    public void testMenu()
    {
    	sendKeys(KeyEvent.KEYCODE_MENU);
        Menu _menu = _tetris.getMenu();
    	assertNotNull(_menu);
    	assertEquals("New game", _menu.findItem(Tetris.NEWGAME_ID).getTitle());
    	assertEquals("Controls", _menu.findItem(Tetris.CONTROLS_ID).getTitle());
    	assertEquals("About", _menu.findItem(Tetris.ABOUT_ID).getTitle());
    	assertEquals("Exit", _menu.findItem(Tetris.EXIT_ID).getTitle());
    }
    
}
