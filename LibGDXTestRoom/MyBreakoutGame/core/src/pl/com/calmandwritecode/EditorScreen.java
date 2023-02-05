package pl.com.calmandwritecode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.StringBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EditorScreen implements Screen {

    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    private TextureAtlas atlas;
    private Rectangle cursorRect;
    private GridCell[][] grid;
    private Stage stage;
    private ButtonGroup<ImageButton> buttons;
    private boolean activeGrid;
    private final BreakoutGame game;

    public EditorScreen(BreakoutGame game){
        this.game = game;
    }

    @Override
    public void show() {

        cursorRect = new Rectangle(0,0,1,1);
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        atlas = new TextureAtlas("breakout-tx.atlas");
        grid = new GridCell[16][25];
        activeGrid = true;

        cleanGrid();


        ImageButton brick1Button = new ImageButton(new SpriteDrawable(atlas.createSprite("brick1")));
        ImageButton brick2Button = new ImageButton(new SpriteDrawable(atlas.createSprite("brick2")));
        ImageButton brick3Button = new ImageButton(new SpriteDrawable(atlas.createSprite("brick3")));
        ImageButton brick4Button = new ImageButton(new SpriteDrawable(atlas.createSprite("brick4")));
        ImageButton brick5Button = new ImageButton(new SpriteDrawable(atlas.createSprite("brick5")));
        ImageButton brick6Button = new ImageButton(new SpriteDrawable(atlas.createSprite("brick6")));
        ImageButton brick7Button = new ImageButton(new SpriteDrawable(atlas.createSprite("brick7")));
        ImageButton brick8Button = new ImageButton(new SpriteDrawable(atlas.createSprite("brick8")));
        ImageButton hardBrickButton = new ImageButton(new SpriteDrawable(atlas.createSprite("hard_brick0")));
        ImageButton wallBrickButton = new ImageButton(new SpriteDrawable(atlas.createSprite("wall_brick")));

        final TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = new BitmapFont();
        textButtonStyle.fontColor = Color.BLACK;
        textButtonStyle.downFontColor = Color.WHITE;
        textButtonStyle.up = new SpriteDrawable(atlas.createSprite("wall_brick"));
        textButtonStyle.over = new SpriteDrawable(atlas.createSprite("hard_brick0"));
        textButtonStyle.down = new SpriteDrawable(atlas.createSprite("hard_brick2"));

        TextButton clear = new TextButton("Clear grid",textButtonStyle);
        clear.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                cleanGrid();
            }
        });
        clear.padLeft(30);
        clear.padRight(30);

        TextButton save = new TextButton("Save level", textButtonStyle);

        save.padLeft(30);
        save.padRight(30);

        Slider.SliderStyle style = new Slider.SliderStyle();
        style.background = new SpriteDrawable(atlas.createSprite("paddle"));
        style.knob = new SpriteDrawable(atlas.createSprite("ball"));
        final Slider slider = new Slider(10,100,5,false,style);


        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();
        final Label label = new Label("Power up chance : "+slider.getValue()+"%",labelStyle);
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
                Level level = new Level(textField.getText(),saveToString(),slider.getValue());
                Json json = new Json();
                File file = Gdx.files.absolute("assets/levels/"+level.getLevelTitle()+".json").file();
                try (FileWriter writer = new FileWriter(file)){
                    json.toJson(level,writer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                activeGrid = true;
                dialog.hide();
                dialog.remove();
            }
        });
        TextButton cancel = new TextButton("Cancel",textButtonStyle);
        cancel.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                activeGrid = true;
                dialog.hide();
                dialog.remove();
            }
        });
        dialog.getContentTable().add(textField).padTop(30);
        dialog.getButtonTable().add(ok,cancel).padBottom(30);

        save.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                activeGrid = false;
                dialog.show(stage);


            }
        });

        buttons = new ButtonGroup<>();
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

        root.setBounds(0,0,Gdx.graphics.getWidth(),150);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        stage.addActor(root);
    }

    private void cleanGrid() {
        for (int row = 0; row<16; row++){
            for (int col = 0; col<25; col++){
                grid[row][col]=new GridCell(row,col);
            }
        }
    }

    private String saveToString(){
        StringBuilder map = new StringBuilder();
        for (int row = 15; row>=0; row--){
            for (int col = 0; col<25; col++){
                GridCell cell = grid[row][col];
                map.append(cell.brickName);
                if (!cell.brickName.equals(" ")) col++;
            }
            map.append("n");
        }
        return map.toString();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cursorRect.setPosition(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY());

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        for (int row = 0; row<16; row++){
            for (int col = 0; col<25; col++){
                GridCell cell = grid[row][col];
                cell.draw(shapeRenderer,cursorRect);
                if (!activeGrid) cell.cursorOver = false;
                if (col==0 || (col<24 && grid[row][col-1].sprite==null)) {
                    cell.setTexture(atlas, buttons);
                }else{
                    cell.sprite=null;
                }
            }
        }
        shapeRenderer.end();

        batch.begin();
        for (int row = 0; row<16; row++) {
            for (int col = 0; col < 25; col++) {
                GridCell cell = grid[row][col];
                cell.drawSprite(batch);
            }
        }
        batch.end();
        stage.draw();

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            game.setScreen(new WelcomeScreen(game));
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

    }
}
