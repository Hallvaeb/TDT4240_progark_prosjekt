package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.states.GameStateManager;
import com.mygdx.game.states.MenuState;


public class MyGdxGame extends ApplicationAdapter {
	public final static String TITLE = "RonaSlice";
	private static FireBaseInterface _FBIC;
	private GameStateManager gsm;
	private SpriteBatch batch;
	private static Music music;
	public static Music sound;

	public MyGdxGame(FireBaseInterface FBIC){
		_FBIC = FBIC;
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		//_FBIC.SomeFunction();
		//_FBIC.FirstFireBaseTest();
		//_FBIC.SetOnValueChangedListener();
		//_FBIC.SetValueInDB(ID+"/message", "new text");
		//_FBIC.SetValueInDB("message2/text2", "new text");
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new MenuState(gsm));
		music = Gdx.audio.newMusic(Gdx.files.internal("music.wav"));
		sound = Gdx.audio.newMusic(Gdx.files.internal("soundclick.wav"));

		sound.setVolume(1.0f);
		music.setVolume(0.5f);
		music.setLooping(true);
		music.play();
	}

	public static FireBaseInterface get_FBIC() {
		return _FBIC;
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}

	public static void setVolume(float number){
		music.setVolume(number);
	}

	public static void setSoundVolume(float number){
		sound.setVolume(number);
	}

	public static float returnVolume(){
		return music.getVolume();
	}
	public static float returnSoundVolume(){
		return sound.getVolume();
	}

	@Override
	public void dispose () {
		music.dispose();
		sound.dispose();
		batch.dispose();
	}
}
