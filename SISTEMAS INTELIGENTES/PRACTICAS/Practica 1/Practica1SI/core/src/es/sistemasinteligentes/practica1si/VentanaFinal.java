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
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
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
public class VentanaFinal implements Screen {

    SpriteBatch batch;
    Texture panel;
    int puntuacion;
    int resultado;
    BitmapFont finalFont;
    private TextureRegion texturaFondo = null;
    private ImageButton exitBtn;
    private Stage stage;
    private OrthographicCamera camera;
    private Viewport gameViewport;
    private int objetivo = 20;
    
    public VentanaFinal(int punt, int result, TextureRegion t){
        //Recoge los parámetros
        puntuacion = punt;
        resultado = result;
        texturaFondo = t;
       
        //Establece la imagen de fondo
        batch = new SpriteBatch();
        panel = new Texture("panelFinal.jpg");
        
        //Establece la fuente
        FreeTypeFontGenerator generator =
                new FreeTypeFontGenerator(Gdx.files.internal("blow.ttf"));

        //Obtiene los parámetros de la fuente para poder modificarlos
        FreeTypeFontGenerator.FreeTypeFontParameter parameter =
                new FreeTypeFontGenerator.FreeTypeFontParameter();
        //Modifica el parámetro de tamaño de fuente
        parameter.size = 100;
        
        //Asigna los parámetros modificados
        finalFont = generator.generateFont(parameter);
        
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        
        gameViewport = new StretchViewport(Gdx.graphics.getWidth(),Gdx. graphics.getHeight(), camera);
        
        exitBtn = new ImageButton(new SpriteDrawable(new Sprite(new Texture("exit.png"))));
        exitBtn.setPosition(362, 100);
        
        stage = new Stage(gameViewport, batch);
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
        
        batch.begin();
        batch.draw(texturaFondo, 0, 0, 1024, 768);
        finalFont.setColor(com.badlogic.gdx.graphics.Color.YELLOW);
        
        if(puntuacion >=objetivo)
            finalFont.draw(batch, "Lo has conseguido", 130, 450);
        else
            finalFont.draw(batch, "Has fallado", 285, 450);
        batch.end();
        
        stage.addActor(exitBtn);
        stage.draw();
        
    }

        void addAllListeners(){
        
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
        panel.dispose();
    }
    
}
