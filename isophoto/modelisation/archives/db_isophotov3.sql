-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler  version: 0.9.1-beta
-- PostgreSQL version: 10.0
-- Project Site: pgmodeler.com.br
-- Model Author: ---


-- Database creation must be done outside an multicommand file.
-- These commands were put in this file only for convenience.
-- -- object: db_isophoto | type: DATABASE --
-- -- DROP DATABASE IF EXISTS db_isophoto;
-- CREATE DATABASE db_isophoto
-- ;
-- -- ddl-end --
-- 

-- object: db_isophoto | type: SCHEMA --
-- DROP SCHEMA IF EXISTS db_isophoto CASCADE;
CREATE SCHEMA db_isophoto;
-- ddl-end --
ALTER SCHEMA db_isophoto OWNER TO postgres;
-- ddl-end --

SET search_path TO pg_catalog,public,db_isophoto;
-- ddl-end --

-- object: public.photo | type: TABLE --
-- DROP TABLE IF EXISTS public.photo CASCADE;
CREATE TABLE public.photo(
	id serial NOT NULL,
	titre varchar,
	image varchar,
	description varchar,
	id_photographe integer,
	id_categorie integer NOT NULL,
	CONSTRAINT photo_pk PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public.photo OWNER TO postgres;
-- ddl-end --

-- object: public.categorie | type: TABLE --
-- DROP TABLE IF EXISTS public.categorie CASCADE;
CREATE TABLE public.categorie(
	id serial NOT NULL,
	nom varchar,
	CONSTRAINT categorie_pk PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public.categorie OWNER TO postgres;
-- ddl-end --

-- object: public.don | type: TABLE --
-- DROP TABLE IF EXISTS public.don CASCADE;
CREATE TABLE public.don(
	id serial NOT NULL,
	"dateDon" timestamp,
	montant smallint,
	id_photographe integer,
	id_commentaire integer,
	CONSTRAINT commande_pk PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public.don OWNER TO postgres;
-- ddl-end --

-- object: public.utilisateur | type: TABLE --
-- DROP TABLE IF EXISTS public.utilisateur CASCADE;
CREATE TABLE public.utilisateur(
	id serial NOT NULL,
	id_authorities integer,
	CONSTRAINT utilisateur_pk PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public.utilisateur OWNER TO postgres;
-- ddl-end --

-- object: public.authorities | type: TABLE --
-- DROP TABLE IF EXISTS public.authorities CASCADE;
CREATE TABLE public.authorities(
	id serial NOT NULL,
	role varchar,
	CONSTRAINT authorities_pk PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public.authorities OWNER TO postgres;
-- ddl-end --

-- object: authorities_fk | type: CONSTRAINT --
-- ALTER TABLE public.utilisateur DROP CONSTRAINT IF EXISTS authorities_fk CASCADE;
ALTER TABLE public.utilisateur ADD CONSTRAINT authorities_fk FOREIGN KEY (id_authorities)
REFERENCES public.authorities (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: public.photographe | type: TABLE --
-- DROP TABLE IF EXISTS public.photographe CASCADE;
CREATE TABLE public.photographe(
	id serial NOT NULL,
	nom varchar,
	prenom varchar,
	pseudo varchar,
	email varchar,
	password varchar,
-- 	id_authorities integer,
	CONSTRAINT photographe_pk PRIMARY KEY (id)

) INHERITS(public.utilisateur)
;
-- ddl-end --
ALTER TABLE public.photographe OWNER TO postgres;
-- ddl-end --

-- object: photographe_fk | type: CONSTRAINT --
-- ALTER TABLE public.photo DROP CONSTRAINT IF EXISTS photographe_fk CASCADE;
ALTER TABLE public.photo ADD CONSTRAINT photographe_fk FOREIGN KEY (id_photographe)
REFERENCES public.photographe (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: photographe_fk | type: CONSTRAINT --
-- ALTER TABLE public.don DROP CONSTRAINT IF EXISTS photographe_fk CASCADE;
ALTER TABLE public.don ADD CONSTRAINT photographe_fk FOREIGN KEY (id_photographe)
REFERENCES public.photographe (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: public.many_don_has_many_photo | type: TABLE --
-- DROP TABLE IF EXISTS public.many_don_has_many_photo CASCADE;
CREATE TABLE public.many_don_has_many_photo(
	id_don integer NOT NULL,
	id_photo integer NOT NULL,
	CONSTRAINT many_don_has_many_photo_pk PRIMARY KEY (id_don,id_photo)

);
-- ddl-end --

-- object: don_fk | type: CONSTRAINT --
-- ALTER TABLE public.many_don_has_many_photo DROP CONSTRAINT IF EXISTS don_fk CASCADE;
ALTER TABLE public.many_don_has_many_photo ADD CONSTRAINT don_fk FOREIGN KEY (id_don)
REFERENCES public.don (id) MATCH FULL
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: photo_fk | type: CONSTRAINT --
-- ALTER TABLE public.many_don_has_many_photo DROP CONSTRAINT IF EXISTS photo_fk CASCADE;
ALTER TABLE public.many_don_has_many_photo ADD CONSTRAINT photo_fk FOREIGN KEY (id_photo)
REFERENCES public.photo (id) MATCH FULL
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: categorie_fk | type: CONSTRAINT --
-- ALTER TABLE public.photo DROP CONSTRAINT IF EXISTS categorie_fk CASCADE;
ALTER TABLE public.photo ADD CONSTRAINT categorie_fk FOREIGN KEY (id_categorie)
REFERENCES public.categorie (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: public.commentaire | type: TABLE --
-- DROP TABLE IF EXISTS public.commentaire CASCADE;
CREATE TABLE public.commentaire(
	id serial NOT NULL,
	commentaire varchar,
	date timestamp,
	CONSTRAINT commentaire_pk PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public.commentaire OWNER TO postgres;
-- ddl-end --

-- object: commentaire_fk | type: CONSTRAINT --
-- ALTER TABLE public.don DROP CONSTRAINT IF EXISTS commentaire_fk CASCADE;
ALTER TABLE public.don ADD CONSTRAINT commentaire_fk FOREIGN KEY (id_commentaire)
REFERENCES public.commentaire (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: don_uq | type: CONSTRAINT --
-- ALTER TABLE public.don DROP CONSTRAINT IF EXISTS don_uq CASCADE;
ALTER TABLE public.don ADD CONSTRAINT don_uq UNIQUE (id_commentaire);
-- ddl-end --


