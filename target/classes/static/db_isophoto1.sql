-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler  version: 0.9.1-beta
-- PostgreSQL version: 10.0
-- Project Site: pgmodeler.com.br
-- Model Author: ---


 Database creation must be done outside an multicommand file.
 These commands were put in this file only for convenience.
 -- object: db_isophoto | type: DATABASE --
 -- DROP DATABASE IF EXISTS db_isophoto;
 CREATE DATABASE db_isophoto
 	OWNER = postgres
 ;
 -- ddl-end --
 
 -- Appended SQL commands --
 -- Database generated with pgModeler (PostgreSQL Database Modeler).
 -- pgModeler  version: 0.9.1-beta
 -- PostgreSQL version: 10.0
 -- Project Site: pgmodeler.com.br
 -- Model Author: ---
 
 
 -- Database creation must be done outside an multicommand file.
 -- These commands were put in this file only for convenience.
- -- object: db_isophoto | type: DATABASE --
 DROP DATABASE IF EXISTS db_isophoto;
CREATE DATABASE db_isophoto
	OWNER = postgres
;
 --ddl-end 
-- -- 
-- 
-- -- object: db_isophoto | type: SCHEMA --
DROP SCHEMA IF EXISTS db_isophoto CASCADE;
 CREATE SCHEMA db_isophoto;
-- ddl-end --
ALTER SCHEMA db_isophoto OWNER TO postgres;
--ddl-end --
-- 
-SET search_path TO pg_catalog,public,db_isophoto;
 --ddl-end --
-- 
object: public.photo | type: TABLE --
DROP TABLE IF EXISTS public.photo CASCADE;
CREATE TABLE public.photo(
 	id smallint NOT NULL,
 	titre varchar,
 	image varchar,
 	description varchar,
 	prix float,
 	"aVendre" boolean,
 	id_categorie smallint,
 	id_exif smallint,
 	id_photographe smallint,
	CONSTRAINT photo_pk PRIMARY KEY (id)
 
 );
 --ddl-end --
 ALTER TABLE public.photo OWNER TO postgres;
-- ddl-end --
-- 
 object: public.exif | type: TABLE --
 DROP TABLE IF EXISTS public.exif CASCADE;
 CREATE TABLE public.exif(
 	id smallint NOT NULL,
 	constructeur varchar,
 	modele smallint,
 	"vitesseObturation" float,
 	ouverture varchar,
 	"dateHeure" timestamp,
 	flash boolean,
 	objectif varchar,
 	CONSTRAINT exif_pk PRIMARY KEY (id)
- 
 );
-- ddl-end --
 ALTER TABLE public.exif OWNER TO postgres;
 --ddl-end --
-- 
-- object: public.categorie | type: TABLE --
 DROP TABLE IF EXISTS public.categorie CASCADE;
 CREATE TABLE public.categorie(
	id smallint NOT NULL,
 	nom varchar,
 	CONSTRAINT categorie_pk PRIMARY KEY (id)
-- 
 );
 --ddl-end --
 ALTER TABLE public.categorie OWNER TO postgres;
-- ddl-end --
-- 
-- object: public.commande | type: TABLE --
 DROP TABLE IF EXISTS public.commande CASCADE;
CREATE TABLE public.commande(
	id smallint NOT NULL,
	"dateCommande" timestamp,
	"fraisDePort" float,
	tva float,
	ttc float,
	id_photographe smallint,
	"quantité" smallint,
	CONSTRAINT commande_pk PRIMARY KEY (id)

- );
- -- ddl-end --
- ALTER TABLE public.commande OWNER TO postgres;
- -- ddl-end --
- 
- -- object: public.utilisateur | type: TABLE --
- -- DROP TABLE IF EXISTS public.utilisateur CASCADE;
- CREATE TABLE public.utilisateur(
- 	id smallint NOT NULL,
- 	id_authorities smallint,
- 	CONSTRAINT utilisateur_pk PRIMARY KEY (id)
- 
- );
- -- ddl-end --
- ALTER TABLE public.utilisateur OWNER TO postgres;
- -- ddl-end --
- 
- -- object: public.adresse | type: TABLE --
- -- DROP TABLE IF EXISTS public.adresse CASCADE;
- CREATE TABLE public.adresse(
- 	id smallint NOT NULL,
- 	"numVoie" smallint,
- 	"nomVoie" varchar,
- 	"codePostal" int4,
- 	ville varchar,
- 	pays varchar,
- 	CONSTRAINT adresse_pk PRIMARY KEY (id)
- 
- );
- -- ddl-end --
- ALTER TABLE public.adresse OWNER TO postgres;
- -- ddl-end --
- 
- -- object: categorie_fk | type: CONSTRAINT --
- -- ALTER TABLE public.photo DROP CONSTRAINT IF EXISTS categorie_fk CASCADE;
- ALTER TABLE public.photo ADD CONSTRAINT categorie_fk FOREIGN KEY (id_categorie)
- REFERENCES public.categorie (id) MATCH FULL
- ON DELETE SET NULL ON UPDATE CASCADE;
- -- ddl-end --
- 
- -- object: photo_uq | type: CONSTRAINT --
- -- ALTER TABLE public.photo DROP CONSTRAINT IF EXISTS photo_uq CASCADE;
- ALTER TABLE public.photo ADD CONSTRAINT photo_uq UNIQUE (id_categorie);
- -- ddl-end --
- 
- -- object: exif_fk | type: CONSTRAINT --
- -- ALTER TABLE public.photo DROP CONSTRAINT IF EXISTS exif_fk CASCADE;
- ALTER TABLE public.photo ADD CONSTRAINT exif_fk FOREIGN KEY (id_exif)
- REFERENCES public.exif (id) MATCH FULL
- ON DELETE SET NULL ON UPDATE CASCADE;
- -- ddl-end --
- 
- -- object: photo_uq1 | type: CONSTRAINT --
- -- ALTER TABLE public.photo DROP CONSTRAINT IF EXISTS photo_uq1 CASCADE;
- ALTER TABLE public.photo ADD CONSTRAINT photo_uq1 UNIQUE (id_exif);
- -- ddl-end --
- 
- -- object: public.many_photo_has_many_commande | type: TABLE --
- -- DROP TABLE IF EXISTS public.many_photo_has_many_commande CASCADE;
- CREATE TABLE public.many_photo_has_many_commande(
- 	id_photo smallint NOT NULL,
- 	id_commande smallint NOT NULL,
- 	CONSTRAINT many_photo_has_many_commande_pk PRIMARY KEY (id_photo,id_commande)
- 
- );
- -- ddl-end --
- 
- -- object: photo_fk | type: CONSTRAINT --
- -- ALTER TABLE public.many_photo_has_many_commande DROP CONSTRAINT IF EXISTS photo_fk CASCADE;
- ALTER TABLE public.many_photo_has_many_commande ADD CONSTRAINT photo_fk FOREIGN KEY (id_photo)
- REFERENCES public.photo (id) MATCH FULL
- ON DELETE CASCADE ON UPDATE CASCADE;
- -- ddl-end --
- 
- -- object: commande_fk | type: CONSTRAINT --
- -- ALTER TABLE public.many_photo_has_many_commande DROP CONSTRAINT IF EXISTS commande_fk CASCADE;
- ALTER TABLE public.many_photo_has_many_commande ADD CONSTRAINT commande_fk FOREIGN KEY (id_commande)
- REFERENCES public.commande (id) MATCH FULL
- ON DELETE CASCADE ON UPDATE CASCADE;
- -- ddl-end --
- 
- -- object: public.timeline | type: TABLE --
- -- DROP TABLE IF EXISTS public.timeline CASCADE;
- CREATE TABLE public.timeline(
- 	id smallint NOT NULL,
- 	nom varchar,
- 	"dateCreation" date,
- 	"dateUpdate" date,
- 	id_photographe smallint,
- 	CONSTRAINT timeline_pk PRIMARY KEY (id)
- 
- );
- -- ddl-end --
- ALTER TABLE public.timeline OWNER TO postgres;
- -- ddl-end --
- 
- -- object: public.many_timeline_has_many_photo | type: TABLE --
- -- DROP TABLE IF EXISTS public.many_timeline_has_many_photo CASCADE;
- CREATE TABLE public.many_timeline_has_many_photo(
- 	id_timeline smallint NOT NULL,
- 	id_photo smallint NOT NULL,
- 	CONSTRAINT many_timeline_has_many_photo_pk PRIMARY KEY (id_timeline,id_photo)
- 
- );
- -- ddl-end --
- 
- -- object: timeline_fk | type: CONSTRAINT --
- -- ALTER TABLE public.many_timeline_has_many_photo DROP CONSTRAINT IF EXISTS timeline_fk CASCADE;
- ALTER TABLE public.many_timeline_has_many_photo ADD CONSTRAINT timeline_fk FOREIGN KEY (id_timeline)
- REFERENCES public.timeline (id) MATCH FULL
- ON DELETE CASCADE ON UPDATE CASCADE;
- -- ddl-end --
- 
- -- object: photo_fk | type: CONSTRAINT --
- -- ALTER TABLE public.many_timeline_has_many_photo DROP CONSTRAINT IF EXISTS photo_fk CASCADE;
- ALTER TABLE public.many_timeline_has_many_photo ADD CONSTRAINT photo_fk FOREIGN KEY (id_photo)
- REFERENCES public.photo (id) MATCH FULL
- ON DELETE CASCADE ON UPDATE CASCADE;
- -- ddl-end --
- 
- -- object: public.authorities | type: TABLE --
- -- DROP TABLE IF EXISTS public.authorities CASCADE;
- CREATE TABLE public.authorities(
- 	id smallint NOT NULL,
- 	role varchar,
- 	CONSTRAINT authorities_pk PRIMARY KEY (id)
- 
- );
- -- ddl-end --
- ALTER TABLE public.authorities OWNER TO postgres;
- -- ddl-end --
- 
- -- object: authorities_fk | type: CONSTRAINT --
- -- ALTER TABLE public.utilisateur DROP CONSTRAINT IF EXISTS authorities_fk CASCADE;
- ALTER TABLE public.utilisateur ADD CONSTRAINT authorities_fk FOREIGN KEY (id_authorities)
- REFERENCES public.authorities (id) MATCH FULL
- ON DELETE SET NULL ON UPDATE CASCADE;
- -- ddl-end --
- 
- -- object: public.photographe | type: TABLE --
- -- DROP TABLE IF EXISTS public.photographe CASCADE;
- CREATE TABLE public.photographe(
- 	id smallint NOT NULL,
- 	nom varchar,
- 	prenom varchar,
- 	pseudo varchar,
- 	email varchar,
- 	password varchar,
- -- 	id_authorities smallint,
- 	CONSTRAINT photographe_pk PRIMARY KEY (id)
- 
- ) INHERITS(public.utilisateur)
- ;
- -- ddl-end --
- ALTER TABLE public.photographe OWNER TO postgres;
- -- ddl-end --
- 
- -- object: public.many_photographe_has_many_adresse | type: TABLE --
- -- DROP TABLE IF EXISTS public.many_photographe_has_many_adresse CASCADE;
- CREATE TABLE public.many_photographe_has_many_adresse(
- 	id_photographe smallint NOT NULL,
- 	id_adresse smallint NOT NULL,
- 	CONSTRAINT many_photographe_has_many_adresse_pk PRIMARY KEY (id_photographe,id_adresse)
- 
- );
- -- ddl-end --
- 
- -- object: photographe_fk | type: CONSTRAINT --
- -- ALTER TABLE public.many_photographe_has_many_adresse DROP CONSTRAINT IF EXISTS photographe_fk CASCADE;
- ALTER TABLE public.many_photographe_has_many_adresse ADD CONSTRAINT photographe_fk FOREIGN KEY (id_photographe)
- REFERENCES public.photographe (id) MATCH FULL
- ON DELETE CASCADE ON UPDATE CASCADE;
- -- ddl-end --
- 
- -- object: adresse_fk | type: CONSTRAINT --
- -- ALTER TABLE public.many_photographe_has_many_adresse DROP CONSTRAINT IF EXISTS adresse_fk CASCADE;
- ALTER TABLE public.many_photographe_has_many_adresse ADD CONSTRAINT adresse_fk FOREIGN KEY (id_adresse)
- REFERENCES public.adresse (id) MATCH FULL
- ON DELETE CASCADE ON UPDATE CASCADE;
- -- ddl-end --
- 
- -- object: photographe_fk | type: CONSTRAINT --
- -- ALTER TABLE public.commande DROP CONSTRAINT IF EXISTS photographe_fk CASCADE;
- ALTER TABLE public.commande ADD CONSTRAINT photographe_fk FOREIGN KEY (id_photographe)
- REFERENCES public.photographe (id) MATCH FULL
- ON DELETE SET NULL ON UPDATE CASCADE;
- -- ddl-end --
- 
- -- object: photographe_fk | type: CONSTRAINT --
- -- ALTER TABLE public.timeline DROP CONSTRAINT IF EXISTS photographe_fk CASCADE;
- ALTER TABLE public.timeline ADD CONSTRAINT photographe_fk FOREIGN KEY (id_photographe)
- REFERENCES public.photographe (id) MATCH FULL
- ON DELETE SET NULL ON UPDATE CASCADE;
- -- ddl-end --
- 
- -- object: photographe_fk | type: CONSTRAINT --
- -- ALTER TABLE public.photo DROP CONSTRAINT IF EXISTS photographe_fk CASCADE;
- ALTER TABLE public.photo ADD CONSTRAINT photographe_fk FOREIGN KEY (id_photographe)
- REFERENCES public.photographe (id) MATCH FULL
- ON DELETE SET NULL ON UPDATE CASCADE;
- -- ddl-end --
- 
- 
- -- ddl-end --
- 

- object: db_isophoto | type: SCHEMA --
-- DROP SCHEMA IF EXISTS db_isophoto CASCADE;
CREATE SCHEMA db_isophoto;
-- ddl-end --
ALTER SCHEMA db_isophoto OWNER TO postgres;
-- ddl-end --

SET search_path TO pg_catalog,public,db_isophoto;
-- ddl-end --

-- object: db_isophoto.photo | type: TABLE --
-- DROP TABLE IF EXISTS db_isophoto.photo CASCADE;
CREATE TABLE db_isophoto.photo(
	id smallint NOT NULL,
	titre varchar,
	image varchar,
	description varchar,
	prix real,
	"aVendre" boolean,
	id_categorie smallint,
	id_exif smallint,
	id_photographe smallint,
	CONSTRAINT photo_pk PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE db_isophoto.photo OWNER TO postgres;
-- ddl-end --

-- object: db_isophoto.exif | type: TABLE --
-- DROP TABLE IF EXISTS db_isophoto.exif CASCADE;
CREATE TABLE db_isophoto.exif(
	id smallint NOT NULL,
	constructeur varchar,
	modele smallint,
	"vitesseObturation" real,
	ouverture varchar,
	"dateHeure" timestamp,
	flash boolean,
	objectif varchar,
	CONSTRAINT exif_pk PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE db_isophoto.exif OWNER TO postgres;
-- ddl-end --

-- object: db_isophoto.categorie | type: TABLE --
-- DROP TABLE IF EXISTS db_isophoto.categorie CASCADE;
CREATE TABLE db_isophoto.categorie(
	id smallint NOT NULL,
	nom varchar,
	CONSTRAINT categorie_pk PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE db_isophoto.categorie OWNER TO postgres;
-- ddl-end --

-- object: db_isophoto.commande | type: TABLE --
-- DROP TABLE IF EXISTS db_isophoto.commande CASCADE;
CREATE TABLE db_isophoto.commande(
	id smallint NOT NULL,
	"dateCommande" timestamp,
	"fraisDePort" real,
	tva real,
	ttc real,
	id_photographe smallint,
	"quantité" smallint,
	CONSTRAINT commande_pk PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE db_isophoto.commande OWNER TO postgres;
-- ddl-end --

-- object: db_isophoto.utilisateur | type: TABLE --
-- DROP TABLE IF EXISTS db_isophoto.utilisateur CASCADE;
CREATE TABLE db_isophoto.utilisateur(
	id smallint NOT NULL,
	id_authorities smallint,
	CONSTRAINT utilisateur_pk PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE db_isophoto.utilisateur OWNER TO postgres;
-- ddl-end --

-- object: db_isophoto.adresse | type: TABLE --
-- DROP TABLE IF EXISTS db_isophoto.adresse CASCADE;
CREATE TABLE db_isophoto.adresse(
	id smallint NOT NULL,
	"numVoie" smallint,
	"nomVoie" varchar,
	"codePostal" int4,
	ville varchar,
	pays varchar,
	CONSTRAINT adresse_pk PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE db_isophoto.adresse OWNER TO postgres;
-- ddl-end --

-- object: categorie_fk | type: CONSTRAINT --
-- ALTER TABLE db_isophoto.photo DROP CONSTRAINT IF EXISTS categorie_fk CASCADE;
ALTER TABLE db_isophoto.photo ADD CONSTRAINT categorie_fk FOREIGN KEY (id_categorie)
REFERENCES db_isophoto.categorie (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: photo_uq | type: CONSTRAINT --
-- ALTER TABLE db_isophoto.photo DROP CONSTRAINT IF EXISTS photo_uq CASCADE;
ALTER TABLE db_isophoto.photo ADD CONSTRAINT photo_uq UNIQUE (id_categorie);
-- ddl-end --

-- object: exif_fk | type: CONSTRAINT --
-- ALTER TABLE db_isophoto.photo DROP CONSTRAINT IF EXISTS exif_fk CASCADE;
ALTER TABLE db_isophoto.photo ADD CONSTRAINT exif_fk FOREIGN KEY (id_exif)
REFERENCES db_isophoto.exif (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: photo_uq1 | type: CONSTRAINT --
-- ALTER TABLE db_isophoto.photo DROP CONSTRAINT IF EXISTS photo_uq1 CASCADE;
ALTER TABLE db_isophoto.photo ADD CONSTRAINT photo_uq1 UNIQUE (id_exif);
-- ddl-end --

-- object: db_isophoto.many_photo_has_many_commande | type: TABLE --
-- DROP TABLE IF EXISTS db_isophoto.many_photo_has_many_commande CASCADE;
CREATE TABLE db_isophoto.many_photo_has_many_commande(
	id_photo smallint NOT NULL,
	id_commande smallint NOT NULL,
	CONSTRAINT many_photo_has_many_commande_pk PRIMARY KEY (id_photo,id_commande)

);
-- ddl-end --

-- object: photo_fk | type: CONSTRAINT --
-- ALTER TABLE db_isophoto.many_photo_has_many_commande DROP CONSTRAINT IF EXISTS photo_fk CASCADE;
ALTER TABLE db_isophoto.many_photo_has_many_commande ADD CONSTRAINT photo_fk FOREIGN KEY (id_photo)
REFERENCES db_isophoto.photo (id) MATCH FULL
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: commande_fk | type: CONSTRAINT --
-- ALTER TABLE db_isophoto.many_photo_has_many_commande DROP CONSTRAINT IF EXISTS commande_fk CASCADE;
ALTER TABLE db_isophoto.many_photo_has_many_commande ADD CONSTRAINT commande_fk FOREIGN KEY (id_commande)
REFERENCES db_isophoto.commande (id) MATCH FULL
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: db_isophoto.timeline | type: TABLE --
-- DROP TABLE IF EXISTS db_isophoto.timeline CASCADE;
CREATE TABLE db_isophoto.timeline(
	id smallint NOT NULL,
	nom varchar,
	"dateCreation" date,
	"dateUpdate" date,
	id_photographe smallint,
	CONSTRAINT timeline_pk PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE db_isophoto.timeline OWNER TO postgres;
-- ddl-end --

-- object: db_isophoto.many_timeline_has_many_photo | type: TABLE --
-- DROP TABLE IF EXISTS db_isophoto.many_timeline_has_many_photo CASCADE;
CREATE TABLE db_isophoto.many_timeline_has_many_photo(
	id_timeline smallint NOT NULL,
	id_photo smallint NOT NULL,
	CONSTRAINT many_timeline_has_many_photo_pk PRIMARY KEY (id_timeline,id_photo)

);
-- ddl-end --

-- object: timeline_fk | type: CONSTRAINT --
-- ALTER TABLE db_isophoto.many_timeline_has_many_photo DROP CONSTRAINT IF EXISTS timeline_fk CASCADE;
ALTER TABLE db_isophoto.many_timeline_has_many_photo ADD CONSTRAINT timeline_fk FOREIGN KEY (id_timeline)
REFERENCES db_isophoto.timeline (id) MATCH FULL
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: photo_fk | type: CONSTRAINT --
-- ALTER TABLE db_isophoto.many_timeline_has_many_photo DROP CONSTRAINT IF EXISTS photo_fk CASCADE;
ALTER TABLE db_isophoto.many_timeline_has_many_photo ADD CONSTRAINT photo_fk FOREIGN KEY (id_photo)
REFERENCES db_isophoto.photo (id) MATCH FULL
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: db_isophoto.authorities | type: TABLE --
-- DROP TABLE IF EXISTS db_isophoto.authorities CASCADE;
CREATE TABLE db_isophoto.authorities(
	id smallint NOT NULL,
	role varchar,
	CONSTRAINT authorities_pk PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE db_isophoto.authorities OWNER TO postgres;
-- ddl-end --

-- object: authorities_fk | type: CONSTRAINT --
-- ALTER TABLE db_isophoto.utilisateur DROP CONSTRAINT IF EXISTS authorities_fk CASCADE;
ALTER TABLE db_isophoto.utilisateur ADD CONSTRAINT authorities_fk FOREIGN KEY (id_authorities)
REFERENCES db_isophoto.authorities (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: db_isophoto.photographe | type: TABLE --
-- DROP TABLE IF EXISTS db_isophoto.photographe CASCADE;
CREATE TABLE db_isophoto.photographe(
	id smallint NOT NULL,
	nom varchar,
	prenom varchar,
	pseudo varchar,
	email varchar,
	password varchar,
-- 	id_authorities smallint,
	CONSTRAINT photographe_pk PRIMARY KEY (id)

) INHERITS(db_isophoto.utilisateur)
;
-- ddl-end --
ALTER TABLE db_isophoto.photographe OWNER TO postgres;
-- ddl-end --

-- object: db_isophoto.many_photographe_has_many_adresse | type: TABLE --
-- DROP TABLE IF EXISTS db_isophoto.many_photographe_has_many_adresse CASCADE;
CREATE TABLE db_isophoto.many_photographe_has_many_adresse(
	id_photographe smallint NOT NULL,
	id_adresse smallint NOT NULL,
	CONSTRAINT many_photographe_has_many_adresse_pk PRIMARY KEY (id_photographe,id_adresse)

);
-- ddl-end --

-- object: photographe_fk | type: CONSTRAINT --
-- ALTER TABLE db_isophoto.many_photographe_has_many_adresse DROP CONSTRAINT IF EXISTS photographe_fk CASCADE;
ALTER TABLE db_isophoto.many_photographe_has_many_adresse ADD CONSTRAINT photographe_fk FOREIGN KEY (id_photographe)
REFERENCES db_isophoto.photographe (id) MATCH FULL
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: adresse_fk | type: CONSTRAINT --
-- ALTER TABLE db_isophoto.many_photographe_has_many_adresse DROP CONSTRAINT IF EXISTS adresse_fk CASCADE;
ALTER TABLE db_isophoto.many_photographe_has_many_adresse ADD CONSTRAINT adresse_fk FOREIGN KEY (id_adresse)
REFERENCES db_isophoto.adresse (id) MATCH FULL
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: photographe_fk | type: CONSTRAINT --
-- ALTER TABLE db_isophoto.commande DROP CONSTRAINT IF EXISTS photographe_fk CASCADE;
ALTER TABLE db_isophoto.commande ADD CONSTRAINT photographe_fk FOREIGN KEY (id_photographe)
REFERENCES db_isophoto.photographe (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: photographe_fk | type: CONSTRAINT --
-- ALTER TABLE db_isophoto.timeline DROP CONSTRAINT IF EXISTS photographe_fk CASCADE;
ALTER TABLE db_isophoto.timeline ADD CONSTRAINT photographe_fk FOREIGN KEY (id_photographe)
REFERENCES db_isophoto.photographe (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: photographe_fk | type: CONSTRAINT --
-- ALTER TABLE db_isophoto.photo DROP CONSTRAINT IF EXISTS photographe_fk CASCADE;
ALTER TABLE db_isophoto.photo ADD CONSTRAINT photographe_fk FOREIGN KEY (id_photographe)
REFERENCES db_isophoto.photographe (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --


