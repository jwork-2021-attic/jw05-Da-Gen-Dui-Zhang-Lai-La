package world;
import java.awt.Color;
public class Monster extends Creature{
    int giveExp;
    public Monster(World world, char glyph,  int maxHP, int attack, int defense, int visionRadius,int giveExp,int giveMoney,int giveGrade) {
        super(world,  glyph, maxHP,  attack, defense, visionRadius);
        this.giveExp=giveExp;
        this.giveMoney=giveMoney;
        this.giveGrade=giveGrade;
    }
    public int giveExp(){
        return this.giveExp;
    }
    
}