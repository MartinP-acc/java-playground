package pl.com.calmandwritecode.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

public class PowerRemainBar extends Table {

    public static final String[] STICK_PADDLE = new String[]{"stick_paddle","blue_bar"};
    public static final String[] POWER_BALL = new String[]{"power_ball","red_bar"};
    public static final String[] LASERS = new String[]{"lasers","green_bar"};

    private ProgressBar progressBar;
    private Image barIcon;
    public PowerRemainBar(float max, float step, String[] barOption, TextureAtlas atlas){

        barIcon = new Image(atlas.findRegion(barOption[0]));

        ProgressBar.ProgressBarStyle barStyle = new ProgressBar.ProgressBarStyle();
        barStyle.knobBefore = new NinePatchDrawable(atlas.createPatch(barOption[1]));
        progressBar = new ProgressBar(1,max,step,false,barStyle);

        add(barIcon).size(20,20);
        add(progressBar).size(100,20);
        progressBar.setValue(0);
        setVisible(false);
        padRight(20);
    }

    public void update(float progress){
        if (progress>0){
            setVisible(true);
            progressBar.setValue(progress);
        } else setVisible(false);
    }
}
