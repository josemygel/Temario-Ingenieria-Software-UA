/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sistemasinteligentes.practica1si;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 *
 * @author mirse
 */
public class MenuPrincipal implements Screen{

    private GameMain game;
    private OrthographicCamera camera;
    private Viewport gameViewport;
    private Texture bg;
    
    private ImageButton playBtn;
    private ImageButton exitBtn;
    
    private Stage stage;
    
    //Pantalla que muestra el menú principal con 2 botones
    //Botón de empezar a jugar que llama a Juego pasándole el mundo
    //y el botón de salir que cierra la aplicación
    public MenuPrincipal(GameMain game){
        this.game = game;
        
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        
        gameViewport = new StretchViewport(Gdx.graphics.getWidth(),Gdx. graphics.getHeight(), camera);
        
        bg = new Texture("fondoMenu.png");
        
        //Creamos los dos botones de la pantalla
        playBtn = new ImageButton(new SpriteDrawable(new Sprite(new Texture("play.png"))));
        exitBtn = new ImageButton(new SpriteDrawable(new Sprite(new Texture("exit.png"))));
        playBtn.setPosition(362, 230);
        exitBtn.setPosition(362, 50);
        
        stage = new Stage(gameViewport, game.getBatch());
        Gdx.input.setInputProcessor(stage);
        addAllListeners();

    }
    
    @Override
    public void show() {
        
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        //Para empezar a dibujar en la pantalla
        game.getBatch().begin();
        //Dibuja el fondo
        game.getBatch().draw(bg, 0, 0);
        game.getBatch().end(); //Indica que terminamos de dibujar en la pantalla
       
        stage.addActor(playBtn);
        stage.addActor(exitBtn);
        stage.draw();
    }

    void addAllListeners(){
        playBtn.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                game.setScreen(new Juego(game));
            }
        });
        
        exitBtn.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
        
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
        bg.dispose();
        playBtn.getStage().dispose();
        exitBtn.getStage().dispose();
        stage.dispose();
    }
    
}
