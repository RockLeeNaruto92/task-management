-- Database: "task-management"

-- DROP DATABASE "task-management";

CREATE DATABASE "task-management"
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'English_United States.1252'
       LC_CTYPE = 'English_United States.1252'
       CONNECTION LIMIT = -1;


       
-- Table: project

-- DROP TABLE project;

CREATE TABLE project
(
  id integer NOT NULL, -- Project id
  name text,
  description text,
  customer text,
  "startDate" date,
  "expectedEndDate" date,
  CONSTRAINT pk_project PRIMARY KEY (id )
)
WITH (
  OIDS=FALSE
);
ALTER TABLE project
  OWNER TO postgres;
COMMENT ON COLUMN project.id IS 'Project id';


-- Table: "user"

-- DROP TABLE "user";

CREATE TABLE "user"
(
  username text NOT NULL,
  fullname text,
  "doB" date,
  "hiredDate" date,
  gender integer,
  "position" text,
  manager text,
  CONSTRAINT pk_user PRIMARY KEY (username ),
  CONSTRAINT fk_user_2_user FOREIGN KEY (manager)
      REFERENCES "user" (username) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "user"
  OWNER TO postgres;

  
-- Table: project_user

-- DROP TABLE project_user;

CREATE TABLE project_user
(
  projectid integer NOT NULL,
  username text NOT NULL,
  CONSTRAINT pk_project_user PRIMARY KEY (projectid , username ),
  CONSTRAINT fk_projectuser_2_project FOREIGN KEY (projectid)
      REFERENCES project (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_projectuser_2_user FOREIGN KEY (username)
      REFERENCES "user" (username) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE project_user
  OWNER TO postgres;


-- Table: skill

-- DROP TABLE skill;

CREATE TABLE skill
(
  id integer NOT NULL,
  name text,
  category integer,
  description text,
  status boolean,
  CONSTRAINT pk_skill PRIMARY KEY (id ),
  CONSTRAINT fk_skill_2_skill_category FOREIGN KEY (category)
      REFERENCES skill_category (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE skill
  OWNER TO postgres;

  

-- Table: project_skill

-- DROP TABLE project_skill;

CREATE TABLE project_skill
(
  project_id integer NOT NULL,
  skill_id integer NOT NULL,
  level integer,
  experience_year integer,
  CONSTRAINT pk_project_id PRIMARY KEY (project_id , skill_id ),
  CONSTRAINT fk_project_skill_2_project FOREIGN KEY (project_id)
      REFERENCES project (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_project_skill_2_skill FOREIGN KEY (skill_id)
      REFERENCES skill (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE project_skill
  OWNER TO postgres;
  

  
-- Table: user_skill

-- DROP TABLE user_skill;

CREATE TABLE user_skill
(
  username text NOT NULL,
  skill_id integer NOT NULL,
  level integer,
  year_of_experience integer,
  CONSTRAINT pk_user_skill PRIMARY KEY (username , skill_id ),
  CONSTRAINT fk_user_skill_2_skill FOREIGN KEY (skill_id)
      REFERENCES skill (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_user_skill_2_user FOREIGN KEY (username)
      REFERENCES "user" (username) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE user_skill
  OWNER TO postgres;

-- Index: fki_user_skill_2_skill

-- DROP INDEX fki_user_skill_2_skill;

CREATE INDEX fki_user_skill_2_skill
  ON user_skill
  USING btree
  (skill_id );

-- Index: fki_user_skill_2_user

-- DROP INDEX fki_user_skill_2_user;

CREATE INDEX fki_user_skill_2_user
  ON user_skill
  USING btree
  (username COLLATE pg_catalog."default" );


-- Table: milestone

-- DROP TABLE milestone;

CREATE TABLE milestone
(
  id integer NOT NULL,
  name text,
  "dueDate" date,
  version_number text,
  project_id integer,
  CONSTRAINT pk_milestone PRIMARY KEY (id ),
  CONSTRAINT fk_milestone_2_project FOREIGN KEY (project_id)
      REFERENCES project (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE milestone
  OWNER TO postgres;

  
-- Table: task_type

-- DROP TABLE task_type;

CREATE TABLE task_type
(
  id integer NOT NULL,
  name text,
  kind_of_task integer,
  CONSTRAINT pk_task_type PRIMARY KEY (id )
)
WITH (
  OIDS=FALSE
);
ALTER TABLE task_type
  OWNER TO postgres;

  
-- Table: project_tasktype

-- DROP TABLE project_tasktype;

CREATE TABLE project_tasktype
(
  project_id integer NOT NULL,
  task_type_id integer NOT NULL,
  CONSTRAINT pk_project_tasktype PRIMARY KEY (project_id , task_type_id ),
  CONSTRAINT fk_project_tasktype_2_project FOREIGN KEY (project_id)
      REFERENCES project (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_project_tasktype_2_tasktype FOREIGN KEY (task_type_id)
      REFERENCES task_type (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE project_tasktype
  OWNER TO postgres;
