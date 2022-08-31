DROP ALL OBJECTS DELETE FILES;
alter table if exists users_friends
        drop constraint if exists foreignKeyUsers;
alter table if exists users_friends
        drop constraint if exists uniqueFriendId;
alter table if exists users_friends
        drop constraint if exists foreignKeyFriends;


drop table if exists gossip CASCADE;
drop table if exists users CASCADE;
drop table if exists users_friends CASCADE;

drop sequence if exists hibernate_sequence;

create sequence hibernate_sequence start with 1 increment by 1;

create table gossip ( id bigint not null default hibernate_sequence.nextval,
                      date timestamp,
                      text varchar(255),
                      user_id bigint,
                      primary key (id));

create table users (id bigint not null default hibernate_sequence.nextval,
                    email varchar(255),
                    name varchar(255),
                    password varchar(255),
                    username varchar(255),
                    gossip_count int,
                    primary key (id));

create table users_friends (user_id bigint not null,
                            friends_id bigint not null);

create table users_gossips (
                               user_id bigint not null,
                               gossips_id bigint not null);

alter table users
       add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email) ;

alter table users
       add constraint UK_r43af9ap4edm43mmtq01oddj6 unique (username) ;

alter table users_gossips
    add constraint UK_3fyp3nci99gxxt0tb6aqltfqv unique (gossips_id);

alter table gossip
       add constraint FKajxq9rb5ypn5y3qpjopd8vtoq
       foreign key (user_id)
       references users;

alter table users_friends
       add constraint FKo51ktjiheso8mkdd5n4pdf9f3
       foreign key (friends_id)
       references users;

alter table users_friends
       add constraint FKry5pun2eg852sbl2l50p236bo
       foreign key (user_id)
       references users;

alter table users_gossips
    add constraint FKt1k1746372hvo31s2lnyamggu
        foreign key (gossips_id)
            references gossip;

alter table users_gossips
    add constraint FKri50o1dcbu1d6fq9r8m5kuoqj
        foreign key (user_id)
            references users;