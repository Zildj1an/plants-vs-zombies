package tp.logic.factories;

import tp.logic.objects.*;

public class PlantFactory {

    private static Plant[] availablePlants = {new Peashooter(), new Sunflower(), new CherryBoom(), new WallNut()};

    public static Plant getPlant(String plantName){
        Plant p = null;

        switch (plantName){
            case "peashooter":
            case "p":
                p = new Peashooter();
                break;
            case "sunflower":
            case "s":
                p = new Sunflower();
                break;
            case "cherryBoom":
            case "c":
                p = new CherryBoom();
            case "wallnut":
            case "n":
                p = new WallNut();
        }

        return p;
    }

    public static String listOfAvilablePlants() {
        StringBuilder sb = new StringBuilder();
        for (Plant p: availablePlants) {
            sb.append(p.getInfo()).append("\n");
        }
        return sb.toString();
    }
}
