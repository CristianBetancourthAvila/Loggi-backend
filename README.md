<h1 align="center">
  <br>
    <a href="http://testlogiiventrasfrontend.s3-website-us-east-1.amazonaws.com/assets/img/svg/logo-paga-todo.svg" target="_blank">
        <img src="https://dev.azure.com/KonexInnovation/ad87ca27-ff98-4e60-8824-a732701c031c/_apis/git/repositories/a875b1c2-5fbf-4745-9cc2-6006d8a6624e/items?path=/recursos/imagenes/logos/logo-paga-todo.png&versionDescriptor%5BversionOptions%5D=0&versionDescriptor%5BversionType%5D=0&versionDescriptor%5Bversion%5D=main&resolveLfs=true&%24format=octetStream&api-version=5.0" 
            alt="Logo paga todo para todos" >
    </a>
  <br>
   PAGA TODO BACKEND OPERACIÓN FINANCIERA Y CONTABLE
  <br>
</h1>
<br>
<p align="center">
        <a href="https://www.oracle.com/java/technologies/javase-jdk17-downloads.html" target="_new"> <i class="fab fa-java"></i>  <img src="https://img.shields.io/badge/Java-17-blue" alt="Java Version"></a>
        <a href="https://spring.io/projects/spring-boot" target="_new"><img src="https://img.shields.io/badge/Spring%20Boot-3.1.4-green" alt="Spring Boot Version"></a>
        <a href="https://maven.apache.org/download.cgi" target="_new"><img src="https://img.shields.io/badge/Maven-4.0.0-red" alt="Maven Version"></a>
        <a href="https://junit.org/junit5/docs/current/user-guide/" target="_new"><img src="https://img.shields.io/badge/JUnit-5.9.2-yellow" alt="JUnit Version"></a>
        <a href="https://site.mockito.org/" target="_new"><img src="https://img.shields.io/badge/Mockito-5.2.0-orange" alt="Mockito Version"></a>
        <a href="https://www.oracle.com/database/technologies/oracle19c-downloads.html" target="_new"><img src="https://img.shields.io/badge/Oracle-19c-purple" alt="Oracle Version"></a>
</p>
<br>
<p align="center">
  <a href="#descripcion">Descripción</a> •
  <a href="#dependencias">Dependencias</a> •
  <a href="#como-usarlo">Como usarlo</a> •
  <a href="#soporte">Soporte</a> •
  <a href="#colaboradores">Colaboradores</a> •
  <a href="#versiones">Versiones</a> •
</p>

## Descripción
El microservicio PAGA TODO BACKEND OPERACIÓN FINANCIERA Y CONTABLE se encarga de gestionar todo lo relacionado a las transacciones con caja , desde crear una caja hasta apertura o cerrar.

## Dependencias
El proyecto base se encuentra desarrollado sobre la versión 3.1.4 de Spring Boot, para las dependencias del proyecto se presenta en la siguiente tabla cuales son las utilizadas:

| Dependencia | Descripción | Versión |
| ------ | ------ |-------|
| Spring Boot Actuator | Para monitorear y administrar la aplicación | Versión del padre (3.1.4) |
| Spring Boot Cache | Para almacenar datos en caché en la aplicación | Versión del padre (3.1.4) |
| Spring Boot Data JPA | Para trabajar con bases de datos relacionales usando JPA | Versión del padre (3.1.4) |
| Spring Boot Web | Para crear aplicaciones web con Spring MVC | Versión del padre (3.1.4) |
| Spring Boot Test |  Para probar aplicaciones Spring Boot | Versión del padre (3.1.4) |
| Jakarta Persistence | Para trabajar con bases de datos relacionales usando JPA | Versión 3.1.0 |
| Project Lombok  | Para reducir el código repetitivo en las clases de Java | Versión del padre (1.18.24)|
| Spring Framework | Para trabajar con el contenedor de inversión de control de Spring | Versión 6.0.6 |
| Swagger Annotations Jakarta | Para documentar las API RESTful en la aplicación | Versión 2.2.8 |
| Oracle JDBC Driver | Para conectarse a bases de datos Oracle | Versión 12.2.0.1 |
| MapStruct | Para mapear objetos Java | Versión 1.5.3.Final |
| Jackson Core | Para mapear objetos a un JSON o viceversa  | Versión 2.14.2 |
| Jacoco | Para manejar la cobertura en  pruebas y generar reporte  | Versión 0.8.10 |

##Cómo usarlo

Para poder clonar y ejecutar con éxito el microservicio debe tener instalado en su equipo [Git](https://git-scm.com) y [Java  17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) 

Para clonar el repositorio siga los siguientes pasos:

```bash
# Ejecute una consola
$ Teniendo instalado git puede proceder a abrir el bash de git

# Clonar repositorio
$ git clone  https://KonexInnovation@dev.azure.com/KonexInnovation/LOGII/_git/pagatodo-backend-financiera-contable

# Acceda al repositorio una vez clonado
$ cd pagatodo-backend-financiera-contable

# Cambie de rama si es necesario
$ git checkout develop

# Importe el proyecto al IDE: 
#  IDE Spring Tool Suite 4 
$ File > Open Projects from File System > Seleccione el proyecto > Seleccione únicamente la carpeta que contiene el proyecto maven > Finish
#  IDE Intellij
$ File > Open > Seleccione el proyecto > Seleccione únicamente la carpeta que contiene el proyecto maven > OK

```

> **Nota:**
> Una vez finalizado este proceso debera tener el microservicio en su workspace y podra iniciar la ejecucion del proyecto, si el proyecto marca como error algunas clases, verifique que  [Lombok](https://projectlombok.org/download) haya sido instalado en su IDE .

## Soporte

El soporte para este proyecto es proporcionado por Konnex Innovation. Puede contactarnos en soporte@konnex.com
<br>
<a href="https://konexinnovation.com/" target="_blank">
  <img src="https://dev.azure.com/KonexInnovation/ad87ca27-ff98-4e60-8824-a732701c031c/_apis/git/repositories/a875b1c2-5fbf-4745-9cc2-6006d8a6624e/items?path=/recursos/imagenes/logos/logo-konex-innovation.png&versionDescriptor%5BversionOptions%5D=0&versionDescriptor%5BversionType%5D=0&versionDescriptor%5Bversion%5D=main&resolveLfs=true&%24format=octetStream&api-version=5.0" 
    alt="Logo paga todo para todos" width="200px" style="margin-top:10px;">
</a>

## Colaboradores

<br>
  <p>Agradecemos a las siguientes personas por su contribución a este proyecto:</p>
  <div >
  <table >
  <tr >
    <td style="text-align: center;">
      <a target="_blank" href="esteban.zuluaga@konexinnovation.com.co"><img src="https://dev.azure.com/KonexInnovation/_api/_common/identityImage?id=48ab77fc-f90d-6540-a48b-682635161cda&size=2&t=0&1682540979374" width="80px;" alt="Foto de perfil de Jesus Esteban Zuluaga"/><br/><p><span>Jesus Esteban Zuluaga</span></p></a>
    </td>
    <td style="text-align: center;">
       <a target="_blank" href="nestor.cuchuyrume@konexinnovation.com.co"><img src="https://dev.azure.com/KonexInnovation/_api/_common/identityImage?id=0d13d180-efa2-6f11-9be0-4fe5eebe60da&size=2&t=00000000-0000-0000-6021-2a8ec319db08&1682540660858" width="100px;" height="90px"  alt="Avatar de Nesto Fico Cuchuyrume"/><br/><p><span>Nesto Fico Cuchuyrume </span></p></a>
    </td>
  </tr>
  </table>

## Versiones

  [v0.0.1]() Versión inicial del proyecto con las funcionalidades primordiales sobre el registro de la venta de baloto.


## Servicios

[Api Swagger]()

[Flujos del servicio]()