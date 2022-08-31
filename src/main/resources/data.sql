insert into `users` (id,email, username,name, password)
--                                               password = admin , for all users
values  (1,'user@starit.bg','user','nikolay','$2a$10$pblF9BftNYu0JUkNGJawwurRwErPb4ygrKoXqi4x80ZmErQBBaheu'),
        (2,'admin@starit.bg','admin','ivan','$2a$10$pblF9BftNYu0JUkNGJawwurRwErPb4ygrKoXqi4x80ZmErQBBaheu'),
        (3,'someone@starit.bg','someone','gergana','$2a$10$pblF9BftNYu0JUkNGJawwurRwErPb4ygrKoXqi4x80ZmErQBBaheu');

insert into `users_friends` (user_id, friends_id)
values  (1,2),
        (2,1),
        (1,3),
        (3,2);

insert into `gossip` (date,text,user_id)
values  (parsedatetime('17-09-2012 18:47:52.690', 'dd-MM-yyyy hh:mm:ss.SS'),'some gossip',3),
        (parsedatetime('13-09-2014 18:47:52.690', 'dd-MM-yyyy hh:mm:ss.SS'),'another gossip',2),
        (parsedatetime('17-01-2011 18:47:52.690', 'dd-MM-yyyy hh:mm:ss.SS'),'yet another gossip',3),
        (parsedatetime('22-09-2001 18:47:52.690', 'dd-MM-yyyy hh:mm:ss.SS'),'gossiping some gossips',3),
        (parsedatetime('01-09-2022 18:47:52.690', 'dd-MM-yyyy hh:mm:ss.SS'),'gossip gossip gossip',3),
        (parsedatetime('04-12-2019 18:47:52.690', 'dd-MM-yyyy hh:mm:ss.SS'),'gossip',3),
        (parsedatetime('17-09-2013 18:47:52.690', 'dd-MM-yyyy hh:mm:ss.SS'),'this is a gossip',2),
        (parsedatetime('30-05-2006 18:47:52.690', 'dd-MM-yyyy hh:mm:ss.SS'),'what a gossip',2),
        (parsedatetime('31-02-2018 18:47:52.690', 'dd-MM-yyyy hh:mm:ss.SS'),'are you gossiping',2),
        (parsedatetime('10-07-2009 18:47:52.690', 'dd-MM-yyyy hh:mm:ss.SS'),'gossiping is bad',3),
        (parsedatetime('07-10-2017 18:47:52.690', 'dd-MM-yyyy hh:mm:ss.SS'),'gossiping is good',3),
        (parsedatetime('03-11-2016 18:47:52.690', 'dd-MM-yyyy hh:mm:ss.SS'),'why are you gossiping',3),
        (parsedatetime('09-04-2021 18:47:52.690', 'dd-MM-yyyy hh:mm:ss.SS'),'some gossip',3);