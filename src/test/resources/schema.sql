-- DROP TABLE contact;

CREATE TABLE contact (
	id uuid NOT NULL DEFAULT gen_random_uuid(),
	create_date_time timestamp NULL,
	update_date_time timestamp NULL,
	given_name varchar(100) NOT NULL,
	middle_name varchar(100) NULL,
	suffix varchar(100) NULL,
	surname varchar(100) NOT NULL,
	CONSTRAINT contact_pkey PRIMARY KEY (id)
);