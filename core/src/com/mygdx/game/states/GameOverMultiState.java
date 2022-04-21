package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.Player;

public class GameOverMultiState extends State{

    private Texture bg;
    private Sprite playBtn;
    private Sprite quitBtn;
    private BitmapFont font;
    private BitmapFont scoreFont;
    private BitmapFont nameFont;
    private OrthographicCamera cam;

    private String text;

    protected GameOverMultiState(GameStateManager gsm, final Player player) {
        super(gsm);
        bg = new Texture("bg_bare_himmel.png");
        cam = new OrthographicCamera();
        cam.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);

        playBtn = new Sprite(new Texture("multiplayerButton.png"));
        quitBtn = new Sprite(new Texture("quitBtn.png"));

        playBtn.setSize(Gdx.graphics.getWidth()/4f, Gdx.graphics.getWidth()/4f);
        playBtn.setPosition(Gdx.graphics.getWidth()-(playBtn.getRegionWidth()/2), (Gdx.graphics.getHeight()/3f));
        quitBtn.setSize(Gdx.graphics.getWidth()/4f, Gdx.graphics.getWidth()/4f);
        quitBtn.setPosition(Gdx.graphics.getWidth()-(quitBtn.getRegionWidth()/2), playBtn.getY()-(playBtn.getRegionHeight()));

        font = new BitmapFont();
        scoreFont = new BitmapFont();
        nameFont = new BitmapFont();

        font = new BitmapFont();
        font.setColor(0,0,0,1);
        font.getData().setScale(2.5f);

        if (player.getType() == 1){
            text = "PLAYER 1 WON!";
        }
        else if (player.getType() == 2){
            text = "PLAYER 2 WON!";
        }
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            if (playBtn.getBoundingRectangle().contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
                MyGdxGame.sound.play();
                gsm.pop();
            }
            if (quitBtn.getBoundingRectangle().contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
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
        Gdx.gl.glViewport(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        sb.begin();
        sb.draw(bg,0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        font.draw(sb, text, (Gdx.graphics.getWidth()/2f)  - (font.getRegion().getRegionWidth()/2f), Gdx.graphics.getHeight()/1.5f);
        sb.draw(playBtn, playBtn.getX(), playBtn.getY(), Gdx.graphics.getWidth()/4f, Gdx.graphics.getWidth()/4f);
        sb.draw(quitBtn, quitBtn.getX(), quitBtn.getY(), Gdx.graphics.getWidth()/4f, Gdx.graphics.getWidth()/4f);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
