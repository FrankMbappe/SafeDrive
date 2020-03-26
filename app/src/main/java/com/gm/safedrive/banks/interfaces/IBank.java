package com.gm.safedrive.banks.interfaces;

import java.util.ArrayList;

public interface IBank<T> {
    ArrayList<T> getAll();
}
