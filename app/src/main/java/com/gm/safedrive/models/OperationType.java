package com.gm.safedrive.models;

public class OperationType {
    public String Id;
    private int LogoDrawableId;
    private int MainColorId;
    private int TitleStringId;

    public OperationType(String id, int logoDrawableId, int mainColorId, int titleStringId) {
        Id = id;
        LogoDrawableId = logoDrawableId;
        MainColorId = mainColorId;
        TitleStringId = titleStringId;
    }

    public String getId() {
        return Id;
    }

    public int getLogoDrawableId() {
        return LogoDrawableId;
    }

    public void setLogoDrawableId(int logoDrawableId) {
        LogoDrawableId = logoDrawableId;
    }

    public int getMainColorId() {
        return MainColorId;
    }

    public void setMainColorId(int mainColorId) {
        MainColorId = mainColorId;
    }

    public int getTitleStringId() {
        return TitleStringId;
    }

    public void setTitleStringId(int titleStringId) {
        TitleStringId = titleStringId;
    }
}
