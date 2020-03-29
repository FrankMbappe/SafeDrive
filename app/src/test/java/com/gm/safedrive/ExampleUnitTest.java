package com.gm.safedrive;

import com.gm.safedrive.banks.ModelBank;
import com.gm.safedrive.banks.dictionnaries.ModelDictionnary;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void returning_right_model(){
        assertEquals(ModelDictionnary.CODE_MBCCLASS300, new ModelBank().getModelByName("c-class").getCode());
    }
}