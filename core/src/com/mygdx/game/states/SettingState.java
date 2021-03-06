package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.mygdx.game.MyGdxGame;

public class SettingState extends State{
    private static final int WIDTH = Gdx.graphics.getWidth();
    private static final int HEIGHT = Gdx.graphics.getHeight();
    private Texture img = new Texture( "bg_sky.png");
    private Sprite returnBtn = new Sprite(new Texture("return.png"));
    private Sprite settingsBtn = new Sprite(new Texture("settings_btn.png"));
    private Sprite soundText = new Sprite((new Texture("sound.png")));
    private Sprite musicText = new Sprite((new Texture("music.png")));
    private Skin uiSkin = new Skin(Gdx.files.internal("uiskin.json"));
    private Stage stage;
    private CheckBox musicButton = new CheckBox("   Turn off music",uiSkin);
    private CheckBox soundButton = new CheckBox("   Turn off sound",uiSkin);
    final Slider volumeMusicSlider = new Slider( 0f, 1f, 0.1f,false, uiSkin );
    final Slider volumeSoundSlider = new Slider( 0f, 1f, 0.1f,false, uiSkin );


    /**
     * Allows the user to adjust and mute the sound and music volume for the entire game.
     * @param gsm GameStateManager controlling the states of the application.
     */
    public SettingState(GameStateManager gsm){
        super(gsm);
        musicButton.setPosition(HEIGHT/2.35f,HEIGHT/2.35f);
        musicButton.setSize(HEIGHT/40f,HEIGHT/40f);
        soundButton.setPosition(HEIGHT/2.35f,HEIGHT/1.86f);
        soundButton.setSize(HEIGHT/40f,HEIGHT/40f);
        settingsBtn.setSize(WIDTH/3f, WIDTH/3f);
        settingsBtn.setPosition(WIDTH/2f - settingsBtn.getWidth()/2,
                HEIGHT/1.33f);
        returnBtn.setSize(WIDTH/3f, WIDTH/3f);
        returnBtn.setPosition(HEIGHT/16f, HEIGHT/16f);
        soundText.setSize(WIDTH/3f,WIDTH/3f);
        soundText.setPosition(WIDTH/2f - soundText.getWidth(),
                HEIGHT/2f);
        musicText.setSize(WIDTH/3f,WIDTH/3f);
        musicText.setPosition(WIDTH/2f - musicText.getWidth(),
                HEIGHT/2.67f);

        /**
         * Stage is used for the music settings.
         */
        stage = new Stage();
        if (MyGdxGame.returnVolume()==0){
            musicButton.toggle();
        }
        else{
            volumeMusicSlider.setValue(MyGdxGame.returnVolume());
        }
        if (MyGdxGame.returnSoundVolume()==0){
            soundButton.toggle();
        }
        else{
            volumeSoundSlider.setValue(MyGdxGame.returnSoundVolume());
        }
        volumeSoundSlider.setPosition(WIDTH/1.78f,HEIGHT/1.7f);
        volumeMusicSlider.setPosition( WIDTH/1.78f,HEIGHT/2.13f);
        stage.addActor(volumeMusicSlider);
        stage.addActor(volumeSoundSlider);
        stage.addActor(musicButton);
        stage.addActor(soundButton);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            if (returnBtn.getBoundingRectangle().contains(Gdx.input.getX(),
                    Gdx.graphics.getHeight() - Gdx.input.getY())) {
                MyGdxGame.sound.play();
                gsm.pop();
            }

            // Music
            if (volumeMusicSlider.isDragging()){
                musicButton.setChecked(false);
                MyGdxGame.setVolume(volumeMusicSlider.getValue());
                MyGdxGame.sound.play();
            }

            // Sound
            if (volumeSoundSlider.isDragging()){
                soundButton.setChecked(false);
                MyGdxGame.setSoundVolume(volumeSoundSlider.getValue());
                MyGdxGame.sound.play();
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        if(musicButton.isChecked()){
            MyGdxGame.setVolume(0.0f);
        }
        else{
            MyGdxGame.setVolume(volumeMusicSlider.getValue());
        }
        if(soundButton.isChecked()){
            MyGdxGame.setSoundVolume(0.0f);
        }
        else{
            MyGdxGame.setSoundVolume(volumeSoundSlider.getValue());
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(img,0, 0, WIDTH, HEIGHT);
        sb.draw(settingsBtn, settingsBtn.getX(), settingsBtn.getY(), WIDTH/3f ,
                WIDTH/3f);
        sb.draw(returnBtn, returnBtn.getX(), returnBtn.getY(), WIDTH/3f,
                WIDTH/3f);
        volumeMusicSlider.draw(sb,100);
        volumeSoundSlider.draw(sb,100);
        musicButton.draw(sb,100);
        soundButton.draw(sb,100);
        sb.draw(soundText, soundText.getX(), soundText.getY(), WIDTH/3f, WIDTH/3f);
        sb.draw(musicText, musicText.getX(), musicText.getY(), WIDTH/3f, WIDTH/3f);
        sb.end();

    }

    @Override
    public void dispose() {
        img.dispose();
        returnBtn.getTexture().dispose();
        settingsBtn.getTexture().dispose();
        soundText.getTexture().dispose();
        musicText.getTexture().dispose();
        uiSkin.dispose();
        stage.dispose();
    }
}
