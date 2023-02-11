package pl.com.calmandwritecode.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import pl.com.calmandwritecode.BreakoutGame;
import pl.com.calmandwritecode.Level;
import pl.com.calmandwritecode.menu.MenuScreen;


public class EditorScreen implements Screen {

    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    private TextureAtlas atlas;
    private Grid grid;
    private Stage stage;
    private final BreakoutGame game;
    private OrthographicCamera camera;
    private Skin skin;

    public EditorScreen(BreakoutGame game){
        this.game = game;
    }

    @Override
    public void show() {

        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setColor(Color.DARK_GRAY);
        atlas = new TextureAtlas("breakout-tx.atlas");
        skin = new Skin(Gdx.files.internal("skins.json"));

        ImageButton brick1Button = createImageButton("brick1","brick1ch");
        ImageButton brick2Button = createImageButton("brick2","brick2ch");
        ImageButton brick3Button = createImageButton("brick3","brick3ch");
        ImageButton brick4Button = createImageButton("brick4","brick4ch");
        ImageButton brick5Button = createImageButton("brick5","brick5ch");
        ImageButton brick6Button = createImageButton("brick6","brick6ch");
        ImageButton brick7Button = createImageButton("brick7","brick7ch");
        ImageButton brick8Button = createImageButton("brick8","brick8ch");
        ImageButton hardBrickButton = createImageButton("hard_brick0","hard_brick_ch");
        ImageButton wallBrickButton = createImageButton("wall_brick","wall_brick_ch");

        final TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = new BitmapFont();
        textButtonStyle.fontColor = Color.BLACK;
        textButtonStyle.downFontColor = Color.WHITE;
        textButtonStyle.up = new SpriteDrawable(atlas.createSprite("wall_brick"));
        textButtonStyle.down = new SpriteDrawable(atlas.createSprite("hard_brick2"));

        TextButton clear = new TextButton("Clear grid",textButtonStyle);
        clear.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                grid.cleanGrid();
            }
        });
        clear.padLeft(30);
        clear.padRight(30);

        TextButton save = new TextButton("Save level", textButtonStyle);
        save.padLeft(30);
        save.padRight(30);

        Slider.SliderStyle style = new Slider.SliderStyle();
        style.background = new SpriteDrawable(atlas.createSprite("paddle120"));
        style.knob = new SpriteDrawable(atlas.createSprite("ball"));
        final Slider slider = new Slider(10,100,5,false,style);

        final Label label = new Label("Power up chance : "+slider.getValue()+"%",skin,"label");
        final Label dialogMessage = new Label("",skin,"message");

        slider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                label.setText("Power up chance : "+slider.getValue()+"%");
            }
        });

        Window.WindowStyle windowStyle = new Window.WindowStyle();
        windowStyle.titleFont = new BitmapFont();
        windowStyle.background = new SpriteDrawable(atlas.createSprite("dialog"));
        final Dialog dialog = new Dialog("Type level name: ",windowStyle);
        dialog.getTitleLabel().getStyle().background = new SpriteDrawable(atlas.createSprite("dialog_stage"));
        dialog.getTitleLabel().setAlignment(Align.center);

        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.font = new BitmapFont();
        textFieldStyle.fontColor = Color.WHITE;
        textFieldStyle.background = new SpriteDrawable(atlas.createSprite("dialog_text_field"));
        final TextField textField = new TextField("myLevelName",textFieldStyle);

        TextButton ok = new TextButton("OK",textButtonStyle);


        ok.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Level level = new Level(textField.getText(),grid.saveToString(),slider.getValue());
                if (game.levelManager.saveLevel(level)){
                    grid.activate();
                    dialog.remove();
                }else {
                    dialogMessage.setText(game.levelManager.getMessage());
                }
            }
        });
        TextButton cancel = new TextButton("Cancel",textButtonStyle);
        cancel.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                grid.activate();
                dialog.remove();
            }
        });
        dialog.getContentTable().add(dialogMessage).padTop(10).row();
        dialog.getContentTable().add(textField).padTop(10);
        dialog.getButtonTable().add(ok,cancel).padBottom(30);

        save.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                grid.deactivate();
                dialog.show(stage);


            }
        });

        ButtonGroup<ImageButton> buttons = new ButtonGroup<>();
        buttons.add(brick1Button,
                brick2Button,
                brick3Button,
                brick4Button,
                brick5Button,
                brick6Button,
                brick7Button,
                brick8Button,
                hardBrickButton,
                wallBrickButton);

        buttons.setUncheckLast(true);

        Table root = new Table();
        root.add(brick1Button,
                brick2Button,
                brick3Button,
                brick4Button,
                brick5Button,
                clear, label);
        root.row();
        root.add(brick6Button,
                brick7Button,
                brick8Button,
                hardBrickButton,
                wallBrickButton,
                save, slider);
        root.getCells().get(6).width(180).padLeft(20);

        root.setBounds(0,0,Gdx.graphics.getWidth(),150);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        stage.addActor(root);

        camera = new OrthographicCamera();
        camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        grid = new Grid(atlas, buttons);

    }

    private ImageButton createImageButton(String normal, String checked) {
        SpriteDrawable normalSprite = new SpriteDrawable(atlas.createSprite(normal));
        SpriteDrawable checkedSprite = new SpriteDrawable(atlas.createSprite(checked));
        ImageButton button = new ImageButton(normalSprite, checkedSprite, checkedSprite);
        button.setName(normal);
        return button;
    }
    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();

        shapeRenderer.setProjectionMatrix(camera.combined);
        batch.setProjectionMatrix(camera.combined);

        grid.updateCursor();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        grid.render(shapeRenderer);
        shapeRenderer.end();

        batch.begin();
        grid.drawSprites(batch);
        batch.end();

        stage.draw();
        stage.getBatch().setProjectionMatrix(camera.combined);

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
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
        shapeRenderer.dispose();
        batch.dispose();
        skin.dispose();
        atlas.dispose();
    }
}
