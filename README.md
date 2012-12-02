onlineBotRepository
===================

onlineBotRepository

1.-Install eclipse JUNO.

2.- Install Mysql community server 5.5.28 (tipical) user i pass = root.

3.- Install Maven 2.2.1 (Descomprimir i crear les variables d'entorn M2_HOME i M2, Posar ruta al directori bin a la variable PATH)

4.-Install jdk (Cal afegir la variable JAVA_HOME  a les variables d'entorn)

5.-POsar dos plugins a eclipse
	-m2e - http://download.eclipse.org/technology/m2e/releases
	-sublcise - http://subclipse.tigris.org/update_1.6.x

6.-Fer unc checkout del projecte desde la vista SVN i des la carpeta trunk
	-https://github.com/kimy82/onlineBotRepository.git (REPO)
	-kimy82,online82

7.-Canviar en el pom XML.
	-posar la ruta del jar tools a on tens el teu jdk.
	-Instalar manualment la llibreria hibernate->	 

//Comanda per instalar la llibreria que falta de mvn....
C:\Users\CrisKim>mvn install:install-file -DgroupId=hibernate -DartifactId=hiber
nate3 -Dversion=3.2.3.ga -Dfile=C:\Users\CrisKim\Desktop\hibernate3-3.2.3.ga.jar
 -Dpackaging=jar -DgeneratePom=true
 

8.-BBDD

mysql> create database online;
Query OK, 1 row affected (0.00 sec)

mysql> grant usage on *.* to root@localhost identified by 'root';
Query OK, 0 rows affected (0.03 sec)

mysql> grant all privileges on online.* to root@localhost ;
Query OK, 0 rows affected (0.03 sec)

9.-Executar les comandes de BBDD->Creations.sql

10.-Configurar JDK a l'eclipse.

11. run as... clean install jetty:run-exploded
 

