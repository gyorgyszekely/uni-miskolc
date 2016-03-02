/* DATABASE PRECONDITIONS */

CREATE TABLESPACE tbs_hyd DATAFILE 'tbs_hyd.dat' SIZE 1M AUTOEXTEND ON NEXT 500K MAXSIZE 200M;
CREATE USER hydrouser IDENTIFIED BY password DEFAULT TABLESPACE tbs_hyd;
GRANT ALL PRIVILEGES TO hydrouser;

/* MAVEN SETUP */

mvn install:install-file -Dfile=C:\Users\gszekely\Downloads\ojdbc7.jar -DgroupId=com.oracle -DartifactId=ojdbc7 -Dversion=12.1.0.2 -Dpackaging=jar

