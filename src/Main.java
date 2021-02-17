import de.ft.ledwall.Application;
import de.ft.ledwall.Sound;
import de.ft.ledwall.SystemInterface;
import org.jetbrains.annotations.NotNull;
import pacman.Direction;
import pacman.Level;

import javax.sound.sampled.Clip;

public class Main implements Application {

        public static void main(String[] args) {

        }

        String Sound_intro="PacManSounds/game_start.wav";
        Level level=new Level();
        int drawcounter=0;
        int level1[][]={
                {0x02,0x02,0x02,0x02,0x02,0x02,0x02,0x02,0x02,0x04},
                {0x02,0x01,0x01,0x01,0x01,0x02,0x01,0x01,0x01,0x02},
                {0x02,0x02,0x02,0x02,0x02,0x02,0x02,0x02,0x02,0x02},
                {0x01,0x01,0x02,0x01,0x01,0x01,0x01,0x02,0x01,0x01},
                {0x02,0x02,0x02,0x02,0x02,0x02,0x02,0x02,0x02,0x02},
                {0x01,0x02,0x01,0x02,0x01,0x01,0x02,0x01,0x02,0x01},
                {0x02,0x02,0x01,0x02,0x01,0x02,0x02,0x01,0x02,0x02},
                {0x02,0x01,0x01,0x02,0x01,0x01,0x02,0x01,0x01,0x02},
                {0x02,0x02,0x02,0x02,0x02,0x02,0x02,0x02,0x02,0x02},
                {0x01,0x01,0x02,0x01,0x01,0x02,0x01,0x01,0x01,0x01},
                {0x02,0x02,0x02,0x02,0x02,0x02,0x02,0x02,0x02,0x02},
                {0x02,0x01,0x01,0x01,0x01,0x02,0x01,0x01,0x01,0x02},
                {0x02,0x02,0x02,0x02,0x01,0x02,0x01,0x02,0x02,0x02},
                {0x01,0x01,0x01,0x02,0x01,0x02,0x01,0x02,0x01,0x02},
                {0x04,0x02,0x02,0x02,0x02,0x02,0x02,0x02,0x02,0x02}
        };
        @Override
        public void onCreate() {
                newGame();
                Sound.PacmanSiren_4.loop(Clip.LOOP_CONTINUOUSLY);

        }

        void newGame(){
                level.setLevel(level1);
                level.player.setMyLevel(level);
                level.player.setNextDirection(Direction.NONE);
                Sound.play(this.Sound_intro);
        }

        @Override
        public void onDraw() {
                SystemInterface.table.clear();
                level.draw();
                level.player.draw();
                for(int i=0;i<level.ghosts.size();i++){
                        level.ghosts.get(i).draw();
                }
                if(drawcounter>3){
                        level.player.move();
                        for(int i=0;i<level.ghosts.size();i++){
                                level.ghosts.get(i).move();
                        }
                        drawcounter=0;
                }
                drawcounter++;
        }

        @Override
        public void onRun() {

        }

        @Override
        public void onDataReceive(@NotNull String data, int playerID) {

                if(data.contentEquals("d")){
                        level.player.setNextDirection(Direction.DOWN);
                }
                if(data.contentEquals("u")){
                        level.player.setNextDirection(Direction.UP);
                }
                if(data.contentEquals("r")){
                        level.player.setNextDirection(Direction.RIGHT);
                }
                if(data.contentEquals("l")){
                        level.player.setNextDirection(Direction.LEFT);
                }
        }

        @NotNull
        @Override
        public String getName() {
                return "PacMan";
        }

        @Override
        public void onStop() {

        }

        @Override
        public int getVersion() {
                return 0;
        }

        @NotNull
        @Override
        public String getUUID() {
                return "c8982e72-7056-11eb-8638-0242ac110002";
        }
}