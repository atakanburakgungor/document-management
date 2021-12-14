create table article
(
    id uuid not null
        constraint article_pkey
            primary key,
    created_by varchar(255),
    created_date timestamp,
    updated_by varchar(255),
    updated_date timestamp,
    article_content varchar(255),
    author varchar(255),
    publish_date timestamp,
    star_count integer not null,
    title varchar(255)
);

create table review
(
    id uuid not null
        constraint review_pkey
            primary key,
    review_content varchar(255),
    reviewer varchar(255),
    article_id uuid
        constraint fk8klm4xeeoxyah8iy90xt2svgp
            references article
);

