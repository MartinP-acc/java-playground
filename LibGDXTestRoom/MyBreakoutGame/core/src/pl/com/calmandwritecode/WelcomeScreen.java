package pl.com.calmandwritecode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class WelcomeScreen implements Screen {

    private BitmapFont font;
    private Stage stage;
    private Table table;
    private Label header;
    private Label.LabelStyle labelStyle;
    private TextButton startButton, exitButton;
    private BreakoutGame game;
    AlphaAction fadeIn, fadeOut;
    private Texture texture;
    private TextureRegion textureRegion;
    private Image image;

    public WelcomeScreen(final BreakoutGame game) {
        this.font = game.font;
        stage = new Stage();
        table = new Table();
        this.game = game;
        Gdx.input.setCursorCatched(false);
        texture = new Texture(Gdx.files.internal("puzzle.png"));
        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        textureRegion = new TextureRegion(texture);
        textureRegion.setRegion(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        image = new Image(textureRegion);
        image.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        image.setPosition(0,0);
    }

    @Override
    public void show() {
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = font;
        buttonStyle.fontColor = Color.GOLD;
        buttonStyle.downFontColor = Color.RED;
        buttonStyle.overFontColor = Color.GREEN;
        startButton = new TextButton("start",buttonStyle);
        startButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new LevelScreen(game));
                dispose();
            }
        });

        exitButton = new TextButton("exit",buttonStyle);
        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                dispose();
                Gdx.app.exit();
            }
        });
        labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        header = new Label("breakout test",labelStyle);

        Gdx.input.setInputProcessor(stage);
        stage.addActor(image);
        stage.addActor(table);

        table.setDebug(false);
        table.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        table.add(header).pad(100);
        table.row();
        table.add(startButton);
        table.row();
        table.add(exitButton);

        fadeIn = new AlphaAction();
        fadeIn.setAlpha(0);
        fadeIn.setDuration(0);

        fadeOut = new AlphaAction();
        fadeOut.setAlpha(1);
        fadeOut.setDuration(3);

        SequenceAction fadeInAndOut = new SequenceAction();
        fadeInAndOut.addAction(fadeIn);
        fadeInAndOut.addAction(fadeOut);
        table.addAction(fadeInAndOut);
        table.act(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();


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
