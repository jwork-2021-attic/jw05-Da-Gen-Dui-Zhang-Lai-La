
package world;

import java.awt.Color;
import screen.*;
/**
 *
 * @author Aeranythe Echosong
 */
public class Creature {

    public World world;

    private int x;

    public void setX(int x) {
        this.x = x;
    }

    public int x() {
        return x;
    }

    private int y;

    public void setY(int y) {
        this.y = y;
    }

    public int y() {
        return y;
    }

    private char glyph;

    public char glyph() {
        return this.glyph;
    }

    private Color color;

    public Color color() {
        return this.color;
    }

    private CreatureAI ai;

    public void setAI(CreatureAI ai) {
        this.ai = ai;
    }

    private int maxHP;

    public int maxHP() {
        return this.maxHP;
    }
    public void modifymaxHP(int amount) {
        this.maxHP += amount;
    }
    private int hp;

    public int hp() {
        return this.hp;
    }

    public void modifyHP(int amount) {
        if(this.hp+amount<=maxHP)
        this.hp += amount;
        else
        this.hp=this.maxHP;
        if (this.hp < 1) {
            world.remove(this);
        }
    }
    int giveExp;
    public int giveExp(){
        return this.giveExp;
    }
    int giveMoney;
    public int giveMoney(){
        return this.giveMoney;
    }
    int giveAttack;
   int giveDefense;
   public int giveAttack() {
    return this.giveAttack;
}
public int giveDefense() {
    return this.giveDefense;
}
    private int attackValue;

    public int attackValue() {
        return this.attackValue;
    }
    public void modifyattackValue(int amount) {
        this.attackValue += amount;
    }
    private int defenseValue;

    public int defenseValue() {
        return this.defenseValue;
    }
    public void modifydefenseValue(int amount) {
        this.defenseValue += amount;
    }
    private int visionRadius;

    public int visionRadius() {
        return this.visionRadius;
    }

    public boolean canSee(int wx, int wy) {
        return ai.canSee(wx, wy);
    }
    boolean ifDiamond=false;
    public boolean ifDiamond() {
        return ifDiamond;
    }
    public Tile tile(int wx, int wy) {
        return world.tile(wx, wy);
    }

    public void dig(int wx, int wy) {
        world.dig(wx, wy);
    }
    public void moveBy(int mx, int my) {
        Creature other = world.creature(x + mx, y + my);

        if (other == null) {
            ai.onEnter(x + mx, y + my, world.tile(x + mx, y + my));
        } else {
            attack(other);
        }
    }

    public void attack(Creature other) {
        int damage =this.attackValue() - other.defenseValue();
        int hurt=other.attackValue() - this.defenseValue();
        int totalHurt=0;
        while(other.hp()>0&&this.hp()>0){
            other.modifyHP(-damage);
            this.modifyHP(-hurt);
            totalHurt+=hurt;
         }
         this.notify("You get %s hurt",totalHurt);
    }
    public void attack(Monster other) {
        int damage =this.attackValue() - other.defenseValue();
        int hurt=other.attackValue() - this.defenseValue();
        int totalHurt=0;
        while(other.hp()>0&&this.hp()>0){
            other.modifyHP(-damage);
            this.modifyHP(-hurt);
            totalHurt+=hurt;
         }
         this.notify("You get %s hurt",totalHurt);
    }


    public void showEnemy(Creature other){
        this.notify("%s HP:%s,ATTACK:%s,DEFENSE:%s",other.glyph(),other.hp(),other.attackValue(),other.defenseValue());
        this.notify("YOU CAN GET ExP:%s,MONEY:%s ",other.giveExp(),other.giveMoney());
    }
    public void update() {
        this.ai.onUpdate();
    }
    public boolean canEnter(int x, int y) {
        return world.tile(x, y).isGround();
    }

    public void notify(String message, Object... params) {
        ai.onNotify(String.format(message, params));
    }
    int giveGrade;
    public int giveGrade(){
        return this.giveGrade;
    }
    public Creature(World world, char glyph, Color color, int maxHP, int attack, int defense, int visionRadius) {
        this.world = world;
        this.glyph = glyph;
        this.color = color;
        this.maxHP = maxHP;
        this.hp = maxHP;
        this.attackValue = attack;
        this.defenseValue = defense;
        this.visionRadius = visionRadius;
    }
}
