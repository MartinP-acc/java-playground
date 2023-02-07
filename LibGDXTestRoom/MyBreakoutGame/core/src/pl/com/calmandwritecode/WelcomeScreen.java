package pl.com.calmandwritecode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;

public class WelcomeScreen implements Screen {

    private Stage stage;
    private final BreakoutGame game;

    public WelcomeScreen(final BreakoutGame game) {
        this.game = game;
        game.player = new PlayerData();
        Gdx.input.setCursorCatched(false);
        initLevels();
    }

    private void initLevels(){
        Json json = new Json();
        game.levels = new Array<>();
        FileHandle[] files  = Gdx.files.internal("assets/levels/").list();
        for (FileHandle file : files){
            Level level = json.fromJson(Level.class,file);
            game.levels.add(level);
        }
    }

    @Override
    public void show() {
        Skin menuSkin = new Skin(Gdx.files.internal("skins.json"));

        Texture texture = new Texture(Gdx.files.internal("puzzle.png"));
        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        TextureRegion textureRegion = new TextureRegion(texture);
        textureRegion.setRegion(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        Image image = new Image(textureRegion);
        image.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        image.setPosition(0,0);

        TextButton startButton = new TextButton("start", menuSkin);
        startButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new LevelScreen(game,0));
                dispose();
            }
        });

        TextButton exitButton = new TextButton("exit", menuSkin);
        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                dispose();
                Gdx.app.exit();
            }
        });

        TextButton editorButton = new TextButton("level editor",menuSkin);
        editorButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new EditorScreen(game));
                dispose();
            }
        });

        Label header = new Label("breakout test", menuSkin);

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
        table.setDebug(false);
        table.setFillParent(true);
        table.add(header).pad(100);
        table.row();
        table.add(startButton);
        table.row();
        table.add(editorButton);
        table.row();
        table.add(exitButton);
        table.addAction(fadeInAndOut);
        table.act(Gdx.graphics.getDeltaTime());

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        stage.addActor(image);
        stage.addActor(table);


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
