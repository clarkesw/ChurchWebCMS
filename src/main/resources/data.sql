/*
insert into address
values (1, 'Smyrna', 'GA', '23 Millford Church Rd',30060); 

insert into calendarevent
values (1,'stufff', '2019-05-19T01:00',2019-05-19,'1:00am',false, '2019-05-19T03:30', 2019-05-19, '3:30am','Chillin','/event/4'); 

// Just use below.
*/
CREATE DATABASE ChurchDB;

insert into church
values (1,'Founded in 1882 we are the oldest Baptist Church in Smyrna, GA.', 'church@milford.com',null,null,'C3','Milford Baptist','919-368-6332',null,null,null,null);

insert into user
values (2,'OtszX/SBSG/JvVrUwvNVDD5N8rrKNayA43Wf1h4IVE4=','ADMIN','clarke');

insert into staff
values (1,null, "Clarke", "Clarke Swindell","clarke.job@gmail.com",null, true,"Swindell","Taylor","@mms.att.net","4044312941","Master",null,null,"IT","4044312941",true,true,"me@church.com","777",null,2,null);

insert into staff
values (3,null, "Pastor", "Pastor Perry","clarke.job@gmail.com",null, true,"Perry",null,"@mms.att.net","433","Master",null,null,"Lead Pastor","515",true,true,"me@church.com","515",null,null,null);

INSERT INTO ChurchDB.staff (id, bio, first_name, full_name, home_email, home_phone, is_admin, last_name, middle_name, mobile_carrier, mobile_phone, name_preffix, name_suffix, photo, `position`, preffered_contact, recieve_church_updates, recieve_prayer_requests, work_email, work_phone, address_id, user_id, sid) 
	VALUES (3, 'Paul Earl Sheppard has been preaching since his teens and has been in pastoral ministry since 1982. He served as associate pastor of West Oak Lane Church of God in Philadelphia, PA for seven years and as senior pastor of Abundant Life Christian Fellowship in Mountain View, CA for twenty years. Presently, Pastor Paul serves as Senior Pastor of Destiny Christian Fellowship in Fremont, CA.
Pastor Paul is a native of Philadelphia, PA. He studied at the University of Pennsylvania, the Center for Urban Theological Studies, and the Southern CA School of Ministry, from which he earned a Master in Ministry degree and was later honored with a Doctorate of Divinity.
An effective communicator of God’s Word, Pastor Paul is widely known for his practical and dynamic teaching style which helps people apply the timeless truths of Scripture to their everyday lives. He serves as speaker for the radio and online broadcast Destined for Victory. He’s also an author whose books include Rebuilding What the Enemy Almost Destroyed, Why God Created Dads, and Build a Bridge and Get Over It. 
He and his wife, Meredith, have been married since 1982. They are the proud parents of two adult children who work alongside them in ministry, Alicia Greer and Aaron Sheppard. ', 'Rev', 'Rev Lovejoy', 'clarke.job@gmail.com', '', false, 'Lovejoy', '', NULL, '433', NULL, NULL, '../images/Lovejoy.jpg', 'Lead Pastor', 'NONE', true, true, 'me@church.com', '515', NULL, NULL, NULL);

INSERT INTO ChurchDB.staff (id, bio, first_name, full_name, home_email, home_phone, is_admin, last_name, middle_name, mobile_carrier, mobile_phone, name_preffix, name_suffix, photo, `position`, preffered_contact, recieve_church_updates, recieve_prayer_requests, work_email, work_phone, address_id, user_id, sid) 
	VALUES (4, ' Deacon William Hassler is a native of Allentown and graduated from ACCHS. After a stint in the Marine Corps, he completed four years at Kutztown University, graduating with a major in English. Deacon Hassler spent most of his career in marketing communications, having worked 31 years for Air Products and Chemicals, Inc. as an Advertising Manager for the company''s Chemicals Group. In 1995, he entered the Permanent Diaconate program, was ordained in 2000, and has served here at the Cathedral ever since. Deacon Hassler is married and has two children and three grandchildren. ', 'Ned', 'Ned Flanders', '', '', false, 'Flanders', '', NULL, '23434', NULL, NULL, '../images/ned-flanders.webp', 'Deacon', 'NONE', false, true, 'ch@milford.com', '', NULL, NULL, NULL);
