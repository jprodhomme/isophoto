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
	CONSTRAINT photo_pk PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public.photo OWNER TO postgres;
-- ddl-end --

-- object: public.tags | type: TABLE --
-- DROP TABLE IF EXISTS public.tags CASCADE;
CREATE TABLE public.tags(
	id serial NOT NULL,
	tag varchar,
	CONSTRAINT tag_pk PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public.tags OWNER TO postgres;
-- ddl-end --

-- object: public.don | type: TABLE --
-- DROP TABLE IF EXISTS public.don CASCADE;
CREATE TABLE public.don(
	id serial NOT NULL,
	"dateDon" timestamp,
	montant smallint,
	commentaire varchar,
	id_photo integer,
	CONSTRAINT commande_pk PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public.don OWNER TO postgres;
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

-- object: public.photographe | type: TABLE --
-- DROP TABLE IF EXISTS public.photographe CASCADE;
CREATE TABLE public.photographe(
	id serial NOT NULL,
	nom varchar,
	prenom varchar,
	pseudo varchar,
	email varchar,
	password varchar,
	id_authorities integer,
	CONSTRAINT photographe_pk PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public.photographe OWNER TO postgres;
-- ddl-end --

-- object: photographe_fk | type: CONSTRAINT --
-- ALTER TABLE public.photo DROP CONSTRAINT IF EXISTS photographe_fk CASCADE;
ALTER TABLE public.photo ADD CONSTRAINT photographe_fk FOREIGN KEY (id_photographe)
REFERENCES public.photographe (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: photo_fk | type: CONSTRAINT --
-- ALTER TABLE public.don DROP CONSTRAINT IF EXISTS photo_fk CASCADE;
ALTER TABLE public.don ADD CONSTRAINT photo_fk FOREIGN KEY (id_photo)
REFERENCES public.photo (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: authorities_fk | type: CONSTRAINT --
-- ALTER TABLE public.photographe DROP CONSTRAINT IF EXISTS authorities_fk CASCADE;
ALTER TABLE public.photographe ADD CONSTRAINT authorities_fk FOREIGN KEY (id_authorities)
REFERENCES public.authorities (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: public.commentaires | type: TABLE --
-- DROP TABLE IF EXISTS public.commentaires CASCADE;
CREATE TABLE public.commentaires(
	id serial NOT NULL,
	commentaires varchar,
	id_photo integer,
	id_photographe integer,
	CONSTRAINT commentaires_pk PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public.commentaires OWNER TO postgres;
-- ddl-end --

-- object: photo_fk | type: CONSTRAINT --
-- ALTER TABLE public.commentaires DROP CONSTRAINT IF EXISTS photo_fk CASCADE;
ALTER TABLE public.commentaires ADD CONSTRAINT photo_fk FOREIGN KEY (id_photo)
REFERENCES public.photo (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: public.many_tags_has_many_photo | type: TABLE --
-- DROP TABLE IF EXISTS public.many_tags_has_many_photo CASCADE;
CREATE TABLE public.many_tags_has_many_photo(
	id_tags integer NOT NULL,
	id_photo integer NOT NULL,
	CONSTRAINT many_tags_has_many_photo_pk PRIMARY KEY (id_tags,id_photo)

);
-- ddl-end --

-- object: tags_fk | type: CONSTRAINT --
-- ALTER TABLE public.many_tags_has_many_photo DROP CONSTRAINT IF EXISTS tags_fk CASCADE;
ALTER TABLE public.many_tags_has_many_photo ADD CONSTRAINT tags_fk FOREIGN KEY (id_tags)
REFERENCES public.tags (id) MATCH FULL
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: photo_fk | type: CONSTRAINT --
-- ALTER TABLE public.many_tags_has_many_photo DROP CONSTRAINT IF EXISTS photo_fk CASCADE;
ALTER TABLE public.many_tags_has_many_photo ADD CONSTRAINT photo_fk FOREIGN KEY (id_photo)
REFERENCES public.photo (id) MATCH FULL
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: photographe_fk | type: CONSTRAINT --
-- ALTER TABLE public.commentaires DROP CONSTRAINT IF EXISTS photographe_fk CASCADE;
ALTER TABLE public.commentaires ADD CONSTRAINT photographe_fk FOREIGN KEY (id_photographe)
REFERENCES public.photographe (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --


