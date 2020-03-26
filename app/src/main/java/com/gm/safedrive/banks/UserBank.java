package com.gm.safedrive.banks;

import com.gm.safedrive.banks.interfaces.IBank;
import com.gm.safedrive.models.User;

import java.util.ArrayList;

public class UserBank {
    public static User SESSION = new User(
            "21/03/2020",
            "abc@mail.com",
            "admin",
            "Junior",
            "Essono",
            656895348,
            0,
            null
    );
}
