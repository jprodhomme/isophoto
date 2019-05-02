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

SET search_path TO pg_catalog,db_isophoto,db_isophoto;
-- ddl-end --

-- object: db_isophoto.photo | type: TABLE --
-- DROP TABLE IF EXISTS db_isophoto.photo CASCADE;
CREATE TABLE db_isophoto.photo(
	id serial NOT NULL,
	titre varchar,
	image varchar,
	description varchar,
	id_photographe integer,
	CONSTRAINT photo_pk PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE db_isophoto.photo OWNER TO postgres;
-- ddl-end --

-- object: db_isophoto.tags | type: TABLE --
-- DROP TABLE IF EXISTS db_isophoto.tags CASCADE;
CREATE TABLE db_isophoto.tags(
	id serial NOT NULL,
	tag varchar,
	CONSTRAINT tag_pk PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE db_isophoto.tags OWNER TO postgres;
-- ddl-end --

-- object: db_isophoto.don | type: TABLE --
-- DROP TABLE IF EXISTS db_isophoto.don CASCADE;
CREATE TABLE db_isophoto.don(
	id serial NOT NULL,
	"dateDon" timestamp,
	montant smallint,
	commentaire varchar,
	id_photo integer,
	CONSTRAINT commande_pk PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE db_isophoto.don OWNER TO postgres;
-- ddl-end --

-- object: db_isophoto.authorities | type: TABLE --
-- DROP TABLE IF EXISTS db_isophoto.authorities CASCADE;
CREATE TABLE db_isophoto.authorities(
	id serial NOT NULL,
	role varchar,
	CONSTRAINT authorities_pk PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE db_isophoto.authorities OWNER TO postgres;
-- ddl-end --

-- object: db_isophoto.photographe | type: TABLE --
-- DROP TABLE IF EXISTS db_isophoto.photographe CASCADE;
CREATE TABLE db_isophoto.photographe(
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
ALTER TABLE db_isophoto.photographe OWNER TO postgres;
-- ddl-end --

-- object: photographe_fk | type: CONSTRAINT --
-- ALTER TABLE db_isophoto.photo DROP CONSTRAINT IF EXISTS photographe_fk CASCADE;
ALTER TABLE db_isophoto.photo ADD CONSTRAINT photographe_fk FOREIGN KEY (id_photographe)
REFERENCES db_isophoto.photographe (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: photo_fk | type: CONSTRAINT --
-- ALTER TABLE db_isophoto.don DROP CONSTRAINT IF EXISTS photo_fk CASCADE;
ALTER TABLE db_isophoto.don ADD CONSTRAINT photo_fk FOREIGN KEY (id_photo)
REFERENCES db_isophoto.photo (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: authorities_fk | type: CONSTRAINT --
-- ALTER TABLE db_isophoto.photographe DROP CONSTRAINT IF EXISTS authorities_fk CASCADE;
ALTER TABLE db_isophoto.photographe ADD CONSTRAINT authorities_fk FOREIGN KEY (id_authorities)
REFERENCES db_isophoto.authorities (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: db_isophoto.commentaires | type: TABLE --
-- DROP TABLE IF EXISTS db_isophoto.commentaires CASCADE;
CREATE TABLE db_isophoto.commentaires(
	id serial NOT NULL,
	commentaires varchar,
	id_photo integer,
	CONSTRAINT commentaires_pk PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE db_isophoto.commentaires OWNER TO postgres;
-- ddl-end --

-- object: photo_fk | type: CONSTRAINT --
-- ALTER TABLE db_isophoto.commentaires DROP CONSTRAINT IF EXISTS photo_fk CASCADE;
ALTER TABLE db_isophoto.commentaires ADD CONSTRAINT photo_fk FOREIGN KEY (id_photo)
REFERENCES db_isophoto.photo (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: db_isophoto.many_tags_has_many_photo | type: TABLE --
-- DROP TABLE IF EXISTS db_isophoto.many_tags_has_many_photo CASCADE;
CREATE TABLE db_isophoto.many_tags_has_many_photo(
	id_tags integer NOT NULL,
	id_photo integer NOT NULL,
	CONSTRAINT many_tags_has_many_photo_pk PRIMARY KEY (id_tags,id_photo)

);
-- ddl-end --

-- object: tags_fk | type: CONSTRAINT --
-- ALTER TABLE db_isophoto.many_tags_has_many_photo DROP CONSTRAINT IF EXISTS tags_fk CASCADE;
ALTER TABLE db_isophoto.many_tags_has_many_photo ADD CONSTRAINT tags_fk FOREIGN KEY (id_tags)
REFERENCES db_isophoto.tags (id) MATCH FULL
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: photo_fk | type: CONSTRAINT --
-- ALTER TABLE db_isophoto.many_tags_has_many_photo DROP CONSTRAINT IF EXISTS photo_fk CASCADE;
ALTER TABLE db_isophoto.many_tags_has_many_photo ADD CONSTRAINT photo_fk FOREIGN KEY (id_photo)
REFERENCES db_isophoto.photo (id) MATCH FULL
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --
