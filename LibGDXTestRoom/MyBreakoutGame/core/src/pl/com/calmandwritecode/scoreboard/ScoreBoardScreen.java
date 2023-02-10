package pl.com.calmandwritecode.scoreboard;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import pl.com.calmandwritecode.BreakoutGame;
import pl.com.calmandwritecode.menu.MenuScreen;

import java.util.ArrayList;

public class ScoreBoardScreen implements Screen {

    private static final String HEADER_ROW = "PLACE\tPLAYER\tSCORE\tLVL";
    private final BreakoutGame game;
    private Stage stage;

    public ScoreBoardScreen(BreakoutGame game){
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage();

        Table table = new Table();
        table.setFillParent(true);

        ArrayList<Score> scores = game.scoreBoard.getScores();

        Label placeHeader = new Label("PLACE",game.skin,"scoreboard");
        Label playerHeader = new Label("PLAYER",game.skin,"scoreboard");
        Label scoreHeader = new Label("SCORE",game.skin,"scoreboard");
        Label lvlHeader = new Label("LEVEL",game.skin,"scoreboard");
        table.add(placeHeader).width(300);
        table.add(playerHeader).width(300);
        table.add(scoreHeader).width(400);
        table.add(lvlHeader).width(100).row();

        for (int i=0; i<scores.size(); i++){
            int place = i+1;
            Score score = scores.get(i);
            Label placeLabel = new Label(place+".",game.skin,"scoreboard");
            Label playerLabel = new Label(score.getPlayerName(),game.skin,"scoreboard");
            Label scoreLabel = new Label(score.getScore()+"",game.skin,"scoreboard");
            Label lvlLabel = new Label((score.getLevel()+1)+"",game.skin,"scoreboard");
            table.add(placeLabel).width(300);
            table.add(playerLabel).width(300);
            table.add(scoreLabel).width(400);
            table.add(lvlLabel).width(100).row();
        }
        stage.addActor(table);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();

        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            ScoreBoardManager scoreBoardManager = new ScoreBoardManager();
            scoreBoardManager.saveScoreBoard(game.scoreBoard);
            game.setScreen(new MenuScreen(game));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
