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
 
 
 
 //POM per crear MINIMITZATS
 
 
 <cssSourceDir>css</cssSourceDir>
					<cssSourceFiles>
						<cssSourceFile>demo_table.css</cssSourceFile>
						<cssSourceFile>components.css</cssSourceFile>
					</cssSourceFiles>
					<cssFinalFile>tables_components.css</cssFinalFile>
					
					<cssSourceDir>css</cssSourceDir>
					<cssSourceFiles>
						<cssSourceFile>loadCalendar.css</cssSourceFile>
						<cssSourceFile>participadasCalendar.css</cssSourceFile>
						<cssSourceFile>demo_table.css</cssSourceFile>
						<cssSourceFile>components.css</cssSourceFile>
						<cssSourceFile>ext-all.css</cssSourceFile>
						<cssSourceFile>calendar-blau.css</cssSourceFile>
					</cssSourceFiles>
					<cssFinalFile>tbl_comp_cal_ext.css</cssFinalFile>
					
						<cssSourceDir>css</cssSourceDir>
					<cssSourceFiles>
						<cssSourceFile>loadCalendar.css</cssSourceFile>
						<cssSourceFile>participadasCalendar.css</cssSourceFile>
						<cssSourceFile>demo_table.css</cssSourceFile>
						<cssSourceFile>components.css</cssSourceFile>						
						<cssSourceFile>calendar-blau.css</cssSourceFile>
					</cssSourceFiles>
					<cssFinalFile>tbl_comp_cal.css</cssFinalFile>
					
					<cssSourceDir>css</cssSourceDir>
					<cssSourceFiles>
						<cssSourceFile>coin-slider-styles.css</cssSourceFile>						
					</cssSourceFiles>
					<cssFinalFile>coin-slider-stylesMim.css</cssFinalFile>
					
					
					<jsSourceDir>js/jquery</jsSourceDir>
					<jsSourceFiles>
						<jsSourceFile>jquery.js</jsSourceFile>
						<jsSourceFile>jquery.ui.core.js</jsSourceFile>
						<jsSourceFile>jquery.ui.widget.js</jsSourceFile>
						<jsSourceFile>jquery.ui.mouse.js</jsSourceFile>
						<jsSourceFile>jquery.ui.position.js</jsSourceFile>
						<jsSourceFile>jquery.ui.draggable.js</jsSourceFile>
						<jsSourceFile>jquery.ui.droppable.js</jsSourceFile>
						<jsSourceFile>jquery.ui.resizable.js</jsSourceFile>
						<jsSourceFile>jquery.effects.core.js</jsSourceFile>
						<jsSourceFile>jquery.bgiframe-2.1.1.js</jsSourceFile>
						<jsSourceFile>jquery-ui.js</jsSourceFile>
					</jsSourceFiles>
					<jsFinalFile>jsQuery.js</jsFinalFile>
					
					<jsSourceDir>js/jquery</jsSourceDir>
					<jsSourceFiles>
						<jsSourceFile>jquery.js</jsSourceFile>						
					</jsSourceFiles>
					<jsFinalFile>jsQueryAlone.js</jsFinalFile>

					<jsSourceDir>js/address</jsSourceDir>
					<jsSourceFiles>
						<jsSourceFile>addressValidationForm.js</jsSourceFile>
						<jsSourceFile>autocompleteTown.js</jsSourceFile>
					</jsSourceFiles>
					<jsFinalFile>addressFunctions.js</jsFinalFile>

					<jsSourceDir>js/calendari</jsSourceDir>
					<jsSourceFiles>
						<jsSourceFile>calendar.js</jsSourceFile>
						<jsSourceFile>calendar-cat.js</jsSourceFile>
						<jsSourceFile>calendar-idioma.js</jsSourceFile>
						<jsSourceFile>calendar-setup.js</jsSourceFile>
					</jsSourceFiles>
					<jsFinalFile>calendarInput.js</jsFinalFile>

					<jsSourceDir>js</jsSourceDir>
					<jsSourceFiles>
						<jsSourceFile>loadCalendar.js</jsSourceFile>
					</jsSourceFiles>
					<jsFinalFile>calendarPop.js</jsFinalFile>

					<jsSourceDir>js/jquery</jsSourceDir>
					<jsSourceFiles>
						<jsSourceFile>jquery.js</jsSourceFile>
						<jsSourceFile>jquery.ui.core.js</jsSourceFile>
					</jsSourceFiles>
					<jsFinalFile>jsQueryBasic.js</jsFinalFile>

					<jsSourceDir>pages/admin/restaurants/begudes</jsSourceDir>
					<jsSourceFiles>
						<jsSourceFile>jsbegudes.js</jsSourceFile>						
					</jsSourceFiles>
					<jsFinalFile>jsbegudes.js</jsFinalFile>
					
					<jsSourceDir>pages/admin/restaurants/configMoters</jsSourceDir>
					<jsSourceFiles>
						<jsSourceFile>jsconfig.js</jsSourceFile>						
					</jsSourceFiles>
					<jsFinalFile>jsconfigMoters.js</jsFinalFile>
					
					<jsSourceDir>pages/admin/restaurants/configRestaurant</jsSourceDir>
					<jsSourceFiles>
						<jsSourceFile>jsconfig.js</jsSourceFile>						
					</jsSourceFiles>
					<jsFinalFile>jsconfigRestaurants.js</jsFinalFile>
					
					<jsSourceDir>pages/admin/restaurants/consulta</jsSourceDir>
					<jsSourceFiles>
						<jsSourceFile>jsrestaurants.js</jsSourceFile>						
					</jsSourceFiles>
					<jsFinalFile>jsconsultaRestaurants.js</jsFinalFile>
					
					<jsSourceDir>pages/admin/restaurants/newplat</jsSourceDir>
					<jsSourceFiles>
						<jsSourceFile>jsplats.js</jsSourceFile>						
					</jsSourceFiles>
					<jsFinalFile>jsplats.js</jsFinalFile>
					
					<jsSourceDir>pages/admin/restaurants/newRestaurant</jsSourceDir>
					<jsSourceFiles>
						<jsSourceFile>jsnewrestaurant.js</jsSourceFile>						
					</jsSourceFiles>
					<jsFinalFile>jsnewrestaurant.js</jsFinalFile>
					
					<jsSourceDir>pages/admin/restaurants/promocions</jsSourceDir>
					<jsSourceFiles>
						<jsSourceFile>jspromocio.js</jsSourceFile>						
					</jsSourceFiles>
					<jsFinalFile>jspromocio.js</jsFinalFile>
					
					<jsSourceDir>pages/admin/usuaris</jsSourceDir>
					<jsSourceFiles>
						<jsSourceFile>jsusuaris.js</jsSourceFile>						
					</jsSourceFiles>
					<jsFinalFile>jsusuaris.js</jsFinalFile>
					
					<jsSourceDir>pages/admin/usuaris/newsletter</jsSourceDir>
					<jsSourceFiles>
						<jsSourceFile>jsnewsletter.js</jsSourceFile>						
					</jsSourceFiles>
					<jsFinalFile>jsnewsletter.js</jsFinalFile>
					
					<jsSourceDir>js/ext</jsSourceDir>
					<jsSourceFiles>
						<jsSourceFile>ext-base.js</jsSourceFile>						
						<jsSourceFile>ext-all-debug.js</jsSourceFile>
					</jsSourceFiles>
					<jsFinalFile>jsext.js</jsFinalFile>
					
//Generacio del certificat
//Passos en el jdk

C:\Program Files\Java\jdk1.7.0_02\bin>keytool -genkey -alias tomcat -keyalg RSA
Enter keystore password:
Re-enter new password:
What is your first and last name?
  [Unknown]:  kim orra
What is the name of your organizational unit?
  [Unknown]:  online
What is the name of your organization?
  [Unknown]:  online
What is the name of your City or Locality?
  [Unknown]:  online
What is the name of your State or Province?
  [Unknown]:  online
What is the two-letter country code for this unit?
  [Unknown]:  es
Is CN=kim orra, OU=online, O=online, L=online, ST=online, C=es correct?
  [no]:  si
What is your first and last name?
  [kim orra]:
What is the name of your organizational unit?
  [online]:
What is the name of your organization?
  [online]:
What is the name of your City or Locality?
  [online]:
What is the name of your State or Province?
  [online]:
What is the two-letter country code for this unit?
  [es]:
Is CN=kim orra, OU=online, O=online, L=online, ST=online, C=es correct?
  [no]:  yes

Enter key password for <tomcat>
        (RETURN if same as keystore password):
Re-enter new password:

C:\Program Files\Java\jdk1.7.0_02\bin>


//Canvis en el server.xml del tomcat

 <Connector port="8443" protocol="org.apache.coyote.http11.Http11NioProtocol" SSLEnabled="true"
               maxThreads="150" scheme="https" secure="true"
               clientAuth="false" sslProtocol="TLS"
               keystoreFile="C:\Users\CrisKim\.keystore"
               keystorePass="changeit" />

ç<Connector port="7070" protocol="HTTP/1.1"
               maxHttpHeaderSize="8192"
               minSpareThreads="25"
               maxSpareThreads="75"
               enableLookups="false"
               redirectPort="8443"
               acceptCount="100"
               connectionTimeout="20000"
               disableUploadTimeout="true"
               compression="on"
               compressionMinSize="2048"
               noCompressionUserAgents="gozilla, traviata"
               compressableMimeType="text/html,text/xml,text/css,text/javascript"/>

