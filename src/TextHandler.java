import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * The Class for Text handler.
 */
public class TextHandler {

    /**
     * Initialise world.
     *
     * @param world   the world
     * @param lvlText the lvl CSV file
     */
    public static void initialiseWorld(World world, String lvlText) {

        // Initialise variable to put the data from the CSV
        ArrayList<Sprite> sprites = new ArrayList<>();
        ArrayList<Tile> tiles = new ArrayList<>();
        ArrayList<Obstacle> obstacles = new ArrayList<>();
        ArrayList<Displacer> displacer = new ArrayList<>();

        String data;
        String[] info;

        String type;
        float x;
        float y;
        String imgSrc;

        // Read the CSV file
        try (BufferedReader br = new BufferedReader(new FileReader(lvlText))) {
            while ((data = br.readLine()) != null) {
                if (!data.contains("#") && data.contains(",")){

                    // Get the type and position of the object from CSV
                    info = data.split(",");
                    type = info[0];
                    x = Integer.parseInt(info[1]);
                    y = Integer.parseInt(info[2]);

                    // Get the image source depending on the object type
                    imgSrc = TextHandler.determineImage(type);

                    // Initialise the object depending on the type gotten from the CSV
                    switch(type){

                        case "water":
                            tiles.add(new Water(x,y,imgSrc));
                            sprites.add(new Water(x,y,imgSrc));
                            break;

                        case "grass":
                            tiles.add(new Grass(x,y,imgSrc));
                            sprites.add(new Grass(x,y,imgSrc));
                            break;

                        case "tree":
                            tiles.add(new Tree(x,y,imgSrc));
                            sprites.add(new Tree(x,y,imgSrc));
                            break;

                        case "bus":
                            obstacles.add(new Bus(imgSrc,x,y,Boolean.parseBoolean(info[3])));
                            sprites.add(new Bus(imgSrc,x,y,Boolean.parseBoolean(info[3])));
                            break;

                        case "racecar":
                            obstacles.add(new Car(imgSrc,x,y,Boolean.parseBoolean(info[3])));
                            sprites.add(new Car(imgSrc,x,y,Boolean.parseBoolean(info[3])));
                            break;

                        case "bike":
                            obstacles.add(new Bike(imgSrc,x,y,Boolean.parseBoolean(info[3])));
                            sprites.add(new Bike(imgSrc,x,y,Boolean.parseBoolean(info[3])));
                            break;

                        case "bulldozer":
                            displacer.add(new Bulldozer(imgSrc,x,y,Boolean.parseBoolean(info[3])));
                            sprites.add(new Bulldozer(imgSrc,x,y,Boolean.parseBoolean(info[3])));
                            break;

                        case "log":
                            displacer.add(new Log(imgSrc,x,y,Boolean.parseBoolean(info[3])));
                            sprites.add(new Log(imgSrc,x,y,Boolean.parseBoolean(info[3])));
                            break;

                        case "longLog":
                            displacer.add(new LongLog(imgSrc,x,y,Boolean.parseBoolean(info[3])));
                            sprites.add(new LongLog(imgSrc,x,y,Boolean.parseBoolean(info[3])));
                            break;

                        case "turtle":
                            displacer.add(new Turtle(imgSrc,x,y,Boolean.parseBoolean(info[3])));
                            sprites.add(new Turtle(imgSrc,x,y,Boolean.parseBoolean(info[3])));
                            break;

                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        // Set the ArrayList in the world to the filled ArrayList
        world.setSpriteSet(sprites);
    }


    /**
     * Determine image string.
     *
     * @param type the type of the object
     * @return the image source
     */
    private static String determineImage(String type){

        // Exception for turtle as it has Turtles.png for its file
        if (type.equals("turtle")){
            return "assets/" + type + "s.png";
        } else {
            return "assets/" + type + ".png";
        }

    }

}

