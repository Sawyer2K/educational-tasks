package ru.cft.focusstart.task3.view;

import java.util.HashMap;
import java.util.Map;

public class GameImageIconMap {

    private static final Map<String, GameImage> cellIconMap = new HashMap<>();
    private static final Map<String, GameImage> uiIconMap = new HashMap<>();

    static {
        cellIconMap.put("closed", GameImage.CLOSED);
        cellIconMap.put("marked", GameImage.MARKED);
        cellIconMap.put("0", GameImage.EMPTY);
        cellIconMap.put("1", GameImage.NUM_1);
        cellIconMap.put("2", GameImage.NUM_2);
        cellIconMap.put("3", GameImage.NUM_3);
        cellIconMap.put("4", GameImage.NUM_4);
        cellIconMap.put("5", GameImage.NUM_5);
        cellIconMap.put("6", GameImage.NUM_6);
        cellIconMap.put("7", GameImage.NUM_7);
        cellIconMap.put("8", GameImage.NUM_8);
        cellIconMap.put("bomb", GameImage.BOMB);

        uiIconMap.put("timerUI", GameImage.TIMER);
        uiIconMap.put("mineUI", GameImage.BOMB_ICON);
    }

    public static GameImage getCellIconMap(String code) {
        return cellIconMap.get(code);
    }

    public static GameImage getUiImageIconMap(String code) {
        return uiIconMap.get(code);
    }

    public static boolean isIconCodeValid(String code) {
        return cellIconMap.containsKey(code);
    }
}
