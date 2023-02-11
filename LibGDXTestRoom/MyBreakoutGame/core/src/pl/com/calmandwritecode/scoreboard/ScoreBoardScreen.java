package pl.com.calmandwritecode.scoreboard;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import pl.com.calmandwritecode.BreakoutGame;
import pl.com.calmandwritecode.menu.MenuScreen;

import java.util.ArrayList;

public class ScoreBoardScreen implements Screen {

    private final BreakoutGame game;
    private Stage stage;

    public ScoreBoardScreen(BreakoutGame game){
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage();

        Skin skin =  new Skin(Gdx.files.internal("skins.json"));

        Table table = new Table();
        table.setFillParent(true);

        ArrayList<Score> scores = game.scoreBoard.getScores();

        Label placeHeader = new Label("PLACE",skin,"score-header");
        Label playerHeader = new Label("PLAYER",skin,"score-header");
        Label scoreHeader = new Label("SCORE",skin,"score-header");
        Label lvlHeader = new Label("LEVEL",skin,"score-header");
        table.add(placeHeader).width(200);
        table.add(playerHeader).width(400);
        table.add(scoreHeader).width(400);
        table.add(lvlHeader).width(100).row();

        for (int i=0; i<scores.size(); i++){
            int place = i+1;
            Score score = scores.get(i);
            Label placeLabel = new Label(place+".",skin,"scoreboard");
            placeLabel.setAlignment(Align.right);
            Label playerLabel = new Label(score.getPlayerName(),skin,"scoreboard");
            Label scoreLabel = new Label(score.getScore()+"",skin,"scoreboard");
            Label lvlLabel = new Label((score.getLevel()+1)+"",skin,"scoreboard");
            table.add(placeLabel).width(200);
            table.add(playerLabel).width(400);
            table.add(scoreLabel).width(400);
            table.add(lvlLabel).width(100).row();
        }
        stage.addActor(table);

        AlphaAction fadeIn = new AlphaAction();
        fadeIn.setAlpha(0);
        fadeIn.setDuration(0);

        AlphaAction fadeOut = new AlphaAction();
        fadeOut.setAlpha(1);
        fadeOut.setDuration(2);

        SequenceAction fadeInAndOut = new SequenceAction();
        fadeInAndOut.addAction(fadeIn);
        fadeInAndOut.addAction(fadeOut);

        table.addAction(fadeInAndOut);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
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
