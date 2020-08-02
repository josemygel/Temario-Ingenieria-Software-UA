package ppss.Premio;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.easymock.Mock;
import org.easymock.MockType;
import org.easymock.internal.MocksControl;
import org.junit.jupiter.api.*;
import ppss.Premio.excepciones.ClienteWebServiceException;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;

class PremioTest {
//    IMocksControl imc = EasyMock.createStrictControl();
    IMocksControl imc = new MocksControl(MockType.STRICT);
    Premio sut;
    float random;
    String indirecta;
    String esperado;
    String real;

    @BeforeEach
    void init() { //Iniciamos el controlador estricto (Es necesario que sea estricto?)
        sut = partialMockBuilder(Premio.class)
                .addMockedMethod("generaNumero").createMock(imc);
        sut.cliente = imc.createMock(ClienteWebService.class);
    }

    @AfterEach //VERIFICAMOS TODOS LOS TESTS
    void verify(){
        imc.verify();
        imc.resetToStrict();
    }

    @Test
    @DisplayName("P06_E2_C1")
    void compruebaPremioC1() {
        //Entradas (ENTRADAS INDIRECTAS, PROVIENEN DE LOS DOC)
        random = 0.07f;
        indirecta = "entrada final Champions"; //Entrada INDIRECTA
        //Salidas
        esperado = "Premiado con " + indirecta;

        //Creamos e inyectamos Mocks
        expect(sut.generaNumero()).andReturn(random).times(1);
        assertDoesNotThrow(()->expect(sut.cliente.obtenerPremio()).andReturn(indirecta));

        //Grabamos
        imc.replay();
        //INVOCAMOS AL SUT
        real = sut.compruebaPremio();
        //Comprobamos resultado
        assertEquals(esperado,real,"Comprobando entrada final a champions");

    }

    @Test
    @DisplayName("P06_E2_C2")
    void compruebaPremioC2() {
        //Entradas (ENTRADAS INDIRECTAS, PROVIENEN DE LOS DOC)
        random = 0.03f;
        //Salidas
        esperado = "No se ha podido obtener el premio";

        //Creamos e inyectamos Mocks
        expect(sut.generaNumero()).andReturn(random).times(1);
        assertDoesNotThrow(()-> expect(sut.cliente.obtenerPremio()).andThrow(new ClienteWebServiceException()));
        //Grabamos
        imc.replay();
        //INVOCAMOS AL SUT
        real = sut.compruebaPremio();
        //Comprobamos resultado
        assertEquals(esperado,real,"Comprobando excepción");
    }

    @Test
    @DisplayName("P06_E2_C3")
    void compruebaPremioC3() throws ClienteWebServiceException {
        //Entradas (ENTRADAS INDIRECTAS, PROVIENEN DE LOS DOC)
        random = 0.3f;
        //Salidas
        esperado = "Sin premio";

        //Creamos e inyectamos Mocks
        expect(sut.generaNumero()).andReturn(random).times(1);

        //Grabamos
        imc.replay();
        //INVOCAMOS AL SUT
        real = sut.compruebaPremio();
        //Comprobamos resultado
        assertEquals(esperado,real,"Comprobando sin premio");
    }
}