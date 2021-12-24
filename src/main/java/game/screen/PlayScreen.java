/*
 * Copyright (C) 2015 Aeranythe Echosong
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package screen;
import java.util.Random;
import world.*;
import asciiPanel.AsciiPanel;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aeranythe Echosong
 */
public class PlayScreen implements Screen {

    private World world;
    private Player player;
    private int screenWidth;
    private int screenHeight;
    private List<String> messages;
    private List<String> oldMessages;
    int countTime=0;
    public PlayScreen() {
        this.screenWidth = 30;
        this.screenHeight = 20;
        createWorld();
        this.messages = new ArrayList<String>();
        this.oldMessages = new ArrayList<String>();

        CreatureFactory creatureFactory = new CreatureFactory(this.world);
        createCreatures(creatureFactory);

    }


    private void createCreatures(CreatureFactory creatureFactory) {
        this.player = (Player) creatureFactory.newPlayer(this.messages);
        for (int i = 0; i < 3; i++) {
            creatureFactory.newShrem(this.messages);
            creatureFactory.newBat(this.messages);
            creatureFactory.newSkeleton(this.messages);
            creatureFactory.newBull(messages);
            creatureFactory.newDragon(messages);
          
        }   
        creatureFactory.newDiamond(messages);
            new Thread(     //循环生成怪物，时间越长怪物越多越强
            ()->{
                    while (true){
                        try {
                            Thread.sleep(7000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                            countTime++;    
                            for(int i=0;i<2;i++){
                            Random rand = new Random();
                            int level=rand.nextInt(30)+countTime;
                            if(level<=10)
                            creatureFactory.newShrem(this.messages);
                            else if(level<=20)
                            creatureFactory.newBat(this.messages);
                            else 
                            creatureFactory.newSkeleton(this.messages);
                            }
                    }
            }
        ).start();
    }

    private void createWorld() {
        world = new WorldBuilder(30, 20).makeCaves().build();
    }

    private void displayTiles(AsciiPanel terminal, int left, int top) {
        // Show terrain
        for (int x = 0; x < screenWidth; x++) {
            for (int y = 0; y < screenHeight; y++) {
                terminal.write((char)61, x, y, Color.gray);
                int wx = x + left;
                int wy = y + top;
                    terminal.write(world.glyph(wx, wy), x, y, world.color(wx, wy));
            }
        }
        // Show creatures
        for (Creature creature : world.getCreatures()) {
            if (creature.x() >= left && creature.x() < left + screenWidth && creature.y() >= top
                    && creature.y() < top + screenHeight) {
                if (player.canSee(creature.x(), creature.y())) {
                    terminal.write(creature.glyph(), creature.x() - left, creature.y() - top, creature.color());
                }
            }
        }
        // Creatures can choose their next action now
        world.update();
    }

    private void displayMessages(AsciiPanel terminal, List<String> messages) {
        for (int i = 0; i < messages.size(); i++) {
            terminal.write(messages.get(i), 1, this.screenHeight+i);
        }
        this.oldMessages.addAll(messages);
        messages.clear();
    }
        int shopCost=20;
    @Override
    public Screen displayOutput(AsciiPanel terminal) {
        // Terrain and creatures
        displayTiles(terminal, getScrollX(), getScrollY());
        // Player
        terminal.write(player.glyph(), player.x() - getScrollX(), player.y() - getScrollY(), player.color());
        // Stats
        String hpStat = String.format("%3d:%3d HP", player.hp(), player.maxHP());
        terminal.write(hpStat, this.screenWidth+1, 17);
        String expStat = String.format("%3d:%3d ExP", player.exp(),player.maxExp());
        terminal.write(expStat, this.screenWidth+1, 18);
        String attackStat = String.format("%3d ATTACK", player.attackValue());
        terminal.write(attackStat, this.screenWidth+1, 19);
        String defenseStat = String.format("%3d DEFENSE", player.defenseValue());
        terminal.write(defenseStat, this.screenWidth+1, 20);
        String moneyStat = String.format("%3d MONEY", player.money());
        terminal.write(moneyStat, this.screenWidth+1, 21);
        //Shop
        terminal.write("Shop",this.screenWidth+1,7);
        String shopStat= String.format("PAY %s TO BUY",shopCost);
        terminal.write(shopStat,this.screenWidth+1,8);
        terminal.write("F1 ATTACK ADD 1",this.screenWidth+1,9);
        terminal.write("F2 DEFENSE ADD 1",this.screenWidth+1,10);
        terminal.write("F3 Hp add 300",this.screenWidth+1,11);
        terminal.write("PRESS F4 ",this.screenWidth+1,0);
        terminal.write("TO SHOW NEARBY",this.screenWidth+1,1);
        terminal.write("ENEMY DATA",this.screenWidth+1,2);
        //Grade
        String Grade= String.format("GRADE %s",player.getGrade());
        terminal.write(Grade,this.screenWidth+1,28);
        // Messages
        displayMessages(terminal, this.messages);
        if(player.hp()<=0){       
            return new LoseScreen();
        }
        return this;
    }
    public void enemyAltras(){
        for(int i=-1;i<=1;i++){
            for(int j=-1;j<=1;j++){
                if(!(i==0&&j==0)){
                Creature other = world.creature(player.x() + i, player.y() + j);
                    if (other == null) {
                     } else if(other.ifDiamond()==false) {
                    player.showEnemy(other);
                    }
                }
            }
        }
    }
    public void buyThing(int type){
        if(player.money()>=shopCost){
            player.getMoney(-shopCost);
            switch (type){
            case 1:
                player.modifyattackValue(1);
                break;
            case 2:
                player.modifydefenseValue(1);
                break;
            case 3:
                player.modifyHP(300);
                break;
            }
            shopCost+=4;
        }
    }
    @Override
    public Screen respondToUserInput(KeyEvent key) {
        switch (key.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                player.moveBy(-1, 0);
                break;
            case KeyEvent.VK_RIGHT:
                player.moveBy(1, 0);
                break;
            case KeyEvent.VK_UP:
                player.moveBy(0, -1);
                break;
            case KeyEvent.VK_DOWN:
                player.moveBy(0, 1);
                break;
            case KeyEvent.VK_F1:
                this.buyThing(1);
                break;
            case KeyEvent.VK_F2:
                this.buyThing(2);
                break;
            case KeyEvent.VK_F3:
                this.buyThing(3);
                break;
            case KeyEvent.VK_F4:
                this.enemyAltras();
                break;
        }
        return this;
    }

    public int getScrollX() {
        return Math.max(0, Math.min(player.x() - screenWidth / 2, world.width() - screenWidth));
    }

    public int getScrollY() {
        return Math.max(0, Math.min(player.y() - screenHeight / 2, world.height() - screenHeight));
    }

}
