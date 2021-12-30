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
package world;

import java.util.List;
import java.awt.Color;
import asciiPanel.AsciiPanel;


/**
 *
 * @author Aeranythe Echosong
 */
public class CreatureFactory {

    private World world;

    public CreatureFactory(World world) {
        this.world = world;
    }

    public Creature newPlayer(List<String> messages) {
        Player player = new Player(this.world, (char)2, 1000, 12, 12, 900,25);
        world.addAtEmptyLocation(player);
        new PlayerAI(player, messages);
        return player;
    }

    public Creature newShrem(List<String> messages) {
        Monster fungus = new Monster(this.world, (char)3, 70, 15, 2, 2,2,2,1);
        world.addAtEmptyLocation(fungus);
        new MonsterAI(fungus, this);
        return fungus;
    }
    public Creature newBat(List<String> messages){
        Monster shrem = new Monster(this.world, (char)5,  100, 25, 5, 3,3,3,2);
        world.addAtEmptyLocation(shrem);
        new MonsterAI(shrem, this);
        return shrem;
    }
    public Creature newSkeleton(List<String> messages){
        Monster skeleton = new Monster(this.world, (char)7,  110, 30, 5,5,5,4,6);
        world.addAtEmptyLocation(skeleton);
        new MonsterAI(skeleton, this);
        return skeleton;
    }
    public Creature newBull(List<String> messages){
        Monster bullGuy = new Monster(this.world, (char)9, 200, 35, 10,5,5,5,8);
        world.addAtEmptyLocation(bullGuy);
        new MonsterAI(bullGuy, this);
        return bullGuy;
    }
    public Creature newDragon(List<String> messages){
        Monster dragon = new Monster(this.world, (char)9,  300, 40, 20,5,8,6,10);
        world.addAtEmptyLocation(dragon);
        new MonsterAI(dragon, this);
        return dragon;
    }
    public Creature newDiamond(List<String> messages){
        Diamond red =new Diamond(this.world, (char)11, 1, 0, 0,0,1,1);
        world.addAtEmptyLocation(red);
        new MonsterAI(red, this);
        for(int i=-1;i<=1;i++)
            for(int j=-1;j<=1;j++){
                if(i!=0||j!=0&&i!=j)
        this.newDiamondKeeper(messages, red.x()+i, red.y()+j);
        }
        return red;
    }
    public Creature newDiamondKeeper(List<String> messages,int x,int y){
        Monster red =new Monster(this.world, (char)6,  210, 30, 5,5,5,4,5);
        world.addAtChooseLocation(red,x,y);
        new MonsterAI(red, this);
        return red;
    }
}
