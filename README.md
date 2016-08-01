------------------- ---------------------------------- ---------------------------------------------------------------------------------------------------------------------------- ---
  

[***1***]

[***Implementación***]

[*Vista General*]

[*Librerías*]

 **2** **Instalar Librerías**           **3** 
  
[*Apollo Broker*] 

[*Levantar la aplicación*]
  ------------------- ---------------------------------- ---------------------------------------------------------------------------------------------------------------------------- ---

  -- -- --
        
        
  -- -- --

Implementacion

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
>> en el momento de realizar esta guia, no existia un repositorio, en orden de compilar las fuentes que estan en este documento por favor siga abajo en el apartado compilar fuentes

  ----------------- -- ---------------------------------------------------------------
  Apollo producer      Estas son las fuentes iniciales de la aplicacion (tambien se pueden obtener con el primer commit usando la interfaz grafica de bitbucket)
                       
                      [ https://drive.google.com/open?id=0Bz-I92ggYYjkNm1DWmQ2a2d5cFE](Link URL)
  ----------------- -- ---------------------------------------------------------------

> **Probado En:**
>
> Windows 7 professional edition 32bits

2.  **Librerias**

> Las siguientes son una lista de librerias que estan en rojo requieren atencion para su
> instalacion, las demas se instalan automaticamente via POM…

  --------------- ------------- ---------------------------------------------------------------------------------
  **Name**        **Version**   

                                

                                

  Apollo Broker   V 1.7.1       Esta es una carpeta ya compilada para windows y linux con el nombre de Agente02
                                
                                [https://drive.google.com/open?id=0Bz-I92ggYYjkQmRGRC1JcU93NTQ](Link URL)

                                

  mongodb                       Base de datos no-sql tipo Document

                                

                                > Librerias
  --------------- ------------- ---------------------------------------------------------------------------------

  --------------------- -- --------------------- --
  Guía de instalacion      Christian130          
                           25 de julio de 2016   
  --------------------- -- --------------------- --



-   **Apollo Broker**

Realmente para instalar las librerias requeridas no se hace falta sino
realmente el JDK, favor descargar el zip con el Apollo descomprimirlo y
ejecutarlo por consola…

 **Instalar Librerias**
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
e.-ir a la ruta especificada en el dibujo
de abajo…

d.-abrir una consola de commandos (inicio)![inicio.png](https://bitbucket.org/repo/jLB75o/images/1303404590-inicio.png)
> todos los programas > consola de commandos

 **Levantar la aplicación**

Con la consola de comandos (en windows) abierto ejecutar: 
```
#!bash

C:\Apollo\Agente02\bin\apollo-broker.cmd run
```

Luego si hemos serviciado nuestra BD no-relacional tipo documento MongoDB, simplemente llamar mongo con el comando 
```
#!bash

mongo
```
 y verificar que la base de datos fieldvision_demo, con el comando: 
```
#!bash

show databases;
```
De lo contrario favor ir a confluence y descargar el siguiente archivo [https://dev-datatraffic.atlassian.net/wiki/download/attachments/34570243/fieldvision_demo.tar.gz?api=v2 ](Link URL)(verificar si se tienen los permisos necesarios), si no se tienen permisos prontamente se añadiran a este repositorio todos los archivos necesarios (para levantar la aplicacion se necesitan una serie de pasos en los cuales se pueden encontrar en[ https://dev-datatraffic.atlassian.net/wiki/display/SUP/Herramientas+ETL+con+Pentaho+Data+Integratio](Link URL)n)