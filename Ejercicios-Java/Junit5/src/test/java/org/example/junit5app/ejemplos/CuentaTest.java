package org.example.junit5app.ejemplos;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*; //Importamos métodos estáticos
import static org.junit.jupiter.api.Assumptions.*;

class CuentaTest {

    //Observemos que estos métodos son estáticos
    @BeforeAll
    static void beforeAll() {
        System.out.println("Inicializando la clase testa");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Finalizando todos los tests");
    }

    //Elementos en común que utiliza cada test
    Cuenta cuenta1;
    Cuenta cuenta2;
    BigDecimal saldo1;
    BigDecimal retiro1;
    BigDecimal saldo2;
    BigDecimal retiro2;

    //Estos métodos se ejecutan antes y después de cada test
    @BeforeEach
    void beforeEach() {
        this.saldo1 = new BigDecimal("1000");
        this.saldo2 = new BigDecimal("1000");
        this.retiro1 = new BigDecimal("500");
        this.retiro2 = new BigDecimal("5000");
        this.cuenta1 = new Cuenta("Juan", saldo1);
        this.cuenta2 = new Cuenta("Carlos", saldo2);

        System.out.println("-----------------Iniciando test--------------");
    }

    @AfterEach
    void tearDown() {
        System.out.println("----------------Finalizando test----------------");
    }


    //------------------------------------------------------------------------------------------------

    @Nested
    @DisplayName("Primeros métodos")
    @Tag("Primeros métodos")
    class testsPrimeraFase{
        @Test
        @Disabled
        void testNombreCuenta(){
            fail(); //Hacer que falle a propósito
            cuenta1.setNombre("Juan");
            //Valor esperado vs valor real
            Assertions.assertEquals(cuenta1.getNombre(),"Juan");
            //Recibe un boolean
            assertTrue(cuenta1.getNombre().equals("Juan"));
        }

        @Test
        @DisplayName("Test saldo cuenta")
        void testSaldoCuenta(){
            //alt+ctrl+v
            Double saldo_esperado = 1000.0;
            Double saldo_real = cuenta1.getSaldo().doubleValue();
            //Compara dos valores para ver si son iguales
            assertEquals(saldo_esperado,saldo_real);
            //Si regresa falso pasa la prueba
            //Num > 0 => 1, Num = 0 => 0, Num < 0 => -1
            int Num = cuenta1.getSaldo().compareTo(BigDecimal.ZERO);
            assertFalse(Num<0);
            assertTrue(Num>0);

        }

        @Test
        @DisplayName("Comparar por valor")
        void testCompararPorValor() {
            //CTRL+D
            Cuenta cuenta1_1 = new Cuenta("Juan", new BigDecimal("1000"));

            assertEquals(cuenta1,cuenta1_1);
        }

        @Test
        void testQuitarSaldo() {
            BigDecimal saldo = new BigDecimal("1000");
            BigDecimal retiro = new BigDecimal("500");
            Double valor_esperado = saldo.doubleValue() - retiro.doubleValue();

            Cuenta cuenta = new Cuenta("Juan", saldo);
            cuenta.quitarSaldo(retiro);
            Double valor_real = cuenta.getSaldo().doubleValue();

            assertEquals(valor_esperado,valor_real);
            assertEquals(valor_esperado.toString(),valor_esperado.toString());
        }

        @Test
        void testAgregarSaldo() {
            BigDecimal saldo = new BigDecimal("1000");
            BigDecimal deposito = new BigDecimal("500");
            Double valor_esperado = saldo.doubleValue() + deposito.doubleValue();

            Cuenta cuenta = new Cuenta("Juan", saldo);
            cuenta.agregarSaldo(deposito);
            Double valor_real = cuenta.getSaldo().doubleValue();

            assertEquals(valor_esperado,valor_real);
            assertEquals(valor_esperado.toString(),valor_esperado.toString());
        }

        @Test
        void TestDineroInsuficienteException() {
            BigDecimal saldo = new BigDecimal("1000");
            BigDecimal retiro = new BigDecimal("5000");
            Double valor_esperado = saldo.doubleValue() - retiro.doubleValue();
            Cuenta cuenta = new Cuenta("Juan", saldo);

            Exception e =  assertThrows(DineroInsuficienteException.class,
                    ()->{
                        cuenta.quitarSaldo(retiro);
                    });

            String mensaje_real = e.getMessage();
            String mensaje_esperado = "Dinero insuficiente";

            assertEquals(mensaje_esperado,mensaje_real);
        }

        @Test
        void testTransferirDineroCuentas() {
            Double saldo_esperado1 = saldo1.doubleValue() - retiro1.doubleValue();
            Double saldo_esperado2 = saldo2.doubleValue() + retiro1.doubleValue();


            Cuenta cuenta1 = new Cuenta("Juan", saldo1);
            Cuenta cuenta2 = new Cuenta("Carlos", saldo2);
            Banco bancoB = new Banco("Banamex");
            bancoB.transferir(cuenta1,cuenta2,retiro1);

            Double saldo_real1 = cuenta1.getSaldo().doubleValue();
            Double saldo_real2 = cuenta2.getSaldo().doubleValue();


            assertEquals(saldo_esperado1,saldo_real1);
            assertEquals(saldo_esperado2,saldo_real2);

        }

        @Test
        @DisplayName("Relación entre el banco y las cuentas")
        void testRelacionBancoCuentas() {
            Double saldo_esperado1 = saldo1.doubleValue() - retiro1.doubleValue();
            Double saldo_esperado2 = saldo2.doubleValue() + retiro1.doubleValue();


            Banco bancoB = new Banco("Banamex");
            bancoB.addCuenta(cuenta1);
            bancoB.addCuenta(cuenta2);
            bancoB.transferir(cuenta1,cuenta2,retiro1);

            Double saldo_real1 = cuenta1.getSaldo().doubleValue();
            Double saldo_real2 = cuenta2.getSaldo().doubleValue();


            Boolean existe_cliente = bancoB.getCuentas()
                    .stream()
                    .anyMatch(cuenta -> cuenta.getNombre().equals("Juan"));

            String nombre_cliente = bancoB.getCuentas()
                    .stream().filter(cuenta -> cuenta.getNombre().equals("Juan"))
                    .findFirst().get().getNombre();

            assertAll(
                    ()->{assertEquals(2,bancoB.getCuentas().size());},
                    ()->{assertEquals(2,bancoB.getCuentas().size());},
                    ()->{assertEquals("Banamex",cuenta1.getBanco().getNombre());},
                    ()->{assertTrue(existe_cliente,
                            ()->"El cliente no existe");},

                    ()->{assertEquals("Juan",nombre_cliente,
                            ()->"No ha clientes con ese nombre");},

                    ()->{assertEquals(saldo_esperado1,saldo_real1,
                            ()->"El valor del saldo de la cuenta 1 no es el esperado");},

                    ()->{assertEquals(saldo_esperado2,saldo_real2,
                            ()->"El valor del saldo de la cuenta 2 no es el esperado");}
            );
        }
    }

    @Nested
    @DisplayName("Métodos posteriores")
    class testsSegundaFase{
        @Test
        @EnabledOnOs(OS.LINUX)
        void soloLinux(){
        }

        @Test
        void imprimirProperties(){
            Properties properties = System.getProperties();
            properties.forEach((clave,valor)-> System.out.println(clave+"-"+valor));
        }

        @Test
        void testSaldoCuentaDev(){

            boolean dev_environment = "dev".equals(System.getProperty("ENV"));
            //Si el assumeTrue es True hace la prueba, si no la salta
            assumeTrue(dev_environment);
            Double saldo_esperado = 1000.0;
            Double saldo_real = cuenta1.getSaldo().doubleValue();
            assertEquals(saldo_esperado,saldo_real);
            int Num = cuenta1.getSaldo().compareTo(BigDecimal.ZERO);
            assertFalse(Num<0);
            assertTrue(Num>0);

        }

        @Test
        void testSaldoCuentaDev2(){
            boolean dev_environment = "dev".equals(System.getProperty("ENV"));
            //Ejecuta los tests dentro de las llaves si se cumple la condición
            int Num = cuenta1.getSaldo().compareTo(BigDecimal.ZERO);
            Double saldo_esperado = 1000.0;
            Double saldo_real = cuenta1.getSaldo().doubleValue();
            assumingThat(dev_environment,
                    ()->{
                        assertEquals(saldo_esperado+1,saldo_real);
                    });
            assertFalse(Num<0);
        }

        @Test
        void metodoPrueba(){
            fail();
        }
    }

    @Nested
    class pruebasParametrizadas{

        @ParameterizedTest(name = "Prueba no. {index} ejecutando con valor {argumentsWithNames}")
        @ValueSource(strings = {"100", "200", "300", "500", "700", "1000.12345"})
        void testDebitoCuentaValueSource(String monto) {
            cuenta1.quitarSaldo(new BigDecimal(monto) );
            assertNotNull(cuenta1.getSaldo());
            assertTrue(cuenta1.getSaldo().compareTo(BigDecimal.ZERO) > 0);
        }

        @ParameterizedTest(name = "numero {index} ejecutando con valor {argumentsWithNames}")
        @CsvSource({"1,100", "2,200", "3,300", "4,500", "5,700", "6,1000.12345"})
        @Disabled
        void testDebitoCuentaCsvSource(String index, String monto) {
            System.out.println(index + " -> " + monto);
            cuenta1.quitarSaldo(new BigDecimal(monto));
            assertNotNull(cuenta1.getSaldo());
            assertTrue(cuenta1.getSaldo().compareTo(BigDecimal.ZERO) > 0);
        }


        @ParameterizedTest(name = "numero {index} ejecutando con valor {0} - {argumentsWithNames}")
        @Disabled
        @CsvFileSource(resources = "/data.csv")
        void testDebitoCuentaCsvFileSource(String monto) {
            cuenta1.quitarSaldo(new BigDecimal(monto));
            assertNotNull(cuenta1.getSaldo());
            assertTrue(cuenta1.getSaldo().compareTo(BigDecimal.ZERO) > 0);
        }

    }

}