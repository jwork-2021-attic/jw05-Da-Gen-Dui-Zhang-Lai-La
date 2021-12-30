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
import java.io.IOException;
import asciiPanel.AsciiPanel;
import netTool.*;
/**
 *
 * @author Aeranythe Echosong
 */
public class LoseScreen extends RestartScreen {
    int finalGrade;
    String rank;
    LoseScreen(int grade){
        this.finalGrade=grade;
    }
    @Override
    public Screen displayOutput(AsciiPanel terminal) {
        getRank();
        terminal.write("YOU DIE,PRESS RNTER TO TRY AGAIN.", 0, 0);
        String gradeData = String.format("GRADE:%3d", this.finalGrade);
        terminal.write(gradeData, 0, 1);
        String rankData = String.format("YOUR RANK:%s", this.rank);
        terminal.write(rankData, 0, 2);
        return this;
    }
    private void getRank() {
        try {
            NetClient netClient = new NetClient(this.finalGrade);
            this.rank= netClient.getRank();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }
}
