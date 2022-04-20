package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import java.util.List;

import com.mygdx.game.MyGdxGame;

public class MenuState extends State {
    private Vector2 touch;
    private Sprite playBtn;
    private Sprite multiBtn;
    private Sprite scoreBtn;
    private Sprite settingBtn;
    private Sprite helpBtn;
    private Texture background;
    private int margin = 80;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("bg_bare_himmel.png");
        playBtn = new Sprite(new Texture("newGameButton.png"));
        multiBtn = new Sprite(new Texture("multiplayerButton.png"));
        scoreBtn = new Sprite(new Texture("highScoreButton.png"));
        settingBtn = new Sprite(new Texture("settingsButton.png"));
        helpBtn = new Sprite(new Texture("tutorialButton.png"));

        playBtn.setSize(Gdx.graphics.getWidth()/4, Gdx.graphics.getWidth()/4);
        playBtn.setPosition(Gdx.graphics.getWidth()/2-playBtn.getWidth()/2, (Gdx.graphics.getHeight()-margin)-playBtn.getHeight());
        multiBtn.setSize(Gdx.graphics.getWidth()/4, Gdx.graphics.getWidth()/4);
        multiBtn.setPosition(Gdx.graphics.getWidth()/2-playBtn.getWidth()/2, (Gdx.graphics.getHeight()-margin)-2*playBtn.getHeight());
        scoreBtn.setSize(Gdx.graphics.getWidth()/4, Gdx.graphics.getWidth()/4);
        scoreBtn.setPosition(Gdx.graphics.getWidth()/2-playBtn.getWidth()/2, (Gdx.graphics.getHeight()-margin)-3*playBtn.getHeight());
        settingBtn.setSize(Gdx.graphics.getWidth()/4, Gdx.graphics.getWidth()/4);
        settingBtn.setPosition(Gdx.graphics.getWidth()/2-playBtn.getWidth()/2, (Gdx.graphics.getHeight()-margin)-4*playBtn.getHeight());
        helpBtn.setSize(Gdx.graphics.getWidth()/4, Gdx.graphics.getWidth()/4);
        helpBtn.setPosition(Gdx.graphics.getWidth()/2-playBtn.getWidth()/2, (Gdx.graphics.getHeight()-margin)-5*playBtn.getHeight());

    }

    @Override
    public void handleInput() {
        MyGdxGame.get_FBIC().SomeFunction();
        if (Gdx.input.justTouched()) {
            if (playBtn.getBoundingRectangle().contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
                MyGdxGame.sound.play();
                gsm.set(new SingleplayerState(gsm));
            }
            if (multiBtn.getBoundingRectangle().contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
                MyGdxGame.sound.play();
                gsm.set(new MultiplayerState(gsm));
            }
            if (scoreBtn.getBoundingRectangle().contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
                MyGdxGame.sound.play();
                gsm.set(new ScoreState(gsm));
            }
            if (settingBtn.getBoundingRectangle().contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
                MyGdxGame.sound.play();
                gsm.push(new SettingState(gsm));
            }
            if (helpBtn.getBoundingRectangle().contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
                MyGdxGame.sound.play();
                gsm.push(new TutorialState(gsm));
            }

        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.draw(playBtn, playBtn.getX(), playBtn.getY(), Gdx.graphics.getWidth()/4, Gdx.graphics.getWidth()/4);
        sb.draw(multiBtn, multiBtn.getX(), multiBtn.getY(), Gdx.graphics.getWidth()/4, Gdx.graphics.getWidth()/4);
        sb.draw(scoreBtn, scoreBtn.getX(), scoreBtn.getY(), Gdx.graphics.getWidth()/4, Gdx.graphics.getWidth()/4);
        sb.draw(settingBtn, settingBtn.getX(), settingBtn.getY(), Gdx.graphics.getWidth()/4, Gdx.graphics.getWidth()/4);
        sb.draw(helpBtn, helpBtn.getX(), helpBtn.getY(), Gdx.graphics.getWidth()/4, Gdx.graphics.getWidth()/4);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
