package world;
import java.awt.Color;
public class Player extends Creature{
    int maxExp;
    int exp;
    int money;
    int grade=0;
    public Player(World world, char glyph, Color color, int maxHP, int attack, int defense, int visionRadius,int maxExp) {
        super(world,  glyph, color,  maxHP,  attack, defense, visionRadius);
        this.maxExp=maxExp;
        this.exp=0;
        //this.money=0;
        this.money=50;
    }
    public void levelUp(){
        super.modifyHP(30);
        super.modifymaxHP(15);
        super.modifyattackValue(1);
        super.modifydefenseValue(1);
       this.maxExp+=5;
    }
    public int exp(){
        return this.exp;
    }
    public int maxExp(){
        return this.maxExp;
    }
    public void getExp(int amount){
        this.exp+=amount;
        if(this.exp>=this.maxExp){
            this.exp-=this.maxExp;
            levelUp();
        }
    }
    public int money(){
        return this.money;
    }
    public void getMoney(int amount){
        this.money+=amount;
    }
    public int getGrade(){
        return this.grade;
    }
    public void modifyGrade(int x){
        this.grade+=x;
    }
    @Override
    public void attack(Creature other) {
        if(other.ifDiamond()==false){//遇到怪物
         int damage =this.attackValue() - other.defenseValue();
         int hurt=other.attackValue() - this.defenseValue();
         int totalHurt=0;
         int hp=other.hp();
            while(hp>0&&this.hp()>0){
                hp-=damage;
                this.modifyHP(-hurt);
                totalHurt+=hurt;
             }
             this.notify("YOU GET %s DAMAGE",totalHurt);
         if(this.hp()<=0)
            return;
            other.world.remove(other);
            this.modifyGrade(other.giveGrade());
         this.getExp(other.giveExp());
         this.getMoney(other.giveMoney());
         }
        else {//捡到宝石
            this.notify("YOU GET A DIAMOND");
            this.modifyattackValue(1);
             this.modifydefenseValue(1);
             other.modifyHP(-this.attackValue());
        }

    }
}