package world;
import java.awt.Color;
public class Diamond extends Creature {
    int giveAttack;
   int giveDefense;

    public Diamond(World world, char glyph, int maxHP, int attack, int defense,
     int visionRadius,int giveAttack,int giveDefense) {
        super(world,  glyph,  maxHP,  attack, defense, visionRadius);
        this.giveAttack=giveAttack;
        this.giveDefense=giveDefense;
        ifDiamond=true;
    }
   
}