package com;

import static org.junit.Assert.assertTrue;
import world.*;
import java.awt.Color;

import org.junit.Test;

import world.Diamond;
public class WorldTest {
    World world = new WorldBuilder(30, 20).makeCaves().build();
    @Test
    public void testWorldWidth() {
        assertTrue(world.width()==30);
    }
    @Test
    public void testWorldHeight() {
        assertTrue(world.height()==20);
    }
    @Test
    public void testMonster() {
        final Monster skeleton = new Monster(this.world, (char) 224, Color.MAGENTA, 110, 30, 5, 5, 5, 4, 6);
        assertTrue(skeleton.hp() == 110);
        assertTrue(skeleton.attackValue() == 30);
        assertTrue(skeleton.defenseValue() == 5);
        assertTrue(skeleton.giveExp() == 5);
        assertTrue(skeleton.giveMoney() == 4);
        assertTrue(skeleton.giveGrade() == 6);
        assertTrue(skeleton.color() == Color.MAGENTA);
    }

    @Test
    public void testPlayer() {
        final Player player = new Player(this.world, (char) 2, Color.WHITE, 1000, 12, 12, 900, 25);
        assertTrue(player.hp() == 1000);
        assertTrue(player.attackValue() == 12);
        assertTrue(player.defenseValue() == 12);
        assertTrue(player.maxExp() == 25);
        assertTrue(player.money() == 50);
        assertTrue(player.color() == Color.WHITE);
    }

    @Test
    public void testDiamond() {
        final Diamond red = new Diamond(this.world, (char) 236, Color.ORANGE, 1, 0, 0, 1, 1, 1);
        assertTrue(red.color()==Color.ORANGE);
        assertTrue(red.ifDiamond()==true);
    }
}