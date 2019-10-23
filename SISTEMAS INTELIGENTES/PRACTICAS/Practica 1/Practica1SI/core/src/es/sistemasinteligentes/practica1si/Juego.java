/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sistemasinteligentes.practica1si;

import AEstrella.AEstrella;
import AEstrella.Coordenada;
import AEstrella.Mundo;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.HexagonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.ScreenUtils;


/**
 *
 * @author mirse
 */
public class Juego implements Screen{
    SpriteBatch batch;
    Texture fondo;
     GameMain game;
     
    //Objetos necesarios para crear el entorno
    TiledMap map;
    MapLayers layers;
    TiledMapTile[] tiles;
    TiledMapTileLayer layer_mundo;
    TiledMapTileLayer layer_camino;
    TiledMapTileLayer layer_personajes;
    Texture hexture;
    
    //Propiedades del mapa necesarias para dibujar correctamente el tablero
    private int tileWidth, tileHeight,
            mapWidthInTiles, mapHeightInTiles,
            mapWidthInPixels, mapHeightInPixels;
        
    //Cámara
    private OrthographicCamera camera;
        
    //Objeto que dibujará la escena
    private HexagonalTiledMapRenderer renderer;
    
    //Objetos necesarios para el juego
    private Mundo mundo; //Contiene el mundo
    private AEstrella solucion; //Calculará el A* para poder dibujar el camino
    int resultado = 0;
    
    private float score;
    private Label scoreLabel;
    private BitmapFont font;
    private Stage stage;
    BitmapFont scoreFont;
    
    private TextureRegion texturaCapturada = null;
    
    //Constructor del juego
    public Juego (GameMain game){
        this.game = game;
        //Obtiene el mundo
        mundo = game.getMundo();
        //Calcula el camino para la situación inicial del tablero
        solucion = new AEstrella(mundo);
        resultado = solucion.CalcularAEstrella();
        score = solucion.getCosteTotal();
        
        //Establece la imagen la fondo
        batch = new SpriteBatch();
        fondo = new Texture("fondo.png");
        texturaCapturada = new TextureRegion();
            
        //Lee las texturas de celda del fichero Tileset.png y la separa en diferentes texturas
        hexture = new Texture(Gdx.files.internal("Tileset.png"));
        TextureRegion[][] hexes = TextureRegion.split(hexture, 72, 72);
            
        //Crea un mapa con las diferentes texturas de las celdas
        map = new TiledMap();
        layers = map.getLayers();
        tiles = new TiledMapTile[9];
        tiles[0] = new StaticTiledMapTile(new TextureRegion(hexes[0][0])); //Textura para las celdas de borde 'b'
        tiles[1] = new StaticTiledMapTile(new TextureRegion(hexes[0][1])); //Textura para las celdas con piedra 'p'
        tiles[2] = new StaticTiledMapTile(new TextureRegion(hexes[0][2])); //Textura para las celdas de camino 'c'
        tiles[3] = new StaticTiledMapTile(new TextureRegion(hexes[1][0])); //Textura para las celdas con hierba 'h'
        tiles[4] = new StaticTiledMapTile(new TextureRegion(hexes[1][1])); //Textura para las celdas con agua 'a'
        tiles[5] = new StaticTiledMapTile(new TextureRegion(hexes[1][2])); //Textura para la celda del caballero 'k'
        tiles[6] = new StaticTiledMapTile(new TextureRegion(hexes[2][0])); //Tectura para la celda del dragón 'd'
        tiles[7] = new StaticTiledMapTile(new TextureRegion(hexes[2][1])); //Textura para la celda que es solución del A*
        tiles[8] = new StaticTiledMapTile(new TextureRegion(hexes[2][2])); //Textura para marcar la posición del ratón
        

        //Crea la capa con el mundo   
        layer_mundo = new TiledMapTileLayer(14, 10, 72, 72);
        crearMundo();
        layers.add(layer_mundo);
        
        //Crea la capa con el camino del A*
        crearCamino();
	layers.add(layer_camino);  

        //Crea la capa con la posición de los personajes
        crearPersonajes();
        layers.add(layer_personajes);
        
            
        //Establece las propiedades del mapa para dibujarlo correctamente
        String mapOrientation = "hexagonal";
        int mapWidth = 14;
        int mapHeight = 10;
        int tileWidth = 72;
        int tileHeight = 72;
        int hexSideLength = 35;
        String staggerAxis = "y";
        String staggerIndex = "odd";
                
        MapProperties mapProperties = map.getProperties();
            mapProperties.put("orientation", mapOrientation);
            mapProperties.put("width", mapWidth);
            mapProperties.put("height", mapHeight);
            mapProperties.put("tilewidth", tileWidth);
            mapProperties.put("tileheight", tileHeight);
            mapProperties.put("hexsidelength", hexSideLength);
            mapProperties.put("staggeraxis", staggerAxis);
            mapProperties.put("staggerindex", staggerIndex);
		
        //Inicializa la cámara
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        camera.update();
        
        //Inicializa el objeto que va a dibujar
        renderer = new HexagonalTiledMapRenderer(map, .94f);
        
        //Crea la etiqueta para mostrar el coste del camino
        crearEtiquetaCoste();
         
        //Crea un listener para el evento de pulsar el botón izquierdo del ratón
        //Se utiliza para cuando se pulsa en el tablero para colocar un muro de piedra
        Gdx.input.setInputProcessor(new InputAdapter () {
            @Override
                public boolean touchDown (int x, int y, int pointer, int button) {
                    if(button == Input.Buttons.LEFT)
                        actualizarMundo(x, y);
                    return true; // return true to indicate the event was handled
                }
        });
    }
    
    //Crea la capa con el mundo
    void crearMundo(){
        //Recorre las diferentes celdas del mundo para ver qué textura debe poner en cada celda
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 14; x++) {                        
                Cell cell = new Cell();
                        
                switch(mundo.getMundo()[y][x]){
                    case 'b': cell.setTile(tiles[0]); //Textura de borde
                              break;
                    case 'c': cell.setTile(tiles[2]); //Textura de camino
                              break;
                    case 'a': cell.setTile(tiles[4]); //Textura de agua
                              break;
                    case 'h': cell.setTile(tiles[3]); //Textura de hierba
                              break;
                    case 'p': cell.setTile(tiles[1]); //Textyra de piedra
                              break;
                }
			
		layer_mundo.setCell(x, 9-y, cell); //Introduce la celda en la capa del mundo
            }
	}
        layer_mundo.setOffsetX(20); //Indica un desplazamiento de 20 píxeles en el ejeX para centrar el tablero en la pantalla
    }
    
    //Crea la capa con el camino
    void crearCamino(){
        //Recorre la solución obtenida por el A* para mostrar por pantalla el camino calculado
        //Las celdas que forman parte del camino del A* las resalta con un borde amarillo
        layer_camino = new TiledMapTileLayer(14, 10, 72, 72);
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 14; x++) {                        
		Cell cell_camino = new Cell();
                if(solucion.camino[y][x] == 'X')
                {
                    cell_camino.setTile(tiles[7]); //Textura con borde amarillo
                    layer_camino.setCell(x, 9-y, cell_camino);
                }
            }
        }
        layer_camino.setOffsetX(20);
    }
    
    //Crea la capa con los personajes
    void crearPersonajes(){
        //Recorre el mundo para ver dónde están los personajes e indica la textura de esas celdas
        layer_personajes = new TiledMapTileLayer(14, 10, 72, 72);
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 14; x++) {                        
                Cell cell_personaje = new Cell();
                    switch(mundo.getMundo()[y][x]){
                        case 'k': cell_personaje.setTile(tiles[5]); //Textura del caballero
                                  break;
                        case 'd': cell_personaje.setTile(tiles[6]); //Textura del dragón
                                  break;
                        }
			
			layer_personajes.setCell(x, 9-y, cell_personaje);
            }
        }
        layer_personajes.setOffsetX(20);
    }
    
    //Función que actualiza el mundo cuando el usuario hace clic sobre el tablero
    void actualizarMundo(int screen_x, int screen_y){
        int mouseX;
        int mouseY;
        Coordenada celda;
        
        //Lee las coordenadas donde el usuario ha hecho clic
        mouseX = screen_x;
        mouseY = screen_y;
        System.out.println("Se ha pulsado el botón izquierdo en las coordenadas: "+mouseX+", "+mouseY);
        //A partir de las coordenadas, calculada el hexágono donde se ha hecho clic
        celda = obtenerCeldaPulsada(mouseX, mouseY);
        System.out.println("Se ha pulsado la celda: " + celda.getY() +", " + celda.getX());
        
        //Actualiza el mundo para en la celda donde se ha hecho clic, añadir un muro de piedra
        Cell cell = new Cell();
        cell.setTile(tiles[1]);
        layer_mundo.setCell(celda.getX(), 9-celda.getY(),cell);
        mundo.setCelda(celda.getX(), celda.getY(), 'p'); //Textyra de muro de piedra
        
        //En cada "tirada" los personajes se mueven aleatoriamente una casilla
        layers.remove(layer_personajes);
        moverPersonajes(); //Calcula de manera aleatoria un movimiento para cada personaje
        crearPersonajes();
            
        //Como el mundo ha cambiado (se ha añadido un muro de piedra y se han movido los personajes)
        //Vuelve a calcular el A* y lo vuelve a dibujar
        layers.remove(layer_camino);
        solucion.reiniciarAEstrella(mundo);
        resultado = solucion.CalcularAEstrella();
        score = solucion.getCosteTotal();
        crearCamino();
        layers.add(layer_camino);
        
        layers.add(layer_personajes);
    }
    
    
    //Calcula un movimiento al azar de los personajes
    void moverPersonajes(){
        int movimientos_pares[][] = new int[6][2];
        int movimientos_impares[][] = new int[6][2];
        Coordenada cab;
        Coordenada dra;
        int nueva_y, nueva_x;
        char celda;
        boolean mov_cab = false;
        boolean mov_dra = false;
        
        //Establece los movimientos posibles para las filas pares
        movimientos_pares[0][0] = -1; movimientos_pares[0][1] = 0; //Hacia arriba izquierda, resta una fila
        movimientos_pares[1][0] = -1; movimientos_pares[1][1] = 1; //Hacia arriba derecha, resta una fila y suma una columna
        movimientos_pares[2][0] = 0; movimientos_pares[2][1] = -1; //Retroceder izquierda, resta columna
        movimientos_pares[3][0] = 0; movimientos_pares[3][1] = 1; //Avanzar derecha, suma columna
        movimientos_pares[4][0] = 1; movimientos_pares[4][1] = 0; //Hacia abajo izquierda, suma fila
        movimientos_pares[5][0] = 1; movimientos_pares[5][1] = 1; //Hacia abajo derecha, suma fila y suma columna

        //Establece los movimientos posibles para las filas impares
        movimientos_impares[0][0] = -1; movimientos_impares[0][1] = -1; //Hacia arriba izquierda, resta una fila y resta una columna
        movimientos_impares[1][0] = -1; movimientos_impares[1][1] = 0; //Hacia arriba derecha, resta una fila
        movimientos_impares[2][0] = 0; movimientos_impares[2][1] = -1; //Retroceder izquierda, resta columna
        movimientos_impares[3][0] = 0; movimientos_impares[3][1] = 1; //Avanzar derecha, suma columna
        movimientos_impares[4][0] = 1; movimientos_impares[4][1] = -1; //Hacia abajo izquierda, suma fila y resta una columna
        movimientos_impares[5][0] = 1; movimientos_impares[5][1] = 0; //Hacia abajo derecha, suma fila
        
        //Obtiene las coordenadas actuales de los personajes
        cab = mundo.getCaballero();
        dra = mundo.getDragon();
        
        //Calcula movimiento al azar del caballero
        do{
            int id = (int)(Math.random() * 5);
            //Si el caballero se encuentra en una fila par
            if(cab.getY()%2 == 0){
                nueva_y = cab.getY() + movimientos_pares[id][0];
                nueva_x = cab.getX() + movimientos_pares[id][1];
                
            }else{ //si el caballero se encuentra en una fila impar
                nueva_y = cab.getY() + movimientos_impares[id][0];
                nueva_x = cab.getX() + movimientos_impares[id][1];
            }
            
            //obtiene la celda calculada al azar
            celda = mundo.getCelda(nueva_x, nueva_y);
            //Comprueba si es una celda que se puede visitar y, si es así, cambia la posición del caballero y actualiza el mundo
            if(celda!='p' && celda!='b'){
                mundo.setCelda(cab.getX(), cab.getY(), 'c');
                mundo.setCelda(nueva_x, nueva_y, 'k');
                mundo.setCaballero(nueva_x, nueva_y);
                mov_cab = true;
            }
        }while(!mov_cab);
        
        //Calcula movimiento al azar del dragón
        do{
            int id = (int)(Math.random() * 5);
            //Si el dragon se encuentra en una fila par
            if(dra.getY()%2 == 0){
                nueva_y = dra.getY() + movimientos_pares[id][0];
                nueva_x = dra.getX() + movimientos_pares[id][1];
                
            }else{ //si el caballero se encuentra en una fila impar
                nueva_y = dra.getY() + movimientos_impares[id][0];
                nueva_x = dra.getX() + movimientos_impares[id][1];
            }
            
            //Obtiene la nueva celda calculada
            celda = mundo.getCelda(nueva_x, nueva_y);
            //Comprueba si es una celda que se puede visitar y, si es así, actualiza la posición del dragón y el mundo
            if(celda!='p' && celda!='b'){
                mundo.setCelda(dra.getX(), dra.getY(), 'c');
                mundo.setCelda(nueva_x, nueva_y, 'd');
                mundo.setDragon(nueva_x, nueva_y);
                mov_dra = true;
            }
        }while(!mov_dra);    
    }
    
    
    private final Coordenada obtenerCeldaPulsada(int x, int y){
        int columna, fila;
        
        //245 es el offset superior, 72 es el tamaño de las celdas
        fila = (y-245-15)/52;
        
        if(fila%2 != 0){
            //20 es el offset izquierdo, 71 es el tamaño de las celdas
            columna = (x-20)/70;
        }else{
            //20 es el offest izquierdo, 71/2 es la mitad de la celda que las filas impares se desvían a la derecha, 71 es el tamaño de la celda
            columna = (x-20-71/2)/70;
        }
        
        return new Coordenada(columna,fila);
    }
    
   
    //Crea la etiqueta que muestra el coste
    void crearEtiquetaCoste(){
        //Establece la fuente
        FreeTypeFontGenerator generator =
                new FreeTypeFontGenerator(Gdx.files.internal("blow.ttf"));

        //Obtiene los parámetros de la fuente para poder modificarlos
        FreeTypeFontGenerator.FreeTypeFontParameter parameter =
                new FreeTypeFontGenerator.FreeTypeFontParameter();
        //Modifica el parámetro de tamaño de fuente
        parameter.size = 80;
        //Asigna los parámetros modificados
        scoreFont = generator.generateFont(parameter);
    }
    
    @Override
    public void show() {
        
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.begin();
        batch.draw(fondo, 0, 0);
        scoreFont.setColor(com.badlogic.gdx.graphics.Color.YELLOW);
        scoreFont.draw(batch, String.valueOf((int)score), 900, 720);
        batch.end();
                
        camera.update();
        renderer.setView(camera);
        renderer.render();   
        
        if(score >= 20 || resultado == -1){
            texturaCapturada = ScreenUtils.getFrameBufferTexture();
            game.setScreen(new VentanaFinal((int)score, resultado, texturaCapturada));
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
        batch.dispose();
        //img.dispose();
        renderer.dispose();
        hexture.dispose();
        map.dispose();
    }

}
