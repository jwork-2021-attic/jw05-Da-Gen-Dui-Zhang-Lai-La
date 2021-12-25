
package world;

public class MonsterAI extends CreatureAI {

    private CreatureFactory factory;
    private int spreadcount = 0;

    public static int spores = 5;
    public static double spreadchance = 0.01;

    public MonsterAI
(Creature creature, CreatureFactory factory) {
        super(creature);
        this.factory = factory;
    }

    public void onUpdate() {
        if (this.spreadcount < MonsterAI.spores && Math.random() < MonsterAI.spreadchance) {
        }
    }
}
