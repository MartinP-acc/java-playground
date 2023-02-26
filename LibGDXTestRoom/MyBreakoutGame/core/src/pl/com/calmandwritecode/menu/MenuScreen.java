package pl.com.calmandwritecode.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import pl.com.calmandwritecode.*;
import pl.com.calmandwritecode.editor.EditorScreen;
import pl.com.calmandwritecode.game.LevelScreen;
import pl.com.calmandwritecode.scoreboard.ScoreBoardScreen;

public class MenuScreen implements Screen {

    private Stage stage;
    private final BreakoutGame game;
    private OrthographicCamera camera;

    public MenuScreen(final BreakoutGame game) {
        this.game = game;
        game.player = new PlayerData();
        Gdx.input.setCursorCatched(false);
        game.levelManager.loadLevelList();
        camera = new OrthographicCamera();
        camera.setToOrtho(false,BreakoutGame.W_WIDTH,BreakoutGame.W_HEIGHT);
    }

    @Override
    public void show() {
        Skin skin = new Skin(Gdx.files.internal("skins.json"));
        Texture texture = game.gameAssets.get(GameAssets.BACKGROUND_FILE);
        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        TextureRegion textureRegion = new TextureRegion(texture);
        textureRegion.setRegion(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        Image image = new Image(textureRegion);
        image.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        image.setPosition(0,0);

        TextButton startButton = new TextButton("start", skin);
        startButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new LevelScreen(game));
                dispose();
            }
        });

        TextButton exitButton = new TextButton("exit", skin);
        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                dispose();
                Gdx.app.exit();
            }
        });

        TextButton editorButton = new TextButton("level editor",skin);
        editorButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new EditorScreen(game));
                dispose();
            }
        });

        TextButton scoreboardButton = new TextButton("scoreboard",skin);
        scoreboardButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new ScoreBoardScreen(game));
                dispose();
            }
        });

        Label header = new Label("breakout test", skin);

        AlphaAction fadeIn = new AlphaAction();
        fadeIn.setAlpha(0);
        fadeIn.setDuration(0);

        AlphaAction fadeOut = new AlphaAction();
        fadeOut.setAlpha(1);
        fadeOut.setDuration(3);

        SequenceAction fadeInAndOut = new SequenceAction();
        fadeInAndOut.addAction(fadeIn);
        fadeInAndOut.addAction(fadeOut);

        Table table = new Table();
        table.setFillParent(true);
        table.add(header).pad(100);
        table.row();
        table.add(startButton);
        table.row();
        table.add(editorButton);
        table.row();
        table.add(scoreboardButton);
        table.row();
        table.add(exitButton);
        table.addAction(fadeInAndOut);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        stage.addActor(image);
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();

        stage.getBatch().setProjectionMatrix(camera.combined);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        show();
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
