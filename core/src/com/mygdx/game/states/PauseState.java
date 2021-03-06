package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;

public class PauseState extends State {
    private static final int WIDTH = Gdx.graphics.getWidth();
    private static final int HEIGHT = Gdx.graphics.getHeight();
    private static final int BUTTON_SIZE = WIDTH/4;
    private static final int X_BUTTON = (WIDTH - BUTTON_SIZE)/2;
    private static final float Y_TITLE = 0.9f*HEIGHT;
    private int X_TITLE;

    private BitmapFont font = new BitmapFont();
    private Texture bg = new Texture("bg_sky.png");
    private Sprite resBtn = new Sprite(new Texture("resume_btn.png"));
    private Sprite settingBtn = new Sprite(new Texture("settings_btn.png"));
    private Sprite tutorialBtn = new Sprite(new Texture("tutorial_btn.png"));
    private Sprite quitBtn = new Sprite(new Texture("quit_btn.png"));
    private OrthographicCamera cam = new OrthographicCamera();

    /**
     * Gives the options to resume, go to settings, tutorial, or quit the current game.
     * @param gsm GameStateManager controlling the states of the application
     */
    public PauseState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, WIDTH, HEIGHT);
        resBtn.setPosition(X_BUTTON, Y_TITLE -2* BUTTON_SIZE);
        resBtn.setSize(BUTTON_SIZE, BUTTON_SIZE);
        settingBtn.setPosition(X_BUTTON, Y_TITLE -3* BUTTON_SIZE);
        settingBtn.setSize(BUTTON_SIZE, BUTTON_SIZE);
        tutorialBtn.setPosition(X_BUTTON, Y_TITLE -4* BUTTON_SIZE);
        tutorialBtn.setSize(BUTTON_SIZE, BUTTON_SIZE);
        quitBtn.setPosition(X_BUTTON, Y_TITLE -5* BUTTON_SIZE);
        quitBtn.setSize(BUTTON_SIZE, BUTTON_SIZE);
        font.setColor(0,0,0,1);
        font.getData().setScale(HEIGHT/320f);
        X_TITLE = (WIDTH - font.getRegion().getRegionWidth())/2;

    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            if (resBtn.getBoundingRectangle().contains(Gdx.input.getX(),
                    Gdx.graphics.getHeight() - Gdx.input.getY())) {
                MyGdxGame.sound.play();
                gsm.pop();
            }
            if (settingBtn.getBoundingRectangle().contains(Gdx.input.getX(),
                    Gdx.graphics.getHeight() - Gdx.input.getY())) {
                MyGdxGame.sound.play();
                gsm.push(new SettingState(gsm));
            }
            if (tutorialBtn.getBoundingRectangle().contains(Gdx.input.getX(),
                    Gdx.graphics.getHeight() - Gdx.input.getY())) {
                MyGdxGame.sound.play();
                gsm.push(new TutorialState(gsm));
            }
            if (quitBtn.getBoundingRectangle().contains(Gdx.input.getX(),
                    Gdx.graphics.getHeight() - Gdx.input.getY())) {
                MyGdxGame.sound.play();
                gsm.pop();
                gsm.set(new MenuState(gsm));
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        Gdx.gl.glViewport(0,0, WIDTH, HEIGHT);

        sb.begin();
        sb.draw(bg,0, 0, WIDTH, HEIGHT);
        sb.draw(resBtn, resBtn.getX(), resBtn.getY(), BUTTON_SIZE, BUTTON_SIZE);
        sb.draw(settingBtn, settingBtn.getX(), settingBtn.getY(), BUTTON_SIZE, BUTTON_SIZE);
        sb.draw(tutorialBtn, tutorialBtn.getX(), tutorialBtn.getY(), BUTTON_SIZE, BUTTON_SIZE);
        sb.draw(quitBtn, quitBtn.getX(), quitBtn.getY(), BUTTON_SIZE, BUTTON_SIZE);
        font.draw(sb, "GAME PAUSED", X_TITLE, Y_TITLE);
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        resBtn.getTexture().dispose();
        settingBtn.getTexture().dispose();
        tutorialBtn.getTexture().dispose();
        quitBtn.getTexture().dispose();
    }
}
