package com;

import static org.junit.Assert.assertTrue;
import asciiPanel.*;
import org.junit.Test;

public class AsciiTest {
    AsciiFont font=new AsciiFont("resources/redjack16.png",16 , 16);
    @Test
    public void testGetFontFilename() {
        assertTrue(font.getFontFilename()=="resources/redjack16.png");
    }
    @Test
    public void testGetHeight() {
        assertTrue(font.getHeight()==16);
    }
    @Test
    public void testGetWidth() {
        assertTrue(font.getWidth()==16);
    }
    @Test
    public void testGetCharHeight() {
    
    }
    @Test
    public void testGetCharWidth() {
    }
    @Test
    public void testGetWidthInCharacters() {

    }
    @Test
    public void testGetHeightInCharacters() {

    }

}