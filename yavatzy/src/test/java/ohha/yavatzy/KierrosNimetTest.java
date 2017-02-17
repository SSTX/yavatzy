/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohha.yavatzy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ttiira
 */
public class KierrosNimetTest {

    public KierrosNimetTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void bonusNimetOikein() {
        assertEquals("ykköset", KierrosNimet.bonusKierrosNimet()[0]);
        assertEquals("kakkoset", KierrosNimet.bonusKierrosNimet()[1]);
        assertEquals("kutoset", KierrosNimet.bonusKierrosNimet()[5]);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
