package es.sistemasinteligentes.practica1si;

import AEstrella.Mundo;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;



//Clase principal que se lanza al empezar la ejecución de la aplicación
//Esta clase lee el mundo que se le pasa por parámetro, y lo único que hace es lanzar la pantalla que muestra el menú principal
//para esto llama a MenuPrincipal
public class GameMain extends Game {
	SpriteBatch batch;
	Texture fondo;
        
        private Mundo mundo;
        
        public GameMain(Mundo m){
            super();
            mundo = new Mundo(m);
        }
        
	@Override
	public void create () {
            batch = new SpriteBatch();
            fondo = new Texture("fondo.png");
            Gdx.graphics.setTitle("Sistemas Inteligentes");
            setScreen(new MenuPrincipal(this));
	}

	@Override
	public void render () {
            super.render();
	}
	
        public SpriteBatch getBatch(){
            return this.batch;
        }
        
        public Mundo getMundo(){
            return this.mundo;
        }
        
	@Override
	public void dispose () {
            batch.dispose();
            fondo.dispose();
	}
}
