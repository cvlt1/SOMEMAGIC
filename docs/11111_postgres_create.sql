CREATE TABLE "page" (
	"id" serial NOT NULL,
	"temlate_id" serial NOT NULL,
	"path" varchar(255) NOT NULL,
	"site_id" serial(255) NOT NULL,
	"moderated_by" bigint NOT NULL,
	"created" timestamp with time zone NOT NULL,
	"updated" timestamp with time zone NOT NULL,
	CONSTRAINT "page_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "site" (
	"id" serial NOT NULL,
	"name" varchar(255) NOT NULL,
	"basepath" varchar(255) NOT NULL,
	"created" timestamp with time zone NOT NULL,
	"updated" timestamp with time zone NOT NULL,
	CONSTRAINT "site_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "template" (
	"id" serial NOT NULL,
	"jsp_path" serial NOT NULL UNIQUE,
	"created" timestamp with time zone NOT NULL,
	"updated" timestamp with time zone NOT NULL,
	CONSTRAINT "template_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "page_item" (
	"id" serial NOT NULL,
	"item_id" serial NOT NULL,
	"page_id" serial NOT NULL,
	"position" serial NOT NULL,
	"created" timestamp with time zone NOT NULL,
	"updated" timestamp with time zone NOT NULL,
	CONSTRAINT "page_item_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "content_item" (
	"id" serial NOT NULL,
	"html" varchar(255) NOT NULL,
	"created" timestamp with time zone NOT NULL,
	"updated" timestamp with time zone NOT NULL,
	CONSTRAINT "content_item_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "user_account" (
	"id" serial NOT NULL,
	"name" varchar(255) NOT NULL UNIQUE,
	"email" varchar(255) NOT NULL UNIQUE,
	"password" varchar(255) NOT NULL,
	"role" serial(255) NOT NULL,
	"status" serial(255) NOT NULL,
	"created" time with time zone NOT NULL,
	"updated" time with time zone NOT NULL,
	CONSTRAINT "user_account_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "post" (
	"id" serial NOT NULL,
	"name" varchar(255) NOT NULL,
	"page_id" serial NOT NULL,
	"status" varchar(255) NOT NULL,
	"created" timestamp with time zone NOT NULL,
	"updated" timestamp with time zone NOT NULL,
	"posted_by" varchar(255) NOT NULL,
	CONSTRAINT "post_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



ALTER TABLE "page" ADD CONSTRAINT "page_fk0" FOREIGN KEY ("temlate_id") REFERENCES "template"("id");
ALTER TABLE "page" ADD CONSTRAINT "page_fk1" FOREIGN KEY ("moderated_by") REFERENCES "user_account"("id");

ALTER TABLE "site" ADD CONSTRAINT "site_fk0" FOREIGN KEY ("id") REFERENCES "page"("site_id");


ALTER TABLE "page_item" ADD CONSTRAINT "page_item_fk0" FOREIGN KEY ("item_id") REFERENCES "content_item"("id");
ALTER TABLE "page_item" ADD CONSTRAINT "page_item_fk1" FOREIGN KEY ("page_id") REFERENCES "page"("id");



ALTER TABLE "post" ADD CONSTRAINT "post_fk0" FOREIGN KEY ("page_id") REFERENCES "page"("id");
ALTER TABLE "post" ADD CONSTRAINT "post_fk1" FOREIGN KEY ("posted_by") REFERENCES "user_account"("id");

