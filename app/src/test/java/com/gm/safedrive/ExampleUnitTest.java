package com.gm.safedrive;

import com.gm.safedrive.banks.ModelBank;
import com.gm.safedrive.banks.UserBank;
import com.gm.safedrive.banks.dictionnaries.ModelDictionnary;
import com.gm.safedrive.models.User;
import com.gm.safedrive.models.Vehicle;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
    @Test
    public void returningRightUser(){
        List<User> users = new ArrayList<>();
        users.add(new User(
                "userId",
                "createdDate",
                "email",
                "password",
                "firstName",
                "lastName",
                656895348,
                new ArrayList<Vehicle>()));
        assertNull(new UserBank().getUserByEmailAndPassword("zjzjzj", "password", users));
    }
}