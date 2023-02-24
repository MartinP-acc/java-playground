package pl.com.calmandwritecode.scoreboard;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import pl.com.calmandwritecode.BreakoutGame;
import pl.com.calmandwritecode.GameAssets;

public class NewScoreScreen implements Screen {

    private final BreakoutGame game;
    private final Stage stage;
    private final Skin skin;
    private TextField textField;
    private Label warning;

    public NewScoreScreen(BreakoutGame game){
        this.game = game;
        this.stage = new Stage();
        this.skin = new Skin(Gdx.files.internal("skins.json"));
    }

    @Override
    public void show() {

        Table table = new Table();
        table.setFillParent(true);

        Label scoreHeader = new Label("SCORE : ",skin,"score-header");
        scoreHeader.setAlignment(Align.right);
        scoreHeader.setWidth(500);
        Label playerScore = new Label(""+game.player.getScore(),skin,"scoreboard");
        playerScore.setWidth(500);
        playerScore.setAlignment(Align.left);
        Label nameHeader = new Label("TYPE YOUR NAME : ", skin,"score-header");
        nameHeader.setAlignment(Align.right);
        nameHeader.setWidth(500);
        warning = new Label("UPPER CASE ONLY ", skin,"score-warning");
        warning.setAlignment(Align.right);
        warning.setWidth(500);

        textField = new TextField("",skin,"score");
        textField.getStyle().background = new SpriteDrawable(new Sprite((Texture) game.gameAssets.get(GameAssets.TF_BG_FILE)));
        textField.setMessageText("YOUR NAME");
        textField.setMaxLength(15);
        textField.setOnlyFontChars(true);

        table.add(scoreHeader).width(500);
        table.add(playerScore).width(500);
        table.row();
        table.add(nameHeader);
        table.add(textField).width(500);
        table.row();
        table.add(warning);

        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();

        warning.setVisible(textField.getText().isEmpty());

        if (Gdx.input.isKeyPressed(Input.Keys.ENTER) && !textField.getText().isEmpty() ){
            String playerName = textField.getText().length()>10 ? textField.getText().substring(0,9) : textField.getText();
            Score score = new Score(playerName, game.player.getScore(),game.player.getCurrentLevel());
            game.scoreBoard.addNewScore(score);
            game.setScreen(new ScoreBoardScreen(game));
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
        skin.dispose();
    }
}
