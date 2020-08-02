package ppss;

public class AlquilaCochesTestable extends AlquilaCoches {
   private IService servicio;
   
   public AlquilaCochesTestable(IService servicio) {
       this.servicio = servicio;
   }
   
   @Override
   public IService getServicio() {
        return servicio;
    }    
}
