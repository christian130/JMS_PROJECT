  ------------------- ---------------------------------- ---------------------------------------------------------------------------------------------------------------------------- ---
  [***1***](#page2)   > [***Implementación***](#page2)   **2**
                      > [*1.1*](#page2)                  > [*Vista General*](#page2) . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
                      > [*1.2*](#page2)                  > [*Lista de Librerías Requeridas*](#page2). . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
  **2**               > **Instalar Librerías**           **3**
                      > [*2.1*](#page3)                  > [*Apollo Broker*](#page3) . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
                      > [*2.2*](#page4)                  > . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
                      > [*2.3*](#page5)                  > . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
                      > [*2.4*](#page6)                  > [*Levantar la aplicación*](#page6) . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
  ------------------- ---------------------------------- ---------------------------------------------------------------------------------------------------------------------------- ---

  -- -- --
        
        
  -- -- --

> <span id="page2" class="anchor"></span>Implementacion

-   **Implementacion**

1.  **Vista general**

> El programa esta implementado en Spring con el plugin spring-boot,
> basicamente es un broker JMS que se conecta a una cola y que genera
> los mensajes como peticiones para luego recogerlos y guardarlos en
> mongoDB.
>
> Para compilar el programa se es necesario obtener el ultimo JDK 1.8,
> para ejecutarlo simplemente basta con el JRE. El codigo Fuente de la
> aplicacion esta en el drive…

  ----------------- -- ---------------------------------------------------------------
  Apollo producer      Estas son las fuentes de la aplicacion
                       
                       https://drive.google.com/open?id=0Bz-I92ggYYjkNm1DWmQ2a2d5cFE
  ----------------- -- ---------------------------------------------------------------

> **Probado En:**
>
> Windows 7 professional edition 32bits

2.  **Librerias**

> Las siguientes librerias que estan en rojo requieren atencion para su
> instalacion, las demas se instalan automaticamente via POM…

  --------------- ------------- ---------------------------------------------------------------------------------
  **Name**        **Version**   

                                

                                

  Apollo Broker   V 1.7.1       Esta es una carpeta ya compilada para windows y linux con el nombre de Agente02
                                
                                https://drive.google.com/open?id=0Bz-I92ggYYjkQmRGRC1JcU93NTQ

                                

  mongodb                       Base de datos no-sql tipo Document

                                

                                > Librerias
  --------------- ------------- ---------------------------------------------------------------------------------

  --------------------- -- --------------------- --
  Guía de instalacion      Christian130          
                           25 de julio de 2016   
  --------------------- -- --------------------- --

<span id="page3" class="anchor"></span>

-   **Instalar Librerias**

Realmente para instalar las librerias requeridas no se hace falta sino
realmente el JDK, favor descargar el zip con el Apollo descomprimirlo y
ejecutarlo por consola…

¿Como instalar el broker?

Para instalar el broker hay que

1.- descomprimirlo en la raiz, el Apollo tiene un detalle que tiene que
ser descomprimido en la raiz para que pueda levanter el serviciado, para
fines o propositos de este manual de ahora en Adelante utilizaremos el
Puerto 7100:

a.- Crear una carpeta en C:\\ (en la raiz de la unidad), llamada Apollo

b.- ir a la carpeta creada

c.- copiar el archivo zip descargado con el nombre apolloProducer.zip a
nuestra carpeta recien creada

d.- Click derecho “extraer aqui”/extract here

![](media/image1.png){width="1.28125in"
height="0.2916666666666667in"}![](media/image2.png){width="0.3854166666666667in"
height="0.3958333333333333in"}e.-ir a la ruta especificada en el dibujo
de abajo…

![](media/image3.png){width="1.4166666666666667in"
height="0.5208333333333334in"}d.-abrir una consola de commandos (inicio)
-&gt; todos los programas -&gt; consola de commandos

![](media/image4.png){width="6.697916666666667in"
height="4.291666666666667in"}2.- llamar a la carpeta Apollo, luego desde
ahi descomprimir el zip, luego desde alli ir a C:\\Apollo